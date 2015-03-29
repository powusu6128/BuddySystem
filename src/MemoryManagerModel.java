import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The Memory Management allows a maximium and minium memory size to be set Uses
 * the Memory buddy system idea to allocate, deallocate, and merge memory
 * 
 * @author Tyler, Edward, Justin, Ryan
 *
 */
public class MemoryManager {

	private static ArrayList<BlockOMemory> memoryBlocks;
	// Holds a list of process id names
	private static HashMap<Integer, Boolean> processIDs;
	// array of free memory available. Uses 2 ^ index for easy access
	private static int[] freeMemory;
	// minimum memory size allowed
	private static long minMemorySize;
	// size of the total starting memory
	private static long maxMemorySize;
	// no buddy value for BlockOMemory
	private static final int NO_BUDDY = -1;
	// no process value for BlockOMemory
	private static final int NO_PROCESS = -1;

	/**
	 * Constructor to create a new MemoryManager
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown if minMemorySize and/or maxMemorySize is not a power
	 *             of 2
	 * @param minMemorySize
	 * @param maxMemorySize
	 */
	public MemoryManager(long minMemorySize, long maxMemorySize) {
		if (!Functions.isPowerOfTwo(minMemorySize)
				&& !Functions.isPowerOfTwo(maxMemorySize)) {
			throw new IllegalArgumentException(
					"The Min and Max Memory must be a power of 2");
		}
		MemoryManager.minMemorySize = minMemorySize;
		MemoryManager.maxMemorySize = maxMemorySize;
		processIDs = new HashMap<>();
		memoryBlocks = new ArrayList<>();
		// Add initial block of memory
		memoryBlocks.add(new BlockOMemory(64, 0, false, NO_BUDDY, NO_PROCESS));
		freeMemory = new int[Functions.log2(maxMemorySize) + 1];
		// Set freememory to maxMemory
		freeMemory[freeMemory.length - 1] = 1;
	}

	/**
	 * Sets the min memory size
	 * 
	 * @return minMemorySize
	 */
	public static double getMinMemorySize() {
		return minMemorySize;
	}

	/**
	 * Sets the max memory size
	 * 
	 * @return maxMemorySize
	 */
	public static double getMaxMemorySize() {
		return maxMemorySize;
	}

