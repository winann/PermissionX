plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "com.winann.permissionx"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

mavenPublishing {
    coordinates("com.swinann", "PermissionX", "1.0.0")

    pom {
        name.set("PermissionX")
        description.set("A Permission request for Android by kotlin")
        inceptionYear.set("2025")
        url.set("https://github.com/winann/PermissionX.git")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("winann")
                name.set("Ann")
                email.set("winann@126.com")
                url.set("https://github.com/winann")
            }
        }

        scm {
            connection.set("scm:git:git@github.com:winann/PermissionX.git")
            developerConnection.set("scm:git:ssh://github.com/winann/PermissionX.git")
            url.set("https://github.com/winann/PermissionX")
        }
    }

    // 3. 发布目标：Maven Central (New API: 0.35.0+)
    // 无需参数，默认指向 Central Portal
    publishToMavenCentral()

    // 4. 自动签署：它会自动寻找 gradle.properties 中的 signing.* 信息
    signAllPublications()
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}