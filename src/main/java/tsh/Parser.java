package tsh;

import tsh.constant.TParserConstants;
import tsh.constant.TParserTreeConstants;
import tsh.entity.TBigDecimal;
import tsh.exception.ParseException;
import tsh.expression.*;
import tsh.util.*;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Parser extends Utils implements TParserConstants, TParserTreeConstants {


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


    public JJTParserState jjtree = new JJTParserState();
    public TParserTokenManager token_source;
    public JavaCharStream jj_input_stream;
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

    final private Parser.LookaheadSuccess jj_ls = new Parser.LookaheadSuccess();


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

    public void reInitInput(Reader in) {
        ReInit(in);
    }

    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = default_1;
        jjtree.reset();
        jj_gen = 0;
        for (int i = 0; i < 88; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new Parser.JJCalls();
        }
    }


    public void reInitTokenInput(Reader in) {
        jj_input_stream.ReInit(in,
                jj_input_stream.getEndLine(),
                jj_input_stream.getEndColumn());
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


    public Parser(java.io.Reader stream) {
        jj_input_stream = new JavaCharStream(stream, 1, 1);
        token_source = new TParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = default_1;
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
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        } else {
            return (jj_ntk = jj_nt.kind);
        }
    }


    static final class JJCalls {
        int gen;
        Token first;
        int arg;
        JJCalls next;
    }

    final private Token jj_consume_token(String kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = default_1;
        if (eq(token.kind, kind)) {
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
        throw new ParseException();
    }



    final private Token jj_consume_token_util(String kind) throws ParseException {
        Token oldToken;
        while(true){
            if ((oldToken = token).next != null) {
                token = token.next;
            } else {
                token = token.next = token_source.getNextToken();
            }
            jj_ntk = default_1;
            if (eq(token.kind, kind)) {
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
        }
    }

    final public Token getNextToken() {
        if (token.next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = default_1;
        jj_gen++;
        return token;
    }

    final private boolean jj_scan_token(String kind) {
        if (jj_scanpos == jj_lastpos) {
            jj_la--;
            if (jj_scanpos.next == null) {
                jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
            } else {
                jj_lastpos = jj_scanpos = jj_scanpos.next;
            }
        } else {
            jj_scanpos = jj_scanpos.next;
        }
        if (jj_rescan) {
            int i = 0;
            Token tok = token;
            while (tok != null && tok != jj_scanpos) {
                i++;
                tok = tok.next;
            }
            if (tok != null) jj_add_error_token(kind, i);
        }
        if (!eq(jj_scanpos.kind, kind)) return true;
        if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
        return false;
    }

    //没有匹配到返回 true ，匹配到了返回 false
    final private boolean jj_scan_token_util(String kind) {
        while (true) {
            if (jj_scanpos == jj_lastpos) {
                if (jj_scanpos.next == null) {
                    jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
                } else {
                    jj_lastpos = jj_scanpos = jj_scanpos.next;
                }
            } else {
                jj_scanpos = jj_scanpos.next;
            }
            if (eq(jj_scanpos.kind, kind)) {
                return false;
            }
            if (eqOR(jj_scanpos.kind, NEXT_LINE)) {
                return true;
            }
        }
    }



    final private Token jj_scan_token_next() {
        if (jj_scanpos == jj_lastpos) {
            if (jj_scanpos.next == null) {
                jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
            } else {
                jj_lastpos = jj_scanpos = jj_scanpos.next;
            }
        } else {
            jj_scanpos = jj_scanpos.next;
        }
        return jj_scanpos;
    }

    //匹配到了，返回 false,没有匹配到，返回 true
    final private boolean jj_scan_token_util(String match, String kind) {
        int flag = 0;
        while (true) {
            if (jj_scanpos == jj_lastpos) {
                if (jj_scanpos.next == null) {
                    jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
                } else {
                    jj_lastpos = jj_scanpos = jj_scanpos.next;
                }
            } else {
                jj_scanpos = jj_scanpos.next;
            }
            if (eq(jj_scanpos.kind, match)) {
                flag++;
                continue;
            }
            if (eq(jj_scanpos.kind, kind)) {
                if (flag == 0) {
                    return false;
                } else {
                    flag--;
                }
            }
            if (eqOR(jj_scanpos.kind, NEXT_LINE)) {
                return true;
            }
        }
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
        lable_:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NEXT_LINE:
                    jj_consume_token(NEXT_LINE);
                    break ;
                default:
                    break lable_;
            }
        }
        if (isMethod()) {
            MethodDeclaration();
        } else {
            Statement();
        }
    }


    private void MethodDeclaration() throws ParseException {
        TSHMethodDeclaration jjtn000 = new TSHMethodDeclaration(T_MethodDeclaration);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            jj_consume_token(DEF);
            t = jj_consume_token(IDENTIFIER);
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
            jj_consume_token_util(RBRACE);
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
                throw (RuntimeException) jjte000;
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException) jjte000;
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
            jj_consume_token(LPAREN);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case IDENTIFIER:
                    FormalParameter();
                    label_3:
                    while (true) {
                        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                            case COMMA:
                                ;
                                break;
                            default:
                                jj_la1[16] = jj_gen;
                                break label_3;
                        }
                        jj_consume_token(COMMA);
                        FormalParameter();
                    }
                    break;
                default:
                    jj_la1[17] = jj_gen;
            }
            jj_consume_token(RPAREN);
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

    final public void FormalParameter() throws ParseException {
        TSHFormalParameter jjtn000 = new TSHFormalParameter(T_FormalParameter);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t;
        try {
            t = jj_consume_token(IDENTIFIER);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.name = t.image;
            t = getNextToken();
            if (eq(t.kind, ASSIGN)) { //如果是等于号
                Expression();
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


    /*
     * Statement syntax follows.
     */
    final public void Statement() throws ParseException {
        if (jj_3_40(3)) {
            LabeledStatement();                         // break lable_;的情况
        } else {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LBRACE:
                    Block();
                    break;
                case FALSE:
                case NULL:
                case TRUE:
                case STR:
                case IDENTIFIER:
                case LPAREN:
                case BANG:
                case TILDE:
                case INCR:
                case DECR:
                case PLUS:
                case MINUS:
                    StatementExpression();
                    break;
                case SWITCH:
                    SwitchStatement();
                    break;
                case IF:
                    IfStatement();
                    break;
                case WHILE:
                    WhileStatement();
                    break;
                case DO:
                    DoStatement();
                    break;
                default:
                    jj_la1[69] = jj_gen;
                    switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                        case FOR:
                            ForStatement();
                            break;
                        case BREAK:
                            BreakStatement();
                            break;
                        case CONTINUE:
                            ContinueStatement();
                            break;
                        case RETURN:
                            ReturnStatement();
                            break;
                        default:
                            jj_la1[70] = jj_gen;
                            jj_consume_token(default_1);
                            throw new ParseException();
                    }
            }
        }
    }


    final public void WhileStatement() throws ParseException {
        /*@bgen(jjtree) WhileStatement */
        TSHWhileStatement jjtn000 = new TSHWhileStatement(T_WhileStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(WHILE);
            jj_consume_token(LPAREN);
            Expression();
            jj_consume_token(RPAREN);
            Statement();
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


    final public void DoStatement() throws ParseException {
        /*@bgen(jjtree) WhileStatement */
        TSHWhileStatement jjtn000 = new TSHWhileStatement(T_WhileStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(DO);
            Statement();
            jj_consume_token(WHILE);
            jj_consume_token(LPAREN);
            Expression();
            jj_consume_token(RPAREN);
            jj_consume_token(SEMICOLON);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.isDoStatement = true;
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

    // label_xx: 用 break 的情况
    final public void LabeledStatement() throws ParseException {
        jj_consume_token(IDENTIFIER);
        jj_consume_token(COLON);
        Statement();
    }

    final public void StatementExpression() throws ParseException {
        Expression();
    }

    final public void SwitchStatement() throws ParseException {
        TSHSwitchStatement jjtn000 = new TSHSwitchStatement(T_SwitchStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(SWITCH);
            jj_consume_token(LPAREN);
            Expression();
            jj_consume_token(RPAREN);
            jj_consume_token(LBRACE);
            label_23:
            while (true) {
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case CASE:
                    case _DEFAULT:
                        ;
                        break;
                    default:
                        jj_la1[73] = jj_gen;
                        break label_23;
                }
                SwitchLabel();
                label_24:
                while (true) {
                    if (jj_2_29(1)) {
                        ;
                    } else {
                        break label_24;
                    }
                    BlockStatement();
                }
            }
            jj_consume_token(RBRACE);
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


    final public void SwitchLabel() throws ParseException {
        TSHSwitchLabel jjtn000 = new TSHSwitchLabel(T_SwitchLabel);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case CASE:
                    jj_consume_token(CASE);
                    Expression();
                    jj_consume_token(COLON);
                    break;
                case _DEFAULT:
                    jj_consume_token(_DEFAULT);
                    jj_consume_token(COLON);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.isDefault = true;
                    break;
                default:
                    jj_la1[74] = jj_gen;
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


    final public void IfStatement() throws ParseException {
        TSHIfStatement jjtn000 = new TSHIfStatement(T_IfStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(IF);
            jj_consume_token(LPAREN);
            Expression();
            jj_consume_token(RPAREN);
            Statement();
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case ELSE:
                    jj_consume_token(ELSE);
                    Statement();
                    break;
                default:
                    jj_la1[75] = jj_gen;
                    ;
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


    final public void ForStatement() throws ParseException {
        TSHForStatement jjtn000 = new TSHForStatement(T_ForStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            jj_consume_token(FOR);                  // for
            jj_consume_token(LPAREN);               // (
            t = jj_consume_token(IDENTIFIER);       // i
            jj_consume_token(IN);                // in
            Expression();                           // range(1,10)
            jj_consume_token(RPAREN);               // )
            Statement();                            // {}
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.varName = t.image;
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

    final public void BreakStatement() throws ParseException {      // break ; 或 break label_1 ; 两种情况
        TSHReturnStatement jjtn000 = new TSHReturnStatement(T_ReturnStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(BREAK);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case IDENTIFIER:
                    jj_consume_token(IDENTIFIER);
                    break;
                default:
                    jj_la1[83] = jj_gen;
                    ;
            }
            jj_consume_token(SEMICOLON);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.kind = BREAK;
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }
    }


    final public void ContinueStatement() throws ParseException {       // continue 和 continue label_1;
        TSHReturnStatement jjtn000 = new TSHReturnStatement(T_ReturnStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(CONTINUE);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case IDENTIFIER:
                    jj_consume_token(IDENTIFIER);
                    break;
                default:
                    jj_la1[84] = jj_gen;
                    ;
            }
            jj_consume_token(SEMICOLON);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.kind = CONTINUE;
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }
    }

    final public void ReturnStatement() throws ParseException {     // return 表达式 ;
        TSHReturnStatement jjtn000 = new TSHReturnStatement(T_ReturnStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(RETURN);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case FALSE:
                case NULL:
                case TRUE:
                case STR:
                case IDENTIFIER:
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
                    jj_la1[85] = jj_gen;
                    ;
            }
            jj_consume_token(SEMICOLON);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.kind = RETURN;
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

    final public void VariableInitializer() throws Exception {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case LBRACKET:
                ListInitializer();
                break;
            case LBRACE:
                MapInitializer();
                break;
            case LPAREN:
                if (isTuple(2147483647)) { //如果是元组
                    TupleInitializer();
                } else {
                    Expression();
                }
                break;
            case FALSE:
            case NULL:
            case TRUE:
            case STR:
            case NUMBER:
            case IDENTIFIER:
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

    final public void Expression() throws ParseException {
        if (jj_2_8(2147483647)) {
            Assignment();
        } else {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case BOOL:
                case FALSE:
                case NULL:
                case TRUE:
                case IDENTIFIER:
                case LPAREN:
                case BANG:
                case STR:
                case NUMBER:
                case TILDE:
                case INCR:
                case DECR:
                case PLUS:
                case MINUS:
                    ConditionalExpression();
                    break;
                default:
                    jj_la1[23] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
        }
    }


    final public void ListInitializer() throws ParseException {
        TSHListInitializer jjtn000 = new TSHListInitializer(T_ListInitializer);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(LBRACKET);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NUMBER:
                case FALSE:
                case NULL:
                case TRUE:
                case STR:
                case IDENTIFIER:
                case LPAREN:
                case LBRACE:
                case BANG:
                case TILDE:
                case INCR:
                case DECR:
                case PLUS:
                case MINUS:
                    VariableInitializer();
                    label_2:
                    while (true) {
                        if (jj_scan_token(COMMA)) {
                            ;
                        } else {
                            break label_2;
                        }
                        jj_consume_token(COMMA);
                        VariableInitializer();
                    }
                    break;
                default:
                    jj_la1[14] = jj_gen;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case COMMA:
                    jj_consume_token(COMMA);
                    break;
                default:
                    jj_la1[15] = jj_gen;
                    ;
            }
            jj_consume_token(RBRACKET);
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

    /*  { 'abc': 123, 98.6: 37 }   写法 */
    final public void MapInitializer() throws ParseException {
        TSHMapInitializer jjtn000 = new TSHMapInitializer(T_MapInitializer);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(LBRACE);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NUMBER:
                case FALSE:
                case NULL:
                case TRUE:
                case STR:
                case IDENTIFIER:
                case LPAREN:
                case LBRACE:
                case BANG:
                case TILDE:
                case INCR:
                case DECR:
                case PLUS:
                case MINUS:
                    VariableInitializer();
                    jj_consume_token(COLON);
                    VariableInitializer();
                    label_2:
                    while (true) {
                        if (jj_2_4(2)) {
                            ;
                        } else {
                            break label_2;
                        }
                        jj_consume_token(COMMA);
                        VariableInitializer();
                        jj_consume_token(COLON);
                        VariableInitializer();
                    }
                    break;
                default:
                    jj_la1[14] = jj_gen;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case COMMA:
                    jj_consume_token(COMMA);
                    break;
                default:
                    jj_la1[15] = jj_gen;
                    ;
            }
            jj_consume_token(RBRACKET);
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


    final public void TupleInitializer() throws ParseException {
        TSHMapInitializer jjtn000 = new TSHMapInitializer(T_TupleInitializer);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(LPAREN);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NUMBER:
                case FALSE:
                case NULL:
                case TRUE:
                case STR:
                case IDENTIFIER:
                case LPAREN:
                case LBRACE:
                case BANG:
                case TILDE:
                case INCR:
                case DECR:
                case PLUS:
                case MINUS:
                    VariableInitializer();
                    label_2:
                    while (true) {
                        if (jj_2_4(2)) {
                            ;
                        } else {
                            break label_2;
                        }
                        jj_consume_token(COMMA);
                        VariableInitializer();
                    }
                    break;
                default:
                    jj_la1[14] = jj_gen;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case COMMA:
                    jj_consume_token(COMMA);
                    break;
                default:
                    jj_la1[15] = jj_gen;
                    ;
            }
            jj_consume_token(RPAREN);
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


    final public void Assignment() throws ParseException {
        /*@bgen(jjtree) Assignment */
        TSHAssignment jjtn000 = new TSHAssignment(T_Assignment);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        String op;
        try {
            PrimaryExpression();
            op = AssignmentOperator();
            jjtn000.operator = op;
            Expression();
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


    final public String AssignmentOperator() throws ParseException {
        Token t;
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case ASSIGN:
                jj_consume_token(ASSIGN);
                break;
            case STARASSIGN:
                jj_consume_token(STARASSIGN);
                break;
            case SLASHASSIGN:
                jj_consume_token(SLASHASSIGN);
                break;
            case MODASSIGN:
                jj_consume_token(MODASSIGN);
                break;
            case PLUSASSIGN:
                jj_consume_token(PLUSASSIGN);
                break;
            case MINUSASSIGN:
                jj_consume_token(MINUSASSIGN);
                break;
            case ANDASSIGN:
                jj_consume_token(ANDASSIGN);
                break;
            case XORASSIGN:
                jj_consume_token(XORASSIGN);
                break;
            case ORASSIGN:
                jj_consume_token(ORASSIGN);
                break;
            case LSHIFTASSIGN:
                jj_consume_token(LSHIFTASSIGN);
                break;
            case RSIGNEDSHIFTASSIGN:
                jj_consume_token(RSIGNEDSHIFTASSIGN);
                break;
            case RUNSIGNEDSHIFTASSIGN:
                jj_consume_token(RUNSIGNEDSHIFTASSIGN);
                break;
            default:
                jj_la1[24] = jj_gen;
                jj_consume_token(default_1);
                throw new ParseException();
        }
        t = getToken(0);
        {
            if (true) {
                return t.kind;
            }
        }
        throw new Error("Missing return statement in function");
    }


    /*    boolean a = true;
    int b = 3;
    int d = 4;
    int x =a ? b *=2 :( d*=2);*/
    final public void ConditionalExpression() throws ParseException {
        ConditionalOrExpression();
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case HOOK:
                jj_consume_token(HOOK);
                Expression();
                jj_consume_token(COLON);
                TSHTernaryExpression jjtn001 = new TSHTernaryExpression(T_TernaryExpression);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
                jjtreeOpenNodeScope(jjtn001);
                try {
                    ConditionalExpression();
                } catch (Throwable jjte001) {
                    if (jjtc001) {
                        jjtree.clearNodeScope(jjtn001);
                        jjtc001 = false;
                    } else {
                        jjtree.popNode();
                    }
                    if (jjte001 instanceof RuntimeException) {
                        {
                            if (true) throw (RuntimeException) jjte001;
                        }
                    }
                    if (jjte001 instanceof ParseException) {
                        {
                            if (true) throw (ParseException) jjte001;
                        }
                    }
                    {
                        if (true) throw (Error) jjte001;
                    }
                } finally {
                    if (jjtc001) {
                        jjtree.closeNodeScope(jjtn001, 3);
                        jjtreeCloseNodeScope(jjtn001);
                    }
                }
                break;
            default:
                jj_la1[25] = jj_gen;
        }
    }


    final public void ConditionalOrExpression() throws ParseException {
        Token t = null;
        ConditionalAndExpression();
        label_7:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case BOOL_OR:
                    ;
                    break;
                default:
                    jj_la1[26] = jj_gen;
                    break label_7;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case BOOL_OR:
                    t = jj_consume_token(BOOL_OR);
                    break;
                default:
                    jj_la1[27] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            ConditionalAndExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }

    final public void ConditionalAndExpression() throws ParseException {
        Token t = null;
        InclusiveOrExpression();
        label_8:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case BOOL_AND:
                    ;
                    break;
                default:
                    jj_la1[28] = jj_gen;
                    break label_8;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case BOOL_AND:
                    t = jj_consume_token(BOOL_AND);
                    break;
                default:
                    jj_la1[29] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            InclusiveOrExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }


    final public void InclusiveOrExpression() throws ParseException {
        Token t = null;
        ExclusiveOrExpression();
        label_9:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case OR:                    // |
                    ;
                    break;
                default:
                    jj_la1[30] = jj_gen;
                    break label_9;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case OR:
                    t = jj_consume_token(OR);
                    break;
                default:
                    jj_la1[31] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            ExclusiveOrExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }

    // ^
    final public void ExclusiveOrExpression() throws ParseException {
        Token t = null;
        AndExpression();
        label_10:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case XOR:
                    ;
                    break;
                default:
                    jj_la1[32] = jj_gen;
                    break label_10;
            }
            t = jj_consume_token(XOR);
            AndExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }

    final public void AndExpression() throws ParseException {
        Token t = null;
        EqualityExpression();
        label_11:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case AND:
                    ;
                    break;
                default:
                    jj_la1[33] = jj_gen;
                    break label_11;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case AND:
                    t = jj_consume_token(AND);
                    break;
                default:
                    jj_la1[34] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            EqualityExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }

    // == !=
    final public void EqualityExpression() throws ParseException {
        Token t = null;
        RelationalExpression();
        label_12:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case EQ:
                case NE:
                    ;
                    break;
                default:
                    jj_la1[35] = jj_gen;
                    break label_12;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case EQ:
                    t = jj_consume_token(EQ);
                    break;
                case NE:
                    t = jj_consume_token(NE);
                    break;
                default:
                    jj_la1[36] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            RelationalExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }


    final public void RelationalExpression() throws ParseException {
        Token t = null;
        ShiftExpression();
        label_13:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case GT:
                case LT:
                case LE:
                case GE:
                    ;
                    break;
                default:
                    jj_la1[38] = jj_gen;
                    break label_13;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LT:
                    t = jj_consume_token(LT);
                    break;
                case GT:
                    t = jj_consume_token(GT);
                    break;
                case LE:
                    t = jj_consume_token(LE);
                    break;
                case GE:
                    t = jj_consume_token(GE);
                    break;
                default:
                    jj_la1[39] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            ShiftExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }

    final public void ShiftExpression() throws ParseException {
        Token t = null;
        AdditiveExpression();
        label_14:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LSHIFT:    // <<
                case RSIGNEDSHIFT:  // >>
                case RUNSIGNEDSHIFT:    // >>>
                    ;
                    break;
                default:
                    jj_la1[40] = jj_gen;
                    break label_14;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LSHIFT:
                    t = jj_consume_token(LSHIFT);
                    break;
                case RSIGNEDSHIFT:
                    t = jj_consume_token(RSIGNEDSHIFT);
                    break;
                case RUNSIGNEDSHIFT:
                    t = jj_consume_token(RUNSIGNEDSHIFT);
                    break;
                default:
                    jj_la1[41] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            AdditiveExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }


    /**
     * + -
     */
    final public void AdditiveExpression() throws ParseException {
        Token t = null;
        MultiplicativeExpression();
        label_15:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case PLUS:
                case MINUS:
                    ;
                    break;
                default:
                    jj_la1[42] = jj_gen;
                    break label_15;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case PLUS:
                    t = jj_consume_token(PLUS);
                    break;
                case MINUS:
                    t = jj_consume_token(MINUS);
                    break;
                default:
                    jj_la1[43] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            MultiplicativeExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }


    /**
     * * / % 处理
     */
    final public void MultiplicativeExpression() throws ParseException {
        Token t = null;
        UnaryExpression();
        label_16:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case STAR:
                case SLASH:
                case MOD:
                    ;
                    break;
                default:
                    jj_la1[44] = jj_gen;
                    break label_16;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case STAR:
                    t = jj_consume_token(STAR);
                    break;
                case SLASH:
                    t = jj_consume_token(SLASH);
                    break;
                case MOD:
                    t = jj_consume_token(MOD);
                    break;
                default:
                    jj_la1[45] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            UnaryExpression();
            TSHBinaryExpression jjtn001 = new TSHBinaryExpression(T_BinaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 2);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 2);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        }
    }


    final public void UnaryExpression() throws ParseException {
        Token t = null;
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case PLUS:      // +
            case MINUS:     // -
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case PLUS: // ++
                        t = jj_consume_token(PLUS);
                        break;
                    case MINUS: // --
                        t = jj_consume_token(MINUS);
                        break;
                    default:
                        jj_la1[46] = jj_gen;
                        jj_consume_token(default_1);
                        throw new ParseException();
                }
                UnaryExpression();
                TSHUnaryExpression jjtn001 = new TSHUnaryExpression(T_UnaryExpression);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
                jjtreeOpenNodeScope(jjtn001);
                try {
                    jjtree.closeNodeScope(jjtn001, 1);
                    jjtc001 = false;
                    jjtreeCloseNodeScope(jjtn001);
                    jjtn001.kind = t.kind;
                } finally {
                    if (jjtc001) {
                        jjtree.closeNodeScope(jjtn001, 1);
                        jjtreeCloseNodeScope(jjtn001);
                    }
                }
                break;
            case INCR: // ++
                PreIncrementExpression();
                break;
            case DECR:  // --
                PreDecrementExpression();
                break;
            case BOOL:
            case FALSE:
            case NULL:
            case TRUE:
            case IDENTIFIER:
            case STR:
            case NUMBER:
            case LPAREN:
            case BANG:
            case TILDE:
                UnaryExpressionNotPlusMinus();
                break;
            default:
                jj_la1[47] = jj_gen;
                jj_consume_token(default_1);
                throw new ParseException();
        }
    }


    // ++
    final public void PreIncrementExpression() throws ParseException {
        Token t = null;
        t = jj_consume_token(INCR);
        PrimaryExpression();
        TSHUnaryExpression jjtn001 = new TSHUnaryExpression(T_UnaryExpression);
        boolean jjtc001 = true;
        jjtree.openNodeScope(jjtn001);
        jjtreeOpenNodeScope(jjtn001);
        try {
            jjtree.closeNodeScope(jjtn001, 1);
            jjtc001 = false;
            jjtreeCloseNodeScope(jjtn001);
            jjtn001.kind = t.kind;
        } finally {
            if (jjtc001) {
                jjtree.closeNodeScope(jjtn001, 1);
                jjtreeCloseNodeScope(jjtn001);
            }
        }
    }


    final public void PreDecrementExpression() throws ParseException {
        Token t = null;
        t = jj_consume_token(DECR);
        PrimaryExpression();
        TSHUnaryExpression jjtn001 = new TSHUnaryExpression(T_UnaryExpression);
        boolean jjtc001 = true;
        jjtree.openNodeScope(jjtn001);
        jjtreeOpenNodeScope(jjtn001);
        try {
            jjtree.closeNodeScope(jjtn001, 1);
            jjtc001 = false;
            jjtreeCloseNodeScope(jjtn001);
            jjtn001.kind = t.kind;
        } finally {
            if (jjtc001) {
                jjtree.closeNodeScope(jjtn001, 1);
                jjtreeCloseNodeScope(jjtn001);
            }
        }
    }

    // ~  !
    final public void UnaryExpressionNotPlusMinus() throws ParseException {
        Token t = null;
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case BANG:
            case TILDE:
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case TILDE:
                        t = jj_consume_token(TILDE);                // ~
                        break;
                    case BANG:
                        t = jj_consume_token(BANG);     // !
                        break;
                    default:
                        jj_la1[48] = jj_gen;
                        jj_consume_token(default_1);
                        throw new ParseException();
                }
                UnaryExpression();
                TSHUnaryExpression jjtn001 = new TSHUnaryExpression(T_UnaryExpression);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
                jjtreeOpenNodeScope(jjtn001);
                try {
                    jjtree.closeNodeScope(jjtn001, 1);
                    jjtc001 = false;
                    jjtreeCloseNodeScope(jjtn001);
                    jjtn001.kind = t.kind;
                } finally {
                    if (jjtc001) {
                        jjtree.closeNodeScope(jjtn001, 1);
                        jjtreeCloseNodeScope(jjtn001);
                    }
                }
                break;
            default:
                jj_la1[49] = jj_gen;
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case FALSE:
                    case NULL:
                    case TRUE:
                    case STR:
                    case IDENTIFIER:
                    case NUMBER:
                    case LPAREN:
                        PostfixExpression();
                        break;
                    default:
                        jj_la1[50] = jj_gen;
                        jj_consume_token(default_1);
                        throw new ParseException();
                }
        }
    }

    // ++ --
    final public void PostfixExpression() throws ParseException {
        Token t = null;
        if (jj_2_12(2147483647)) {
            PrimaryExpression();
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case INCR:
                    t = jj_consume_token(INCR);
                    break;
                case DECR:
                    t = jj_consume_token(DECR);
                    break;
                default:
                    jj_la1[53] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            TSHUnaryExpression jjtn001 = new TSHUnaryExpression(T_UnaryExpression);
            boolean jjtc001 = true;
            jjtree.openNodeScope(jjtn001);
            jjtreeOpenNodeScope(jjtn001);
            try {
                jjtree.closeNodeScope(jjtn001, 1);
                jjtc001 = false;
                jjtreeCloseNodeScope(jjtn001);
                jjtn001.kind = t.kind;
                jjtn001.postfix = true;
            } finally {
                if (jjtc001) {
                    jjtree.closeNodeScope(jjtn001, 1);
                    jjtreeCloseNodeScope(jjtn001);
                }
            }
        } else {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case FALSE:
                case NULL:
                case TRUE:
                case STR:
                case NUMBER:
                case IDENTIFIER:
                case LPAREN:
                    PrimaryExpression();
                    break;
                default:
                    jj_la1[54] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
        }
    }

    // { [ .
    final public void PrimaryExpression() throws ParseException {
        TSHPrimaryExpression jjtn000 = new TSHPrimaryExpression(T_PrimaryExpression);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            PrimaryPrefix();
            label_17:
            while (true) {
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case LBRACE:
                    case LBRACKET:
                    case DOT:
                        ;
                        break;
                    default:
                        jj_la1[56] = jj_gen;
                        break label_17;
                }
                PrimarySuffix();
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

    final public void PrimaryPrefix() throws ParseException {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case FALSE:
            case NULL:
            case TRUE:
            case NUMBER:
            case STR:
                Literal();
                break;
            case LPAREN:
                jj_consume_token(LPAREN);
                Expression();
                jj_consume_token(RPAREN);
                break;
            default:
                jj_la1[57] = jj_gen;
                if (jj_2_14(2147483647)) {
                    MethodInvocation();
                } else {
                    switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                        case IDENTIFIER:
                            AmbiguousName();
                            break;
                        default:
                            jj_la1[58] = jj_gen;
                            jj_consume_token(default_1);
                            throw new ParseException();
                    }
                }
        }
    }

    final public void MethodInvocation() throws ParseException {
        TSHMethodInvocation jjtn000 = new TSHMethodInvocation(T_MethodInvocation);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            AmbiguousName();
            Arguments();
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


    final public void AmbiguousName() throws ParseException {
        TSHAmbiguousName jjtn000 = new TSHAmbiguousName(T_AmbiguousName);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t;
        StringBuffer s;
        try {
            t = jj_consume_token(IDENTIFIER);
            s = new StringBuffer(t.image);
            label_5:
            while (true) {
                if (jj_2_7(2)) {
                    ;
                } else {
                    break label_5;
                }
                jj_consume_token(DOT);
                t = jj_consume_token(IDENTIFIER);
                s.append("." + t.image);
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.text = s.toString();
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }
    }


    final public void Literal() throws ParseException {
        /*@bgen(jjtree) Literal */
        TSHLiteral jjtn000 = new TSHLiteral(T_Literal);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token x;
        boolean b;
        String literal;
        char ch;
        try {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NUMBER:
                    x = jj_consume_token(NUMBER);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    literal = x.image;
                    TBigDecimal tBigDecimal = new TBigDecimal();
                    tBigDecimal.setValue(NumberUtil.objToBigDecimalDefault(literal, BigDecimal.ZERO));
                    tBigDecimal.setPrecision(NumberUtil.getPrecision(literal));
                    try {
                        jjtn000.value = tBigDecimal;
                    } catch (NumberFormatException e) {
                        {
                            if (true) throw createParseException(
                                    "Error or number too big for integer type: " + literal);
                        }
                    }
                    break;
                case STR:
                    x = jj_consume_token(STR);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    try {
                        jjtn000.stringSetup(x.image.substring(1, x.image.length() - 1));
                    } catch (Exception e) {
                        {
                            if (true) throw createParseException("Error parsing string: " + x.image);
                        }
                    }
                    break;
                case FALSE:
                case TRUE:
                    b = BooleanLiteral();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.value = b ? Primitive.TRUE : Primitive.FALSE;
                    break;
                case NULL:
                    NullLiteral();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.value = Primitive.NULL;
                    break;
                default:
                    jj_la1[61] = jj_gen;
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


    final public boolean BooleanLiteral() throws ParseException {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case TRUE:
                jj_consume_token(TRUE);
            {
                if (true) return true;
            }
            break;
            case FALSE:
                jj_consume_token(FALSE);
            {
                if (true) return false;
            }
            break;
            default:
                jj_la1[62] = jj_gen;
                jj_consume_token(default_1);
                throw new ParseException();
        }
        throw new Error("Missing return statement in function");
    }


    final public void NullLiteral() throws ParseException {
        jj_consume_token(NULL);
    }


    final public void Arguments() throws ParseException {
        /*@bgen(jjtree) Arguments */
        TSHArguments jjtn000 = new TSHArguments(T_Arguments);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(LPAREN);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case FALSE:
                case NULL:
                case TRUE:
                case IDENTIFIER:
                case NUMBER:
                case LPAREN:
                case BANG:
                case TILDE:
                case INCR:
                case DECR:
                case PLUS:
                case MINUS:
                    ArgumentList();
                    break;
                default:
                    jj_la1[63] = jj_gen;
                    ;
            }
            jj_consume_token(RPAREN);
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

    // leave these on the stack for Arguments() to handle
    final public void ArgumentList() throws ParseException {
        Expression();
        label_18:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case COMMA:
                    ;
                    break;
                default:
                    jj_la1[64] = jj_gen;
                    break label_18;
            }
            jj_consume_token(COMMA);
            Expression();
        }
    }


    // todo
    final public void PrimarySuffix() throws ParseException {
        TSHPrimarySuffix jjtn000 = new TSHPrimarySuffix(T_PrimarySuffix);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LBRACKET:
                    jj_consume_token(LBRACKET);
                    Expression();
                    jj_consume_token(RBRACKET);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.operation = TSHPrimarySuffix.INDEX;
                    break;
                case DOT:
                    jj_consume_token(DOT);
                    t = jj_consume_token(IDENTIFIER);
                    switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                        case LPAREN:
                            Arguments();
                            break;
                        default:
                            jj_la1[59] = jj_gen;
                            ;
                    }
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.operation = TSHPrimarySuffix.NAME;
                    jjtn000.field = t.image;
                    break;
                case LBRACE:
                    jj_consume_token(LBRACE);
                    Expression();
                    jj_consume_token(RBRACE);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.operation = TSHPrimarySuffix.PROPERTY;
                    break;
                default:
                    jj_la1[60] = jj_gen;
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

    final public boolean Line() throws ParseException {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case EOF:
                jj_consume_token(EOF);
                Interpreter.debug("End of File!");
            {
                if (true) return true;
            }
            break;
            default:
                jj_la1[0] = jj_gen;
                BlockStatement();
                if (true) {
                    return false;
                }
        }
        throw new Error("Missing return statement in function");
    }


    final private boolean isMethod() {
        jj_lastpos = jj_scanpos = token;
        try {
            return !notDef();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {

        }
    }


    final private boolean notDef() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(DEF)) {
            jj_scanpos = xsp;
            return true;
        }
        return false;
    }

    final private boolean jj_3_22() {
        if (jj_3R_40()) return true;
        return false;
    }


    final private boolean jj_3_40(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3R_40();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(28, xla);
        }
    }

    final private boolean jj_3R_40() {
        if (jj_scan_token(IDENTIFIER)) return true;         //  identifier
        if (jj_scan_token(COLON)) return true;              //  :
        return false;
    }


    final private boolean jj_2_29(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3R_28();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(28, xla);
        }
    }


    final private boolean jj_3R_45() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3_22()) {
            jj_scanpos = xsp;
            if (jj_3R_38()) {               // {}  静态代码块
                jj_scanpos = xsp;
                if (jj_3R_78()) {                   // 大概三目运算符 ? xxx: bbb ;
                    jj_scanpos = xsp;
                    if (jj_scan_token(SWITCH)) {                   // switch 表达式
                        jj_scanpos = xsp;
                        if (jj_scan_token(IF)) {                       // if 表达式
                            jj_scanpos = xsp;
                            if (jj_scan_token(WHILE)) {                   //  while 表达式
                                jj_scanpos = xsp;
                                if (jj_scan_token(DO)) {                   // do while()表达式
                                    jj_scanpos = xsp;
                                    if (jj_scan_token(FOR)) {                   // for (i in list )
                                        jj_scanpos = xsp;
                                        if (jj_scan_token(BREAK)) {                   // break; 或 break label_1 ;
                                            jj_scanpos = xsp;
                                            if (jj_scan_token(CONTINUE)) {                   // continue 或 continue label_1;
                                                jj_scanpos = xsp;
                                                if (jj_scan_token(RETURN)) {               // return 表达式;
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    final private boolean jj_3R_78() {
        if (jj_3R_39()) return true;
        if (jj_scan_token(NEXT_LINE)) return true; //
        return false;
    }


    final private boolean jj_3R_38() {
        Token xsp;
        if (jj_scan_token(LBRACE)) return true; // {
        while (true) {
            xsp = jj_scanpos;
            if (jj_3_23()) {
                jj_scanpos = xsp;
                break;
            }
        }
        if (jj_scan_token(RBRACE)) return true;         //  }
        return false;
    }

    final private boolean jj_3_23() {
        while (true) {
            Token t = jj_scan_token_next();
            if (StringUtil.isNotBlank(t.image)) {
                if (eq(t.kind, RBRACE)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    final private boolean jj_3R_28() {
        Token xsp;
        xsp = jj_scanpos;
        if (jjMethod()) {                       // method
            jj_scanpos = xsp;
            if (jj_3R_49()) {               // 定义一个list
                jj_scanpos = xsp;
                if (jj_3R_45()) {  // do while ,while ,for ,  {}等
                    jj_scanpos = xsp;
                }
            }
        }
        return false;
    }


    final private boolean jj_3R_49() {
        if (jj_3R_93()) return true;
        if (jj_scan_token(NEXT_LINE)) return true;      // ;
        return false;
    }


    final private boolean jj_3R_93() {
        if (jj_3R_176()) return true;      // 数组变量名称或 =
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_177()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    final private boolean jj_3R_177() {
        if (jj_scan_token(COMMA)) return true;
        if (jj_3R_176()) return true;
        return false;
    }


    final private boolean jj_3R_176() {
        if (jj_scan_token(IDENTIFIER)) return true;                 // 变量名称
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_180()) jj_scanpos = xsp;                  // =
        return false;
    }

    final private boolean jj_3R_180() {
        if (jj_scan_token(ASSIGN)) return true;
        if (jj_3R_31()) return true;
        return false;
    }


    final private boolean jj_3R_31() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_97()) {
            jj_scanpos = xsp;
            if (jj_3R_39()) return true;
        }
        return false;
    }

    final private boolean jj_3R_97() {
        if (jj_scan_token(LBRACE)) return true;         // {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_163()) jj_scanpos = xsp;                  //
        xsp = jj_scanpos;
        if (jj_scan_token(COMMA)) jj_scanpos = xsp;                // ,
        if (jj_scan_token(RBRACE)) return true;                     // }
        return false;
    }

    final private boolean jj_3R_39() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_107()) {
            jj_scanpos = xsp;
            if (jj_3R_108()) return true;
        }
        return false;
    }

    final private boolean jj_3R_107() {
        if (jj_3R_33()) return true;
        if (jj_3R_34()) return true;            // += <<< ...
        if (jj_3R_39()) return true;
        return false;
    }

    final private boolean jj_3R_33() {
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_104()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3R_104() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_131()) {                  //表示一个 list 类型
            jj_scanpos = xsp;
            if (jj_3R_132()) {
                jj_scanpos = xsp;
                if (jj_3R_133()) return true;
            }
        }
        return false;
    }

    final private boolean jj_3R_133() {
        if (jj_scan_token(LBRACE)) return true;
        if (jj_3R_39()) return true;
        if (jj_scan_token(RBRACE)) return true;
        return false;
    }


    final private boolean jj_3R_108() {
        if (jj_3R_135()) return true;
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_156()) jj_scanpos = xsp;                  // ? : xxx
        return false;
    }

    final private boolean jj_3R_156() {
        if (jj_scan_token(HOOK)) return true;
        if (jj_3R_39()) return true;
        if (jj_scan_token(COLON)) return true;
        if (jj_3R_108()) return true;
        return false;
    }

    final private boolean jj_3R_135() {
        if (jj_3R_148()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_159()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3R_148() {
        if (jj_3R_153()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_162()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3R_153() {
        if (jj_3R_158()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_165()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    final private boolean jj_3R_165() {
        Token xsp;
        if (jj_scan_token(OR)) {
            return true;
        }
        if (jj_3R_158()) return true;
        return false;
    }

    final private boolean jj_3R_159() {
        Token xsp;
        if (jj_scan_token(BOOL_OR)) {                                // ||
            return true;
        }
        if (jj_3R_148()) return true;                           // && and
        return false;
    }

    final private boolean jj_3R_162() {
        Token xsp;
        if (jj_scan_token(BOOL_AND)) {
            return true;
        }
        if (jj_3R_153()) return true;
        return false;
    }

    final private boolean jj_2_12(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_12();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(11, xla);
        }
    }


    final private boolean jj_3_12() {
        if (jj_3R_33()) return true;
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(INCR)) {
            jj_scanpos = xsp;
            if (jj_scan_token(DECR)) return true;
        }
        return false;
    }

    final private boolean jj_3R_158() {
        if (jj_3R_161()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_167()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3R_161() {
        if (jj_3R_164()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_169()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3R_164() {
        if (jj_3R_166()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_171()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3R_166() {
        if (jj_3R_168()) return true;
        return false;
    }

    final private boolean jj_3R_167() {
        if (jj_scan_token(XOR)) return true;
        if (jj_3R_161()) return true;
        return false;
    }


    final private boolean jj_3R_168() {
        if (jj_3R_170()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_182()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    final private boolean jj_3R_169() {
        Token xsp;
        if (jj_scan_token(AND)) {                                   // &
            return true;
        }
        if (jj_3R_164()) return true;
        return false;
    }


    final private boolean jj_3R_170() {
        if (jj_3R_178()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_192()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    final private boolean jj_3R_171() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(EQ)) {                // ==
            jj_scanpos = xsp;
            if (jj_scan_token(NE)) return true;                 // !=
        }
        if (jj_3R_166()) return true;
        return false;
    }


    final private boolean jj_3R_178() {
        if (jj_3R_181()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_200()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    final private boolean jj_3R_200() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(PLUS)) {           // +
            jj_scanpos = xsp;
            if (jj_scan_token(MINUS)) return true; // -
        }
        if (jj_3R_181()) return true;
        return false;
    }


    final private boolean jj_3R_181() {
        if (jj_3R_191()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_209()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    final private boolean jj_3R_209() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(STAR)) {           // *
            jj_scanpos = xsp;
            if (jj_scan_token(SLASH)) {           //      /
                jj_scanpos = xsp;
                if (jj_scan_token(MOD)) return true;                // %
            }
        }
        if (jj_3R_191()) return true;
        return false;
    }

    final private boolean jj_3R_182() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(LT)) {                    // <
            jj_scanpos = xsp;
            if (jj_scan_token(GT)) {            // >
                jj_scanpos = xsp;
                if (jj_scan_token(LE)) {         // <=
                    jj_scanpos = xsp;
                    if (jj_scan_token(GE)) {  // >=
                        return true;
                    }
                }
            }
        }
        if (jj_3R_170()) return true;
        return false;
    }

    final private boolean jj_3R_191() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_196()) {           // + -
            jj_scanpos = xsp;
            if (jj_3R_206()) {      // ++
                jj_scanpos = xsp;
                if (jj_3R_207()) {      // --
                    jj_scanpos = xsp;
                    if (jj_3R_208()) return true;
                }
            }
        }
        return false;
    }


    final private boolean jj_3R_192() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(LSHIFT)) {                           // <<
            jj_scanpos = xsp;
            if (jj_scan_token(RSIGNEDSHIFT)) {               // >>
                jj_scanpos = xsp;
                if (jj_scan_token(RUNSIGNEDSHIFT)) {               // >>>
                    return true;        // @right_unsigned_shift
                }
            }
        }
        if (jj_3R_178()) return true;
        return false;
    }


    final private boolean jj_3R_206() {
        if (jj_scan_token(INCR)) return true;
        if (jj_3R_33()) return true;
        return false;
    }


    final private boolean jj_3R_207() {
        if (jj_scan_token(DECR)) return true;       //  --
        if (jj_3R_33()) return true;        //
        return false;
    }

    final private boolean jj_3R_208() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_211()) {                              // ! ~
            jj_scanpos = xsp;
            if (jj_3R_215()) return true;
        }
        return false;
    }


    final private boolean jj_3R_215() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_218()) {
            jj_scanpos = xsp;
            if (jj_3R_33()) return true;
        }
        return false;
    }


    final private boolean jj_3R_218() {
        if (jj_3R_33()) return true;
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(INCR)) {
            jj_scanpos = xsp;
            if (jj_scan_token(DECR)) return true;
        }
        return false;
    }


    final private boolean jj_3R_211() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(TILDE)) {            // ~ !
            jj_scanpos = xsp;
            if (jj_scan_token(BANG)) return true;
        }
        if (jj_3R_191()) return true;
        return false;
    }

    final private boolean jj_3R_196() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(PLUS)) {   //  +
            jj_scanpos = xsp;
            if (jj_scan_token(MINUS)) return true;    // -
        }
        if (jj_3R_191()) return true;
        return false;
    }

    final private boolean jj_3R_132() {
        if (jj_scan_token(DOT)) return true;
        if (jj_scan_token(IDENTIFIER)) return true;     // .xxx
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_69()) jj_scanpos = xsp;
        return false;
    }

    final private boolean jj_3R_69() {
        if (jj_scan_token(LPAREN)) return true;
        if (jj_scan_token_util(LPAREN, RPAREN)) return true;
        return false;
    }

    final private boolean jj_3R_134() {
        if (jj_3R_39()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_147()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3R_147() {
        if (jj_scan_token(COMMA)) return true;
        if (jj_3R_39()) return true;
        return false;
    }


    final private boolean jj_3R_131() {
        if (jj_scan_token(LBRACKET)) return true;           // [
        if (jj_3R_39()) return true;
        if (jj_scan_token(RBRACKET)) return true;           // ]
        return false;
    }

    final private boolean jj_3R_34() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(ASSIGN)) {        //  =
            jj_scanpos = xsp;
            if (jj_scan_token(STARASSIGN)) {       //  *=
                jj_scanpos = xsp;
                if (jj_scan_token(SLASHASSIGN)) {       // /=
                    jj_scanpos = xsp;
                    if (jj_scan_token(MODASSIGN)) {       //  %=
                        jj_scanpos = xsp;
                        if (jj_scan_token(PLUSASSIGN)) {       //  +=
                            jj_scanpos = xsp;
                            if (jj_scan_token(MINUSASSIGN)) {       //   -=
                                jj_scanpos = xsp;
                                if (jj_scan_token(ANDASSIGN)) {       //  &=
                                    jj_scanpos = xsp;
                                    if (jj_scan_token(XORASSIGN)) {       //  ^=
                                        jj_scanpos = xsp;
                                        if (jj_scan_token(ORASSIGN)) {       //  |=
                                            jj_scanpos = xsp;
                                            if (jj_scan_token(LSHIFTASSIGN)) {           //  <<=
                                                jj_scanpos = xsp;
                                                if (jj_scan_token(RSIGNEDSHIFTASSIGN)) {       //  >>=
                                                    jj_scanpos = xsp;
                                                    if (jj_scan_token(RUNSIGNEDSHIFTASSIGN)) {       //  >>>=
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    final private boolean jj_2_8(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_8();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(7, xla);
        }
    }

    final private boolean jj_3_8() {
        if (jj_scan_token(IDENTIFIER)) return true;
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_34()) {
            jj_scanpos = xsp;
            while (true) {
                xsp = jj_scanpos;
                if (jj_scan_token(COMMA)) {
                    jj_scanpos = xsp;
                    break;
                }
                xsp = jj_scanpos;
                if (jj_scan_token(IDENTIFIER)) {
                    jj_scanpos = xsp;
                    break;
                }
                xsp = jj_scanpos;
                if (jj_scan_token(NEXT_LINE)) {
                    jj_scanpos = xsp;
                } else {
                    return true;
                }
            }
            if (jj_3R_34()) return true;
        }
        return false;
    }


    final private boolean jj_3R_163() {
        if (jj_3R_31()) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3_4()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    final private boolean jj_2_14(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3R_37();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(13, xla);
        }
    }

    final private boolean jj_3R_37() {
        if (jj_3R_29()) return true;
        if (jj_3R_69()) return true;
        return false;
    }

    final private boolean jj_3R_29() {
        if (jj_scan_token(IDENTIFIER)) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3_7()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }


    final private boolean jj_3_7() {
        if (jj_scan_token(DOT)) return true;
        if (jj_scan_token(IDENTIFIER)) return true;
        return false;
    }


    final private boolean jj_3_4() {
        if (jj_scan_token(COMMA)) return true;
        if (jj_3R_31()) return true;
        return false;
    }

    final private boolean jj_2_4(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_4();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(3, xla);
        }
    }

    final private boolean jjMethod() {
        if (jj_scan_token(DEF)) return true;
        return false;
    }

    final private boolean jj_2_23(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_23();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(22, xla);
        }
    }




    // 变量的定义可能是 a = 或 a,b,c =
    final private boolean jj_3_28() {
        if (jj_scan_token(IDENTIFIER)) return true;         //
        return false;
    }


    final private boolean jj_2_7(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_7();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(6, xla);
        }
    }

    final private boolean isTuple(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_9();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(8, xla);
        }
    }


    final private boolean jj_3_9() {
        Token xsp = jj_scanpos;
        if (jj_scan_token(IDENTIFIER)) {
            if (jj_scan_token(STR)) {
                jj_scanpos = xsp;
                if (jj_scan_token(NUMBER)) {
                    jj_scanpos = xsp;
                    if (jj_scan_token(TRUE)) {
                        jj_scanpos = xsp;
                        if (jj_scan_token(FALSE)) {
                            jj_scanpos = xsp;
                            if (jj_3_9()) {        //如果不是 tuple 调用
                                jj_scanpos = xsp;
                                if (jj_3R_37()) {      //是方法调用
                                    jj_scanpos = xsp;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (jj_scan_token(COMMA)) return true;
        return false;
    }


    final private void jj_save(int index, int xla) {
        Parser.JJCalls p = jj_2_rtns[index];
        while (p.gen > jj_gen) {
            if (p.next == null) {
                p = p.next = new Parser.JJCalls();
                break;
            }
            p = p.next;
        }
        p.gen = jj_gen + xla - jj_la;
        p.first = token;
        p.arg = xla;
    }

    ParseException createParseException(String message) {
        Token errortok = token;
        int line = errortok.beginLine, column = errortok.beginColumn;
        return new ParseException("Parse error at line " + line
                + ", column " + column + " : " + message);
    }


}
