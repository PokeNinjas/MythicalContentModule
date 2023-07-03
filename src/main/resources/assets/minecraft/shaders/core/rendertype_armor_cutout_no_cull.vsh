#version 150
#define VSH
#define RENDERTYPE_ARMOR_CUTOUT_NO_CULL

#moj_import <light.glsl>
#moj_import <fog.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in vec2 UV1;
in ivec2 UV2;
in vec3 Normal;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform int FogShape;

uniform vec3 Light0_Direction;
uniform vec3 Light1_Direction;

out float vertexDistance;
out vec4 baseColor;
out vec4 lightColor;
out vec4 normalLightColor;
out vec2 texCoord0;
out vec3 normal;
out vec3 position;
out vec2 corner;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexDistance = fog_distance(ModelViewMat, Position, FogShape);

    baseColor = Color;
    lightColor = texelFetch(Sampler2, UV2 / 16, 0);
    normalLightColor = minecraft_mix_light(Light0_Direction, Light1_Direction, Normal, vec4(1.0));
    texCoord0 = UV0;
    normal = Normal;
    position = Position;
    corner = vec2[](vec2(-1.0, +1.0), vec2(-1.0, -1.0), vec2(+1.0, -1.0), vec2(+1.0, +1.0))[gl_VertexID % 4];
}
