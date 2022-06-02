package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import model.*;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import controller.RepositoryDB;

import javax.swing.JTable;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tabela {

	public JFrame frmTabela;
	private JTable table;
	DefaultTableModel model;
	private static RepositoryDB repository;
	Object[] coluna = {"nome","rua","bairro","cidade","estado","avaliação","plug tipo1","plug tipo2","plug CSS2","plug CHAdeMO", "preço KWH"};
	Object[] row = new Object[11];
	

	public Tabela(RepositoryDB repository) {
		this.repository = repository;
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(coluna);
		
		atualizar();
		
		initialize();
	}

	private void atualizar() {
		try {
			ArrayList<PostoDeGasolina> postos = repository.getPostos();
			
			for (PostoDeGasolina posto : postos) {
				row[0] = posto.getNome();
				row[1] = posto.getEndereco().getRua();
				row[2] = posto.getEndereco().getBairro();
				row[3] = posto.getEndereco().getCidade();
				row[4] = posto.getEndereco().getEstado();
				row[5] = posto.getAvaliacao();
				row[6] = posto.getPlug1();
				row[7] = posto.getPlug2();
				row[8] = posto.getPlug3();
				row[9] = posto.getPlug4();
				row[10]= posto.getPrecoKWH();
				
				model.addRow(row);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private void initialize() {
		frmTabela = new JFrame();
		frmTabela.setTitle("Tabela");
		frmTabela.getContentPane().setBackground(Color.DARK_GRAY);
		SpringLayout springLayout = new SpringLayout();
		frmTabela.getContentPane().setLayout(springLayout);
		
		table = new JTable();
		table.setForeground(Color.LIGHT_GRAY);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		springLayout.putConstraint(SpringLayout.NORTH, table, 5, SpringLayout.NORTH, frmTabela.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, table, 5, SpringLayout.WEST, frmTabela.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, -5, SpringLayout.SOUTH, frmTabela.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, -5, SpringLayout.EAST, frmTabela.getContentPane());
		table.setBackground(Color.DARK_GRAY);
		table.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, frmTabela.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, frmTabela.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, frmTabela.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, frmTabela.getContentPane());
		
		scrollPane.setViewportView(table);
		frmTabela.getContentPane().add(scrollPane);
		
		frmTabela.setBounds(100, 100, 885, 499);
		frmTabela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
