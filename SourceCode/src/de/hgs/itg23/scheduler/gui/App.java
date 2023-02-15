package de.hgs.itg23.scheduler.gui;

import de.hgs.itg23.scheduler.model.Scheduler;

public class App {

	public static void main(String[] args) {
		
		InputModel inputModel = new InputModel();
		View v = new View();
		Scheduler s = new Scheduler(inputModel, v);
		OutputModel outputModel = new OutputModel(inputModel, s);
		Controller c = new Controller(inputModel, v, s);
		v.setVisible(true);
		c.initController();
	}

}
