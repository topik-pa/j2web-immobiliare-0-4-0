import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Component;


public class j2web_GUI extends JFrame {
	private JTextField txtCodiceInserzione;
	private JTextField textTitoloAnnuncio;
	private JTextField textFieldCap;
	private JTextField textFieldIndirizzoLocalita;
	private JTextField textFieldTitoloDellaScheda;
	private JTextField textSuperficieImmobile;
	private JTextField textFieldPrezzoImmobile;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_15;
	private JTextField textField_11;
	private JTextField textField_14;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					j2web_GUI frame = new j2web_GUI();
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
	public j2web_GUI() {
		setTitle("J2Web - Immobiliare ver. 0.4.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Documents and Settings\\user\\workspace\\j2web-0.4.0\\images\\J2Web.ico"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 720);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar_1.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Creazione schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panel_4.add(tabbedPane_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("Dati obbligatori", null, panel_3, null);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("235px"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				RowSpec.decode("17px"),}));
		
		JLabel lblCodiceInserzione = new JLabel("Codice inserzione");
		panel_3.add(lblCodiceInserzione, "1, 2");
		
		txtCodiceInserzione = new JTextField();
		lblCodiceInserzione.setLabelFor(txtCodiceInserzione);
		txtCodiceInserzione.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodiceInserzione.setToolTipText("Inserire un codice per identificare univocamente l'annuncio");
		txtCodiceInserzione.setColumns(10);
		panel_3.add(txtCodiceInserzione, "1, 3");
		
		JLabel lblTitoloAnnuncio = new JLabel("Titolo annuncio");
		panel_3.add(lblTitoloAnnuncio, "1, 5");
		lblTitoloAnnuncio.setLabelFor(textTitoloAnnuncio);
		
		textTitoloAnnuncio = new JTextField();
		textTitoloAnnuncio.setToolTipText("Inserire un titolo per l'annuncio");
		textTitoloAnnuncio.setColumns(28);
		panel_3.add(textTitoloAnnuncio, "1, 6");
		
		JLabel lblRegione = new JLabel("Regione");
		panel_3.add(lblRegione, "1, 8");
		
		JComboBox comboBoxRegione = new JComboBox();
		comboBoxRegione.setToolTipText("Selezionare la regione");
		lblRegione.setLabelFor(comboBoxRegione);
		panel_3.add(comboBoxRegione, "1, 9");
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setToolTipText("Selezionare la provincia");
		panel_3.add(lblProvincia, "1, 11");
		
		JComboBox comboBoxProvincia = new JComboBox();
		lblProvincia.setLabelFor(comboBoxProvincia);
		comboBoxProvincia.setToolTipText("Selezionare la provincia");
		panel_3.add(comboBoxProvincia, "1, 12");
		lblComune.setLabelFor(comboBoxComune);
		
		JLabel lblComune = new JLabel("Comune");
		lblComune.setToolTipText("Selezionare il comune");
		panel_3.add(lblComune, "1, 14");
		
		JComboBox comboBoxComune = new JComboBox();
		comboBoxComune.setToolTipText("Selezionare il comune");
		panel_3.add(comboBoxComune, "1, 15");
		
		JLabel lblCap = new JLabel("CAP");
		panel_3.add(lblCap, "1, 17");
		
		textFieldCap = new JTextField();
		textFieldCap.setToolTipText("Inserire il CAP");
		lblCap.setLabelFor(textFieldCap);
		textFieldCap.setColumns(10);
		panel_3.add(textFieldCap, "1, 18");
		
		JLabel lblIndirizzoLocalita = new JLabel("Indirizzo/Località");
		panel_3.add(lblIndirizzoLocalita, "1, 20");
		lblIndirizzoLocalita.setLabelFor(textFieldIndirizzoLocalita);
		
		textFieldIndirizzoLocalita = new JTextField();
		textFieldIndirizzoLocalita.setToolTipText("Specificare un indirizzo completo o la località dell'immobile");
		textFieldIndirizzoLocalita.setColumns(28);
		panel_3.add(textFieldIndirizzoLocalita, "1, 21");
		lblTitoloDellaScheda.setLabelFor(textFieldTitoloDellaScheda);
		
