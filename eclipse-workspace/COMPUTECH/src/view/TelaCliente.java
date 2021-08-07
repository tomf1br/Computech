package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientes();
			}
		});
		txtCliente.setBounds(10, 23, 253, 20);
		contentPanel.add(txtCliente);
		txtCliente.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/lupa32.png")));
		lblNewLabel.setBounds(293, 23, 32, 32);
		contentPanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 774, 107);
		contentPanel.add(scrollPane);

		tableCliente = new JTable();
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//setar os campos da tabela
				setarCampos();
			}
		});
		scrollPane.setColumnHeaderView(tableCliente);

		lblNewLabel_1 = new JLabel("Id:");
		lblNewLabel_1.setBounds(10, 218, 22, 14);
		contentPanel.add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(42, 215, 86, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);

		lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(138, 218, 46, 14);
		contentPanel.add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setBounds(189, 215, 349, 20);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);

		lblNewLabel_3 = new JLabel("CEP:");
		lblNewLabel_3.setBounds(548, 218, 27, 14);
		contentPanel.add(lblNewLabel_3);

		txtCep = new JTextField();
		txtCep.setBounds(585, 215, 86, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(681, 214, 89, 23);
		contentPanel.add(btnCep);

		JLabel lblNewLabel_4 = new JLabel("Endere\u00E7o:");
		lblNewLabel_4.setBounds(10, 258, 70, 14);
		contentPanel.add(lblNewLabel_4);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(71, 255, 335, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("N\u00FAmero:");
		lblNewLabel_5.setBounds(416, 258, 56, 14);
		contentPanel.add(lblNewLabel_5);

		txtNumero = new JTextField();
		txtNumero.setBounds(471, 255, 56, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Complemento:");
		lblNewLabel_6.setBounds(548, 258, 89, 14);
		contentPanel.add(lblNewLabel_6);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(636, 255, 134, 20);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Bairro:");
		lblNewLabel_7.setBounds(10, 296, 51, 14);
		contentPanel.add(lblNewLabel_7);

		txtBairro = new JTextField();
		txtBairro.setBounds(71, 293, 178, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Cidade:");
		lblNewLabel_8.setBounds(279, 296, 46, 14);
		contentPanel.add(lblNewLabel_8);

		txtCidade = new JTextField();
		txtCidade.setBounds(352, 293, 197, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("UF:");
		lblNewLabel_9.setBounds(601, 296, 46, 14);
		contentPanel.add(lblNewLabel_9);

		txtUf = new JTextField();
		txtUf.setBounds(684, 293, 86, 20);
		contentPanel.add(txtUf);
		txtUf.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("RG:");
		lblNewLabel_10.setBounds(10, 337, 46, 14);
		contentPanel.add(lblNewLabel_10);

		txtRg = new JTextField();
		txtRg.setBounds(69, 334, 142, 20);
		contentPanel.add(txtRg);
		txtRg.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("CPF:");
		lblNewLabel_11.setBounds(229, 337, 46, 14);
		contentPanel.add(lblNewLabel_11);

		txtCpf = new JTextField();
		txtCpf.setBounds(255, 334, 146, 20);
		contentPanel.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Fone 1:");
		lblNewLabel_12.setBounds(411, 337, 46, 14);
		contentPanel.add(lblNewLabel_12);

		txtFone1 = new JTextField();
		txtFone1.setBounds(460, 334, 102, 20);
		contentPanel.add(txtFone1);
		txtFone1.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Fone 2:");
		lblNewLabel_13.setBounds(572, 337, 46, 14);
		contentPanel.add(lblNewLabel_13);

		txtFone2 = new JTextField();
		txtFone2.setBounds(628, 334, 142, 20);
		contentPanel.add(txtFone2);
		txtFone2.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Email:");
		lblNewLabel_14.setBounds(10, 376, 46, 14);
		contentPanel.add(lblNewLabel_14);

		txtEmail = new JTextField();
		txtEmail.setBounds(66, 373, 197, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Observa\u00E7\u00F5es:");
		lblNewLabel_15.setBounds(293, 376, 82, 14);
		contentPanel.add(lblNewLabel_15);

		txtObs = new JTextField();
		txtObs.setBounds(385, 373, 385, 20);
		contentPanel.add(txtObs);
		txtObs.setColumns(10);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorder(null);
		btnAdicionar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/create.png")));
		btnAdicionar.setBounds(66, 472, 70, 70);
		contentPanel.add(btnAdicionar);

		JButton btnAtualiazar = new JButton("");
		btnAtualiazar.setBackground(SystemColor.menu);
		btnAtualiazar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtualiazar.setBorder(null);
		btnAtualiazar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/update.png")));
		btnAtualiazar.setBounds(238, 472, 70, 70);
		contentPanel.add(btnAtualiazar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setBackground(SystemColor.menu);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBorder(null);
		btnExcluir.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/delete.png")));
		btnExcluir.setBounds(422, 472, 70, 70);
		contentPanel.add(btnExcluir);
	}

	// FIM DO CONSTRUTOR
	// criar um objeto para acessar a classe DAO (camada model)

	DAO dao = new DAO();
	private JLabel lblNewLabel_1;
	private JTextField txtId;
	private JLabel lblNewLabel_2;
	private JTextField txtNome;
	private JLabel lblNewLabel_3;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtFone1;
	private JTextField txtFone2;
	private JTextField txtEmail;
	private JTextField txtObs;

	// metodo de pesquisa avançada de clientes
	private void pesquisarClientes() {
		String read = "select * from tbcliente where nomecli like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// atenção ao % napassagem do parâmetro
			pst.setString(1, txtCliente.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// a linha baixo usa a biblioteca rs2xml para preencher a tabela
			tableCliente.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//metodo para setar os campos com o conteudo da tabela
	private void setarCampos( ) {
		//a variavel abaixo obtem o conteudo da linha da coluna
		int setar = tableCliente.getSelectedRow();
		txtId.setText(tableCliente.getModel().getValueAt(setar,0).toString());
		txtNome.setText(tableCliente.getModel().getValueAt(setar,1).toString());
		txtCep.setText(tableCliente.getModel().getValueAt(setar,2).toString());
		txtEndereco.setText(tableCliente.getModel().getValueAt(setar,3).toString());
		txtNumero.setText(tableCliente.getModel().getValueAt(setar,4).toString());
		txtComplemento.setText(tableCliente.getModel().getValueAt(setar,5).toString());
		txtBairro.setText(tableCliente.getModel().getValueAt(setar,6).toString());
		txtCidade.setText(tableCliente.getModel().getValueAt(setar,7).toString());
		txtUf.setText(tableCliente.getModel().getValueAt(setar,8).toString());
		txtRg.setText(tableCliente.getModel().getValueAt(setar,9).toString());
		txtCpf.setText(tableCliente.getModel().getValueAt(setar,10).toString());
		txtFone1.setText(tableCliente.getModel().getValueAt(setar,11).toString());
		txtFone2.setText(tableCliente.getModel().getValueAt(setar,12).toString());
		txtEmail.setText(tableCliente.getModel().getValueAt(setar,13).toString());
		txtObs.setText(tableCliente.getModel().getValueAt(setar,14).toString());
	}

	// metodo para buscar CEP
	private void buscarCep() {
		//as variavéis abaixo foram usadas para unir tipo_logradouro com logradouro
		String logradouro = "";
		String tipoLogradouro = "";
		//variável de apoio para verificar se o Cep existe
		String resultado = null;

			try {
				//a linha abaixo armazena o cep digitado na variável cep
				String cep = txtCep.getText();			
				//a linha abaixo executa a url de acordo com o webservice 
				URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");				
				// o bloco de código abaixo usa a biblioteca dom4j para percorrer o arquivo xml extraindo as informações do Cep pesquisado
				SAXReader xml = new SAXReader();
				Document documento = xml.read(url);
				Element root = documento.getRootElement();			
				for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {				
					Element elemento = (Element) i.next();				
					if (elemento.getQualifiedName().equals("resultado")) {
						resultado = elemento.getText();
					}
					// a estrutura abaixo verifica se o Cep existe
					if (resultado.equals("0")) {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
					if (elemento.getQualifiedName().equals("uf")) {
						txtUf.setText(elemento.getText());
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
				//setar o campo txtEndereco com o conteúdo das variáveis
				txtEndereco.setText(tipoLogradouro + " " + logradouro);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: " + e);
			}
	}
}
