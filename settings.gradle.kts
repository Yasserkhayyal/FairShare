pluginManagement {
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
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
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
include(":core:design-system")
include(":core:navigation")
include(":feature:auth")
include(":feature:groups")
include(":feature:friends")
include(":feature:profile")
include(":feature:onboarding")
include(":core:test")
include(":core:base")
include(":core:ui-tooling")
