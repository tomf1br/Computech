package view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class TelaOs extends JDialog {
	private JTextField txtOs;
	private JTextField txtData;
	private JTextField txtCliente;
	private JTextField txtId;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtSerial;
	private JTextField txtSenha;
	private JTextField txtAcessorios;
	private JTextField txtDefeito;
	private JTextField txtTecnico;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtDiagnostico;
	private JTextField txtValor;
	private JTextField txtSinal;
	private JTextField txtReceber;
	private JTable tableCliente;

	// Variavel de apoio a captura do JRadioButton
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaOs dialog = new TelaOs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaOs() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaOs.class.getResource("/icones/pc.png")));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		JLabel lblOs = new JLabel("OS");
		lblOs.setBounds(29, 23, 48, 14);
		getContentPane().add(lblOs);

		JLabel lblNewLabel = new JLabel("Data");
		lblNewLabel.setBounds(107, 23, 48, 14);
		getContentPane().add(lblNewLabel);

		txtOs = new JTextField();
		txtOs.setEnabled(false);
		txtOs.setBounds(29, 48, 68, 20);
		getContentPane().add(txtOs);
		txtOs.setColumns(10);

		txtData = new JTextField();
		txtData.setEnabled(false);
		txtData.setBounds(107, 48, 126, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);

		rdbtnOrcamento = new JRadioButton("Or\u00E7amento");
		rdbtnOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tipo = "Orçamento";
			}
		});
		buttonGroup.add(rdbtnOrcamento);
		rdbtnOrcamento.setBounds(27, 95, 96, 23);
		getContentPane().add(rdbtnOrcamento);

		rdbtnServico = new JRadioButton("Servi\u00E7o");
		rdbtnServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tipo = "Serviço";
			}
		});
		buttonGroup.add(rdbtnServico);
		rdbtnServico.setBounds(125, 95, 96, 23);
		getContentPane().add(rdbtnServico);

		JLabel lblNewLabel_1 = new JLabel("Situa\u00E7\u00E3o");
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(10, 151, 67, 14);
		getContentPane().add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 253, 117);
		getContentPane().add(panel);

		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(
				new String[] { "", "Or\u00E7amento aprovado", "Or\u00E7amento reprovado", "Aguardando pe\u00E7as" }));
		cboStatus.setBounds(76, 147, 187, 22);
		getContentPane().add(cboStatus);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});

		txtCliente.setBounds(286, 48, 229, 20);
		getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(286, 23, 48, 14);
		getContentPane().add(lblCliente);

