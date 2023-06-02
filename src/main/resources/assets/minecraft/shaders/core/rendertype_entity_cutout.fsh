#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform float GameTime;

in float vertexDistance;
in vec4 lightColor;
in vec4 overlayColor;
in vec2 texCoord0;
in vec3 normal;
in vec3 position;
in float isGui;

#moj_import <pokemoneffects.glsl>

out vec4 fragColor;

void main() {
    ivec2 texSize = textureSize(Sampler0, 0);
    
    vec4 color = texture(Sampler0, texCoord0);
    if(color.a < 0.1) discard;

    SurfaceData surface;
    surface.texSize = textureSize(Sampler0, 0);
    surface.albedo = color.rgb * overlayColor.rgb;
    surface.alpha = (color.a * overlayColor.a) < 0.1 ? 0.0 : 1.0;
    surface.position = position;
    surface.normal = normalize(normal);
    surface.uv = texCoord0;
    surface.emissive = 0.0;
    surface.isGui = isGui;

    if(isGui > 0.5) surface.position = vec3(1.0, -1.0, -3.0);

    if(texSize.x < 512) applyEffects(surface, uint(round(color.a * 255.0)));
    if(getDither(ivec2(gl_FragCoord.xy)) > surface.alpha) discard;

    fragColor = linear_fog(vec4(surface.albedo, 1.0) * mix(lightColor, vec4(1.0), clamp(surface.emissive, 0.0, 1.0)), vertexDistance, FogStart, FogEnd, FogColor);
}
