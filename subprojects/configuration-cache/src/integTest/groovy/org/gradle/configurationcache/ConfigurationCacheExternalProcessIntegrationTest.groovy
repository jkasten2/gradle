/*
 * Copyright 2021 the original author or authors.
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

package org.gradle.configurationcache

import org.gradle.configurationcache.fixtures.ExternalProcessFixture
import org.gradle.test.fixtures.file.TestFile

import static org.hamcrest.CoreMatchers.startsWith

class ConfigurationCacheExternalProcessIntegrationTest extends AbstractConfigurationCacheIntegrationTest {
    // TODO(mlopatkin) Finish polishing tests there
    @Override
    def setup() {
        writeExecutableScript(testDirectory)
    }

    def "using exec in settings is a problem"() {
        given:
        settingsFile << """
        def baos = new java.io.ByteArrayOutputStream()
        exec {
            executable("$executable");
            args("$executableScript");
            setStandardOutput(baos);
        }
        println(baos.toString())
        """

        when:
        configurationCacheFails(":help")

        then:
        failure.assertOutputContains("Hello")
        problems.assertFailureHasProblems(failure) {
            withProblem("Settings file 'settings.gradle': external process started '$executable $executableScript'")
        }
    }

    def "using javaExec in settings is a problem"() {
        given:
        settingsFile << """
        def baos = new java.io.ByteArrayOutputStream()
        javaexec {
            mainClass = "${ExternalProcessFixture.executableClass()}"
            classpath("${ExternalProcessFixture.classpath()}")
            setStandardOutput(baos);
        }
        println(baos.toString())
        """

        when:
        configurationCacheFails(":help")

        then:
        failure.assertOutputContains("Hello from Java")
        problems.assertFailureHasProblems(failure) {
            withProblem(startsWith("Settings file 'settings.gradle': external process started"))
        }
    }

    private static String getExecutable() {
        return "sh"
    }

    private static String getExecutableScript() {
        return "exec.sh"
    }

    private static void writeExecutableScript(TestFile root) {
        TestFile scriptFile = root.file(executableScript)
        scriptFile << '''
            #!/bin/sh
            echo Hello
        '''
    }
}
