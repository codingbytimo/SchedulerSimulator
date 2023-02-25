package de.hgs.itg23.scheduler.model;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

import de.hgs.itg23.scheduler.gui.InputModel;
import de.hgs.itg23.scheduler.gui.View;

public class Scheduler {
	
	InputModel m = new InputModel();
	View v = new View();
	ProcessPrioComparator prioComparator = new ProcessPrioComparator();
	
	String[] pName = new String[m.getRowCount()];
	String[] pTime = new String[m.getRowCount()];
	int[] pPrio = new int[m.getRowCount()];
	ArrayList<Process> processes = m.getData();
	ArrayList<ArrayList<String>> processesOutput;
	private Process cacheProcess;
	ArrayList<Integer> timesList;
	ArrayList<String> waitTime;
	private boolean run = false;

	private final static String newline = "\n";
	
	public Process getCacheProcess() {
		return cacheProcess;
	}

	public void startScheduling() {
		v.getTextArea().setText("");
		v.getTextAreaStateOutput().setText("");
		v.getTextAreaStateOutput().setText("Legende: r = rechnen; b = blockiert; w = warten");
		v.getTextAreaStateOutput().append(newline);
		v.getTextAreaStateOutput().append(newline);
		v.getTextArea().append("-----/Takt 0/-----");
		v.getTextArea().append(newline);
		int tick = 1;
		this.run = true;
		processes = m.getData();
		processesOutput = new ArrayList<>(processes.size());
		int processGetterEval = processes.size();
		int processGetter = 1;
		for(int k = 0; k < processes.size(); k++) {
			processes.get(k).setTimesList(processes.get(k).splitTime());
			processesOutput.add(k, processes.get(k).tickStates);
			v.getTextAreaStateOutput().append((k + 1) + ". " + "Zustaende Prozess: " + processes.get(k).getpName());
			v.getTextAreaStateOutput().append(newline);
		}
		v.getTextAreaStateOutput().append(newline);
		while(run) {
			Collections.sort(processes, prioComparator); // Processes will be sorted from highest to lowest priority
			cacheProcess = processes.get(0);
			// if the processes time list is empty
			if(cacheProcess.getTimesList().isEmpty()) {
				simOutput(cacheProcess, 2);
				cacheProcess.setState(ProcessState.FINISHED);
				cacheProcess.getTickStates().add("FERTIG");
				if(processes.size() > 1) {
					processes.remove(0);
				}
				else {
					// printing of the process states for the different ticks
					for(int whichList = 0; whichList < processesOutput.size(); whichList++) {
						v.getTextAreaStateOutput().append(processGetter + " :  ");
						if(processGetter < processGetterEval) processGetter++;
						else processGetter = 1;
						for(int list = 0; list < processesOutput.get(whichList).size(); list++) {
							v.getTextAreaStateOutput().append("Tick " + list + ": " + processesOutput.get(whichList).get(list) + " | ");
						}
						v.getTextAreaStateOutput().append(newline);
					}
					// if there are no processes to schedule anymore
					processesOutput.clear();
					m.getData().clear();
					m.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Alle Prozesse sind fertig.");
					this.run = false;
				}
			}
			// if the processes time list has something in it
			else {
				// if the process should be calculating
				if(cacheProcess.getIsCalc()) {
					cacheProcess.setState(ProcessState.CALC);
					cacheProcess.getTickStates().add("r");
					simOutput(cacheProcess, 0);
					if(cacheProcess.getTimesList().get(0) > 1) {
						cacheProcess.getTimesList().set(0, cacheProcess.getTimesList().get(0) - 1);
					}
					else {
						if(cacheProcess.getpPrio() > 1) {
							cacheProcess.setpPrio(cacheProcess.getpPrio() - 2);
						}
						else {
							cacheProcess.setpPrio(0);
						}
						cacheProcess.getTimesList().remove(0);
						cacheProcess.setState(ProcessState.BLOCKED);
						cacheProcess.setIsCalc(!cacheProcess.getIsCalc());
					}
				}
				// if the process should be blocked
				else {
					cacheProcess.setState(ProcessState.BLOCKED);
					simOutput(cacheProcess, 1);
					cacheProcess.getTickStates().add("b");
					if(cacheProcess.getTimesList().get(0) > 1) {
						cacheProcess.getTimesList().set(0, cacheProcess.getTimesList().get(0) - 1);
					}
					else {
						cacheProcess.getTimesList().remove(0);
						cacheProcess.setIsCalc(!cacheProcess.getIsCalc());
						cacheProcess.setState(ProcessState.WAITING);
					}
				}
				// handle the rest of the processes, which are not the cacheprocess
				for(int p = 1; p < processes.size(); p++) {
					if(processes.get(p).getState() != ProcessState.FINISHED) {
						// output for blocked processes; other processes may be blocked while others are calculating
						if(!processes.get(p).getIsCalc() && processes.get(p).getState() != ProcessState.FINISHED) {
							// if the processes time list is empty
							if(processes.get(p).getTimesList().isEmpty()) {
								simOutput(processes.get(p), 2);
								processes.get(p).getTickStates().add("FERTIG");
								processes.get(p).setState(ProcessState.FINISHED);
								processes.remove(p);
							}
							// if the processes time list has something in it
							else {
								simOutput(processes.get(p), 1);
								processes.get(p).getTickStates().add("b");
								if(processes.get(p).getTimesList().get(0) > 1) {
									processes.get(p).getTimesList().set(0, processes.get(p).getTimesList().get(0) - 1);
								}
								else {
									processes.get(p).getTimesList().remove(0);
									processes.get(p).setIsCalc(!processes.get(p).getIsCalc());
									processes.get(p).setState(ProcessState.WAITING);
								}
							}
						}
						// output for waiting processes
						else {
							simOutput(processes.get(p), 3);
							processes.get(p).getTickStates().add("w");
						}
					}
					else {
						processes.get(p).getTickStates().add("FERTIG");
						processes.get(p).setState(ProcessState.FINISHED);
						processes.remove(p);
					}
					
				}
				v.getTextArea().append("-----/Takt " + tick + "/-----");
				tick++;
				v.getTextArea().append(newline);
			}
		}
		
	}
	
	public Scheduler(InputModel inputModel, View view) {
		m = inputModel;
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
			//v.getTextArea().append(process.getpName() + " rechnet, noch " + process.getTimesList().get(0) + " ZE...Die Prioritaet ist: " + process.getpPrio() + newline);
			v.getTextArea().append(process.getpName() + " rechnet." + newline);
			break;
		case 1:
			//v.getTextArea().append(process.getpName() + " ist blockiert fuer noch " + process.getTimesList().get(0) + " ZE..." + newline);
			v.getTextArea().append(process.getpName() + " ist blockiert." + newline);
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
