package de.hgs.itg23.scheduler.model;

public class Process {
	
	private String pName;
	private int pWaitTime;
	private int pCalcTime;
	
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpWaitTime() {
		return pWaitTime;
	}

	public void setpWaitTime(int pWaitTime) {
		this.pWaitTime = pWaitTime;
	}

	public int getpCalcTime() {
		return pCalcTime;
	}

	public void setpCalcTime(int pCalcTime) {
		this.pCalcTime = pCalcTime;
	}

	public Process(String pName, int pWaitTime, int pCalcTime) {
		this.pName = pName;
		this.pWaitTime = pWaitTime;
		this.pCalcTime = pCalcTime;
	}
	
}
