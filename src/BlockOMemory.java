public class BlockOMemory {
	
	private long memorySize;
	private double processSize;
	private boolean haveProcess;
	
	public BlockOMemory(long memorySize, long processSize, boolean haveProcess){
		if(!Functions.isPowerOfTwo(processSize))
		this.memorySize = memorySize;
		this.processSize = processSize;
		this.haveProcess = haveProcess;
	}

	public double getProcessSize() {
		return processSize;
	}

	public void setProcessSize(double processSize) {
		this.processSize = processSize;
	}

	public boolean isHaveProcess() {
		return haveProcess;
	}

	public void setHaveProcess(boolean haveProcess) {
		this.haveProcess = haveProcess;
	}

	public long getMemorySize() {
		return memorySize;
	}
}