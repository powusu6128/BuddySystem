import java.util.ArrayList;

public class MemoryManager {
	
	static ArrayList<BlockOMemory> memoryBlocks;
	static double minMemorySize;
	static double maxMemorySize;
	static int processID = 0;
	
	public MemoryManager(int minMemorySize, int maxMemorySize){
		MemoryManager.minMemorySize = minMemorySize;
		MemoryManager.maxMemorySize = maxMemorySize;
	}

	public static double getMinMemorySize() {
		return minMemorySize;
	}

	public static double getMaxMemorySize() {
		return maxMemorySize;
	}
	
	public void allocateMemory(BlockOMemory process){
		
	}
	
	public void deallocateMemory(BlockOMemory process){
		
	}
	
	public void mergeMemory(BlockOMemory process1, BlockOMemory process2){
		
	}
}
