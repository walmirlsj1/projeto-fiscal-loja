package Controller;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.Helper.VersaoHelper;
import Model.DAO.VersaoDAO;
import View.VersaoGUI;

public class VersaoController {
	private final VersaoGUI view;
	private final VersaoHelper helper;
	
	public VersaoController(View.VersaoGUI view) {
		this.view = view;
		this.helper = new VersaoHelper(view);
	}

	public void atualizaTabela() {
		VersaoDAO versaoDAO = new VersaoDAO();
		ArrayList<Model.Versao> tipoSys = new ArrayList<Model.Versao>();
		try {
			tipoSys = versaoDAO.selectAll();
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
		VersaoDAO versaoDAO = new VersaoDAO();
		int id = helper.tablePegaID(evt);
		Model.Versao versao = null;
		try {
			versao = versaoDAO.selectPerID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.preencherCampos(versao);
	}

	public void limpaTela() {
		helper.limpaTela();
	}

	public void salvar() {
		Model.Versao versao = helper.pegaCampos();
		if(versao == null) return;
		
		if (versao.getId() == -1) {
			//Cadastrar
			VersaoDAO versaoDAO = new VersaoDAO();
			try {
				versaoDAO.insert(versao);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//Editar
			VersaoDAO versaoDAO = new VersaoDAO();
			try {
				versaoDAO.update(versao);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		helper.limpaTela();
		atualizaTabela();
	}
	
	public void excluir() {
		Model.Versao versao = helper.pegaCampos();
		if(versao == null) return;
		
		VersaoDAO versaoDAO = new VersaoDAO();
		try {
			versaoDAO.delete(versao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		atualizaTabela();
	}
}
