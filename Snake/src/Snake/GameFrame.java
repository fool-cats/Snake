

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        /*
        * GamePanel panel = new GamePanel()
        * this.add(panel);
        * */
    this.add(new GamePanel());//this is also a shortcut
    this.setTitle("Snake");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.pack();//add all components to Jframe
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    }
}
