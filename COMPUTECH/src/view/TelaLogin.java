package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setTitle("TS TECH - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(38, 52, 46, 14);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(94, 49, 215, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(38, 89, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(94, 86, 215, 20);
		contentPane.add(txtSenha);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// executar o método logar ao pressionar o botão
				logar();
			}
		});
		btnLogin.setBounds(38, 143, 89, 23);
		contentPane.add(btnLogin);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/icones/dberror.png")));
		lblStatus.setBounds(330, 155, 32, 32);
		contentPane.add(lblStatus);
	}// fim do construtor

	DAO dao = new DAO();
	private JLabel lblStatus;

	/**
	 * Status da conexão
	 */
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

	// Login do usuário
	@SuppressWarnings("deprecation")
	private void logar() {
		// validação dos campos obrigatórios
		if (txtUsuario.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
			txtUsuario.requestFocus();
		} else if (txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtSenha.requestFocus();
		} else {
			String read = "select * from tbusuarios where login=? and senha=md5(?)";
			try {
				// estabelecer a conexão
				Connection con = dao.conectar();
				// preparar o comando sql para executar no banco de dados
				PreparedStatement pst = con.prepareStatement(read);
				pst = con.prepareStatement(read);
				// substituir o conteúdo da caixa de texto pelo parâmetro (?)
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtSenha.getText());
				// resultado (obter os dados do contato pesquisado)
				ResultSet rs = pst.executeQuery();
				// se existir um contato correspondente
				if (rs.next()) {
					// Tratamento do perfil de usuário
					String perfil = rs.getString(5);
					// apoio ao entendimento da lógica
					// System.out.println(perfil);
					if (perfil.equals("admin")) {
						// criar um objeto para acessar a classe TelaPrincipal
						TelaPrincipal principal = new TelaPrincipal();
						// habilitar os botões
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						// alterar a label lblUsuario para o nome do usuário
						principal.lblUsuario.setText(rs.getString(2));
						// alterar a cor do painel(rodapé)
						principal.panelRodape.setBackground(Color.RED);
						// centralizar a tela principal
						principal.setLocationRelativeTo(null);
						// Ativar a tela principal
						principal.setVisible(true);
						// fechar o Jframe (TelaLogin)
						this.dispose();
						// encerrar a conexão
						con.close();
					} else {
						TelaPrincipal principal = new TelaPrincipal();
						principal.lblUsuario.setText(rs.getString(2));
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						this.dispose();
						con.close();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
					txtUsuario.setText(null);
					txtSenha.setText(null);
					txtUsuario.requestFocus();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
