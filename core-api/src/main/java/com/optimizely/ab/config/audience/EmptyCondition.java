/**
 *    Copyright 2019, Optimizely Inc. and contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.optimizely.ab.config.audience;

import com.optimizely.ab.config.ProjectConfig;
import com.optimizely.ab.optimizelydecision.DecisionReasons;
import com.optimizely.ab.optimizelydecision.OptimizelyDecideOption;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EmptyCondition<T> implements Condition<T> {
    @Nullable
    @Override
    public Boolean evaluate(ProjectConfig config,
                            Map<String, ?> attributes,
                            List<OptimizelyDecideOption> options,
                            DecisionReasons reasons) {
        return true;
    }

    @Nullable
    public Boolean evaluate(ProjectConfig config, Map<String, ?> attributes) {
        return evaluate(config, attributes, Collections.emptyList(), new DecisionReasons());
    }

}
