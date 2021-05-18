
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT = 25;
    static final int GAME_UNIT = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT;
    // delay for our time
    static final int DELAY = 75;
    // the body of snake including head
    final int x[] = new int[GAME_UNIT];
    final int y[] = new int[GAME_UNIT];
    int bodyParts = 6;
    int appleEaten;// initial in 0
    int appleX;
    int appleY;
    // default direction
    char direction = 'R';
    // the begin with game
    boolean running = false;
    Timer timer;
    // Random genrate a apple
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));// the sizes of the game panel
        this.setBackground(Color.black);
        this.setFocusable(true);// focus ability
        this.addKeyListener(new MyKeyAdapter());//
        startGame();
    }

    public void startGame() {
        // Once we start the gmae ,We want to call the newApple() to genrate a apple
        newApple();
        running = true;
        // Starting timing,
        timer = new Timer(DELAY, this);// using this keyword because we the action listener interface
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT; i++) {
                g.drawLine(i * UNIT, 0, i * UNIT, SCREEN_HEIGHT);// column
                g.drawLine(0, i * UNIT, SCREEN_WIDTH, i * UNIT);// row
            }
            g.setColor(Color.RED);// apple color
            g.fillOval(appleX, appleY, UNIT, UNIT);// fill the apple in the grid and the unit is the size of the apple

            // draw the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT, UNIT);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    //random color
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT, UNIT);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metric = getFontMetrics(g.getFont());
            g.drawString("Score: " + appleEaten, (SCREEN_WIDTH - metric.stringWidth("Score: " + appleEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);// g is our graphics
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT)) * UNIT;// "SCREEN_WIDTH / UNIT"the bound of apple ,ensure
                                                                    // the apple in the midle of grid
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT)) * UNIT;
    }

    public void move() {
        // for loop to iterate the body part of snake
        for (int i = bodyParts; i > 0; i--) {
            // shifting the body
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        // x[0] and y[0] is the head of snake
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT;
                break;
            case 'D':
                y[0] = y[0] + UNIT;
                break;
            case 'L':
                x[0] = x[0] - UNIT;
                break;
            case 'R':
                x[0] = x[0] + UNIT;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            appleEaten++;
            newApple();
        }
    }

    public void checkCollsions() {
        // check if the head collide with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // check if head touch left boreder
        if (x[0] < 0) {
            running = false;
        }
        // check if head touch Right boreder
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        // check if head touch top boreder
        if (y[0] < 0) {
            running = false;
        }
        // check if head touch bottom boreder
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        //Score
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metric1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + appleEaten, (SCREEN_WIDTH - metric1.stringWidth("Score: " + appleEaten)) / 2, g.getFont().getSize());
        // Game over text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metric = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metric.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollsions();
        }
        // if the game is no logner running , call the repaint()
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {// KeyAdapter is a abstract class
        @Override
        public void keyPressed(KeyEvent e) { // Invoked when a key has been pressed.
            switch (e.getKeyCode()) {
                // We don't wnat turn 180 degree
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
