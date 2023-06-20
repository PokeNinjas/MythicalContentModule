import com.mythicalnetwork.gradle.Dependencies
import com.mythicalnetwork.gradle.Versions
import com.mythicalnetwork.gradle.ProjectInfo
import com.mythicalnetwork.gradle.Repos

plugins {
    id("java")
    id("io.github.juuxel.loom-quiltflower") version ("1.8.+")
    id("org.quiltmc.loom") version("1.1.+")
    kotlin("jvm") version ("1.8.0")
    kotlin("kapt") version("1.8.20")
}

group = ProjectInfo.GROUP
version = ProjectInfo.VERSION
base {
    archivesName.set("MythicalMod")
}

loom {
    mixin {
        defaultRefmapName.set("mixins.${project.name}.refmap.json")
    }
    interfaceInjection {
        enableDependencyInterfaceInjection.set(true)
    }

    accessWidenerPath.set(file("src/main/resources/mythicalmod.accesswidener"))
}

repositories {
    mavenCentral()
    for(repo in Repos.BASE){
        if(repo.contains("sonatype.org")){
            maven(url = repo){
                name = "sonatype-oss-snapshots1"
                mavenContent { snapshotsOnly() }
            }
        } else {
            maven(url = repo)
        }
    }
}


dependencies {
    minecraft("com.mojang:minecraft:${Versions.MINECRAFT}")
    mappings(loom.layered {
        mappings(Dependencies.QUILT_MAPPINGS)
        officialMojangMappings()
    })
    for(dep in Dependencies.CORE_DEPS){
        if(Dependencies.DONT_INCLUDE.contains(dep)){
            modImplementation(dep)
        } else {
            include(dep)?.let {
                if(!dep.contains("owo-sentinel")){
                    modImplementation(it)
                }
            }
        }
        if(dep.contains("owo-lib")){
            annotationProcessor(dep)
            kapt(dep)
        }
    }
    testImplementation(Dependencies.JUNIT_JUPITER_API)
    testRuntimeOnly(Dependencies.JUNIT_JUPITER_ENGINE)
    modImplementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}