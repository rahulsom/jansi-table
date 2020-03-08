plugins {
    `java-library`
    groovy
    id("nebula.source-jar") version "17.2.1"
    id("nebula.javadoc-jar") version "17.2.1"
}

apply(from = "gradle/publishRoot.gradle")
apply(from = "gradle/publishModule.gradle")

repositories {
    jcenter()
}

group = "com.github.rahulsom"

dependencies {
    implementation("org.fusesource.jansi:jansi:1.18")

    testImplementation("org.codehaus.groovy:groovy:3.0.2")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}
