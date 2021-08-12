package view;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCliente;
	private JTable tableCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCliente dialog = new TelaCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCliente() {
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCliente.class.getResource("/icones/pc.png")));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1, 571);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientes();
				btnExcluir.setEnabled(true);
				btnAtualizar.setEnabled(true);

			}
		});
		txtCliente.setBounds(11, 34, 282, 20);
		getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 77, 747, 146);
		getContentPane().add(scrollPane);

		tableCliente = new JTable();

		tableCliente.setRowSelectionAllowed(false);
		tableCliente.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				// Setar os campos da tabela

				setarCampos();

				btnAdicionar.setEnabled(false);

			}
		});
		scrollPane.setColumnHeaderView(tableCliente);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(11, 261, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(33, 258, 46, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(152, 258, 259, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(104, 261, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CEP:");
		lblNewLabel_3.setBounds(436, 261, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtCep = new JTextField();
		txtCep.setBounds(465, 258, 119, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JButton btnCep = new JButton("");
		btnCep.setBackground(SystemColor.menu);
		btnCep.setBorder(null);
		btnCep.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/read.png")));
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(594, 231, 70, 70);
		getContentPane().add(btnCep);

		JLabel lblNewLabel_4 = new JLabel("Endere\u00E7o:");
		lblNewLabel_4.setBounds(11, 312, 57, 14);
		getContentPane().add(lblNewLabel_4);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(89, 309, 312, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("N\u00FAmero:");
		lblNewLabel_5.setBounds(411, 312, 57, 14);
		getContentPane().add(lblNewLabel_5);

		txtNumero = new JTextField();
		txtNumero.setBounds(476, 309, 62, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Complemento:");
		lblNewLabel_6.setBounds(548, 312, 95, 14);
		getContentPane().add(lblNewLabel_6);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(653, 310, 121, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Bairro:");
		lblNewLabel_7.setBounds(11, 350, 46, 14);
		getContentPane().add(lblNewLabel_7);

		txtBairro = new JTextField();
		txtBairro.setBounds(58, 347, 162, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Cidade:");
		lblNewLabel_8.setBounds(230, 350, 49, 14);
		getContentPane().add(lblNewLabel_8);

		txtCidade = new JTextField();
		txtCidade.setBounds(284, 347, 302, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("UF:");
		lblNewLabel_9.setBounds(625, 350, 46, 14);
		getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("RG:");
		lblNewLabel_10.setBounds(11, 395, 26, 14);
		getContentPane().add(lblNewLabel_10);

		txtRg = new JTextField();
		txtRg.setBounds(47, 392, 153, 20);
		getContentPane().add(txtRg);
		txtRg.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("CPF:");
		lblNewLabel_11.setBounds(210, 395, 32, 14);
		getContentPane().add(lblNewLabel_11);

		txtCpf = new JTextField();
		txtCpf.setBounds(259, 392, 86, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Fone (1):");
		lblNewLabel_12.setBounds(355, 395, 57, 14);
		getContentPane().add(lblNewLabel_12);

		txtFone1 = new JTextField();
		txtFone1.setBounds(411, 392, 86, 20);
		getContentPane().add(txtFone1);
		txtFone1.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Fone (2):");
		lblNewLabel_13.setBounds(507, 395, 57, 14);
		getContentPane().add(lblNewLabel_13);

		txtFone2 = new JTextField();
		txtFone2.setBounds(566, 392, 86, 20);
		getContentPane().add(txtFone2);
		txtFone2.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("E-mail:");
		lblNewLabel_14.setBounds(11, 448, 46, 14);
		getContentPane().add(lblNewLabel_14);

		txtEmail = new JTextField();
		txtEmail.setBounds(58, 445, 198, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Observa\u00E7\u00F5es:");
		lblNewLabel_15.setBounds(289, 448, 89, 14);
		getContentPane().add(lblNewLabel_15);

		txtObs = new JTextField();
		txtObs.setBounds(376, 445, 398, 20);
		getContentPane().add(txtObs);
		txtObs.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirCliente();

			}

		});
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBorder(null);
		btnAdicionar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/create.png")));
		btnAdicionar.setBounds(152, 489, 70, 70);
		getContentPane().add(btnAdicionar);

		btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnAtualizar.setBackground(SystemColor.menu);
		btnAtualizar.setBorder(null);
		btnAtualizar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/update.png")));
		btnAtualizar.setBounds(284, 489, 70, 70);
		getContentPane().add(btnAtualizar);

		btnExcluir = new JButton("");
		btnAtualizar.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarCliente();

			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBorder(null);
		btnExcluir.setForeground(SystemColor.menu);
		btnExcluir.setBackground(SystemColor.menu);
		btnExcluir.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/delete.png")));
		btnExcluir.setBounds(426, 489, 70, 70);
		getContentPane().add(btnExcluir);

		RestrictedTextField bairro = new RestrictedTextField(txtBairro);
		bairro.setLimit(20);

		RestrictedTextField cidade = new RestrictedTextField(txtCidade);
		cidade.setLimit(15);

		RestrictedTextField rg = new RestrictedTextField(txtRg);
		rg.setLimit(9);

		RestrictedTextField cpf = new RestrictedTextField(txtCpf);
		cpf.setOnlyNums(true);
		cpf.setLimit(11);

		RestrictedTextField fone = new RestrictedTextField(txtFone1);
		fone.setLimit(11);

		RestrictedTextField fone2 = new RestrictedTextField(txtFone2);
		fone2.setLimit(11);

		RestrictedTextField email = new RestrictedTextField(txtEmail);
		email.setLimit(15);

		RestrictedTextField obs = new RestrictedTextField(txtObs);

		JLabel lblNewLabel_16 = new JLabel("Buscar CEP");
		lblNewLabel_16.setBounds(674, 258, 70, 20);
		getContentPane().add(lblNewLabel_16);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				btnAdicionar.setEnabled(true);

				limpar();

			}
		});
		btnBuscar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/lupa32.png")));
		btnBuscar.setBounds(303, 34, 32, 32);
		getContentPane().add(btnBuscar);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(663, 346, 46, 22);
		getContentPane().add(cboUf);
		obs.setLimit(20);

	} // FIM DO CONSTRUTOR

	// TODO Auto-generated method stub

	// Criar o objeto para acessar o Bando de Dados classe DAO

	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtFone2;
	private JTextField txtEmail;
	private JTextField txtObs;
	private JTextField txtFone1;
	private JButton btnAdicionar;
	private JComboBox cboUf;
	private JButton btnExcluir;
	private JButton btnBuscar;
	private JButton btnAtualizar;

	// Método para pesquisa avançada de clientes
	private void pesquisarClientes() {
		String read = "select * from tbcliente where nomecli like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// Atenção ao % na passagem do parâmetro
			pst.setString(1, txtCliente.getText() + "%");

			ResultSet rs = pst.executeQuery();
			// A linha abaixo usa a biblioteca rs2xml para preencher a tabela
			tableCliente.setModel(DbUtils.resultSetToTableModel(rs));
// REVISSAR PARA INSERIR O MÉTODO DE LIBERAÇÃO DOS BOTÕES
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Método para setar os campos com o conteúdo da tabela
	private void setarCampos() {
		// A variável abaixo obtem o conteúdo da linha da coluna
		int setar = tableCliente.getSelectedRow();
		txtId.setText(tableCliente.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(tableCliente.getModel().getValueAt(setar, 1).toString());
		txtCep.setText(tableCliente.getModel().getValueAt(setar, 2).toString());
		txtEndereco.setText(tableCliente.getModel().getValueAt(setar, 3).toString());
		txtNumero.setText(tableCliente.getModel().getValueAt(setar, 4).toString());
		txtComplemento.setText(tableCliente.getModel().getValueAt(setar, 5).toString());
		txtBairro.setText(tableCliente.getModel().getValueAt(setar, 6).toString());
		txtCidade.setText(tableCliente.getModel().getValueAt(setar, 7).toString());
		cboUf.setSelectedItem(tableCliente.getModel().getValueAt(setar, 8).toString());
		txtRg.setText(tableCliente.getModel().getValueAt(setar, 9).toString());
		txtCpf.setText(tableCliente.getModel().getValueAt(setar, 10).toString());
		txtFone1.setText(tableCliente.getModel().getValueAt(setar, 11).toString());
		txtFone2.setText(tableCliente.getModel().getValueAt(setar, 12).toString());
		txtEmail.setText(tableCliente.getModel().getValueAt(setar, 13).toString());
		txtObs.setText(tableCliente.getModel().getValueAt(setar, 14).toString());

	}

	// Método para buscar o Cep

	private void buscarCep() {
		// As variáveis abaixo foram criadas para unir tipo_logradouro com logradouro
		String logradouro = "";
		String tipoLogradouro = "";
		// Variável de apoio para verificar se o cep existe
		String resultado = null;

		try {
			// A linha abaixo
			String cep = txtCep.getText();
			// A linha abaixo execulta a variável de acordo com o WebService
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			// O bloco de código abaixo usa a biblioteca dom4j para percorrer o arquivo xml
			// extraindo as informações do CEP pesquisado.
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
				Element elemento = (Element) i.next();
				if (elemento.getQualifiedName().equals("resultado")) {
					resultado = elemento.getText();
				}
				// A estrutura abaixo verifica se o CEP existe
				if (resultado.equals("0")) {
					JOptionPane.showMessageDialog(null, "CEP não encontrado");
				}
				if (elemento.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("cidade")) {
					txtCidade.setText(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("bairro")) {
					txtBairro.setText(elemento.getText());
				}
				if (elemento.getQualifiedName().equals("logradouro")) {
					logradouro = elemento.getText();
				}
				if (elemento.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = elemento.getText();
				}
			}

			// Setar o campo txtEndereco com o contúdo das variáveis
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}

	private void inserirCliente() {

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do Cliente obrigatório");
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cep obrigatório");
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Endereço obrigatório");
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Número senha obrigatório");
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Bairro obrigatório");
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cidade obrigatório");
		} else if (cboUf.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "UF obrigatório");
		} else if (txtRg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "RG obrigatório");
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "CPF obrigatório");

		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Fone 1 obrigatório");
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email obrigatório");

		} else {
			String create = "insert into tbcliente (nomecli, cep, logradouro, numero, complemento, bairro, cidade, uf, rg, cpf, fonecli, fonecli2, emailcli, obs) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, (String) cboUf.getSelectedItem());
				pst.setString(9, txtRg.getText());
				pst.setString(10, txtCpf.getText());
				pst.setString(11, txtFone1.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());
				pst.setString(14, txtObs.getText());
				// Executar Query (Insert no Banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente adicionado");
				con.close();
				limpar();
			} catch (SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "CPF já cadastrado");
				txtCpf.setText(null);
				txtCpf.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void alterarCliente() {
		// validação dos campos
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do Cliente obrigatório");
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cep obrigatório");
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Endereço obrigatório");
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Número senha obrigatório");
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Bairro obrigatório");
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cidade obrigatório");
		} else if (cboUf.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "UF obrigatório");
		} else if (txtRg.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "RG obrigatório");
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "CPF obrigatório");
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Fone 1 obrigatório");
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email obrigatório");
		} else {
			String update = "update tbcliente set nomecli=?, cep=?, logradouro=?, numero=?, complemento=?, bairro=?, cidade=?, uf=?, rg=?, cpf=?, fonecli=?, fonecli2=?, emailcli=?, obs=? where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				// substituir os parâmetros(?,?,?,?) pelo conteúdo das caixas de texto
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, (String) cboUf.getSelectedItem());
				pst.setString(9, txtRg.getText());
				pst.setString(10, txtCpf.getText());
				pst.setString(11, txtFone1.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());
				pst.setString(14, txtObs.getText());
				pst.setString(15, txtId.getText());
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Contato editado com sucesso");
				limpar();
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void deletarCliente() {

		String delete = "delete from tbcliente where idcli=?";
		// Confirmação de exclusão do cliente
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão dese cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
				limpar();
				con.close();
				btnAdicionar.setEnabled(true);
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
		txtNome.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtRg.setText(null);
		txtCpf.setText(null);
		txtFone1.setText(null);
		txtFone2.setText(null);
		txtEmail.setText(null);
		txtObs.setText(null);
		((DefaultTableModel) tableCliente.getModel()).setRowCount(0);

	}
}
