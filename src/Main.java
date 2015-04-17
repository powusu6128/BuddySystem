import javax.swing.UIManager;

import com.gui.StartScreen;

/**
 * Controller used to talk to GUI. In a Swing based GUI, the controller will
 * typically contain a JFrame.
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		new StartScreen();
	}
}
