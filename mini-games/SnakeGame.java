import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    //snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    //food
    Tile food;
    Random random;
    Color foodColor; // Store the food color

    //game logic
    int velocityX;
    int velocityY;
    Timer gameLoop;

    boolean gameOver = false;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(new Color(9, 0, 0, 255));
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 1;
        velocityY = 0;

        //game timer
        gameLoop = new Timer(200, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Food (circle)
        g.setColor(foodColor);
        int foodSize = tileSize / 2; // Radius of the circle
        g.fillOval(food.x * tileSize + tileSize / 4, food.y * tileSize + tileSize / 4, foodSize, foodSize);

        // Snake Head
        g.setColor(new Color(229, 4, 4, 255)); // Set the outline color to green
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize); // Draw a filled square at the head's position

        // Snake Body
        boolean isGlowing = true;
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            if (isGlowing) {
                g.setColor(new Color(43, 241, 241, 244)); // Glowing color
            } else {
                g.setColor(Color.white);
            }
            g.fillOval(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize); // Draw a filled circle at the snake part's position
            isGlowing = !isGlowing; // Toggle between glowing and black
        }
        g.setFont(new Font( "Arial", Font.BOLD, 25));
        FontMetrics fontMetrics = g.getFontMetrics();

        String scoreText = "Score: " + snakeBody.size();
        int scoreTextWidth = fontMetrics.stringWidth(scoreText);
        int scoreTextX = (boardWidth - scoreTextWidth) / 2;
        int scoreTextY = tileSize;

        g.setColor(Color.white);
        g.drawString(scoreText, scoreTextX, scoreTextY);

        // Game Over
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            fontMetrics = g.getFontMetrics();

            String gameOverText = "Game Over";
            int gameOverTextWidth = fontMetrics.stringWidth(gameOverText);
            int gameOverTextX = (boardWidth - gameOverTextWidth) / 2;
            int gameOverTextY = (boardHeight - fontMetrics.getHeight()) / 2;

            g.setColor(Color.red);
            g.drawString(gameOverText, gameOverTextX, gameOverTextY);

        }
    }




    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize);
        food.y = random.nextInt(boardHeight / tileSize);
        foodColor = getRandomColor(); // Generate a random color for the food
    }
    private Color getRandomColor() {
        Random rand = new Random();
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        return new Color(red, green, blue);
    }

    public Color move() {
        //eat food
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }


        //move snake body
        for (int i = snakeBody.size()-1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) { //right before the head
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        //move snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //game over conditions
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            //collide with snake head
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > boardWidth || //passed left border or right border
                snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > boardHeight ) { //passed top border or bottom border
            gameOver = true;
        }
        return foodColor;
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) { //called every x milliseconds by gameLoop timer
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("KeyEvent: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    //not needed
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}