### **Android Gradle Plugin 9.0 Migration Guide (Updated)**

#### 🧠 **Introduction**
Migrating to Android Gradle Plugin (AGP) 9.0 is a crucial step to keep your projects updated, secure, and efficient. This version introduces significant changes, such as **built-in Kotlin integration** and a **new DSL for build configuration**, which improve performance and simplify scripts.

This guide details the migration steps that were followed for the `android-koin-skeleton` project, serving as a precise reference of the completed process.

---
#### 📝 **Summary of Key Changes Applied**
*   **Core Version Updates:** Upgraded Gradle to `9.1` and AGP to `9.0.0`.
*   **Adoption of Built-in Kotlin:** The `org.jetbrains.kotlin.android` plugin was completely removed from all build scripts (`build.gradle.kts`), simplifying the configuration.
*   **Gradle API Modernization:** The deprecated `applicationVariants` API was replaced with the new `androidComponents` API to register KSP-generated sources.
*   **Script Cleanup:** Unnecessary `buildscript` blocks and plugin aliases were removed from the root `build.gradle.kts` file to align with modern Gradle practices.
---

#### ✔️ **Final Requirements and Versions**
After the migration, the project uses the following versions:
*   **Android Gradle Plugin (AGP):** 9.0.0
*   **Gradle:** 9.1
*   **Kotlin Gradle Plugin (KGP):** 2.3.0
*   **KSP:** 2.3.4
*   **Android Studio:** Jellyfish | 2023.3.1 or higher.

---

### 🧱 **1. `android-koin-skeleton` Project Migration**

Below are the exact steps that were executed to migrate the project.

#### **🪜 Migration Steps Executed**

1.  **Update Gradle Version to 9.1:**
    *   The error `Minimum supported Gradle version is 9.1.0` indicated that the Gradle version was insufficient.
    *   **Action:** Modified `gradle/wrapper/gradle-wrapper.properties` to use the correct version.

    ```properties
    #gradle/wrapper/gradle-wrapper.properties
    distributionUrl=https\://services.gradle.org/distributions/gradle-9.1-bin.zip
    ```

2.  **Update Plugin Versions in `libs.versions.toml`:**
    *   The versions of the AGP, Kotlin, and KSP plugins were updated to ensure compatibility.
    *   **Action:** The `gradle/libs.versions.toml` file was modified.

    ```toml
    # gradle/libs.versions.toml
    [versions]
    androidGradlePlugin = "9.0.0"
    kotlinGradlePlugin = "2.3.0"
    ksp = "2.3.4"
    # ... (other versions)

    [plugins]
    # The line for `kotlin = { ... }` pointing to the obsolete plugin was removed.
    ```

3.  **Clean Up the Root `build.gradle.kts`:**
    *   The project-level `build.gradle.kts` contained a reference to the Kotlin plugin alias (`libs.plugins.kotlin`) and an obsolete `buildscript` block, causing sync errors.
    *   **Action:** Both declarations were removed.

    **Before:**
    ```kotlin
    // build.gradle.kts (root)
    plugins {
        alias(libs.plugins.kotlin) apply false // <-- Line removed
        // ...
    }

    buildscript { // <-- Block removed
        dependencies {
            classpath(libs.dep.google.secrets.gradle.plugin)
        }
    }
    ```

4.  **Update the `app` Module's `build.gradle.kts`:**
    *   **Remove Plugin Alias:** The Kotlin plugin alias was removed, as it's no longer necessary with Built-in Kotlin.
    *   **Migrate `applicationVariants`:** The `applicationVariants` API was removed in AGP 9.0. It was migrated to the new `androidComponents` API to register KSP-generated sources.

    **Action:** Modifications in `app/build.gradle.kts`.

    ```kotlin
    // app/build.gradle.kts
    plugins {
        alias(libs.plugins.kotlin) // <-- Line removed
        // ...
    }

    // Obsolete block removed:
    /*
    android {
        applicationVariants.forEach { variant ->
            variant.sourceSets.forEach {
                it.javaDirectories += files("build/generated/ksp/${variant.name}/kotlin")
            }
        }
    }
    */

    // New block added:
    androidComponents {
        onVariants { variant ->
            variant.sources.java?.addGeneratedSourceDirectory(
                layout.buildDirectory.dir("generated/ksp/${variant.name}/kotlin")
            )
        }
    }
    ```

5.  **Final Validation:**
    *   After applying all changes, the Gradle sync completed successfully.
    *   **Note:** During the process, Gradle download errors occurred (`Cause: https://downloads.gradle.org/distributions/gradle-9.1-bin.zip`). These are often temporary network issues and can be resolved by retrying the sync or invalidating Android Studio caches (`File` > `Invalidate Caches...`).

---

### 🤝 **2. Kotlin Multiplatform (KMP) Project Migration**

*(This section is kept as a general reference, as `android-koin-skeleton` is not a KMP project).*

The fundamental change in AGP 9.0 is that **you can no longer apply an Android plugin (`com.android.application` or `com.android.library`) and the KMP plugin (`org.jetbrains.kotlin.multiplatform`) in the same module.**

The solution is to separate the project into two modules:
1.  **`shared` Module (KMP):** Contains the shared logic. It uses the new `com.android.kotlin.multiplatform.library` plugin.
2.  **`androidApp` Module (Android Application):** Acts as the app's entry point and depends on the `shared` module.

---

### 📊 **3. Post-Migration Verification Checklist**

*   **✅ KGP Compatibility:** It was verified that KGP `2.3.0` works correctly with AGP `9.0.0`.
*   **✅ Dependencies and Plugins:** `kapt` was migrated to KSP, and other plugins like `detekt` were validated to continue working.
*   **✅ Tests and CI:** As a next step, it is recommended to run all test suites (unit and instrumentation tests) to ensure there are no functional regressions.
*   **✅ Rollback Strategy:** If blocking issues had arisen, temporary flags in `gradle.properties` like `android.newDsl=false` or `android.builtInKotlin=false` could have been used to facilitate debugging.
