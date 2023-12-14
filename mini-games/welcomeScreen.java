import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class welcomeScreen extends JFrame {
    private JProgressBar progressBar;
    private Timer timer;
    private JPanel panel;
    private JPanel progressPanel;
    private JPanel buttonPanel;

    public welcomeScreen() {
        initComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            welcomeScreen loadingScreen = new welcomeScreen();
            loadingScreen.setVisible(true);
        });
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mini Games");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel(new BorderLayout());

        // Load the image from file
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\acer\\IdeaProjects\\mini-games\\logo-java-2048.png");
        Image originalImage = originalIcon.getImage();

        int width = 200; // Adjust according to the desired width
        int height = 200; // Adjust according to the desired height
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(width, height));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.NORTH);

        panel.add(imagePanel, BorderLayout.NORTH);

        progressPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        RoundedCornerButton startButton = new RoundedCornerButton("START THE GAME");
        startButton.setBackground(new Color(237, 125, 19, 255));
        startButton.setForeground(new Color(255, 255, 255, 255)); // Set the foreground color to yellow
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar = new JProgressBar(0, 100);
                progressBar.setStringPainted(true);

                int progressBarWidth = 320; // Adjust according to the desired width
                int progressBarHeight = 15; // Adjust according to the desired height
                progressBar.setPreferredSize(new Dimension(progressBarWidth, progressBarHeight));

                // Customizing the size of the progress panel
                int progressPanelWidth = progressBarWidth + 10; // Adjust according to the desired width
                int progressPanelHeight = progressBarHeight + 10; // Adjust according to the desired height
                progressPanel.setPreferredSize(new Dimension(progressPanelWidth, progressPanelHeight));
                progressPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Set FlowLayout

                progressBar.setForeground(new Color(239, 126, 19, 255)); // Set the foreground color
                progressBar.setBackground(new Color(255, 255, 255, 0)); // Set the background color

                progressPanel.add(progressBar); // Add the progress bar to the progress panel

                panel.remove(buttonPanel); // Remove the button panel
                panel.add(progressPanel, BorderLayout.PAGE_END); // Add the progress panel to the center of the panel

                getContentPane().revalidate(); // Revalidate the content pane
                getContentPane().repaint(); // Repaint the content pane
                startLoading(); // Start the loading process
            }
        });
        buttonPanel.add(startButton); // Add button to the button panel
        panel.add(buttonPanel, BorderLayout.CENTER); // Add button panel initially

        getContentPane().add(panel);
    }

    private void startLoading() {
        timer = new Timer(50, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress >= 100) {
                    timer.stop();
                    transitionToMainMenu();
                } else {
                    progress++;
                    progressBar.setValue(progress);
                    progressBar.repaint(); // Force repaint
                    panel.revalidate(); // Revalidate the panel
                    panel.repaint();
                }
            }
        });
        timer.start();
    }


    private void transitionToMainMenu() {
        SwingUtilities.invokeLater(() -> {
          new mainMenuPage();
            dispose();
        });
    }
}
