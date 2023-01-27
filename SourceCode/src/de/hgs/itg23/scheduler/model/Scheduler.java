package de.hgs.itg23.scheduler.model;

import java.util.ArrayList;

import de.hgs.itg23.scheduler.gui.Controller;
import de.hgs.itg23.scheduler.gui.Model;

public class Scheduler {
	
	private Controller controller;
	private Model model;
	private Process process;
	
	ArrayList<Process> data = model.getData();
	
	public Scheduler(Model m, Controller c) {
		model = m;
		controller = c;
	}
	
	public void splitTime() {
		
	}
	
}
