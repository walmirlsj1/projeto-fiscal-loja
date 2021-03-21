package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.commons.mail.EmailException;

import Controller.Helper.FiscalHelper;
import Model.Cliente;
import Model.Fiscal;
import Model.Status;
import Model.Versao;
import Model.DAO.ClienteDAO;
import Model.DAO.FiscalDAO;
import Model.DAO.StatusDAO;
import Model.DAO.VersaoDAO;
import Util.CommonsMail;
import Util.GeradorFiscal;
import Util.LoadingFile;
import View.FiscalGUI;
import View.SelecionaContadorGUI;

public class FiscalController {

	private final FiscalGUI view;
	private final FiscalHelper helper;

	private JList<String> listFiles;

	private JButton btnGerarFiscal;
	private JButton btnEnviarPendentes;
	private JButton btnCarregarArquivos;

	private JButton btnEnviarEmail;
	private JButton btnConfigSMTP;

	private JComboBox<String> cmbMesFiscal;
	private JComboBox<String> cmbAnoFiscal;

	private JTextField txtPesquisa;

	private ClienteDAO clienteDAO;
	private FiscalDAO fiscalDAO;
	private StatusDAO statusDAO;

	private LoadingFile loadFiles;

	public FiscalController(View.FiscalGUI view) {
		this.view = view;
		this.helper = new FiscalHelper(view);

		clienteDAO = new ClienteDAO();
		fiscalDAO = new FiscalDAO();
		statusDAO = new StatusDAO();
		loadFiles = new LoadingFile();
		init_controller();
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

	public void init_controller() {
		this.carregaCmbVersao();
		this.carregaCmbMesFiscal();
		this.carregaCmbAnoFiscal();

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

		txtPesquisa = view.getTxtPesquisa();

		cmbMesFiscal = view.getCmbMesFiscal();
		cmbAnoFiscal = view.getCmbAnoFiscal();

		btnGerarFiscal = view.getBtnGerarFiscal();
		btnEnviarPendentes = view.getBtnEnviarPendentes();
		btnCarregarArquivos = view.getBtnCarregarArquivos();
		listFiles = view.getListFiles();

		btnConfigSMTP = view.getBtnConfigSMTP();
		btnEnviarEmail = view.getBtnEnviarEmail();

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

		JComboBox<Model.Versao> cmbVersao = view.getCmbVersao();
		cmbVersao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				Object item = evt.getSource();

//				System.out.println("ID: " + cmbVersao.getSelectedIndex() + " Value: " + cmbVersao.getSelectedItem().toString());
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

		cmbMesFiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				atualizaTabela2();
			}
		});

		cmbAnoFiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				atualizaTabela2();
			}
		});

		btnGerarFiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Não implementado! - btnGerarFiscal");

				GeradorFiscal gerFiscal = new GeradorFiscal();
				int mes = view.getCmbMesFiscal().getSelectedIndex() + 1;
				int ano = Integer.parseInt(view.getCmbAnoFiscal().getSelectedItem().toString());

				try {
					System.out.println(gerFiscal.gerarFiscal(clienteDAO.selectAll(), mes, ano) + " " + mes + " " + ano);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				atualizaTabela2();
			}

		});

		btnCarregarArquivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				preencheListaArquivos();
			}
		});

		btnConfigSMTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Não implementado! - btnConfigSMTP");
			}
		});

		btnEnviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

