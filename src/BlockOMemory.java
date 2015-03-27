public class BlockOMemory {
	
	private long memorySize;
	private double processSize;
	private boolean haveProcess;
        private String processID;
	
	public BlockOMemory(long memorySize, long processSize, boolean haveProcess, String processID){
		if(!Functions.isPowerOfTwo(processSize))
		this.memorySize = memorySize;
		this.processSize = processSize;
		this.haveProcess = haveProcess;
                this.processID = processID;
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
         
}