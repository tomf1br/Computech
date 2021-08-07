package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	// para manipular as propriedades dos objetos abaixo na classe TelaLogin,
	// modificar private para public
	public JButton btnUsuarios;
	public JButton btnRelatorios;
	public JLabel lblUsuario;
	public JPanel panelRodape;
	private JLabel lblDataLabel;
	private JLabel lblNewLabel;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setBackground(SystemColor.menuText);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/icones/jslogo.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				alterarData();
				status();
			}
		});
		setResizable(false);
		setTitle("COMPUTECH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario usuario = new TelaUsuario();
				usuario.setLocationRelativeTo(null);
				usuario.setVisible(true);
			}
		});
		btnUsuarios.setToolTipText("Usu\u00E1rios");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/users.png")));
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setBounds(37, 71, 128, 128);
		contentPane.add(btnUsuarios);

		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				cliente.setLocationRelativeTo(null);
			}
		});
		btnClientes.setToolTipText("Clientes");
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/clientes.png")));
		btnClientes.setBounds(231, 71, 128, 128);
		contentPane.add(btnClientes);

		JButton btnOs = new JButton("");
		btnOs.setToolTipText("Gerar OS");
		btnOs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOs.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/tools.png")));
		btnOs.setBounds(415, 71, 128, 128);
		contentPane.add(btnOs);

		btnRelatorios = new JButton("");
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setToolTipText("Relat\u00F3rios");
		btnRelatorios.setEnabled(false);
		btnRelatorios.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/report.png")));
		btnRelatorios.setBounds(607, 71, 128, 128);
		contentPane.add(btnRelatorios);

		panelRodape = new JPanel();
		panelRodape.setBackground(SystemColor.activeCaptionText);
		panelRodape.setBounds(0, 499, 794, 72);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);

		lblUsuario = new JLabel("");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setBounds(103, 22, 194, 25);
		panelRodape.add(lblUsuario);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(21, 22, 86, 25);
		panelRodape.add(lblNewLabel_1);

		lblDataLabel = new JLabel("");
		lblDataLabel.setForeground(Color.WHITE);
		lblDataLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDataLabel.setBounds(481, 22, 313, 25);
		panelRodape.add(lblDataLabel);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(413, 0, 58, 58);
		panelRodape.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/jslogo.png")));

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre sobre = new TelaSobre();
				sobre.setLocationRelativeTo(null);
				sobre.setVisible(true);

			}
		});
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBorder(null);
		btnSobre.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/about.png")));
		btnSobre.setBounds(264, 252, 64, 64);
		contentPane.add(btnSobre);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/dbok.png")));
		lblStatus.setBounds(734, 440, 60, 60);
		contentPane.add(lblStatus);
		
		btnNewButton = new JButton("T\u00E9cnicos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaTecnicos telaTecnicos = new TelaTecnicos();
				//TelaTecnicos tecnicos = new TelaTecnicos();
				telaTecnicos.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(37, 222, 128, 117);
		contentPane.add(btnNewButton);
	}// fim do construtor

	private void alterarData() {
		Date datalabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblDataLabel.setText(formatador.format(datalabel));
	}

	DAO dao = new DAO();
	private JButton btnNewButton;

	private void status() {
		try {
			// estabelecer uma conexão
			Connection con = dao.conectar();
			// status
			// System.out.println(con);
			// trocando o ícone do banco(status da conexão)
			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));

			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			}
			// encerrar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}