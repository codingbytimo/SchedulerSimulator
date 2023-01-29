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
			"Time", "Priority"};
	
	ArrayList<Process> data = new ArrayList<>(
			Arrays.asList( 
					new Process("A", "1", 3), 
					new Process("B", "1", 5),
					new Process("C", "1", 4)
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
					value = data.get(row).getpTime();
					break;
				case 2:
					value = data.get(row).getpPrio();
					break;
				default:
					break;
				}
				return value;
			}
			
			Class[] columns = new Class[] {String.class,
					String.class, Integer.class}; 
			
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
					data.get(row).setpTime((String)value);
					break;
				case 2:
					data.get(row).setpPrio((Integer)value);
					break;
				default:
					break;
				}
		        fireTableCellUpdated(row, col);
			}
			
			public ArrayList<Process> getData() {
				return data;
			}
			
			public String[] getColumnNames() {
				return columnNames;
			}

			public void appendEmptyRow() {
				data.add(new Process("PROZESS", "Zeit;Zeit", 1));
				int count = getRowCount();
				fireTableRowsInserted(count-1, count-1); 
			}
			
			public void deleteRow(int rowIndex) {
				data.remove(rowIndex);
				int count = getRowCount();
				fireTableRowsDeleted(count-1, count-1);
			}
}
