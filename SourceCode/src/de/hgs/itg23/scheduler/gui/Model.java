package de.hgs.itg23.scheduler.gui;

import javax.swing.table.AbstractTableModel;

import de.hgs.itg23.scheduler.model.Process;

public class Model extends AbstractTableModel {

	private View view;
	private Process process;
	private Controller controller;
	
	String[] columnNames = {"WaitTime", 
			"CalcTime",
			"ID"};

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
