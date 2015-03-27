import java.util.ArrayList;

public class MemoryManager {
	
	static ArrayList<BlockOMemory> memoryBlocks;
	static int[] freeMemory;
	static long minMemorySize;
	static long maxMemorySize;
	static int processID = 0;
	
	public MemoryManager(long minMemorySize, long maxMemorySize){
		if(!Functions.isPowerOfTwo(minMemorySize) && !Functions.isPowerOfTwo(maxMemorySize)){
			throw new IllegalArgumentException("The Min and Max Memory must be a power of 2");
		}
		MemoryManager.minMemorySize = minMemorySize;
		MemoryManager.maxMemorySize = maxMemorySize;
		freeMemory = new int[Functions.log2(maxMemorySize)];
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
