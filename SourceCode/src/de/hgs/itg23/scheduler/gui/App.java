package de.hgs.itg23.scheduler.gui;

public class App {

	public static void main(String[] args) {
		
		Model m = new Model();
		View v = new View();
		v.setVisible(true);
		Controller c = new Controller(m, v);
		c.initController();
	}

}
