plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.uitooling"
}

dependencies {
    implementation(project(":core:base"))
    api(libs.androidx.compose.ui.tooling)
    testImplementation(project(":core:test"))
    androidTestImplementation(project(":core:test"))
}