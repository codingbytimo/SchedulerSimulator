package de.hgs.itg23.scheduler.model;

import de.hgs.itg23.scheduler.model.Process;

public class Scheduler {

	Process activeJobs[];
	Process waitingJobs[];
	
	Process p0 = new Process(1, 1, 0);
	Process p1 = new Process(2, 1, 1);
	
}
