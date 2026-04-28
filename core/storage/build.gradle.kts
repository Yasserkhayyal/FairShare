plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.storage"
}

dependencies {
    implementation(project(":core:base"))
    api(libs.androidx.datastore.preferences)
}