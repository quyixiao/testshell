/*****************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one                *
 * or more contributor license agreements.  See the NOTICE file              *
 * distributed with this work for additional information                     *
 * regarding copyright ownership.  The ASF licenses this file                *
 * to you under the Apache License, Version 2.0 (the                         *
 * "License"); you may not use this file except in compliance                *
 * with the License.  You may obtain a copy of the License at                *
 *                                                                           *
 *     http://www.apache.org/licenses/LICENSE-2.0                            *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing,                *
 * software distributed under the License is distributed on an               *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                    *
 * KIND, either express or implied.  See the License for the                 *
 * specific language governing permissions and limitations                   *
 * under the License.                                                        *
 *                                                                           *
 *                                                                           *
 * This file is part of the BeanShell Java Scripting distribution.           *
 * Documentation and updates may be found at http://www.beanshell.org/       *
 * Patrick Niemeyer (pat@pat.net)                                            *
 * Author of Learning Java, O'Reilly & Associates                            *
 *                                                                           *
 *****************************************************************************/


package tsh.expression;


import tsh.*;
import tsh.exception.EvalError;

public class TSHBlock extends SimpleNode {
    public boolean isSynchronized = false;

    public TSHBlock(String id) {
        super(id);
    }


    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        return eval(callstack, interpreter, false);
    }

    public Object eval( CallStack callstack, Interpreter interpreter,boolean overrideNamespace)throws EvalError {
        return evalBlock(callstack, interpreter, overrideNamespace, null/*filter*/);
    }


    Object evalBlock(CallStack callstack, Interpreter interpreter,boolean overrideNamespace, NodeFilter nodeFilter)
            throws EvalError {
        Object ret = Primitive.VOID;
        NameSpace enclosingNameSpace = null;
        if (!overrideNamespace) {
            enclosingNameSpace = callstack.top();
            BlockNameSpace bodyNameSpace = new BlockNameSpace(enclosingNameSpace);
            callstack.swap(bodyNameSpace);
        }

        int startChild = isSynchronized ? 1 : 0;
        int numChildren = jjtGetNumChildren();

        try {
            for (int i = startChild; i < numChildren; i++) {
                SimpleNode node = ((SimpleNode) jjtGetChild(i));

                // filter nodes
                if (nodeFilter != null && !nodeFilter.isVisible(node))
                    continue;

                ret = node.eval(callstack, interpreter);
                // statement or embedded block evaluated a return statement
                if (ret instanceof ReturnControl)
                    break;
            }
        } finally {
            // make sure we put the namespace back when we leave.
            if (!overrideNamespace)
                callstack.swap(enclosingNameSpace);
        }
        return ret;
    }

    public interface NodeFilter {
        public boolean isVisible(SimpleNode node);
    }


}

