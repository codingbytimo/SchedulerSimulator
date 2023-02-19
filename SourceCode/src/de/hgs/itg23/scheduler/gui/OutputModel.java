package de.hgs.itg23.scheduler.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class OutputModel extends AbstractTableModel {
	
	ArrayList<String> outputList = new ArrayList<>();
	
	public void setData(ArrayList<String> data) {
		outputList.add("Test");
		this.outputList = data;
	}

	public ArrayList<String> getOutputList() {
		return outputList;
	}

	public void setOutputList(ArrayList<String> outputList) {
		this.outputList = outputList;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return outputList.size();
		//return v.getTable().getRowCount()*2;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 15;
	}

	/*@Override
	public Object getValueAt(int row, int col) {
		Object value = null;
		switch (col) {
		case 0:
			value = outputList.get(row);
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
	}*/
	
	public void setValueAt(Object value, int row, int col) {
		
	}
	
	public boolean isCellEditable(int row, int col) {			
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
