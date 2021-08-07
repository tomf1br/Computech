package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class TelaTecnicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtTecnico;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnDeletar;
	private JButton btnPesquisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaTecnicos dialog = new TelaTecnicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaTecnicos() {
		setTitle("Tecnico");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaTecnicos.class.getResource("/icones/jslogo.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(28, 40, 46, 14);
		contentPanel.add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(78, 37, 57, 20);
		contentPanel.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tecnico");
		lblNewLabel_1.setBounds(28, 77, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtTecnico = new JTextField();
		txtTecnico.setBounds(78, 74, 134, 20);
		contentPanel.add(txtTecnico);
		txtTecnico.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirTecnico();
			}
		});
		btnAdicionar.setEnabled(false);
		btnAdicionar.setIcon(new ImageIcon(TelaTecnicos.class.getResource("/icones/create.png")));
		btnAdicionar.setBounds(58, 187, 48, 48);
		contentPanel.add(btnAdicionar);

		btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTecnico();
			}
		});
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setEnabled(false);
		btnAtualizar.setIcon(new ImageIcon(TelaTecnicos.class.getResource("/icones/update.png")));
		btnAtualizar.setBounds(182, 187, 48, 48);
		contentPanel.add(btnAtualizar);

		btnPesquisar = new JButton("");
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarTecnico();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaTecnicos.class.getResource("/icones/read.png")));
		btnPesquisar.setBounds(254, 43, 48, 48);
		contentPanel.add(btnPesquisar);

		btnDeletar = new JButton("");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarUsuario();
			}
		});
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.setEnabled(false);
		btnDeletar.setIcon(new ImageIcon(TelaTecnicos.class.getResource("/icones/delete.png")));
		btnDeletar.setBounds(314, 187, 48, 48);
		contentPanel.add(btnDeletar);
	} // final fantasy

	DAO dao = new DAO();

	private void pesquisarTecnico() {
		// instru��o sql para pesquisar um contato pelo nome
		String pesquisar = "select * from tbTecnicos where nomeTec = ?";
		try {

			Connection con = dao.conectar();

			PreparedStatement pst = con.prepareStatement(pesquisar);

			pst.setString(1, txtTecnico.getText());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				txtID.setText(rs.getString(1));
				// txtTecnico.setText(rs.getString(2));

				btnAtualizar.setEnabled(true);
				btnDeletar.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null, "Tecnico inexistente");
				btnAdicionar.setEnabled(true);
				btnPesquisar.setEnabled(false);
				btnAtualizar.setEnabled(false);
				btnDeletar.setEnabled(false);

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	private void inserirTecnico() {

		if (txtTecnico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Nome obrigat�rio");
		}
		String create = "insert into tbTecnicos (nomeTec) values (?)";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, txtTecnico.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Tecnico adicionado");

			con.close();
			limpar();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limpar() {
		btnAdicionar.setEnabled(false);
		btnPesquisar.setEnabled(true);
		btnAtualizar.setEnabled(false);
		btnDeletar.setEnabled(false);
		txtTecnico.setText(null);
		txtTecnico.requestFocus();
		txtID.setText(null);

	}

	private void alterarTecnico() {
		String alterar = "update tbTecnicos set nomeTec=? where idtec=?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(alterar);
			pst.setString(1, txtTecnico.getText());

			pst.setString(2, txtID.getText());
			pst.executeUpdate();

			JOptionPane.showMessageDialog(null, "Tecnico atualizado");

			con.close();
			limpar();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void deletarUsuario() {
		String delete = "delete from tbTecnicos where nomeTec=?";
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o deste Tecnico?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtTecnico.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Tecnico excluido com sucesso");
				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
}
