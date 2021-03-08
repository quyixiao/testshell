package test;

import org.junit.Test;
import tsh.CallStack;
import tsh.Interpreter;
import tsh.NameSpace;
import tsh.SimpleNode;
import tsh.exception.InterpreterError;
import tsh.exception.ParseException;
import tsh.expression.ReturnControl;
import tsh.util.BshClassManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class RunTest {


    @Test
    public void test() throws Exception {
        Object retVal = null;
        String file = "/Users/quyixiao/git/java-python/script/kelihua/kelihua10.tsh";
        Reader in = new BufferedReader(new FileReader(file));
        Interpreter localInterpreter =
                new Interpreter(
                        in, System.out, System.err, false, null, null, file);
        BshClassManager bcm = BshClassManager.createClassManager(localInterpreter);
        NameSpace nameSpace = new NameSpace(bcm, "global");
        CallStack callstack = new CallStack(nameSpace);

        boolean eof = false;
        while (!eof) {
            SimpleNode node = null;
            try {
                eof = localInterpreter.Line();
                if (localInterpreter.get_jjtree().nodeArity() > 0) {
                    node = (SimpleNode) localInterpreter.get_jjtree().rootNode();
                    node.setSourceFile(file);
                    retVal = node.eval(callstack, localInterpreter);
                    if (callstack.depth() > 1)
                        throw new InterpreterError(
                                "Callstack growing: " + callstack);

                    if (retVal instanceof ReturnControl) {
                        retVal = ((ReturnControl) retVal).value;
                        break; // non-interactive, return control now
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                localInterpreter.get_jjtree().reset();
                if (callstack.depth() > 1) {
                    callstack.clear();
                    callstack.push(nameSpace);
                }
            }
        }
    }
}

