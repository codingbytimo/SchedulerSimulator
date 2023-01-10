package de.hgs.itg23.scheduler.gui;

import de.hgs.itg23.scheduler.gui.View;

public class Controller {
	
	private View view;
	private Model model;
	
	public Controller(Model m, View v) { 

		  view = v;
		  model = m;

	}
	
	public void initController() { 
		//view.getBtnAddProcess().addActionListener(e -> );
		//view.getBtnRun().addActionListener(e -> );
	}
	
}
