import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class RoundedCornerButton extends JButton {

    private static final int ARC_WIDTH = 15;
    private static final int ARC_HEIGHT = 15;

    public RoundedCornerButton(String label) {
        super(label);
        setContentAreaFilled(false); // Make the button transparent
        setFocusPainted(false); // Remove focus border
        setForeground(Color.WHITE); // Set text color
        setFont(new Font("Arial", Font.BOLD, 14)); // Set font and size
        setPreferredSize(new Dimension(300, 40)); // Set preferred size
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // Change the appearance of the button when clicked
            g.setColor(new Color(90, 90, 90)); // Set a darker color when clicked
        } else {
            g.setColor(getBackground());
        }

        // Draw a rounded rectangle shape for the button
        Graphics2D g2 = (Graphics2D) g.create();
        Dimension size = getSize();
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, size.width - 1, size.height - 1, ARC_WIDTH, ARC_HEIGHT);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(roundedRectangle);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Prevent the border from being painted
    }
}
