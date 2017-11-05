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
package org.codenarc.rule.concurrency

import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.SynchronizedStatement
import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule

/**
 * Synchronized On This Rule - This rule reports uses of the synchronized blocks where
 * the synchronization reference is 'this'. Doing this effectively makes your
 * synchronization policy public and modifiable by other objects. To avoid possibilities
 * of deadlock, it is better to synchronize on internal objects.
 *
 * @author Hamlet D'Arcy
 */
class SynchronizedOnThisRule extends AbstractAstVisitorRule {

    String name = 'SynchronizedOnThis'
    int priority = 2
    Class astVisitorClass = SynchronizedOnThisAstVisitor
}

class SynchronizedOnThisAstVisitor extends AbstractAstVisitor  {

    @Override
    void visitSynchronizedStatement(SynchronizedStatement statement) {
        if (isFirstVisit(statement)) {
            if (statement.getExpression() instanceof VariableExpression) {
                if (statement.expression?.variable == 'this') {
                    addViolation(statement, "The synchronized statement uses the 'this' reference")
                }
            }
        }
        super.visitSynchronizedStatement(statement)
    }
}
