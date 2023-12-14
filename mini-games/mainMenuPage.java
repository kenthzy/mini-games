import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class mainMenuPage extends JFrame implements ActionListener {


    mainMenuPage() {
        setTitle("MINI GAMES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        JPanel mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout
        mainPanel.setBackground(new Color(93, 56, 36, 255)); // Dark background color
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add empty border for spacing

        JLabel titleLabel = new JLabel("WELCOME TO OUR MINI GAMES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(234, 164, 100, 255));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH); // Add title to the top

        JPanel centerPanel = new JPanel(new GridLayout(8, 1, 4, 10)); // Use GridLayout with spacing
        centerPanel.setOpaque(false); // Make it transparent for the background color to show
        mainPanel.add(centerPanel, BorderLayout.CENTER); // Add the center panel

        JLabel infoLabel = new JLabel("Choose the game you want to play");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        infoLabel.setForeground(new Color(231, 209, 194, 255));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(infoLabel);

        RoundedCornerButton snakeButton = createStyledButton("Snake");
        RoundedCornerButton ticTacToeButton = createStyledButton("Tic-Tac-Toe");
        RoundedCornerButton pingPongButton = createStyledButton("Ping-Pong");
        RoundedCornerButton tetrisButton = createStyledButton("Tetris");
        RoundedCornerButton mineSweeper = createStyledButton("Minesweeper");
        RoundedCornerButton brickBreaker = createStyledButton("Brick-Breaker");
        RoundedCornerButton exitButton = createStyledButton("Exit");

        snakeButton.addActionListener(this);
        ticTacToeButton.addActionListener(this);
        pingPongButton.addActionListener(this);
        tetrisButton.addActionListener(this);
        mineSweeper.addActionListener(this);
        brickBreaker.addActionListener(this);
        exitButton.addActionListener(this);

        centerPanel.add(snakeButton);
        centerPanel.add(ticTacToeButton);
        centerPanel.add(pingPongButton);
        centerPanel.add(tetrisButton);
        centerPanel.add(mineSweeper);
        centerPanel.add(brickBreaker);
        centerPanel.add(exitButton);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private RoundedCornerButton createStyledButton(String label) {
        RoundedCornerButton button = new RoundedCornerButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(new Color(253, 153, 57));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMargin(new Insets(10, 20, 10, 20));
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch (choice) {
            case "Snake":
                int boardWidth = 600;
                int boardHeight = boardWidth;
                JFrame frame = new JFrame("Snake 3.0");
                frame.setVisible(true);
                frame.setSize(boardWidth, boardHeight);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);
                frame.add(snakeGame);
                frame.pack();
                snakeGame.requestFocus();
               break;

            case "Tic-Tac-Toe":
                TicTacToe ticTacToe = new TicTacToe();
                break;

            case "Ping-Pong":
                SwingUtilities.invokeLater(() -> {
                    Frame pongFrame = new Frame(0, 0, 1100, 600);
                    pongFrame.setBounds(200, 150, 1100, 638);
                    pongFrame.setVisible(true);
                });
                break;

            case "Tetris":
                JFrame f = new JFrame("Tetris");
                f.setSize(12 * 26 + 10, 26 * 23 + 25);
                f.setVisible(true);

                Tetris game = new Tetris();
                game.init();
                f.add(game);

                f.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent keyEvent) {
                    }

                    public void keyPressed(KeyEvent keyEvent) {
                        switch (keyEvent.getKeyCode()) { // Use keyEvent.getKeyCode() instead of e.getKeyCode()
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

                    public void keyReleased(KeyEvent keyEvent) {
                    }
                });

                new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            game.dropDown();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }).start();
                break;

            case "Minesweeper":
                Minesweeper minesweeper = new Minesweeper();
                break;

            case "Brick-Breaker":
                JFrame obj = new JFrame();
                GamePlay gameplay = new GamePlay();
                obj.setBounds(10,10,700,600);
                obj.setTitle("BrickBreaker");
                obj.setResizable(false);
                obj.setVisible(true);
                obj.add(gameplay);
                break;

            case "Exit":
                // Ask the user for confirmation
                int result = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to exit?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    // Exit the application
                    System.exit(0);
                }
                break;
        }
    }
}