buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com/") }
    }
    dependencies {
        val navVersion = "2.5.0"

        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.6.21-1.0.5")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
