package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.DAO;

public class TelaUsuario extends JDialog {
	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario dialog = new TelaUsuario();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaUsuario.class.getResource("/icones/pc.png")));
		setTitle("Usu\u00E1rios");
		setBounds(100, 100, 652, 438);
		getContentPane().setLayout(null);

		JLabel lbl = new JLabel("id");
		lbl.setBounds(92, 68, 46, 14);
		getContentPane().add(lbl);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(148, 65, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(147, 114, 392, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(59, 117, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtLogin = new JTextField();
		txtLogin.setBounds(148, 168, 128, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(59, 171, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(318, 171, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(381, 168, 158, 20);
		getContentPane().add(txtSenha);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(59, 235, 34, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(148, 231, 128, 22);
		getContentPane().add(cboPerfil);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirUsuario();
			}
		});
		btnNewButton.setBounds(59, 323, 89, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Pesquisar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(197, 323, 96, 23);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Alterar");
		btnNewButton_2.setBounds(331, 323, 89, 23);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Excluir");
		btnNewButton_3.setBounds(479, 323, 89, 23);
		getContentPane().add(btnNewButton_3);

	}
	// fim do construtor

	DAO dao = new DAO();
	private JComboBox cboPerfil;

	private void inserirUsuario() {
		// validação dos campos
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Nome de Usuário");
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Login Obrigatório");
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Senha Obrigatório");
		} else if (cboPerfil.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Campo Perfil Obrigatório");
		} else {
			String create = "insert into tbusuarios (usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				// substituir os parâmetros(?,?,?) pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuario adicionado");
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);

			}
		}
	}

	private void limpar() {
		// limpar os campos
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		cboPerfil.setSelectedItem(null);

	}
}
