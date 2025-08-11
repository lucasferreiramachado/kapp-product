import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

val libraryVersion          = "1.0.0"
val libraryNamespace        = "io.github.lucasferreiramachado"
val frameworkLibraryName    = "KAppProductCompose" // execute gradle clean after changed it
val libraryArtifact         = "kapp-product-compose"
val libraryPackage          = "${libraryNamespace}.kapp.product.compose"
val githubRepoName          = "kapp-product"

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.kotlinxSerialization)
    id("com.vanniktech.maven.publish") version "0.28.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = frameworkLibraryName
            isStatic = true
            binaryOption("bundleId", libraryPackage)
            binaryOption("bundleVersion", libraryVersion)
        }
    }

    jvm("desktop")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
    }
    
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.navigation.compose)
            implementation(libs.material.icons.core)
            implementation(libs.material.icons.extended)
            implementation(libs.kcoordinator)
            implementation(libs.kcoordinator.navigation.compose)
            implementation(project(":kapp-product-core"))
            implementation(libs.kapp.auth)
            implementation(libs.kapp.deeplink)
            implementation(libs.kapp.data.product)
            implementation(libs.kapp.data.purchase)
            implementation(libs.kapp.data.user)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

android {
    namespace = "com.lucasferreiramachado.kapp.product.compose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.lucasferreiramachado.kapp.product.compose"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = libraryVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    lint {
        disable.add(
            "NullSafeMutableLiveData"
        )
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.lucasferreiramachado.kapp.product.compose.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.lucasferreiramachado.kapp.product.compose"
            packageVersion = "1.0.0"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/lucasferreiramachado/${githubRepoName}")
            credentials(PasswordCredentials::class)
        }
    }
}

mavenPublishing {
    // Define coordinates for the published artifact
    coordinates(
        groupId = libraryNamespace,
        artifactId = libraryArtifact,
        version = libraryVersion
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set(libraryArtifact)
        description.set("")
        inceptionYear.set("2025")
        url.set("https://maven.pkg.github.com/lucasferreiramachado/${githubRepoName}")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://github.com/lucasferreiramachado/${githubRepoName}/blob/main/LICENSE")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("lucasferreiramachado")
                name.set("Lucas Ferreira Machado")
                url.set("https://github.com/lucasferreiramachado")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/lucasferreiramachado/${githubRepoName}")
            connection.set("scm:git:git://github.com/lucasferreiramachado/${githubRepoName}.git")
        }
    }
}