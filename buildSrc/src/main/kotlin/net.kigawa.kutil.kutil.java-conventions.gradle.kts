/*
 * This file was generated by the Gradle 'init' task.
 */
import dependencies.ProjectConfig
import java.net.URI

plugins {
  kotlin("multiplatform")
  `maven-publish`
}

repositories {
  mavenLocal()
  maven {
    url = uri("https://repo.maven.apache.org/maven2/")
  }

  maven {
    url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
  }
}

dependencies {
}

version = ProjectConfig.VERSION
group = ProjectConfig.GROUP


kotlin {
  jvm {
  }
}



publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(findProject(":api")?.components?.get("kotli"))
      pom {
        name.set("kutil")
        description.set("utilities for java")
        url.set("https://github.com/kigawa01/kutil-java/")
        properties.set(
          mapOf(
          )
        )
        licenses {
          license {
            name.set("MIT License")
            url.set("http://www.opensource.org/licenses/mit-license.php")
          }
        }
        developers {
          developer {
            id.set("net.kigawa")
            name.set("kigawa")
            email.set("contact@kigawa.net")
          }
        }
        scm {
          connection.set("scm:git:https://github.com/kigawa01/kutil-java.git")
          developerConnection.set("scm:git:https://github.com/kigawa01/kutil-java.git")
          url.set("https://github.com/kigawa01/kutil-java")
        }
      }
    }
  }

  repositories {
    maven {
      name = "OSSRH"
      url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
      credentials {
        username = System.getenv("MAVEN_USERNAME")
        password = System.getenv("MAVEN_PASSWORD")
      }
    }
  }
}
