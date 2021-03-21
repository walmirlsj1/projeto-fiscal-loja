package View;

import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import Controller.MainWinController;
import Model.Versao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainWinGUI extends JFrame {
	
	private final MainWinController controller;
	private final Model.Usuario usuario;
	/**
	 * Create the application.
	 */
	public MainWinGUI(Model.Usuario usuario) {
		this.usuario = usuario;
		initialize();
		controller = new MainWinController(this);
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.SOUTH);
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
//		setDefaultLookAndFeelDecorated(false);
		
		final GraphicsConfiguration config = this.getGraphicsConfiguration();

        final int left = Toolkit.getDefaultToolkit().getScreenInsets(config).left;
        final int right = Toolkit.getDefaultToolkit().getScreenInsets(config).right;
        final int top = Toolkit.getDefaultToolkit().getScreenInsets(config).top;
        final int bottom = Toolkit.getDefaultToolkit().getScreenInsets(config).bottom;

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = screenSize.width - left - right;
        final int height = screenSize.height - top - bottom;

//        this.setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setSize(width,height);
		setLocationRelativeTo(null);
				        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mntCad = new JMenu("Cadastro");
		menuBar.add(mntCad);
		
		JMenuItem mntCadContador = new JMenuItem("Contador");
		mntCadContador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.navegarContador();
			}
		});
		mntCad.add(mntCadContador);
		
		JMenuItem mntCadClientes = new JMenuItem("Clientes");
		mntCadClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.navegarCliente();
			}
		});
		mntCad.add(mntCadClientes);
		
		JMenuItem mntCadTipo = new JMenuItem("Versão");
		mntCadTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.navegarVersao();
			}
		});
		mntCad.add(mntCadTipo);
		
		JMenuItem mntCadStatus = new JMenuItem("Status");
		mntCadStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.navegarStatus();
			}
		});
		mntCad.add(mntCadStatus);
		
		JMenu mntFiscal = new JMenu("Fiscal");
		menuBar.add(mntFiscal);
		
		JMenuItem mntFiscalGer = new JMenuItem("Gerenciar");
		mntFiscalGer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.navegarFiscal();
			}
		});
		mntFiscal.add(mntFiscalGer);
		
		JMenu mntUsers = new JMenu("Usuarios");
		menuBar.add(mntUsers);
		
		JMenuItem mntUsersGer = new JMenuItem("Gerenciar");
		mntUsersGer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.navegarUsuario();
			}
		});
		mntUsers.add(mntUsersGer);
		
		JMenuItem mntUsersGer2 = new JMenuItem("teste");
		mntUsersGer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.navegarTeste();
			}
		});
		mntUsers.add(mntUsersGer2);
		
		JMenu mntSair = new JMenu("Sair");
		mntSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(mntSair);
	}

	public Model.Usuario getUsuario() {
		return usuario;
	}
	
}
