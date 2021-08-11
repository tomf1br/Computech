package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

		JButton btnRead = new JButton("Pesquisar");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				selecionarContato();
			}
		});
	
		btnRead.setBounds(179, 323, 97, 23);
		getContentPane().add(btnRead);
		

		btnUpdate_1 = new JButton("Alterar");
		
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		btnUpdate_1.setBounds(331, 323, 89, 23);
		getContentPane().add(btnUpdate_1);


		btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarUsuario();
				
			}
		});

		btnDelete.setBounds(479, 323, 89, 23);
		getContentPane().add(btnDelete);
		

		btnCreate2 = new JButton("Adicionar");
		btnCreate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirUsuario();
				
			}
		});
		btnCreate2.setEnabled(false);
		btnCreate2.setBounds(59, 323, 89, 23);
		getContentPane().add(btnCreate2);

	}
	// fim do construtor

	DAO dao = new DAO();
	private JButton btnRead;
	private JButton btnUpdate;
	private JButton BtnDelete;
	private JComboBox cboPerfil;
	private JButton btnCreate2;
	private JButton btnUpdate_1;
	private JButton btnDelete;

	private void status() {
		try {
			// Estabelecer uma conexão
			Connection con = dao.conectar();
			// status
			// System.out.println(con);
			// Trocando o icone do DBA
			if (con != null) {

				btnRead.setEnabled(true);
				// btnUpdate.setEnabled(true);
				// BtnDelete.setEnabled(true);

			} else {

			}
			// Encerrar conexão5
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	// Selecionar Usuario com o read
	private void selecionarContato() {
		// Instrução sql para pesquisar um contato pelo nome
		String read = "select * from tbusuarios where usuario = ?";
		try {

			// Estabelecer uma conexão
			Connection con = dao.conectar();
			// Preparar a instrução SQL
			PreparedStatement pst = con.prepareStatement(read);
			// Substituir o parâmetro (?) pelo nome do contato
			pst.setString(1, txtUsuario.getText());
			// Resultado (obter os dados do contato pesquisado)
			ResultSet rs = pst.executeQuery();
			// se existir um contato correspondente
			if (rs.next()) {
				txtId.setText(rs.getString(1));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
				cboPerfil.setSelectedItem(rs.getString(5));
		
				
			} else {
				// Criar uma caixa de MSG no java
				JOptionPane.showMessageDialog(null, "Contato inexistente");
				btnCreate2.setEnabled(true);
		
				limpar();

			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void inserirUsuario() {
		// Validação dos campos
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do Usuário obrigatório");
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login obrigatório");
		} else if (txtSenha.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo senha obrigatório");
		} else if (cboPerfil.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "O campo perfil obrigatório");

		} else {
			String create = "insert into tbusuarios (usuario, login, senha, perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// Executar Query (Insert no Banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuário adicionado");
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	/**
	 * Editar contato CRUD UPDATE
	 *
	 */
	
	private void alterarUsuario() {
		// validação dos campos
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do contato");
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o fone do contato");
		} else if (txtSenha.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ter mais que 50 caracteres");
		
		
		} else {
			String update = "update tbusuarios set usuario=?,login=?,senha=md5(?), perfil=? where iduser=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				// substituir os parâmetros(?,?,?,?) pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Contato editado com sucesso");
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

private void deletarUsuario() {
		
		String delete = "delete from tbusuarios where iduser=?";
		// Confirmação de exclusão do contato
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Contato excluido com sucesso");
				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			limpar();
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
