plugins {
    `java-library`
    groovy
    id("nebula.release") version "9.2.0"
}

repositories {
    jcenter()
}

group = "com.github.rahulsom"

dependencies {
    implementation("org.fusesource.jansi:jansi:1.17.1")

    testImplementation("org.codehaus.groovy:groovy-all:2.5.6")

    testImplementation("org.spockframework:spock-core:1.2-groovy-2.5")
    testImplementation("junit:junit:4.12")
}
