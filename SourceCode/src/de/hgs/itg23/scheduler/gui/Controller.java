package de.hgs.itg23.scheduler.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import de.hgs.itg23.scheduler.model.Scheduler;

public class Controller {
	
	private InputModel inputModel; 
	private View view;
	DefaultTableModel dm = new DefaultTableModel(0, 0);
	private Scheduler scheduler;
	
	// Controller Konstruktor
	public Controller(InputModel m, View v, Scheduler s) {
	  inputModel = m;
	  view = v;
	  scheduler = s;
	  initView();
	}
	
	// initialisierung der GUI
	public void initView() {
		view.getInputTable().setModel(inputModel);
		view.getInputTable().getTableHeader().setBackground(Color.ORANGE);
		view.getInputTable().getColumnModel().getColumn(0).setHeaderValue("Prozessname");
		view.getInputTable().getColumnModel().getColumn(1).setHeaderValue("Zeiten (durch ' ; ' trennen)");
		view.getInputTable().getColumnModel().getColumn(2).setHeaderValue("Prioritaet");
		view.getInputTable().getTableHeader().resizeAndRepaint();
	}
	
	// initialisierung des Controllers (Funktion der Buttons)
	public void initController() { 
		view.getBtnNewProcess().addActionListener(e -> appendEmptyRow(e));
		view.getBtnRemoveProcess().addActionListener(e -> deleteRow(e));
		view.getBtnRun().addActionListener(e -> startScheduling(e));
		view.getBtnResetAll().addActionListener(e -> resetInput(e));
	}
	
	// hinzufuegen eines Prozesses in der Input Tabelle
	private void appendEmptyRow(ActionEvent e) { 
		this.inputModel.appendEmptyRow();
		int count = view.getInputTable().getRowCount(); 
		view.getInputTable().setRowSelectionInterval(count-1, count-1); 
		view.getInputTable().editCellAt(count-1, 0);
		view.getInputTable().setSurrendersFocusOnKeystroke(true);
		view.getInputTable().getEditorComponent().requestFocus();
	}
	
	// loeschen eines Prozesses in der Input Tabelle
	private void deleteRow(ActionEvent e) { 
		  int row = view.getInputTable().getSelectedRow(); 
		  this.inputModel.deleteRow(row);
	}
	
	// Run Button
	private void startScheduling(ActionEvent e) {
		this.scheduler.startScheduling();
	}
	
	// Reset Button
	private void resetInput(ActionEvent e) {
		inputModel.getData().clear();
		inputModel.fireTableDataChanged();
	}
	
}
