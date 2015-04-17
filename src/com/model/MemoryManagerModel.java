
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import ImportantFunctions.Functions;

/**
 * The Memory Management allows a maximum and minimum memory size to be set Uses
 * the Memory buddy system idea to allocate, deallocate, and merge memory
 * 
 * @author Tyler, Edward, Justin, Ryan
 *
 */
public class MemoryManagerModel extends java.util.Observable {

	private static ArrayList<BlockOMemory> memoryBlocks;
	// Holds a list of process id names
	private static HashMap<Integer, Boolean> processIDs;
	// array of free memory available. Uses 2 ^ index for easy access
	private static int[] freeMemory;
	// minimum memory size allowed
	private long minMemorySize;
	// size of the total starting memory
	private long maxMemorySize;
	// no process value for BlockOMemory
	private static final int NO_PROCESS = -1;
	private static long usedMemory = 0;

	/**
	 * Constructor to create a new MemoryManager
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown if minMemorySize and/or maxMemorySize is not a power
	 *             of 2
	 * @param minMemorySize
	 * @param maxMemorySize
	 */
	public MemoryManagerModel(long minMemorySize, long maxMemorySize) {
		if (!Functions.isPowerOfTwo(minMemorySize)
				&& !Functions.isPowerOfTwo(maxMemorySize)) {
			throw new IllegalArgumentException(
					"The Min and Max Memory must be a power of 2");
		}
		this.minMemorySize = minMemorySize;
		this.maxMemorySize = maxMemorySize;

		processIDs = new HashMap<>();
		memoryBlocks = new ArrayList<>();
		// Add initial block of memory
		memoryBlocks.add(new BlockOMemory(maxMemorySize, 0, false, null,
				NO_PROCESS)); // null=No parent
		freeMemory = new int[Functions.log2(maxMemorySize) + 1];
		// Set freememory to maxMemory
		freeMemory[freeMemory.length - 1] = 1;
	}

	/**
	 * Sets the min memory size
	 * 
	 * @return minMemorySize
	 */
	public double getMinMemorySize() {
		return minMemorySize;
	}

	/**
	 * Sets the max memory size
	 * 
	 * @return maxMemorySize
	 */
	public double getMaxMemorySize() {
		return maxMemorySize;
	}

	/**
	 * This method allows memory to be allocated using the Buddy Memory System.
	 * A list of available memory slots is checked to see if any memory is
	 * available.
	 * 
	 * @param processSize
	 *            The process size to allocate.
	 * @param id
	 *            The ID that should be assigned to this process.
	 */
	public void allocateMemory(long processSize, int id) {
		System.out.println("Attempting to allocate memory of size "
				+ processSize);
		if (processSize < minMemorySize)
			displayMessage("Process size less than minimum size allowed.");
		else if (processSize > (maxMemorySize))
			displayMessage("Process size greater than maximum size allowed");
		else if (processIDs.containsKey(id))
			displayMessage("ID: #" + id + " already in use.");
		else {
			displayMessage("Allocate process with id = " + id + " and procSize = " + processSize); // MVC, notify all observers
			allocateMemoryHelper(processSize, id);
			setChanged();
		}
	}
	
	private void displayMessage(String msg)
	{
		setChanged();
		notifyObservers(msg);
	}

	private int findClosestPowerOf2(long processSize) {
		// finds power of 2 closest and >= processSize
		int x;
		if (Functions.isPowerOfTwo(processSize)) {
			x = Functions.log2(processSize);
		} else {
			x = Functions.log2(processSize) + 1;
		}
		return x;
	}

	/**
	 *
	 * A helper function that will allocate memory using the Buddy Memory
	 * System.
	 * 
	 * @author Edward Carter
	 * @param processSize
	 * @param id
	 */
	private void allocateMemoryHelper(long processSize, int id) {
		boolean noSpaceAvailable = false;
		boolean sizeFound = false;
		int x = findClosestPowerOf2(processSize);
		// uses that power of 2 to look in freememory array for available
		// space
		while (!sizeFound) {
			// finds closest available memory chunk size to what is needed
			if (Math.pow(2, x) > (maxMemorySize)) {
				noSpaceAvailable = true;
				break;
				// have exceeded maximum space
			} else if (freeMemory[x] > 0) {
				sizeFound = true;

			} else {
				x++;
			}
		}
		if (!noSpaceAvailable) {
			boolean memoryFound = false;
			int i = 0;
			// finds the closest chunk of memory size's index
			while (!memoryFound && i < memoryBlocks.size()) {
				if (memoryBlocks.get(i).getMemorySize() >= processSize
						&& !memoryBlocks.get(i).isProcess()
						&& memoryBlocks.get(i).getMemorySize() == Math
								.pow(2, x)) {
					if (processSize <= (memoryBlocks.get(i).getMemorySize() / 2)) {
						// Splits memory chunk in two if its large enough to
						// allocate the process
						long blockSize = memoryBlocks.get(i).getMemorySize();
						BlockOMemory parent = memoryBlocks.get(i); // the new
																	// parent
						// Remove the new parent from the array
						memoryBlocks.remove(parent);

						// Block1 has a process in it
						BlockOMemory block1 = new BlockOMemory((blockSize / 2),
								processSize, false, parent, NO_PROCESS);
						// Block 2 does not have a process in it
						BlockOMemory block2 = new BlockOMemory((blockSize / 2),
								processSize, false, parent, NO_PROCESS);
						// Add the blocks into our array
						memoryBlocks.add(i, block1);
						memoryBlocks.add(i + 1, block2);
						System.out.println("MEM BLOCKS = " + memoryBlocks);
						System.out.println("Split ran. Turned size "
								+ blockSize + " into two blocks of size "
								+ blockSize / 2);
						freeMemory[x] -= 1;
						x = x - 1;
						freeMemory[x] += 2;
						// if (blockSize / 2 ==
						// findClosestPowerOf2(processSize))
						// return; //we have a perfect fit, don't search anymore
					} else {
						// add in the new process
						usedMemory = usedMemory + memoryBlocks.get(i).getMemorySize();
						displayMessage("Progress update " + usedMemory);
						memoryFound = true;
						processIDs.put(id, true);
						memoryBlocks.get(i).setProcessSize(processSize);
						memoryBlocks.get(i).setIsProcess(true);
						memoryBlocks.get(i).setProcessID(id);
						freeMemory[x] -= 1;
						displayMessage("Memory of size " + processSize
								+ " added to block of size "
								+ memoryBlocks.get(i).getMemorySize());
					}
				} else {
					i++;
				}
			}
		} else {
			displayMessage("No Space available to add this process.");
		}
	}

