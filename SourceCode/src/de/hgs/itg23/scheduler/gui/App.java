package de.hgs.itg23.scheduler.gui;

import de.hgs.itg23.scheduler.model.Scheduler;

public class App {

	public static void main(String[] args) {
		
		Model m = new Model();
		View v = new View();
		Controller c = new Controller(m, v);
		Scheduler scheduler = new Scheduler(m, c);
		v.setVisible(true);
		c.initController();
	}

}
