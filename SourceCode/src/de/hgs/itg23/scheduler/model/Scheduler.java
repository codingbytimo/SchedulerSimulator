package de.hgs.itg23.scheduler.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import javax.security.auth.x500.X500Principal;
import javax.swing.JOptionPane;
import de.hgs.itg23.scheduler.gui.InputModel;
import de.hgs.itg23.scheduler.gui.OutputModel;
import de.hgs.itg23.scheduler.gui.View;

public class Scheduler {
	
	InputModel m = new InputModel();
	OutputModel o = new OutputModel(m);
	View v = new View();
	
	ProcessPrioComparator prioComparator = new ProcessPrioComparator();
	
	String[] pName = new String[m.getRowCount()];
	String[] pTime = new String[m.getRowCount()];
	int[] pPrio = new int[m.getRowCount()];
	ArrayList<Process> processes = m.getData();
	ArrayList<ArrayList<String>> processesOutput = o.getOutputList();
	private Process cacheProcess;
	ArrayList<Integer> timesList;
	ArrayList<String> waitTime;
	private boolean run = false;
	
	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

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
		this.run = true;
		processes = m.getData();
		processesOutput = o.getOutputList();
		while(run) {
			Collections.sort(processes, prioComparator); // Processes will be sorted from highest to lowest priority
			cacheProcess = processes.get(0);
			printArrays();
			if(cacheProcess.getTimesList().isEmpty()) {
				simOutput(cacheProcess, 2);
				cacheProcess.setState(ProcessState.FINISHED);
				if(processes.size() > 1) {
					processes.remove(0);
				}
				else {
					JOptionPane.showMessageDialog(null, "Alle Prozesse sind fertig.");
					v.getTextArea().setText("");
					m.setDefaultData();
					this.run = false;
				}
			}
			else {
				if(cacheProcess.getIsCalc()) {
					cacheProcess.setState(ProcessState.CALC);
					processesOutput.get(0).add("r");
					simOutput(cacheProcess, 0);
					if(cacheProcess.getpPrio() > 1) {
						cacheProcess.setpPrio(cacheProcess.getpPrio() - 2);
					}
					else {
						cacheProcess.setpPrio(0);
					}
					if(cacheProcess.getTimesList().get(0) > 5) {
						cacheProcess.getTimesList().set(0, cacheProcess.getTimesList().get(0) - 5);
					}
					else {
						cacheProcess.getTimesList().remove(0);
						cacheProcess.setState(ProcessState.BLOCKED);
						cacheProcess.setIsCalc(!cacheProcess.getIsCalc());
					}
				} 
				else {
					cacheProcess.setState(ProcessState.BLOCKED);
						simOutput(cacheProcess, 1);
						cacheProcess.getTimesList().remove(0);
						cacheProcess.setIsCalc(!cacheProcess.getIsCalc());
						cacheProcess.setState(ProcessState.WAITING);
				}
				for(int p = 1; p < processes.size(); p++) {
					if(!processes.get(p).getIsCalc()) {
						if(processes.get(p).getTimesList().isEmpty()) {
							simOutput(processes.get(p), 2);
							processes.get(p).setState(ProcessState.FINISHED);
							processes.remove(p);
						}
						else {
							simOutput(processes.get(p), 1);
							processesOutput.get(p).add("b");
							processes.get(p).getTimesList().remove(0);
							processes.get(p).setIsCalc(!processes.get(p).getIsCalc());
						}
					}
					else {
						simOutput(processes.get(p), 3);
						processesOutput.get(p).add("w");
					}
				}
				
			}
		}
		
		
	}
	
	public Scheduler(InputModel inputModel, OutputModel outputModel, View view) {
		m = inputModel;
		o = outputModel;
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
			v.getTextArea().append(process.getpName() + " rechnet fuer " + process.getTimesList().get(0) + " ZE..." + newline);
			break;
		case 1:
			v.getTextArea().append(process.getpName() + " ist blockiert fuer " + process.getTimesList().get(0) + " ZE..." + newline);
			break;
		case 2:
			v.getTextArea().append(process.getpName() + " ist komplett fertig." + newline);
			break;
		case 3:
			v.getTextArea().append(process.getpName() + " wartet." + newline);
			break;
		default:
			break;
		}
		
	}
	
	private void printArrays() {
		System.out.println("time List" + cacheProcess.getpName() + " : " + cacheProcess.getTimesList());
		System.out.println("----------");
		System.out.println(processesOutput);
		System.out.println("-----//-----");
	}

}