	/**
	 * Deallocates memory by finding the process ID and marking it as free It
	 * will perform a merge if necessary
	 * 
	 * @param process
	 */
	public void deallocateMemory(int process) {
		displayMessage("Deallocate process # " + process);
		deallocateMemoryHelper(process);
	}

	/**
	 * Helper method that deallocates the memory by finding the given process id
	 * and changing block values
	 *
	 * @author Justin Hyland
	 * @param process
	 */
	private void deallocateMemoryHelper(int process) {
		boolean processRemoved = false;
		System.out.println("Attempting to deallocate process #" + process);
		// Traverse through every Block in memory and find the matching
		for (BlockOMemory x : memoryBlocks) {

			// if the process IDs match
			if (process == x.getProcessID()) {
				processRemoved = true;
				int i = Functions.log2(x.getMemorySize());
				freeMemory[i] += 1;
				usedMemory = usedMemory - x.getMemorySize();
				displayMessage("Progress update " + usedMemory);
				
				processIDs.remove(process);
				displayMessage("Process #" + process + " of size "
						+ x.getProcessSize() + " deallocated successfully.");
				x.setProcessSize(0);
				x.setIsProcess(false);
				x.setProcessID(NO_PROCESS);
				break;
			}// end if

		}// end for
		if (!processRemoved) {
			displayMessage("Process #" + process + " not found.");
		}

		mergeMemory();

	}// end deallocate

	/**
	 * Finds two chunks of memory with no processes and are buddies. Puts the
	 * two given chunks together to make a larger chunk using the parents as
	 * reference. This process can be thought of as checking the children of a
	 * parent node in a binary tree and if they are both free, to remove them
	 * both.
	 * 
	 * @author Ryan Smith
	 * @param blockIndex
	 */
	private void mergeMemory() {
		if (memoryBlocks.size() < 2)
			return; // Do not even attempt a merge
		for (int i = 0; i < memoryBlocks.size() - 1; i++) {
			BlockOMemory block = memoryBlocks.get(i);
			BlockOMemory possibleBuddy = memoryBlocks.get(i + 1);
			if (block.getParent() == possibleBuddy.getParent()
					&& !block.isProcess() && !possibleBuddy.isProcess()) {
				// They have the same parent and are both free, so coalesce!
				memoryBlocks.remove(block);
				memoryBlocks.remove(possibleBuddy);
				block.getParent().setProcessSize(0.0);
				memoryBlocks.add(i, block.getParent()); // insert the parent
														// block at the ith
														// location

				int j = Functions.log2(block.getMemorySize()); // get the index
																// in our
																// freeMemory
																// array for
																// this block
				freeMemory[j] = freeMemory[j] - 2; // there are 2 less blocks of
													// this size 2^j
				freeMemory[j + 1]++; // there is 1 more of this higher block
										// size 2^(j+1)

				// There might be a "chain" of coalescing that can be performed
				// So since we performed a merge, we shall call the method
				// again.
				mergeMemory();
			}
		}
	}
	
	/**
	 * Gets the list of memory blocks
	 * 
	 * @return ArrayList of memory blocks
	 */
	public ArrayList<BlockOMemory> getMemoryBlocks() {
		return memoryBlocks;
	}

	/**
	 * Gets the hash map of process IDs
	 * 
	 * @return map of process IDs
	 */
	public HashMap<Integer, Boolean> getProcessIDs() {
		return processIDs;
	}

	/**
	 * Get the array of free memory
	 * 
	 * @return free memory
	 */
	public int[] getFreeMemory() {
		return freeMemory;
	}

	/**
	 * ToString method
	 */
	@Override
	public String toString() {
		String s = "MemoryManager [minMemory=" + minMemorySize + ", maxMemory="
				+ maxMemorySize + ", FreeMemory={"
				+ Arrays.toString(freeMemory) + ",\nMemoryBlocks=\n{";
		int i = 0;
		for (BlockOMemory x : memoryBlocks) {
			s += "Index " + i + ": " + x + ",\n";
			i++;
		}
		s += "}]";
		return s;
	}
}
