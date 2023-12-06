import javax.swing.*;
public class app {
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(() -> {
            ModernLoadingScreen loadingScreen = new ModernLoadingScreen();
            loadingScreen.setVisible(true);
        });
    }
}
