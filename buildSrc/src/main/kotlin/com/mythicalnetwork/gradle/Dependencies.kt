package com.mythicalnetwork.gradle

object Dependencies {
    const val QUILT_MAPPINGS = "org.quiltmc:quilt-mappings:${Versions.MINECRAFT}+build.${Versions.QUILT_MAPPINGS}:intermediary-v2"
    const val QUILT_KOTLIN_CORE = "org.quiltmc.quilt-kotlin-libraries:core:${Versions.QUILT_KOTLIN_CORE}"
    const val QUILT_KOTLIN_LIBRARIES = "org.quiltmc.quilt-kotlin-libraries:library:${Versions.QUILT_KOTLIN_LIBRARIES}"
    const val QUILT_LOADER = "org.quiltmc:quilt-loader:${Versions.QUILT_LOADER}"
    const val QUILTED_FABRIC_API = "org.quiltmc.quilted-fabric-api:quilted-fabric-api:${Versions.QUILTED_FABRIC_API}-${Versions.MINECRAFT}"
    const val ARCHITECTURY = "dev.architectury:architectury-fabric:6.5.69"
    const val COBBLEMON = "com.cobblemon:fabric:1.3.1+1.19.2-SNAPSHOT"
    const val JUNIT_JUPITER_API = "org.junit.jupiter:junit-jupiter-api:5.9.2"
    const val JUNIT_JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:5.9.2"
    const val OWOLIB = "io.wispforest:owo-lib:${Versions.OWOLIB}"
    const val OWOSENTINEL = "io.wispforest:owo-sentinel:${Versions.OWOLIB}"
    const val LUCKPERMS = "net.luckperms:api:5.4"
    const val FABRIC_PERMS_API = "me.lucko:fabric-permissions-api:0.2-SNAPSHOT"
    const val MODMENU = "com.terraformersmc:modmenu:${Versions.MODMENU}"
    const val PLACEHOLDERAPI = "eu.pb4:placeholder-api:${Versions.PLACEHOLDERAPI}"
    const val GECKOLIB = "software.bernie.geckolib:geckolib-quilt-1.19:${Versions.GECKOLIB}"
    const val VEIL = "foundry.veil:Veil-quilt-1.19.2:${Versions.VEIL}"

    val CORE_DEPS = listOf<String>(
        QUILT_KOTLIN_CORE,
        QUILT_KOTLIN_LIBRARIES,
        QUILT_LOADER,
        QUILTED_FABRIC_API,
        ARCHITECTURY,
        COBBLEMON,
        OWOLIB,
        OWOSENTINEL,
        LUCKPERMS,
        FABRIC_PERMS_API,
        MODMENU,
        PLACEHOLDERAPI,
        GECKOLIB,
        VEIL
    )
}