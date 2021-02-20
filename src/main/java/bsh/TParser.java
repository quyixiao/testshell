package bsh;

import java.util.HashMap;
import java.util.Map;

public class TParser extends TParserBase implements TParserConstants, TParserTreeConstants {


    public static final Map<String, Integer> idMap = new HashMap<>();

    static {
        idMap.put(T_MethodDeclaration, 1);
        idMap.put(T_VariableDeclarator, 2);
        idMap.put(T_ArrayInitializer, 3);
        idMap.put(T_FormalParameters, 4);
        idMap.put(T_FormalParameter, 5);
        idMap.put(T_ReturnType, 6);
        idMap.put(T_AmbiguousName, 7);
        idMap.put(T_Assignment, 8);
        idMap.put(T_TernaryExpression, 9);
        idMap.put(T_BinaryExpression, 10);
        idMap.put(T_UnaryExpression, 11);
        idMap.put(T_CastExpression, 12);
        idMap.put(T_PrimaryExpression, 13);
        idMap.put(T_MethodInvocation, 14);
        idMap.put(T_PrimarySuffix, 15);
        idMap.put(T_Literal, 16);
        idMap.put(T_Arguments, 17);
        idMap.put(T_AllocationExpression, 18);
        idMap.put(T_ArrayDimensions, 19);
        idMap.put(T_Block, 20);
        idMap.put(T_SwitchStatement, 21);
        idMap.put(T_SwitchLabel, 22);
        idMap.put(T_IfStatement, 23);
        idMap.put(T_WhileStatement, 24);
        idMap.put(T_ForStatement, 25);
        idMap.put(T_TypedVariableDeclaration, 26);
        idMap.put(T_StatementExpressionList, 27);
        idMap.put(T_ReturnStatement, 28);
        idMap.put(T_ThrowStatement, 29);
        idMap.put(T_TryStatement, 30);
    }


    protected JJTParserState jjtree = new JJTParserState();
    public TParserTokenManager token_source;
    JavaCharStream jj_input_stream;
    public Token token, jj_nt;
    private String jj_ntk;
    private Token jj_scanpos, jj_lastpos;
    private int jj_la;
    public boolean lookingAhead = false;
    private boolean jj_semLA;
    private int jj_gen;
    final private int[] jj_la1 = new int[88];
    static private int[] jj_la1_0;
    static private int[] jj_la1_1;
    static private int[] jj_la1_2;
    static private int[] jj_la1_3;
    static private int[] jj_la1_4;


    final private JJCalls[] jj_2_rtns = new JJCalls[31];
    private boolean jj_rescan = false;
    private int jj_gc = 0;
    private java.util.Vector jj_expentries = new java.util.Vector();
    private int[] jj_expentry;
    private String jj_kind = "-1";
    private int[] jj_lasttokens = new int[100];
    private int jj_endpos;

    boolean retainComments = false;

    static {
        jj_la1_0();
        jj_la1_1();
        jj_la1_2();
        jj_la1_3();
        jj_la1_4();
    }

    private static void jj_la1_0() {
        jj_la1_0 = new int[]{0x1, 0x8000400, 0x8000400, 0x2000, 0x2000000, 0x0, 0x20424800, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x24424800, 0x24424800, 0x0, 0x0, 0x20424800, 0x0, 0x20424800, 0x20424800, 0x20424800, 0x0, 0x24424800, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x24424800, 0x0, 0x0, 0x24424800, 0x4000000, 0x0, 0x0, 0x24424800, 0x0, 0x0, 0x4000000, 0x0, 0x0, 0x0, 0x4000000, 0x4000000, 0x24424800, 0x0, 0x0, 0x0, 0x0, 0x0, 0x24624800, 0x40081000, 0x0, 0x0, 0x108000, 0x108000, 0x800000, 0x2c424c00, 0x24424800, 0x24424800, 0x40000000, 0x24424800, 0x0, 0x0, 0x0, 0x0, 0x24424800, 0x10000, 0x10000000,};
    }

    private static void jj_la1_1() {
        jj_la1_1 = new int[]{0x0, 0x41b3880, 0x41b3880, 0x20, 0x0, 0x2, 0x2008050, 0x400000, 0x10000, 0x10000, 0x0, 0x4, 0x0, 0x12808350, 0x12808350, 0x0, 0x0, 0x8050, 0x0, 0x8050, 0x2008050, 0x8050, 0x0, 0x12808350, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x12808350, 0x0, 0x0, 0x12808350, 0x12800300, 0x0, 0x0, 0x12808350, 0x0, 0x0, 0x12800300, 0x0, 0x0, 0x0, 0x12800200, 0x800000, 0x12808350, 0x0, 0x0, 0x100, 0x0, 0x0, 0x1a858351, 0x1284000, 0x10000, 0x404, 0x0, 0x0, 0x0, 0x169bbbd0, 0x12808350, 0x12808350, 0x0, 0x12808350, 0x0, 0x0, 0x0, 0x0, 0x12808350, 0x0, 0x0,};
    }

