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
    implementation("org.fusesource.jansi:jansi:2.3.1")

    testImplementation("org.codehaus.groovy:groovy:3.0.7")
    testImplementation("org.spockframework:spock-core:2.0-M4-groovy-3.0")
}

tasks.withType<Test> {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
  }
}
