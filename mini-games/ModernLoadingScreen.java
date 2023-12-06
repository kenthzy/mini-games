import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModernLoadingScreen extends JFrame {

    public ModernLoadingScreen() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mini Games");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
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

        RoundedCornerButton startButton = new RoundedCornerButton("START THE GAME");
        startButton.setBackground(new Color(97, 146, 196, 252));
        startButton.setForeground(new Color(204, 108, 11, 255)); // Set the foreground color to yellow
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions on button click
                SwingUtilities.invokeLater(() -> {
                    new WelcomePage();
                    dispose();
                });
                // Insert code to start loading process or transition to the next screen
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }
}
