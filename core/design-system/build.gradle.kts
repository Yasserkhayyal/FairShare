plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.designsystem"
}

dependencies {
    implementation(project(":core:base"))
    api(libs.material)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.material.extended.icons)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.googlefonts)
    testImplementation(project(":core:test:jvm"))
    androidTestImplementation(project(":core:test:instrumentation"))
    debugImplementation(project(":core:ui-tooling"))
}