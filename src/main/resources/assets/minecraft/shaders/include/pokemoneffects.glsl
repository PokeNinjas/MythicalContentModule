#version 150

#moj_import<spheya_utils.glsl>

struct SurfaceData { 
    ivec2 texSize;
    vec3 albedo;
    float alpha;
    vec3 position;
    vec3 normal;
    vec2 uv;
    vec2 corner;
    bool isGui;
    float emissive;
    float normalEmissive;
    vec3 dyeColor;
};

void applyRadiantEffect(inout SurfaceData surface) {
    float fresnel = fresnel(surface.normal, -normalize(surface.position), CALC_R0(1.5));
    surface.albedo = mix(surface.albedo, luminance(gamma(surface.albedo)) * hsvToRgb(vec3((surface.uv.x + surface.uv.y) * 5.0 + GameTime * 400.0, 0.7, 1.0)), 0.3);
    surface.albedo = mix(surface.albedo, vec3(1.0), fresnel);
}

bool checkMagmaParticle(ivec2 pixel, float frequency) {
    return random(vec2(pixel)) < frequency; 
}

void applyMagmaLayer(inout SurfaceData surface, float scale, float alpha, float frequency) {
    ivec2 pixelPos = ivec2(surface.uv * scale * surface.texSize - vec2(0.0, GameTime * 1200.0 * 12));

    for(int i = 0; i < 12; i++) {
        if(checkMagmaParticle(pixelPos + ivec2(0,i), frequency) || checkMagmaParticle(pixelPos + ivec2(1,1 + i), frequency) || checkMagmaParticle(pixelPos + ivec2(-1,1 + i), frequency)) {
            surface.albedo += hsvToRgb(mix(vec3(0.16, 0.6, 1), vec3(-0.0444, 0.75, 0.20), (i-1.0) / 11.0) + ivec3(0.0, 0.3, 0.0)) * alpha;
            if(i == 0.0) surface.albedo = vec3(1.0, 0.95, 0.85);
            surface.emissive = (1.0 - i / 11.0) * alpha;
            return;
        }
    }
}

void applyMagmaEffect(inout SurfaceData surface) {
    surface.albedo = luminance(surface.albedo) * rgb(130, 0, 40);
    applyMagmaLayer(surface, 1.0, 0.7, 0.025);
    applyMagmaLayer(surface, 2.0, 0.7, 0.015);
}

void applyGlitchEffect(inout SurfaceData surface) {
    vec2 offset = (0.2 + 0.1 * vec2(noise(GameTime * 20000.0), noise(GameTime * 20000.0 + 10000.0))) / surface.texSize;

    if(mod(GameTime * 1200, 1.0) < 0.1) offset += 0.5 * vec2(random(GameTime), random(GameTime + 10.0)) / surface.texSize;

    float scanline1 = int((surface.uv.y + offset.y * 0.25) * surface.texSize.y + GameTime * 1200.0) & 0x1;
    float scanline2 = int((surface.uv.y - offset.y * 0.25) * surface.texSize.y + GameTime * 1200.0) & 0x1;

    vec4 sample1 = texture(Sampler0, surface.uv + offset);
    if(sample1.a == 0.0) sample1.rgb = vec3(0.0);
    vec4 sample2 = texture(Sampler0, surface.uv - offset);
    if(sample2.a == 0.0) sample2.rgb = vec3(0.0);

    surface.albedo = 
        sample1.rgb * vec3(1.0, 0.5, 0.0) + 
        sample2.rgb * vec3(0.0, 0.5, 1.0) +
        scanline1 * vec3(0.0, 0.5, 1.0) * 0.15 +
        scanline2 * vec3(0.5, 0.5, 0.0) * 0.15;
}

vec3 starLayer(vec2 uv, float scale, vec2 direction, float rarity) {
    uv = uv * scale + direction * GameTime * 1200.0;
    ivec2 pixel = ivec2(floor(uv));
    if(random(pixel) < rarity) {
        uv = (uv - pixel) - 0.5;
        uv = rotateUv(uv, (random(pixel - 1) * 2.0 - 1.0) * GameTime * 1200.0);

        ivec2 starPixel = ivec2(round(uv * 5.0));
        float f = max(abs(starPixel.x), abs(starPixel.y));
        if((starPixel.x != 0 && starPixel.y != 0) || f > 2) return vec3(0.0);

        vec3 waveLengths = vec3(random(pixel + 1), random(pixel + 2), random(pixel + 3));
        waveLengths = 1.0 - 0.4 * waveLengths * waveLengths;
        return vec3(exp(-f * waveLengths));
    }
    return vec3(0.0);
}

