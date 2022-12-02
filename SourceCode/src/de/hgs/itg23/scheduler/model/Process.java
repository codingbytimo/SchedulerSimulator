package de.hgs.itg23.scheduler.model;

public class Process {
	
	private int pWaitTime;
	private int pCalcTime;
	private int pID;
	
	public Process(int pWaitTime, int pCalcTime, int pID) {
		this.pWaitTime = pWaitTime;
		this.pCalcTime = pCalcTime;
		this.pID = pID;
	}
	
}