		JLabel lblTitoloDellaScheda = new JLabel("Titolo della scheda");
		lblTitoloDellaScheda.setToolTipText("Inserire un titolo descrittivo per l'annuncio");
		panel_3.add(lblTitoloDellaScheda, "1, 23");
		
		textFieldTitoloDellaScheda = new JTextField();
		textFieldTitoloDellaScheda.setToolTipText("Inserire un titolo descrittivo per l'annuncio");
		panel_3.add(textFieldTitoloDellaScheda, "1, 24");
		textFieldTitoloDellaScheda.setColumns(28);
		
		JLabel lblTestoDellaScheda = new JLabel("Testo della scheda");
		panel_3.add(lblTestoDellaScheda, "1, 26");
		lblTestoDellaScheda.setLabelFor(editorPaneTestoDellaScheda);
		
		JEditorPane editorPaneTestoDellaScheda = new JEditorPane();
		editorPaneTestoDellaScheda.setToolTipText("Inserire una descrizione completa dell'immobile");
		panel_3.add(editorPaneTestoDellaScheda, "1, 27");
		
		JLabel lblCategoriaImmobile = new JLabel("Categoria immobile");
		panel_3.add(lblCategoriaImmobile, "1, 29");
		lblCategoriaImmobile.setLabelFor(comboBoxCategoriaImmobile);
		
		JComboBox comboBoxCategoriaImmobile = new JComboBox();
		comboBoxCategoriaImmobile.setToolTipText("Selezionare la categoria dell'immobile");
		panel_3.add(comboBoxCategoriaImmobile, "1, 30");
		
		JLabel lblTipologiaImmobile = new JLabel("Tipologia immobile");
		panel_3.add(lblTipologiaImmobile, "1, 32");
		lblTipologiaImmobile.setLabelFor(comboBoxTipologiaImmobile);
		
		JComboBox comboBoxTipologiaImmobile = new JComboBox();
		comboBoxTipologiaImmobile.setToolTipText("Selezionare la tipologia di immobile");
		panel_3.add(comboBoxTipologiaImmobile, "1, 33");
		
		JLabel lblSuperficieImmmobile = new JLabel("Superficie (mq)");
		panel_3.add(lblSuperficieImmmobile, "1, 35");
		lblSuperficieImmmobile.setLabelFor(textSuperficieImmobile);
		
		textSuperficieImmobile = new JTextField();
		textSuperficieImmobile.setToolTipText("Inserire la superficie dell'immobile in metri quadrati");
		panel_3.add(textSuperficieImmobile, "1, 36");
		textSuperficieImmobile.setColumns(10);
		lblPrezzoImmobile.setLabelFor(textFieldPrezzoImmobile);
		
		JLabel lblTipologiaContratto = new JLabel("Tipologia contratto");
		lblTipologiaContratto.setToolTipText("Selezionare la tipologia di contratto valida per l'immobile");
		panel_3.add(lblTipologiaContratto, "1, 38");
		lblTipologiaContratto.setLabelFor(comboBoxTipologiaContratto);
		
		JComboBox comboBoxTipologiaContratto = new JComboBox();
		comboBoxTipologiaContratto.setToolTipText("Selezionare la tipologia di contratto valida per l'immobile");
		panel_3.add(comboBoxTipologiaContratto, "1, 39");
		
		JLabel lblPrezzoImmobile = new JLabel("Prezzo");
		panel_3.add(lblPrezzoImmobile, "1, 41");
		
