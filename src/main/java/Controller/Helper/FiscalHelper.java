package Controller.Helper;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Cliente;
import Model.Contador;
import Model.Fiscal;
import Model.Versao;
import Model.DAO.ContadorDAO;
import Util.LoadingFile;
import View.FiscalGUI;

public class FiscalHelper {

	private final FiscalGUI view;

	public FiscalHelper(FiscalGUI view) {
		this.view = view;
		view.getTxtContador().setEditable(false);
	}

	public void preencherCmbMesFiscal(ArrayList<String> lstMes) {
		DefaultComboBoxModel<String> cmbMesFiscalModel = new DefaultComboBoxModel<String>();

		for (String mes : lstMes) {
			cmbMesFiscalModel.addElement(mes);
		}
		view.getCmbMesFiscal().setModel(cmbMesFiscalModel);
		Calendar calendario = Calendar.getInstance();
		view.getCmbMesFiscal().setSelectedIndex((int) calendario.get(Calendar.MONTH));

	}

	public void preencherCmbAnoFiscal(ArrayList<String> lstAno) {
		DefaultComboBoxModel<String> cmbAnoFiscalModel = new DefaultComboBoxModel<String>();

		for (String ano : lstAno) {
			cmbAnoFiscalModel.addElement(ano);
		}
		view.getCmbAnoFiscal().setModel(cmbAnoFiscalModel);

		Calendar calendario = Calendar.getInstance();

		int index = lstAno.indexOf(String.valueOf(calendario.get(Calendar.YEAR)));
		view.getCmbAnoFiscal().setSelectedIndex(index);
	}

	public void preencherCmbVersao(ArrayList<Versao> lstVersao) {
		DefaultComboBoxModel<Versao> comboBoxModel = (DefaultComboBoxModel<Versao>) view.getCmbVersao().getModel();

		for (Versao versao : lstVersao) {
			comboBoxModel.addElement(versao);
		}
	}

	public void preencherTabela(ArrayList<Cliente> clientes) {
		DefaultTableModel tableModel = new DefaultTableModel();

		// DefaultTableModel tableModel = (DefaultTableModel)
		// view.getTable().getModel(); // duplica ao atualizar 2vezes
		tableModel.setNumRows(0);

		tableModel.addColumn("ID");
		tableModel.addColumn("Nome");
		tableModel.addColumn("CNPJ");
		tableModel.addColumn("Telefone");
		tableModel.addColumn("Email");
		tableModel.addColumn("ID Remoto");
		tableModel.addColumn("EnviarDadosCont");
		tableModel.addColumn("Modificado");
		tableModel.addColumn("ArquivoNaPasta");

		LoadingFile arquivos = new LoadingFile();
		boolean arqInFolder;
		// 1646353 23897111
		int mes = view.getCmbMesFiscal().getSelectedIndex() + 1;
		int ano = Integer.parseInt(view.getCmbAnoFiscal().getSelectedItem().toString());
		for (Cliente cliente : clientes) {
			arqInFolder = arquivos.findFolder(cliente.getSerial(), mes, ano);

			tableModel.addRow(new Object[] { cliente.getId(), cliente.getNome(), cliente.getCnpj(),
					cliente.getTelefone(), cliente.getEmail(), cliente.getIdRemoto(),
					(cliente.getEnviarDadosContador() ? "Sim" : "Não"), cliente.getModificado(),
					arqInFolder ? "Sim" : "Não" });
		}

		view.getTable().setModel(tableModel);

	}

	public void preencherTabela2(ArrayList<Fiscal> lstFiscal) {
		DefaultTableModel tableModel = new DefaultTableModel();

		// DefaultTableModel tableModel = (DefaultTableModel)
		// view.getTable().getModel(); // duplica ao atualizar 2vezes
		tableModel.setNumRows(0);

		tableModel.addColumn("ID");
		tableModel.addColumn("Serial");
		tableModel.addColumn("Nome");
		tableModel.addColumn("ID Remoto");
		tableModel.addColumn("EnviarDadosCont");
		tableModel.addColumn("Status");
		tableModel.addColumn("ArquivoNaPasta");
		tableModel.addColumn("Modificado");

		LoadingFile arquivos = new LoadingFile();
		boolean arqInFolder;
		int mes = view.getCmbMesFiscal().getSelectedIndex() + 1;
		int ano = Integer.parseInt(view.getCmbAnoFiscal().getSelectedItem().toString());
		for (Fiscal fiscal : lstFiscal) {
			arqInFolder = arquivos.findFolder(fiscal.getCliente().getSerial(), mes, ano);

			tableModel.addRow(new Object[] { fiscal.getCliente().getId(), fiscal.getCliente().getSerial(),
					fiscal.getCliente().getNome(), fiscal.getCliente().getIdRemoto(),
					(fiscal.getCliente().getEnviarDadosContador() ? "Sim" : "Não"), fiscal.getStatus().getDescricao(),
					arqInFolder ? "Sim" : "Não", fiscal.getModificado() });
		}

		view.getTable().setModel(tableModel);

	}

