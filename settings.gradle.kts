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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lesson4"
include(":app")
include(":scrollviewapp")
include(":listviewapp")
include(":listviewapp2")
include(":retrofitapp")
include(":fragmentapp")
include(":fragmentmanagerapp")
include(":resultapifragmentapp")
include(":bottomnavigationapp")
include(":navigationdrawerapp")
