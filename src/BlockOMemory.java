/**
 * Represents each process' needed memory for the {@link MemoryManagerModel}
 * 
 * @author Tyler Andjel
 *
 */
public class BlockOMemory {

	// The needed memory size for a process
	private long memorySize;
	// The process memory size
	private double processSize;
	// Tells whether a block of memory has a process in it
	private boolean haveProcess;
	// The process id
	private int processID;
	// the location in our array where this blocks buddy is located
	private int buddy;

	/**
	 * The constructor to create a block of memory
	 * 
	 * @param memorySize
	 *            The size of memory being created
	 * @param processSize
	 *            The size of memory for the process
	 * @param haveProcess
	 *            Tells if the block of memory has a process
	 * @param buddy
	 *            The location of the memories buddy
	 * @param processID
	 *            The processes' ID
	 */
	public BlockOMemory(long memorySize, long processSize, boolean haveProcess,
			int buddy, int processID) {
		this.memorySize = memorySize;
		this.processSize = processSize;
		this.haveProcess = haveProcess;
		this.processID = processID;
		this.buddy = buddy;
	}

	/**
	 * Returns the buddy location
	 * 
	 * @return location of the buddy
	 */
	public int getBuddy() {
		return buddy;
	}

	/**
	 * Sets the location of the block's buddy
	 * 
	 * @param newBuddy
	 *            The location of the new buddy
	 */
	public void setBuddy(int newBuddy) {
		this.buddy = newBuddy;
	}

	/**
	 * Gets the process size
	 * 
	 * @return the process size
	 */
	public double getProcessSize() {
		return processSize;
	}

	/**
	 * Sets the process size
	 * 
	 * @param processSize
	 *            the process size
	 */
	public void setProcessSize(double processSize) {
		this.processSize = processSize;
	}

	/**
	 * Tells if a block of memory has a process
	 * 
	 * @return True if the block of memory has a process otherwise false
	 */
	public boolean isProcess() {
		return haveProcess;
	}

	/**
	 * Sets the block of memory to have a process
	 * 
	 * @param haveProcess
	 *            true if a block has a process otherwise false
	 */
	public void setIsProcess(boolean haveProcess) {
		this.haveProcess = haveProcess;
	}

	/**
	 * Gets the blocks memory size
	 * 
	 * @return The blocks memory size
	 */
	public long getMemorySize() {
		return memorySize;
	}

	/**
	 * Gets the blocks process ID
	 * 
	 * @return The blocks process ID
	 */
	public int getProcessID() {
		return processID;
	}

	/**
	 * Sets the memory size
	 * 
	 * @param size
	 *            The memory size
	 */
	public void setMemorySize(long size) {
		this.memorySize = size;
	}

	/**
	 * Sets the process ID
	 * 
	 * @param id
	 *            The id.
	 */
	public void setProcessID(int id) {
		this.processID = id;
	}

	@Override
	/**
	 * toString method
	 */
	public String toString() {
		return "BlockOMemory [memorySize=" + memorySize + ", processSize="
				+ processSize + ", haveProcess=" + haveProcess + ", processID="
				+ processID + ", buddy=" + buddy + "]";
	}
}