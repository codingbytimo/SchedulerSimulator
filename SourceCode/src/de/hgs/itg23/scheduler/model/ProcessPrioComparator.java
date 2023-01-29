package de.hgs.itg23.scheduler.model;

import java.util.Comparator;

public class ProcessPrioComparator implements Comparator<Process>{
	
	@Override
    public int compare(Process firstProcess, Process secondProcess) {
       return Integer.compare(secondProcess.getpPrio(), firstProcess.getpPrio());
    }

}
