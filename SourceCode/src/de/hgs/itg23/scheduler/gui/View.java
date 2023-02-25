package de.hgs.itg23.scheduler.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import java.awt.TextArea;
import java.awt.ScrollPane;

public class View extends JFrame {
	
	private JTabbedPane tabbedPane;
	
	// JPanel Deklaration
	private JPanel panelSim;
	private JPanel panelConfigure;
	private JLabel lblAddProcess;
	private JButton btnNewProcess;
	private JButton btnRemoveProcess;
	private JTable inputTable;
	private JScrollPane scrollPaneInput;
	private JButton btnRun;
	private JButton btnResetAll;
	private TextArea textArea;
	private TextArea textAreaStateOutput;
	
	
	public View() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setTitle("Scheduler von Timo");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelSim = new JPanel();
		panelConfigure = new JPanel();
		
	    tabbedPane.addTab("Sim", panelSim);
	    panelSim.setLayout(null);
	    
	    btnRun = new JButton("Run");
	    btnRun.setBounds(10, 11, 89, 23);
	    panelSim.add(btnRun);
	    
	    textArea = new TextArea();
	    textArea.setBounds(10, 40, 300, 400);
	    textArea.setEditable(false);
	    panelSim.add(textArea);
	    
	    textAreaStateOutput = new TextArea();
	    textAreaStateOutput.setEditable(false);
	    textAreaStateOutput.setBounds(345, 40, 600, 400);
	    panelSim.add(textAreaStateOutput);
	    
	    tabbedPane.addTab("Configure", panelConfigure);
	    panelConfigure.setLayout(null);
	    
	    lblAddProcess = new JLabel("Add processes for scheduling:");
	    lblAddProcess.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    lblAddProcess.setBounds(10, 11, 300, 14);
	    panelConfigure.add(lblAddProcess);
	    
	    inputTable = new JTable();
	    inputTable.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
	    inputTable.getTableHeader().setBackground(Color.ORANGE);
	    scrollPaneInput = new JScrollPane(inputTable);
	    scrollPaneInput.setBounds(10, 36, 600, 300);
	    panelConfigure.add(scrollPaneInput);
	    
	    btnNewProcess = new JButton("Add process");
	    btnNewProcess.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    btnNewProcess.setBounds(10, 347, 150, 23);
	    panelConfigure.add(btnNewProcess);
	    
	    btnRemoveProcess = new JButton("Remove process");
	    btnRemoveProcess.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    btnRemoveProcess.setBounds(170, 347, 150, 23);
	    panelConfigure.add(btnRemoveProcess);
	    
	    btnResetAll = new JButton("Reset all");
	    btnResetAll.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    btnResetAll.setBounds(330, 347, 150, 23);
	    panelConfigure.add(btnResetAll);
	}


	public TextArea getTextAreaStateOutput() {
		return textAreaStateOutput;
	}


	public void setTextAreaStateOutput(TextArea textAreaStateOutput) {
		this.textAreaStateOutput = textAreaStateOutput;
	}

	public JButton getBtnResetAll() {
		return btnResetAll;
	}


	public TextArea getTextArea() {
		return textArea;
	}


	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}


	public JButton getBtnRun() {
		return btnRun;
	}


	public JButton getBtnRemoveProcess() {
		return btnRemoveProcess;
	}


	public JButton getBtnNewProcess() {
		return btnNewProcess;
	}


	public JTable getInputTable() {
		return inputTable;
	}


	public void setInputTable(JTable table) {
		this.inputTable = table;
	}
}
