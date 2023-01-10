package de.hgs.itg23.scheduler.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;

public class View extends JFrame {
	
	JTable table;
	private JPanel panel;
	private JLabel lblInput;
	private JLabel lblWaitingTime;
	private JLabel lblCalculatingTime;
	private JButton btnRun;
	private JButton btnAddProcess;
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;
	
	public View() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 450);
		setTitle("Scheduler von Timo");
		getContentPane().setLayout(new BorderLayout(0, 0));
		table = new JTable();
		getContentPane().add(table);
		
		btnRun = new JButton("Run");
		getContentPane().add(btnRun, BorderLayout.SOUTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setBackground(Color.YELLOW);
		
		lblInput = new JLabel("[PROCESS INPUT]");
		panel.add(lblInput);
		
		lblWaitingTime = new JLabel("Waiting time:");
		panel.add(lblWaitingTime);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format) {
	    	@Override
	        public Object stringToValue(String text) throws ParseException {
	            if (text.length() == 0)
	                return null;
	            return super.stringToValue(text);
	        }
	    };
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    
		formattedTextField = new JFormattedTextField(formatter);
		formattedTextField.setColumns(4);
		panel.add(formattedTextField);
		
		lblCalculatingTime = new JLabel("Calculating time:");
		panel.add(lblCalculatingTime);
		
		formattedTextField_1 = new JFormattedTextField(formatter);
		formattedTextField_1.setColumns(4);
		panel.add(formattedTextField_1);
		
		btnAddProcess = new JButton("Add Process");
		panel.add(btnAddProcess);
		
		
	}

	public JButton getBtnRun() {
		return btnRun;
	}

	public void setBtnRun(JButton btnRun) {
		this.btnRun = btnRun;
	}

	public JButton getBtnAddProcess() {
		return btnAddProcess;
	}

	public void setBtnAddProcess(JButton btnAddProcess) {
		this.btnAddProcess = btnAddProcess;
	}
	
}
