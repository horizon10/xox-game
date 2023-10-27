import javax.annotation.processing.Messager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(800, 300, 500, 500);

        Font font1 = new Font("Arial", Font.BOLD, 15);
        Font font2 = new Font("Arial", Font.BOLD, 30);

        //jframe ayarları
        jFrame.getContentPane().setBackground(Color.PINK);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        ImageIcon icon = new ImageIcon("icons/529184.png");
        jFrame.setIconImage(icon.getImage());

        //butonları oluşturma
        JButton[] buttons = new JButton[9];

        int x = 0, y = 50, a = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i + a] = new JButton();
                buttons[i + a].setBackground(Color.ORANGE);
                buttons[i + a].setBounds(155 + x, 90 + y, 50, 50);
                jFrame.add(buttons[i + a]);
                x += 60;
                a++;
            }
            a -= 1;
            x = 0;
            y += 60;
        }

        //oyuncu adı ve puanları tanımı
        JLabel jLabel1 = new JLabel("Player_1");
        jLabel1.setFont(font1);
        jLabel1.setBounds(50, 50, 100, 30);
        jFrame.getContentPane().add(jLabel1);

        JLabel jLabel1Win = new JLabel("0");
        jLabel1Win.setFont(font1);
        jLabel1Win.setBounds(75, 90, 100, 30);
        jFrame.getContentPane().add(jLabel1Win);

        JLabel jLabel2 = new JLabel("Player_2");
        jLabel2.setFont(font1);
        jLabel2.setBounds(380, 50, 100, 30);
        jFrame.getContentPane().add(jLabel2);

        JLabel jLabel2Win = new JLabel("0");
        jLabel2Win.setFont(font1);
        jLabel2Win.setBounds(405, 90, 100, 30);
        jFrame.getContentPane().add(jLabel2Win);



        final int[][] sira = {{0}};

        final int[] puan1 = {0};
        final int[] puan2 = {0};
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (sira[0][0] % 2 == 0) {
                        buttons[finalI].setText("x");
                    } else {
                        buttons[finalI].setText("o");
                    }
                    if (sira[0][0] >= 3) {

                        int result=winner(buttons);
                        if(result==1){
                            JOptionPane.showMessageDialog(jFrame, "Player_1 winner");
                            fresh(buttons, sira[0]);
                            puan1[0]++;
                            jLabel1Win.setText(String.valueOf(puan1[0]));
                            return;
                        }
                        else if(result==2){
                            JOptionPane.showMessageDialog(jFrame, "Player_2 winner");
                            fresh(buttons, sira[0]);
                            puan2[0]++;
                            jLabel2Win.setText(String.valueOf(puan2[0]));
                            return;
                        } else if (sira[0][0]==8) {
                            JOptionPane.showMessageDialog(jFrame, "Berabere");
                            fresh(buttons, sira[0]);
                            return;
                        }
                    }
                        sira[0][0]++;


                }
            });
        }



        jFrame.setLayout(null);
        jFrame.setVisible(true);

    }
    //yeni tur için temizleme
    public static void fresh(JButton[] buttons,int[] sira){
        for (int i=0;i< buttons.length;i++){
            buttons[i].setText("");
        }
        for (int i=0;i< sira.length;i++){
            sira[i]=0;
        }
    }
    //kazanıp kazanmadığını anlama
    public static int winner(JButton[] buttons) {

        for (int i = 0; i < 7; i += 3) {
            if (buttons[i].getText() == buttons[i + 1].getText() && buttons[i].getText() == buttons[i + 2].getText()) {
                if (buttons[i].getText() == "x") {
                    return 1;
                } else if(buttons[i].getText() == "o"){
                    return 2;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText() == buttons[i + 3].getText() && buttons[i].getText() == buttons[i + 6].getText()) {
                if (buttons[i].getText() == "x") {
                    return 1;
                } else if(buttons[i].getText() == "o"){
                    return 2;
                }
            }
        }
        for (int i = 0; i < 3; i += 2) {
            if (i == 0) {
                if (buttons[i].getText() == buttons[i + 4].getText() && buttons[i].getText() == buttons[i + 8].getText()) {
                    if (buttons[i].getText() == "x") {
                        return 1;
                    } else if(buttons[i].getText() == "o"){
                        return 2;
                    }
                }
            } else if (buttons[i].getText() == buttons[i + 2].getText() && buttons[i].getText() == buttons[i + 4].getText()){
                if (buttons[i].getText() == "x") {
                    return 1;
                } else if(buttons[i].getText() == "o"){
                    return 2;
                }
            }
        }
        return 0;
    }
}