//				lstFiscal = fiscalDAO.selectFilterClienteMesAno(cliente, mes, ano);
				enviaEmail(view.getId());
			}
		});
		btnEnviarPendentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				System.out.println("Não implementado! - btnEnviarPendentes");
				ArrayList<Cliente> lstCliente = null;
				try {
					lstCliente = clienteDAO.selectAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int mes = view.getCmbMesFiscal().getSelectedIndex() + 1;
				int ano = Integer.parseInt(view.getCmbAnoFiscal().getSelectedItem().toString());
				if (lstCliente != null) {
					for (Cliente cliente : lstCliente) {

						Fiscal fiscal = null;
						try {
							fiscal = fiscalDAO.selectFilterClienteMesAno(cliente, mes, ano).get(0);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						System.out.println(":: >> #ID fiscal" + fiscal.getStatus().getId());
						if ((fiscal.getStatus().getId() == 2 || fiscal.getStatus().getId() == 3) && fiscal != null) {
							carregaForm(cliente.getId());

							if (JOptionPane.showConfirmDialog(null,
									"Confera os dados! \n Antes de enviar, \n Posso enviar?", "Atenção!!!",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								enviaEmail(cliente.getId());
							}
						}
					}
					atualizaTabela2();
				}
			}
		});

		this.atualizaTabela2();
	}

	public void preencheListaArquivos() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		List<Path> files = getListaArquivos();
		if (files == null)
			return;
		for (Path file : files) {
			listModel.addElement(file.getFileName().toString());

			System.out.println(file.toString());
		}

		listFiles.setModel(listModel);
	}

	public List<Path> getListaArquivos() {
		Cliente cliente = null;
		try {
			cliente = clienteDAO.selectPerID(view.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (cliente != null) {
			return loadFiles.getFilesFolderPath(cliente.getSerial());
		}
		return null;
	}

	public void enviaEmail(int id) {

		Cliente cliente = null;
		Fiscal fiscal = null;
		Status status_ok = null;
		Status status_nok = null;

		int mes = view.getCmbMesFiscal().getSelectedIndex() + 1;
		int ano = Integer.parseInt(view.getCmbAnoFiscal().getSelectedItem().toString());

		try {
			status_ok = statusDAO.selectPerID(4);
			status_nok = statusDAO.selectPerID(3);
			cliente = clienteDAO.selectPerID(id);
			fiscal = fiscalDAO.selectFilterClienteMesAno(cliente, mes, ano).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fiscal.getStatus().getId() == 1 || fiscal.getStatus().getId() == 4 || fiscal == null)
			return;

		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##.###.###/####-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String cnpj = null;
		try {
			cnpj = maskData.valueToString(fiscal.getCliente().getCnpj());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		CommonsMail email = new CommonsMail();
		List<Path> listaArquivos = getListaArquivos();

		if (listaArquivos != null) {
			email.addAnexo(listaArquivos);
		}
		email.setDestinatario_email(fiscal.getCliente().getContador().getEmail());
		email.setDestinatario_nome(fiscal.getCliente().getContador().getNome());
		email.setMsg("Segue em anexo, os arquivos fiscais, \n\n\n att. Walmir Luiz"); // username usuario logado
		email.setTitulo("Arquivos Fiscais - " + obterMesString(mes - 1) + "/" + ano + " - "
				+ fiscal.getCliente().getNome() + " - " + cnpj);

		try {
			email.enviaEmailComAnexo();
			fiscal.setStatus(status_ok);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fiscal.setStatus(status_nok);
		}
		try {
			fiscalDAO.update(fiscal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String obterMesString(int mes) {
		String[] meses = { "JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ" };
		return meses[mes];
	}

	private void carregaCmbMesFiscal() {
		ArrayList<String> lstMes = new ArrayList<String>(Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio",
				"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"));

		helper.preencherCmbMesFiscal(lstMes);
	}

	private void carregaCmbAnoFiscal() {
		ArrayList<String> lstAno = new ArrayList<String>(Arrays.asList("2019", "2020", "2021", "2022", "2023"));

		helper.preencherCmbAnoFiscal(lstAno);

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
		ArrayList<Model.Cliente> clientes = null;
		try {
			clientes = this.clienteDAO.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		helper.preencherTabela(clientes);
	}

	public void atualizaTabela2() {
		FiscalDAO fiscalDAO = new FiscalDAO();
		ArrayList<Model.Fiscal> lstFiscal = null;
		int mes = view.getCmbMesFiscal().getSelectedIndex() + 1;
		int ano = Integer.parseInt(view.getCmbAnoFiscal().getSelectedItem().toString());

		try {
			lstFiscal = fiscalDAO.selectFilterMesAno(mes, ano);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		helper.preencherTabela2(lstFiscal);
	}

	public void pesquisaPor(String value) {
		JTable table = view.getTable();
		int index = table.getSelectedColumn();
		String filter = table.getColumnName(index);
		ArrayList<Model.Cliente> clientes = null;
		try {
			clientes = this.clienteDAO.selectFilter(value, filter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		helper.preencherTabela(clientes);
	}

	public void carregaForm(int id) {
		JTable table = view.getTable();
		int row = table.getSelectedRow();
		try {
			if (id == -1) {
				id = (Integer) table.getModel().getValueAt(row, 0);
			}

			Model.Cliente cliente = getClienteTableId(id);
			helper.preencherCampos(cliente);
			helper.focusInTabCad(1);
			preencheListaArquivos();
		} catch (Exception e) {
			System.out.println("ID não encontrado!");
		}
	}

	public Model.Cliente getClienteTableId(int id) {
		Model.Cliente cliente = null;
		try {
			cliente = this.clienteDAO.selectPerID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}

}
