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


package tsh.exception;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.SimpleNode;


public class UtilEvalError extends Exception {
    protected UtilEvalError() {
    }

    public UtilEvalError(String s) {
        super(s);
    }


    public EvalError toEvalError(
            String msg, SimpleNode node, CallStack callstack) {
        if (Interpreter.DEBUG)
            printStackTrace();

        if (msg == null)
            msg = "";
        else
            msg = msg + ": ";
        return new EvalError(msg + getMessage(), node, callstack);
    }

    public EvalError toEvalError(SimpleNode node, CallStack callstack) {
        return toEvalError(null, node, callstack);
    }

}

