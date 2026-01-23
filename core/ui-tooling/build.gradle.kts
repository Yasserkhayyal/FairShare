plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.uitooling"
}

dependencies {
    implementation(project(":core:base"))
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
}