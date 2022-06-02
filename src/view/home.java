package view;

import java.awt.EventQueue;
import model.*;
import model.enums.*;
import controller.*;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class home {

public JFrame home;
private CadastroController cadastroController;
private static RepositoryDB repository;
private JTextField txtNome;
private JTextField txtRua;
private JTextField txtBairro;
private JTextField txtCidade;
private JTextField txtEstado;
private JTextField txtAvaliacao;
private JTextField txtPrecoKWH;
private JLabel lblTipoPlug;

private JCheckBox cbxTipo1 = new JCheckBox("Tipo 1");;
private boolean cbxTipo1IsChecked = false;

private JCheckBox cbxTipo2 = new JCheckBox("Tipo 2");;
private boolean cbxTipo2IsChecked = false;

private JCheckBox cbxCSS2 = new JCheckBox("CSS2");;
private boolean cbxCSS2IsChecked = false;

private JCheckBox cbxCHA = new JCheckBox("CHAdeMO");;
private boolean cbxCHAIsChecked = false;

	public home(CadastroController cadastroControllerC, RepositoryDB repository) throws SQLException {
		cadastroController = cadastroControllerC;
		 cbxTipo1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cbxTipo1IsChecked = !cbxTipo1IsChecked;
			}    
         
         });    
		 
		 cbxTipo2.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					cbxTipo2IsChecked = !cbxTipo2IsChecked;
				}    
	         
	         });    
		 
		 cbxCSS2.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					cbxCSS2IsChecked = !cbxCSS2IsChecked;
				}    
	         
	         });    
		 
		 cbxCHA.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					cbxCHAIsChecked = !cbxCHAIsChecked;
				}    
	         
	         });    

		this.repository = repository;
		initialize();
	}

	private void initialize() {
		home = new JFrame();
		home.setBackground(Color.DARK_GRAY);
		home.getContentPane().setBackground(Color.DARK_GRAY);
		SpringLayout springLayout = new SpringLayout();
		home.getContentPane().setLayout(springLayout);
		
		JLabel lbTitle = new JLabel("Cadastrar novo posto");
		springLayout.putConstraint(SpringLayout.NORTH, lbTitle, 20, SpringLayout.NORTH, home.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lbTitle, 190, SpringLayout.WEST, home.getContentPane());
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTitle.setForeground(Color.LIGHT_GRAY);
		home.getContentPane().add(lbTitle);
		
		JLabel lblNome = new JLabel("Nome");
		springLayout.putConstraint(SpringLayout.NORTH, lblNome, 20, SpringLayout.SOUTH, lbTitle);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, lblNome, 20, SpringLayout.WEST, home.getContentPane());
		lblNome.setForeground(Color.LIGHT_GRAY);
		home.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtNome, 0, SpringLayout.NORTH, lblNome);
		springLayout.putConstraint(SpringLayout.WEST, txtNome, 6, SpringLayout.EAST, lblNome);
		springLayout.putConstraint(SpringLayout.EAST, txtNome, 190, SpringLayout.EAST, lblNome);
		home.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua");
		springLayout.putConstraint(SpringLayout.NORTH, lblRua, 20, SpringLayout.SOUTH, lbTitle);
		springLayout.putConstraint(SpringLayout.WEST, lblRua, 70, SpringLayout.EAST, txtNome);
		lblRua.setForeground(Color.LIGHT_GRAY);
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		home.getContentPane().add(lblRua);
		
		txtRua = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtRua, 0, SpringLayout.NORTH, lblRua);
		springLayout.putConstraint(SpringLayout.WEST, txtRua, 6, SpringLayout.EAST, lblRua);
		springLayout.putConstraint(SpringLayout.EAST, txtRua, -12, SpringLayout.EAST, home.getContentPane());
		txtRua.setColumns(10);
		home.getContentPane().add(txtRua);
		
		JLabel lblBairro = new JLabel("Bairro");
		springLayout.putConstraint(SpringLayout.NORTH, lblBairro, 20, SpringLayout.SOUTH, lblRua);
		springLayout.putConstraint(SpringLayout.EAST, lblBairro, 0, SpringLayout.EAST, lblRua);
		lblBairro.setForeground(Color.LIGHT_GRAY);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		home.getContentPane().add(lblBairro);
		
		txtBairro = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtBairro, 6, SpringLayout.EAST, lblBairro);
		springLayout.putConstraint(SpringLayout.SOUTH, txtBairro, 0, SpringLayout.SOUTH, lblBairro);
		springLayout.putConstraint(SpringLayout.EAST, txtBairro, 0, SpringLayout.EAST, txtRua);
		txtBairro.setColumns(10);
		home.getContentPane().add(txtBairro);
		
		
		JLabel lblCidade = new JLabel("Cidade");
		springLayout.putConstraint(SpringLayout.NORTH, lblCidade, 20, SpringLayout.SOUTH, lblBairro);
		springLayout.putConstraint(SpringLayout.EAST, lblCidade, 0, SpringLayout.EAST, lblBairro);
		lblCidade.setForeground(Color.LIGHT_GRAY);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		home.getContentPane().add(lblCidade);
		
		txtCidade = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtCidade, 6, SpringLayout.EAST, lblCidade);
		springLayout.putConstraint(SpringLayout.SOUTH, txtCidade, 0, SpringLayout.SOUTH, lblCidade);
		springLayout.putConstraint(SpringLayout.EAST, txtCidade, 0, SpringLayout.EAST, txtBairro);
		txtBairro.setColumns(10);
		home.getContentPane().add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado");
		springLayout.putConstraint(SpringLayout.NORTH, lblEstado, 20, SpringLayout.SOUTH, lblCidade);
		springLayout.putConstraint(SpringLayout.EAST, lblEstado, 0, SpringLayout.EAST, lblCidade);
		lblEstado.setForeground(Color.LIGHT_GRAY);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		home.getContentPane().add(lblEstado);
		
		txtEstado = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtEstado, 6, SpringLayout.EAST, lblEstado);
		springLayout.putConstraint(SpringLayout.SOUTH, txtEstado, 0, SpringLayout.SOUTH, lblEstado);
		springLayout.putConstraint(SpringLayout.EAST, txtEstado, 0, SpringLayout.EAST, txtCidade);
		txtCidade.setColumns(10);
		home.getContentPane().add(txtEstado);
		
		JLabel lblAvaliacao = new JLabel("Avaliação");
		springLayout.putConstraint(SpringLayout.WEST, lblAvaliacao, 0, SpringLayout.WEST, lblNome);
		springLayout.putConstraint(SpringLayout.SOUTH, lblAvaliacao, 0, SpringLayout.SOUTH, lblBairro);
		lblAvaliacao.setForeground(Color.LIGHT_GRAY);
		lblAvaliacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		home.getContentPane().add(lblAvaliacao);
		
		txtAvaliacao = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtAvaliacao, 6, SpringLayout.EAST, lblAvaliacao);
		springLayout.putConstraint(SpringLayout.SOUTH, txtAvaliacao, 0, SpringLayout.SOUTH, lblBairro);
		springLayout.putConstraint(SpringLayout.EAST, txtAvaliacao, 0, SpringLayout.EAST, txtNome);
		txtAvaliacao.setColumns(10);
		home.getContentPane().add(txtAvaliacao);
		
		JLabel lblPrecoKWH = new JLabel("Preço do kWh");
		springLayout.putConstraint(SpringLayout.WEST, lblPrecoKWH, 0, SpringLayout.WEST, lblAvaliacao);
		springLayout.putConstraint(SpringLayout.SOUTH, lblPrecoKWH, 0, SpringLayout.SOUTH, lblCidade);
		lblPrecoKWH.setForeground(Color.LIGHT_GRAY);
		lblPrecoKWH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		home.getContentPane().add(lblPrecoKWH);
		
		txtPrecoKWH = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPrecoKWH, 0, SpringLayout.NORTH, lblCidade);
		springLayout.putConstraint(SpringLayout.WEST, txtPrecoKWH, 6, SpringLayout.EAST, lblPrecoKWH);
		springLayout.putConstraint(SpringLayout.EAST, txtPrecoKWH, -46, SpringLayout.EAST, txtNome);
		home.getContentPane().add(txtPrecoKWH);
		txtPrecoKWH.setColumns(10);
		
		lblTipoPlug = new JLabel("Tipos de Plug");
		springLayout.putConstraint(SpringLayout.NORTH, lblTipoPlug, 0, SpringLayout.NORTH, lblEstado);
		springLayout.putConstraint(SpringLayout.WEST, lblTipoPlug, 0, SpringLayout.EAST, lblPrecoKWH);
		lblTipoPlug.setForeground(Color.LIGHT_GRAY);
		lblTipoPlug.setFont(new Font("Tahoma", Font.PLAIN, 14));
		home.getContentPane().add(lblTipoPlug);
		
		springLayout.putConstraint(SpringLayout.NORTH, cbxTipo1, 49, SpringLayout.SOUTH, lblPrecoKWH);
		springLayout.putConstraint(SpringLayout.WEST, cbxTipo1, -20, SpringLayout.WEST, lblTipoPlug);
		home.getContentPane().add(cbxTipo1);
		
		springLayout.putConstraint(SpringLayout.WEST, cbxTipo2, 28, SpringLayout.EAST, cbxTipo1);
		springLayout.putConstraint(SpringLayout.SOUTH, cbxTipo2, 0, SpringLayout.SOUTH, cbxTipo1);
		home.getContentPane().add(cbxTipo2);
		
		springLayout.putConstraint(SpringLayout.NORTH, cbxCSS2, 23, SpringLayout.SOUTH, cbxTipo1);
		springLayout.putConstraint(SpringLayout.WEST, cbxCSS2, 0, SpringLayout.WEST, cbxTipo1);
		home.getContentPane().add(cbxCSS2);
		
		springLayout.putConstraint(SpringLayout.WEST, cbxCHA, 0, SpringLayout.WEST, cbxTipo2);
		springLayout.putConstraint(SpringLayout.SOUTH, cbxCHA, 0, SpringLayout.SOUTH, cbxCSS2);
		home.getContentPane().add(cbxCHA);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ArrayList<tipoPlug> plugs = new ArrayList<tipoPlug>();
				
				if (cbxTipo1IsChecked) {
					plugs.add(tipoPlug.tipo1);
				}
				if (cbxTipo2IsChecked) {
					plugs.add(tipoPlug.tipo2);
				}
				if (cbxCSS2IsChecked) {
					plugs.add(tipoPlug.css2);
				}
				if (cbxCHAIsChecked) {
					plugs.add(tipoPlug.chademo);
				}
				
				Endereco enderecoCadastro = new Endereco(txtRua.getText(), 
												 txtBairro.getText(), 
												 txtCidade.getText(), 
												 txtEstado.getText());
				
				PostoDeGasolina novoPosto = new PostoDeGasolina(txtNome.getText(), enderecoCadastro, txtAvaliacao.getText(), plugs, txtPrecoKWH.getText());
				
				try {
					repository.inserirPosto(novoPosto);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnCadastrar.setBackground(new Color(0, 204, 102));
		springLayout.putConstraint(SpringLayout.NORTH, btnCadastrar, 39, SpringLayout.SOUTH, cbxCHA);
		springLayout.putConstraint(SpringLayout.WEST, btnCadastrar, 243, SpringLayout.WEST, home.getContentPane());
		home.getContentPane().add(btnCadastrar);
		
		JButton btnTabela = new JButton("Tabela");
		btnTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Tabela window = new Tabela(repository);
							window.frmTabela.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnTabela, 6, SpringLayout.SOUTH, btnCadastrar);
		springLayout.putConstraint(SpringLayout.WEST, btnTabela, -50, SpringLayout.WEST, btnCadastrar);
		btnTabela.setBackground(new Color(0, 204, 102));
		home.getContentPane().add(btnTabela);
		
		JButton btnMapa = new JButton("Mapa");
		btnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Mapa window = new Mapa(repository);
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnMapa, 6, SpringLayout.SOUTH, btnCadastrar);
		springLayout.putConstraint(SpringLayout.EAST, btnMapa, 50, SpringLayout.EAST, btnCadastrar);
		btnMapa.setBackground(new Color(0, 204, 102));
		home.getContentPane().add(btnMapa);
		
		
		home.setForeground(Color.WHITE);
		home.setTitle("Gerenciador postos de abastecimento");
		home.setBounds(100, 100, 599, 505);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
