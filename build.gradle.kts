import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
    groovy
    id("com.github.rahulsom.waena.root").version("0.4.0")
    id("com.github.rahulsom.waena.published").version("0.4.0")
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
    implementation("org.fusesource.jansi:jansi:1.18")

    testImplementation("org.codehaus.groovy:groovy:3.0.8")
    testImplementation("org.spockframework:spock-core:2.0-groovy-3.0")
}

tasks.withType<Test> {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
  }
}
