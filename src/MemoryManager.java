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
	
	
	
		/**
     * This method allows memory to be allocated using the Memory Buddy System.
     * A list of available memory slots is checked to see if any memory is available.
     * 
     * @param size
     * @param processSize
     * @param isProcess
     * @param buddy
     * @param id 
     */
    private static void AllocateMemory(long size, long processSize, boolean isProcess, int buddy, int id)
    {
        boolean noSpaceAvailable = false;
        if (processSize < minMemorySize)
            System.out.println("Process size less than minimum size allowed.");
        else if (processSize > (maxMemorySize / 2))
            System.out.println("Process size greater than maximum size allowed");
        else
        {
            boolean rounding = true;
            int x = 0;
            boolean sizeFound = false;
            double closestAvailableMemorySize = 0;
            //finds power of 2 closest and >= processSize
            if (Functions.isPowerOfTwo(processSize))
            {
                x = Functions.logTwo(processSize);
            }
            else
            {
                x = Functions.logTwo(processSize) + 1;
            }
            //uses that power of 2 to look in freememory array for available space
            while (!sizeFound)
            {
                if (Math.pow(2, x) > (maxMemorySize / 2))
                {
                    noSpaceAvailable = true;
                    break;
                    //have exceeded maximum space
                }
                else if (freeMemory[x] > 0)
                {
                    sizeFound = true;
                }
                else
                {
                    x++;
                }
            }
            if (!noSpaceAvailable)
            {
                boolean memoryFound = false;
                int i = 0;
                while(!memoryFound)
                {
                  if (memoryBlocks.get(i).getSize() == processSize && !memoryBlocks.get(i).isProcess())
                    {
                        if (processSize < (memoryBlocks.get(i).getSize() / 2))
                        {
                            //Split and make babies!!!
                            int blockSize = memoryBlocks.get(i).getSize();
                            memoryBlocks.add(i+1, new BlockOMemory((blockSize/2),0,false,i,null));
                            memoryBlocks.get(i).setSize(blockSize/2);
                        }
                        else
                        {
                            memoryFound = true;
                            memoryBlocks.get(i).setProcessSize(processSize);
                            memoryBlocks.get(i).setIsProcess(true);
                            memoryBlocks.get(i).setProcessID(id);
                            freeMemory[x] = freeMemory[x] + 1;
                        }
                     }
                }
            }
            else
            {
                System.out.println("No Space available to add this process.");
            }
        }
    }


	
	
	
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
