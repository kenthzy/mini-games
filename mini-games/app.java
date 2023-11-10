import javax.swing.*;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class app {
    public static void main(String[] args) throws Exception {
        Scanner r = new Scanner(System.in);
        welcomePage welcomeInstance = new welcomePage();

        welcomeInstance.welcome();

        int choice = r.nextInt();
        switch (choice) {
            case 1 -> {
                // Code to start the Snake game
                int boardWidth = 600;
                int boardHeight = boardWidth;
                JFrame frame = new JFrame("Snake 3.0");
                frame.setVisible(true);
                frame.setSize(boardWidth, boardHeight);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);
                frame.add(snakeGame);
                frame.pack();
                snakeGame.requestFocus();
            }
            case 2 -> {
                TicTacToe ticTacToe = new TicTacToe();
            }
            case 3 -> {
                SwingUtilities.invokeLater(() -> {
                    Frame pongFrame = new Frame(0, 0, 1100, 600);
                    pongFrame.setBounds(200, 150, 1100, 638);
                    pongFrame.setVisible(true);
                });
            }

            case 4 -> {
                JFrame f = new JFrame("Tetris");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(12 * 26 + 10, 26 * 23 + 25);
                f.setVisible(true);

                Tetris game = new Tetris();
                game.init();
                f.add(game);

                // Keyboard controls
                f.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent e) {
                    }

                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                game.rotate(-1);
                                break;
                            case KeyEvent.VK_DOWN:
                                game.rotate(+1);
                                break;
                            case KeyEvent.VK_LEFT:
                                game.move(-1);
                                break;
                            case KeyEvent.VK_RIGHT:
                                game.move(+1);
                                break;
                            case KeyEvent.VK_SPACE:
                                game.dropDown();
                                game.score += 1;
                                break;
                        }
                    }

                    public void keyReleased(KeyEvent e) {
                    }
                });

                // Make the falling piece drop every second
                new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            game.dropDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            default -> {
                System.out.println(" ");
                System.out.println("Invalid choice. Please choose a number between 1 and 4.");
            }
        }
    }
}
