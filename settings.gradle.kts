pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
		jcenter()
		mavenCentral()
		gradlePluginPortal()
		maven(url = "https://jitpack.io")
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		jcenter()
		mavenCentral()
		maven(url = "https://jitpack.io")
	}
}

rootProject.name = "Meal Recipes"
include(":app")
