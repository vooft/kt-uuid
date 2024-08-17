import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    // core kotlin plugins
    alias(libs.plugins.kotlin.multiplatform)

    // publish
    alias(libs.plugins.dokka)
    alias(libs.plugins.maven.central.publish)
}

kotlin {
    jvm()

    iosX64()
    macosX64()

    macosArm64()
    iosArm64()
    iosSimulatorArm64()

    mingwX64()
    linuxX64()

    js(IR) {
        browser()
        nodejs()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.secure.random)
        }

        jvmMain.dependencies { }

        jsMain.dependencies { }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotest.assertions.core)
        }
    }

    tasks.withType<AbstractTestTask> {
        testLogging {
            showExceptions = true
            showStandardStreams = true
            events = setOf(
                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
            )
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    pom {
        name = "kt-uuid"
        description = "Pure Kotlin Multiplatform UUID library"
        url = "https://github.com/vooft/kt-uuid"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        scm {
            connection = "https://github.com/vooft/kt-uuid"
            url = "https://github.com/vooft/kt-uuid"
        }
        developers {
            developer {
                name = "kt-uuid team"
            }
        }
    }
}
