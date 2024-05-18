pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BudgetApp"
include(":app")
include(":core")
include(":domain")
include(":features:records")
include(":features:add")
include(":features:reports")
include(":features:settings")
