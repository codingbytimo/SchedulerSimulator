package de.hgs.itg23.scheduler.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;
import de.hgs.itg23.scheduler.gui.Model;
import de.hgs.itg23.scheduler.gui.View;

public class Scheduler {
	
	Model m = new Model();
	View v = new View();
	
	ProcessPrioComparator prioComparator = new ProcessPrioComparator();
	
	String[] pName = new String[m.getRowCount()];
	String[] pTime = new String[m.getRowCount()];
	int[] pPrio = new int[m.getRowCount()];
	ArrayList<Process> processes = m.getData();
	private Process cacheProcess;
	ArrayList<Integer> timesList;
	ArrayList<String> waitTime;
	private final static String newline = "\n";
	
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
	
	public Process getCacheProcess() {
		return cacheProcess;
	}

	public void startScheduling() {
		processes = m.getData();
		Collections.sort(processes, prioComparator); // Processes will be sorted from highest to lowest priority
		cacheProcess = processes.get(0);
		printArrays();
		if(cacheProcess.getpPrio() > 1) {
			cacheProcess.setState(ProcessState.CALC);
			simOutput(cacheProcess, 0);
			if(cacheProcess.getTimesList().get(0) > 0) {
				cacheProcess.getTimesList().set(0, cacheProcess.getTimesList().get(0) - 1);
			}
			else {
				cacheProcess.getTimesList().remove(0);
			}
			cacheProcess.setpPrio(cacheProcess.getpPrio() - 2);
			cacheProcess.setState(ProcessState.WAITING);
			simOutput(cacheProcess, 1);
		}
		else { // if all processes have the priority 1, this will fire
			simOutput(cacheProcess, 2);
			cacheProcess.setpPrio(cacheProcess.getpPrio() - 1);
			cacheProcess.setState(ProcessState.FINISHED);
			if(processes.size() > 1) {
				m.deleteRow(0);
			}
			else {
				JOptionPane.showMessageDialog(null, "Alle Prozesse sind fertig.");
				v.getTextArea().setText("");
				m.setDefaultData();
			}
		}
	}
	
	public Scheduler(Model model, View view) {
		m = model;
		v = view;
	}
	
	public void printProccessList(ArrayList<Process> pList) {
		for(Process process : pList) {
			System.out.println(process);
		}
	}
	
	private void simOutput(Process process, int output) {
		switch (output) {
		case 0:
			v.getTextArea().append(process.getpName() + " rechnet fuer " + cacheProcess.getTimesList().get(0) + " ZE..." + newline);
			break;
		case 1:
			v.getTextArea().append(process.getpName() + " hat fertig gerechnet." + newline);
			break;
		case 2:
			v.getTextArea().append(process.getpName() + " ist komplett fertig." + newline);
			break;
		default:
			break;
		}
		/*if(cacheProcess.getCalcIndex() < times.size()-1) {
			cacheProcess.setCalcIndex(cacheProcess.getCalcIndex() + 1);
		}
		else {
			cacheProcess.setCalcIndex(0);
		}
		if(cacheProcess.getWaitIndex() < waitTime.size()-1) {
			cacheProcess.setWaitIndex(cacheProcess.getWaitIndex() + 1);
		}
		else {
			cacheProcess.setWaitIndex(0);
		}*/
	}
	
	private void printArrays() {
		//System.out.println("singleTime Array: " + Arrays.toString(singleTime));
		System.out.println("time List: " + cacheProcess.getTimesList());
		//System.out.println("waitTime List: " + waitTime);
		System.out.println("----------");
	}
}
