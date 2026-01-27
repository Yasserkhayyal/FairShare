plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.test.instrumentation"
}

dependencies {
    implementation(project(":core:base"))
    api(libs.androidx.compose.ui.test.junit4)
    api(libs.androidx.compose.ui.test.manifest)
    api(libs.androidx.espresso.core)
    api(libs.androidx.junit)
    api(libs.io.mockk)
    api(libs.io.mockk.agent)
    api(libs.io.mockk.android)
    api(libs.google.test.paramter.injector)
}