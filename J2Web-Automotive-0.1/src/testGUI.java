import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;


public class testGUI {

	private JFrame frmJwebAutomotive;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testGUI window = new testGUI();
					window.frmJwebAutomotive.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJwebAutomotive = new JFrame();
		frmJwebAutomotive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		frmJwebAutomotive.setTitle("J2Web - Automotive 0.5");
		frmJwebAutomotive.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\imaginationLogo.png"));
		frmJwebAutomotive.setBounds(100, 100, 1024, 600);
		frmJwebAutomotive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJwebAutomotive.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmJwebAutomotive.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Veicolo", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{437, 0};
		gbl_panel.rowHeights = new int[] {300, 100, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{3.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{143, 143, 143, 0};
		gbl_panel_3.rowHeights = new int[]{174, 0};
		gbl_panel_3.columnWeights = new double[]{2.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.insets = new Insets(0, 0, 0, 5);
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 0;
		panel_3.add(panel_11, gbc_panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{204, 0};
		gbl_panel_11.rowHeights = new int[]{237, 30, 0};
		gbl_panel_11.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_11.add(scrollPane, gbc_scrollPane);
		
		JPanel panel_16 = new JPanel();
		scrollPane.setViewportView(panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[]{100, 0};
		gbl_panel_16.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_16.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_16.setLayout(gbl_panel_16);
		
		
		
		
		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new TitledBorder(null, "Caratteristiche principali", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_20 = new GridBagConstraints();
		gbc_panel_20.insets = new Insets(0, 0, 5, 0);
		gbc_panel_20.fill = GridBagConstraints.BOTH;
		gbc_panel_20.gridx = 0;
		gbc_panel_20.gridy = 0;
		panel_16.add(panel_20, gbc_panel_20);
		panel_20.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JRadioButton rdbtnAutoveicolo = new JRadioButton("Autoveicolo");
		panel_20.add(rdbtnAutoveicolo, "2, 2");
		
		JRadioButton rdbtnMotoscooter = new JRadioButton("Moto/Scooter");
		panel_20.add(rdbtnMotoscooter, "6, 2");
		
		JLabel lblMarca = new JLabel("Marca");
		panel_20.add(lblMarca, "2, 4");
		
		JLabel lblModello = new JLabel("Modello");
		panel_20.add(lblModello, "6, 4");
		
		JLabel lblVersione = new JLabel("Versione");
		panel_20.add(lblVersione, "10, 4");
		
		JComboBox comboBox_5 = new JComboBox();
		panel_20.add(comboBox_5, "2, 6, fill, default");
		
		JComboBox comboBox_6 = new JComboBox();
		panel_20.add(comboBox_6, "6, 6, fill, default");
		
		JComboBox comboBox_7 = new JComboBox();
		panel_20.add(comboBox_7, "10, 6, fill, default");
		
		JLabel lblDataPrimaImmatricolazione = new JLabel("Data prima immatricolazione");
		panel_20.add(lblDataPrimaImmatricolazione, "2, 8");
		
		JLabel lblCarburante = new JLabel("Carburante");
		panel_20.add(lblCarburante, "10, 8");
		
		JComboBox comboBox = new JComboBox();
		panel_20.add(comboBox, "2, 10, fill, default");
		
		JComboBox comboBox_1 = new JComboBox();
		panel_20.add(comboBox_1, "6, 10, fill, default");
		
		textField = new JTextField();
		panel_20.add(textField, "10, 10, fill, default");
		textField.setColumns(10);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		panel_20.add(lblTipologia, "2, 12");
		
		JLabel lblCarrozzeria = new JLabel("Carrozzeria");
		panel_20.add(lblCarrozzeria, "6, 12");
		
		JLabel lblPostiASedere = new JLabel("Posti a sedere");
		panel_20.add(lblPostiASedere, "10, 12");
		
		JComboBox comboBox_2 = new JComboBox();
		panel_20.add(comboBox_2, "2, 14, fill, default");
		
		JComboBox comboBox_3 = new JComboBox();
		panel_20.add(comboBox_3, "6, 14, fill, default");
		
		JComboBox comboBox_9 = new JComboBox();
		panel_20.add(comboBox_9, "10, 14, fill, default");
		
		JLabel lblPotenzaKw = new JLabel("Potenza KW");
		panel_20.add(lblPotenzaKw, "2, 16");
		
		JLabel lblPotenzaCv = new JLabel("Potenza CV");
		panel_20.add(lblPotenzaCv, "6, 16");
		
		textField_1 = new JTextField();
		panel_20.add(textField_1, "2, 18, fill, default");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel_20.add(textField_2, "6, 18, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblColoreEsterno = new JLabel("Colore esterno");
		panel_20.add(lblColoreEsterno, "2, 20");
		
		JComboBox comboBox_4 = new JComboBox();
		panel_20.add(comboBox_4, "2, 22, fill, default");
		
		JCheckBox chckbxMetallizzato = new JCheckBox("Metallizzato");
		panel_20.add(chckbxMetallizzato, "6, 22");
		
		JLabel lblPrecedentiProprietari = new JLabel("Precedenti proprietari");
		panel_20.add(lblPrecedentiProprietari, "2, 24");
		
		JLabel lblChilometraggio = new JLabel("Chilometraggio");
		panel_20.add(lblChilometraggio, "6, 24");
		
		JComboBox comboBox_8 = new JComboBox();
		panel_20.add(comboBox_8, "2, 26, fill, default");
		
		textField_3 = new JTextField();
		panel_20.add(textField_3, "6, 26, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		panel_20.add(lblPrezzo, "2, 28");
		
		textField_4 = new JTextField();
		panel_20.add(textField_4, "2, 30, fill, default");
		textField_4.setColumns(10);
		
		JCheckBox chckbxTrattabile = new JCheckBox("Trattabile");
		panel_20.add(chckbxTrattabile, "6, 30");
		
		JCheckBox chckbxIvaDeducibile = new JCheckBox("IVA deducibile");
		panel_20.add(chckbxIvaDeducibile, "10, 30");
		
		JLabel lblFinitureInterne = new JLabel("Finiture interne");
		panel_20.add(lblFinitureInterne, "2, 32");
		
		JLabel lblColoreInterni = new JLabel("Colore interni");
		panel_20.add(lblColoreInterni, "6, 32");
		
		JComboBox comboBox_10 = new JComboBox();
		panel_20.add(comboBox_10, "2, 34, fill, default");
		
		JComboBox comboBox_11 = new JComboBox();
		panel_20.add(comboBox_11, "6, 34, fill, default");
		
		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new TitledBorder(null, "Sicurezza", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_21 = new GridBagConstraints();
		gbc_panel_21.insets = new Insets(0, 0, 5, 0);
		gbc_panel_21.fill = GridBagConstraints.BOTH;
		gbc_panel_21.gridx = 0;
		gbc_panel_21.gridy = 1;
		panel_16.add(panel_21, gbc_panel_21);
		panel_21.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblSicurezza = new JLabel("Sicurezza");
		panel_21.add(lblSicurezza, "2, 2, 2, 1, fill, default");
		
		JLabel lblComodit = new JLabel("Comodità");
		panel_21.add(lblComodit, "4, 2, fill, default");
		
		JLabel lblExtra = new JLabel("Extra");
		panel_21.add(lblExtra, "6, 2, fill, default");
		
		JCheckBox chckbxAbs = new JCheckBox("ABS");
		panel_21.add(chckbxAbs, "2, 4, fill, default");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Alzacristalli elettrici");
		panel_21.add(chckbxNewCheckBox, "4, 4, fill, default");
		
		JCheckBox chckbxHandicap = new JCheckBox("Handicap");
		panel_21.add(chckbxHandicap, "6, 4, fill, default");
		
		JCheckBox chckbxAirbag = new JCheckBox("Airbag");
		panel_21.add(chckbxAirbag, "2, 6, fill, default");
		
		JCheckBox chckbxClima = new JCheckBox("Clima");
		panel_21.add(chckbxClima, "4, 6, fill, default");
		
		JCheckBox chckbxCerchiInLega = new JCheckBox("Cerchi in lega");
		panel_21.add(chckbxCerchiInLega, "6, 6, fill, default");
		
		JCheckBox chckbxAntifurto = new JCheckBox("Antifurto");
		panel_21.add(chckbxAntifurto, "2, 8, fill, default");
		
		JCheckBox chckbxNavigatoreSatellitare = new JCheckBox("Navigatore satellitare");
		panel_21.add(chckbxNavigatoreSatellitare, "4, 8, fill, default");
		
		JCheckBox chckbxGancioTraino = new JCheckBox("Gancio traino");
		panel_21.add(chckbxGancioTraino, "6, 8, fill, default");
		
		JCheckBox chckbxChiusuraCentralizzata = new JCheckBox("Chiusura centralizzata");
		panel_21.add(chckbxChiusuraCentralizzata, "2, 10, fill, default");
		
		JCheckBox chckbxRadiolettoreCd = new JCheckBox("Radio/Lettore CD");
		panel_21.add(chckbxRadiolettoreCd, "4, 10, fill, default");
		
		JCheckBox chckbxPortapacchi = new JCheckBox("Portapacchi");
		panel_21.add(chckbxPortapacchi, "6, 10, fill, default");
		
		JCheckBox chckbxContrAutomTrazione = new JCheckBox("Contr. autom. trazione");
		panel_21.add(chckbxContrAutomTrazione, "2, 12, fill, default");
		
		JCheckBox chckbxParkDistControl = new JCheckBox("Park dist. control");
		panel_21.add(chckbxParkDistControl, "4, 12, fill, default");
		
		JCheckBox chckbxSediliSportivi = new JCheckBox("Sedili sportivi");
		panel_21.add(chckbxSediliSportivi, "6, 12, fill, default");
		
		JCheckBox chckbxEsp = new JCheckBox("ESP");
		panel_21.add(chckbxEsp, "2, 14, fill, default");
		
		JCheckBox chckbxSediliRiscaldat = new JCheckBox("Sedili riscaldat");
		panel_21.add(chckbxSediliRiscaldat, "4, 14, fill, default");
		
		JCheckBox chckbxImmobilizer = new JCheckBox("Immobilizer");
		panel_21.add(chckbxImmobilizer, "2, 16, fill, default");
		
		JCheckBox chckbxVosterzo = new JCheckBox("vosterzo");
		panel_21.add(chckbxVosterzo, "4, 16, fill, default");
		
		JCheckBox chckbxFreniADisco = new JCheckBox("Freni a disco");
		panel_21.add(chckbxFreniADisco, "2, 18, fill, default");
		
		JCheckBox chckbxTeMultifunzione = new JCheckBox("volante multifunzione");
		panel_21.add(chckbxTeMultifunzione, "4, 18, fill, default");
		
		JCheckBox chckbxCupolino = new JCheckBox("Cupolino");
		panel_21.add(chckbxCupolino, "2, 20, fill, default");
		
		JPanel panel_22 = new JPanel();
		panel_22.setBorder(new TitledBorder(null, "Altri dettagli", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_22 = new GridBagConstraints();
		gbc_panel_22.insets = new Insets(0, 0, 5, 0);
		gbc_panel_22.fill = GridBagConstraints.BOTH;
		gbc_panel_22.gridx = 0;
		gbc_panel_22.gridy = 2;
		panel_16.add(panel_22, gbc_panel_22);
		panel_22.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_22.add(lblNewLabel, "2, 2");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_22.add(lblNewLabel_1, "6, 2");
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_22.add(lblNewLabel_2, "10, 2");
		
		JComboBox comboBox_12 = new JComboBox();
		panel_22.add(comboBox_12, "2, 4, fill, default");
		
		JComboBox comboBox_13 = new JComboBox();
		panel_22.add(comboBox_13, "6, 4, fill, default");
		
		JComboBox comboBox_14 = new JComboBox();
		panel_22.add(comboBox_14, "10, 4, fill, default");
		
		JLabel lblCilindrata = new JLabel("Cilindrata");
		panel_22.add(lblCilindrata, "2, 6");
		
		JLabel lblClasseDiEmissione = new JLabel("Classe di emissione");
		panel_22.add(lblClasseDiEmissione, "6, 6");
		
		JLabel lblConsumoMedio = new JLabel("Consumo medio");
		panel_22.add(lblConsumoMedio, "10, 6");
		
		JComboBox comboBox_15 = new JComboBox();
		panel_22.add(comboBox_15, "2, 8, fill, default");
		
		JComboBox comboBox_16 = new JComboBox();
		panel_22.add(comboBox_16, "6, 8, fill, default");
		
		JComboBox comboBox_17 = new JComboBox();
		panel_22.add(comboBox_17, "10, 8, fill, default");
		
		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new TitledBorder(null, "Foto/Video", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_23 = new GridBagConstraints();
		gbc_panel_23.insets = new Insets(0, 0, 5, 0);
		gbc_panel_23.fill = GridBagConstraints.BOTH;
		gbc_panel_23.gridx = 0;
		gbc_panel_23.gridy = 3;
		panel_16.add(panel_23, gbc_panel_23);
		panel_23.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("70px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("70px"),
				ColumnSpec.decode("4dlu:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("50px"),}));
		
		JButton btnNewButton_2 = new JButton("Immagine 1");
		panel_23.add(btnNewButton_2, "2, 2");
		
		JPanel panel_25 = new JPanel();
		panel_25.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_25, "4, 2, fill, fill");
		
		JButton btnNewButton_3 = new JButton("Immagine 2");
		panel_23.add(btnNewButton_3, "8, 2");
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_26, "10, 2, fill, fill");
		
		JButton btnNewButton_4 = new JButton("Immagine 3");
		panel_23.add(btnNewButton_4, "2, 4");
		
		JPanel panel_27 = new JPanel();
		panel_27.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_27, "4, 4, fill, fill");
		
		JButton btnNewButton_5 = new JButton("Immagine 4");
		panel_23.add(btnNewButton_5, "8, 4");
		
		JPanel panel_28 = new JPanel();
		panel_28.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_28, "10, 4, fill, fill");
		
		JButton btnNewButton_6 = new JButton("Immagine 5");
		panel_23.add(btnNewButton_6, "2, 6");
		
		JPanel panel_29 = new JPanel();
		panel_29.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_29, "4, 6, fill, fill");
		
		JButton btnNewButton_7 = new JButton("Immagine 6");
		panel_23.add(btnNewButton_7, "8, 6");
		
		JPanel panel_30 = new JPanel();
		panel_30.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_30, "10, 6, fill, fill");
		
		JButton btnNewButton_8 = new JButton("Immagine 7");
		panel_23.add(btnNewButton_8, "2, 8");
		
		JPanel panel_31 = new JPanel();
		panel_31.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_31, "4, 8, fill, fill");
		
		JButton btnNewButton_9 = new JButton("Immagine 8");
		panel_23.add(btnNewButton_9, "8, 8");
		
		JPanel panel_32 = new JPanel();
		panel_32.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_32, "10, 8, fill, fill");
		
		JButton btnNewButton_10 = new JButton("Immagine 9");
		panel_23.add(btnNewButton_10, "2, 10");
		
		JPanel panel_33 = new JPanel();
		panel_33.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_33, "4, 10, fill, fill");
		
		JButton btnNewButton_11 = new JButton("Immagine 10");
		panel_23.add(btnNewButton_11, "8, 10");
		
		JPanel panel_34 = new JPanel();
		panel_34.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_34, "10, 10, fill, fill");
		
		JPanel panel_24 = new JPanel();
		panel_24.setBorder(new TitledBorder(null, "Descrizione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_24 = new GridBagConstraints();
		gbc_panel_24.fill = GridBagConstraints.BOTH;
		gbc_panel_24.gridx = 0;
		gbc_panel_24.gridy = 4;
		panel_16.add(panel_24, gbc_panel_24);
		panel_24.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("200px:grow"),}));
		
		JLabel lblDescrizionemax = new JLabel("Descrizione (max 400 caratteri)");
		panel_24.add(lblDescrizionemax, "2, 2");
		
		JTextPane textPane = new JTextPane();
		panel_24.add(textPane, "2, 4, fill, fill");
		
		JPanel panel_17 = new JPanel();
		GridBagConstraints gbc_panel_17 = new GridBagConstraints();
		gbc_panel_17.fill = GridBagConstraints.BOTH;
		gbc_panel_17.gridx = 0;
		gbc_panel_17.gridy = 1;
		panel_11.add(panel_17, gbc_panel_17);
		panel_17.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_18 = new JPanel();
		panel_17.add(panel_18);
		
		JButton btnNewButton = new JButton("New button");
		panel_18.add(btnNewButton);
		
		JPanel panel_19 = new JPanel();
		panel_17.add(panel_19);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_19.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 0;
		panel_3.add(scrollPane_1, gbc_scrollPane_1);
		
		JPanel panel_9 = new JPanel();
		scrollPane_1.setViewportView(panel_9);
		panel_9.setBackground(Color.GREEN);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 0;
		panel_3.add(scrollPane_2, gbc_scrollPane_2);
		
		JPanel panel_10 = new JPanel();
		scrollPane_2.setViewportView(panel_10);
		panel_10.setBackground(Color.CYAN);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		panel.add(scrollPane_3, gbc_scrollPane_3);
		
		JPanel panel_4 = new JPanel();
		scrollPane_3.setViewportView(panel_4);
		panel_4.setBackground(Color.GREEN);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Cliente", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {437, 0};
		gbl_panel_1.rowHeights = new int[] {300, 100, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{3.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_1.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] {100, 100};
		gbl_panel_5.rowHeights = new int[]{1, 0};
		gbl_panel_5.columnWeights = new double[]{2.0, 1.0};
		gbl_panel_5.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_4.gridx = 0;
		gbc_scrollPane_4.gridy = 0;
		panel_5.add(scrollPane_4, gbc_scrollPane_4);
		
		JPanel panel_12 = new JPanel();
		scrollPane_4.setViewportView(panel_12);
		panel_12.setBackground(Color.RED);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridx = 1;
		gbc_scrollPane_5.gridy = 0;
		panel_5.add(scrollPane_5, gbc_scrollPane_5);
		
		JPanel panel_13 = new JPanel();
		scrollPane_5.setViewportView(panel_13);
		panel_13.setBackground(Color.MAGENTA);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridx = 0;
		gbc_scrollPane_6.gridy = 1;
		panel_1.add(scrollPane_6, gbc_scrollPane_6);
		
		JPanel panel_6 = new JPanel();
		scrollPane_6.setViewportView(panel_6);
		panel_6.setBackground(Color.GREEN);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Incrocio", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {437, 0};
		gbl_panel_2.rowHeights = new int[] {300, 100, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{3.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		panel_2.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] {100, 100, 0};
		gbl_panel_7.rowHeights = new int[]{170, 0};
		gbl_panel_7.columnWeights = new double[]{2.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{4.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_7 = new GridBagConstraints();
		gbc_scrollPane_7.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_7.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_7.gridx = 0;
		gbc_scrollPane_7.gridy = 0;
		panel_7.add(scrollPane_7, gbc_scrollPane_7);
		
		JPanel panel_14 = new JPanel();
		scrollPane_7.setViewportView(panel_14);
		panel_14.setBackground(Color.MAGENTA);
		GridBagLayout gbl_panel_14 = new GridBagLayout();
		gbl_panel_14.columnWidths = new int[]{0, 0};
		gbl_panel_14.rowHeights = new int[]{100, 30, 0};
		gbl_panel_14.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_14.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_14.setLayout(gbl_panel_14);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_8 = new GridBagConstraints();
		gbc_scrollPane_8.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_8.gridx = 1;
		gbc_scrollPane_8.gridy = 0;
		panel_7.add(scrollPane_8, gbc_scrollPane_8);
		
		JPanel panel_15 = new JPanel();
		scrollPane_8.setViewportView(panel_15);
		panel_15.setBackground(Color.BLUE);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_9 = new GridBagConstraints();
		gbc_scrollPane_9.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_9.gridx = 0;
		gbc_scrollPane_9.gridy = 1;
		panel_2.add(scrollPane_9, gbc_scrollPane_9);
		
		JPanel panel_8 = new JPanel();
		scrollPane_9.setViewportView(panel_8);
		panel_8.setBackground(Color.GREEN);
		
		panel_16.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel_24, rdbtnAutoveicolo, rdbtnMotoscooter, lblMarca, panel_20, lblModello, lblVersione, comboBox_5, comboBox_6, comboBox_7, lblDataPrimaImmatricolazione, lblCarburante, comboBox, comboBox_1, textField, lblTipologia, lblCarrozzeria, lblPostiASedere, comboBox_2, comboBox_3, comboBox_9, lblPotenzaKw, lblPotenzaCv, textField_1, textField_2, lblColoreEsterno, comboBox_4, chckbxMetallizzato, lblPrecedentiProprietari, lblChilometraggio, comboBox_8, textField_3, lblPrezzo, textField_4, chckbxTrattabile, chckbxIvaDeducibile, lblFinitureInterne, lblColoreInterni, comboBox_10, comboBox_11, lblDescrizionemax, textPane, panel_23, btnNewButton_2, panel_25, btnNewButton_3, panel_26, btnNewButton_4, panel_27, btnNewButton_5, panel_28, btnNewButton_6, panel_29, btnNewButton_7, panel_30, btnNewButton_8, panel_31, btnNewButton_9, panel_32, btnNewButton_10, panel_33, btnNewButton_11, panel_34, panel_22, lblNewLabel, lblNewLabel_1, lblNewLabel_2, comboBox_12, comboBox_13, comboBox_14, lblCilindrata, lblClasseDiEmissione, lblConsumoMedio, comboBox_15, comboBox_16, comboBox_17, panel_21, lblSicurezza, lblComodit, lblExtra, chckbxAbs, chckbxNewCheckBox, chckbxHandicap, chckbxAirbag, chckbxClima, chckbxCerchiInLega, chckbxAntifurto, chckbxNavigatoreSatellitare, chckbxGancioTraino, chckbxChiusuraCentralizzata, chckbxRadiolettoreCd, chckbxPortapacchi, chckbxContrAutomTrazione, chckbxParkDistControl, chckbxSediliSportivi, chckbxEsp, chckbxSediliRiscaldat, chckbxImmobilizer, chckbxVosterzo, chckbxFreniADisco, chckbxTeMultifunzione, chckbxCupolino}));
		
		JMenuBar menuBar = new JMenuBar();
		frmJwebAutomotive.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnTest = new JMenu("test");
		menuBar.add(mnTest);
		
		JMenuItem mntmTesting = new JMenuItem("testing1");
		mnTest.add(mntmTesting);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnTest.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnTest.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_4);
		frmJwebAutomotive.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmJwebAutomotive.getContentPane(), panel, panel_1, panel_2}));
	}
}
