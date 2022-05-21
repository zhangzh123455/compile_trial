package lexer;

public class Tag {
    public final static int
//            QM = '?' ,  LP = '(', RP = ')' ,LBK = '[' ,RBK = ']', POWER = '^',SEMICOLON = ';' , LB = '{', RB = '}' ,
//            ADD = '+', REDUCE = '-',MULTIPLY = '*',DIVISION = '/', AE = '+=', RE = '-=', ME = '*=', DE = '/=' ,CE = ':=',
//          LEXER_ERROR
//            SR = '--' ,SD = '++'
//            COMMA = ','
//            LS = '<<', RS = '>>'
            AND = 256, BASIC = 257, BREAK = 258, DO = 259, ELSE = 260, EQ= 261,
            FALSE = 262, GE= 263, ID= 264, IF = 265, INDEX = 266, LE= 267, MINUS = 268,
            NE= 269, NUM= 270, OR= 271, REAL= 272, TEMP = 273, TRUE = 274, WHILE = 275,

            QM = 276 ,  LP = 277, RP = 278 ,LBK = 279 ,RBK = 280, POWER = 281,SEMICOLON = 282,
            ADD = 283, REDUCE = 284,MULTIPLY = 285,DIVISION = 286, AE = 287, RE = 288, ME = 289, DE = 290 ,CE = 291,
                    LB = 292 , RB = 293,
                    LEXER_ERROR = 294 ,
            SR = 295, SA = 296,COMMA = 297,
            LS = 298, RS = 299,
            IN = 300, FOR = 301,
            FileEnd = 302;
}
