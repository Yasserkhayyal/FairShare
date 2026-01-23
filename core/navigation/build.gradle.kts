plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.navigation"
}

dependencies {
    implementation(project(":core:base"))
    api(libs.androidx.navigation.compose)
    testImplementation(project(":core:test:jvm"))
    androidTestImplementation(project(":core:test:instrumentation"))
}