import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
group = "org.debooklog"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

val asciidoctorExt: Configuration by configurations.creating

object Version {
    const val JJWT = "0.11.5"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    runtimeOnly("com.h2database:h2")
    // kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.8.1")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation("io.kotest:kotest-property:5.8.1")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    // mockk + mockkBean
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    // feign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    // jwt
    implementation("io.jsonwebtoken:jjwt-api:${Version.JJWT}")
    implementation("io.jsonwebtoken:jjwt-impl:${Version.JJWT}")
    implementation("io.jsonwebtoken:jjwt-jackson:${Version.JJWT}")

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.1")
    }
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
    // spring-restdocs-mockmvc를 통해 만들어진 파일을 index 파일로 생성 (템플릿은 src/docs/asciidoc/index.adoc에 구현)
    asciidoctor {
        dependsOn(test)
        configurations("asciidoctorExt")
        baseDirFollowsSourceFile()
        inputs.dir(snippetsDir)
    }
    // 만들어진 index 파일을 로컬에서 확인가능하게 카피
    register<Copy>("copyDocument") {
        dependsOn(asciidoctor)
        from("${asciidoctor.get().outputDir}/index.html")
        into("src/main/resources/static/docs")
    }
    // 만들어진 index 파일을 jar파일안에 삽입
    bootJar {
        dependsOn("copyDocument")
        dependsOn("copyDocument")
        from("${asciidoctor.get().outputDir}/index.html") {
            into("static/docs")
        }
    }
}
