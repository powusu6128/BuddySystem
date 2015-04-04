import com.gui.StartScreen;

/**
 * Controller used to talk to GUI. In a Swing based GUI, the controller will
 * typically contain a JFrame.
 *
 */
public class Main {

	public static void main(String[] args) {
		// BufferedReader r = new BufferedReader(new
		// InputStreamReader(System.in));
		// MemoryManagerModel m = new MemoryManagerModel(1, 64);
		// m.allocateMemory(32, 1);
		// m.allocateMemory(16, 2);
		// m.allocateMemory(8, 3);
		// m.allocateMemory(8, 4);
		// m.deallocateMemory(4);
		// // m.deallocateMemory(3);
		// // m.allocateMemory(7, 1);
		// // m.allocateMemory(7, 2);
		// // m.allocateMemory(7, 3);
		// System.out.println("Printing array of memory.");
		// System.out.println(m);
		StartScreen f = new StartScreen();
		// f.setVisible(true);
		// AllocateDialog a = new AllocateDialog(new JFrame());
		// a.setVisible(true);
		// DeallocateDialog d = new DeallocateDialog(new JFrame());
		// d.setVisible(true);
		// MainObserver m = new MainObserver(new MemoryManagerModel(0, 64),
		// true);
	}
}
