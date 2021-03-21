package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.VersaoController;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VersaoGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 782528448030883957L;
	
	private final VersaoController controller;
	private final Model.Usuario usuario;
	
	private int id;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtVersao;


	/**
	 * Create the frame.
	 */
	public VersaoGUI(Model.Usuario usuario) {
		this.usuario = usuario;
		initComponents();
		controller = new VersaoController(this);
		iniciar();
	}
	
	public void iniciar() {
		controller.configInit();
	}

	public void initComponents() {
//		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 374, 201);
//		setSize(464, 201);
		
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
		
		JLabel lblNewLabel = new JLabel("Versão");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		txtVersao = new JTextField();
		txtVersao.setBounds(10, 26, 150, 20);
		panel.add(txtVersao);
		txtVersao.setColumns(10);
		
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

	public JTextField getTxtVersao() {
		return txtVersao;
	}

	public void setTxtVersao(JTextField txtVersao) {
		this.txtVersao = txtVersao;
	}
}
