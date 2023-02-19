package de.hgs.itg23.scheduler.gui;

import de.hgs.itg23.scheduler.model.Scheduler;

public class App {

	public static void main(String[] args) {
		
		InputModel inputModel = new InputModel();
		View v = new View();
		OutputModel outputModel = new OutputModel(inputModel);
		Scheduler s = new Scheduler(inputModel, outputModel, v);
		Controller c = new Controller(inputModel, outputModel, v, s);
		v.setVisible(true);
		c.initController();
	}

}
