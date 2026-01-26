plugins {
    alias(libs.plugins.fairshare.android.library)
    alias(libs.plugins.screenshot)
}

android {
    namespace = "com.khayal.onboarding"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:design-system"))
    implementation(project(":core:navigation"))
    testImplementation(project(":core:test:jvm"))
    androidTestImplementation(project(":core:test:instrumentation"))
    debugImplementation(project(":core:ui-tooling"))
    screenshotTestImplementation(project(":core:test:screenshot"))
    screenshotTestImplementation(project(":core:design-system"))
}