package de.hgs.itg23.scheduler.gui;

import java.awt.event.ActionEvent;

public class Controller {
	
	private Model model; 
	private View view;

	public Controller(Model m, View v) { 

	  model = m;
	  view = v;

	  initView();
	}

	public void initView() { 
		view.getTable().setModel(model);
	} 

	public void initController() { 
		view.getBtnNewProcess().addActionListener(e -> appendEmptyRow(e));
		view.getBtnRemoveProcess().addActionListener(e -> deleteRow(e));
	}
	
	private void appendEmptyRow(ActionEvent e) { 
		this.model.appendEmptyRow();
		int count = view.getTable().getRowCount(); 
		view.getTable().setRowSelectionInterval(count-1, count-1); 
		view.getTable().editCellAt(count-1, 0);
		view.getTable().setSurrendersFocusOnKeystroke(true);
		view.getTable().getEditorComponent().requestFocus();
	}
	
	private void deleteRow(ActionEvent e) { 
		  int row = view.getTable().getSelectedRow(); 
		  this.model.deleteRow(row);
	}
}
