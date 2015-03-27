import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
	BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	MemoryManager m = new MemoryManager(1, 64);
	m.AllocateMemory( 7, 5);
	m.deallocateMemory(5);
	}
}
