/*
 * Copyright 2014 Lukas Krejci
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
 * limitations under the License
 */

package test;

import java.lang.Override;
import java.util.Collections;
import java.util.List;

import javax.lang.model.element.TypeElement;

import org.revapi.ChangeSeverity;
import org.revapi.Difference;
import org.revapi.java.JavaCompatibility;

public class Extension extends org.revapi.java.CheckBase {

    @Override
    public void doVisitClass(TypeElement oldType, TypeElement newType) {
        if ("test.Dep".equals(oldType.getQualifiedName().toString())) {
            pushActive(oldType, newType);
        }
    }

    @Override
    public List<Difference> doEnd() {
        ActiveElements<TypeElement> types = popIfActive();
        if (types != null) {
            return Collections.singletonList(
                Difference.builder().withCode("!!TEST_CODE!!").withName("test check")
                    .withDescription("test description")
                    .addClassification(JavaCompatibility.SOURCE, ChangeSeverity.BREAKING).build());
        }

        return null;
    }
}
