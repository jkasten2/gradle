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

package org.gradle.api.plugins.quality.internal;

import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.plugins.quality.TargetJdk;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;

public interface PmdParameters {
    ConfigurableFileCollection getPmdClasspath();
    ConfigurableFileCollection getClasspath();
    ConfigurableFileCollection getSource();

    Property<TargetJdk> getTargetJdk();

    ListProperty<String> getRuleSets();
    ConfigurableFileCollection getRuleSetFiles();
    RegularFileProperty getRuleSetConfig();
    Property<Integer> getRulePriority();

    Property<Boolean> getConsoleOutput();
    Property<Boolean> getStdOutIsAttachedToTerminal();

    Property<Boolean> getIgnoreFailures();

    Property<Boolean> getIncrementalAnalysis();
    RegularFileProperty getIncrementalCacheFile();
}
