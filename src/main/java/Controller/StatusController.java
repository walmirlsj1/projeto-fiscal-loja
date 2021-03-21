package Controller;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Helper.StatusHelper;
import Model.DAO.StatusDAO;
import View.StatusGUI;

public class StatusController {
	
	private final StatusGUI view;
	private final StatusHelper helper;
	
	public StatusController(StatusGUI view) {
		this.view = view;
		this.helper = new StatusHelper(view);
	}
	
	public void atualizaTabela() {
		StatusDAO statusDAO = new StatusDAO();
		ArrayList<Model.Status> tipoSys = new ArrayList<Model.Status>();
		try {
			tipoSys = statusDAO.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.preencherTabela(tipoSys);
	}
	
	public void configInit() {
		helper.configInit();
		atualizaTabela();
	}
	
	
	public void tablePegaID(MouseEvent evt) {
		StatusDAO statusDAO = new StatusDAO();
		int id = helper.tablePegaID(evt);
		Model.Status status = null;
		try {
			status = statusDAO.selectPerID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.preencherCampos(status);
	}

	public void limpaTela() {
		helper.limpaTela();
	}

	public void salvar() {
		Model.Status status = helper.pegaCampos();
		if(status == null) return;
		
		if (status.getId() == -1) {
			//Cadastrar
			StatusDAO statusDAO = new StatusDAO();
			try {
				statusDAO.insert(status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//Editar
			StatusDAO statusDAO = new StatusDAO();
			try {
				statusDAO.update(status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		helper.limpaTela();
		atualizaTabela();
	}
	
	public void excluir() {
		Model.Status status = helper.pegaCampos();
		if(status == null) return;
		
		StatusDAO statusDAO = new StatusDAO();
		try {
			statusDAO.delete(status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		atualizaTabela();
	}
}
