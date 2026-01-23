plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.friends"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:design-system"))
    testImplementation(project(":core:test"))
    androidTestImplementation(project(":core:test"))
    debugImplementation(project(":core:ui-tooling"))
}