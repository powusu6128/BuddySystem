package com.model;

/*
  * This Driver class creates allocation calls and deallocation calls randomly, with random process
 *  IDs and sizes
 */
/**
 *
 * @author Justin Hyland
 */
import java.util.HashMap;
import java.util.Random;

import com.model.MemoryManagerModel;

import javax.swing.Timer;

/**
 *
 * This Driver class creates allocation calls and deallocation calls randomly, with random process
 *  IDs and sizes
 */
public class Driver {

    private final Random random;

    private Timer timer;
    private MemoryManagerModel memoryManager;
    private int procIDCounter = 0;
    

    public Driver(MemoryManagerModel memory) {

        random = new Random();
        memoryManager = memory;
        //Start with allocating 32
      

    }//end constructor

    /**
     * Calls random allocation and deallocation
     * Returns: None
     */
    public void drive() {

        HashMap<Integer, Boolean> processIDs;

        processIDs = memoryManager.getProcessIDs();
        if (memoryManager.getMaxMemorySize() - memoryManager.getUsedMemory() <= 8)
     	  {
     		  doDeallocate();
     		  return;
     	  }
      if (processIDs.size() < 3)
      {
    	  doAllocate();
      }
      else
      {
    	  //Randomly allocate or deallocate
    	  if (processIDs.size() > 8 || random.nextInt(100) < 30)
    	  {
    		  //Deallocate if more than 8 processes or 30% of the time
    		  doDeallocate();
    	  }
    	  else if (random.nextInt(100) < 50)
    	  {
    		  doAllocate();
    	  }
    	  else
    		  allocatePair();
      }
    }
    
    /*
     * Attempts to find a random free memory block and allocate from there rather than completely randomly
     * This makes the visualization a little nicer
     */
    private void allocatePair()
    {
   	  int freeMemory[] = memoryManager.getFreeMemory();
   	  int randIndex = random.nextInt(freeMemory.length); //rand int from [0, length)
   	 int i = randIndex;
   	 while (freeMemory[i] <= 0)
   	 {
   		 i++;
   		 if (i >= freeMemory.length)
   			 i = 0;
   	 }
   	 
   	 //Can we split the block? If so, let's try
   	 int blockSize = (int) Math.pow(2, i);
   	 if (blockSize / 2 > 2)
   		 blockSize = blockSize / 2;
   	  
   		 memoryManager.allocateMemory((long)blockSize, ++procIDCounter);
    }
    
      private void doAllocate()
      {
    	  if (random.nextInt(100) > 30) //50% of the time do it this way
    		  allocatePair();
    	  else //50% of the time allocate completely randomly
    	  { 
    		  int blockSize = random.nextInt(2) + 4;
    		  memoryManager.allocateMemory(blockSize, ++procIDCounter);
    	  }
      }
      
      public void doDeallocate()
      {
    	  //Pick a random process to deallocate
    	  int randIndex = random.nextInt(memoryManager.getMemoryBlocks().size());
    	  int procID = memoryManager.getMemoryBlocks().get(randIndex).getProcessID();
    	  
    	  //Find somewhere that is a process
    	  while (!memoryManager.getMemoryBlocks().get(randIndex).isProcess())
    	  {
    		  randIndex++;
    		  if (randIndex >= memoryManager.getMemoryBlocks().size())
    			  randIndex = 0;
    	  }
    	  memoryManager.deallocateMemory(procID);
      }

}