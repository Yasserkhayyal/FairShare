pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FairShare"
include(":app")
include(":core:base")
include(":core:design-system")
include(":core:navigation")
include(":core:ui-tooling")
include(":core:test:instrumentation")
include(":core:test:jvm")
include(":feature:auth")
include(":feature:groups")
include(":feature:friends")
include(":feature:profile")
include(":feature:onboarding")
