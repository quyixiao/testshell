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
package tsh.util;

import tsh.Capabilities;
import tsh.exception.ReflectError;
import tsh.exception.UtilEvalError;

import java.lang.reflect.InvocationTargetException;

public abstract class ClassGenerator {
    private static ClassGenerator cg;

    public static ClassGenerator getClassGenerator()
            throws UtilEvalError {
        if (cg == null) {
            try {
                Class clas = Class.forName("tsh.ClassGeneratorImpl");
                cg = (ClassGenerator) clas.newInstance();
            } catch (Exception e) {
                throw new Capabilities.Unavailable("ClassGenerator unavailable: " + e);
            }
        }

        return cg;
    }


    /**
     * Invoke a super.method() style superclass method on an object instance.
     * This is not a normal function of the Java reflection API and is
     * provided by generated class accessor methods.
     */
    public abstract Object invokeSuperclassMethod(
            BshClassManager bcm, Object instance, String methodName, Object[] args
    )
            throws UtilEvalError, ReflectError, InvocationTargetException;


}
