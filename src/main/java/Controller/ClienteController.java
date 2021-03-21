package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Controller.Helper.ClienteHelper;
import Model.DAO.ClienteDAO;
import Model.DAO.VersaoDAO;
import View.ClienteGUI;
import View.SelecionaContadorGUI;

public class ClienteController {

	private final ClienteGUI view;
	private final ClienteHelper helper;
	private int statusView = 0; // @fixme tem que Melhorar Enum? 0 para lista, 1 para visualizar, 2 para Novo, 3
								// para Editar //4 salvar
	private JTextField txtCNPJ;
	private JTextField txtTelefone;
	private JButton btnEditar;
	private JButton btnNovo;
	private JButton btnCancelar;
	private JButton btnExcluir;

	private JTextField txtPesquisa;

	public ClienteController(View.ClienteGUI view) {
		this.view = view;
		this.helper = new ClienteHelper(view);
		init_controller();
		setStatusView(-1);
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
		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##.###.###/####-##");
//			"99.999.999/9999-99"(67) 9 9999-9999
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
	    maskData.install(view.getTxtCNPJ());   
	    
//	    MaskFormatter maskCep = new MaskFormatter("#####-###"); 
//	    maskCep.install(jCepResidencial);
//	    maskCep.install(jCepComercial);
	    
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
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
//				JTable table = view.getTable();
//				int row = table.getSelectedRow();
//		        int col = table.getSelectedColumn();
//		        carregaForm((Integer) table.getModel().getValueAt(row, 0));
//				tablePegaID(evt);
			}
		});

		JComboBox<Model.Versao> cmbVersao = view.getCmbVersao();
		cmbVersao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				Object item = evt.getSource();

//				System.out.println("ID: " + cmbVersao.getSelectedIndex() + " Value: " + cmbVersao.getSelectedItem().toString());
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
					view.mensagemView("Cadastro Cliente", "Selecione um registro para ser editado! ", "Warning");
				}
			}
		});

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setStatusView(1);
				// System.out.println("Apertei novo - mudei para Status 2 - Salvar");
			}
		});
		JButton btnProcuraContadorDetalhes = view.getBtnProcuraContadorDetalhes();
		btnProcuraContadorDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JFrame janela = new SelecionaContadorGUI(view);
				montarJanela(janela);
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatusView(-2);
			}
		});

//		txtPesquisa.getDocument().addDocumentListener(
//                new DocumentListener() {
//                    public void changedUpdate(DocumentEvent e) {
//                    	System.out.println("Changed");
//                    }
//                    public void insertUpdate(DocumentEvent e) {
//                    	System.out.println("Insert");
//                    }
//                    public void removeUpdate(DocumentEvent e) {
//                    	System.out.println("Remove");
//                    }
//                });

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
//				System.out.println("NÃO IMPLEMENTADO! AINDA");
				
				int id;
				if (helper.getActiveTabCad() == 0) {
					JTable table = view.getTable();
					int row = table.getSelectedRow();
					id = (Integer) table.getModel().getValueAt(row, 0);
					
					carregaForm(id);
				} else {
					id = view.getId();
				}
				if (id == -1) {
					view.mensagemView("Atencão", "Selecione um item da tabela para excluir!", "" );
					return;
				}
				int resp = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o item selecionado?",
						"Excluir item", JOptionPane.YES_NO_OPTION);

				if (resp == JOptionPane.YES_OPTION) {
					
					
					try {
						getClienteDAO().delete(getClienteTableId(id));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					helper.limparCampos();
					atualizaTabela();
					helper.focusInTabCad(0);
					JOptionPane.showMessageDialog(null, "Item excluido!");
				} else {
					JOptionPane.showMessageDialog(null, "A ação de excluir o item foi cancelada!");
				}
			}
		});
		this.atualizaTabela();
		this.carregaCmbVersao();
	}

	public ClienteDAO getClienteDAO() {
		return new ClienteDAO(); //ClienteDAO clienteDAO = new ClienteDAO();
	}
	public void carregaCmbVersao() {
		VersaoDAO versaoDAO = new VersaoDAO();
		
		ArrayList<Model.Versao> lstVersao = null;
		try {
			lstVersao = versaoDAO.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.preencherCmbVersao(lstVersao);
	}

	public void atualizaTabela() {
//		ClienteDAO clienteDAO = new ClienteDAO();

//		Model.DAOBD.ClienteDAO clienteDAO = new Model.DAOBD.ClienteDAO();
		ArrayList<Model.Cliente> clientes = null;
//		try {
		try {
			clientes = getClienteDAO().selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		helper.preencherTabela(clientes);
	}

	public void pesquisaPor(String value) {
		JTable table = view.getTable();
		int index = table.getSelectedColumn();
		String filter = table.getColumnName(index);
//		carregaForm((Integer) table.getModel().getValueAt(0, col));

//		ClienteDAO clienteDAO = new ClienteDAO();

//		Model.DAOBD.ClienteDAO clienteDAO = new Model.DAOBD.ClienteDAO();
		ArrayList<Model.Cliente> clientes = null;
//		try {
		try {
			clientes = getClienteDAO().selectFilter(value, filter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		helper.preencherTabela(clientes);
	}

	public void carregaForm(int id) {
		JTable table = view.getTable();
		int row = table.getSelectedRow();
//        int col = table.getSelectedColumn();
		try {
			if (id == -1) {
				id = (Integer) table.getModel().getValueAt(row, 0);
			}

			Model.Cliente cliente = getClienteTableId(id);
			helper.preencherCampos(cliente);
//		carregaForm(helper.tablePegaID(evt));
			helper.focusInTabCad(1);

		} catch (Exception e) {
			System.out.println("ID não encontrado!");
		}
	}

	public Model.Cliente getClienteTableId(int id) {
//		ClienteDAO clienteDAO = new ClienteDAO();
		Model.Cliente cliente = null;
		try {
			cliente = getClienteDAO().selectPerID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}

	public int getStatusView() {
		return statusView;
	}

	public void salvarDados() {
		if (helper.validarCampos()) {
//			Object clienteDAO = new Model.DAO.ClienteDAO();
			Model.Cliente cliente = helper.obterDados();

			switch (getStatusView()) { // modo edicao
			case 1:
				try {
					getClienteDAO().insert(cliente);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 2:
				try {
					getClienteDAO().update(cliente);
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
			view.mensagemView("Cadastro Cliente", "Falha ao validar os campos, por favor preencha corretamente! ",
					"Warning");
		}

	}

	public void setContador(Model.Contador contador) {
		helper.setContador(contador);
	}

}
