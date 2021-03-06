package tsh.util;


import tsh.Token;
import tsh.constant.ParserConstants;
import tsh.exception.TokenMgrError;
import tsh.t.TSHTuple;
import tsh.t.TTuple2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserTokenManager extends Utils implements ParserConstants {

    public static List<char[]> type = new ArrayList<>();


    static {
        for (String temp : tempType) {
            type.add(temp.toCharArray());
        }
    }

    static {
        mapChar.put((int) '!', BANG);
        mapChar.put((int) '"', DOUBLE_QUOT);
        mapChar.put((int) '#', NOTES);
        mapChar.put((int) '$', DOLLAR);
        mapChar.put((int) '%', MOD);
        mapChar.put((int) '&', AND);
        mapChar.put((int) '\'', SINGLE_QUOT);
        mapChar.put((int) '(', LPAREN);
        mapChar.put((int) ')', RPAREN);
        mapChar.put((int) '*', STAR);
        mapChar.put((int) '+', PLUS);
        mapChar.put((int) ',', COMMA);
        mapChar.put((int) '-', MINUS);
        mapChar.put((int) '.', DOT);
        mapChar.put((int) '/', SLASH);
        mapChar.put((int) ':', COLON);
        mapChar.put((int) ';', SEMICOLON);
        mapChar.put((int) '<', LT);
        mapChar.put((int) '=', ASSIGN);
        mapChar.put((int) '>', GT);
        mapChar.put((int) '?', HOOK);
        mapChar.put((int) '@', AT);
        mapChar.put((int) '[', LBRACKET);
        mapChar.put((int) ']', RBRACKET);
        mapChar.put((int) '\\', CONTINUE_LINE);
        mapChar.put((int) '^', XOR);
        mapChar.put((int) '_', UNDERLINE);
        mapChar.put((int) '`', DEAD_CHAR);
        mapChar.put((int) '{', LBRACE);
        mapChar.put((int) '|', OR);
        mapChar.put((int) '}', RBRACE);
        mapChar.put((int) '~', TILDE);
    }

    protected JavaCharStream input_stream;
    private final int[] jjrounds = new int[74];
    private final int[] jjstateSet = new int[148];
    protected char curChar;

    int curLexState = 0;
    int defaultLexState = 0;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    String jjmatchedKind;
    private boolean continueReader = false;
    private String curKind = "";


    public ParserTokenManager(JavaCharStream stream) {
        if (JavaCharStream.staticFlag) {
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
        }
        input_stream = stream;
    }

    public java.io.PrintStream debugStream = System.out;

    public void setDebugStream(java.io.PrintStream ds) {
        debugStream = ds;
    }


    public void ReInit(JavaCharStream stream) {
        jjmatchedPos = jjnewStateCnt = 0;
        curLexState = defaultLexState;
        input_stream = stream;
        ReInitRounds();
    }

    private final void ReInitRounds() {
        int i;
        jjround = 0x80000001;
        for (i = 74; i-- > 0; )
            jjrounds[i] = 0x80000000;
    }

    protected Token jjFillToken() {
        Token t = new Token();
        t.kind = jjmatchedKind;
        t.image = input_stream.GetImage().trim();
        if (jjmatchedKind.equals(EOF) && TStringUtil.isNotBlank(t.image)) {
            if (TRUE.equals(t.image.trim())) {
                t.kind = TRUE;
            } else if (FALSE.equals(t.image.trim())) {
                t.kind = FALSE;
            }
        }
        t.beginLine = input_stream.getBeginLine();
        t.beginColumn = input_stream.getBeginColumn();
        t.endLine = input_stream.getEndLine();
        t.endColumn = input_stream.getEndColumn();
        return t;
    }

    public static Token jjTempFillToken() {
        Token t = new Token();
        t.kind = default_1;
        t.image = "";
        t.beginLine = 0;
        t.beginColumn = 0;
        t.endLine = 0;
        t.endColumn = 0;
        return t;
    }

    protected Token jjFillTokenEof() {
        Token t = new Token();
        t.kind = jjmatchedKind;
        t.image = "";
        return t;
    }


    public Token getNextToken() {
        Token specialToken = null;
        Token matchedToken;
        int curPos = 0;

        EOFLoop:
        for (; ; ) {
            try {
                curChar = input_stream.BeginToken();
            } catch (java.io.IOException e) {
                jjmatchedKind = EOF;
                return jjFillTokenEof();
            }

            jjmatchedKind = default_jjmatchedKind;            // 2147483647
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_0();
            if (curPos == -1) {     // 如读取到续行符 \ 时，继续向下读取
                continue;
            }
            if (jjmatchedKind != default_jjmatchedKind) {
                //文件读取结束
                if (eqOR(jjmatchedKind, EOF)) {
                    return jjFillToken();
                }
                //如果返回的位置小于当前读取到的位置，回退到当前返回位置
                // 如 buffer = [a,b,c,d,e,f,g]
                // jjmatchedPos = 3
                // curPos = 6,则需要回退 6 - 3 - 1 = 2 ，因此下次从 e 开始读取
                if (jjmatchedPos + 1 < curPos) {
                    input_stream.backup(curPos - jjmatchedPos - 1);
                }
                // false jjmatchedKind is : [1, 2, 3, 4, 5, 6, 7, 8, 9, 61, 62, 63, 65, 70, 71]
                if (!commonJjstrLiteralImages.contains(jjmatchedKind)) {
                    matchedToken = jjFillToken();
                    matchedToken.specialToken = specialToken;
                    return matchedToken;
                } else {
                    // false jjmatchedKind is :
                    if (!jjstrLiteralImages.contains(jjmatchedKind)) {
                        matchedToken = jjFillToken();
                        if (specialToken == null)
                            specialToken = matchedToken;
                        else {
                            matchedToken.specialToken = specialToken;
                            specialToken = (specialToken.next = matchedToken);
                        }
                    }
                    continue EOFLoop;
                }
            }
            //如果读取不正确，返回出错的行和列
            int error_line = input_stream.getEndLine();
            int error_column = input_stream.getEndColumn();
            String error_after = null;
            boolean EOFSeen = false;
            try {
                input_stream.readChar();
                input_stream.backup(1);
            } catch (java.io.IOException e1) {
                EOFSeen = true;
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
                if (curChar == '\n' || curChar == '\r') {
                    error_line++;
                    error_column = 0;
                } else
                    error_column++;
            }
            if (!EOFSeen) {
                input_stream.backup(1);
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
            }
            throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
        }
    }

    private final int jjStartNfaWithStates_0(String kind) {
        //continueReader 主要是用于处理字符串，只要""或''包裹的任意字符，都当成字符串来处理
        if (!continueReader && eqOR(kind, TAB, SPACE)) {               //如果遇到 tab ,空格， 和逗号
            return getCommon();
        } else if (!continueReader && eqOR(kind, ENTER, NEXT_LINE)) {  //当读取到\r和 \n
            String image = input_stream.GetImage().trim();
            if (image.length() > 0) {
                input_stream.backup(1);
            } else {
                jjmatchedPos = input_stream.bufpos;
                jjmatchedKind = NEXT_LINE;
                return 1;
            }
            return getCommon();
        } else if (!continueReader && eqOR(kind, CONTINUE_LINE)) { //当读取到续行符 \ 时
            char c = readChar();
            if (c > 0) {
                input_stream.backup(1);
            }
            String temp = c + "";
            if (c == 0 || TStringUtil.isBlank(temp)) {
                //如果续行符前面的类型不为空时，返回续行符前面的内容
                String image = input_stream.GetImage().trim();
                if (image.length() > 1) {
                    input_stream.backup(1);
                    return getCommon();
                } else {
                    TTuple2<Boolean, Integer> line = readUtilChar('\n').getData();
                    int i = line.getSecond() + input_stream.GetImage().length() - 1;
                    input_stream.tokenBegin += i;
                    return -1;
                }
            }
        } else if (!continueReader && eqOR(kind, LPAREN, RPAREN, LBRACE, RBRACE, LBRACKET, RBRACKET, SEMICOLON, COMMA,
                COLON, HOOK, AT, TILDE)) { //如果读取到 （ ）{ } [ ]  ； , : ? @ ~
            String image = input_stream.GetImage().trim();
            if (eqOR(image, SEMICOLON)) {     //如果是; ，略过, 在 java中代码必需以;结尾，但是在脚本中，写不写;都不影响
                input_stream.tokenBegin += 1;
                return -1;
            }
            if (image.length() > 1) {
                input_stream.backup(1);
            }
            return getCommon();
        } else if (!continueReader && eqOR(kind, DOT)) {    //. 的处理
            String image = input_stream.GetImage().trim();
            image = image.substring(0, image.length() - 1);
            if (TStringUtil.isNumber(image)) { //如果前面是数字，则继续读取
                return 0;
            }
            if (image.length() > 0) {
                input_stream.backup(1);
            }
            return getCommon();
        } else if (!continueReader && eqOR(kind, LT)) { //如果是 < ，可能是<=，<<=,<< 三种情况
            return getSpecial(new char[]{'<'}, '<', '=');
        } else if (!continueReader && eqOR(kind, GT)) {//可能会出现的情况>=，>>，>>>，>>=，>>>=
            return getSpecial(new char[]{'>'}, '=', '>');
        } else if (!continueReader && eqOR(kind, SLASH)) {     // / 号后面只能接 = 但是，如果 //,/**... */ 表示注释,下面主要是对注释处理
            char c = readChar();
            input_stream.backup(1);
            if (c == '/') {       //表示注释
                TTuple2<Boolean, Integer> line = readUtilChar('\n').getData();
                if (line.getFirst()) {
                    input_stream.backup(1);
                }
                int i = line.getSecond() + input_stream.GetImage().length() - 1;
                input_stream.tokenBegin += i;
                return -1;                                  //返回-1 表示略过 所解析的字符  , //表示注释
            } else if (c == '*') {                        // /* .... */ 也表示注释
                readChar();         //读取当前 *
                int i = input_stream.GetImage().length() - 1;
                while (true) {
                    TTuple2<Boolean, Integer> line = readUtilChar('*').getData();
                    i += line.getSecond();
                    char temp = readChar();
                    if (temp == '/') {
                        i = i + 1;
                        break;
                    }
                }
                input_stream.tokenBegin += i;
                return -1;
            }
            return getSpecial(null, '=');
        } else if (!continueReader && eqOR(kind, ASSIGN)) {     // = ,后面只能接 =,如 ==，<=，<<=，>=，>>=，>>>=，!=，+=，-=，*=，/=，&=，|=，^=，%=
            return getSpecial(new char[]{'=', '<', '>', '!', '+', '-', '*', '/', '&', '|', '^', '%'}, '=');
        } else if (!continueReader && eqOR(kind, STAR)) {     // ! ,*  ,^,% ,后面只能接 =
            return getSpecial(new char[]{'*'}, '*', '=');
        } else if (!continueReader && eqOR(kind, BANG, XOR, MOD)) {     // ! ,*  ,^,% ,后面只能接 =
            return getSpecial(null, '=');
        } else if (!continueReader && eqOR(kind, PLUS)) {            // + 号后面能接 +,= 有 ，++ 和+= 两种情况
            return getSpecial(new char[]{'+'}, '+', '=');
        } else if (!continueReader && eqOR(kind, MINUS)) {           // - 号后面 只能接 - 或 = ，组成 -= 或--
            return getSpecial(new char[]{'-'}, '-', '=');
        } else if (!continueReader && eqOR(kind, AND)) {     // & 后面只能接 & 或 =
            return getSpecial(new char[]{'&'}, '&', '=');
        } else if (!continueReader && eqOR(kind, OR)) {      // | 后面只能接 | 或 =
            return getSpecial(new char[]{'|'}, '|', '=');
        } else if (eqOR(kind, DOUBLE_QUOT, SINGLE_QUOT)) {      // " 双引号处理,' 单引号处理
            if (continueReader && eqOR(kind, curKind)) {
                input_stream.backup(2);
                char c = readChar();
                readChar();
                if (c == '\\') {//表示继续
                    return 0;
                }
                continueReader = !continueReader;
                curKind = "";
                int position = getCommon();
                jjmatchedKind = STR;
                return position;
            }
            if (!continueReader) {
                continueReader = !continueReader;
                curKind = kind;
            }
        } else if (eqOR(kind, EOF)) {
            jjmatchedKind = EOF;
            return 1;
        }
        return 0;
    }


    public int getSpecial(char[] preMatches, char... afterMatches) {
        String image = input_stream.GetImage().trim();
        image = image.substring(0, image.length() - 1);
        boolean preFlag = true;
        if (preMatches != null && preMatches.length > 0) {
            for (char m : preMatches) {
                String temp = image;                    // 如 <<= 的情况，读取到最后一个等于符号时
                if (TStringUtil.isNotBlank(temp) && temp.trim().length() > 0) {
                    temp = temp.substring(temp.length() - 1);
                }
                if (eq(temp, m + "")) {
                    preFlag = false;
                    break;
                }
            }
        }
        if (image.length() > 0 && preFlag) {
            input_stream.backup(1);
            return getCommon();
        } else {
            char c = readChar();
            if (c > 0) {
                input_stream.backup(1);
            } else {
                return getCommon();
            }
            boolean flag = true;
            for (char m : afterMatches) {
                if (c == m) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return getCommon();
            }
        }
        return 0;
    }


    public char readChar() {
        try {
            return input_stream.readChar();
        } catch (IOException e) {

        }
        return 0;
    }


    public TSHTuple readUtilChar(char compare) {
        int i = 0;
        while (true) {
            try {
                char c = input_stream.readChar();
                ++i;
                if (c == compare) {
                    return new TSHTuple(true, i);
                }
            } catch (IOException e) {
                return new TSHTuple(false, i);
            }
        }
    }

    public int getCommon() {
        String image = input_stream.GetImage().trim();
        if (TStringUtil.isNotBlank(image)) {       //表明前面有非空格，tab，的字符
            // 如 image = for ,和我们的关键字 for 匹配上了，则 match = for
            String match = matchs(image.trim().toCharArray());
            jjmatchedPos = input_stream.bufpos;
            if (TStringUtil.isNotBlank(match)) {
                jjmatchedKind = match;      //设置 匹配到的类型
            } else {
                if (TStringUtil.isNumber(image)) {      //判断当前字符串是不是数字类型
                    jjmatchedKind = NUMBER;
                } else {
                    jjmatchedKind = IDENTIFIER;         //如果既不是关键字类型，也还是数字类型，则只能是identifier类型了
                }
            }
            return 2;
        }
        return 0;
    }


    public static String matchs(char[] buffer) {
        List<Integer> locations = new ArrayList<>();
        for (int i = 0; i < type.size(); i++) {
            locations.add(i);
        }
        int flag = 0;
        for (char buf : buffer) {
            int a = (int) buf;
            if (a == 0) {
                break;
            }
            if (TStringUtil.isBlank(buf + "")) {
                continue;
            }
            List<Integer> tempLocations = new ArrayList<>();
            for (int i : locations) {
                if (type.get(i).length > flag && type.get(i)[flag] == buf) {
                    tempLocations.add(i);
                }
            }
            locations = tempLocations;
            flag++;
        }
        int mini = -1;
        for (int i : locations) {
            if (type.get(i).length == buffer.length) {
                mini = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (mini > -1) {
            char a[] = type.get(mini);
            for (char b : a) {
                sb.append(b);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        char[] a = {'&'};
        String b = matchs(a);
        System.out.println(b);
        StringBuilder sb = new StringBuilder();
        for (char[] t : type) {
            sb.append(t).append(" , ");
        }
        System.out.println(sb);
    }

    public int jjMoveStringLiteralDfa0_0() {
        while (true) {
            try {
                int flag = 0;
                if (curChar == '\t') {              //如果是 TAB 类型
                    flag = jjStartNfaWithStates_0(TAB);
                } else if (curChar == ' ') {        //如果是 空格 类型
                    flag = jjStartNfaWithStates_0(SPACE);
                } else if (curChar == '\r') {       //如果是 回车 类型
                    flag = jjStartNfaWithStates_0(ENTER);
                } else if (curChar == '\n') {       //如果是换行符
                    flag = jjStartNfaWithStates_0(NEXT_LINE);
                } else if ((curChar >= 48 && curChar <= 57)
                        || (curChar >= 65 && curChar <= 90)
                        || (curChar >= 97 && curChar <= 122)) { //如果是 a~z,A~Z,0~9 三种情况，表示是字面量类型
                    flag = jjStartNfaWithStates_0(IDENTIFIER);
                } else {//@,[,\,],^,_,`,!,",#,$,%,&,',(,),*,+,-,.,/,:,;,{,<,|,=,},>,~,? 类型
                    flag = jjStartNfaWithStates_0(mapChar.get(new Integer(curChar)));
                }
                if (flag > 0) {         //如果 flag > 0 ，表示本次读取结束 ，如读取到 for 了
                    return input_stream.bufpos + 1;
                }
                if (flag == -1) {                       // 当读取到续行符  \ 时，表示不要返回，继续读取
                    return -1;
                }
                curChar = input_stream.readChar();      //继续向后读取一个字符
            } catch (IOException e) {
                jjStartNfaWithStates_0(SPACE);
                return input_stream.bufpos + 1;
            } catch (Exception e) {
                e.printStackTrace();
                return input_stream.bufpos + 1;
            }
        }

    }


}
