package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

import Controller.Helper.SelecionaContadorHelper;
import Model.DAO.ContadorDAO;
import View.SelecionaContadorGUI;

public class SelecionaContadorController {
	
	private final SelecionaContadorGUI view;
	private final SelecionaContadorHelper helper;
	
	private final View.ClienteGUI viewCliente;
	
	private JButton btnPesquisar;
	private JButton btnCadastrar;
	private JButton btnSelecionar;
	private JTable table;
	
	public SelecionaContadorController(View.SelecionaContadorGUI view, View.ClienteGUI viewCliente) {
		this.viewCliente = viewCliente;
		this.view = view;
		this.helper = new SelecionaContadorHelper(view);
		this.configInit();
	}

	public void atualizaTabela() {
		ContadorDAO contadorDAO = new ContadorDAO();
		ArrayList<Model.Contador> lstContador = new ArrayList<Model.Contador>();
		try {
			lstContador = contadorDAO.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.preencherTabela(lstContador);
	}
//	
	public void configInit() {
		table = view.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					evt.consume();
					tablePegaID(evt);
				}
			}
		});
		btnSelecionar = view.getBtnSelecionar();
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				int row_index =(int) table.getSelectedRow();
				int col_index =(int) table.getSelectedColumn();
				
				if (col_index < 0 || row_index < 0) return;
				
				int id = (Integer) table.getValueAt(row_index, col_index);
				selecionaContador(id);
			}
		});
		btnCadastrar = view.getBtnCadastrar();
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.ContadorGUI contadorView = new View.ContadorGUI();
				
				contadorView.getTabbedPane().setEnabledAt(0, false);;
				contadorView.getTabbedPane().setSelectedIndex(1);
				montarJanela(contadorView);
				atualizaTabela();
			}
		});
		btnPesquisar = view.getBtnPesquisar();
		atualizaTabela();
	}
	
	
	public void tablePegaID(MouseEvent evt) {
		
		selecionaContador(helper.tablePegaID(evt));
		
	}
	
	private void selecionaContador(int id) {
		ContadorDAO contadorDAO = new ContadorDAO();
		Model.Contador contador = null;
		try {
			contador = contadorDAO.selectPerID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		viewCliente.getController().setContador(contador);
		view.dispose();
	}
	
	private void montarJanela(JFrame view) {
		final JDialog frame = new JDialog(view);
		
		frame.setContentPane(view.getContentPane());
		frame.pack();
		frame.setAutoRequestFocus(true);
		frame.setModal(true); 
		frame.setSize(view.getSize());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
