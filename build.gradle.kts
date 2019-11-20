plugins {
    `java-library`
    groovy
    id("nebula.source-jar") version "14.1.1"
    id("nebula.javadoc-jar") version "14.1.1"
}

apply(from = "gradle/publishRoot.gradle")
apply(from = "gradle/publishModule.gradle")

repositories {
    jcenter()
}

group = "com.github.rahulsom"

dependencies {
    implementation("org.fusesource.jansi:jansi:1.17.1")

    testImplementation("org.codehaus.groovy:groovy:2.5.8")
    testImplementation("org.spockframework:spock-core:1.3-RC1-groovy-2.5")
}
