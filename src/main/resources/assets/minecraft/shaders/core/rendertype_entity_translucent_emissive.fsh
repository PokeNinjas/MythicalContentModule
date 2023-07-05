#version 150
#define FSH
#define RENDERTYPE_ENTITY_CUTOUT

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform float GameTime;
uniform vec2 ScreenSize;

in float vertexDistance;
in vec4 baseColor;
in vec4 lightColor;
in vec4 normalLightColor;
in vec2 texCoord0;
in vec3 normal;
in vec3 position;
in vec2 corner;

out vec4 fragColor;

#moj_import <pokemoneffects.glsl>

void main() {
    vec4 color = texture(Sampler0, texCoord0) * baseColor;
    if(color.a < 0.1) discard;

    SurfaceData surface;
    surface.texSize = textureSize(Sampler0, 0);
    surface.albedo = color.rgb;
    surface.alpha = color.a == 0.0 ? 0.0 : 1.0;
    surface.position = position;
    surface.normal = normal;
    surface.uv = texCoord0;
    surface.corner = corner;
    surface.isGui = FogStart > 2000.0;
    surface.emissive = 0.0;
    surface.normalEmissive = 0.0;
    surface.dyeColor = vec3(1.0);

    if(surface.isGui) surface.position = vec3(1.0, -1.0, -3.0);

    if(max(surface.texSize.x, surface.texSize.y) < 512.0)
        applyEffects(surface, uint(round(color.a * 255.0)));

    if(getDither(ivec2(gl_FragCoord.xy)) > surface.alpha) discard;

    vec4 diffuseLight = lightColor * mix(normalLightColor, vec4(1.0), surface.normalEmissive);
    fragColor = vec4(surface.albedo, surface.alpha) * mix(diffuseLight, vec4(1.0), surface.emissive) * ColorModulator;
    fragColor = linear_fog(fragColor, vertexDistance, FogStart, FogEnd, FogColor);
}
