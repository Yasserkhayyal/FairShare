plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.mvi"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:design-system"))
}