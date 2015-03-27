public class BlockOMemory {
	
	private long memorySize;
	private double processSize;
	private boolean haveProcess;
    private int processID;
	private int buddy; //the location in our array where this blocks buddy is located
	
	public BlockOMemory(long memorySize, long processSize, boolean haveProcess, int buddy, int processID){
		if(!Functions.isPowerOfTwo(processSize))
		this.memorySize = memorySize;
		this.processSize = processSize;
		this.haveProcess = haveProcess;
		this.processID = processID;
		this.buddy = buddy;
	}

	
	public int getBuddy()
	{
		return buddy;
	}
	
	public void setBuddy(int newBuddy)
	{
		this.buddy = newBuddy;
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
        
    public int getProcessID(){
        return processID;
    }
	
	public void setMemorySize(long l)
	{
		this.memorySize = l;
	}
	
	public void setProcessID(int id){
		this.processID = id;
	}
}