	public void preencherCampos(Cliente cliente) {
		view.setId(cliente.getId());

		view.getTxtNome().setText(cliente.getNome());
		view.getTxtCNPJ().setText(cliente.getCnpj());
		view.getTxtTelefone().setText(cliente.getTelefone());
		view.getTxtSerial().setText(cliente.getSerial());

		view.getCmbVersao().setSelectedItem(cliente.getVersao());

		this.setContador(cliente.getContador());

		view.getTxtEmail().setText(cliente.getEmail());
		view.getTxtIdRemoto().setText(cliente.getIdRemoto());
		view.getChbEnviaDadosContador().setSelected(cliente.getEnviarDadosContador());
//		focusInTabCad(1);
	}

	public void focusInTabCad(int index) {
		view.getTabbedPane().setSelectedIndex(index);
	}

	public int getActiveTabCad() {
		return view.getTabbedPane().getSelectedIndex();
	}

	public void setContador(Contador contador) {

		view.setContadorId(contador.getId());
		view.getTxtContador().setText(contador.getNome());

	}

	public void bloquearCampos(Boolean value) {
		value = !value;
		view.getTxtNome().setEnabled(value);
		view.getTxtCNPJ().setEnabled(value);
		view.getTxtTelefone().setEnabled(value);
		view.getTxtSerial().setEnabled(value);

		view.getCmbVersao().setEnabled(value);
		view.getBtnProcuraContadorDetalhes().setEnabled(value);

		view.getTxtEmail().setEnabled(value);
		view.getTxtIdRemoto().setEnabled(value);
		view.getChbEnviaDadosContador().setEnabled(value);

	}

	public Cliente obterDados() {
		Cliente cliente = new Cliente(view.getId());
		cliente.setNome(view.getTxtNome().getText());

		cliente.setCnpj(view.getTxtCNPJ().getText());
		cliente.setTelefone(view.getTxtTelefone().getText());
		cliente.setSerial(view.getTxtSerial().getText().replaceAll("\\s+", ""));

		cliente.setVersao((Versao) view.getCmbVersao().getSelectedItem());

		ContadorDAO contadorDAO = new ContadorDAO();
		Contador contador = null;
		try {
			contador = contadorDAO.selectPerID(view.getContadorId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cliente.setContador(contador);

		cliente.setEmail(view.getTxtEmail().getText());
		cliente.setIdRemoto(view.getTxtIdRemoto().getText().replaceAll("\\s+", ""));
		cliente.setEnviarDadosContador(view.getChbEnviaDadosContador().isSelected());

		return cliente;
	}

	public Boolean validarCampos() {
		// FIXME melhorar forma de validacao dos campos, e criar mensagens tipo toolkit
		// para informar oque precisa ser preenchido!

		if (view.getTxtNome().getText().isEmpty() || view.getTxtCNPJ().getText().isEmpty()
				|| view.getTxtTelefone().getText().isEmpty() || view.getTxtSerial().getText().isEmpty()
				|| view.getContadorId() == -1) {
			return false;
		}
		return true;
	}

	public int getId() {
		return view.getId();
	}

	public int tablePegaID(MouseEvent evt) {
		JTable table = view.getTable();
		int row = table.rowAtPoint(evt.getPoint());
		int col = table.columnAtPoint(evt.getPoint());
		if (row >= 0 && col >= 0) {
			try {
				return (Integer) table.getModel().getValueAt(row, 0);
			} catch (Exception e) {
				return -1;
			}
		}
		return -1;
	}

	public void limparCampos() {
		view.setId(-1);
		view.getTxtNome().setText("");
		view.getTxtCNPJ().setText("");
		view.getTxtTelefone().setText("");
		view.getTxtSerial().setText("");

		view.getCmbVersao().setSelectedIndex(0);

		view.setContadorId(-1);
		view.getTxtContador().setText("");

		view.getTxtEmail().setText("");
		view.getTxtIdRemoto().setText("");
		view.getChbEnviaDadosContador().setSelected(false);

	}

	public void bloquearBotoes(Boolean value) {
		// TODO Auto-generated method stub
		value = !value;

	}
}
