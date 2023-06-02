#version 150

#moj_import <light.glsl>
#moj_import <fog.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV1;
in ivec2 UV2;
in vec3 Normal;

uniform sampler2D Sampler1;
uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform mat3 IViewRotMat;
uniform int FogShape;
uniform float FogStart;

uniform vec3 Light0_Direction;
uniform vec3 Light1_Direction;

out float vertexDistance;
out vec4 lightColor;
out vec4 overlayColor;
out vec2 texCoord0;
out vec3 normal;
out vec3 position;
out float isGui;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexDistance = fog_distance(ModelViewMat, IViewRotMat * Position, FogShape);
    lightColor = texelFetch(Sampler2, UV2 / 16, 0) * minecraft_mix_light(Light0_Direction, Light1_Direction, Normal, Color);
    overlayColor = texelFetch(Sampler1, UV1, 0);
    texCoord0 = UV0;
    normal = Normal;
    position = Position;
    isGui = FogStart > 2000.0 ? 1.0 : 0.0;
}