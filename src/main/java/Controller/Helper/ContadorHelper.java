package Controller.Helper;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.ContadorGUI;

public class ContadorHelper {

	private final ContadorGUI view;

	public ContadorHelper(ContadorGUI view) {
		this.view = view;
	}
	
	public void preencherTabela(ArrayList<Model.Contador> contadors) {
		DefaultTableModel tableModel = new DefaultTableModel();

		// DefaultTableModel tableModel = (DefaultTableModel)
		// view.getTable().getModel(); // duplica ao atualizar 2vezes
		tableModel.setNumRows(0);

		tableModel.addColumn("ID");
		tableModel.addColumn("Nome");
		tableModel.addColumn("CNPJ");
		tableModel.addColumn("Telefone");
		tableModel.addColumn("Email");
		tableModel.addColumn("Modificado");

		for (Model.Contador contador : contadors) {
			tableModel.addRow(new Object[] { contador.getId(), contador.getNome(), contador.getCnpj(),
					contador.getTelefone(), contador.getEmail(), contador.getModificado() });
		}

		view.getTable().setModel(tableModel);

	}

	public void preencherCampos(Model.Contador contador) {
		view.setId(contador.getId());

		view.getTxtNome().setText(contador.getNome());
		view.getTxtCNPJ().setText(contador.getCnpj());
		view.getTxtTelefone().setText(contador.getTelefone());
		view.getTxtEmail().setText(contador.getEmail());
//		focusInTabCad(1);
	}

	public void focusInTabCad(int index) {
		view.getTabbedPane().setSelectedIndex(index);
	}

	public int getActiveTabCad() {
		return view.getTabbedPane().getSelectedIndex();
	}

	public void bloquearCampos(Boolean value) {
		value = !value;
		view.getTxtNome().setEnabled(value);
		view.getTxtCNPJ().setEnabled(value);
		view.getTxtTelefone().setEnabled(value);

		view.getTxtEmail().setEnabled(value);

	}

	public Model.Contador obterDados() {
		Model.Contador contador = new Model.Contador(view.getId());
		contador.setNome(view.getTxtNome().getText());

		contador.setCnpj(view.getTxtCNPJ().getText());
		contador.setTelefone(view.getTxtTelefone().getText());

		contador.setEmail(view.getTxtEmail().getText());

		return contador;
	}

	public Boolean validarCampos() {
		// FIXME melhorar forma de validacao dos campos, e criar mensagens tipo toolkit
		// para informar oque precisa ser preenchido!

		if (view.getTxtNome().getText().isEmpty() || view.getTxtCNPJ().getText().isEmpty()
				|| view.getTxtTelefone().getText().isEmpty()) {
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

	}

	public void bloquearBotoes(Boolean value) {
		// TODO Auto-generated method stub
		value = !value;

	}
}

