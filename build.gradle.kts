import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    `java-library`
    groovy
    id("nebula.source-jar") version "17.3.2"
    id("nebula.javadoc-jar") version "17.3.2"
}

apply(from = "gradle/publishRoot.gradle")
apply(from = "gradle/publishModule.gradle")

repositories {
    jcenter()
}

group = "com.github.rahulsom"

dependencies {
    implementation("org.fusesource.jansi:jansi:1.18")

    testImplementation("org.codehaus.groovy:groovy:2.5.9")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}

tasks.withType<Test> {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
  }
}