//	btnBuscar = new JButton("");
//
//		btnBuscar.setBorder(null);
//		btnBuscar.setBackground(SystemColor.menu);
//		btnBuscar.setIcon(new ImageIcon(TelaOs.class.getResource("/icones/lupa32.png")));
//		btnBuscar.setSelectedIcon(new ImageIcon(TelaOs.class.getResource("/icones/lupa32.png")));
//		btnBuscar.setBounds(525, 27, 35, 41);
//		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setBounds(588, 51, 48, 14);
		getContentPane().add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setEditable(false);

		txtId.setBounds(611, 48, 96, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Computadores");
		lblNewLabel_3.setBounds(29, 186, 87, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Modelo:");
		lblNewLabel_4.setBounds(146, 204, 48, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Marca:");
		lblNewLabel_5.setBounds(512, 204, 48, 14);
		getContentPane().add(lblNewLabel_5);

		txtModelo = new JTextField();
		txtModelo.setBounds(204, 201, 287, 20);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);

		txtMarca = new JTextField();
		txtMarca.setBounds(562, 201, 173, 20);
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Serial Number:");
		lblNewLabel_6.setBounds(107, 241, 87, 14);
		getContentPane().add(lblNewLabel_6);

		txtSerial = new JTextField();
		txtSerial.setBounds(204, 238, 287, 20);
		getContentPane().add(txtSerial);
		txtSerial.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Senha:");
		lblNewLabel_7.setBounds(512, 241, 48, 14);
		getContentPane().add(lblNewLabel_7);

		txtSenha = new JTextField();
		txtSenha.setBounds(562, 238, 173, 20);
		getContentPane().add(txtSenha);
		txtSenha.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Acess\u00F3rios:");
		lblNewLabel_8.setBounds(107, 272, 75, 14);
		getContentPane().add(lblNewLabel_8);

		txtAcessorios = new JTextField();
		txtAcessorios.setBounds(204, 269, 531, 20);
		getContentPane().add(txtAcessorios);
		txtAcessorios.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Defeito:");
		lblNewLabel_9.setBounds(146, 305, 48, 14);
		getContentPane().add(lblNewLabel_9);

		txtDefeito = new JTextField();
		txtDefeito.setBounds(204, 302, 531, 20);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("T\u00E9cnico:");
		lblNewLabel_10.setBounds(27, 415, 50, 14);
		getContentPane().add(lblNewLabel_10);

		txtTecnico = new JTextField();
		txtTecnico.setBounds(76, 412, 167, 20);
		getContentPane().add(txtTecnico);
		txtTecnico.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Diagn\u00F3stico:");
		lblNewLabel_11.setBounds(253, 415, 81, 14);
		getContentPane().add(lblNewLabel_11);

		txtDiagnostico = new JTextField();
		txtDiagnostico.setBounds(341, 412, 394, 20);
		getContentPane().add(txtDiagnostico);
		txtDiagnostico.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Valor:");
		lblNewLabel_12.setBounds(40, 460, 37, 14);
		getContentPane().add(lblNewLabel_12);

		txtValor = new JTextField();
		txtValor.setBounds(76, 457, 68, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Sinal:");
		lblNewLabel_13.setBounds(156, 460, 48, 14);
		getContentPane().add(lblNewLabel_13);

		txtSinal = new JTextField();
		txtSinal.setBounds(195, 457, 68, 20);
		getContentPane().add(txtSinal);
		txtSinal.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Forma de pagamento:");
		lblNewLabel_14.setBounds(268, 460, 135, 14);
		getContentPane().add(lblNewLabel_14);

		cboPagamento = new JComboBox();
		cboPagamento.setModel(new DefaultComboBoxModel(
				new String[] { "", "Dinheiro", "Cr\u00E9dito", "D\u00E9bito", "1x", "2x", "3x" }));
		cboPagamento.setBounds(398, 456, 69, 22);
		getContentPane().add(cboPagamento);

		JLabel lblNewLabel_15 = new JLabel("\u00C0 receber:");
		lblNewLabel_15.setBounds(478, 460, 61, 14);
		getContentPane().add(lblNewLabel_15);

		txtReceber = new JTextField();
		txtReceber.setBounds(543, 457, 96, 20);
		getContentPane().add(txtReceber);
		txtReceber.setColumns(10);

		btnNewButton = new JButton("Calcular");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restante();

			}
		});

		btnNewButton.setBounds(657, 456, 89, 23);
		getContentPane().add(btnNewButton);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitirOS();
			}
		});
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBorder(null);
		btnAdicionar.setIcon(new ImageIcon(TelaOs.class.getResource("/icones/create.png")));
		btnAdicionar.setBounds(29, 494, 60, 60);
		getContentPane().add(btnAdicionar);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBackground(SystemColor.menu);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setIcon(new ImageIcon(TelaOs.class.getResource("/icones/read.png")));
		btnNewButton_3.setBounds(95, 494, 60, 60);
		getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBackground(SystemColor.menu);

		btnNewButton_4.setBorder(null);
		btnNewButton_4.setIcon(new ImageIcon(TelaOs.class.getResource("/icones/update.png")));
		btnNewButton_4.setBounds(161, 494, 60, 60);
		getContentPane().add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setBackground(SystemColor.menu);

		btnNewButton_5.setBorder(null);
		btnNewButton_5.setIcon(new ImageIcon(TelaOs.class.getResource("/icones/delete.png")));
		btnNewButton_5.setBounds(231, 494, 60, 60);
		getContentPane().add(btnNewButton_5);

		dataPrevisao = new JDateChooser();
		dataPrevisao.setBounds(398, 510, 107, 20);
		getContentPane().add(dataPrevisao);

		dataRetirada = new JDateChooser();
		dataRetirada.setBounds(529, 510, 107, 20);
		getContentPane().add(dataRetirada);

		dataGarantia = new JDateChooser();
		dataGarantia.setBounds(657, 510, 107, 20);
		getContentPane().add(dataGarantia);

		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.setBackground(SystemColor.menu);
		btnNewButton_6.setBorder(null);
		btnNewButton_6.setIcon(new ImageIcon(TelaOs.class.getResource("/icones/printer.png")));
		btnNewButton_6.setBounds(297, 494, 60, 60);
		getContentPane().add(btnNewButton_6);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(286, 76, 442, 50);
		getContentPane().add(scrollPane_1);

		tableCliente = new JTable();
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencherCampo();
			}
		});
		scrollPane_1.setColumnHeaderView(tableCliente);

		RestrictedTextField modelo = new RestrictedTextField(txtModelo);
		modelo.setLimit(40);

		RestrictedTextField marca = new RestrictedTextField(txtMarca);
		marca.setLimit(9);

		RestrictedTextField serial = new RestrictedTextField(txtSerial);
		serial.setOnlyNums(true);

		RestrictedTextField acessorios = new RestrictedTextField(txtAcessorios);
		acessorios.setLimit(100);

		RestrictedTextField defeito = new RestrictedTextField(txtDefeito);
		defeito.setLimit(11);

		RestrictedTextField tecnico = new RestrictedTextField(txtTecnico);
		tecnico.setLimit(15);

		RestrictedTextField diagnostico = new RestrictedTextField(txtDiagnostico);
		diagnostico.setLimit(100);

		RestrictedTextField valor = new RestrictedTextField(txtValor);
		valor.setOnlyNums(true);

		RestrictedTextField sinal = new RestrictedTextField(txtSinal);
		sinal.setOnlyNums(true);

		JLabel lblNewLabel_16 = new JLabel("Data Prevista Or\u00E7.");
		lblNewLabel_16.setBounds(409, 540, 106, 14);
		getContentPane().add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("Data Retirada");
		lblNewLabel_17.setBounds(547, 540, 89, 14);
		getContentPane().add(lblNewLabel_17);

		JLabel lblNewLabel_18 = new JLabel("Data Garantia");
		lblNewLabel_18.setBounds(667, 541, 89, 14);
		getContentPane().add(lblNewLabel_18);
		serial.setOnlyNums(true);

	}

