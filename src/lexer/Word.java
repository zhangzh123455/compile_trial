package lexer;

public class Word extends Token {
    public String lexeme = "";
    public Word(String s, int tag) {super(tag); lexeme = s;}
    public String toString() {
        if(super.tag == Tag.ID)
            return "<id,"+lexeme+'>';
        else
            return  "<"+lexeme+'>';
    }
    public static final Word
        and = new Word("&&", Tag.AND), or =new Word("||", Tag.OR),
        eq = new Word("==", Tag.EQ), ne =new Word("!=", Tag.NE),
        le = new Word("<=>", Tag.LE), ge =new Word(">=", Tag.GE),
        minus = new Word("minus", Tag.MINUS),
        True = new Word("true", Tag.TRUE),
        False = new Word("false", Tag.FALSE),

        qm = new Word("?", Tag.QM),
        lp = new Word("(", Tag.LP),
        rp = new Word(")", Tag.RP),
        lbk = new Word("[", Tag.LBK),
        rbk = new Word("]", Tag.RBK),
        lb = new Word("{", Tag.LB),
        rb = new Word("}", Tag.RB),
        power = new Word("^", Tag.POWER),
        semicolon = new Word(";", Tag.SEMICOLON),
        add = new Word("+", Tag.ADD),
        reduce = new Word("-", Tag.REDUCE),
        multiply = new Word("*", Tag.MULTIPLY),
        division = new Word("?", Tag.DIVISION),
        ae = new Word("+=", Tag.AE),
        re = new Word("-=", Tag.RE),
        me = new Word("*=", Tag.ME),
        de = new Word("/=", Tag.DE),
        ce = new Word(":=", Tag.CE),
        lexer_error = new Word("lexer_error", Tag.LEXER_ERROR),
        sr = new Word("--", Tag.SR),
        sa = new Word("++", Tag.SA),
        comma = new Word(",", Tag.COMMA),
        ls = new Word(",", Tag.LS),
        rs = new Word(",", Tag.RS),
                fileEnd = new Word(",", Tag.FileEnd);
}
