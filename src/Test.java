import java.io.IOException;
import javax.swing.*;
import lexer.*;

public class Test extends JFrame{
    public Test(){
        init();
        setVisible(true);   // 显示窗体
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口设置
    }
    JTabbedPane p = new JTabbedPane(JTabbedPane.TOP);
    JPanel lexer = new JPanel();
    JPanel parser = new JPanel();
    JTextArea input, output;
    JButton lex_button;
    void init(){
        lex_button = new JButton("词法分析");
        input = new JTextArea(20,70);
        output = new JTextArea(20,70);
        lexer.add(new JLabel("input: "));
        lexer.add(new JScrollPane(input));
        lexer.add(new JLabel("output:"));
        lexer.add(new JScrollPane(output));
        lexer.add(lex_button);
        p.add("词法分析", lexer);
        p.add("语法分析", parser);
        Lexer_Listener lexer_listener = new Lexer_Listener(this);
        lex_button.addActionListener(lexer_listener);
        // 设置窗体大小
        setSize(800, 800);
        // 窗体居中显示
        setLocationRelativeTo(null);
        add(p);
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.setTitle("lexer");
//        Lexer lex = new Lexer();
//        lex.init();
//        Token temp = null;
//        boolean error = false;
//        while (true) {
//            temp = lex.scan(error);
//            if(temp!=null &&temp.tag == Tag.FileEnd){
////                读到文件末尾 结束
//                break;
//            } else if (temp!=null && (temp.tag != Tag.LEXER_ERROR)) {
//                error = false;
//                System.out.println(temp);
//            } else{
////                错误放在了lexer的 error_list里
//                error = true;
////                System.out.println("error");
//            }
//        }
    }
}
