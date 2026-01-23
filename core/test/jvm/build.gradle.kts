plugins {
    alias(libs.plugins.fairshare.android.library)
}

android {
    namespace = "com.khayal.test.jvm"
}

dependencies {
    api(platform(libs.junit.bom))
    api(libs.io.mockk)
    api(libs.io.mockk.agent)
    api(libs.io.mockk.android)
    api(libs.junit)
    api(libs.junit.jupiter.api)
    api(libs.junit.jupiter.engine)
    api(libs.junit.jupiter.param)
    api(libs.junit.vintage.engine)
    api(libs.robolectric.core)
}