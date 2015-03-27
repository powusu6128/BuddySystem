import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Controller used to talk to GUI
 *
 */
public class Main {

	public static void main(String[] args) {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		MemoryManager m = new MemoryManager(1, 64);
		m.allocateMemory(7, 5);
		m.allocateMemory(7, 5);
		m.allocateMemory(7, 6);
		m.allocateMemory(30, 11);
		m.deallocateMemory(5);
		m.deallocateMemory(5);
		m.deallocateMemory(11);
		m.deallocateMemory(6);
		m.allocateMemory(65, 3);
		m.allocateMemory(32, 4);
		m.allocateMemory(16, 9);
		m.allocateMemory(32, 56);
		m.allocateMemory(2, 89);
		m.deallocateMemory(89);
		System.out.println("Printing array of memory.");
		System.out.println(m);
	}
}