		textFieldPrezzoImmobile = new JTextField();
		textFieldPrezzoImmobile.setToolTipText("Inserire il prezzo di vendita o affitto dell'immobile");
		panel_3.add(textFieldPrezzoImmobile, "1, 42");
		textFieldPrezzoImmobile.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("Immagini", null, panel_5, null);
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("max(8dlu;min)"),
				ColumnSpec.decode("121px"),},
			new RowSpec[] {
				RowSpec.decode("25px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("25px"),}));
		
		JButton btnNewButton = new JButton("New button");
		panel_5.add(btnNewButton, "1, 1, fill, fill");
		
		textField_7 = new JTextField();
		panel_5.add(textField_7, "3, 1, fill, fill");
		textField_7.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_5.add(btnNewButton_1, "1, 3, fill, fill");
		
		textField_8 = new JTextField();
		panel_5.add(textField_8, "3, 3, fill, fill");
		textField_8.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel_5.add(btnNewButton_4, "1, 5, fill, fill");
		
		textField_9 = new JTextField();
		panel_5.add(textField_9, "3, 5, fill, fill");
		textField_9.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_5.add(btnNewButton_3, "1, 7, fill, fill");
		
		textField_10 = new JTextField();
		panel_5.add(textField_10, "3, 7, fill, fill");
		textField_10.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("New button");
		panel_5.add(btnNewButton_6, "1, 9, fill, fill");
		
		textField_12 = new JTextField();
		panel_5.add(textField_12, "3, 9, fill, fill");
		textField_12.setColumns(10);
		
		JButton btnNewButton_7 = new JButton("New button");
		panel_5.add(btnNewButton_7, "1, 11, fill, fill");
		
		textField_13 = new JTextField();
		panel_5.add(textField_13, "3, 11, fill, fill");
		textField_13.setColumns(10);
		
		JButton btnNewButton_9 = new JButton("New button");
		panel_5.add(btnNewButton_9, "1, 11, fill, fill");
		
		textField_15 = new JTextField();
		panel_5.add(textField_15, "3, 11, fill, fill");
		textField_15.setColumns(10);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Dati secondari", null, panel, null);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Numero locali");
		panel.add(lblNewLabel_6);
		
		JComboBox comboBox_6 = new JComboBox();
		panel.add(comboBox_6);
		
		JLabel lblNewLabel_5 = new JLabel("Numero camere da letto");
		panel.add(lblNewLabel_5);
		
		JComboBox comboBox_7 = new JComboBox();
		panel.add(comboBox_7);
		
		JLabel lblNewLabel_7 = new JLabel("Numero bagni");
		panel.add(lblNewLabel_7);
		
		JComboBox comboBox_8 = new JComboBox();
		panel.add(comboBox_8);
		
		JLabel lblNewLabel_8 = new JLabel("Stato dell'immobile");
		panel.add(lblNewLabel_8);
		
		JComboBox comboBox_9 = new JComboBox();
		panel.add(comboBox_9);
		
		JLabel lblNewLabel_9 = new JLabel("Arredamenti");
		panel.add(lblNewLabel_9);
		
		JComboBox comboBox_10 = new JComboBox();
		panel.add(comboBox_10);
		
		JLabel lblNewLabel_10 = new JLabel("Tipologia di riscaldamento");
		panel.add(lblNewLabel_10);
		
		JComboBox comboBox_11 = new JComboBox();
		panel.add(comboBox_11);
		
		JLabel lblNewLabel_11 = new JLabel("Clima");
		panel.add(lblNewLabel_11);
		
		JComboBox comboBox_12 = new JComboBox();
		panel.add(comboBox_12);
		
		JLabel lblNewLabel_12 = new JLabel("Certificazioni energetiche");
		panel.add(lblNewLabel_12);
		
		JComboBox comboBox_13 = new JComboBox();
		panel.add(comboBox_13);
		
		JLabel lblNewLabel_13 = new JLabel("Giardino");
		panel.add(lblNewLabel_13);
		
		JComboBox comboBox_14 = new JComboBox();
		panel.add(comboBox_14);
		
		JLabel lblNewLabel_14 = new JLabel("Parcheggio");
		panel.add(lblNewLabel_14);
		
		JComboBox comboBox_15 = new JComboBox();
		panel.add(comboBox_15);
		
		JLabel lblNewLabel_15 = new JLabel("Piano");
		panel.add(lblNewLabel_15);
		
		JComboBox comboBox_16 = new JComboBox();
		panel.add(comboBox_16);
		
		JLabel lblNewLabel_16 = new JLabel("Numero totale di piani");
		panel.add(lblNewLabel_16);
		
		textField_11 = new JTextField();
		panel.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("Anno");
		panel.add(lblNewLabel_17);
		
		textField_14 = new JTextField();
		panel.add(textField_14);
		textField_14.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_4);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_5);
		
		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_6);
		
		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_7);
		
		JCheckBox chckbxNewCheckBox_8 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_8);
		
		JCheckBox chckbxNewCheckBox_9 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_9);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Schede immobile create", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new TitledBorder(null, "Inserimento schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(scrollPane_2);
	}
}
