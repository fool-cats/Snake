import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GameFrame(){
        //method 1
        // GamePanel panel = new GamePanel();
        //add the panel into the frame
        //this.add(panel);
        //method 2 shrotcut
        this.add(new GamePanel());
        //set the title of this game
        setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.pack() take our Jframe and fit it snugly around all of compent
        this.pack();
        //It can be visible
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
