package com.khayal.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidCommonConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply common plugins every module needs
            pluginManager.apply(KOTLIN_ANDROID_PLUGIN)
            pluginManager.apply(KOTLIN_COMPOSE_PLUGIN)
            // Find the Android extension (app or library) and configure it.
            extensions.findByType(ApplicationExtension::class.java)?.let {
                configureAndroid(it)
            }
            extensions.findByType(LibraryExtension::class.java)?.let {
                configureAndroid(it)
            }

            // Configure Kotlin-specific settings using the correct extension
            extensions.configure<KotlinAndroidProjectExtension> {
                jvmToolchain(21)
            }
        }
    }

    /**
     * A private helper function to configure the common settings for both
     * ApplicationExtension and LibraryExtension.
     */
    private fun configureAndroid(
        commonExtension: CommonExtension<*, *, *, *, *, *>,
    ) {
        commonExtension.apply {
            experimentalProperties["android.experimental.enableScreenshotTest"] = true
            compileSdk = 36

            defaultConfig {
                minSdk = 26
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildFeatures {
                compose = true
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }

            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }

            packaging {
                resources {
                    excludes += setOf(
                        "META-INF/LICENSE.md",
                        "META-INF/LICENSE-notice.md"
                    )
                }
            }
        }
    }
}