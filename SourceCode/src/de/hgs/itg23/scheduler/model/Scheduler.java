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
		printProccessList(processes);
		Collections.sort(processes, prioComparator);
		printProccessList(processes);
	}
	
	public void splitTime() {
		
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
