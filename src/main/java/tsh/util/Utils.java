package tsh.util;

import tsh.CallStack;
import tsh.Interpreter;
import tsh.NameSpace;
import tsh.SimpleNode;
import tsh.exception.InterpreterError;
import tsh.exception.ParseException;
import tsh.expression.*;
import tsh.service.ImportHelpService;
import tsh.t.TSHTuple;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {

    static Window splashScreen;

    /*
        This could live in the desktop script.
        However we'd like to get it on the screen as quickly as possible.
    */
    public static void startSplashScreen() {
        int width = 275, height = 148;
        Window win = new Window(new Frame());
        win.pack();
        BshCanvas can = new BshCanvas();
        can.setSize(width, height); // why is this necessary?
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        win.setBounds(
                dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        win.add("Center", can);
        Image img = tk.getImage(
                Interpreter.class.getResource("/tsh/util/lib/splash.gif"));
        MediaTracker mt = new MediaTracker(can);
        mt.addImage(img, 0);
        try {
            mt.waitForAll();
        } catch (Exception e) {
        }
        Graphics gr = can.getBufferedGraphics();
        gr.drawImage(img, 0, 0, can);
        win.setVisible(true);
        win.toFront();
        splashScreen = win;
    }

    public static void endSplashScreen() {
        if (splashScreen != null)
            splashScreen.dispose();
    }

    public static boolean eq(String value, String... str) {
        for (String s : str) {
            if (!s.equals(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean notEq(String value, String... str) {
        return !eq(value, str);
    }

    public static boolean eqOR(String value, String... str) {
        for (String s : str) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static TSHTuple run(String script, Map<String, Object> init, Map<String, Object> imports, ImportHelpService helpService) throws Exception {
        Object retVal = null;
        Reader in = null;
        Map<String, Object> global = new LinkedHashMap<>();
        try {
            in = new BufferedReader(new StringReader(script));
            Interpreter localInterpreter = new Interpreter(in, System.out, System.err, false, null, null, script);
            BshClassManager bcm = BshClassManager.createClassManager(localInterpreter);
            NameSpace nameSpace = new NameSpace(bcm, "global");
            CallStack callstack = new CallStack(nameSpace);
            Utils.batchInitVariable(nameSpace, init);
            boolean eof = false;
            while (!eof) {
                SimpleNode node = null;
                try {
                    eof = localInterpreter.Line();
                    if (localInterpreter.get_jjtree().nodeArity() > 0) {
                        node = (SimpleNode) localInterpreter.get_jjtree().rootNode();
                        node.setSourceFile(script);
                        if (node instanceof TSHImportDeclaration) {
                            ((TSHImportDeclaration) node).eval(imports, helpService, callstack, localInterpreter);
                        } else {
                            retVal = node.eval(callstack, localInterpreter);

                            if (callstack.depth() > 1)
                                throw new InterpreterError("Callstack growing: " + callstack);
                            if (retVal instanceof ReturnControl) {
                                retVal = ((ReturnControl) retVal).value;
                                return new TSHTuple(allVariable(nameSpace), global, retVal);
                            }
                            if (retVal instanceof GlobalControl) {
                                global.put(((GlobalControl) retVal).name, ((GlobalControl) retVal).value);
                            }
                            if (retVal instanceof ExportControl) {
                                retVal = ((ExportControl) retVal).export;
                                return new TSHTuple(allVariable(nameSpace), global, retVal);
                            }
                        }
                    }
                } catch (ParseException e) {
                    throw e;
                } catch (Exception e) {
                    throw e;
                } finally {
                    localInterpreter.get_jjtree().reset();
                    if (callstack.depth() > 1) {
                        callstack.clear();
                        callstack.push(nameSpace);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return null;
    }

    public static Map<String, Object> allVariable(NameSpace nameSpace) throws Exception {
        Map<String, Object> map = new HashMap<>();
        for (String name : nameSpace.getVariableNames()) {
            Object value = nameSpace.getVariable(name);
            if (value != null) {
                map.put(name, value);
            }
        }
        for (String name : nameSpace.getMethodNames()) {
            Object value = nameSpace.getVariable(name);
            if (value != null) {
                map.put(name, value);
            }
        }
        return map;
    }

    public static void batchInitVariable(NameSpace nameSpace, Map<String, Object> map) throws Exception {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            setVariable(nameSpace, entry.getKey(), entry.getValue());
        }
    }

    public static void setVariable(NameSpace nameSpace, String name, Object value) throws Exception {
        if (value == null) {
            return;
        }
        if (value instanceof TshMethod) {
            nameSpace.setMethod(name, (TshMethod) value);
        } else {
            nameSpace.setVariable(name, value, false);
        }
    }


}
