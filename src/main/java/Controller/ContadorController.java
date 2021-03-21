package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controller.Helper.ContadorHelper;
import Model.Contador;
import Model.DAO.ContadorDAO;

import View.ContadorGUI;

public class ContadorController {

	private final ContadorGUI view;
	private final ContadorHelper helper;
	private int statusView = 0; // @fixme tem que Melhorar Enum? 0 para lista, 1 para visualizar, 2 para Novo, 3
								// para Editar //4 salvar

	private JButton btnEditar;
	private JButton btnNovo;
	private JButton btnCancelar;
	private JButton btnExcluir;

	private JTextField txtPesquisa;

	public ContadorController(View.ContadorGUI view) {
		this.view = view;
		this.helper = new ContadorHelper(view);
		init_controller();
		setStatusView(-1);
	}

	public void setStatusView(int statusView) {
		this.statusView = statusView;

		switch (statusView) {
		case -2: // Cancelar
			int id = view.getId();
			helper.limparCampos();
			if (id != -1) {
				carregaForm(id);
			}
			setStatusView(0);
			break;
		case -1: // Start
//			helper.bloquearBotoes(true);
			helper.bloquearCampos(true);
			btnEditar.setText("Editar");
			btnCancelar.setEnabled(false);
			btnNovo.setEnabled(true);
			helper.focusInTabCad(0);
			view.getTabbedPane().setEnabledAt(0, true);
			break;
		case 0: // Visualizacao
			helper.bloquearCampos(true);
			btnEditar.setText("Editar");
			btnCancelar.setEnabled(false);
			btnNovo.setEnabled(true);
			view.getTabbedPane().setEnabledAt(0, true);
			helper.focusInTabCad(1);
			break;
		case 1: // Novo
			helper.limparCampos();
			btnNovo.setEnabled(false);
			btnCancelar.setEnabled(true);
			helper.bloquearCampos(false);
			btnEditar.setText("Salvar");
			view.getTabbedPane().setEnabledAt(0, false);
			helper.focusInTabCad(1);
			break;
		case 2: // Edicao
			btnNovo.setEnabled(false);
			helper.bloquearCampos(false);
			btnCancelar.setEnabled(true);
			btnEditar.setText("Salvar");
			view.getTabbedPane().setEnabledAt(0, false);
			helper.focusInTabCad(1);
//			helper.(1);
			break;
		default:
			break;
		}
	}

	public void init_controller() {
		btnCancelar = view.getBtnCancelar();
		btnEditar = view.getBtnEditar();
		btnNovo = view.getBtnNovo();
		btnExcluir = view.getBtnExcluir();

		txtPesquisa = view.getTxtPesquisa();

		JTable table = view.getTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {

				if (evt.getClickCount() == 2) {
					evt.consume();
					carregaForm(-1);
				}
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if ((getStatusView() == 0 || getStatusView() == -1) && view.getId() != -1) {
					helper.focusInTabCad(1);
					setStatusView(2);
					// System.out.println("Apertei editar - mudei para Status 3 - Editar");
				} else if (getStatusView() == 1 || getStatusView() == 2) {
					salvarDados();
					// System.out.println("Apertei Salvar - mudei para Status 0 -
					// Visualiza(Salvei)");
				} else {
					view.mensagemView("Cadastro Contador", "Selecione um registro para ser editado! ", "Warning");
				}
			}
		});

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setStatusView(1);
				// System.out.println("Apertei novo - mudei para Status 2 - Salvar");
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatusView(-2);
			}
		});

		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pesquisaPor(txtPesquisa.getText());
				}

			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				setStatusView(-2);
//				System.out.println("N�O IMPLEMENTADO! AINDA");

				int id;
				if (helper.getActiveTabCad() == 0) {
					JTable table = view.getTable();
					int row = table.getSelectedRow();
					try {
						id = (Integer) table.getModel().getValueAt(row, 0);
					} catch (Exception e) {
						if (table.getModel().getRowCount() == 0) {
							view.mensagemView("Atenc�o", "Tabela est� vazia!", "");
						} else {
							view.mensagemView("Atenc�o", "Selecione um item da tabela para excluir!", "");
						}
						return;
					}
					carregaForm(id);
				} else {
					id = view.getId();
				}
				if (id == -1) {
					view.mensagemView("Atenc�o", "Selecione um item da tabela para excluir!", "");
					return;
				}
				int resp = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o item selecionado?",
						"Excluir item", JOptionPane.YES_NO_OPTION);

				if (resp == JOptionPane.YES_OPTION) {

					try {
						getContadorDAO().delete(getContadorTableId(id));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					helper.limparCampos();
					atualizaTabela();
					helper.focusInTabCad(0);
					JOptionPane.showMessageDialog(null, "Item excluido!");
				} else {
					JOptionPane.showMessageDialog(null, "A a��o de excluir o item foi cancelada!");
				}
			}
		});
		this.atualizaTabela();
	}

	public ContadorDAO getContadorDAO() {
		return new ContadorDAO(); // ContadorDAO clienteDAO = new ContadorDAO();
	}

	public void atualizaTabela() {
//		ContadorDAO clienteDAO = new ContadorDAO();

//		DAOBD.ContadorDAO clienteDAO = new DAOBD.ContadorDAO();
		ArrayList<Contador> lstContador = null;

		try {
			lstContador = getContadorDAO().selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		helper.preencherTabela(lstContador);
	}

	public void pesquisaPor(String value) {
		JTable table = view.getTable();
		int index = table.getSelectedColumn();
		String filter = table.getColumnName(index);
		ArrayList<Contador> lstContador = null;
		
		try {
			lstContador = getContadorDAO().selectFilter(value, filter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		helper.preencherTabela(lstContador);
	}

	public void carregaForm(int id) {
		JTable table = view.getTable();
		int row = table.getSelectedRow();
//        int col = table.getSelectedColumn();
		try {
			if (id == -1) {
				id = (Integer) table.getModel().getValueAt(row, 0);
			}

			Contador cliente = getContadorTableId(id);
			helper.preencherCampos(cliente);
//		carregaForm(helper.tablePegaID(evt));
			helper.focusInTabCad(1);

		} catch (Exception e) {
			System.out.println("ID n�o encontrado!");
		}
	}

	public Contador getContadorTableId(int id) {
//		ContadorDAO clienteDAO = new ContadorDAO();
		Contador contador = null;
		try {
			contador = getContadorDAO().selectPerID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contador;
	}

	public int getStatusView() {
		return statusView;
	}

	public void salvarDados() {
		if (helper.validarCampos()) {
			ContadorDAO contadorDAO = this.getContadorDAO();
			Contador contador = helper.obterDados();

			switch (getStatusView()) { // modo edicao
			case 1:
				try {
					contadorDAO.insert(contador);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					contadorDAO.update(contador);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			this.setStatusView(0);

			this.atualizaTabela();
		} else {
			view.mensagemView("Cadastro Contador", "Falha ao validar os campos, por favor preencha corretamente! ",
					"Warning");
		}

	}

}
