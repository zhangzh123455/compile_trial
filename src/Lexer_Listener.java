import lexer.*;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;

public class Lexer_Listener implements ActionListener {
    Test test;
    JTextArea input = new JTextArea();
    JTextArea output = new JTextArea();
    public Lexer_Listener(Test test){
        this.test = test;
        this.input = test.input;
        this.output = test.output;
    }
    public void actionPerformed(ActionEvent e) {
        Lexer lex = new Lexer();
        String text = input.getText();
        lex.init(text);
        Token temp = null;
        boolean error = false;
        while (true) {
            try {
                temp = lex.scan(error);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(temp!=null &&temp.tag == Tag.FileEnd){
//                读到文件末尾 结束
                break;
            } else if (temp!=null && (temp.tag != Tag.LEXER_ERROR)) {
                error = false;
                System.out.println(temp);
                output.append(temp.toString());
                output.append("\r\n");
            } else{
//                错误放在了lexer的 error_list里
                error = true;
                output.append("error in line" + lex.line);
                output.append("\r\n");
//                System.out.println("error");
            }
        }


    }
}
