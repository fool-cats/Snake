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
    int bodyPart = 6;
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
        this.setBackground(Color.black);
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
        //draw the grid
        for(int i = 0;i < SCREEN_HEIGHT/UNIT_SIZE;i++){
            //draw the grid
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);//x
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WINDTH, i*UNIT_SIZE);//y
        }
        //apple
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
        //draw the snake
        for(int i =0;i < bodyPart;i++){
            if(i == 0){
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            else{
                g.setColor(new Color(45,180,0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    public void NewApple(){
        appleX = random.nextInt((int)(SCREEN_WINDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_WINDTH/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move() {
        for(int i = bodyPart;i > 0;i--){
            x[i] = x[i] - 1;
            y[i] = y[i] - 1;
        }
        switch(direction){
            case 'U':
                 y[0] = y[0] - UNIT_SIZE;
                 break;
            case 'D':
                 y[0] = y[0] + UNIT_SIZE;
                 break;
            case 'R':
                 x[0] = x[0] + UNIT_SIZE;
                 break;
            case 'L':
                 x[0] = x[0] - UNIT_SIZE;
                 break;
        }
    }

    public void checkApple() {
        if((x[0] == appleX)&&(y[0] == appleY)){
            bodyPart++;
            appleEaten++;
            NewApple();
        }
    }

    public void checkCollisions() {
        //check if the head collides with body
        for(int i = bodyPart;i > 0;i--){
            if((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
            //check if head touched left border
            if(x[0] < 0){
                running = false;
            }
            //check if head touched right border
            if(x[0] > SCREEN_WINDTH){
                running = false;
            }
            //check if head touched top border
            if(y[0] < 0){
                running = false;
            }
            //check if head touched botton border
            if(y[0] > SCREEN_HEIGHT){
                running = false;
            }
            //Stop the Timer
            if(!running){
                timer.stop();
            }
            
        }
    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(running){
           move();
           checkApple();
           checkCollisions();
       }
       repaint();
    }
    //Control the snake
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                //limit the degree of direction
                if((direction != 'R')){
                    direction = 'L';
                }
                break;
                case KeyEvent.VK_RIGHT:
                //limit the degree of direction
                if((direction != 'L')){
                    direction = 'R';
                }
                break;
                case KeyEvent.VK_UP:
                //limit the degree of direction
                if((direction != 'D')){
                    direction = 'U';
                }
                break;
                case KeyEvent.VK_DOWN:
                //limit the degree of direction
                if((direction != 'U')){
                    direction = 'D';
                }
                break;
            }
        }
    }
}
