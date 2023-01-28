package de.hgs.itg23.scheduler.model;

public class Process {
	
	private String pName;
	private String pTime;
	private int pPrio;
	private ProcessState pState;
	
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
		if(pPrio >= 12) {
			this.pPrio = 12;
		}
		else if (pPrio <= 1) {
			this.pPrio = 1;
		}
		else {
			this.pPrio = pPrio;
		}
	}
	
	public ProcessState getState() {
		return pState;
	}

	public Process(String pName, String pTime, int pPrio) {
		this.pName = pName;
		this.pTime = pTime;
		this.pPrio = pPrio;
		this.pState = ProcessState.waiting;
	}
	
}
