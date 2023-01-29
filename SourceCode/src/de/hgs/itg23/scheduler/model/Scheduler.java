package de.hgs.itg23.scheduler.model;

import java.util.ArrayList;
import java.util.Collections;
import de.hgs.itg23.scheduler.gui.Model;

public class Scheduler {
	
	Model m = new Model();
	
	ProcessPrioComparator prioComparator = new ProcessPrioComparator();
	
	String[] pName = new String[m.getRowCount()];
	String[] pTime = new String[m.getRowCount()];
	int[] pPrio = new int[m.getRowCount()];
	ArrayList<Process> processes = m.getData();
	private Process cacheProcess;
	private String cacheTime;
	String[] singleTime;
	
	public String[] getName() {
		for(int i = 0; i <= m.getRowCount(); i++) {
			pName[i] = (String)m.getValueAt(0, i);
		}
		return pName;
	}
	
	public String[] getTime() {
		for(int i = 0; i <= m.getRowCount(); i++) {
			pTime[i] = (String)m.getValueAt(1, i);
		}
		return pTime;
	}
	
	public int[] getPrio() {
		for(int i = 0; i <= m.getRowCount(); i++) {
			pPrio[i] = (Integer)m.getValueAt(2, i);
		}
		return pPrio;
	}
	
	public void startScheduling() {
		processes = m.getData();
		Collections.sort(processes, prioComparator); // Processes will be sorted from highest to lowest priority
		cacheProcess = processes.get(0);
		if(processes.get(0).getpPrio() > 1) {
			System.out.println("------------");
			cacheProcess.setState(ProcessState.calcRunning);
			cacheProcess.setpPrio(cacheProcess.getpPrio() - 2);
			cacheProcess.setState(ProcessState.calcReady);
		}
		else { // if all processes have the priority 1, this will fire
			System.out.println("Alles EINS");
		}
	}
	
	public void splitTime(Process p) {
		cacheTime = p.getpTime();
		singleTime = cacheTime.split(";");
	}
	
	public Scheduler(Model model) {
		m = model;
	}
	
	public void printProccessList(ArrayList<Process> pList) {
		for(Process process : pList) {
			System.out.println(process);
		}
	}
}
