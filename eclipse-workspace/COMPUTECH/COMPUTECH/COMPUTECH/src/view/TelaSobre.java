package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaSobre extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtComputechInformtica;
	private JTextField txtAutorJerryFerreira;
	private JTextField txtWwwcomputechcombr;
	private JTextField txtVerso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaSobre dialog = new TelaSobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaSobre() {
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSobre.class.getResource("/icones/jslogo.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtComputechInformtica = new JTextField();
		txtComputechInformtica.setEditable(false);
		txtComputechInformtica.setBorder(null);
		txtComputechInformtica.setText("Computech Inform\u00E1tica");
		txtComputechInformtica.setBounds(10, 25, 211, 20);
		contentPanel.add(txtComputechInformtica);
		txtComputechInformtica.setColumns(10);

		txtAutorJerryFerreira = new JTextField();
		txtAutorJerryFerreira.setEditable(false);
		txtAutorJerryFerreira.setBorder(null);
		txtAutorJerryFerreira.setText("Autor: Jerry Ferreira e Igor Jord\u00E3o");
		txtAutorJerryFerreira.setBounds(10, 93, 211, 20);
		contentPanel.add(txtAutorJerryFerreira);
		txtAutorJerryFerreira.setColumns(10);

		txtWwwcomputechcombr = new JTextField();
		txtWwwcomputechcombr.setEditable(false);
		txtWwwcomputechcombr.setBorder(null);
		txtWwwcomputechcombr.setText("www.computech.com.br");
		txtWwwcomputechcombr.setBounds(10, 147, 211, 20);
		contentPanel.add(txtWwwcomputechcombr);
		txtWwwcomputechcombr.setColumns(10);

		txtVerso = new JTextField();
		txtVerso.setEditable(false);
		txtVerso.setBorder(null);
		txtVerso.setText("Vers\u00E3o 1.0");
		txtVerso.setBounds(10, 207, 211, 20);
		contentPanel.add(txtVerso);
		txtVerso.setColumns(10);
	}
}