//FIM DO CONSTRUTOR
	DAO dao = new DAO();

//private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnNewButton;
	private JButton btnAdicionar;
	private JRadioButton rdbtnOrcamento;
	private JRadioButton rdbtnServico;
	private JComboBox cboStatus;
	private JComboBox cboPagamento;
	private JDateChooser dataPrevisao;
	private JDateChooser dataRetirada;
	private JDateChooser dataGarantia;

	private void pesquisarCliente() {
		String pesquisar = "select idcli,nomecli,fonecli from tbcliente where nomecli like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(pesquisar);
			pst.setString(1, txtCliente.getText() + "%");
			ResultSet rs = pst.executeQuery();
			tableCliente.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

// Preencher tabela
	private void preencherCampo() {

		int setar = tableCliente.getSelectedRow();
		txtId.setText(tableCliente.getModel().getValueAt(setar, 0).toString());

	}

// Váriavel para calcular Sinal //

	private void restante() {
		double valor, sinal, receber;
		valor = Double.parseDouble(txtValor.getText());
		sinal = Double.parseDouble(txtSinal.getText());
		receber = valor - sinal;
		txtReceber.setText("R$ " + receber);

	}

	// CRUD CREATE (INSERT)
	private void emitirOS() {
		// VALDAÇÕES DE CAMPOS OBRIGATÓRIOS
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Identifique o cliente");
			txtCliente.requestFocus();

			// ! (NOT) - > SE O JRADIOBUTTON NÃO ESTIVER SELECIONADO
		} else if (!rdbtnServico.isSelected() && !rdbtnOrcamento.isSelected()) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS");

		} else {
			String insertOs = "insert into tbos (tipoOs, statusOs, tecnico, modelo, marca, serialNumber, senha, acessorios, defeitoCli, diagnostico, valor, sinal, dataPrevista, dataRetirada, formPag, garantiaData, idCli) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(insertOs);
				// Captura -> associada ao texto do JRadioButton

				pst.setString(1, tipo);
				pst.setString(2, cboStatus.getSelectedItem().toString());
				pst.setString(3, txtTecnico.getText());
				pst.setString(4, txtModelo.getText());
				pst.setString(5, txtMarca.getText());
				pst.setString(6, txtSerial.getText());
				pst.setString(7, txtSenha.getText());
				pst.setString(8, txtAcessorios.getText());
				pst.setString(9, txtDefeito.getText());
				pst.setString(10, txtDiagnostico.getText());
				pst.setString(11, txtValor.getText());
				pst.setString(12, txtSinal.getText());
				SimpleDateFormat formatador = new SimpleDateFormat("yyyMMdd");
				String dataPrevista = formatador.format(dataPrevisao.getDate());
				pst.setString(13, dataPrevista);
				SimpleDateFormat formatador2 = new SimpleDateFormat("yyyMMdd");
				String dataRetirar = formatador2.format(dataRetirada.getDate());
				pst.setString(14, dataRetirar);
				pst.setString(15, cboPagamento.getSelectedItem().toString());
				SimpleDateFormat formatador3 = new SimpleDateFormat("yyyMMdd");
				String dataGarantir = formatador3.format(dataGarantia.getDate());
				pst.setString(16, dataGarantir);
				pst.setString(17, txtId.getText());
				int sucesso = pst.executeUpdate();
				if (sucesso == 1) {
					JOptionPane.showMessageDialog(null, "OS Emitida com sucesso!");
				}

				limpar();
			} catch (Exception e) {
				System.out.println(e);

			}
		}

	}

	private void limpar() {
		// limpar os campos
		txtId.setText(null);
		txtCliente.setText(null);
		txtModelo.setText(null);
		txtMarca.setText(null);
		txtSerial.setText(null);
		txtSenha.setText(null);
		txtAcessorios.setText(null);
		txtDefeito.setText(null);
		txtTecnico.setText(null);
		cboStatus.setSelectedItem(null);
		cboPagamento.setSelectedItem(null);
		txtReceber.setText(null);
		txtDiagnostico.setText(null);
		txtValor.setText(null);
		txtSinal.setText(null);
		dataPrevisao.setDate(null);
		dataRetirada.setDate(null);
		dataGarantia.setDate(null);
		((DefaultTableModel) tableCliente.getModel()).setRowCount(0);
	}
}
