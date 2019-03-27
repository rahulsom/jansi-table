import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    `java-library`
    groovy
    id("nebula.release") version "9.2.0"
    id("nebula.maven-publish") version "9.5.0"
    id("nebula.source-jar") version "9.5.0"
    id("nebula.javadoc-jar") version "9.5.0"
    id("nebula.nebula-bintray-publishing") version "5.0.0"
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

bintray {
    user = (System.getenv("BINTRAY_USER"))
    key = (System.getenv("BINTRAY_KEY"))
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        userOrg = ("rahulsom")
        repo = ("m2")
        websiteUrl = ("https://github.com/rahulsom/jansi-table")
        issueTrackerUrl = ("https://github.com/rahulsom/jansi-table/issues")
        vcsUrl = ("https://github.com/rahulsom/jansi-table.git")
        setLicenses("Apache-2.0")
        setLabels("jansi", "textui", "table")
    })
}
