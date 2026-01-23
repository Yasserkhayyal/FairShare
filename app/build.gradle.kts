plugins {
    alias(libs.plugins.fairshare.android.application)
}

android {
    namespace = "com.khayal.fairshare"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:design-system"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:onboarding"))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(project(":core:test:jvm"))
    androidTestImplementation(project(":core:test:instrumentation"))
    debugImplementation(project(":core:ui-tooling"))
}