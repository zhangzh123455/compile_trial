package lexer;
public class Type extends Word {
    public int width = 0;
    public Type(String s, int tag, int v) { super(s, tag); width = v; }

    @Override
    public String toString() {
        return "<"+super.lexeme+">";
    }

    public static final Type
        Int = new Type("int", Tag.BASIC, 4),
        Float = new Type("float", Tag.BASIC, 8),
        Char = new Type("char", Tag.BASIC, 1),
        Bool = new Type("bool", Tag.BASIC, 1);
}
