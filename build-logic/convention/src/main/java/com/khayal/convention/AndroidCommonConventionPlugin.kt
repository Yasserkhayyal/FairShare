package com.khayal.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class AndroidCommonConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply common plugins every module needs
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            // Find the Android extension (app or library) and configure it.
            extensions.findByType(CommonExtension::class)?.run {
                experimentalProperties["android.experimental.enableScreenshotTest"] = true
                compileSdk = 36
                buildTypes.getByName("release").apply {
                    isMinifyEnabled = true
                }
                defaultConfig.run {
                    minSdk = 21
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                packaging.run {
                    resources {
                        excludes += setOf(
                            "META-INF/LICENSE.md",
                            "META-INF/LICENSE-notice.md"
                        )
                    }
                }
                testOptions.unitTests.isIncludeAndroidResources = true
            }
        }
    }
}