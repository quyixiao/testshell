package tsh;

import bsh.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private String[] jj_expentry;
    private String jj_kind = "-1";
    private String[] jj_lasttokens = new String[100];
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

    static private final class LookaheadSuccess extends java.lang.Error {
    }

    final private TParser.LookaheadSuccess jj_ls = new TParser.LookaheadSuccess();


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

    final private boolean jj_scan_token(String kind) {
        if (jj_scanpos == jj_lastpos) {
            if (jj_scanpos.next == null) {
                jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
            } else {
                jj_lastpos = jj_scanpos = jj_scanpos.next;
            }
        } else {
            jj_scanpos = jj_scanpos.next;
        }
        if (!eq(jj_scanpos.tKind , kind)) return true;
        return false;
    }

    private void jj_add_error_token(String kind, int pos) {
        if (pos >= 100) return;
        if (pos == jj_endpos + 1) {
            jj_lasttokens[jj_endpos++] = kind;
        } else if (jj_endpos != 0) {
            jj_expentry = new String[jj_endpos];
            for (int i = 0; i < jj_endpos; i++) {
                jj_expentry[i] = jj_lasttokens[i];
            }
            boolean exists = false;
            for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements(); ) {
                String[] oldentry = (String[]) (e.nextElement());
                if (oldentry.length == jj_expentry.length) {
                    exists = true;
                    for (int i = 0; i < jj_expentry.length; i++) {
                        if (oldentry[i] != jj_expentry[i]) {
                            exists = false;
                            break;
                        }
                    }
                    if (exists) break;
                }
            }
            if (!exists) jj_expentries.addElement(jj_expentry);
            if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
        }
    }

    final public void BlockStatement() throws ParseException {
       if(isMethod()){
           MethodDeclaration();
       }
    }


    private void VariableDeclaration() {


    }

    private void MethodDeclaration()   throws ParseException{
        /*@bgen(jjtree) MethodDeclaration */
        TSHMethodDeclaration jjtn000 = new TSHMethodDeclaration(T_MethodDeclaration);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            jj_consume_token(DEF);
            t = jj_consume_token(LITERAL);
            jjtn000.methodName = t.image;
            FormalParameters();


            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LBRACE:
                    Block();
                    break;
                default:
                    jj_la1[8] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }

        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    if (true) throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    if (true) throw (ParseException) jjte000;
                }
            }
            {
                if (true) throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }
    }


    final public void Block() throws ParseException {
        TSHBlock jjtn000 = new TSHBlock(T_Block);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token s = null;
        try {
            jj_consume_token(LBRACE);
            label_22:
            while (true) {
                if (jj_2_23(1)) {
                    ;
                } else {
                    break label_22;
                }
                BlockStatement();
            }
            jj_consume_token(RBRACE);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    if (true) throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    if (true) throw (ParseException) jjte000;
                }
            }
            {
                if (true) throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }
    }


    final public void FormalParameters() throws ParseException {
        TSHFormalParameters jjtn000 = new TSHFormalParameters(T_FormalParameters);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            List<String> paramterNames = new ArrayList<>();
            jj_consume_token(LPAREN);
            Token t;
            label_3:
            while(true){
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case LITERAL:
                        t = jj_consume_token(LITERAL);
                        paramterNames.add(t.image);
                        break;
                    case RPAREN:
                        break label_3;
                    case COMMA:
                        jj_consume_token(COMMA);
                        break ;
                    default:
                        jj_la1[17] = jj_gen;
                        ;
                }
            }
            jj_consume_token(RPAREN);

            jjtn000.numArgs = paramterNames.size();
            jjtn000.paramNames = paramterNames.toArray(new String[paramterNames.size()]);


        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    if (true) throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    if (true) throw (ParseException) jjte000;
                }
            }
            {
                if (true) throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }
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


    final private boolean isMethod() {
        jj_lastpos = jj_scanpos = token;
        try {
            return !notDef();
        } catch (TParser.LookaheadSuccess ls) {
            return true;
        } finally {

        }
    }



    final private boolean notDef() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(DEF)) {
            jj_scanpos = xsp;
        }
        return false;
    }



    final private boolean jj_2_23(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3R_28();
        } catch (TParser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(22, xla);
        }
    }


    final private boolean jj_3R_28() {
        Token xsp;
        xsp = jj_scanpos;
        if (jjMethod()) {                       // method
            jj_scanpos = xsp;
            /*if (jj_3R_49()) {               // 定义一个数组类型
                jj_scanpos = xsp;
                if (jj_3_28()) {  // do while ,while ,for , static {}, synchronized (){} 等
                    jj_scanpos = xsp;
                    if (jj_3R_50()) {               // import *   ; import
                        jj_scanpos = xsp;
                        if (jj_3R_51()) {               // package com.lz;
                            jj_scanpos = xsp;
                            if (jj_3R_52()) return true;        // formal_comment
                        }
                    }
                }
            }*/
        }
        return false;
    }

    final private boolean jjMethod() {
        Token xsp;
        xsp = jj_scanpos;
        /*if (jj_3R_126()) {              // 不是文本
            jj_scanpos = xsp;
            if (jj_3R_127()) return true;       // 表明是一个方法
        }
        if (jj_3R_43()) return true;        // 方法 （ 类型 变量， 类型 变量, ... )
        xsp = jj_scanpos;
        if (jj_3R_174()) jj_scanpos = xsp;          // 方法是否 throws 异常
        xsp = jj_scanpos;
        if (jj_3R_175()) {              //          方法参数的 { ,body ,}
            jj_scanpos = xsp;
            if (jj_scan_token(78)) return true;     //如果 没有 {,body,} ,则方法一定有; 表明是一个抽象方法
        }*/
        return false;
    }


    final private boolean jj_3R_126() {
        if (jj_scan_token(LITERAL)) return true;
        return false;
    }

    final private void jj_save(int index, int xla) {
        TParser.JJCalls p = jj_2_rtns[index];
        while (p.gen > jj_gen) {
            if (p.next == null) {
                p = p.next = new TParser.JJCalls();
                break;
            }
            p = p.next;
        }
        p.gen = jj_gen + xla - jj_la;
        p.first = token;
        p.arg = xla;
    }


}
