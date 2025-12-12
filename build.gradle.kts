import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
    alias(libs.plugins.waena.root)
    alias(libs.plugins.waena.published)
}

repositories {
    mavenCentral()
}

contacts {
    validateEmails = true
    with(addPerson("rahulsom@noreply.github.com")) {
        moniker("Rahul Somasunderam")
        roles("owner")
        github("https://github.com/rahulsom")
    }
}

group = "com.github.rahulsom"
description = "Build tables using jansi"

dependencies {
    implementation(libs.jansi)

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
  }
  useJUnitPlatform()
}
