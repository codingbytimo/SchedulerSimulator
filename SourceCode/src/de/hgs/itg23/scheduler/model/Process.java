package de.hgs.itg23.scheduler.model;

public class Process {
	
	private String pName;
	private String pTime;
	private int pPrio;
	private ProcessState pState;
	/*private int waitIndex = 0;
	private int calcIndex = 0;
	
	public int getWaitIndex() {
		return waitIndex;
	}

	public void setWaitIndex(int waitIndex) {
		this.waitIndex = waitIndex;
	}

	public int getCalcIndex() {
		return calcIndex;
	}

	public void setCalcIndex(int calcIndex) {
		this.calcIndex = calcIndex;
	}*/
	
	public String getpName() {
		return pName;
	}
	
	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrio() {
		return pPrio;
	}

	public String getpTime() {
		return pTime;
	}

	public void setpTime(String pTime) {
		this.pTime = pTime;
	}

	public void setpPrio(int pPrio) {
		if(pPrio >= 15) {
			this.pPrio = 15;
		}
		else if (pPrio <= 0) {
			this.pPrio = 0;
		}
		else {
			this.pPrio = pPrio;
		}
	}
	
	public ProcessState getState() {
		return pState;
	}
	
	public void setState(ProcessState state) {
		pState = state;
	}

	public Process(String pName, String pTime, int pPrio) {
		this.pName = pName;
		this.pTime = pTime;
		this.pPrio = pPrio;
		this.pState = ProcessState.CALCREADY;
	}
	
}
