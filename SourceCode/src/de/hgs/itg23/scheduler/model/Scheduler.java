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
	
	// Scheduler Konstruktor
	public Scheduler(InputModel inputModel, View view) {
		m = inputModel;
		v = view;
	}

	public void startScheduling() {
		// Befehle fuer den Start des Schedulers
		v.getTextArea().setText("");
		v.getTextAreaStateOutput().setText("Uebersicht der Zustaende der Prozesse fuer jeden Takt" + newline + "Legende: r = rechnen; b = blockiert; w = warten" + newline);
		v.getTextAreaStateOutput().append(newline);
		v.getTextArea().append("Scheduler LOG - Zuerst wird der Takt angezeigt, danach was in diesem Takt passiert ist" + newline + "-----/Takt 0/-----");
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
		// Solange der Scheduler laeuft, bleibt das Programm innerhalb dieser Schleife
		while(run) {
			Collections.sort(processes, prioComparator); // Processes will be sorted from highest to lowest priority
			cacheProcess = processes.get(0);
			// falls die Prozessliste des ausgewaelten Prozesses leer ist; Passiert auch sobald der Scheduler fertig ist, da Prozesse, sobald sie fertig sind geloescht werden
			if(cacheProcess.getTimesList().isEmpty()) {
				simOutput(cacheProcess, 2);
				cacheProcess.setState(ProcessState.FINISHED);
				cacheProcess.getTickStates().add("FERTIG");
				if(processes.size() > 1) {
					processes.remove(0);
				}
				else {
					// Ausgabe der verschiedenen Zustaende fuer jeden Takt
					for(int whichList = 0; whichList < processesOutput.size(); whichList++) {
						v.getTextAreaStateOutput().append(processGetter + " :  ");
						if(processGetter < processGetterEval) processGetter++;
						else processGetter = 1;
						for(int list = 0; list < processesOutput.get(whichList).size(); list++) {
							v.getTextAreaStateOutput().append("Takt " + list + ": " + processesOutput.get(whichList).get(list) + " | ");
						}
						v.getTextAreaStateOutput().append(newline);
					}
					// Wenn der Scheduler komplett fertig ist
					processesOutput.clear();
					m.getData().clear();
					m.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Alle Prozesse sind fertig.");
					this.run = false;
				}
			}
			// Wenn die Zeitenliste des Prozesses Zeiten enthaelt
			else {
				// Wenn der Prozess im Zustand 'Rechnen' ist
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
							if(cacheProcess.getpPrio() >= 1) v.getTextArea().append("Prio von " + cacheProcess.getpName() + " wurde von " + (cacheProcess.getpPrio() + 2) + " zu " + cacheProcess.getpPrio() + " geaendert." + newline);
							else v.getTextArea().append("Prio von " + cacheProcess.getpName() + " ist nun 0." + newline);
						}
						else {
							cacheProcess.setpPrio(0);
							v.getTextArea().append("Prio von " + cacheProcess.getpName() + " ist nun 0." + newline);
						}
						cacheProcess.getTimesList().remove(0);
						cacheProcess.setState(ProcessState.BLOCKED);
						cacheProcess.setIsCalc(!cacheProcess.getIsCalc());
					}
				}
				// Wenn der Prozess im Zustand 'Blockiert' ist
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
				// Was alle anderen Prozesse fuer diesen Takt machen
				for(int p = 1; p < processes.size(); p++) {
					if(processes.get(p).getState() != ProcessState.FINISHED) {
						// Ausgabe für blockierte Prozesse; andere Prozesse können blockiert werden, während andere rechnen
						if(!processes.get(p).getIsCalc() && processes.get(p).getState() != ProcessState.FINISHED) {
							// if the processes time list is empty
							if(processes.get(p).getTimesList().isEmpty()) {
								simOutput(processes.get(p), 2);
								processes.get(p).getTickStates().add("FERTIG");
								processes.get(p).setState(ProcessState.FINISHED);
								processes.remove(p);
							}
							// Wenn die Zeitenliste des Prozesses Zeiten enthaelt
							else {
								simOutput(processes.get(p), 1);
								processes.get(p).getTickStates().add("b");
								if(processes.get(p).getTimesList().get(0) > 1) {
									processes.get(p).getTimesList().set(0, processes.get(p).getTimesList().get(0) - 1);
								}
								else {
									processes.get(p).getTimesList().remove(0);
									// Falls nach dem blockieren der Prozess fertig ist
									if(processes.get(p).getTimesList().isEmpty()) {
										simOutput(processes.get(p), 2);
										processes.get(p).getTickStates().add("FERTIG");
										processes.get(p).setState(ProcessState.FINISHED);
										processes.remove(p);
									} 
									else {
										processes.get(p).setIsCalc(!processes.get(p).getIsCalc());
										processes.get(p).setState(ProcessState.WAITING);
									}
								}
							}
						}
						// Ausgabe, falls ein Prozess warten muss
						else {
							simOutput(processes.get(p), 3);
							processes.get(p).getTickStates().add("w");
						}
					}
					
				}
				// Befehle fuer die Ausgabe
				v.getTextArea().append("-----/Takt " + tick + "/-----");
				tick++;
				v.getTextArea().append(newline);
			}
		}
		
	}
	
	// Ausgabe Methode fuer den Scheduler LOG/'textArea'
	private void simOutput(Process process, int output) {
		switch (output) {
		case 0:
			// Alternative Ausgabe mit mehr Info
			//v.getTextArea().append(process.getpName() + " rechnet, noch " + process.getTimesList().get(0) + " ZE...Die Prioritaet ist: " + process.getpPrio() + newline);
			v.getTextArea().append(process.getpName() + " rechnet. " + "Prio: " + process.getpPrio() + newline);
			break;
		case 1:
			// Alternative Ausgabe mit mehr Info
			//v.getTextArea().append(process.getpName() + " ist blockiert fuer noch " + process.getTimesList().get(0) + " ZE..." + newline);
			v.getTextArea().append(process.getpName() + " ist blockiert. " + "Prio: " + process.getpPrio() + newline);
			break;
		case 2:
			v.getTextArea().append(process.getpName() + " ist komplett fertig." + newline);
			break;
		case 3:
			v.getTextArea().append(process.getpName() + " wartet. " + "Prio: " + process.getpPrio() + newline);
			break;
		default:
			break;
		}
		
	}
	
	// DEBUG Methoden
	private void printArrays() {
		System.out.println("time List" + cacheProcess.getpName() + " : " + cacheProcess.getTimesList());
		System.out.println("----------");
		System.out.println(processesOutput);
		System.out.println("-----//-----");
	}
	
	public void printProccessList(ArrayList<Process> pList) {
		for(Process process : pList) {
			System.out.println(process);
		}
	}

}
