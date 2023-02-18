package de.hgs.itg23.scheduler.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Process {
	
	private String pName;
	private String pTime;
	private int pPrio;
	private ProcessState pState;
	private String cacheTime;
	private boolean isCalc = true;

	String[] singleTime;
	int[] timesArray;
	ArrayList<Integer> cacheTimesList;
	ArrayList<Integer> timesList;
	
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
	
	public boolean getIsCalc() {
		return isCalc;
	}

	public void setIsCalc(boolean pBool) {
		this.isCalc = pBool;
	}
	
	public ProcessState getState() {
		return pState;
	}
	
	public void setState(ProcessState state) {
		pState = state;
	}
	
	public void calculate() {
		if(this.timesList.get(0) != 0) {
			this.timesList.set(0, this.timesList.get(0) - 1);
			this.pState = ProcessState.CALC;
		}
		else {
			this.timesList.remove(0);
			setpPrio(pPrio - 2);
			this.pState = ProcessState.WAITING;
		}
	}
	
	public void beBlocked() {
		if(this.timesList.get(0) != 0) {
			this.timesList.set(0, this.timesList.get(0) - 1);
			this.pState = ProcessState.BLOCKED;
		}
		else {
			this.timesList.remove(0);
			setpPrio(pPrio - 2);
			this.pState = ProcessState.WAITING;
		}
	}
	
	public ArrayList<Integer> splitTime() {
		cacheTime = getpTime();
		singleTime = cacheTime.split(";");
		timesArray = new int[singleTime.length];
		int index = 0;
		for(int i = 0; i < singleTime.length; i++)
		{
		    try
		    {
		        timesArray[index] = Integer.parseInt(singleTime[i]);
		        index++;
		    }
		    catch (NumberFormatException nfe)
		    {
		        //Do nothing or you could print error if you want
		    	System.out.println("Wrong input for process time!");
		    }
		}
		// Now there will be a number of 'invalid' elements 
		// at the end which will need to be trimmed
		timesArray = Arrays.copyOf(timesArray, index);
		
		cacheTimesList = new ArrayList<Integer>(timesArray.length);
		for(int k : timesArray) {
			cacheTimesList.add(k);
		}
		
		return cacheTimesList;
		
		//timesArray = Arrays.stream(cacheTime.split(";")).mapToInt(Integer::parseInt).toArray();
		/*waitTime = new ArrayList<>();
		for(int i = 0; i < singleTime.length; i = i+2) {
			times.add(singleTime[i]);
		}
		for(int i = 1; i < singleTime.length; i = i+2) {
			waitTime.add(singleTime[i]);
		}*/
	}

	public ArrayList<Integer> getTimesList() {
		return timesList;
	}

	public void setTimesList(ArrayList<Integer> timesList) {
		this.timesList = timesList;
	}

	public Process(String pName, String pTime, int pPrio) {
		this.pName = pName;
		this.pTime = pTime;
		this.pPrio = pPrio;
		this.timesList = splitTime();
		this.pState = ProcessState.WAITING;
	}
	
}
