package de.hgs.itg23.scheduler.gui;

import javax.swing.table.AbstractTableModel;

import de.hgs.itg23.scheduler.model.Scheduler;

public class OutputModel extends AbstractTableModel {
	
	private InputModel m;
	private Scheduler s;
	int clock;

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return m.getData().size();
		//return v.getTable().getRowCount()*2;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object value = null;
		switch (col) {
		case 0:
			value = m.getData().get(row).getpName();
			break;
		case 1:
			value = m.getData().get(row).getpTime();
			break;
		case 2:
			value = m.getData().get(row).getpPrio();
			break;
		default:
			break;
		}
		return value;
	}
	
	public void setValueAt(Object value, int row, int col) {
		
	}
	
	public boolean isCellEditable(int row, int col) {			
		return false;
	}

	public OutputModel( InputModel m, Scheduler s) {
		this.m = m;
		this.s = s;
	}
	
}
