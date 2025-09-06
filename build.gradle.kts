import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
    groovy
    alias(libs.plugins.waena.root)
    alias(libs.plugins.waena.published)
}

repositories {
    mavenCentral()
}

configure<nebula.plugin.contacts.ContactsExtension> {
    validateEmails = true
    addPerson("rahulsom@noreply.github.com", closureOf<nebula.plugin.contacts.Contact> {
        moniker("Rahul Somasunderam")
        roles("owner")
        github("https://github.com/rahulsom")
    })
}

group = "com.github.rahulsom"
description = "Build tables using jansi"

dependencies {
    implementation(libs.jansi)

    testImplementation(libs.spock.core)
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
  }
  useJUnitPlatform()
}
