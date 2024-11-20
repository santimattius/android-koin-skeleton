import com.automattic.android.measure.reporters.SlowSlowTasksMetricsReporter

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.secrets.gradle.plugin)
    alias(libs.plugins.automattic.measure.builds)
}

apply("$rootDir/gradle/coverage.gradle")

android {
    namespace = "com.santimattius.basic.skeleton"
    compileSdk = extraString("target_sdk_version").toInt()

    defaultConfig {
        applicationId = extraString("application_id")
        minSdk = extraString("min_sdk_version").toInt()
        targetSdk = extraString("target_sdk_version").toInt()
        versionCode = extraString("version_code").toInt()
        versionName = extraString("version_name")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
        unitTests.all {
            testCoverage {
                version = "0.8.8"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    // Set KSP sourceSet
    applicationVariants.forEach { variant ->
        variant.sourceSets.forEach {
            it.javaDirectories += files("build/generated/ksp/${variant.name}/kotlin")
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

fun extraString(key: String): String {
    return extra[key] as String
}

detekt {
    config.setFrom("${project.rootDir}/config/detekt/detekt.yml")
    baseline = file("$rootDir/detekt-baseline.xml")
    autoCorrect = true
}

measureBuilds {
    enable = true
    attachGradleScanId =
        false // `false`, if no Enterprise plugin applied OR don't want to attach build scan id
    onBuildMetricsReadyListener {
        SlowSlowTasksMetricsReporter.report(this)
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.activity.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)

    implementation(libs.bundles.coroutine)
    testImplementation(libs.coroutine.test)
    implementation(libs.bundles.retrofit)
    implementation(libs.gson.core)
    testImplementation(libs.mockwebserver)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.startup)

    compileOnly(libs.koin.annotations.core)
    ksp(libs.koin.annotations.compiler)

    implementation(libs.coil.core)

    testImplementation(platform(libs.compose.bom))
    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.test.ext)
    androidTestImplementation(libs.test.espresso)

}
