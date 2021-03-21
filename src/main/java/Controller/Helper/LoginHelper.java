package Controller.Helper;

import Model.Usuario;
import View.LoginGUI;

public class LoginHelper {
	
	private final LoginGUI view;
	
	public LoginHelper(LoginGUI view) {
		this.view = view;
	}
	
	public Usuario obterModelo() { //Tipo USuario
		String username = view.getTxtLogin().getText();
		String senha = new String(view.getTxtSenha().getPassword());
		
		Usuario modelo = new Usuario(0, username, senha);
		return modelo;
//		return (new Usuario(1,"nome", "username", "123", "12312", "q2erf@gwdf.com", "10/04/1995","Admin"));
	}
	
	public void setModelo() {
//		String nome = modelo.getNome();
//		String senha = modelo.getSenha();
//		view.getTxtLogin().setText(nome);
//		view.getTxtSenha().setText(senha);
		
	}
	
	public void limparTela() {
		view.getTxtLogin().setText("");
		view.getTxtSenha().setText("");
	}
	
}