void applyGalaxyEffect(inout SurfaceData surface) {
    float brightness = luminance(surface.albedo);
    surface.albedo = vec3(0.0);
    float fresnel = fresnel(surface.normal, -normalize(surface.position), CALC_R0(1.5));

    vec2 uv = surface.uv * surface.texSize;
    surface.albedo += starLayer(uv, 0.5, 1.25 *vec2(1.0, -0.5), 0.1);
    surface.albedo += starLayer(uv, 1.0, 1.00 *vec2(1.0, -0.5), 0.075) * 0.75;
    surface.albedo += starLayer(uv, 2.0, 0.75 * vec2(1.0, -0.5), 0.05) * 0.5;

    surface.albedo += fresnel * 0.5;

    surface.emissive = luminance(surface.albedo);
    surface.albedo += mix(vec3(0.0, 0.0, 0.1), vec3(0.3, 0.25, 0.3), brightness);
}

bool segmentDisplay(ivec2 pixel, uint value) {
    if(pixel.x < 0 || pixel.x > 2 || pixel.y < 0 || pixel.y > 4) return false;

    if(bool(value & 0x01u) && pixel.y == 0) return true;
    if(bool(value & 0x02u) && pixel.x == 2 && pixel.y < 3) return true;
    if(bool(value & 0x04u) && pixel.x == 2 && pixel.y > 1) return true;
    if(bool(value & 0x08u) && pixel.y == 4) return true;
    if(bool(value & 0x10u) && pixel.x == 0 && pixel.y > 1) return true;
    if(bool(value & 0x20u) && pixel.x == 0 && pixel.y < 3) return true;
    if(bool(value & 0x40u) && pixel.y == 2) return true;

    return false;
}

vec4 matrixLayer(vec2 uv, float scale, float gap) {
    float seed = floor(uv.x * scale);
    float offsetX = random(seed);
    float offsetY = random(seed + 10.0) * 10.0 + GameTime * 1200.0 * mix(0.25, 1.0, random(seed + 20.0));

    ivec2 gridCoords = ivec2(floor(uv * scale - vec2(0.0, offsetY)));
    ivec2 pixelCoords = ivec2(floor((uv * scale - vec2(0.0, offsetY) - gridCoords) * 6.0 - vec2(mix(0.0, 3.0, offsetX), 0.5)));

    const uint numbers[10] = uint[10]( 0x3Fu, 0x06u, 0x5Bu, 0x4Fu, 0x66u, 0x6Du, 0x7Du, 0x07u, 0x7Fu, 0x6Fu );
    uint n = uint(floor(random(seed + floor(GameTime * 1200 * 6 + 0.5) + gridCoords.y) * 9.99));
    
    float toScanLine = mod(-gridCoords.y + GameTime * 1200 * 6 + floor(random(seed + 30.0) * 20.0), gap);
    float opacity = max(1.0 - (toScanLine / 16.0), 0.3);

    if(segmentDisplay(pixelCoords, numbers[n])) {
        return vec4(mix(vec3(0.25, 1.0, 1.0), vec3(0.6, 1.0, 0.3), opacity), easeIn(opacity));
    }
    return vec4(0.0);
}

void applyMatrixEffect(inout SurfaceData surface) {
    vec4 layer1 = matrixLayer(surface.uv * surface.texSize, 0.4, 16.0) * vec4(1.0, 1.0, 1.0, 1.0);
    vec4 layer2 = matrixLayer(PHI + surface.uv * surface.texSize, 0.4 * PHI, 16.0) * vec4(1.0, 1.0, 1.0, 0.35);

    vec4 finalColor = layerColors(layer1, layer2);

    surface.albedo = layerColors(finalColor, mix(vec4(0.0, 0.0, 0.0, 1.0), vec4(0.0, 0.25, 0.15, 1.0), luminance(surface.albedo))).rgb;
    surface.emissive = finalColor.a;
}

