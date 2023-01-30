package de.hgs.itg23.scheduler.gui;

import de.hgs.itg23.scheduler.model.Scheduler;

public class App {

	public static void main(String[] args) {
		
		Model m = new Model();
		View v = new View();
		Scheduler s = new Scheduler(m, v);
		Controller c = new Controller(m, v, s);
		v.setVisible(true);
		c.initController();
	}

}
