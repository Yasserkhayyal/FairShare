plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.network"
}

dependencies {
    implementation(project(":core:base"))

}