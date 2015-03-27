public class BlockOMemory {
	
	private long memorySize;
	private double processSize;
	private boolean haveProcess;
    private String processID;
	private int buddy; //the location in our array where this blocks buddy is located
	
	public BlockOMemory(long memorySize, long processSize, boolean haveProcess, String processID, int buddy){
		if(!Functions.isPowerOfTwo(processSize))
		this.memorySize = memorySize;
		this.processSize = processSize;
		this.haveProcess = haveProcess;
		this.processID = processID;
		this.buddy = buddy;
	}

	
	private int getBuddy()
	{
		return buddy;
	}
	
	private int setBuddy(int newBuddy)
	{
		buddy = newBuddy;
	}
	
	public double getProcessSize() {
		return processSize;
	}

	public void setProcessSize(double processSize) {
		this.processSize = processSize;
	}

	public boolean isProcess() {
		return haveProcess;
	}

	public void setIsProcess(boolean haveProcess) {
		this.haveProcess = haveProcess;
	}

	public long getMemorySize() {
		return memorySize;
	}
        
    public String getProcessID(){
        return processID;
    }
	
	public void setMemorySize(int newSize)
	{
		memorySize = newSize;
	}
	
	
}