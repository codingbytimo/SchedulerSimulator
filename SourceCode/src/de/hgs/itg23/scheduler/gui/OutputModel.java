package de.hgs.itg23.scheduler.gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class OutputModel extends AbstractTableModel {
	
	private InputModel inputModel = new InputModel();
	//String[][] outputArray = new String[inputModel.getData().size()][];
	ArrayList<ArrayList<String>> outputList;
	//ArrayList<String> outputList = new ArrayList<>();
	
	public void fillOutputList() {
		for(int i = 0; i < inputModel.getData().size(); i++) {
			outputList.add(new ArrayList<String>());
		}
	}
	
	public void setData(ArrayList<ArrayList<String>> data) {
		this.outputList = data;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return outputList.size();
		//return v.getTable().getRowCount()*2;
	}

	public ArrayList<ArrayList<String>> getOutputList() {
		return outputList;
	}

	public void setOutputList(ArrayList<ArrayList<String>> outputList) {
		this.outputList = outputList;
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
	
	public OutputModel(InputModel m) {
		this.inputModel = m;
	}
	
}
