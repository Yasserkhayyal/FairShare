plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.onboarding"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:design-system"))
    implementation(project(":core:navigation"))
    testImplementation(project(":core:test"))
    androidTestImplementation(project(":core:test"))
    debugImplementation(project(":core:ui-tooling"))
}