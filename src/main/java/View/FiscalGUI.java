package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.FiscalController;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Model.Versao;
import javax.swing.JList;
import java.awt.Font;
import java.awt.Color;

public class FiscalGUI extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7984720303972360445L;

	private final FiscalController controller;
	
	private JPanel contentPane;
	
	private JTabbedPane tabbedPane;

	private int id;
	private int fiscal_id;
	private int contadorId;
	
	private JPanel panel_lista;
	private JPanel panel_detalhe;

	private JComboBox<String> cmbMesFiscal;
	private JComboBox<String> cmbAnoFiscal;
	
	private JTable table;
	private JTextField txtNome;
	private JFormattedTextField txtCNPJ;
	private JFormattedTextField txtTelefone;
	private JTextField txtSerial;
	private JTextField txtContador;
	private JComboBox<Model.Versao> cmbVersao;
	private JTextField txtEmail;
	private JTable tblFiscal;
	private JTextField txtPesquisa;
	private JTextField txtIdRemoto;
	private JCheckBox chbEnviaDadosContador;
	private JButton btnProcuraContadorDetalhes;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	
	private JList<String> listFiles;
	
	private JButton btnGerarFiscal;
	private JButton btnEnviarPendentes;
	private JButton btnCarregarArquivos;
	
	private JButton btnEnviarEmail;
	private JButton btnConfigSMTP;
	
	
	/**
	 * Create the frame.
	 */
	public FiscalGUI() {
		initComponents();
		this.controller = new FiscalController(this);
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 808, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new LineBorder(Color.DARK_GRAY));
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(63, 0, 74, 44);
		panel_2.add(btnEditar);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(0, 0, 66, 44);
		panel_2.add(btnNovo);
		
		txtPesquisa = new JTextField();
		
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(600, 11, 159, 20);
		panel_2.add(txtPesquisa);
		
		JLabel label_2 = new JLabel("Pesquisar");
		label_2.setBounds(548, 15, 66, 14);
		panel_2.add(label_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 769, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Mes");
		lblNewLabel_8.setBounds(543, 14, 34, 14);
		panel.add(lblNewLabel_8);
		
		cmbMesFiscal = new JComboBox();
		cmbMesFiscal.setBounds(574, 11, 89, 20);
		panel.add(cmbMesFiscal);
		
		cmbAnoFiscal = new JComboBox();
		cmbAnoFiscal.setBounds(697, 11, 75, 20);
		panel.add(cmbAnoFiscal);
		
		JLabel lblNewLabel_9 = new JLabel("Ano");
		lblNewLabel_9.setBounds(673, 14, 24, 14);
		panel.add(lblNewLabel_9);
		
		btnGerarFiscal = new JButton("Gerar Fiscal >>");
		btnGerarFiscal.setBounds(411, 10, 122, 23);
		panel.add(btnGerarFiscal);
		
		btnEnviarPendentes = new JButton("Enviar Pendentes");
		btnEnviarPendentes.setBounds(267, 10, 134, 23);
		panel.add(btnEnviarPendentes);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_6.setBounds(0, 0, 122, 42);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("FISCAL");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_10.setBounds(20, 11, 92, 20);
		panel_6.add(lblNewLabel_10);
		
		btnConfigSMTP = new JButton("Configura\u00E7\u00E3o");
		btnConfigSMTP.setBounds(132, 10, 101, 23);
		panel.add(btnConfigSMTP);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(136, 0, 80, 44);
		panel_2.add(btnCancelar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(216, 0, 89, 44);
		panel_2.add(btnExcluir);
		
		panel_lista = new JPanel();
		tabbedPane.addTab("Listagem", null, panel_lista, null);
		panel_lista.setLayout(null);
		
		table = new JTable() {
					
					private static final long serialVersionUID = -1402267673119957797L;

					public boolean editCellAt(int row, int column, java.util.EventObject e) {
		         		return false;
		         }};
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 777, 504);
		panel_lista.add(scrollPane);
		
		panel_detalhe = new JPanel();
		tabbedPane.addTab("Detalhes", null, panel_detalhe, null);
		panel_detalhe.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_4.setBounds(10, 11, 749, 100);
		panel_detalhe.add(panel_4);
		panel_4.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("email@servidor.com");
		txtEmail.setBounds(428, 65, 268, 20);
		panel_4.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel label = new JLabel("E-mail");
		label.setBounds(382, 68, 46, 14);
		panel_4.add(label);
		
		JLabel lblNewLabel_4 = new JLabel("Serial");
		lblNewLabel_4.setBounds(10, 68, 46, 14);
		panel_4.add(lblNewLabel_4);
		
		txtSerial = new JTextField();
		txtSerial.setToolTipText("XXX0000000000X0");
		txtSerial.setBounds(46, 65, 156, 20);
		panel_4.add(txtSerial);
		txtSerial.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Contador");
		lblNewLabel_5.setBounds(10, 40, 57, 14);
		panel_4.add(lblNewLabel_5);
		
		txtContador = new JTextField();
		txtContador.setBounds(66, 37, 218, 20);
		panel_4.add(txtContador);
		txtContador.setColumns(10);
		
		btnProcuraContadorDetalhes = new JButton("Procurar Contador");
		btnProcuraContadorDetalhes.setBounds(286, 36, 38, 23);
		panel_4.add(btnProcuraContadorDetalhes);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 14, 38, 14);
		panel_4.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(46, 11, 278, 20);
		panel_4.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CNPJ");
		lblNewLabel_2.setBounds(334, 14, 48, 14);
		panel_4.add(lblNewLabel_2);
		
		txtCNPJ = new JFormattedTextField();
		txtCNPJ.setToolTipText("99.999.999/9999-99");
		txtCNPJ.setBounds(382, 11, 128, 20);
		panel_4.add(txtCNPJ);
		txtCNPJ.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setBounds(332, 40, 59, 14);
		panel_4.add(lblNewLabel_3);
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.setToolTipText("(67) 9 9999-9999");
		txtTelefone.setBounds(392, 37, 118, 20);
		panel_4.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Vers\u00E3o");
		lblNewLabel_6.setBounds(214, 68, 46, 14);
		panel_4.add(lblNewLabel_6);
		
		cmbVersao = new JComboBox<Model.Versao>();
		cmbVersao.setBounds(256, 65, 118, 20);
		panel_4.add(cmbVersao);
		
		JLabel lblNewLabel = new JLabel("ID Remoto");
		lblNewLabel.setBounds(522, 14, 59, 14);
		panel_4.add(lblNewLabel);
		
		txtIdRemoto = new JTextField();
		txtIdRemoto.setBounds(584, 11, 103, 20);
		panel_4.add(txtIdRemoto);
		txtIdRemoto.setColumns(10);
		
		chbEnviaDadosContador = new JCheckBox("Enviar Dados Contador");
		chbEnviaDadosContador.setBounds(516, 36, 165, 23);
		panel_4.add(chbEnviaDadosContador);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 122, 749, 298);
		panel_detalhe.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 13, 375, 69);
		panel_5.add(scrollPane_1);
		
		tblFiscal = new JTable();
		scrollPane_1.setViewportView(tblFiscal);
		
		JLabel lblNewLabel_7 = new JLabel("Fiscal informa\u00E7\u00F5es");
		lblNewLabel_7.setBounds(0, 0, 278, 14);
		panel_5.add(lblNewLabel_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(385, 13, 186, 280);
		panel_5.add(panel_1);
		panel_1.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_1.setLayout(null);
		
		listFiles = new JList();
		listFiles.setBounds(0, 0, 186, 240);
		panel_1.add(listFiles);
		listFiles.setBorder(new LineBorder(Color.DARK_GRAY));
		
		btnCarregarArquivos = new JButton("Carregar Arquivos");
		btnCarregarArquivos.setBounds(10, 246, 166, 23);
		panel_1.add(btnCarregarArquivos);
		
		JLabel lblArquivosDaPasta = new JLabel("Arquivos da Pasta");
		lblArquivosDaPasta.setBounds(385, 0, 168, 14);
		panel_5.add(lblArquivosDaPasta);
		
		JLabel lblOpes = new JLabel("Op\u00E7\u00F5es");
		lblOpes.setBounds(581, 0, 140, 14);
		panel_5.add(lblOpes);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(581, 13, 168, 280);
		panel_5.add(panel_3);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_3.setLayout(null);
		
		btnEnviarEmail = new JButton("Enviar");
		btnEnviarEmail.setBounds(10, 11, 148, 23);
		panel_3.add(btnEnviarEmail);
		
		JButton btnNaoFuncional = new JButton("finalizar");
		btnNaoFuncional.setBounds(10, 34, 148, 23);
		panel_3.add(btnNaoFuncional);
		
		JButton btnAtivar = new JButton("Ativar");
		btnAtivar.setBounds(10, 56, 148, 23);
		panel_3.add(btnAtivar);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(10, 79, 148, 23);
		panel_3.add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setBounds(10, 102, 148, 23);
		panel_3.add(button_3);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_8.setBounds(0, 96, 375, 197);
		panel_5.add(panel_8);
		
		JLabel lblOutros = new JLabel("Outros");
		lblOutros.setBounds(0, 82, 278, 14);
		panel_5.add(lblOutros);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 422, 749, 38);
		panel_detalhe.add(panel_7);
		panel_7.setLayout(null);
		contentPane.setLayout(gl_contentPane);
	}

	
	
	public JComboBox<String> getCmbMesFiscal() {
		return cmbMesFiscal;
	}

	public void setCmbMesFiscal(JComboBox<String> cmbMesFiscal) {
		this.cmbMesFiscal = cmbMesFiscal;
	}

	public JComboBox<String> getCmbAnoFiscal() {
		return cmbAnoFiscal;
	}

	public void setCmbAnoFiscal(JComboBox<String> cmbAnoFiscal) {
		this.cmbAnoFiscal = cmbAnoFiscal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JFormattedTextField getTxtCNPJ() {
		
		return txtCNPJ;
	}

	public void setTxtCNPJ(JFormattedTextField txtCNPJ) {
		this.txtCNPJ = txtCNPJ;
	}

	public JFormattedTextField getTxtTelefone() {
		return txtTelefone;
	}

	public void setTxtTelefone(JFormattedTextField txtTelefone) {
		this.txtTelefone = txtTelefone;
	}

	public JTextField getTxtSerial() {
		return txtSerial;
	}

	public void setTxtSerial(JTextField txtSerial) {
		this.txtSerial = txtSerial;
	}

	public JTextField getTxtContador() {
		return txtContador;
	}

	public void setTxtContador(JTextField txtContador) {
		this.txtContador = txtContador;
	}

	public JComboBox<Model.Versao> getCmbVersao() {
		return cmbVersao;
	}

	public void setCmbVersao(JComboBox<Model.Versao> cmbVersao) {
		this.cmbVersao = cmbVersao;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JTable getTblFiscal() {
		return tblFiscal;
	}

	public void setTblFiscal(JTable tblFiscal) {
		this.tblFiscal = tblFiscal;
	}

	public JTextField getTxtPesquisa() {
		return txtPesquisa;
	}

	public void setTxtPesquisa(JTextField txtPesquisa) {
		this.txtPesquisa = txtPesquisa;
	}

	
	public JButton getBtnProcuraContadorDetalhes() {
		return btnProcuraContadorDetalhes;
	}

	public void setBtnProcuraContadorDetalhes(JButton btnProcuraContadorDetalhes) {
		this.btnProcuraContadorDetalhes = btnProcuraContadorDetalhes;
	}

	public JList<String> getListFiles() {
		return listFiles;
	}

	public void setListFiles(JList<String> listFiles) {
		this.listFiles = listFiles;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public int getFiscal_id() {
		return fiscal_id;
	}

	public void setFiscal_id(int fiscal_id) {
		this.fiscal_id = fiscal_id;
	}

	public JTextField getTxtIdRemoto() {
		return txtIdRemoto;
	}

	public void setTxtIdRemoto(JTextField txtIdRemoto) {
		this.txtIdRemoto = txtIdRemoto;
	}

	public JCheckBox getChbEnviaDadosContador() {
		return chbEnviaDadosContador;
	}

	public void setChbEnviaDadosContador(JCheckBox chbEnviaDadosContador) {
		this.chbEnviaDadosContador = chbEnviaDadosContador;
	}

	public int getContadorId() {
		return contadorId;
	}

	public void setContadorId(int contadorId) {
		this.contadorId = contadorId;
	}

	public JButton getBtnNovo() {
		return btnNovo;
	}

	public void setBtnNovo(JButton btnNovo) {
		this.btnNovo = btnNovo;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(JButton btnExcluir) {
		this.btnExcluir = btnExcluir;
	}
	
	public JButton getBtnGerarFiscal() {
		return btnGerarFiscal;
	}

	public void setBtnGerarFiscal(JButton btnGerarFiscal) {
		this.btnGerarFiscal = btnGerarFiscal;
	}

	public JButton getBtnEnviarPendentes() {
		return btnEnviarPendentes;
	}

	public void setBtnEnviarPendentes(JButton btnEnviarPendentes) {
		this.btnEnviarPendentes = btnEnviarPendentes;
	}

	public JButton getBtnCarregarArquivos() {
		return btnCarregarArquivos;
	}

	public void setBtnCarregarArquivos(JButton btnCarregarArquivos) {
		this.btnCarregarArquivos = btnCarregarArquivos;
	}

	public JPanel getPanel_lista() {
		return panel_lista;
	}

	public void setPanel_lista(JPanel panel_lista) {
		this.panel_lista = panel_lista;
	}

	public JPanel getPanel_detalhe() {
		return panel_detalhe;
	}

	public void setPanel_detalhe(JPanel panel_detalhe) {
		this.panel_detalhe = panel_detalhe;
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	public FiscalController getController() {
		return controller;
	}
	
	public JButton getBtnEnviarEmail() {
		return btnEnviarEmail;
	}

	public void setBtnEnviarEmail(JButton btnEnviarEmail) {
		this.btnEnviarEmail = btnEnviarEmail;
	}

	public JButton getBtnConfigSMTP() {
		return btnConfigSMTP;
	}

	public void setBtnConfigSMTP(JButton btnConfigSMTP) {
		this.btnConfigSMTP = btnConfigSMTP;
	}

	public void mensagemView(String title, String msg, String nivel) {
		JOptionPane.showMessageDialog(new JFrame(), msg, title,
		        JOptionPane.ERROR_MESSAGE);
	}
}
