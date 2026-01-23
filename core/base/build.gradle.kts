plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.base"
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
}