// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android")version "2.57.2" apply  false
}

/*    // build.gradle (Project)
    plugins {
        id 'com.android.application' version '8.X.X' apply false
        id 'org.jetbrains.kotlin.android' version '1.9.X' apply false
        id 'com.google.dagger.hilt.android' version '2.51.1' apply false // Check this version
    }
    */