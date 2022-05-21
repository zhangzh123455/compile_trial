package lexer;

import java.io.*;
import java.util.*;
// 还差一个 float越界
public class Lexer {
    public static int line = 1;
    char peek = ' ';
    Hashtable words = new Hashtable();
    List error_list = new ArrayList();
    String filePath = "F:\\JAVAprofiles\\compile_trial\\src\\lexer\\word_data";
//    boolean is_end = false;
    File inFile;
    InputStream input;
    Reader r;
    boolean fileEnd = false;
    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    public void  init(){

        try {
            r = new FileReader(filePath);
            fileEnd = false;
//            System.out.println("sadasd");
//            input = new FileInputStream((inFile));
        }catch (java.io.FileNotFoundException e){
            System.out.println("无文件");
            e.printStackTrace();
        }
    }
    public void  init(String s){
        r  = new StringReader(s);
        fileEnd = false;
    }
    public Lexer() {
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(new Word("in", Tag.IN));
        reserve(new Word("for", Tag.FOR));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);

//        把那些 标识符加进去 有点多后面再写


    }

    void readch() throws IOException {
//        peek = (char) System.in.read();
//        peek = (char) input.read();
        peek = (char) r.read();
        if((int)peek == 65535){
            fileEnd = true;
        }

//        字母都改成小写
        if(Character.isLetter(peek)){
            peek = Character.toLowerCase(peek);
        }
    }

    boolean readch(char c) throws IOException {
        readch();
        if (peek != c) return false;
        peek = ' ';
        return true;
    }
    void get_error() {
//        把行数加进去,然后scan返回null 当做错误 然后line+1
        System.out.println("error in line" + line);
        error_list.add(new Integer(line));
    }

    public Token scan(boolean error) throws IOException {

        boolean is_int=false,is_id=false,is_float=false,get_first=false,is_string=false;
//        错误 需要换下行
        if(error){
            while(peek!='\n'){
                readch();
            }
        }
        for (; ; readch()) {
            if (peek == ' ' || peek == '\t') continue;
            else if (peek == '\n'||(int)peek ==13) {
//                java 换行有可能是 两个\r\n
                if(peek == '\n')
                line = line + 1;
                continue;}
            else break;
        }
        switch (peek) {
            case '&':
                if (readch('&')) return Word.and;
                else return new Token('&');
            case '|':
                if (readch('|')) return Word.or;
                else return new Token('|');
            case '=':
                if (readch('=')) return Word.eq;
                else return new Token('=');
            case '!':
                if (readch('=')) return Word.ne;
                else return new Token('!');
            case '<':
                if (readch('=')) return Word.le;
                else if(peek == '<') {peek = ' '; return Word.ls;}
                else return new Token('<');
            case '>':
                if (readch('=')) return Word.ge;
                else if(peek == '>') {peek = ' '; return Word.rs;}
                else return new Token('>');

            case '+':
                if (readch('=')) return Word.ae;
                else if(peek == '+'){peek = ' '; return Word.sa; }
                else return Word.add;
            case '-':
                if (readch('=')) return Word.re;
                else if(peek == '-'){peek = ' '; return Word.sr; }
                else return Word.reduce;
            case '*':
                if (readch('=')) return Word.me;
                else return Word.multiply;
            case '/':
                if (readch('=')) return Word.de;
                else return Word.division;

            case ':':
                if (readch('=')) return Word.ce;
                else {
                    get_error();
                    return Word.lexer_error;
                }
            case '?':
                peek = ' ';
                return Word.qm;
            case '(':
                peek = ' ';
                return Word.lp;
            case ')':
                peek = ' ';
                return Word.rp;
            case '[':
                peek = ' ';
                return Word.lbk;
            case ']':
                peek = ' ';
                return Word.rbk;
            case '{':
                peek = ' ';
                return Word.lb;
            case '}':
                peek = ' ';
                return Word.rb;
            case '^':
                peek = ' ';
                return Word.power;
            case ';':
                peek = ' ';
                return Word.semicolon;
            case ',':
                peek = ' ';
                return Word.comma;
        }
        if (Character.isDigit(peek)) {
            float v = 0;

            if(peek=='0'){    //三种情况 0.555    0x555  05555
                readch();

                if(Character.isDigit(peek)){// 处理八进制
                    do {
                        v = 8 * v + Character.digit(peek, 10);
                        readch();
                    } while (Character.isDigit(peek));
//                    if(peek == '.'){
//                        get_error();
//                        return Word.lexer_error;
//                    }
                    return new Num((int)v);
                }

                if(peek == 'x'){// 处理十六进制
                    readch();
                    do {
                        v = 16 * v + Character.digit(peek, 10);
                        readch();
                    } while (Character.isDigit(peek));
                    if(peek == '.'){
                        get_error();
                        return Word.lexer_error;
                    }
                    return new Num((int)v);
                }

                if(peek == '.'){// 小数0.5555
                    float x = v;
                    float d = 10;
                    for (; ; ) {
                        readch();
                        if (!Character.isDigit(peek)) break;

//                解决 132.
                        if(!is_float) is_float = true;
                        x = x + Character.digit(peek, 10) / d;
                        d = d * 10;
                    }
                    if(is_float)
                        return new Real(x);
                    else{
                        get_error();
                        if(peek!='\n')
                            peek = ' ';
                        return Word.lexer_error;
                    }
                }
            }

            do {   //获取整数部分
                v = 10 * v + Character.digit(peek, 10);
                readch();
            } while (Character.isDigit(peek));
            if (peek != '.') {
                if(v<=2147483648.0)
                    return new Num((int)v);
                else{
                    get_error();
                    if(peek!='\n')
                        peek = ' ';
                    return Word.lexer_error;
                }
            }


            float x = v;
            float d = 10;
            for (; ; ) {
                readch();
                if (!Character.isDigit(peek)) break;

//                解决 132.
                if(!is_float)
                    is_float = true;
//
                x = x + Character.digit(peek, 10) / d;
                d = d * 10;
            }
            if(is_float && x<Float.POSITIVE_INFINITY && x>Float.NEGATIVE_INFINITY) {
                return new Real(x);
            }
            else{
                get_error();
                if(peek!='\n')
                    peek = ' ';
                return Word.lexer_error;
            }
        }
        if (Character.isLetter(peek)||peek=='_') {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek));
            String s = b.toString();
            Word w = (Word) words.get(s);
            if (w != null) return w;
            w = new Word(s, Tag.ID);
            words.put(s, w);
            return w;
        }

        if((int)peek==65535){
//            读到文件末尾
            return Word.fileEnd;
        }
        get_error();
        if(peek!='\n')
            peek = ' ';
        return Word.lexer_error;
    }
}
