package tsh.expression;

import tsh.*;
import tsh.exception.EvalError;
import tsh.exception.InterpreterError;
import tsh.exception.TargetError;
import tsh.exception.UtilEvalError;

import java.util.Vector;

public class TSHTryStatement extends SimpleNode {

    public TSHTryStatement(String id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        TSHBlock tryBlock = ((TSHBlock) jjtGetChild(0));

        Vector catchParams = new Vector();
        Vector catchBlocks = new Vector();

        int nchild = jjtGetNumChildren();
        Node node = null;
        int i = 1;
        while ((i < nchild) && ((node = jjtGetChild(i++)) instanceof TSHFormalParameter)) {
            catchParams.addElement(node);
            catchBlocks.addElement(jjtGetChild(i++));
            node = null;
        }
        TSHBlock finallyBlock = null;
        if (node != null) {
            finallyBlock = (TSHBlock) node;
        }

        TargetError target = null;
        Throwable thrown = null;
        Object ret = null;
        int callstackDepth = callstack.depth();
        try {
            ret = tryBlock.eval(callstack, interpreter);
        } catch (TargetError e) {
            target = e;
            String stackInfo = "Bsh Stack: ";
            while (callstack.depth() > callstackDepth)
                stackInfo += "\t" + callstack.pop() + "\n";
        }
        if (target != null) {
            thrown = target.getTarget();
        }

        if (thrown != null) {
            int n = catchParams.size();
            for (i = 0; i < n; i++) {
                TSHFormalParameter fp = (TSHFormalParameter) catchParams.elementAt(i);
                fp.eval(callstack, interpreter);
                TSHBlock cb = (TSHBlock) (catchBlocks.elementAt(i));
                NameSpace enclosingNameSpace = callstack.top();
                BlockNameSpace cbNameSpace = new BlockNameSpace(enclosingNameSpace);
                try {
                    cbNameSpace.setBlockVariable(fp.name, thrown);
                } catch (UtilEvalError e) {
                    throw new InterpreterError("Unable to set var in catch block namespace.");
                }
                callstack.swap(cbNameSpace);
                try {
                    ret = cb.eval(callstack, interpreter);
                } finally {
                    callstack.swap(enclosingNameSpace);
                }
                target = null;
                break;
            }
        }
        if (finallyBlock != null) {
            ret = finallyBlock.eval(callstack, interpreter);
        }

        if (target != null) {
            throw target;
        }

        if (ret instanceof ReturnControl) {
            return ret;
        } else {
            return Primitive.VOID;
        }
    }

}
