plugins {
    `kotlin-dsl`
}

group = "com.khayal.buildlogic"

kotlin {
    jvmToolchain(21)
}

// To write convention plugins that apply and configure other plugins,
// you need to add those plugins' APIs to the compile classpath.
dependencies {
    // Use compileOnly because these dependencies are provided by Gradle at runtime.
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidCommon") {
            id = "fairshare.android.common"
            implementationClass = "com.khayal.convention.AndroidCommonConventionPlugin"
        }
        register("androidApplication") {
            id = "fairshare.android.application"
            implementationClass = "com.khayal.convention.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "fairshare.android.library"
            implementationClass = "com.khayal.convention.AndroidLibraryConventionPlugin"
        }
    }
}
