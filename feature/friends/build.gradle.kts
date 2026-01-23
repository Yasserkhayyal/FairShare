plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.friends"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:design-system"))
    testImplementation(project(":core:test:jvm"))
    androidTestImplementation(project(":core:test:instrumentation"))
    debugImplementation(project(":core:ui-tooling"))
}