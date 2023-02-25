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
	ArrayList<String> tickStates;
	
	// Prozess Konstruktor
	public Process(String pName, String pTime, int pPrio) {
		this.pName = pName;
		this.pTime = pTime;
		this.pPrio = pPrio;
		this.timesList = splitTime();
		this.tickStates = new ArrayList<>();
		this.pState = ProcessState.WAITING;
	}
	
	// Methode zum seperieren der einzelnen Zeiten
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
		        // Falls keine Zahl als Zeit angegeben wurde
		    	System.out.println("Wrong input for process time!");
		    }
		}
		
		timesArray = Arrays.copyOf(timesArray, index);
		
		cacheTimesList = new ArrayList<Integer>(timesArray.length);
		for(int k : timesArray) {
			cacheTimesList.add(k);
		}
		
		return cacheTimesList;
	}
	
	// Getter und Setter Methoden

	public ArrayList<Integer> getTimesList() {
		return timesList;
	}

	public void setTimesList(ArrayList<Integer> timesList) {
		this.timesList = timesList;
	}

	public ArrayList<String> getTickStates() {
		return tickStates;
	}

	public void setTickStates(ArrayList<String> tickStates) {
		this.tickStates = tickStates;
	}
	
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
	
}
