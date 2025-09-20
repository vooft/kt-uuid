![Build and test](https://github.com/vooft/kt-uuid/actions/workflows/build.yml/badge.svg?branch=main)
![Releases](https://img.shields.io/github/v/release/vooft/kt-uuid)
![Maven Central](https://img.shields.io/maven-central/v/io.github.vooft/kt-uuid-core)
![License](https://img.shields.io/github/license/vooft/kt-uuid)

Since kotlin-2.0 a native [Uuid](https://kotlinlang.org/api/core/2.0/kotlin-stdlib/kotlin.uuid/-uuid/) class is available, there is no need for this library anymore.

# kt-uuid

Simple Kotlin Multiplatform library to generate UUIDs.

## Supported platforms

Library has only one critical dependency - [secure-random](https://github.com/KotlinCrypto/secure-random), adding new platforms is mostly limited by which platforms does this library support.

Currently it is compiled for:
* jvm
* macosArm64
* macosX64
* iosArm64
* iosSimulatorArm64
* iosX64
* js (browser/nodejs)
* wasm (browser/nodejs)
* mingwX64
* linuxX64

# Quick start
Library is published to Maven Central under name [io.github.vooft:kt-uuid-core](https://central.sonatype.com/search?namespace=io.github.vooft&name=kt-uuid-core).

Add the dependency to your project:
```kotlin
kotlin {
    ...

    sourceSets {
        commonMain.dependencies {
            implementation("io.github.vooft:kt-uuid-core:<version>")
        }
    }
}
```

API is trying to mirror the `java.util.UUID` class as much as possible.

```kotlin
import io.github.vooft.kuuid.UUID

val uuid1 = UUID.randomUUID()
val uuid2 = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")

println(uuid1.toString())
println(uuid2.toString()) // prints "123e4567-e89b-12d3-a456-426614174000"
```
