package com.mythicalnetwork.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.BasePluginExtension

class DefaultPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        setupDefaults(target)
    }

    private fun setupDefaults(project: Project){
        project.plugins.apply(BasePlugin::class.java)
        val base = project.extensions.getByType(BasePluginExtension::class.java)
    }
}