package de.hgs.itg23.scheduler.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import de.hgs.itg23.scheduler.model.Scheduler;

public class Controller {
	
	private InputModel inputModel; 
	private OutputModel outputModel;
	private View view;
	DefaultTableModel dm = new DefaultTableModel(0, 0);
	private Scheduler scheduler;
	
	public Controller(InputModel m, OutputModel o, View v, Scheduler s) { 

	  inputModel = m;
	  outputModel = o;
	  view = v;
	  scheduler = s;
	  initView();
	}

	public void initView() {
		view.getInputTable().setModel(inputModel);
		changeOutputTable();
	}
	
	public void changeOutputTable() {
		Vector<String> header = new Vector<>();
		for(int i = 0; i < inputModel.getData().size(); i++) {
			header.add(inputModel.getData().get(i).getpName());
		}
	    dm.setColumnIdentifiers(header);
	    view.getOutputTable().setModel(dm);
	}

	public void initController() { 
		view.getBtnNewProcess().addActionListener(e -> appendEmptyRow(e));
		view.getBtnRemoveProcess().addActionListener(e -> deleteRow(e));
		view.getBtnRun().addActionListener(e -> startScheduling(e));
	}
	
	private void appendEmptyRow(ActionEvent e) { 
		this.inputModel.appendEmptyRow();
		int count = view.getInputTable().getRowCount(); 
		view.getInputTable().setRowSelectionInterval(count-1, count-1); 
		view.getInputTable().editCellAt(count-1, 0);
		view.getInputTable().setSurrendersFocusOnKeystroke(true);
		view.getInputTable().getEditorComponent().requestFocus();
		changeOutputTable();
	}
	
	private void deleteRow(ActionEvent e) { 
		  int row = view.getInputTable().getSelectedRow(); 
		  this.inputModel.deleteRow(row);
		  changeOutputTable();
	}
	
	private void startScheduling(ActionEvent e) {
		this.scheduler.startScheduling();
	}
	
}
