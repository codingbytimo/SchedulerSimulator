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
	private String cacheTime;
	String[] singleTime;
	ArrayList<String> calcTime;
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
		splitTime(cacheProcess);
		if(processes.get(0).getpPrio() > 1) {
			cacheProcess.setState(ProcessState.CALCRUNNING);
			simOutput(cacheProcess, 0);
			cacheProcess.setpPrio(cacheProcess.getpPrio() - 2);
			cacheProcess.setState(ProcessState.CALCREADY);
			simOutput(cacheProcess, 1);
		}
		else { // if all processes have the priority 1, this will fire
			simOutput(cacheProcess, 2);
			cacheProcess.setState(ProcessState.FINISHED);
			if(processes.size() > 1) {
				m.deleteRow(0);
			}
			else {
				JOptionPane.showMessageDialog(null, "Fertig.");
				v.getTextArea().setText("");
				m.setDefaultData();
			}
		}
	}
	
	public void splitTime(Process p) {
		cacheTime = p.getpTime();
		singleTime = cacheTime.split(";");
		calcTime = new ArrayList<>();
		waitTime = new ArrayList<>();
		for(int i = 0; i < singleTime.length; i = i+2) {
			calcTime.add(singleTime[i]);
		}
		for(int i = 1; i < singleTime.length; i = i+2) {
			waitTime.add(singleTime[i]);
		}
		printArrays();
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
			v.getTextArea().append(process.getpName() + " rechnet jetzt..." + newline);
			break;
		case 1:
			v.getTextArea().append(process.getpName() + " hat fertig gerechnet und wartet." + newline);
			break;
		case 2:
			v.getTextArea().append(process.getpName() + " ist komplett fertig." + newline);
			break;
		default:
			break;
		}
	}
	
	private void printArrays() {
		System.out.println("singleTime Array: " + Arrays.toString(singleTime));
		System.out.println("calcTime List: " + calcTime);
		System.out.println("waitTime List: " + waitTime);
		System.out.println("----------");
	}
}
