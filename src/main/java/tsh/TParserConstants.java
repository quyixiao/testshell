
package tsh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TParserConstants {

    String EOF = "eof";                            //  eof
    String SPACE = " ";
    String TAB = "\t";
    String ENTER = "\r";
    String NEXT_LINE = "\n";
    String BREAK = "break";                         //  break
    String CASE = "case";                          //  case
    String CATCH = "catch";                         //  catch
    String CONTINUE = "continue";                      //  continue
    String _DEFAULT = "default";                      //  default
    String DO = "do";                            //  do
    String ELSE = "else";                          //  else
    String FALSE = "false";                         //  false
    String FINALLY = "finally";                       //  finally
    String FOR = "for";                           //  for
    String IF = "if";                            //  if
    String NULL = "null";                          //  null
    String RETURN = "return";                        //  return
    String SWITCH = "switch";                        //  switch
    String THROW = "throw";                         //  throw
    String THROWS = "throws";                        //  throws
    String TRUE = "true";                          //  true
    String TRY = "try";                           //  try
    String WHILE = "while";                     //  while
    String DEF = "def";                            //  def
    String METHOD = "method";                            //方法
    String VAR = "var";                            //变量
    String EXPORT = "export";                            // 变量导出

    String LITERAL = "literal";                            // 字符字面量
    String BOOL = "bool";                            // bool 数据类型
    String STR = "str";                            // 字符串数据类型
    String NUMBER = "number";                            // 数字类型
    String LIST = "list";                            // 列表类型
    String SET = "set";                            // set 数据类型
    String MAP = "map";                              // map 类型
    String TUPLE = "tuple";                          //元组类型


    String LPAREN = "(";                        //  (
    String RPAREN = ")";                        //  )
    String LBRACE = "{";                        //  {
    String RBRACE = "}";                        //  }
    String LBRACKET = "[";                      //  [
    String RBRACKET = "]";                      //  ]
    String SEMICOLON = ";";                     //  ;
    String COMMA = ",";                         //  ,
    String DOT = ".";                           //  .

    String ASSIGN = "=";                        //  =
    String GT = ">";                            //  >
    String LT = "<";                            //  <
    String BANG = "!";                          //  !
    String TILDE = "~";                         //  ~
    String HOOK = "?";                          //  ?
    String COLON = ":";                         //  :
    String OR = "|";                       //  |
    String AND = "&";                      //  &
    String PLUS = "+";                         //  +
    String MINUS = "-";                        //  -
    String STAR = "*";                         //  *
    String SLASH = "/";                        //  /
    String XOR = "^";                          //  ^
    String MOD = "%";                          //  %
    String DOUBLE_QUOT = "\"";                          //  "
    String SINGLE_QUOT = "'";                          //  '
    String NOTES = "#";                          //  #
    String DOLLAR = "$";                          //  $
    String DEAD_CHAR = "`";                          //  `
    String UNDERLINE = "_";                          //
    String CONTINUE_LINE = "\\";                          //
    String AT = "@";                          //

    String EQ = "==";                            //  ==
    String LE = "<=";                            //  <=
    String GE = ">=";                            //  >=
    String NE = "!=";                            //  !=
    String INCR = "++";                         //  ++
    String DECR = "--";                         //  --
    String BIT_AND = "&&";                      //  &&
    String BIT_OR = "||";                       //  ||
    String LSHIFT = "<<";                       //  <<
    String RSIGNEDSHIFT = ">>";                 //  >>
    String RUNSIGNEDSHIFT = ">>>";               //  >>>
    String PLUSASSIGN = "+=";                   //  +=
    String MINUSASSIGN = "-=";                  //  -=
    String STARASSIGN = "*=";                   //  *=
    String SLASHASSIGN = "/=";                  //  /=
    String ANDASSIGN = "&=";                    //  &=
    String ORASSIGN = "|=";                     //  |=
    String XORASSIGN = "^=";                    //  ^=
    String MODASSIGN = "%=";                    //  %=
    String LSHIFTASSIGN = "<<=";                 //  <<=
    String RSIGNEDSHIFTASSIGN = ">>=";         //  >>=
    String RUNSIGNEDSHIFTASSIGN = ">>>=";         //  >>>=


    public final static String default_jjmatchedKind = "0x7fffffff";
    String default_1 = "-1";


    String DEFAULT = "default";

    public static final List<String> commonJjstrLiteralImages = Arrays.asList(new String[]{
            "", "<EOF>", " ", "\\t", "\\r", "\\f", "\\n",
    });


    public static final List<String> jjstrLiteralImages = Arrays.asList(new String[]{
            "break", "case", "catch", "continue", "default", "do", "else", "enum", "false", "finally", "for", "if",
            "return", "switch", "throw", "throws", "true", "try", "void", "while", "(", ")", "{", "}", "[", "]", ";",
            ",", ".", "=", ">", "<", "!", "~", "?", ":", "==", "<=", ">=", "!=", "||", "&&", "++", "--", "+", "-", "*",
            "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>=",
    });

    public static final List<String> tempType = Arrays.asList(new String[]{
            BREAK, CASE, CATCH, CONTINUE, _DEFAULT, DO, ELSE, FALSE, FINALLY, FOR, IF, NULL, RETURN, SWITCH, THROW, THROWS,
            TRUE, TRY, WHILE, DEF, METHOD, VAR, EXPORT,
            LPAREN,
            RPAREN,
            LBRACE,
            RBRACE,
            LBRACKET,
            RBRACKET,
            SEMICOLON,
            COMMA,
            DOT,

            ASSIGN, GT, LT, BANG, OR, AND, PLUS, MINUS, STAR, SLASH, XOR, MOD,
            EQ, LE, GE, NE, INCR, DECR, BIT_AND, BIT_OR, LSHIFT, RSIGNEDSHIFT, RUNSIGNEDSHIFT, PLUSASSIGN, MINUSASSIGN,
            STARASSIGN, SLASHASSIGN, ANDASSIGN, ORASSIGN, XORASSIGN, MODASSIGN, LSHIFTASSIGN, RSIGNEDSHIFTASSIGN, RUNSIGNEDSHIFTASSIGN,
    });


    public static final Map<Integer, String> mapChar = new HashMap<>();


}
