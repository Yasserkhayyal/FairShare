plugins {
    alias(libs.plugins.fairshare.android.application)
}

android {
    namespace = "com.khayal.fairshare"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(project(":core:base"))
    implementation(project(":core:design-system"))
    testImplementation(project(":core:test"))
    androidTestImplementation(project(":core:test"))
    debugImplementation(project(":core:ui-tooling"))
}