import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JPanel;
//method 2
/**
 * import java.awt.*
 * import java.awt.enent.*
 * these can replace those stuff 
 */

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WINDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    //calculate how many object cna fit in the screen
    static final int GAME_SIZE = (SCREEN_HEIGHT*SCREEN_WINDTH)/UNIT_SIZE;
    //timer
    static final int DELAY = 75;
    final int x[] = new int[GAME_SIZE];
    final int y[] = new int[GAME_SIZE];
    // initial body part
    int bodyPart = 3;
    int appleEaten;
    int appleX;
    int appleY;
    // the initial direction
    char direction = 'R';// It call be Right,Left and Up and Down
    boolean running = false;
    Timer timer;
    Random random;
    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WINDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        NewApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for(int i = 2;i < SCREEN_HEIGHT/UNIT_SIZE;i++){
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);//x
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WINDTH, i*UNIT_SIZE);//y
        }
    }

    public void NewApple(){
        appleX = random.nextInt((int)(SCREEN_WINDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_WINDTH/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move() {
        
    }

    public void checkApple() {

    }

    public void checkCollisions() {

    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent k) {
            
        }
    }
}
