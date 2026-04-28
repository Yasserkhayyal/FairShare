package com.khayal.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType

class CommonConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply common plugins every module needs
            // Find the Android extension (app or library) and configure it.
            extensions.findByType(CommonExtension::class)?.run {
                //we don't apply compose plugin unless we have an android module
                pluginManager.apply {
                    apply("org.jetbrains.kotlin.plugin.compose")
                    apply("com.google.devtools.ksp")
                    apply("com.google.dagger.hilt.android")
                }
                experimentalProperties["android.experimental.enableScreenshotTest"] = true
                compileSdk = 36
                buildTypes.getByName("release").apply {
                    isMinifyEnabled = true
                }
                defaultConfig.run {
                    minSdk = 23
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
            //strangely I have to add the following snippet to fix the issue:
            //The Hilt Android Gradle plugin is applied but no com.google.dagger:hilt-android
            //dependency was found instead of applying it transitively to core:di
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("dagger.hilt.android").get())
                add("implementation", libs.findLibrary("hilt-navigation-compose").get())
                add("ksp", libs.findLibrary("dagger.hilt.compiler").get())
            }
        }
    }
}