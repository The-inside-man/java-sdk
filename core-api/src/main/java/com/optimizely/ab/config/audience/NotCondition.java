/**
 *
 *    Copyright 2016-2019, Optimizely and contributors
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
import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Represents a 'Not' conditions condition operation.
 */
@Immutable
public class NotCondition<T> implements Condition<T> {

    private final Condition condition;

    public NotCondition(@Nonnull Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    @Nullable
    public Boolean evaluate(ProjectConfig config,
                            Map<String, ?> attributes,
                            List<OptimizelyDecideOption> options,
                            DecisionReasons reasons) {

        Boolean conditionEval = condition == null ? null : condition.evaluate(config, attributes, options, reasons);
        return (conditionEval == null ? null : !conditionEval);
    }

    @Nullable
    public Boolean evaluate(ProjectConfig config, Map<String, ?> attributes) {
        return evaluate(config, attributes, Collections.emptyList(), new DecisionReasons());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[not, ");
        s.append(condition);
        s.append("]");

        return s.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof NotCondition))
            return false;

        NotCondition otherNotCondition = (NotCondition) other;

        return condition.equals(otherNotCondition.getCondition());
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
    }
}