	/**
	 * This method allows memory to be allocated using the Memory Buddy System.
	 * A list of available memory slots is checked to see if any memory is
	 * available.
	 *
	 * @author Edward Carter
	 * @param processSize
	 * @param id
	 */
	public void allocateMemory(long processSize, int id) {
		System.out.println("Attempting to allocate memory of size "
				+ processSize);
		boolean noSpaceAvailable = false;
		if (processSize < minMemorySize)
			System.out.println("Process size less than minimum size allowed.");
		else if (processSize > (maxMemorySize / 2))
			System.out
					.println("Process size greater than maximum size allowed");
		else if (processIDs.containsKey(id))
			System.out.println("ID: #" + id + " already in use.");
		else {
			processIDs.put(id, true);
			int x = 0;
			boolean sizeFound = false;
			// finds power of 2 closest and >= processSize
			if (Functions.isPowerOfTwo(processSize)) {
				x = Functions.log2(processSize);
			} else {
				x = Functions.log2(processSize) + 1;
			}
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
							&& memoryBlocks.get(i).getMemorySize() == Math.pow(
									2, x)) {
						if (processSize <= (memoryBlocks.get(i).getMemorySize() / 2)) {
							// Splits memory chunk in two if its large enough to
							// allocate the process
							long blockSize = memoryBlocks.get(i)
									.getMemorySize();
							memoryBlocks.add(i + 1, new BlockOMemory(
									(blockSize / 2), 0, false, i, NO_PROCESS));
							memoryBlocks.get(i).setMemorySize(blockSize / 2);
							memoryBlocks.get(i).setBuddy(i + 1);
							System.out.println("Split ran. Turned size "
									+ blockSize + " into two blocks of size "
									+ blockSize / 2);
							freeMemory[x] -= 1;
							x = x - 1;
							freeMemory[x] += 2;

						} else {
							// add in the new process
							memoryFound = true;
							memoryBlocks.get(i).setProcessSize(processSize);
							memoryBlocks.get(i).setIsProcess(true);
							memoryBlocks.get(i).setProcessID(id);
							freeMemory[x] -= 1;
							System.out.println("Memory of size " + processSize
									+ " added to block of size "
									+ memoryBlocks.get(i).getMemorySize());
						}
					} else {
						i++;
					}
				}
			} else {
				System.out.println("No Space available to add this process.");
			}
		}
	}

	/**
	 * Deallocates the memory by finding the given process id and changing block
	 * values
	 *
	 * @author Justin Hyland
	 * @param process
	 */
	public void deallocateMemory(int process) {
		boolean processRemoved = false;
		System.out.println("Attempting to deallocate process #" + process);
		// Traverse through every Block in memory and find the matching
		for (BlockOMemory x : memoryBlocks) {

			// if the process IDs match
			if (process == x.getProcessID()) {
				processRemoved = true;
				int i = Functions.log2(x.getMemorySize());
				freeMemory[i] += 1;
				processIDs.remove(process);
				System.out.println("Process #" + process + " of size "
						+ x.getProcessSize() + " deallocated successfully.");
				x.setProcessSize(0);
				x.setIsProcess(false);
				x.setProcessID(NO_PROCESS);
				break;
			}// end if

		}// end for
		if (!processRemoved) {
			System.out.println("Process #" + process + " not found.");
		}

		mergeMemory(0);

	}// end deallocate

	/**
	 * Finds two chunks of memory with no processes and are buddies. Puts the
	 * two given chunks together to make a larger chunk.
	 * 
	 * @author Ryan Smith
	 * @param blockIndex
	 */
	private void mergeMemory(int blockIndex) {
		boolean mergeDone = false;
		while (blockIndex < memoryBlocks.size() - 1 && !mergeDone) {
			// We will iterate through the array and check if the block and it's
			// neighbor are "buddies", and both free (isProcess() == false).
			// If so, we will merge them into 1 big block.
			// If we do a merge, we will run this method again, in case there
			// needs to be more merges performed.
			BlockOMemory leftPiece = memoryBlocks.get(blockIndex);
			BlockOMemory rightPiece = memoryBlocks.get(blockIndex + 1);
			// Check if these processes are buddies
			if (leftPiece.getMemorySize() == rightPiece.getMemorySize()
					&& leftPiece.getBuddy() == blockIndex + 1
					&& rightPiece.getBuddy() == blockIndex
					&& !leftPiece.isProcess() && !rightPiece.isProcess()) {
				// We know these two are buddies, so we must now perform a merge
				int j = Functions.log2(memoryBlocks.get(blockIndex)
						.getMemorySize());
				leftPiece.setMemorySize(leftPiece.getMemorySize() * 2); // coalesce
																		// the
																		// blocks
																		// into
																		// one
																		// big
																		// piece
				memoryBlocks.remove(blockIndex + 1);
				freeMemory[j] -= 2;
				freeMemory[j + 1] += 1;
				System.out.println("Two blocks of size "
						+ leftPiece.getMemorySize() / 2
						+ " combined into one block of size "
						+ leftPiece.getMemorySize());
				// Update links
				if (memoryBlocks.size() == 1)
					leftPiece.setBuddy(NO_BUDDY);
				mergeDone = true;
				mergeMemory(blockIndex); // Run through the process again
			}
			blockIndex++;
		}
	}

	/**
	 * Gets the list of memory blocks
	 * 
	 * @return ArrayList of memory blocks
	 */
	public static ArrayList<BlockOMemory> getMemoryBlocks() {
		return memoryBlocks;
	}

	/**
	 * Gets the hash map of process IDs
	 * 
	 * @return map of process IDs
	 */
	public static HashMap<Integer, Boolean> getProcessIDs() {
		return processIDs;
	}

	/**
	 * Get the array of free memory
	 * 
	 * @return free memory
	 */
	public static int[] getFreeMemory() {
		return freeMemory;
	}

	/**
	 * ToString method
	 */
	@Override
	public String toString() {
		String s = "MemoryManage [minMemory=" + minMemorySize + ", maxMemory="
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