    private static void jj_la1_2() {
        jj_la1_2 = new int[]{0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x20, 0x0, 0x4400, 0x0, 0x10000, 0x0, 0x20000, 0xc0052d, 0xc0052d, 0x8000, 0x8000, 0x20, 0x20, 0x20, 0x20, 0x0, 0x8000, 0xc0012d, 0x20000, 0x1000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x84000000, 0x84000000, 0x0, 0x783c0000, 0x783c0000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xc0012d, 0xc00000, 0xc00000, 0x12d, 0xc0012d, 0x100, 0x0, 0x12d, 0x100, 0x11400, 0x10d, 0x20, 0x100, 0x11400, 0xd, 0x0, 0xc0012d, 0x8000, 0x1100, 0x0, 0x1000, 0x1000, 0xc0452d, 0x0, 0x0, 0x10, 0x0, 0x0, 0x0, 0xc0012d, 0xc0012d, 0xc0012d, 0x0, 0xc0012d, 0x8000, 0x8000, 0x20, 0x20, 0xc0012d, 0x0, 0x0,};
    }

    private static void jj_la1_3() {
        jj_la1_3 = new int[]{0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xf0, 0xf0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xf0, 0xd7c00000, 0x0, 0x3, 0x3, 0xc, 0xc, 0x3000, 0x3000, 0x4000, 0xc00, 0xc00, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3f0000, 0x3f0000, 0xc0, 0xc0, 0x8300, 0x8300, 0xc0, 0xf0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x30, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xf0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xf0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xf0, 0xf0, 0xf0, 0x0, 0xf0, 0x0, 0x0, 0x0, 0x0, 0xf0, 0x0, 0x0,};
    }

    private static void jj_la1_4() {
        jj_la1_4 = new int[]{0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3f, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,};
    }


    public void setRetainComments(boolean b) {
        retainComments = b;
    }


    public static int getId(String key) {
        return idMap.get(key);
    }

    public SimpleNode popNode() {
        if (jjtree.nodeArity() > 0) {  // number of child nodes
            return (SimpleNode) jjtree.popNode();
        } else {
            return null;
        }
    }


    void jjtreeOpenNodeScope(Node n) {
        ((SimpleNode) n).firstToken = getToken(1);
    }


    void jjtreeCloseNodeScope(Node n) {
        ((SimpleNode) n).lastToken = getToken(0);
    }


    final public Token getToken(int index) {
        Token t = lookingAhead ? jj_scanpos : token;
        for (int i = 0; i < index; i++) {
            if (t.next != null) {
                t = t.next;
            } else {
                t = t.next = token_source.getNextToken();
            }
        }
        return t;
    }


    public TParser(java.io.Reader stream) {
        jj_input_stream = new JavaCharStream(stream, 1, 1);
        token_source = new TParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = default_jjmatchedKind;
        jj_gen = 0;
        for (int i = 0; i < 88; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new JJCalls();
        }
    }

    final private String jj_ntk() {
        if ((jj_nt = token.next) == null) {
            return (jj_ntk = (token.next = token_source.getNextToken()).tKind);
        } else {
            return (jj_ntk = jj_nt.tKind);
        }
    }


    static final class JJCalls {
        int gen;
        Token first;
        int arg;
        JJCalls next;
    }

    final private Token jj_consume_token(String kind) throws Exception {
        Token oldToken;
        if ((oldToken = token).next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = default_1;
        if (eq(token.tKind, kind)) {
            jj_gen++;
            if (++jj_gc > 100) {
                jj_gc = 0;
                for (int i = 0; i < jj_2_rtns.length; i++) {
                    JJCalls c = jj_2_rtns[i];
                    while (c != null) {
                        if (c.gen < jj_gen) {
                            c.first = null;
                        }
                        c = c.next;
                    }
                }
            }
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw new Exception();
    }


    final public void BlockStatement() throws ParseException {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case VAR:
                VariableDeclaration();
                break;
            case METHOD:
                break;
            default:
                Statement();
        }

    }


    private void VariableDeclaration() {
        /*@bgen(jjtree) VariableDeclarator */
        BSHVariableDeclarator jjtn000 = new BSHVariableDeclarator(getId(T_VariableDeclarator));
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t;
        try {
            t = jj_consume_token(VAR);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case ASSIGN:
                    jj_consume_token(ASSIGN);
                    VariableInitializer();
                    break;
                default:
                    jj_la1[12] = jj_gen;
                    ;
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.name = t.image;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }


    }

    private void MethodDeclaration() {

    }

    private void Statement() {

    }

    final public void VariableInitializer() throws Exception {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case LBRACE:
                //ArrayInitializer();
                break;
            case FALSE:
            case NULL:
            case TRUE:
            case LITERAL:
            case LPAREN:
            case BANG:
            case TILDE:
            case INCR:
            case DECR:
            case PLUS:
            case MINUS:
                Expression();
                break;
            default:
                jj_la1[13] = jj_gen;
                jj_consume_token(default_1);
                throw new Exception();
        }
    }

    private void Expression() {

    }

    final public boolean Line() throws Exception {
        switch ((jj_ntk == default_jjmatchedKind) ? jj_ntk() : jj_ntk) {
            case DEFAULT:
                jj_consume_token("0");
                Interpreter.debug("End of File!");
            {
                if (true) return true;
            }
            break;
            default:
                jj_la1[0] = jj_gen;
                BlockStatement();
                if (true) return false;
        }
        throw new Error("Missing return statement in function");
    }
}
