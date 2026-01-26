plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.test.screenshot"
}

dependencies {
    implementation(project(":core:base"))
    api(libs.screenshot.validation.api)
    api(libs.androidx.compose.ui.tooling)
}