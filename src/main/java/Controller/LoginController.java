package Controller;

import java.awt.event.KeyEvent;
import java.sql.SQLException;

import Controller.Helper.LoginHelper;
import Model.Usuario;
import Model.DAO.UsuarioDAO;
import View.LoginGUI;
import View.MainWinGUI;


public class LoginController {
	
	private final LoginGUI view;
	private LoginHelper helper;
	
	public LoginController(LoginGUI view) {
		this.view = view;
		this.helper = new LoginHelper(view);
		
	}
	
	public void login() {
		Usuario usuario = helper.obterModelo();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarioAutenticado = null;
		try {
			usuarioAutenticado = usuarioDAO.selectPorNomeESenha(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(usuarioAutenticado != null) {
			MainWinGUI main = new MainWinGUI(usuarioAutenticado);
			main.setVisible(true);
			view.dispose();
		} else {
			
			view.exibeMensagem("Usuario ou senha invalidos!");
//			helper.limparTela();
			focusLogin();
		}
	}
	
	public void focusLogin() {
       	view.getTxtLogin().requestFocus();
	}
	
	public void focusSenha(KeyEvent e) {
		int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
        	   view.getTxtSenha().requestFocus();
        }
	}
	
	public void focusEnter(KeyEvent e) {
		int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
        	view.getBtnEntrar().requestFocus();
        }
	}
	
	public void keyPressLogin(KeyEvent e) {
		int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
        	login();
        }
	}
}
