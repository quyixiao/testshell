package test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class JavaCharStreamTest {
    public static final boolean staticFlag = false;

    public static final int DEFAULT_BUFSIZE = 4;
    public static final int DEFAULT_MAX_NEXT_CHAR_IND = 8;

    static final int hexval(char c) throws java.io.IOException {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;

            case 'a':
            case 'A':
                return 10;
            case 'b':
            case 'B':
                return 11;
            case 'c':
            case 'C':
                return 12;
            case 'd':
            case 'D':
                return 13;
            case 'e':
            case 'E':
                return 14;
            case 'f':
            case 'F':
                return 15;
        }

        throw new java.io.IOException(); // Should never come here
    }

    public int bufpos = -1;
    public int bufsize;
    public int available;
    public int tokenBegin;
    protected int bufline[];
    protected int bufcolumn[];

    protected int column = 0;
    protected int line = 1;

    protected boolean prevCharIsCR = false;
    protected boolean prevCharIsLF = false;

    protected java.io.Reader inputStream;

    protected char[] nextCharBuf;
    protected char[] buffer;
    protected int maxNextCharInd = 0;
    protected int nextCharInd = -1;
    protected int inBuf = 0;

    protected void ExpandBuff(boolean wrapAround) {
        char[] newbuffer = new char[bufsize + DEFAULT_BUFSIZE];
        int newbufline[] = new int[bufsize + DEFAULT_BUFSIZE];
        int newbufcolumn[] = new int[bufsize + DEFAULT_BUFSIZE];

        try {

            if (wrapAround) {
                System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
                System.arraycopy(buffer, 0, newbuffer, bufsize - tokenBegin, bufpos);
                buffer = newbuffer;


                System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
                System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
                bufline = newbufline;

                System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
                System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
                bufcolumn = newbufcolumn;

                bufpos += (bufsize - tokenBegin);
            } else {
                System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
                buffer = newbuffer;

                System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
                bufline = newbufline;

                System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
                bufcolumn = newbufcolumn;

                bufpos -= tokenBegin;
            }
        } catch (Throwable t) {
            throw new Error(t.getMessage());
        }

        available = (bufsize += DEFAULT_BUFSIZE);
        tokenBegin = 0;
    }

    protected void FillBuff() throws java.io.IOException {
        int i;
        if (maxNextCharInd == DEFAULT_MAX_NEXT_CHAR_IND)
            maxNextCharInd = nextCharInd = 0;

        try {
            if ((i = inputStream.read(nextCharBuf, maxNextCharInd, DEFAULT_MAX_NEXT_CHAR_IND - maxNextCharInd)) == -1) {
                inputStream.close();
                throw new java.io.IOException();
            } else
                maxNextCharInd += i;
            return;
        } catch (java.io.IOException e) {
            if (bufpos != 0) {
                --bufpos;
                backup(0);
            } else {
                bufline[bufpos] = line;
                bufcolumn[bufpos] = column;
            }
            throw e;
        }
    }

    protected char ReadByte() throws java.io.IOException {
        if (++nextCharInd >= maxNextCharInd)
            FillBuff();

        return nextCharBuf[nextCharInd];
    }

    public char BeginToken() throws java.io.IOException {
        if (inBuf > 0) {
            --inBuf;

            if (++bufpos == bufsize)
                bufpos = 0;

            tokenBegin = bufpos;
            return buffer[bufpos];
        }

        tokenBegin = 0;
        bufpos = -1;

        return readChar();
    }

    protected void AdjustBuffSize() {
        if (available == bufsize) {
            if (tokenBegin > DEFAULT_BUFSIZE) {
                bufpos = 0;
                available = tokenBegin;
            } else {
                ExpandBuff(false);
            }
        } else if (available > tokenBegin) {
            available = bufsize;
        } else if ((tokenBegin - available) < DEFAULT_BUFSIZE) {
            ExpandBuff(true);
        } else {
            available = tokenBegin;
        }
    }

    protected void UpdateLineColumn(char c) {
        column++;

        if (prevCharIsLF) {
            prevCharIsLF = false;
            line += (column = 1);
        } else if (prevCharIsCR) {
            prevCharIsCR = false;
            if (c == '\n') {
                prevCharIsLF = true;
            } else
                line += (column = 1);
        }

        switch (c) {
            case '\r':
                prevCharIsCR = true;
                break;
            case '\n':
                prevCharIsLF = true;
                break;
            case '\t':
                column--;
                column += (8 - (column & 07));
                break;
            default:
                break;
        }

        bufline[bufpos] = line;
        bufcolumn[bufpos] = column;
    }

    public char readChar() throws java.io.IOException {
        if (inBuf > 0) {
            --inBuf;

            if (++bufpos == bufsize)
                bufpos = 0;

            return buffer[bufpos];
        }

        char c;

        if (++bufpos == available)
            AdjustBuffSize();

        if ((buffer[bufpos] = c = ReadByte()) == '\\') {
            UpdateLineColumn(c);

            int backSlashCnt = 1;
            // 读取所有的 反斜杠
            for (; ; ) {
                if (++bufpos == available)
                    AdjustBuffSize();

                try {
                    if ((buffer[bufpos] = c = ReadByte()) != '\\') {
                        UpdateLineColumn(c);
                        // found a non-backslash char.
                        if ((c == 'u') && ((backSlashCnt & 1) == 1)) {
                            if (--bufpos < 0)
                                bufpos = bufsize - 1;

                            break;
                        }

                        backup(backSlashCnt);
                        return '\\';
                    }
                } catch (java.io.IOException e) {
                    if (backSlashCnt > 1)
                        backup(backSlashCnt);

                    return '\\';
                }

                UpdateLineColumn(c);
                backSlashCnt++;
            }

            // 在这里，我们看到了奇数个反斜杠，后跟一个'u'
            try {
                while ((c = ReadByte()) == 'u')
                    ++column;

                buffer[bufpos] = c = (char) (hexval(c) << 12 |
                        hexval(ReadByte()) << 8 |
                        hexval(ReadByte()) << 4 |
                        hexval(ReadByte()));

                column += 4;
            } catch (java.io.IOException e) {
                throw new Error("Invalid escape character at line " + line +
                        " column " + column + ".");
            }

            if (backSlashCnt == 1)
                return c;
            else {
                backup(backSlashCnt - 1);
                return '\\';
            }
        } else {
            UpdateLineColumn(c);
            return (c);
        }
    }

    /**
     * @see #getEndColumn
     * @deprecated
     */

    public int getColumn() {
        return bufcolumn[bufpos];
    }

    /**
     * @see #getEndLine
     * @deprecated
     */

    public int getLine() {
        return bufline[bufpos];
    }

    public int getEndColumn() {
        return bufcolumn[bufpos];
    }

    public int getEndLine() {
        return bufline[bufpos];
    }

    public int getBeginColumn() {
        return bufcolumn[tokenBegin];
    }

    public int getBeginLine() {
        return bufline[tokenBegin];
    }

    public void backup(int amount) {

        inBuf += amount;
        if ((bufpos -= amount) < 0)
            bufpos += bufsize;
    }

    public JavaCharStreamTest(java.io.Reader dstream, int startline, int startcolumn, int buffersize) {
        inputStream = dstream;
        line = startline;
        column = startcolumn - 1;

        available = bufsize = buffersize;
        buffer = new char[buffersize];
        bufline = new int[buffersize];
        bufcolumn = new int[buffersize];
        nextCharBuf = new char[DEFAULT_MAX_NEXT_CHAR_IND];
    }

    public JavaCharStreamTest(java.io.Reader dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, DEFAULT_MAX_NEXT_CHAR_IND);
    }


    public String GetImage() {
        if (bufpos >= tokenBegin) {
            return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
        } else {
            return new String(buffer, tokenBegin, bufsize - tokenBegin) +
                    new String(buffer, 0, bufpos + 1);
        }
    }

    // [0,1,2,3,4,-1,-1,-1]
    // [0,1,2,3,4,-1,-1,5]

    public static void test1() throws Exception {
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/test/resources/test.tsh");
        JavaCharStreamTest jj_input_stream = new JavaCharStreamTest(in, 1, 1);
        jj_input_stream.BeginToken();
        String a = jj_input_stream.read(jj_input_stream, 5);
        jj_input_stream.backup(7);
        System.out.println("第一次读取：" + jj_input_stream.GetImage());

        jj_input_stream.BeginToken();
        String c = jj_input_stream.read(jj_input_stream, 17);
        System.out.println("第二次读取：" + jj_input_stream.GetImage());

    }


    public static void main(String[] args) throws Exception {
        //test1();
        test2();
        //test3();


        char c = '\u2605';
        System.out.println(c);

        c = (char) (hexval('2') << 12 |
                hexval('6') << 8 |
                hexval('0') << 4 |
                hexval('5'));
        System.out.println(c);
    }

    public static void test2() throws Exception {
        Reader in = new FileReader("/Users/quyixiao/project/testshell/src/test/resources/test.tsh");
        JavaCharStreamTest jj_input_stream = new JavaCharStreamTest(in, 1, 1);
        jj_input_stream.BeginToken();
        String a = jj_input_stream.read(jj_input_stream, 5);
        jj_input_stream.backup(7);
        System.out.println("第一次读取内容：" + jj_input_stream.GetImage());


        jj_input_stream.BeginToken();
        jj_input_stream.read(jj_input_stream, 17);
        System.out.println("第二次读取内容：" + jj_input_stream.GetImage());

        jj_input_stream.BeginToken();
        jj_input_stream.read(jj_input_stream, 10);
        System.out.println("第三次读取内容：" + jj_input_stream.GetImage());


    }

    public static void test3() {
        for (int i = 0; i < 30; i++) {
            System.out.println(i + " " + (i & 1));
        }


    }


    public String read(JavaCharStreamTest jj_input_stream, int p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p; i++) {
            try {
                char a = jj_input_stream.readChar();
                sb.append(a + "");
                System.out.println("i = " + i + " ,c = " + a + " ,buffer = " + Arrays.toString(buffer));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
