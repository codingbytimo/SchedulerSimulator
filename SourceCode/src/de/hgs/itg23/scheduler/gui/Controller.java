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
	
	public Controller(InputModel m, View v, Scheduler s) { 

	  inputModel = m;
	  view = v;
	  scheduler = s;
	  initView();
	}

	public void initView() {
		view.getInputTable().setModel(inputModel);
		view.getInputTable().getTableHeader().setBackground(Color.ORANGE);
		view.getInputTable().getColumnModel().getColumn(0).setHeaderValue("Prozessname");
		view.getInputTable().getColumnModel().getColumn(1).setHeaderValue("Zeiten");
		view.getInputTable().getColumnModel().getColumn(2).setHeaderValue("Prioritaet");
		view.getInputTable().getTableHeader().resizeAndRepaint();
	}

	public void initController() { 
		view.getBtnNewProcess().addActionListener(e -> appendEmptyRow(e));
		view.getBtnRemoveProcess().addActionListener(e -> deleteRow(e));
		view.getBtnRun().addActionListener(e -> startScheduling(e));
		view.getBtnResetAll().addActionListener(e -> resetInput(e));
	}
	
	private void appendEmptyRow(ActionEvent e) { 
		this.inputModel.appendEmptyRow();
		int count = view.getInputTable().getRowCount(); 
		view.getInputTable().setRowSelectionInterval(count-1, count-1); 
		view.getInputTable().editCellAt(count-1, 0);
		view.getInputTable().setSurrendersFocusOnKeystroke(true);
		view.getInputTable().getEditorComponent().requestFocus();
	}
	
	private void deleteRow(ActionEvent e) { 
		  int row = view.getInputTable().getSelectedRow(); 
		  this.inputModel.deleteRow(row);
	}
	
	private void startScheduling(ActionEvent e) {
		this.scheduler.startScheduling();
	}
	
	private void resetInput(ActionEvent e) {
		inputModel.getData().clear();
		inputModel.fireTableDataChanged();
	}
	
}
