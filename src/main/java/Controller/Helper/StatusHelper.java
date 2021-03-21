package Controller.Helper;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.StatusGUI;

public class StatusHelper {
	
	public final StatusGUI view;
	
	public StatusHelper(StatusGUI view) {
		this.view = view;
	}
	
	public void preencherCampos(Model.Status versao) {
		view.setId(Integer.valueOf(versao.getId()));
		view.getTxtDescricao().setText(versao.getDescricao());
	}
	
	public int getId() {
		return view.getId();
	}
	
	public Model.Status pegaCampos() {
		String status = view.getTxtDescricao().getText();
		
		if(!status.isEmpty())
			return new Model.Status(this.getId(), status, view.getUsuario());
		
		return null;
	}
	
	
	public void preencherTabela(ArrayList<Model.Status> lstStatus) {
		//(DefaultTableModel) view.getTable().getModel()
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.setNumRows(0);
//		tableModel.setColumnCount(0);
		
		dataModel.addColumn("ID");
		dataModel.addColumn("Status");
		dataModel.addColumn("Usuario");
		dataModel.addColumn("Modificado");

		
		for(Model.Status status : lstStatus) {
			dataModel.addRow(new Object[] {
					status.getId(),
					status.getDescricao(),
					status.getUsuario().getUsername(),
					status.getModificado()
//					cliente.getNome(),
//					cliente.getCnpj(),
//					cliente.getTelefone(),
//					cliente.getEmail(),
//					cliente.getModificado()
			});
		}
		view.getTable().setModel(dataModel);
	}
	
	public void configInit() {
//		view.getTxtId().setEditable(false);
//		view.getTxtModificado().setEditable(false);
//		view.getTxtUser().setText(view.getUsuario().getUsername());
////		view.getTxtUser().setEnabled(false);
//		view.getTxtUser().setEditable(false);
		view.getTxtDescricao().setText("");;
		view.setId(-1);
	}
	
	public int tablePegaID(MouseEvent evt) {
		JTable table = view.getTable();
		int row = table.rowAtPoint(evt.getPoint());
        int col = table.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
//        	System.out.println("Clico na Row: " + row + " Col: " + col);
        	return (Integer) table.getModel().getValueAt(row, 0);
        }
        return -1;
	}
	
	public void limpaTela() {
		view.getTxtDescricao().setText("");;
		view.setId(-1);
	}
}
