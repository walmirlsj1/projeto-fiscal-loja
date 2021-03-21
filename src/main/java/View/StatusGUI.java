package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Controller.LoginController;
import Controller.StatusController;
import Model.DAO_OLD.Banco;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class StatusGUI extends JFrame {
	
	private final StatusController controller;

	private final Model.Usuario usuario;
	
	private int id;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtDescricao;

	/**
	 * Create the frame.
	 */
	public StatusGUI(Model.Usuario usuario) {
		this.usuario = usuario;
		initComponents();
		controller = new StatusController(this);
		iniciar();
	}
		
	public void iniciar() {
		controller.configInit();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 374, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();

		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					evt.consume();
					controller.tablePegaID(evt);
				}
			}
		});
		scrollPane.setBounds(0, 0, 185, 162);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.light"), UIManager.getColor("Button.shadow")));
		panel.setBounds(186, 0, 172, 162);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 26, 150, 20);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.excluir();
			}
		});
		btnExcluir.setBounds(10, 124, 150, 23);
		panel.add(btnExcluir);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.limpaTela();
			}
		});
		btnNovo.setBounds(10, 57, 62, 23);
		panel.add(btnNovo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.salvar();
			}
		});
		btnEditar.setBounds(74, 57, 86, 23);
		panel.add(btnEditar);
		
		JButton btnAtivar = new JButton("Ativar");
		btnAtivar.setBounds(10, 91, 150, 23);
		panel.add(btnAtivar);

	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public Model.Usuario getUsuario() {
		return usuario;
	}

	public JTextField getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(JTextField txtDescricao) {
		this.txtDescricao = txtDescricao;
	}
}
