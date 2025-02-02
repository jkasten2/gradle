/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

rootProject.name = "catalog"

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

if (providers.systemProperty("create1").getOrNull() != null) {
    // tag::simple_catalog[]
    dependencyResolutionManagement {
        versionCatalogs {
            create("libs") {
                alias("groovy-core").to("org.codehaus.groovy:groovy:3.0.5")
                alias("groovy-json").to("org.codehaus.groovy:groovy-json:3.0.5")
                alias("groovy-nio").to("org.codehaus.groovy:groovy-nio:3.0.5")
                alias("commons-lang3").to("org.apache.commons", "commons-lang3").version {
                    strictly("[3.8, 4.0[")
                    prefer("3.9")
                }
            }
        }
    }
    // end::simple_catalog[]
}

if (providers.systemProperty("create2").getOrNull() != null) {
    // tag::catalog_with_versions[]
    dependencyResolutionManagement {
        versionCatalogs {
            create("libs") {
                version("groovy", "3.0.5")
                version("checkstyle", "8.37")
                alias("groovy-core").to("org.codehaus.groovy", "groovy").versionRef("groovy")
                alias("groovy-json").to("org.codehaus.groovy", "groovy-json").versionRef("groovy")
                alias("groovy-nio").to("org.codehaus.groovy", "groovy-nio").versionRef("groovy")
                alias("commons-lang3").to("org.apache.commons", "commons-lang3").version {
                    strictly("[3.8, 4.0[")
                    prefer("3.9")
                }
            }
        }
    }
    // end::catalog_with_versions[]
}

if (providers.systemProperty("create3").getOrNull() != null) {
    // tag::catalog_with_bundle[]
    dependencyResolutionManagement {
        versionCatalogs {
            create("libs") {
                version("groovy", "3.0.5")
                version("checkstyle", "8.37")
                alias("groovy-core").to("org.codehaus.groovy", "groovy").versionRef("groovy")
                alias("groovy-json").to("org.codehaus.groovy", "groovy-json").versionRef("groovy")
                alias("groovy-nio").to("org.codehaus.groovy", "groovy-nio").versionRef("groovy")
                alias("commons-lang3").to("org.apache.commons", "commons-lang3").version {
                    strictly("[3.8, 4.0[")
                    prefer("3.9")
                }
                bundle("groovy", listOf("groovy-core", "groovy-json", "groovy-nio"))
            }
        }
    }
    // end::catalog_with_bundle[]
}

if (providers.systemProperty("create4").getOrNull() != null) {
    // tag::catalog_with_plugin[]
    dependencyResolutionManagement {
        versionCatalogs {
            create("libs") {
                alias("jmh").toPluginId("me.champeau.jmh").version("0.6.5")
            }
        }
    }
    // end::catalog_with_plugin[]
    dependencyResolutionManagement {
        versionCatalogs {
            named("libs") {
                version("groovy", "3.0.5")
                version("checkstyle", "8.37")
                alias("groovy-core").to("org.codehaus.groovy", "groovy").versionRef("groovy")
                alias("groovy-json").to("org.codehaus.groovy", "groovy-json").versionRef("groovy")
                alias("groovy-nio").to("org.codehaus.groovy", "groovy-nio").versionRef("groovy")
                alias("commons-lang3").to("org.apache.commons", "commons-lang3").version {
                    strictly("[3.8, 4.0[")
                    prefer("3.9")
                }
                bundle("groovy", listOf("groovy-core", "groovy-json", "groovy-nio"))
            }
        }
    }
}
