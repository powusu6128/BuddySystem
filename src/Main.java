import javax.swing.UIManager;

import com.gui.StartScreen;

/**
 * Main File to be ran upon execution.
 * Uses the Nimbus look and feel.
 *
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager
            .setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // If Nimbus is not available, default will be used
        }
        new StartScreen();
    }
}
