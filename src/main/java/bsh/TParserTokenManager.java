package bsh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TParserTokenManager extends TParserBase implements TParserConstants {

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
        mapChar.put((int) '/', BANG);
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


    public TParserTokenManager(JavaCharStream stream) {
        if (JavaCharStream.staticFlag) {
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
        }
        input_stream = stream;
    }

    public java.io.PrintStream debugStream = System.out;

    public void setDebugStream(java.io.PrintStream ds) {
        debugStream = ds;
    }


    protected Token jjFillToken() {
        Token t = new Token();
        t.tKind = jjmatchedKind;
        t.image = input_stream.GetImage().trim();
        t.beginLine = input_stream.getBeginLine();
        t.beginColumn = input_stream.getBeginColumn();
        t.endLine = input_stream.getEndLine();
        t.endColumn = input_stream.getEndColumn();
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
                matchedToken = jjFillToken();
                matchedToken.specialToken = specialToken;
                return matchedToken;
            }

            jjmatchedKind = default_jjmatchedKind;            // 2147483647
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_0();
            if (jjmatchedKind != default_jjmatchedKind) {
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
        if (eqOR(kind, TAB, SPACE, COMMA, EOF, ENTER, NEXT_LINE)) {               //如果遇到 tab ,空格， 和逗号
            String image = input_stream.GetImage();
            if (StringUtil.isNotBlank(image)) {       //表明前面有非空格，tab，的字符
                String match = matchs(image.trim().toCharArray());
                jjmatchedPos = input_stream.bufpos;
                if (StringUtil.isNotBlank(match)) {
                    jjmatchedKind = match;
                } else {
                    jjmatchedKind = LITERAL;
                }
                return 1;
            } else if (eqOR(kind, EOF)) {
                jjmatchedKind = EOF;
                return 1;
            }
        } else if (eqOR(kind, LPAREN, RPAREN, LBRACE, RBRACE, LBRACKET, RBRACKET,
                SEMICOLON, COMMA, DOT)) {
            String image = input_stream.GetImage().trim();
            if (image.length() > 1) {
                input_stream.backup(1);
            }
            return getCommon();
        } else if (eqOR(kind, LT)) { //如果是小于号，可能是<=，<<=,<< 三种情况
            return getSpecial(kind, '<', '=');
        } else if (eqOR(kind, GT)) {//可能会出现的情况>=，>>，>>>，>>=，>>>=
            return getSpecial(kind, '=', '>');
        } else if (eqOR(kind, ASSIGN, BANG, STAR, SLASH, XOR, MOD)) {     // = ,! ,* ,/ ,^,% ,后面只能接 =
            return getSpecial(kind, '=');
        } else if (eqOR(kind, PLUS)) {            // + 号后面能接 +,=
            return getSpecial(kind, '+', '=');
        } else if (eqOR(kind, MINUS)) {           // - 号后面 只能接 - 或 = ，组成 -= 或--
            return getSpecial(kind, '-', '=');
        } else if (eqOR(kind, AND)) {     // & 后面只能接 & 或 =
            return getSpecial(kind, '&', '=');
        } else if (eqOR(kind, OR)) {      // | 后面只能接 | 或 =
            return getSpecial(kind, '|', '=');
        }
        return 0;
    }


    public int getSpecial(String kind, char... matches) {
        String image = input_stream.GetImage().trim();
        image = image.substring(0, image.length() - 1);
        if (image.length() > 0 && !image.endsWith(kind)) {
            input_stream.backup(1);
            return getCommon();
        } else {
            char c = readChar();
            input_stream.backup(1);
            boolean flag = true;
            for (char m : matches) {
                if (c == m) {
                    flag = false;
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
            e.printStackTrace();
        }
        return 0;
    }

    public int getCommon() {
        String image = input_stream.GetImage().trim();
        if (StringUtil.isNotBlank(image)) {       //表明前面有非空格，tab，的字符
            String match = matchs(image.trim().toCharArray());
            jjmatchedPos = input_stream.bufpos;
            if (StringUtil.isNotBlank(match)) {
                jjmatchedKind = match;
            } else {
                jjmatchedKind = LITERAL;
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
            if (StringUtil.isBlank(buf + "")) {
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
    }

    public int jjMoveStringLiteralDfa0_0() {
        while (true) {
            try {
                int flag = 0;
                if (curChar == '\t') {
                    flag = jjStartNfaWithStates_0(TAB);
                } else if (curChar == ' ') {
                    flag = jjStartNfaWithStates_0(SPACE);
                } else if (curChar == '\r') {
                    flag = jjStartNfaWithStates_0(ENTER);
                } else if (curChar == '\n') {
                    flag = jjStartNfaWithStates_0(NEXT_LINE);
                } else if (curChar == '\n') {
                    flag = jjStartNfaWithStates_0(NEXT_LINE);
                } else if ((curChar >= 48 && curChar <= 57) || (curChar >= 65 && curChar <= 90) || (curChar >= 97 && curChar <= 122)) {
                    flag = jjStartNfaWithStates_0(LITERAL);
                } else {
                    flag = jjStartNfaWithStates_0(mapChar.get(new Integer(curChar)));
                }
                if (flag > 0) {
                    return input_stream.bufpos + 1;
                }
                curChar = input_stream.readChar();
            } catch (IOException e) {
                jjStartNfaWithStates_0(EOF);
                return input_stream.bufpos + 1;
            } catch (Exception e) {
                e.printStackTrace();
                return input_stream.bufpos + 1;
            }
        }

    }


}
