#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;
uniform float LumiTransparency;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform float GameTime;
uniform float Speed;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

vec4 transformColor(vec4 initialColor, float lumiTransparent) {
    initialColor = initialColor.rgb == vec3(0, 0, 0) ? vec4(0,0,0,0) : initialColor;
    initialColor = lumiTransparent == 1. ? vec4(initialColor.xyz, 0.21 * initialColor.r + 0.71 * initialColor.g + 0.07 * initialColor.b) : initialColor;
    return initialColor;
}

vec4 applyFog(vec4 initialColor, float fogStart, float fogEnd, vec4 fogColor, float vertexDistance) {
    return linear_fog(vec4(initialColor.rgb, initialColor.a*linear_fog_fade(vertexDistance, fogStart, fogEnd)), vertexDistance, fogStart, fogEnd, vec4(fogColor.rgb, initialColor.r));
}

void main() {
    vec2 uv = texCoord0;
    uv.y += GameTime*Speed;
    vec4 color = transformColor(texture(Sampler0, uv) * vertexColor * ColorModulator, LumiTransparency);
    fragColor = applyFog(color, FogStart, FogEnd, FogColor, vertexDistance);
}