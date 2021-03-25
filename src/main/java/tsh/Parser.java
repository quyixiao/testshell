package tsh;

import tsh.constant.ParserConstants;
import tsh.constant.ParserTreeConstants;
import tsh.entity.TBigDecimal;
import tsh.exception.ParseException;
import tsh.expression.*;
import tsh.util.*;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser extends Utils implements ParserConstants, ParserTreeConstants {


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
    public ParserTokenManager token_source;
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
        token_source = new ParserTokenManager(jj_input_stream);
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
        String message = token.beginLine + "行," + token.beginColumn + "列,代码书写有误";
        throw new ParseException(message);
    }


    final private Token jj_consume_token_next() throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = default_1;
        return token;
    }


    final private void jj_consume_token_next_line() throws ParseException {
        lable_:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NEXT_LINE:     //一直消费，直到不为 \n 时，停止消息
                    jj_consume_token(NEXT_LINE);
                    break;
                default:
                    break lable_;
            }
        }
    }


    final private Token jj_consume_token_util(String kind) throws ParseException {
        Token oldToken;
        while (true) {
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


    final public boolean BlockStatement() throws ParseException {
        //消费掉所有的的行符
        jj_consume_token_next_line();
        if (jj_2_33(3, EOF)) return false;      //如果文件己经结束，则终止执行树的创建
        if (isMethod()) {           //如果是方法声明，我们定义以 def 开头的都是方法声明
            MethodDeclaration();
        } else if (jj_2_31(2147483647)) {   //变量声明，只要是 变量=的情况都是变量声明，也可以是 变量,变量...变量 = 的情况
            VariableDeclarator();
        } else if (jj_2_33(3, AT)) {        //当解析到@时，表明是一个注解
            AnnotationMethodInvocationDeclarator();
        } else if (jj_2_33(3, IMPORT)) {    //当解析到import时，表示是引入一个变量或源码
            ImportDeclaration();
        } else if (jj_2_33(3, GLOBAL)) {    // global 定义全局变量
            GlobalDeclaration();
        } else {
            Statement();                        //普通表达式的创建
        }
        return true;
    }


    private void MethodDeclaration() throws ParseException {
        //如果是方法的声明，创建TSHMethodDeclaration节点
        TSHMethodDeclaration jjtn000 = new TSHMethodDeclaration(T_MethodDeclaration);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            //消费掉 def
            jj_consume_token(DEF);
            //消费掉方法名
            t = jj_consume_token(IDENTIFIER);
            jjtn000.methodName = t.image;       //设置方法名
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

    // 块内解析
    final public void Block() throws ParseException {
        TSHBlock jjtn000 = new TSHBlock(T_Block);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token s = null;
        try {
            jj_consume_token(LBRACE); //消费掉 {
            label_22:
            while (true) {
                // jj_2_23()方法的判断很简单，如果遇到 } ,则终止循环，可能有人会犯诋估了， 如果 @1{ ... @2{ ... @3} ... @4}
                //这种情况，上面消费掉@1的{ ,如果消费掉@3 } 怎么办呢？
                // 大家不用担心，因为@1所对应的 { ，只可能消费掉@4 对应的 }
                // 因为在解析@2时 { ，会成对的将@3 } 消费掉，因此，@1所对应的{,只能是@4对应的 }，因此在消费掉一个{后，只需要等到
                // 下一个}出现，即可退出 {} 内节点的解析
                if (jj_2_23(1)) {
                    ;
                } else {
                    break label_22;
                }
                //继续块的解析
                BlockStatement();
            }
            jj_consume_token_util(RBRACE);      // } ，为什么会直到消费掉 }呢？因为在 }之前可能会有多个回车换行
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

    final public void LambdaBlock() throws ParseException {
        TSHLambdaBlock jjtn000 = new TSHLambdaBlock(T_LambdaBlock);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            BlockStatement();
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


    //方法参数出现的形式 (a,b,c) 或 (a = 1 ,b = 2 ,c ) 或 (a = 1 ,* args ,**kwargs )或(a = 1 , b = lambda x ,y : x + y )等
    final public void FormalParameters() throws ParseException {
        //方法参数列表对象
        TSHFormalParameters jjtn000 = new TSHFormalParameters(T_FormalParameters);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(LPAREN);   //消费方法名后的(
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case STAR:
                case SSTAR:
                case IDENTIFIER:        //如果当前参数是*,**,或 字符常量,创建子节点FormalParameter
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
                        jj_consume_token(COMMA);    //消费掉逗号
                        FormalParameter();          //再次创建子节点
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
            if (jj_2_33(3, STAR)) {             // def fn(x, y,*args)   一个* 的情况
                jj_consume_token(STAR);
                jjtn000.kind = STAR;
            } else if (jj_2_33(3, SSTAR)) {        // def fn(x, y,**kwargs) 2 个 * 的情况
                jj_consume_token(SSTAR);
                jjtn000.kind = SSTAR;
            }
            t = jj_consume_token(IDENTIFIER);       //消费得到方法参数变量名
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.name = t.image;
            if (jj_2_33(3, ASSIGN)) { //如果没有扫描到=号，则变量的定义肯定是(a ,b)
                jj_consume_token(ASSIGN); //如果扫描到=号，则解析等号后面的表达式，如(a = lambda x ,y : x + y )
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


    final public void LambdaFormalParameters() throws ParseException {
        TSHFormalParameters jjtn000 = new TSHFormalParameters(T_FormalParameters);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case STAR:
                case SSTAR:
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


    final public void Statement() throws ParseException {
        jj_consume_token_next_line();           //消费掉所有的行
        if (jj_3_40(3)) {                   //如果是 三目运算符  如 a > b ? a : b
            LabeledStatement();                 // break lable_;的情况
        } else {
            doStatement();      // 正在开始解析表达式
        }
    }

    final public void doStatement() throws ParseException {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case LBRACE:                //如果是 { ,表明是一个执行块 ，如 { c= a + b \n print(c ,a ,b )}等
                Block();
                break;
            case FALSE:
            case NULL:
            case TRUE:
            case STR:
            case IDENTIFIER:
            case LPAREN:
            case LBRACKET:
            case BANG:
            case TILDE:
            case INCR:
            case DECR:
            case PLUS:
            case MINUS:
            case MAP:
                StatementExpression();                      // 如 map1 = {'username':'zhangsan' ,'age':18} ...
                break;
            case SWITCH:
                SwitchStatement();                          // swith(...) { case condition1: ... break \n case condition2 : ... break label_1 \n default : ... break  }
                break;
            case IF:
                IfStatement();                              //if(condition) {...}
                break;
            case WHILE:
                WhileStatement();                           //while(condition) { ...}
                break;
            case DO:
                DoStatement();                      // do {...} while(condition)
                break;
            default:
                jj_la1[69] = jj_gen;
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case FOR:
                        ForAfterStatement();                // for (i in range( 5 )) { ...}
                        break;
                    case BREAK:
                        BreakStatement();                   // break 或break label_1
                        break;
                    case CONTINUE:                          // continue 或continue label_1
                        ContinueStatement();
                        break;
                    case RETURN:                            // return a 或return a ,b ,c
                        ReturnStatement();
                        break;
                    case THROW:
                        ThrowStatement();                   // throw new Exception(...)
                        break;
                    case TRY:
                        TryStatement();                     //try {...} catch (..) { ...}
                        break;
                    case EXPORT:                            // export a ,b ,c  或 export a ,a + b
                        ExportStatement();
                        break;
                    case EOF:
                        break;
                    default:
                        jj_la1[70] = jj_gen;
                        jj_consume_token(default_1);
                        throw new ParseException();
                }
        }
    }

    //while 表达式解析
    final public void WhileStatement() throws ParseException {
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

    // do { ...} while( condition ) ，do while 表达式解析
    final public void DoStatement() throws ParseException {
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

    // 三目运算符解析 a > b ? a > c ? a : c : b > c  ? b : c ，计算 a b c 的最大值
    final public void LabeledStatement() throws ParseException {
        TSHLabeledStatement jjtn000 = new TSHLabeledStatement(T_LabeledStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            Token t = jj_consume_token(IDENTIFIER);
            jj_consume_token(COLON);        //消费掉 :
            jj_consume_token_next_line();
            // 可能会出现 a > b ? sum(a - b ) : sub(b - a ) ，计算绝对值，因此Statement()解析的不一定是一个变量名，
            //也可能是一个表达式，因此调用Statement
            Statement();
            jjtn000.label = t.image;
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

    final public void StatementExpression() throws ParseException {
        //表达式解析
        Expression();
    }


    // swith ( ... ) { case condition1 :  ....; condition2 : ... break label_1 } 的情况
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
                jj_consume_token_next_line();
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
                int i = 0;
                label_24:
                while (true && i != 1) {                // case condition1 : condition2 : ....  break 的情况
                    i = jj_2_29(1);
                    if (i == 2 || i == 1) {             //如果是  i = 1 ,则下次退出
                        ;
                    } else {                            //如果遇到 case 或 } 情况，直接终止到label_24
                        break label_24;
                    }
                    BlockStatement();
                }
            }
            jj_consume_token_util(RBRACE);
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

    // switch 表达式解析,switch (...) { case condition:  ... ; default : ... ;  }
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

    // if 表达式 if( condition ) { ... }
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
                case ELSE:          //如果有 else  ,如 if(condition) { ... }else {...} 的情况
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

    // for 表达式在之前的情况，为[]集体赋值 ，如 [x + 1 for (x in range(10))] 得到 [1,2,3,4,5,6,7,8,9,10]
    final public void ForBeforeStatement() throws ParseException {
        TSHBeforeForStatement jjtn000 = new TSHBeforeForStatement(T_BeforeForStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            doStatement();
            jj_consume_token(FOR);                  // for
            jj_consume_token(LPAREN);               // (
            t = jj_consume_token(IDENTIFIER);       // i
            Token t1 = jj_consume_token_next();
            if (eq(t1.kind, COMMA)) {              // for(i,item in range(1,5)){} 的情况
                jjtn000.kOrI = t.image;
                Token t2 = jj_consume_token(IDENTIFIER);       // i
                jjtn000.varName = t2.image;
                jj_consume_token(IN);      // in
            } else {                                        //如果没有消费到, 的话，肯定消费了 in
                jjtn000.varName = t.image;
            }
            Expression();                           // range(1,10)
            jj_consume_token(RPAREN);               // )
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

    // for(i in range(5)) { ... } ，for表达式解析
    final public void ForAfterStatement() throws ParseException {
        TSHForStatement jjtn000 = new TSHForStatement(T_ForStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            jj_consume_token(FOR);                  // for
            jj_consume_token(LPAREN);               // (
            t = jj_consume_token(IDENTIFIER);       // i
            Token t1 = jj_consume_token_next();
            if (eq(t1.kind, COMMA)) {              // for(i,item in range(1,5)){} 的情况
                jjtn000.kOrI = t.image;
                Token t2 = jj_consume_token(IDENTIFIER);       // i
                jjtn000.varName = t2.image;
                jj_consume_token(IN);      // in
            } else {                                        //如果没有消费到, 的话，肯定消费了 in
                jjtn000.varName = t.image;
            }
            Expression();                           // range(1,10)
            jj_consume_token(RPAREN);               // )
            Statement();                            // {}
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


    // break  或 break label_1 两种情况
    final public void BreakStatement() throws ParseException {
        TSHReturnStatement jjtn000 = new TSHReturnStatement(T_ReturnStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(BREAK);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case IDENTIFIER:        // break label_1 的情况
                    Token t = jj_consume_token(IDENTIFIER);
                    jjtn000.label = t.image;
                    break;
                default:
                    jj_la1[83] = jj_gen;
                    ;
            }
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

    // continue 块
    final public void ContinueStatement() throws ParseException {       // continue 和 continue label_1;
        TSHReturnStatement jjtn000 = new TSHReturnStatement(T_ReturnStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(CONTINUE);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case IDENTIFIER:        // continue label_1 的解析
                    Token t = jj_consume_token(IDENTIFIER);
                    jjtn000.label = t.image;
                    break;
                default:
                    jj_la1[84] = jj_gen;
                    ;
            }
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

    // return 表达式
    final public void ReturnStatement() throws ParseException {
        TSHReturnStatement jjtn000 = new TSHReturnStatement(T_ReturnStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(RETURN);       // return  sum(a + b )
            Expression();
            while (jj_2_33(3, COMMA)) { //下面是 return sum(a + b ) ,a ,b  返回一个元组
                jj_consume_token(COMMA);
                Expression();
            }
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

    // throw new Exception()表达式解析
    final public void ThrowStatement() throws ParseException {
        TSHThrowStatement jjtn000 = new TSHThrowStatement(T_ThrowStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(THROW);     //消费掉 throw
            Expression();           // 会被AllocationExpression方法解析到，new Exception(...)
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

    // try 表达式 ,java 中的语法 try { \n ... \n } catch(e){ \n e.printStackTrace() \n}finally{\n .... \n }
    final public void TryStatement() throws ParseException {
        TSHTryStatement jjtn000 = new TSHTryStatement(T_TryStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        boolean closed = false;
        try {
            jj_consume_token(TRY);
            Block(); // {} 块表达式解析
            label_27:
            while (true) {
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case CATCH:
                        ;
                        break;
                    default:
                        jj_la1[86] = jj_gen;
                        break label_27;
                }
                jj_consume_token(CATCH);
                jj_consume_token(LPAREN);
                FormalParameter();          // 解析 catch( e ) 的方法参数
                jj_consume_token(RPAREN);
                Block();        // 再解析 catch 后的{}表达式
                closed = true;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case FINALLY:
                    jj_consume_token(FINALLY);
                    Block();
                    closed = true;
                    break;
                default:
                    jj_la1[87] = jj_gen;
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            if (!closed) {
                if (true) throw new ParseException();
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

    // export 表达式解析
    final public void ExportStatement() throws ParseException {
        TSHExportStatement jjtn000 = new TSHExportStatement(T_ExportStatement);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            //export sum(a,b) , (lambda x ,y : x - y )(5,4) 这种写法
            //export a ,b 都可以，因此第一个参数是 export，每个表达式都是,隔开
            jj_consume_token(EXPORT);
            Expression();
            while (jj_2_33(3, COMMA)) {
                jj_consume_token(COMMA);
                Expression();
            }
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

    //变量声明
    final public void VariableDeclarator() throws ParseException {
        TSHVariableDeclarator jjtn000 = new TSHVariableDeclarator(T_VariableDeclarator);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t;
        try {
            List<String> list = new ArrayList<>();
            t = jj_consume_token(IDENTIFIER); //变量声明的第一个参数肯定是identifier
            list.add(t.image);
            if (!jj_2_33(3, ASSIGN)) {   //如果变量后面第一个参数不是=号，申明了多个变量名 a ,b .... = []
                do {
                    jj_consume_token(COMMA);
                    t = jj_consume_token(IDENTIFIER);
                    list.add(t.image);
                } while (!jj_2_33(3, ASSIGN));      //直到出现等号结束变量
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case ASSIGN:
                    jj_consume_token(ASSIGN);
                    VariableInitializer();      //变量初始化
                    break;
                default:
                    jj_la1[12] = jj_gen;
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.names = list.toArray(new String[list.size()]);
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

    //变量初始化
    final public void VariableInitializer() throws ParseException {
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case LBRACKET:                  // 如果 [开头，表示的是list 初始化
                ListInitializer();
                break;
            case LBRACE:                    //如果{开头，表示map 初始化
                MapInitializer();
                break;
            case LPAREN:
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
                if (jj_3_42(10)) {     // 可能是 [x for ( i in range(5))] ，这种情况
                    ForBeforeStatement();
                } else {
                    Expression();           //普通表达式的解析
                }
                break;
            default:
                jj_la1[13] = jj_gen;
                jj_consume_token(default_1);
                throw new ParseException();
        }
    }

    //下面这种情况，注解情况的处理 ，如@logger \n def method( a ){\n print(a) \n} 这种情况处理
    final public void AnnotationMethodInvocationDeclarator() throws ParseException {
        TSHAnnotationMethodDeclaration jjtn000 = new TSHAnnotationMethodDeclaration(T_AnnotationMethodDeclaration);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(AT);
            MethodInvocation();              //方法调用解析
            BlockStatement();               // 方法申明解析
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

    // import 开头的变量声明， import 有两种情况， import a ,b 这种还有 import common.tsh 引入公共方法
    final public void ImportDeclaration() throws ParseException {
        TSHImportDeclaration jjtn000 = new TSHImportDeclaration(T_ImportDeclaration);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        StringBuilder sb = new StringBuilder();
        try {
            jj_consume_token(IMPORT);       // import 以,隔开
            while (true) {
                if (jj_2_33(3, COMMA)) {
                    jj_consume_token(COMMA);
                }
                t = jj_consume_token(IDENTIFIER);        //消费变量名
                StringBuffer s = new StringBuffer(t.image);
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
                sb.append(s.toString()).append(",");
                if (jj_2_33(3, NEXT_LINE)) {
                    break;
                }
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.text = sb.toString();
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


    //变量全局变量 global a = 1 ，在所有的后续脚本调用中都可以修改，引用此变量
    final public void GlobalDeclaration() throws ParseException {
        TSHGlobalStatement jjtn000 = new TSHGlobalStatement(T_GlobalDeclaration);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(GLOBAL);
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

    //表达式解析
    final public void Expression() throws ParseException {
        //等于表达式 ，如 a,b,c.... = .... 的情况
        //也可能是 a += b ,a -=b ， a >>>=b ,a <<=b 等等
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
                case NEW:
                case PLUS:
                case MINUS:
                    ConditionalExpression();
                    break;
                case LBRACKET:
                    ListInitializer();
                    break;
                case LBRACE:
                    MapInitializer();
                    break;
                case STAR:
                case SSTAR:
                    StarArgument();                 // * ,** 表达式处理
                    break;
                case LAMBDA:
                    LambdaDeclaration();
                    break;
                case MAP:
                    MapInvocation();
                    break;
                default:
                    jj_la1[23] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
        }
    }

    // list 类型初始化
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

    // * 类型参数
    final public void StarArgument() throws ParseException {
        TSHStarArgument jjtn000 = new TSHStarArgument(T_StarArgument);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            Token t1 = jj_consume_token_next();
            jjtn000.kind = t1.kind;
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

    // lambda表达式声明
    private void LambdaDeclaration() throws ParseException {
        TSHLambdaDeclaration jjtn000 = new TSHLambdaDeclaration(T_LambdaDeclaration);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(LAMBDA);
            jjtn000.methodName = TOrderUtil.getMethodName();
            LambdaFormalParameters();
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case COLON:
                    jj_consume_token(COLON);
                    if (jj_2_33(3, EOF)) {
                        Block();
                    } else {
                        LambdaBlock();
                    }
                    break;
                default:
                    jj_la1[8] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }

            if (jj_2_33(3, RPAREN)) {
                Token t2 = jj_consume_token(RPAREN);
                if (jj_2_33(3, LPAREN)) {
                    Token t = LambdaMethodInvocation(jjtn000.methodName);
                    token = ParserTokenManager.jjTempFillToken();
                    token.next = t;
                } else {
                    token = ParserTokenManager.jjTempFillToken();
                    token.next = t2;
                }
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


    // map 调用
    private void MapInvocation() throws ParseException {
        TSHMapInvocation jjtn000 = new TSHMapInvocation(T_MapInvocation);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jj_consume_token(MAP);
            jj_consume_token(LPAREN);
            Expression();
            label_33:
            while (true) {
                if (jj_2_33(3, COMMA)) {
                    jj_consume_token(COMMA);
                    Expression();
                } else {
                    break label_33;
                }
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

    // 等号表达式
    final public void Assignment() throws ParseException {
        TSHAssignment jjtn000 = new TSHAssignment(T_Assignment);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        String op;
        try {
            PrimaryExpression();            //解析主表达式
            op = AssignmentOperator();      //解析操作符 = ，+= ，-= ，*= ，/= ,%= ,>>>= ,== 等等
            jjtn000.operator = op;          //TSHAssignment保存当前表达式的操作符类型
            Expression();                   //解析右节点表达式  如 sum(1 ,2 ) ， a > b ? a : b 情况太多
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

    // = 操作类型 = ， *= ，/= ,%= ,+= ,-= &= ,^= ,<<= ,>>= ,>>>=
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
            case HOOK:  //如果当前是？,那么一定是三目运算符 条件表达式 ? 表达式 : 表达式
                jj_consume_token(HOOK);
                Expression();            //表达式节点解析
                jj_consume_token(COLON);
                TSHTernaryExpression jjtn001 = new TSHTernaryExpression(T_TernaryExpression);
                boolean jjtc001 = true;
                jjtree.openNodeScope(jjtn001);
                jjtreeOpenNodeScope(jjtn001);
                try {
                    ConditionalExpression();        //条件表达式
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


    // || 表达式解析
    final public void ConditionalOrExpression() throws ParseException {
        Token t = null;
        ConditionalAndExpression();
        label_7:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case BOOL_OR:       //   ||
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
            //三元表达式
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

    // && 表达式解析
    final public void ConditionalAndExpression() throws ParseException {
        Token t = null;
        InclusiveOrExpression();
        label_8:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case BOOL_AND:          // &&
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

    // |
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

    // ^ 取反表达式解析
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

    // & 表达式解析
    final public void AndExpression() throws ParseException {
        Token t = null;
        EqualityExpression();
        label_11:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case AND:           // &
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
                case EQ:    // =
                case NE:    // !=
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
                case GT:    // >
                case LT:    // <
                case LE:    // <=
                case GE:    // >=
                    ;
                    break;
                default:
                    jj_la1[38] = jj_gen;
                    break label_13;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LT:    // <
                    t = jj_consume_token(LT);
                    break;
                case GT:    // >
                    t = jj_consume_token(GT);
                    break;
                case LE:    // <=
                    t = jj_consume_token(LE);
                    break;
                case GE:    // >=
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
    // 位移表达式 << >> >>>
    final public void ShiftExpression() throws ParseException {
        Token t = null;
        AdditiveExpression();
        label_14:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LSHIFT:            // <<
                case RSIGNEDSHIFT:      // >>
                case RUNSIGNEDSHIFT:    // >>>
                    ;
                    break;
                default:
                    jj_la1[40] = jj_gen;
                    break label_14;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case LSHIFT:            //<<
                    t = jj_consume_token(LSHIFT);
                    break;
                case RSIGNEDSHIFT:      //>>
                    t = jj_consume_token(RSIGNEDSHIFT);
                    break;
                case RUNSIGNEDSHIFT:    //>>>
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


    // + -
    final public void AdditiveExpression() throws ParseException {
        Token t = null;
        MultiplicativeExpression();
        label_15:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case PLUS:          // +
                case MINUS:         // -
                    ;
                    break;
                default:
                    jj_la1[42] = jj_gen;
                    break label_15;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case PLUS:          // +
                    t = jj_consume_token(PLUS);
                    break;
                case MINUS:         // -
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


    // / % 处理
    final public void MultiplicativeExpression() throws ParseException {
        Token t = null;
        UnaryExpression();
        label_16:
        while (true) {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case STAR:              // *
                case SLASH:             // /
                case MOD:               // %
                    ;
                    break;
                default:
                    jj_la1[44] = jj_gen;
                    break label_16;
            }
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case STAR:              // *
                    t = jj_consume_token(STAR);
                    break;
                case SLASH:             // /
                    t = jj_consume_token(SLASH);
                    break;
                case MOD:               // %
                    t = jj_consume_token(MOD);
                    break;
                default:
                    jj_la1[45] = jj_gen;
                    jj_consume_token(default_1);
                    throw new ParseException();
            }
            UnaryExpression();
            //二元表达式处理
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

    // 一元表达式处理
    final public void UnaryExpression() throws ParseException {
        Token t = null;
        switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
            case PLUS:              // +
            case MINUS:             // -
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case PLUS:      // ++
                        t = jj_consume_token(PLUS);
                        break;
                    case MINUS:     // --
                        t = jj_consume_token(MINUS);
                        break;
                    default:
                        jj_la1[46] = jj_gen;
                        jj_consume_token(default_1);
                        throw new ParseException();
                }
                // 一元表达式
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
            case INCR: // ++ i
                PreIncrementExpression();
                break;
            case DECR:  // -- i
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
            case NEW:                   // 非 + ，- 的一元表达式
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
                    case NEW:
                        PostfixExpression();            //后缀表达式
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
                case INCR:      // i ++
                    t = jj_consume_token(INCR);
                    break;
                case DECR:      // i --
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
                case NEW:
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
            PrimaryPrefix();                // 前缀表达式处理
            label_17:
            while (true) {
                switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                    case LBRACE:            // {
                    case LBRACKET:          // [
                    case DOT:               // .
                        ;
                        break;
                    default:
                        jj_la1[56] = jj_gen;
                        break label_17;
                }
                PrimarySuffix();            //后缀表达式
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
                Literal();          // false ,null ,true ,number ,str 常量表达式
                break;
            case LPAREN:            // ( a + b ) 的情况
                jj_consume_token(LPAREN);           // 为表达式去括号
                Expression();
                jj_consume_token(RPAREN);
                break;
            case NEW:
                AllocationExpression();         // new Exception()情况
                break;
            default:
                jj_la1[57] = jj_gen;
                if (jj_2_14(2147483647)) {  //方法调用 ，如 sum(1,2)
                    MethodInvocation();
                } else {
                    switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                        case IDENTIFIER:        // 变量名  a = 1 ,此时 a 是变量名，需要从名称空间中取变量的
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

    // lambda 表达式方法调用
    final public Token LambdaMethodInvocation(String methodName) throws ParseException {
        TSHMethodInvocation jjtn000 = new TSHMethodInvocation(T_MethodInvocation);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            AmbiguousName(methodName);                    //方法名称
            while (jj_2_33(3, LPAREN)) {   //如果存在 a()()形式的方法调用
                t = Arguments();
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
        return t;
    }

    // 方法调用
    final public void MethodInvocation() throws ParseException {
        TSHMethodInvocation jjtn000 = new TSHMethodInvocation(T_MethodInvocation);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            AmbiguousName();                    //方法名称
            while (jj_2_33(3, LPAREN)) {   //如果存在 a()()形式的方法调用
                Arguments();
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

    final public void AmbiguousName(String name) {
        TSHAmbiguousName jjtn000 = new TSHAmbiguousName(T_AmbiguousName);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            jjtreeCloseNodeScope(jjtn000);
            jjtn000.text = name;
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
                jjtreeCloseNodeScope(jjtn000);
            }
        }
    }

    //变量名，或方法引用名
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

    // 自定义字面量节点
    final public void CustomLiteral(String kind, Object value) throws ParseException {
        TSHLiteral jjtn000 = new TSHLiteral(T_Literal);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        boolean b;
        String literal = value.toString();
        try {
            switch (kind) {
                case NUMBER:
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    TBigDecimal tBigDecimal = new TBigDecimal();
                    tBigDecimal.setValue(TNumberUtil.objToBigDecimalDefault(literal, BigDecimal.ZERO));
                    tBigDecimal.setPrecision(TNumberUtil.getPrecision(literal));
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
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    try {
                        jjtn000.stringSetup(literal.substring(1, literal.length() - 1));
                    } catch (Exception e) {
                        {
                            if (true) throw createParseException("Error parsing string: " + literal);
                        }
                    }
                    break;
                case FALSE:
                case TRUE:
                    b = TNumberUtil.objToBooleanDefault(value, false);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.value = b ? Primitive.TRUE : Primitive.FALSE;
                    break;
                case NULL:
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


    final public void Literal() throws ParseException {
        TSHLiteral jjtn000 = new TSHLiteral(T_Literal);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token x;
        boolean b;
        String literal;
        try {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NUMBER:
                    //如果是 number 类型，创建 TBigDecimal 对象，这个对象中值为BigDecimal 类型，精度为小数点后几位
                    //在 java中 int类型的精度为0，值为 int类型的具体值，在 java中所有的数值类型都可以用TBigDecimal来封装
                    x = jj_consume_token(NUMBER);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    literal = x.image;
                    TBigDecimal tBigDecimal = new TBigDecimal();
                    tBigDecimal.setValue(TNumberUtil.objToBigDecimalDefault(literal, BigDecimal.ZERO));
                    tBigDecimal.setPrecision(TNumberUtil.getPrecision(literal));
                    try {
                        jjtn000.value = tBigDecimal;
                    } catch (NumberFormatException e) {
                        {
                            if (true) throw createParseException(
                                    "Error or number too big for integer type: " + literal);
                        }
                    }
                    break;
                case STR:           //字符串类型，
                    x = jj_consume_token(STR);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    try {
                        //字符串类型，处理掉字符串中的转化义字符
                        jjtn000.stringSetup(x.image.substring(1, x.image.length() - 1));
                    } catch (Exception e) {
                        {
                            if (true) throw createParseException("Error parsing string: " + x.image);
                        }
                    }
                    break;
                case FALSE:         //true ，false 表示 boolean 类型字面量
                case TRUE:
                    b = BooleanLiteral();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtreeCloseNodeScope(jjtn000);
                    jjtn000.value = b ? Primitive.TRUE : Primitive.FALSE;
                    break;
                case NULL:          //空类型
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

    //参数列表
    final public Token Arguments() throws ParseException {
        TSHArguments jjtn000 = new TSHArguments(T_Arguments);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        Token t = null;
        try {
            jj_consume_token(LPAREN);
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case FALSE:
                case NULL:
                case TRUE:
                case IDENTIFIER:
                case STR:
                case NUMBER:
                case LPAREN:
                case LBRACKET:
                case LBRACE:
                case BANG:
                case TILDE:
                case INCR:
                case DECR:
                case PLUS:
                case MINUS:
                case STAR:
                case SSTAR:
                case LAMBDA:
                case MAP:
                    ArgumentList();
                    break;
                default:
                    jj_la1[63] = jj_gen;
                    ;
            }
            t = jj_consume_token(RPAREN);
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
        return t;
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

    // new 表达式解析
    final public void AllocationExpression() throws ParseException {
        TSHAllocationExpression jjtn000 = new TSHAllocationExpression(T_AllocationExpression);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        jjtreeOpenNodeScope(jjtn000);
        try {
            switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                case NEW:
                    jj_consume_token(NEW);      //变量名
                    AmbiguousName();
                    switch ((jj_ntk == default_1) ? jj_ntk() : jj_ntk) {
                        case LPAREN:
                            Arguments(); //方法参数  new A(a,b,c)的情况
                            break;
                        default:
                            jj_la1[65] = jj_gen;
                            jj_consume_token(default_1);
                            throw new ParseException();
                    }
                    break;
                default:
                    jj_la1[66] = jj_gen;
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
                    int flag = jj_3_41(33);
                    if (flag == 1) {             // [:3]
                        CustomLiteral(NUMBER, 0);
                        jj_consume_token(COLON);
                        Expression();
                    } else if (flag == 2) {      // [1:]
                        Expression();
                        jj_consume_token(COLON);
                        CustomLiteral(NUMBER, DEFAULT_INT_MAX);     //默认最大值为 int最大值
                    } else if (flag == 3) {      // [1:3]
                        Expression();
                        jj_consume_token(COLON);
                        Expression();
                    } else {                      // [3]
                        Expression();
                    }
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
                return !BlockStatement();
        }
        throw new Error("Missing return statement in function");
    }


    final private boolean isMethod() {
        jj_lastpos = jj_scanpos = token;
        try {
            return !notDef();               //如果以def 开头，则表示是定义的是方法声明
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

    final private boolean jj_3_42(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3R_42();
        } catch (Parser.LookaheadSuccess ls) {
            return false;
        } finally {
            jj_save(28, xla);
        }
    }

    final private boolean jj_3R_42() {              // i for (i in range(5)) 的情况，第一个不是 for ,一行中，后面肯定有 for
        if (!jj_scan_token(FOR)) return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_scan_token(EOF)) {
                jj_scanpos = xsp;
            } else {
                return true;
            }
            if (!jj_scan_token(FOR)) return false;      //
            xsp = jj_scanpos;
            if (jj_scan_token(NEXT_LINE)) {
                jj_scanpos = xsp;
            } else {
                return true;
            }
        }
    }


    final private boolean jj_3R_40() {
        if (jj_scan_token(IDENTIFIER)) return true;         //  identifier
        if (jj_scan_token(COLON)) return true;              //  :
        return false;
    }


    final private int jj_3_41(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return jj_3_41_1();
        } catch (Parser.LookaheadSuccess ls) {
            return 0;
        } finally {
            jj_save(26, xla);
        }
    }


    final private int jj_3_41_1() {
        Token t1 = jj_scan_token_next();
        if (eq(t1.kind, COLON)) {               //   [:3]这种情况
            return 1;
        }

        while (true) {
            Token t2 = jj_scan_token_next();
            if (eq(t2.kind, COLON)) {
                Token t3 = jj_scan_token_next();
                if (eq(t3.kind, RBRACKET)) {    //  [1:]
                    return 2;
                } else {
                    return 3;                   //  [1:2]
                }
            }
            if (eq(t2.kind, RBRACKET)) {        //  [2]
                return 4;
            }
        }
    }


    final private int jj_2_29(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return jj_3R_28();
        } catch (Parser.LookaheadSuccess ls) {
            return 0;
        } finally {
            jj_save(28, xla);
        }
    }

    final private int jj_3R_28() {
        while (true) {
            Token t = jj_scan_token_next();
            if (TStringUtil.isNotBlank(t.image)) {   // },和 case 的情况，表示本次终止掉
                if (eqOR(t.kind, RBRACE, CASE)) {
                    return 0;
                } else if (eqOR(t.kind, BREAK)) {      // break 表示下一次终止掉
                    return 1;
                }
                return 2;
            }
        }
    }


    final private boolean jj_3_23() {
        while (true) {
            Token t = jj_scan_token_next();
            if (TStringUtil.isNotBlank(t.image)) {
                if (eq(t.kind, RBRACE)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    final private int jj_3_24() {
        while (true) {
            Token t = jj_scan_token_next();
            if (eq(t.kind, NEXT_LINE)) {
                return 0;
            }
            if (TStringUtil.isNotBlank(t.image)) {
                if (eq(t.kind, RPAREN)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
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


    final private boolean jj_3R_57() {
        if (jj_scan_token(IDENTIFIER)) return true;
        return false;
    }

    final private boolean jj_3R_33() {
        if (jj_3R_57()) return true;
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
        if (jj_3R_131()) {                      // []
            jj_scanpos = xsp;
            if (jj_3R_132()) {                  //.xxx(xxx)
                jj_scanpos = xsp;
                if (jj_3R_133()) return true;   // { }
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

    // 第一个不是++, 后面有 ++ 符号
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
        jj_3_30();
        jj_3_34();
        if (jj_3R_34()) return true;
        return false;
    }


    final private void jj_3_34() {
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_scan_token(DOT)) {
                jj_scanpos = xsp;
                break;
            }
            xsp = jj_scanpos;
            if (jj_scan_token(IDENTIFIER)) {
                jj_scanpos = xsp;
                break;
            }
        }
    }


    final private void jj_3_29() {
        Token xsp;
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
            jj_3_30();
        }
    }

    final private void jj_3_30() {
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_scan_token(LBRACKET)) {
                jj_scanpos = xsp;
                break;
            }
            xsp = jj_scanpos;
            if (jj_scan_token_util(RBRACKET)) {
                jj_scanpos = xsp;
                break;
            }
        }
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


    final private boolean jj_3_10() {
        if (jj_scan_token(COMMA)) return true;
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
            return !jj_2_4_4();
        } catch (Parser.LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(3, xla);
        }
    }

    final private boolean jj_2_4_4() {
        if (jj_scan_token(COMMA)) return true;
        return false;
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


    final private int jj_2_24(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return jj_3_24();
        } catch (Parser.LookaheadSuccess ls) {
            return 0;
        } finally {
            jj_save(22, xla);
        }
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


    final private boolean jj_2_31(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_31();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(30, xla);
        }
    }

    final private boolean jj_2_33(int xla, String kind) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_2_34(kind);
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(30, xla);
        }
    }


    final private boolean jj_2_34(String kind) {
        if (jj_scan_token(kind)) return true;
        return false;
    }


    // 变量初始化 a = [1,2,3] 或 a = {"username":"zhangsan","password":"123456"} 这两种情况处理
    final private boolean jj_3_31() {
        if (jj_scan_token(IDENTIFIER)) return true;
        jj_3_30();
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(ASSIGN)) {
            jj_scanpos = xsp;
            jj_3_29();
        }
        if (jj_scan_token(ASSIGN)) return true;
        return false;
    }

    final private boolean jj_3_32() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_scan_token(LBRACE)) {
            jj_scanpos = xsp;
            if (jj_scan_token(LBRACKET)) {
                return true;
            }
        }
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