void applyfireWorksLayer(inout SurfaceData surface, float t) {
    float seed = floor(t);
    t = t - seed;

    vec2 position = vec2(random(seed), random(seed + 0.1));
    vec3 color = hsvToRgb(vec3(random(seed + 0.2), 0.7, 1.0));

    float size = 0.6;

    if(t < 0.2) {
        t /= 0.2;
        vec2 pos = position + 5.0 * vec2(0.0, 1.0 - t) / surface.texSize;
        vec2 delta = abs((pos - surface.uv));
        if(delta.x < 0.5 * size / surface.texSize.x && delta.y < 0.5 * size / surface.texSize.y) surface.albedo += t * 0.5;
    }else{
        t = (t - 0.2) / 0.8;
        for(int i = 0; i < 32; i++) {
            for(int j = 0; j < 3; j++) {
                float gradient = (1.0 - t) / 3.0;
                if(gradient < 0.0) continue;
                gradient = 1.0 - easeIn(1.0 - gradient);

                float time = (8.0 - j) * t / 8.0;

                float theta = i * PHI * TAU;//random(seed + i * 0.563632) * TAU;
                float speed = (random(seed + i * 0.123423) * 7.0 + 1.0) * mix(1.0, 0.7, time);
                float rotation = random(seed + i * 0.846316) * TAU + j;
                vec2 pos = position + (vec2(sin(theta), cos(theta)) * speed * time + vec2(0.0, 1.0 * time)) / surface.texSize;

                vec2 delta = abs((pos - surface.uv) * mat2(cos(rotation), sin(rotation), -sin(rotation), cos(rotation)));
                if(delta.x < 0.5 * size / surface.texSize.x && delta.y < 0.5 * size / surface.texSize.y) {
                     surface.albedo += color * gradient;
                     surface.emissive += gradient;
                }
            }
        }
    }
}

void applyFireworksEffect(inout SurfaceData surface) {
    surface.albedo *= 0.5;

    applyfireWorksLayer(surface, 1000.0 + GameTime * 2400.0);
    applyfireWorksLayer(surface, 2000.0 + GameTime * 2400.0);
    applyfireWorksLayer(surface, 3000.0 + GameTime * 2400.0);
    applyfireWorksLayer(surface, 4000.0 + GameTime * 2400.0);
}

void applyHolographicEffect(inout SurfaceData surface) {
    float fres = fresnel(surface.normal, normalize(-surface.position), 0.1);
    float offset = GameTime * 1200.0;

    vec2 pixel = surface.uv * surface.texSize + offset;
    surface.alpha = fres * 2.0;
    if(min(mod(pixel.x, 3.0), mod(pixel.y, 3.0)) < 0.5) {
        surface.alpha = 1.0;
    }
    
    if(bool(int(surface.uv.y * surface.texSize.y * 4.0 + GameTime * 2400.0) & 0x1)) surface.alpha += 0.3;

    surface.albedo = mix(vec3(0.0, 0.1, 0.2), vec3(0.3, 1.0, 0.4) , min(surface.alpha, 1.0) * luminance(surface.albedo));
    surface.albedo += 0.2 * fres;
    surface.emissive = 0.5 + fres;
}

#define MATERIAL_EFFECTS switch(key)

#ifdef RENDERTYPE_ENTITY_CUTOUT
    #define RADIANT(key, col)     case uint(key): applyRadiantEffect(surface); return;
    #define MAGMA(key, col)       case uint(key): applyMagmaEffect(surface); return;
    #define FIREWORKS(key, col)   case uint(key): applyFireworksEffect(surface); return;
    #define GALAXY(key, col)      case uint(key): applyGalaxyEffect(surface); return;
    #define MATRIX(key, col)      case uint(key): applyMatrixEffect(surface); return;
    #define GLITCH(key, col)      case uint(key): applyGlitchEffect(surface); return;
    #define HOLOGRAPHIC(key, col) case uint(key): applyHolographicEffect(surface); return;
#else
    #define RADIANT(key, col)     case ((uint(col.r) << 24) | (uint(col.g) << 16) | (uint(col.b) << 8) | 255u): applyRadiantEffect(surface); return;
    #define MAGMA(key, col)       case ((uint(col.r) << 24) | (uint(col.g) << 16) | (uint(col.b) << 8) | 255u): applyMagmaEffect(surface); return;
    #define FIREWORKS(key, col)   case ((uint(col.r) << 24) | (uint(col.g) << 16) | (uint(col.b) << 8) | 255u): applyFireworksEffect(surface); return;
    #define GALAXY(key, col)      case ((uint(col.r) << 24) | (uint(col.g) << 16) | (uint(col.b) << 8) | 255u): applyGalaxyEffect(surface); return;
    #define MATRIX(key, col)      case ((uint(col.r) << 24) | (uint(col.g) << 16) | (uint(col.b) << 8) | 255u): applyMatrixEffect(surface); return;
    #define GLITCH(key, col)      case ((uint(col.r) << 24) | (uint(col.g) << 16) | (uint(col.b) << 8) | 255u): applyGlitchEffect(surface); return;
    #define HOLOGRAPHIC(key, col) case ((uint(col.r) << 24) | (uint(col.g) << 16) | (uint(col.b) << 8) | 255u): applyHolographicEffect(surface); return;
#endif

void applyEffects(inout SurfaceData surface, uint key) {
#moj_import<config.glsl>
    surface.albedo *= surface.dyeColor;
}