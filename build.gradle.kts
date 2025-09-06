import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
    groovy
    id("com.github.rahulsom.waena.root").version("0.16.0")
    id("com.github.rahulsom.waena.published").version("0.16.0")
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
    implementation("org.fusesource.jansi:jansi:2.4.2")

    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.9.1")
}

tasks.withType<Test> {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
  }
  useJUnitPlatform()
}
