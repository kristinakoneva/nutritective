buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}