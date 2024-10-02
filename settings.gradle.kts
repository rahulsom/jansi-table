plugins {
    id("com.gradle.enterprise").version("3.13.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
        buildScanPublished {
            file("build").mkdirs()
            file("build/gradle-scan.md").appendText("""Gradle Build Scan - [`${this.buildScanId}`](${this.buildScanUri})""")
        }
    }
}

rootProject.name = "jansi-table"
