/*
 * Copyright 2010 the original author or authors.
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
package org.codenarc.rule.unnecessary

import org.codenarc.rule.AbstractRuleTestCase
import org.codenarc.rule.Rule

/**
 * Tests for UnnecessaryConstructorRule
 *
 * @author 'Tomasz Bujok'
 * @version $Revision$ - $Date$
 */
class UnnecessaryConstructorRuleTest extends AbstractRuleTestCase {

    void testRuleProperties() {
        assert rule.priority == 3
        assert rule.name == "UnnecessaryConstructor"
    }

    void testSuccessScenario() {
        final SOURCE = '''
            class MyClass {
                public MyClass() {}
                public MyClass(String text) {}
            }
            class MyUtility {
              private MyUtility(){
                def inner = new Object() {}
              }
            }
        '''
        assertNoViolations(SOURCE)
    }

    void testSingleViolation() {
        final SOURCE = '''
            class MyClass {
                public MyClass() {}               
            }
        '''
        assertSingleViolation(SOURCE, 3, 'public MyClass() {}')
    }

    protected Rule createRule() {
        new UnnecessaryConstructorRule()
    }
}