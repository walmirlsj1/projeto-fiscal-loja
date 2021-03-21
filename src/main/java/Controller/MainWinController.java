package Controller;

import javax.swing.JDialog;
import javax.swing.JFrame;

import View.ClienteGUI;
import View.ContadorGUI;
import View.FiscalGUI;
import View.MainWinGUI;
import View.UsuarioGUI;
import View.VersaoGUI;
import View.StatusGUI;

public class MainWinController {
	private final MainWinGUI view;
	
	public MainWinController(MainWinGUI view) {
		this.view = view;
	}
	
	private void montarJanela(JFrame view) {
		JDialog frame = new JDialog();
		
//		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
//		Dimension dw = view.getSize();
		
//		frame.setBounds(view.getBounds());
//		System.out.println(
//				"Tela principal DS -- DW frame " + " \n "+
//				" ds.height: " + ds.height + " \n "+
//				" ds.with: " + ds.width + " \n "+
//				" dw.height: " + dw.height + " \n "+
//				" dw.with: " + dw.width + " \n "+
//				" x (ds.width - dw.width) / 2 " + (ds.width - dw.width) / 2 + " \n "+
//				" y (ds.height - dw.height) / 2 " + (ds.height - dw.height) / 2
//		);
//		frame.setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
		
		frame.setContentPane(view.getContentPane());
		frame.pack();
		frame.setAutoRequestFocus(true);
		frame.setModal(true);
		frame.setSize(view.getSize());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void navegarCliente() {
		ClienteGUI cliente = new ClienteGUI();
		montarJanela(cliente);
	}
	
	public void navegarContador() {
		ContadorGUI contador = new ContadorGUI();
		montarJanela(contador);
	}

	public void navegarFiscal() {
		FiscalGUI fiscal = new FiscalGUI();
		montarJanela(fiscal);
	}
	
	public void navegarUsuario() {
		UsuarioGUI usuario = new UsuarioGUI();
		montarJanela(usuario);
	}
	
	public void navegarVersao() {
		VersaoGUI versao = new VersaoGUI(view.getUsuario());
		montarJanela(versao);
	}
	
	public void navegarStatus() {
		StatusGUI status = new StatusGUI(view.getUsuario());
		montarJanela(status);
	}
	
	public void sair() {
		System.exit(0);
	}

	public void navegarTeste() {
		View.SelecionaContadorGUI status = new View.SelecionaContadorGUI(new View.ClienteGUI());
		montarJanela(status);
		
	}

}
