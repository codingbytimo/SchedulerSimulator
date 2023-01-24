package de.hgs.itg23.scheduler.gui;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import de.hgs.itg23.scheduler.model.Process;

public class Model extends AbstractTableModel {

	private View view;
	private Process process;
	private Controller controller;
	
	String[] columnNames = {"Process name", 
			"Waiting time",
			"Calculating time"};
	
	ArrayList<Process> data = new ArrayList<>(
			Arrays.asList( 
					new Process("Prozess1", 1, 2), 
					new Process("Prozess2", 2, 1)
			)
			);

			public void setData(ArrayList<Process> data) {
				this.data = data;
			}

			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public int getRowCount() {
				return data.size(); 
			}

			@Override
			public Object getValueAt(int row, int col) {
				Object value = null;
				switch (col) {
				case 0:
					value = data.get(row).getpName();
					break;
				case 1:
					value = data.get(row).getpWaitTime();
					break;
				case 2:
					value = data.get(row).getpCalcTime();
					break;
				default:
					break;
				}
				return value;
			}
			
			Class[] columns = new Class[] {String.class,
					Integer.class, 
					Integer.class}; 
			
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
			
			public boolean isCellEditable(int row, int col) {
				return true;
			}

			public void setValueAt(Object value, int row, int col) {
				switch (col) {
				case 0:
					data.get(row).setpName((String)value);
					break;
				case 1:
					data.get(row).setpWaitTime((Integer)value);
					break;
				case 2:
					data.get(row).setpCalcTime((Integer)value);
					break;
				default:
					break;
				}
		        fireTableCellUpdated(row, col);
			}
			
			public void addElements() {
				data.add(new Process("Prozess32", getRowCount(), getColumnCount()));
			}
			
			public ArrayList<Process> getData() {
				return data;
			}
			
			public void appendEmptyRow() {
				data.add(new Process("", 0, 0));
				int count = getRowCount();
				fireTableRowsInserted(count-1, count-1); 
			}
			
			public void deleteRow(int rowIndex) {
				data.remove(rowIndex);
				int count = getRowCount();
				fireTableRowsDeleted(count-1, count-1);
			}

}
