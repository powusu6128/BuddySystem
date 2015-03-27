import java.util.ArrayList;

public class MemoryManager {
	
	static ArrayList<BlockOMemory> memoryBlocks;
	static int[] freeMemory;
	static long minMemorySize;
	static long maxMemorySize;
	static int processID = 0;
	public static final int NO_BUDDY = -1;
	
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
		/**
     * deallocate - This method will
     *
     * @param thing process or something that will be stored in memory
     */

        //Traverse through every Block in memory and find the matching 
        for (BlockOMemory x : memoryBlocks) {

            //if the process IDs match
            if (process.getProcessID() == x.getProcessID()) {

                x.setProcessSize(0);
                x.setIsProcess(false);

            }//end if

        }//end for

        mergeMemory();

    }//end deallocate
	
	
	private void mergeMemory()
	{
		for (int i = 0; i<memoryBlocks.size() - 1; i++)
		{
			//We will iterate through the array and check if the block and it's neighbor are "buddies", and both free (isProcess() == false).
			//If so, we will merge them into 1 big block.
			//If we do a merge, we will run this method again, in case there needs to be more merges performed.
			BlockOMemory leftPiece = memoryBlocks.get(i);
			BlockOMemory rightPiece = memoryBlocks.get(i+1)
			//Check if these processes are buddies
			if (leftPiece.getMemorySize() == rightPiece.getMemorySize() && leftPiece.buddy == i+1 && rightPiece.buddy==i && leftPiece.isProcess() && rightPiece.isProcess())
			{
				//We know these two are buddies, so we must now perform a merge
				leftPiece.setMemorySize(leftPiece.getMemorySize() * 2); //coalesce the blocks into one big piece
				memoryBlocks.remove(i+1);
				//Update links
				leftPiece.setBuddy(NO_BUDDY);
				doMerge(); //Run through the process again
			}
		}
}
}
