import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import javax.swing.border.EtchedBorder;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;


public class J2Web_UI implements parametriGenerali{

	//Nome della GUI
	private static JFrame imagination_05;

	//Campi della GUI
	//Form veicolo
	//Combobox
	private static JComboBox<String> comboBox_Versione;
	private static JComboBox<String> comboBox_Marca;
	private static JComboBox<String> comboBox_Modello;
	private static JComboBox<String> comboBox_Tipologia;
	private static JComboBox<String> comboBox_Carrozzeria;
	private static JComboBox<String> comboBox_PostiASedere;
	private static JComboBox<String> comboBox_FinitureInterni;
	private static JComboBox<String> comboBox_ColoreInterni;
	private static JComboBox<String> comboBox_Motore;
	private static JComboBox<String> comboBox_Carburante;
	private static JComboBox<String> comboBox_MeseImmatricolazione;
	private static JComboBox<String> comboBox_AnnoImmatricolazione;
	private static JComboBox<String> comboBox_ColoreEsterno;
	private static JComboBox<String> comboBox_PrecedentiProprietari;
	private static JComboBox<String> comboBox_Cambio;
	private static JComboBox<String> comboBox_NumeroRapporti;
	private static JComboBox<String> comboBox_ClasseEmissioni;
	private static JTextField comboBox_ConsumoMedio;
	private static JTextField comboBox_Cilindrata;
	//Textfield	
	private static JTextField txtFieldKw;
	private static JTextField txtFieldCv;
	private static JTextField textField_Chilometraggio;
	private static JTextField textField_Prezzo;
	private static JTextField txtField_YouTubeUrl;
	private static JTextField textFieldRagioneSociale;
	private static JTextField textFieldIndirizzo;
	private static JTextField textFieldTelefonoGenerico;
	private static JTextField textFieldReferente;
	private static JTextField textFieldTelefonoReferente;
	private static JTextField textFieldEmailReferente;
	//Checkbox
	private static JCheckBox chckbxCupolino;
	private static JCheckBox chckbxHandicap;
	private static JCheckBox chckbxServosterzo;
	private static JCheckBox chckbxSediliSportivi;
	private static JCheckBox chckbxBauletto;
	private static JCheckBox chckbxAvviamentoAPedale;
	private static JCheckBox chckbxAvviamentoElettrico;
	private static JCheckBox chckbxParkDistControl;
	private static JCheckBox chckbxFreniADisco;
	private static JCheckBox chckbxRadiolettoreCd;
	private static JCheckBox chckbxAntifurto;
	private static JCheckBox chckbxAbs;
	private static JCheckBox chckbxGancioTraino;
	private static JCheckBox chckbxVolanteMultifunzione;
	private static JCheckBox chckbxImmobilizer;
	private static JCheckBox chckbxPortapacchi;
	private static JCheckBox chckbxAirbag;
	private static JCheckBox chckbxEsp;
	private static JCheckBox chckbxAlzacristalliElettrici;
	private static JCheckBox chckbxNavigatoreSatellitare;
	private static JCheckBox chckbxCerchiInLega;
	private static JCheckBox chckbxContrAutomTrazione;
	private static JCheckBox chckbxChiusuraCentralizzata;
	private static JCheckBox chckbxSediliRiscaldati;
	private static JCheckBox chckbxClima;
	private static JCheckBox chckbxTrattabile;
	private static JCheckBox chckbxIvaDeducibile;
	private static JCheckBox chckbxMetallizzato;
	//Radio button
	private static JRadioButton rdbtnAutoveicolo;
	private static JRadioButton rdbtnMotoScooter;
	//Button group
	private static final ButtonGroup buttonGroup = new ButtonGroup(); //auto/moto
	//Label immagine
	private static JLabel label_Immagine1;
	private static JLabel label_Immagine2;
	private static JLabel label_Immagine3;
	private static JLabel label_Immagine4;
	private static JLabel label_Immagine5;
	private static JLabel label_Immagine6;
	private static JLabel label_Immagine7;
	private static JLabel label_Immagine8;
	private static JLabel label_Immagine9;
	private static JLabel label_Immagine10;
	//Textpane
	private static JTextPane textPane_Descrizione;
	//Form cliente
	//Combobox
	private static JComboBox<String> comboBox_Marca_Cliente;
	private static JComboBox<String> comboBox_Modello_Cliente;
	private static JComboBox<String> comboBox_Versione_Cliente;
	private static JComboBox<String> comboBox_TipologiaCarburante_Cliente;
	private static JComboBox<String> comboBox_Colore_Cliente;
	private static JComboBox<String> comboBoxTipologia_Cliente;
	//Textfield
	private static JTextField formCliente_textFieldNome;
	private static JTextField formCliente_textFieldCognome;
	private static JTextField formCliente_textFieldEmail;
	private static JTextField formCliente_textFieldTelefono1;
	private static JTextField formCliente_textFieldTelefono2;
	private static JTextField formCliente_textFieldVia;
	private static JTextField formCliente_textFieldNumeroCivico;
	private static JTextField formCliente_textFieldCAP;
	private static JTextField formCliente_textFieldCitta;
	//Radio button
	private static JRadioButton formCliente_rdbtnSignore;
	private static JRadioButton formCliente_rdbtnSignora;
	//Button group
	private static final ButtonGroup buttonGroup_1 = new ButtonGroup(); //sign/sigra


	//Variabili immagine
	private static File imgFile1;
	private static File imgFile2;
	private static File imgFile3;
	private static File imgFile4;
	private static File imgFile5;
	private static File imgFile6;
	private static File imgFile7;
	private static File imgFile8;
	private static File imgFile9;
	private static File imgFile10;

	//Variabili pannello
	private static JPanel panel_9;
	private static JPanel panel_10;
	private static JPanel panel_13;
	private static JPanel panel_4;
	private static JPanel panel_6;
	private static JPanel panel_31;
	private static JPanel panel_15;
	private static JPanel panel_8;

	//Button group usato nei sottopannelli scheda
	private static final ButtonGroup radioGrpSchede = new ButtonGroup();

	//La struttura dati che contiene le schede veicolo create e utilizzate a runtime (deve essere inserita qui in quanto non può essere 'final')
	public static LinkedList<SchedaVeicolo> listSchedeVeicolo = new LinkedList<SchedaVeicolo>();

	//La struttura dati che contiene le schede cliente create e utilizzate a runtime (deve essere inserita qui in quanto non può essere 'final')
	public static LinkedList<SchedaCliente> listSchedeCliente = new LinkedList<SchedaCliente>();

	//Serve per bloccare temporaneamente l'ascoltatore di alcune combobox
	public static boolean nonUserSelection = false;
	private JButton btnImmagine1;
	private JButton btnImmagine2;
	private JButton btnImmagine3;
	private JButton btnImmagine4;
	private JButton btnImmagine5;
	private JButton btnImmagine6;
	private JButton btnImmagine7;
	private JButton btnImmagine8;
	private JButton btnImmagine9;
	private JButton btnImmagine10;



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
					new J2Web_UI();
					J2Web_UI.imagination_05.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the application.
	 */
	public J2Web_UI() {

		//Procedure di inizializzazione
		System.out.print("Inizializzo la GUI e gli ascoltatori...");
		initialize(); //GUI e ascoltatori
		System.out.print(" fatto." + "\n");

		//Popolo la mappa dei dialoghi modali
		System.out.print("Popolo la mappa dei dialoghi modali...");
		j2web.inizializzaMappaDialoghiModali();
		System.out.print(" fatto." + "\n");

		//Popolo la mappa dei limiti di caratteri per i campi testuali
		System.out.print("Popolo la mappa dei limiti di caratteri per i campi testuali...");
		j2web.inizializzaMappaLimiteCaratteri();
		System.out.print(" fatto." + "\n");

		//Popolo la lista delle schede caricando i dati dal file dat
		System.out.print("Carico la lista delle schede veicolo precedentemente salvate...");
		j2web.caricaListaSchedeVeicoloCreate();
		System.out.print(" fatto." + "\n");

		//Carica le schede create nelle sessioni precedenti
		System.out.print("Carico la lista delle schede cliente precedentemente salvate...");
		j2web.caricaListaSchedeClienteCreate();
		System.out.print(" fatto." + "\n");

		//Inizializzo la lista concatenata che contiene le informazioni sui portali web in sincronizzazione
		System.out.print("Inizializzo la lista concatenata che contiene le informazioni sui portali web in sincronizzazione...");
		j2web.inizializzaPortaliAttivi();
		System.out.print(" fatto." + "\n");

		//La selezione della categoria autoveicoli, nella form veicoli, è forzata all'avvio
		selezioneAutoVeicolo(); 

		//I campi della form veicolo sono inseriti in una lista concatenata
		System.out.print("Popolo la lista dei campi veicolo...");
		popolaListaCampiFormVeicolo();
		System.out.print(" fatto." + "\n");

		//I campi della form cliente sono inseriti in una lista concatenata
		System.out.print("Popolo la lista dei campi cliente...");
		popolaListaCampiFormCliente();
		System.out.print(" fatto." + "\n");

		//I campi della form veicolo obbligatori sono inseriti in una lista concatenata
		System.out.print("Popolo la lista dei campi veicolo obbligatori...");
		popolaListaCampiObbligatoriFormVeicolo();
		System.out.print(" fatto." + "\n");

		//I campi della form cliente obbligatori sono inseriti in una lista concatenata
		System.out.print("Popolo la lista dei campi cliente obbligatori...");
		popolaListaCampiObbligatoriFormCliente();
		System.out.print(" fatto." + "\n");				

		//Aggiorno i pannelli della GUI con le info delle schede caricate nei passi precedenti
		System.out.print("Aggiorno i pannelli della GUI...\n");
		//aggiorna il pannello centrale (lista veicoli)
		aggiornaPannelloListaSchedeVeicolo(); 
		//aggiorna il pannello centrale (lista veicoli)
		aggiornaPannelloListaSchedeCliente(); 
		//aggiorna il pannello di destra (lista portali attivi)
		aggiornaPannelloListaPortaliSincronizzazione();
		System.out.print(" fatto." + "\n");

	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/*Il JFrame principale*/
		imagination_05 = new JFrame();


		imagination_05.addWindowListener(new WindowAdapter() {
			@Override
			//Messaggio di chiusura applicazione
			public void windowClosing(WindowEvent e) {
				System.out.println("Ciao ciao...");
			}
		});

		//Caratteristiche principali della GUI
		imagination_05.setTitle(nomeGUI);
		imagination_05.setIconImage(frameIcon);
		imagination_05.setBounds(GUI_bounds[0], GUI_bounds[1], GUI_bounds[2], GUI_bounds[3]);
		imagination_05.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imagination_05.getContentPane().setLayout(new BorderLayout(0, 0));

		//Le tab
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		imagination_05.getContentPane().add(tabbedPane, BorderLayout.CENTER);










		//Prima tab
		JPanel panel = new JPanel();
		panel.setBorder(null);
		tabbedPane.addTab("Anagrafica veicolo", icoAnagrVeicolo, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{437, 0};
		gbl_panel.rowHeights = new int[] {170, 70, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{4.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{450, 330, 330, 0};
		gbl_panel_3.rowHeights = new int[]{174, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		gbl_panel_16.columnWidths = new int[]{130, 0};
		gbl_panel_16.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_16.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_16.setLayout(gbl_panel_16);	

		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Caratteristiche principali veicolo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		rdbtnAutoveicolo = new JRadioButton("Autoveicolo");
		rdbtnAutoveicolo.setToolTipText("Selezione autoveicolo");
		rdbtnAutoveicolo.setSelected(true);
		rdbtnAutoveicolo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Al click sulla radio Veicolo la form si attiva in modalità veicolo
				selezioneAutoVeicolo();
			}
		});
		buttonGroup.add(rdbtnAutoveicolo);
		panel_20.add(rdbtnAutoveicolo, "2, 2");

		rdbtnMotoScooter = new JRadioButton("Moto/Scooter");
		rdbtnMotoScooter.setToolTipText("Selezione moto/scooter");
		rdbtnMotoScooter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Al click sulla radio Moto/Scooter la form si attiva in modalità moto/scooter
				selezioneMotoScooter();
			}
		});
		buttonGroup.add(rdbtnMotoScooter);
		panel_20.add(rdbtnMotoScooter, "6, 2");

		JLabel lblMarca = new JLabel("Marca*");
		panel_20.add(lblMarca, "2, 4");

		JLabel lblModello = new JLabel("Modello*");
		panel_20.add(lblModello, "6, 4");

		JLabel lblVersione = new JLabel("Versione*");
		panel_20.add(lblVersione, "10, 4");

		comboBox_Marca = new JComboBox<String>();
		comboBox_Marca.setToolTipText("Selezione marca veicolo");
		comboBox_Marca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {			

				//Non fare nulla se l'azione non è dell'utente
				if (nonUserSelection) {return;}

				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					//Selezionando un marca veicolo popolo la combobox Modello veicolo
					String marcaVeicoloSelezionata = (String) comboBox_Marca.getSelectedItem();

					if(!marcaVeicoloSelezionata.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {					
						JComboBox<String> comboboxModello =  getComboBox_Modello();
						JComboBox<String> comboboxVersione = getComboBox_Versione();

						popolaModelloVeicolo(marcaVeicoloSelezionata, comboboxModello, comboboxVersione);
					}						
				}	
			}
		});
		comboBox_Marca.setModel(new DefaultComboBoxModel<String>(marcheAutoveicoli));
		panel_20.add(comboBox_Marca, "2, 6, fill, default");

		comboBox_Modello = new JComboBox<String>();
		comboBox_Modello.setToolTipText("Selezione modello veicolo");
		comboBox_Modello.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				//Non fare nulla se l'azione non è dell'utente
				if (nonUserSelection) {return;}
				
				//Selezionando un modello veicolo popolo la combobox Versione veicolo
				if (e.getStateChange() == ItemEvent.SELECTED) {

					String marcaVeicolo = (String) comboBox_Marca.getSelectedItem();
					String modelloVeicolo = (String) comboBox_Modello.getSelectedItem();
					JComboBox<String> comboboxVersione = getComboBox_Versione();

					if(modelloVeicolo!=null && !modelloVeicolo.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {
						popolaVersioneVeicolo(marcaVeicolo, modelloVeicolo, comboboxVersione);
					}								
				}
			}
		});
		comboBox_Modello.setEditable(true);
		panel_20.add(comboBox_Modello, "6, 6, fill, default");

		comboBox_Versione = new JComboBox<String>();
		comboBox_Versione.setToolTipText("Selezione versione veicolo");
		comboBox_Versione.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				//Non fare nulla se l'azione non è dell'utente
				if (nonUserSelection) {return;}
				
				//Selezionando una versione veicolo popolo vari campi della form veicolo				
				String versioneVeicolo = (String) comboBox_Versione.getSelectedItem();
				if(versioneVeicolo!=null && !versioneVeicolo.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {

					int selectedComboboxIndex = comboBox_Versione.getSelectedIndex();
					//C'è una corrispondenza uno-a-uno (al netto della "Seleziona") tra la lista delle versioni selezionabili nella combobox e gli oggetti inseriti nella lista listVersioniVeicoli
					String selectedModelId = listVersioniVeicoli.get(selectedComboboxIndex - 1); //meno uno perchè esiste anche la label "Seleziona"
					popolaInfoVeicolo(selectedModelId);
				}			
			}
		});
		comboBox_Versione.setEditable(true);
		panel_20.add(comboBox_Versione, "10, 6, fill, default");

		JLabel lblDataPrimaImmatricolazione = new JLabel("Prima immatricolazione");
		panel_20.add(lblDataPrimaImmatricolazione, "2, 8");

		JLabel lblCarburante = new JLabel("Carburante");
		panel_20.add(lblCarburante, "10, 8");

		comboBox_MeseImmatricolazione = new JComboBox<String>();
		comboBox_MeseImmatricolazione.setToolTipText("Selezione mese prima immatricolazione");
		comboBox_MeseImmatricolazione.setModel(new DefaultComboBoxModel<String>(comboboxModelMesi));
		panel_20.add(comboBox_MeseImmatricolazione, "2, 10, fill, default");

		comboBox_AnnoImmatricolazione = new JComboBox<String>();
		comboBox_AnnoImmatricolazione.setToolTipText("Selezione anno prima immatricolazione");
		comboBox_AnnoImmatricolazione.setModel(new DefaultComboBoxModel<String>(comboboxModelAnni));
		panel_20.add(comboBox_AnnoImmatricolazione, "6, 10, fill, default");

		comboBox_Carburante = new JComboBox<String>();
		comboBox_Carburante.setToolTipText("Selezione tipologia carburante");
		comboBox_Carburante.setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));
		panel_20.add(comboBox_Carburante, "10, 10, fill, default");

		JLabel lblTipologia = new JLabel("Tipologia*");
		panel_20.add(lblTipologia, "2, 12");

		JLabel lblCarrozzeria = new JLabel("Carrozzeria");
		panel_20.add(lblCarrozzeria, "6, 12");

		JLabel lblPostiASedere = new JLabel("Posti a sedere");
		panel_20.add(lblPostiASedere, "10, 12");

		comboBox_Tipologia = new JComboBox<String>();
		comboBox_Tipologia.setToolTipText("Selezione tipologia veicolo");
		comboBox_Tipologia.setModel(new DefaultComboBoxModel<String>(tipologiaAutoveicoli));
		panel_20.add(comboBox_Tipologia, "2, 14, fill, default");

		comboBox_Carrozzeria = new JComboBox<String>();
		comboBox_Carrozzeria.setToolTipText("Selezione carrozzeria veicolo");
		comboBox_Carrozzeria.setModel(new DefaultComboBoxModel<String>(comboboxModelCarrozzeria));
		panel_20.add(comboBox_Carrozzeria, "6, 14, fill, default");

		comboBox_PostiASedere = new JComboBox<String>();
		comboBox_PostiASedere.setToolTipText("Selezione posti a sedere omologati");
		comboBox_PostiASedere.setModel(new DefaultComboBoxModel<String>(comboboxModelPostiASedere));
		panel_20.add(comboBox_PostiASedere, "10, 14, fill, default");

		JLabel lblPotenzaKw = new JLabel("Potenza (KW/CV)");
		panel_20.add(lblPotenzaKw, "2, 16");

		txtFieldKw = new JTextField();
		txtFieldKw.setToolTipText("Inserimento potenza in KW");
		txtFieldKw.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo i tipo e numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || txtFieldKw.getText().length()>=maxCaratteri.get("txtFieldKw")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		txtFieldKw.setText("");
		panel_20.add(txtFieldKw, "2, 18, fill, default");
		txtFieldKw.setColumns(10);

		txtFieldCv = new JTextField();
		txtFieldCv.setToolTipText("Inserimento potenza in CV");
		txtFieldCv.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo i tipo e numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || txtFieldCv.getText().length()>=maxCaratteri.get("txtFieldCv")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		txtFieldCv.setText("");
		panel_20.add(txtFieldCv, "6, 18, fill, default");
		txtFieldCv.setColumns(10);

		JLabel lblColoreEsterno = new JLabel("Colore esterno*");
		panel_20.add(lblColoreEsterno, "2, 20");

		comboBox_ColoreEsterno = new JComboBox<String>();
		comboBox_ColoreEsterno.setToolTipText("Selezione colore esterno veicolo");
		comboBox_ColoreEsterno.setModel(new DefaultComboBoxModel<String>(comboboxModelColoreEsterno));
		panel_20.add(comboBox_ColoreEsterno, "2, 22, fill, default");

		chckbxMetallizzato = new JCheckBox("Metallizzato");
		chckbxMetallizzato.setToolTipText("Il colore esterno è metallizzato?");
		panel_20.add(chckbxMetallizzato, "6, 22");

		JLabel lblPrecedentiProprietari = new JLabel("Precedenti proprietari");
		panel_20.add(lblPrecedentiProprietari, "2, 24");

		JLabel lblChilometraggio = new JLabel("Chilometraggio");
		panel_20.add(lblChilometraggio, "6, 24");

		comboBox_PrecedentiProprietari = new JComboBox<String>();
		comboBox_PrecedentiProprietari.setToolTipText("Selezione numero dei precedenti proprietari");
		comboBox_PrecedentiProprietari.setModel(new DefaultComboBoxModel<String>(comboboxModelPrecedentiProprietari));
		panel_20.add(comboBox_PrecedentiProprietari, "2, 26, fill, default");

		textField_Chilometraggio = new JTextField();
		textField_Chilometraggio.setToolTipText("Inserimento chilometri percorsi dal veicolo");
		textField_Chilometraggio.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo i tipo e numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || textField_Chilometraggio.getText().length()>=maxCaratteri.get("textField_Chilometraggio")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		panel_20.add(textField_Chilometraggio, "6, 26, fill, default");
		textField_Chilometraggio.setColumns(10);

		JLabel lblPrezzo = new JLabel("Prezzo*");
		panel_20.add(lblPrezzo, "2, 28");

		textField_Prezzo = new JTextField();
		textField_Prezzo.setToolTipText("Inserimento prezzo di vendita del veicolo");
		textField_Prezzo.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo i tipo e numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || textField_Prezzo.getText().length()>=maxCaratteri.get("textField_Prezzo")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		panel_20.add(textField_Prezzo, "2, 30, fill, default");
		textField_Prezzo.setColumns(10);

		chckbxTrattabile = new JCheckBox("Trattabile");
		chckbxTrattabile.setToolTipText("Il prezzo di vendita è trattabile?");
		panel_20.add(chckbxTrattabile, "6, 30");

		chckbxIvaDeducibile = new JCheckBox("IVA deducibile");
		chckbxIvaDeducibile.setToolTipText("Il prezzo di vendita è deducibile?");
		panel_20.add(chckbxIvaDeducibile, "10, 30");

		JLabel lblFinitureInterne = new JLabel("Finiture interni");
		panel_20.add(lblFinitureInterne, "2, 32");

		JLabel lblColoreInterni = new JLabel("Colore interni");
		panel_20.add(lblColoreInterni, "6, 32");

		comboBox_FinitureInterni = new JComboBox<String>();
		comboBox_FinitureInterni.setToolTipText("Selezione fiiture interne veicolo");
		comboBox_FinitureInterni.setModel(new DefaultComboBoxModel<String>(comboboxModelFinitureInterni));
		panel_20.add(comboBox_FinitureInterni, "2, 34, fill, default");

		comboBox_ColoreInterni = new JComboBox<String>();
		comboBox_ColoreInterni.setToolTipText("Selezione colore interno del veicolo");
		comboBox_ColoreInterni.setModel(new DefaultComboBoxModel<String>(comboboxModelColoreInterni));
		panel_20.add(comboBox_ColoreInterni, "6, 34, fill, default");

		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Equipaggiamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		lblSicurezza.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panel_21.add(lblSicurezza, "2, 2, 2, 1, fill, default");

		JLabel lblComodit = new JLabel("Comodità");
		lblComodit.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panel_21.add(lblComodit, "4, 2, fill, default");

		JLabel lblExtra = new JLabel("Altro");
		lblExtra.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panel_21.add(lblExtra, "6, 2, fill, default");

		chckbxAbs = new JCheckBox("ABS");
		chckbxAbs.setToolTipText("Veicolo con ABS");
		panel_21.add(chckbxAbs, "2, 4, fill, default");

		chckbxAlzacristalliElettrici = new JCheckBox("Alzacristalli elettrici");
		chckbxAlzacristalliElettrici.setToolTipText("Veicolo con alzacristalli elettrici");
		panel_21.add(chckbxAlzacristalliElettrici, "4, 4, fill, default");

		chckbxHandicap = new JCheckBox("Handicap");
		chckbxHandicap.setToolTipText("Veicolo adatto a portatori di handicap");
		panel_21.add(chckbxHandicap, "6, 4, fill, default");

		chckbxAirbag = new JCheckBox("Airbag");
		chckbxAirbag.setToolTipText("Veicolo con airbag");
		panel_21.add(chckbxAirbag, "2, 6, fill, default");

		chckbxClima = new JCheckBox("Clima");
		chckbxClima.setToolTipText("Veicolo con climatizzatore");
		panel_21.add(chckbxClima, "4, 6, fill, default");

		chckbxCerchiInLega = new JCheckBox("Cerchi in lega");
		chckbxCerchiInLega.setToolTipText("Veicolo con cerchi in lega");
		panel_21.add(chckbxCerchiInLega, "6, 6, fill, default");

		chckbxAntifurto = new JCheckBox("Antifurto");
		chckbxAntifurto.setToolTipText("Veicolo con antifurto");
		panel_21.add(chckbxAntifurto, "2, 8, fill, default");

		chckbxNavigatoreSatellitare = new JCheckBox("Navigatore satellitare");
		chckbxNavigatoreSatellitare.setToolTipText("Veicolo con navigatore satellitare");
		panel_21.add(chckbxNavigatoreSatellitare, "4, 8, fill, default");

		chckbxGancioTraino = new JCheckBox("Gancio traino");
		chckbxGancioTraino.setToolTipText("Veicolo con gancio traino");
		panel_21.add(chckbxGancioTraino, "6, 8, fill, default");

		chckbxChiusuraCentralizzata = new JCheckBox("Chiusura centralizzata");
		chckbxChiusuraCentralizzata.setToolTipText("Veicolo con chiusura centralizzata");
		panel_21.add(chckbxChiusuraCentralizzata, "2, 10, fill, default");

		chckbxRadiolettoreCd = new JCheckBox("Radio/Lettore CD");
		chckbxRadiolettoreCd.setToolTipText("Veicolo con radio o lettore CD");
		panel_21.add(chckbxRadiolettoreCd, "4, 10, fill, default");

		chckbxPortapacchi = new JCheckBox("Portapacchi");
		chckbxPortapacchi.setToolTipText("Veicolo con portapacchi");
		panel_21.add(chckbxPortapacchi, "6, 10, fill, default");

		chckbxContrAutomTrazione = new JCheckBox("Contr. autom. trazione");
		chckbxContrAutomTrazione.setToolTipText("Veicolo con controllo automatico della trazione");
		panel_21.add(chckbxContrAutomTrazione, "2, 12, fill, default");

		chckbxParkDistControl = new JCheckBox("Park dist. control");
		chckbxParkDistControl.setToolTipText("Veicolo con park distance control");
		panel_21.add(chckbxParkDistControl, "4, 12, fill, default");

		chckbxSediliSportivi = new JCheckBox("Sedili sportivi");
		chckbxSediliSportivi.setToolTipText("Veicolo con sedili sportivi");
		panel_21.add(chckbxSediliSportivi, "6, 12, fill, default");

		chckbxBauletto = new JCheckBox("Bauletto");
		chckbxBauletto.setToolTipText("Veicolo con bauletto");
		panel_21.add(chckbxBauletto, "6, 14, fill, default");

		chckbxAvviamentoAPedale = new JCheckBox("Avviamento a pedale");
		chckbxAvviamentoAPedale.setToolTipText("Veicolo con avviamento a pedale");
		panel_21.add(chckbxAvviamentoAPedale, "6, 16, fill, default");

		chckbxAvviamentoElettrico = new JCheckBox("Avviamento elettrico");
		chckbxAvviamentoElettrico.setToolTipText("Veicolo con avviamento elettrico");
		panel_21.add(chckbxAvviamentoElettrico, "6, 18, fill, default");

		chckbxEsp = new JCheckBox("ESP");
		chckbxEsp.setToolTipText("Veicolo con ESP");
		panel_21.add(chckbxEsp, "2, 14, fill, default");

		chckbxSediliRiscaldati = new JCheckBox("Sedili riscaldati");
		chckbxSediliRiscaldati.setToolTipText("Veicolo con sedili riscaldati");
		panel_21.add(chckbxSediliRiscaldati, "4, 14, fill, default");

		chckbxImmobilizer = new JCheckBox("Immobilizer");
		chckbxImmobilizer.setToolTipText("Veicolo con Immobilirez");
		panel_21.add(chckbxImmobilizer, "2, 16, fill, default");

		chckbxServosterzo = new JCheckBox("Servosterzo");
		chckbxServosterzo.setToolTipText("Veicolo con servo sterzo");
		panel_21.add(chckbxServosterzo, "4, 16, fill, default");

		chckbxFreniADisco = new JCheckBox("Freni a disco");
		chckbxFreniADisco.setToolTipText("Veicolo con freni a disco");
		panel_21.add(chckbxFreniADisco, "2, 18, fill, default");

		chckbxVolanteMultifunzione = new JCheckBox("Volante multifunzione");
		chckbxVolanteMultifunzione.setToolTipText("Veicolo con volante multifunzione");
		panel_21.add(chckbxVolanteMultifunzione, "4, 18, fill, default");

		chckbxCupolino = new JCheckBox("Cupolino");
		chckbxCupolino.setToolTipText("Veicolo con cupolino");
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

		JLabel lblNewLabel = new JLabel("Motore");
		panel_22.add(lblNewLabel, "2, 2");

		JLabel lblNewLabel_1 = new JLabel("Cambio");
		panel_22.add(lblNewLabel_1, "6, 2");

		JLabel lblNewLabel_2 = new JLabel("Numero rapporti");
		panel_22.add(lblNewLabel_2, "10, 2");

		comboBox_Motore = new JComboBox<String>();
		comboBox_Motore.setToolTipText("Selezione tipologia veicolo");
		comboBox_Motore.setModel(new DefaultComboBoxModel<String>(comboboxModelMotore));
		panel_22.add(comboBox_Motore, "2, 4, fill, default");

		comboBox_Cambio = new JComboBox<String>();
		comboBox_Cambio.setToolTipText("Selezione tipologia cambio veicolo");
		comboBox_Cambio.setModel(new DefaultComboBoxModel<String>(comboboxModelCambio));
		panel_22.add(comboBox_Cambio, "6, 4, fill, default");

		comboBox_NumeroRapporti = new JComboBox<String>();
		comboBox_NumeroRapporti.setToolTipText("Selezione numero rapporti");
		comboBox_NumeroRapporti.setModel(new DefaultComboBoxModel<String>(comboboxModelNumeroRapporti));
		panel_22.add(comboBox_NumeroRapporti, "10, 4, fill, default");

		JLabel lblCilindrata = new JLabel("Cilindrata");
		panel_22.add(lblCilindrata, "2, 6");

		JLabel lblClasseDiEmissione = new JLabel("Classe di emissione");
		panel_22.add(lblClasseDiEmissione, "6, 6");

		JLabel lblConsumoMedio = new JLabel("Consumo medio (l/100km)");
		panel_22.add(lblConsumoMedio, "10, 6");

		comboBox_Cilindrata = new JTextField();
		comboBox_Cilindrata.setToolTipText("Inserimento cilindrata veicolo");
		comboBox_Cilindrata.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo i tipo e numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || comboBox_Cilindrata.getText().length()>=maxCaratteri.get("comboBox_Cilindrata")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		panel_22.add(comboBox_Cilindrata, "2, 8, fill, default");

		comboBox_ClasseEmissioni = new JComboBox<String>();
		comboBox_ClasseEmissioni.setToolTipText("Selezione della classe di emissione del veicolo");
		comboBox_ClasseEmissioni.setModel(new DefaultComboBoxModel<String>(comboboxModelClasseEmissioni));
		panel_22.add(comboBox_ClasseEmissioni, "6, 8, fill, default");

		comboBox_ConsumoMedio = new JTextField();
		comboBox_ConsumoMedio.setToolTipText("Inserimento consumo medio veicolo");
		comboBox_ConsumoMedio.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo i tipo e numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD)) || comboBox_ConsumoMedio.getText().length()>=maxCaratteri.get("comboBox_ConsumoMedio")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		panel_22.add(comboBox_ConsumoMedio, "10, 8, fill, default");

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
				RowSpec.decode("50px"),
				RowSpec.decode("16dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		btnImmagine1 = new JButton("Immagine 1");
		btnImmagine1.setToolTipText("Inserimento immagine 1");
		btnImmagine1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine1, 1);
			}
		});
		panel_23.add(btnImmagine1, "2, 2");

		label_Immagine1 = new JLabel();
		label_Immagine1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine1, "4, 2, fill, fill");

		btnImmagine2 = new JButton("Immagine 2");
		btnImmagine2.setToolTipText("Inserimento immagine 2");
		btnImmagine2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine2, 2);
			}
		});
		panel_23.add(btnImmagine2, "8, 2");

		label_Immagine2 = new JLabel();
		label_Immagine2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine2, "10, 2, fill, fill");

		btnImmagine3 = new JButton("Immagine 3");
		btnImmagine3.setToolTipText("Inserimento immagine 3");
		btnImmagine3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine3, 3);
			}
		});
		panel_23.add(btnImmagine3, "2, 4");

		label_Immagine3 = new JLabel();
		label_Immagine3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine3, "4, 4, fill, fill");

		btnImmagine4 = new JButton("Immagine 4");
		btnImmagine4.setToolTipText("Inserimento immagine 4");
		btnImmagine4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine4, 4);
			}
		});
		panel_23.add(btnImmagine4, "8, 4");

		label_Immagine4 = new JLabel();
		label_Immagine4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine4, "10, 4, fill, fill");

		btnImmagine5 = new JButton("Immagine 5");
		btnImmagine5.setToolTipText("Inserimento immagine 5");
		btnImmagine5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine5, 5);
			}
		});
		panel_23.add(btnImmagine5, "2, 6");

		label_Immagine5 = new JLabel();
		label_Immagine5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine5, "4, 6, fill, fill");

		btnImmagine6 = new JButton("Immagine 6");
		btnImmagine6.setToolTipText("Inserimento immagine 6");
		btnImmagine6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine6, 6);
			}
		});
		panel_23.add(btnImmagine6, "8, 6");

		label_Immagine6 = new JLabel();
		label_Immagine6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine6, "10, 6, fill, fill");

		btnImmagine7 = new JButton("Immagine 7");
		btnImmagine7.setToolTipText("Inserimento immagine 7");
		btnImmagine7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine7, 7);
			}
		});
		panel_23.add(btnImmagine7, "2, 8");

		label_Immagine7 = new JLabel();
		label_Immagine7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine7, "4, 8, fill, fill");

		btnImmagine8 = new JButton("Immagine 8");
		btnImmagine8.setToolTipText("Inserimento immagine 8");
		btnImmagine8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine8, 8);
			}
		});
		panel_23.add(btnImmagine8, "8, 8");

		label_Immagine8 = new JLabel();
		label_Immagine8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine8, "10, 8, fill, fill");

		btnImmagine9 = new JButton("Immagine 9");
		btnImmagine9.setToolTipText("Inserimento immagine 9");
		btnImmagine9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine9, 9);
			}
		});
		panel_23.add(btnImmagine9, "2, 10");

		label_Immagine9 = new JLabel();
		label_Immagine9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine9, "4, 10, fill, fill");

		btnImmagine10 = new JButton("Immagine 10");
		btnImmagine10.setToolTipText("Inserimento immagine 10");
		btnImmagine10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine10, 10);
			}
		});
		panel_23.add(btnImmagine10, "8, 10");

		label_Immagine10 = new JLabel();
		label_Immagine10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine10, "10, 10, fill, fill");

		JLabel label_YouTubeUrl = new JLabel("YouTube video url");
		panel_23.add(label_YouTubeUrl, "2, 12");

		txtField_YouTubeUrl = new JTextField();
		txtField_YouTubeUrl.setToolTipText("Inserimento di una url per l'inclusione di un video da YouTube");
		panel_23.add(txtField_YouTubeUrl, "2, 14, fill, fill");
		txtField_YouTubeUrl.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (txtField_YouTubeUrl.getText().length()>=maxCaratteri.get("txtField_YouTubeUrl")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		txtField_YouTubeUrl.setColumns(10);


		JPanel panel_24 = new JPanel();
		panel_24.setBorder(new TitledBorder(null, "Descrizione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_24 = new GridBagConstraints();
		gbc_panel_24.insets = new Insets(0, 0, 5, 0);
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

		JLabel lblDescrizionemax = new JLabel("Descrizione (max 1000 caratteri)*");
		panel_24.add(lblDescrizionemax, "2, 2");		

		textPane_Descrizione = new JTextPane();
		textPane_Descrizione.setPreferredSize( new Dimension(400, 200 ));  
		textPane_Descrizione.setToolTipText("Inserimento di una descrizione testuale");
		textPane_Descrizione.setContentType("text/plain\r\ntext/xml\r\ntext/html");
		textPane_Descrizione.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (textPane_Descrizione.getText().length()>=maxCaratteri.get("textPane_Descrizione")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		panel_24.add(textPane_Descrizione, "2, 4, fill, fill");

		JPanel panel_33 = new JPanel();
		panel_33.setBorder(new TitledBorder(null, "Informazioni di contatto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_33 = new GridBagConstraints();
		gbc_panel_33.fill = GridBagConstraints.BOTH;
		gbc_panel_33.gridx = 0;
		gbc_panel_33.gridy = 5;
		panel_16.add(panel_33, gbc_panel_33);
		panel_33.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lbRagioneSociale = new JLabel("Ragione sociale*");
		panel_33.add(lbRagioneSociale, "2, 2");

		JLabel lblIndirizzo = new JLabel("Indirizzo*");
		panel_33.add(lblIndirizzo, "4, 2");

		JLabel lblTelefonoGenerico = new JLabel("Telefono*");
		panel_33.add(lblTelefonoGenerico, "6, 2");

		textFieldRagioneSociale = new JTextField();
		textFieldRagioneSociale.setToolTipText("Inserimento della ragione sociale dell'inserzionista");
		panel_33.add(textFieldRagioneSociale, "2, 4, fill, default");
		textFieldRagioneSociale.setColumns(10);
		textFieldRagioneSociale.setText(RAGIONESOCIALE_UTENTE);
		textFieldRagioneSociale.setEditable(false);

		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setToolTipText("Inserimento dell'indirizzo di riferimento dell'inserzionista ");
		panel_33.add(textFieldIndirizzo, "4, 4, fill, default");
		textFieldIndirizzo.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (textFieldIndirizzo.getText().length()>=maxCaratteri.get("textFieldIndirizzo")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldIndirizzo.setColumns(10);

		textFieldTelefonoGenerico = new JTextField();
		textFieldTelefonoGenerico.setToolTipText("Inserimento di un recapito telefonico dell'inserzionista");
		panel_33.add(textFieldTelefonoGenerico, "6, 4, fill, default");
		textFieldTelefonoGenerico.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero dei caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (textFieldTelefonoGenerico.getText().length()>=maxCaratteri.get("textFieldTelefonoGenerico")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldTelefonoGenerico.setColumns(10);

		JLabel lblReferente = new JLabel("Referente*");
		panel_33.add(lblReferente, "2, 6");

		JLabel lblTelefonoReferente = new JLabel("Telefono*");
		panel_33.add(lblTelefonoReferente, "4, 6");

		JLabel lblEmailReferente = new JLabel("E-mail*");
		panel_33.add(lblEmailReferente, "6, 6");

		textFieldReferente = new JTextField();
		textFieldReferente.setToolTipText("Inserimento del nome del referente");
		panel_33.add(textFieldReferente, "2, 8, fill, default");
		textFieldReferente.setColumns(10);
		textFieldReferente.setText(UTENTE);
		textFieldReferente.setEditable(false);

		textFieldTelefonoReferente = new JTextField();
		textFieldTelefonoReferente.setToolTipText("Inserimento del numero di telefono del referente");
		panel_33.add(textFieldTelefonoReferente, "4, 8, fill, default");
		textFieldTelefonoReferente.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (textFieldTelefonoReferente.getText().length()>=maxCaratteri.get("textFieldTelefonoReferente")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldTelefonoReferente.setColumns(10);

		textFieldEmailReferente = new JTextField();
		textFieldEmailReferente.setToolTipText("Inserimento della mail del referente");
		panel_33.add(textFieldEmailReferente, "6, 8, fill, default");
		textFieldEmailReferente.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (textFieldEmailReferente.getText().length()>=maxCaratteri.get("textFieldEmailReferente")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldEmailReferente.setColumns(10);

		JPanel panel_17 = new JPanel();
		panel_17.setBorder(null);
		GridBagConstraints gbc_panel_17 = new GridBagConstraints();
		gbc_panel_17.fill = GridBagConstraints.BOTH;
		gbc_panel_17.gridx = 0;
		gbc_panel_17.gridy = 1;
		panel_11.add(panel_17, gbc_panel_17);
		panel_17.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_18 = new JPanel();
		panel_17.add(panel_18);



		//Pulsante per resettare la form veicolo
		JButton btnResetta = new JButton("Reset form");
		btnResetta.setToolTipText("Resetta i contenuti del modulo di inserimento scheda veicolo");
		btnResetta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Resetta la form
				resettaForm(listCampiFormVeicolo);

				//Forza la selezione autoveicolo
				selezioneAutoVeicolo();
				getRdbtnAutoveicolo().setSelected(true);
			}
		});
		btnResetta.setIcon(new ImageIcon(".\\images\\refresh.png"));
		panel_18.add(btnResetta);

		JPanel panel_19 = new JPanel();
		panel_17.add(panel_19);


		//Pulsante per creare la scheda veicolo
		final JButton btnInserisci = new JButton("Crea scheda");
		btnInserisci.setToolTipText("Crea una scheda veicolo utilizzando i dati inseriti nel modulo di inserimento veicolo");
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				System.out.print("Creazione della scheda veicolo...");				
				if(isFormValid(listCampiFormVeicoloObbligatori)) { 
					System.out.println(" ...form valido... ");

					//Disabilito i campi della form
					disabilitaCampiForm(listCampiFormVeicolo);

					//Istanzio l'oggetto scheda e lo salvo nel file
					SchedaVeicolo schedaVeicolo = new SchedaVeicolo();
					aggiungiSchedaVeicolo(schedaVeicolo);

					//Il pannello centrale viene ridisegnato             	   	
					aggiornaPannelloListaSchedeVeicolo();        	   

					System.out.print(" fatto." + "\n");
				}
				else {
					System.out.println(" ...form non valido. Scheda non creata.");
					JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("creazioneDellaSchedaVeicolo"), "Form non valido", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnInserisci.setIcon(new ImageIcon(".\\images\\forward.png"));
		panel_19.add(btnInserisci);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 0;
		panel_3.add(scrollPane_1, gbc_scrollPane_1);


		//Il panel_9 è il pannello che contiene la lista delle schede create
		panel_9 = new JPanel();
		panel_9.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_9.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				//Ascolto l'inserimento di un component nel pannello e lo aggiorno di conseguenza
				panel_9.updateUI();	
			}
		});
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista schede veicolo inserite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setViewportView(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));


		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 0;
		panel_3.add(scrollPane_2, gbc_scrollPane_2);


		//Il panel_10 ospita i portali web di sincronizzazione
		panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sincronizzazione veicoli nei portali Web", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_2.setViewportView(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.getVerticalScrollBar().setUnitIncrement(2);
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		panel.add(scrollPane_3, gbc_scrollPane_3);

		panel_4 = new JPanel();
		panel_4.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				//Ascolto l'inserimento di un component nel pannello e lo aggiorno di conseguenza
				panel_4.updateUI();	
			}
		});
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Clienti potenzialmente interessati al veicolo selezionato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_3.setViewportView(panel_4);
		panel_4.setBackground(new Color(255, 255, 224));		

		JPanel panelNessunaScedaSelezionata = new JPanel();
		JLabel lblNessunaScedaSelezionata = new JLabel("Non è stata selezionata alcuna scheda veicolo");                
		panelNessunaScedaSelezionata.add(lblNessunaScedaSelezionata);
		panel_4.add(panelNessunaScedaSelezionata);
		//Fine prima tab




















		//Seconda tab
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		tabbedPane.addTab("Anagrafica cliente", new ImageIcon(".\\images\\icon_pilot.png"), panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {437, 0};
		gbl_panel_1.rowHeights = new int[] {170, 70, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{4.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(null);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_1.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] {740, 370};
		gbl_panel_5.rowHeights = new int[]{1, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_5.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);

		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.insets = new Insets(0, 0, 0, 5);
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 0;
		panel_5.add(panel_12, gbc_panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{500, 0};
		gbl_panel_12.rowHeights = new int[]{237, 30, 0};
		gbl_panel_12.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);

		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_4.gridx = 0;
		gbc_scrollPane_4.gridy = 0;
		panel_12.add(scrollPane_4, gbc_scrollPane_4);

		JPanel panel_25 = new JPanel();
		scrollPane_4.setViewportView(panel_25);
		GridBagLayout gbl_panel_25 = new GridBagLayout();
		gbl_panel_25.columnWidths = new int[]{0, 0};
		gbl_panel_25.rowHeights = new int[]{0, 0, 0};
		gbl_panel_25.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_25.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_25.setLayout(gbl_panel_25);

		JPanel panel_29 = new JPanel();
		panel_29.setBorder(new TitledBorder(null, "Dati cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_29 = new GridBagConstraints();
		gbc_panel_29.insets = new Insets(0, 0, 5, 0);
		gbc_panel_29.fill = GridBagConstraints.BOTH;
		gbc_panel_29.gridx = 0;
		gbc_panel_29.gridy = 0;
		panel_25.add(panel_29, gbc_panel_29);
		panel_29.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
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
				FormFactory.DEFAULT_ROWSPEC,}));

		formCliente_rdbtnSignore = new JRadioButton("Sig.");
		formCliente_rdbtnSignore.setToolTipText("Selezione titolo cliente");
		formCliente_rdbtnSignore.setSelected(true);
		buttonGroup_1.add(formCliente_rdbtnSignore);
		panel_29.add(formCliente_rdbtnSignore, "2, 2");

		formCliente_rdbtnSignora = new JRadioButton("Sig.ra");
		formCliente_rdbtnSignora.setToolTipText("Selezione titolo cliente");
		buttonGroup_1.add(formCliente_rdbtnSignora);
		panel_29.add(formCliente_rdbtnSignora, "4, 2");

		JLabel lblNome = new JLabel("Nome*");
		panel_29.add(lblNome, "2, 4");

		JLabel lblCognome = new JLabel("Cognome*");
		panel_29.add(lblCognome, "4, 4");

		formCliente_textFieldNome = new JTextField();
		formCliente_textFieldNome.setToolTipText("Inserire il nome del cliente");
		panel_29.add(formCliente_textFieldNome, "2, 6, fill, default");
		formCliente_textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldNome.getText().length()>=maxCaratteri.get("formCliente_textFieldNome")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldNome.setColumns(10);

		formCliente_textFieldCognome = new JTextField();
		formCliente_textFieldCognome.setToolTipText("Inserire il cognome del cliente");
		panel_29.add(formCliente_textFieldCognome, "4, 6, fill, default");
		formCliente_textFieldCognome.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldCognome.getText().length()>=maxCaratteri.get("formCliente_textFieldCognome")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldCognome.setColumns(10);

		JLabel lblEmail = new JLabel("Email*");
		panel_29.add(lblEmail, "2, 8");

		JLabel lblTelefono = new JLabel("Telefono 1*");
		panel_29.add(lblTelefono, "4, 8, fill, default");

		JLabel lblTelefono_1 = new JLabel("Telefono 2");
		panel_29.add(lblTelefono_1, "6, 8");

		formCliente_textFieldEmail = new JTextField();
		formCliente_textFieldEmail.setToolTipText("Inserire un recapito e-mail del cliente");
		panel_29.add(formCliente_textFieldEmail, "2, 10, fill, default");
		formCliente_textFieldEmail.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldEmail.getText().length()>=maxCaratteri.get("formCliente_textFieldEmail")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldEmail.setColumns(10);

		formCliente_textFieldTelefono1 = new JTextField();
		formCliente_textFieldTelefono1.setToolTipText("Inserire un recapito telefonico del cliente");
		panel_29.add(formCliente_textFieldTelefono1, "4, 10, fill, default");
		formCliente_textFieldTelefono1.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldTelefono1.getText().length()>=maxCaratteri.get("formCliente_textFieldTelefono1")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldTelefono1.setColumns(10);

		formCliente_textFieldTelefono2 = new JTextField();
		formCliente_textFieldTelefono2.setToolTipText("Inserire un recapito telefonico del cliente");
		panel_29.add(formCliente_textFieldTelefono2, "6, 10, fill, default");
		formCliente_textFieldTelefono2.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldTelefono2.getText().length()>=maxCaratteri.get("formCliente_textFieldTelefono2")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldTelefono2.setColumns(10);

		JLabel lblVia = new JLabel("Via/Piazza*");
		panel_29.add(lblVia, "2, 12");

		JLabel lblNumero = new JLabel("Numero*");
		panel_29.add(lblNumero, "4, 12");

		formCliente_textFieldVia = new JTextField();
		formCliente_textFieldVia.setToolTipText("Inserire la via del cliente");
		panel_29.add(formCliente_textFieldVia, "2, 14, fill, default");
		formCliente_textFieldVia.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldVia.getText().length()>=maxCaratteri.get("formCliente_textFieldVia")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldVia.setColumns(10);

		formCliente_textFieldNumeroCivico = new JTextField();
		formCliente_textFieldNumeroCivico.setToolTipText("Inserire il numero civico del cliente");
		panel_29.add(formCliente_textFieldNumeroCivico, "4, 14, fill, default");
		formCliente_textFieldNumeroCivico.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldNumeroCivico.getText().length()>=maxCaratteri.get("formCliente_textFieldNumeroCivico")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldNumeroCivico.setColumns(10);

		JLabel lblCap = new JLabel("CAP*");
		panel_29.add(lblCap, "2, 16");

		JLabel lblCitt = new JLabel("Città*");
		panel_29.add(lblCitt, "4, 16");

		formCliente_textFieldCAP = new JTextField();
		formCliente_textFieldCAP.setToolTipText("Inserire il CAP del cliente");
		panel_29.add(formCliente_textFieldCAP, "2, 18, fill, default");
		formCliente_textFieldCAP.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il tipo e il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || formCliente_textFieldCAP.getText().length()>=maxCaratteri.get("formCliente_textFieldCAP")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldCAP.setColumns(10);

		formCliente_textFieldCitta = new JTextField();
		formCliente_textFieldCitta.setToolTipText("Inserire la città del cliente");
		panel_29.add(formCliente_textFieldCitta, "4, 18, fill, default");
		formCliente_textFieldCitta.addKeyListener(new KeyAdapter() {
			@Override
			//Controllo il numero di caratteri immessi
			public void keyTyped(KeyEvent e) {
				if (formCliente_textFieldCitta.getText().length()>=maxCaratteri.get("formCliente_textFieldCitta")) {
					e.getComponent().getToolkit().beep();
					e.consume();
				}
			}
		});
		formCliente_textFieldCitta.setColumns(10);

		JPanel panel_30 = new JPanel();
		panel_30.setBorder(new TitledBorder(null, "Automobile ricercata", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_30 = new GridBagConstraints();
		gbc_panel_30.fill = GridBagConstraints.BOTH;
		gbc_panel_30.gridx = 0;
		gbc_panel_30.gridy = 1;
		panel_25.add(panel_30, gbc_panel_30);
		panel_30.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
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
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblMarca_1 = new JLabel("Marca");
		panel_30.add(lblMarca_1, "2, 2");

		JLabel lblModello_1 = new JLabel("Modello");
		panel_30.add(lblModello_1, "4, 2");

		JLabel lblVersione_1 = new JLabel("Versione");
		panel_30.add(lblVersione_1, "6, 2");

		comboBox_Marca_Cliente = new JComboBox<String>();
		comboBox_Marca_Cliente.setToolTipText("Selezionare la marca dell'automobile ricercata");
		comboBox_Marca_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				//Non fare nulla se l'azione non è dell'utente
				if (nonUserSelection) {return;}
				
				System.out.println("Marca veicolo cliente");
				//Selezionando un marca veicolo popolo la combobox Modello veicolo
				if (arg0.getStateChange() == ItemEvent.SELECTED) {

					String marcaVeicolo = (String) comboBox_Marca_Cliente.getSelectedItem();

					if(!marcaVeicolo.equals("Seleziona")) {

						JComboBox<String> comboboxModello =  formCliente_getModelloVeicolo();
						JComboBox<String> comboboxVersione = formCliente_getVersioneVeicolo();

						popolaModelloVeicolo(marcaVeicolo, comboboxModello, comboboxVersione);
					}	

				}	

			}
		});
		comboBox_Marca_Cliente.setModel(new DefaultComboBoxModel<String>(marcheAutoveicoli));
		panel_30.add(comboBox_Marca_Cliente, "2, 4, fill, default");

		comboBox_Modello_Cliente = new JComboBox<String>();
		comboBox_Modello_Cliente.setToolTipText("Selezionare il modello dell'automobile ricercata");
		comboBox_Modello_Cliente.setEditable(true);
		comboBox_Modello_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				//Non fare nulla se l'azione non è dell'utente
				if (nonUserSelection) {return;}
				
				System.out.println("Modello veicolo cliente");

				//Selezionando un modello veicolo popolo la combobox Versione veicolo
				if (arg0.getStateChange() == ItemEvent.SELECTED) {

					String marcaVeicolo = (String) comboBox_Marca_Cliente.getSelectedItem();
					String modelloVeicolo = (String) comboBox_Modello_Cliente.getSelectedItem();
					JComboBox<String> comboboxVersione = formCliente_getVersioneVeicolo();

					if(modelloVeicolo!=null && !modelloVeicolo.equals("Seleziona")) {
						popolaVersioneVeicolo(marcaVeicolo, modelloVeicolo, comboboxVersione);
					}								
				}
			}
		});
		panel_30.add(comboBox_Modello_Cliente, "4, 4, fill, default");

		comboBox_Versione_Cliente = new JComboBox<String>();
		comboBox_Versione_Cliente.setToolTipText("Selezionare la versione dell'automobile ricercata");
		comboBox_Versione_Cliente.setEditable(true);
		comboBox_Versione_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//
			}
		});
		panel_30.add(comboBox_Versione_Cliente, "6, 4, fill, default");

		comboBox_TipologiaCarburante_Cliente = new JComboBox<String>();
		comboBox_TipologiaCarburante_Cliente.setToolTipText("Selezionare la tipologia di carburante dell'automobile ricercata");
		comboBox_TipologiaCarburante_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});

		JLabel lblTipologiaCarburante = new JLabel("Tipologia carburante");
		panel_30.add(lblTipologiaCarburante, "2, 6");

		JLabel lblColore = new JLabel("Colore");
		panel_30.add(lblColore, "4, 6");

		JLabel lblTipologia_1 = new JLabel("Tipologia");
		panel_30.add(lblTipologia_1, "6, 6");
		comboBox_TipologiaCarburante_Cliente.setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));
		panel_30.add(comboBox_TipologiaCarburante_Cliente, "2, 8, fill, default");

		comboBox_Colore_Cliente = new JComboBox<String>();
		comboBox_Colore_Cliente.setToolTipText("Selezionare il colore dell'automobile ricercata");
		comboBox_Colore_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		comboBox_Colore_Cliente.setModel(new DefaultComboBoxModel<String>(comboboxModelColoreEsterno));
		panel_30.add(comboBox_Colore_Cliente, "4, 8, fill, default");

		comboBoxTipologia_Cliente = new JComboBox<String>();
		comboBoxTipologia_Cliente.setToolTipText("Selezionare la tipologia dell'automobile ricercata");
		comboBoxTipologia_Cliente.setModel(new DefaultComboBoxModel<String>(tipologiaAutoveicoli));
		panel_30.add(comboBoxTipologia_Cliente, "6, 8, fill, default");

		JPanel panel_26 = new JPanel();
		GridBagConstraints gbc_panel_26 = new GridBagConstraints();
		gbc_panel_26.fill = GridBagConstraints.BOTH;
		gbc_panel_26.gridx = 0;
		gbc_panel_26.gridy = 1;
		panel_12.add(panel_26, gbc_panel_26);
		panel_26.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_27 = new JPanel();
		panel_26.add(panel_27);



		//Pulsante reset form cliente
		JButton btnResetFormCliente = new JButton("Reset form");
		btnResetFormCliente.setToolTipText("Resetta la form di creazione scheda cliente");
		btnResetFormCliente.setIcon(new ImageIcon(".\\images\\refresh.png"));
		btnResetFormCliente.setSelectedIcon(null);
		btnResetFormCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Resetta la form
				resettaForm(listCampiFormCliente);
			}
		});
		panel_27.add(btnResetFormCliente);

		JPanel panel_28 = new JPanel();
		panel_26.add(panel_28);



		//Pulsante di creazione della scheda cliente
		JButton btnCreaSchedaCliente = new JButton("Crea scheda");
		btnCreaSchedaCliente.setToolTipText("Crea una nuova scheda cliente");
		btnCreaSchedaCliente.setIcon(new ImageIcon(".\\images\\forward.png"));
		btnCreaSchedaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("Creazione della scheda cliente...");				
				if(isFormValid(listCampiFormClienteObbligatori)) { 
					System.out.println(" ...form cliente valido... ");

					//Disabilito i campi della form
					disabilitaCampiForm(listCampiFormCliente);

					//Istanzio l'oggetto scheda e lo salvo nel file
					SchedaCliente schedaCliente = new SchedaCliente();
					aggiungiSchedaCliente(schedaCliente);

					//Il pannello di destra viene ridisegnato             	   	
					aggiornaPannelloListaSchedeCliente();      	

					System.out.print(" fatto." + "\n");
				}
				else {
					System.out.println(" ...form non valido. Scheda cliente non creata.");
					JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("creazioneDellaSchedaCliente"), "FormNonValido", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_28.add(btnCreaSchedaCliente);

		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridx = 1;
		gbc_scrollPane_5.gridy = 0;
		panel_5.add(scrollPane_5, gbc_scrollPane_5);

		panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista schede cliente inserite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				//Ascolto l'inserimento di un component nel pannello e lo aggiorno di conseguenza
				panel_13.updateUI();	
			}
		});
		scrollPane_5.setViewportView(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.Y_AXIS));

		JScrollPane scrollPane_6 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridx = 0;
		gbc_scrollPane_6.gridy = 1;
		panel_1.add(scrollPane_6, gbc_scrollPane_6);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Veicoli potenzialmente interessanti per il cliente selezionato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_6.setViewportView(panel_6);
		panel_6.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				//Ascolto l'inserimento di un component nel pannello e lo aggiorno di conseguenza
				panel_6.updateUI();	
			}
		});
		panel_6.setBackground(new Color(255, 255, 224));
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelNessunaScedaSelezionata2 = new JPanel();
		JLabel lblNessunaScedaSelezionata2 = new JLabel("Non è stata selezionata alcuna scheda cliente");                
		panelNessunaScedaSelezionata2.add(lblNessunaScedaSelezionata2);
		panel_6.add(panelNessunaScedaSelezionata2);
		//Fine seconda tab


















		//Terza tab
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		tabbedPane.addTab("Incrocio MLS", new ImageIcon(".\\images\\icon_db.png"), panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {437, 0};
		gbl_panel_2.rowHeights = new int[] {170, 70, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{4.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(null);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		panel_2.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] {555, 555, 0};
		gbl_panel_7.rowHeights = new int[]{170, 0};
		gbl_panel_7.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{4.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);

		JPanel panel_14 = new JPanel();
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.insets = new Insets(0, 0, 0, 5);
		gbc_panel_14.fill = GridBagConstraints.BOTH;
		gbc_panel_14.gridx = 0;
		gbc_panel_14.gridy = 0;
		panel_7.add(panel_14, gbc_panel_14);
		GridBagLayout gbl_panel_14 = new GridBagLayout();
		gbl_panel_14.columnWidths = new int[]{500, 0};
		gbl_panel_14.rowHeights = new int[] {237, 30, 0};
		gbl_panel_14.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_14.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_14.setLayout(gbl_panel_14);

		JScrollPane scrollPane_7 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_7 = new GridBagConstraints();
		gbc_scrollPane_7.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_7.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_7.gridx = 0;
		gbc_scrollPane_7.gridy = 0;
		panel_14.add(scrollPane_7, gbc_scrollPane_7);

		panel_31 = new JPanel();
		panel_31.setBorder(new TitledBorder(null, "Lista schede cliente inserite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_7.setViewportView(panel_31);
		panel_31.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				//Ascolto l'inserimento di un component nel pannello e lo aggiorno di conseguenza
				panel_31.updateUI();	
			}
		});
		panel_31.setLayout(new BoxLayout(panel_31, BoxLayout.Y_AXIS));

		JPanel panel_32 = new JPanel();
		GridBagConstraints gbc_panel_32 = new GridBagConstraints();
		gbc_panel_32.fill = GridBagConstraints.BOTH;
		gbc_panel_32.gridx = 0;
		gbc_panel_32.gridy = 1;
		panel_14.add(panel_32, gbc_panel_32);

		JButton btnAggiornaRisultati = new JButton("Sincronizza risultati");
		btnAggiornaRisultati.setIcon(new ImageIcon(".\\images\\update.png"));
		panel_32.add(btnAggiornaRisultati);

		JScrollPane scrollPane_8 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_8 = new GridBagConstraints();
		gbc_scrollPane_8.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_8.gridx = 1;
		gbc_scrollPane_8.gridy = 0;
		panel_7.add(scrollPane_8, gbc_scrollPane_8);

		panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estrazione Multi Level Sharing per il cliente selezionato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_8.setViewportView(panel_15);
		panel_15.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				//Ascolto l'inserimento di un component nel pannello e lo aggiorno di conseguenza
				panel_15.updateUI();	
			}
		});
		panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.Y_AXIS));

		JScrollPane scrollPane_9 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_9 = new GridBagConstraints();
		gbc_scrollPane_9.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_9.gridx = 0;
		gbc_scrollPane_9.gridy = 1;
		panel_2.add(scrollPane_9, gbc_scrollPane_9);

		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dettagli scheda veicolo selezionata", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				//Ascolto l'inserimento di un component nel pannello e lo aggiorno di conseguenza
				panel_8.updateUI();	
			}
		});
		scrollPane_9.setViewportView(panel_8);
		panel_8.setBackground(new Color(255, 255, 224));

		JPanel panelNessunaScedaSelezionata3 = new JPanel();
		JLabel lblNessunaScedaSelezionata3 = new JLabel("Non è stata selezionata alcuna scheda veicolo");                
		panelNessunaScedaSelezionata3.add(lblNessunaScedaSelezionata3);
		panel_8.add(panelNessunaScedaSelezionata3);
		//Fine terza tab









		//Menu top window
		JMenuBar menuBar = new JMenuBar();
		imagination_05.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnTest = new JMenu("Menu1");
		menuBar.add(mnTest);

		JMenuItem mntmTesting = new JMenuItem("MenuItem1");
		mnTest.add(mntmTesting);

		JMenuItem mntmNewMenuItem = new JMenuItem("MenuItem2");
		mnTest.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("MenuItem3");
		mnTest.add(mntmNewMenuItem_1);

		JMenu mnNewMenu = new JMenu("Menu2");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("MenuItem1");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("MenuItem2");
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("MenuItem3");
		mnNewMenu.add(mntmNewMenuItem_4);
		//Fine menu top window

	}





	//Lista dei metodi specifici della GUI

	//Metodo per aggiornare la UI del pannello schede veicolo
	protected static void aggiornaPannelloListaSchedeVeicolo() {

		JPanel pannelloListaSchedeVeicolo = getPanel_9();

		pannelloListaSchedeVeicolo.removeAll();

		pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(6));

		if(listSchedeVeicolo.isEmpty()) {
			//Pannello senza schede
			System.out.println("...la lista delle schede veicolo è vuota...");
			JPanel panelNessunaScheda = new JPanel();
			JLabel lblNessunaScheda = new JLabel("La lista delle schede veicolo è vuota.");                
			panelNessunaScheda.add(lblNessunaScheda);
			pannelloListaSchedeVeicolo.add(panelNessunaScheda);
		}    	
		else {
			//Pannello con schede
			System.out.println("...la lista delle schede veicolo contiene delle schede...");
			ListIterator<SchedaVeicolo> iterator = listSchedeVeicolo.listIterator();
			while(iterator.hasNext()) {
				SchedaVeicolo schedaCorrente = iterator.next();
				//I sottopannelli sono istanze di una classe comune definita più sotto
				JPanel pannelloSchedaVeicolo = new PanelSchedaVeicolo(schedaCorrente, listSchedeVeicolo, radioGrpSchede);            
				pannelloListaSchedeVeicolo.add(pannelloSchedaVeicolo);
				pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(5));
			}
		}

	}


	//Metodo per aggiornare la UI del pannello schede cliente
	protected static void aggiornaPannelloListaSchedeCliente() {

		JPanel pannelloListaSchedeCliente = getPanel_13();
		JPanel pannelloListaSchedeClienteMLS = getPanel_31();
		JPanel pannelloListaSchedeVeicoloMLS = getPanel_15();

		pannelloListaSchedeCliente.removeAll();
		pannelloListaSchedeClienteMLS.removeAll();

		pannelloListaSchedeCliente.add(Box.createVerticalStrut(6));
		pannelloListaSchedeClienteMLS.add(Box.createVerticalStrut(6));

		if(listSchedeCliente.isEmpty()) {
			//Pannello senza schede
			System.out.println("...la lista delle schede cliente è vuota...");

			JPanel panelNessunaScheda = new JPanel();
			JLabel lblNessunaScheda = new JLabel("La lista delle schede cliente è vuota.");                
			panelNessunaScheda.add(lblNessunaScheda);

			JPanel panelNessunaSchedaClienteMLS = new JPanel();
			JLabel lblNessunaSchedaClienteMLS = new JLabel("La lista delle schede cliente è vuota.");                
			panelNessunaSchedaClienteMLS.add(lblNessunaSchedaClienteMLS);

			JPanel panelNessunaSchedaVeicoloMLS = new JPanel();
			JLabel lblNessunaSchedaVeicoloMLS = new JLabel("Non ci sono veicoli che soddisfano le richieste del cliente selezionato.");                
			panelNessunaSchedaVeicoloMLS.add(lblNessunaSchedaVeicoloMLS);

			pannelloListaSchedeCliente.add(panelNessunaScheda);
			pannelloListaSchedeClienteMLS.add(panelNessunaSchedaClienteMLS);
			pannelloListaSchedeVeicoloMLS.add(panelNessunaSchedaVeicoloMLS);
		}    	
		else {
			//Pannello con schede
			System.out.println("...la lista delle schede cliente contiene delle schede...");
			ListIterator<SchedaCliente> iterator = listSchedeCliente.listIterator();
			while(iterator.hasNext()) {
				SchedaCliente schedaCorrente = iterator.next();

				//I sottopannelli sono istanze di una classe comune definita più sotto
				JPanel pannelloSchedaCliente = new PanelSchedaCliente(schedaCorrente, listSchedeCliente, radioGrpSchede);    
				JPanel pannelloSchedaClienteMLS = new PanelSchedaClienteMLS(schedaCorrente, listSchedeCliente, radioGrpSchede);  

				pannelloListaSchedeCliente.add(pannelloSchedaCliente);
				pannelloListaSchedeCliente.add(Box.createVerticalStrut(5));

				pannelloListaSchedeClienteMLS.add(pannelloSchedaClienteMLS);
				pannelloListaSchedeClienteMLS.add(Box.createVerticalStrut(5));
			}
		}

	}


	//Metodo per disabilitare i campi della form di creazione scheda (cliente o veicolo sono determinati dal parametro attuale)
	protected static void disabilitaCampiForm(LinkedList<JComponent> listCampiForm) {
		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = iteratorListCampiForm.next();
			campoCorrente.setEnabled(false);
		}	

	}


	//Metodo per impostare la form di creazione scheda nella modalità autoveicolo
	private void selezioneAutoVeicolo() {

		//Modifico le opzioni della combobox Marca con le opzioni per i autoveicoli
		JComboBox<String> comboBoxMarca = getComboBox_Marca();
		comboBoxMarca.removeAllItems();
		comboBoxMarca.setModel(new DefaultComboBoxModel<String>(marcheAutoveicoli));

		//Svuoto la combobox Modello
		JComboBox<String> comboBoxModello = getComboBox_Modello();
		comboBoxModello.removeAllItems();

		//Attivo le combobox che servono nel caso di autoveicolo
		JComboBox<String> comboBoxCarrozzeria = getComboBox_Carrozzeria();
		comboBoxCarrozzeria.setEnabled(true);		
		JComboBox<String> comboBoxPostiASedere = getComboBox_PostiASedere();
		comboBoxPostiASedere.setEnabled(true);
		JComboBox<String> comboBoxFinitureInterni = getComboBox_FinitureInterni();
		comboBoxFinitureInterni.setEnabled(true);
		JComboBox<String> comboBoxColoreInterni = getComboBox_ColoreInterni();
		comboBoxColoreInterni.setEnabled(true);
		JComboBox<String> comboBoxMotore = getComboBox_Motore();
		comboBoxMotore.setEnabled(true);

		//Disattivo le checkbox che non servono nel caso di autoveicolo
		JCheckBox chckbxHandicap = getChckbxHandicap();
		chckbxHandicap.setEnabled(true);
		JCheckBox chckbxServosterzo = getChckbxServosterzo();
		chckbxServosterzo.setEnabled(true);
		JCheckBox chckbxSediliSportivi = getChckbxSediliSportivi();
		chckbxSediliSportivi.setEnabled(true);
		JCheckBox chckbxParkDistControl = getChckbxParkDistControl();
		chckbxParkDistControl.setEnabled(true);
		JCheckBox chckbxFreniADisco = getChckbxFreniADisco();
		chckbxFreniADisco.setEnabled(true);
		JCheckBox chckbxRadiolettoreCd = getChckbxRadiolettoreCd();
		chckbxRadiolettoreCd.setEnabled(true);
		JCheckBox chckbxAbs = getChckbxAbs();
		chckbxAbs.setEnabled(true);
		JCheckBox chckbxGancioTraino = getChckbxGancioTraino();
		chckbxGancioTraino.setEnabled(true);
		JCheckBox chckbxVolanteMultifunzione = getChckbxVolanteMultifunzione();
		chckbxVolanteMultifunzione.setEnabled(true);
		JCheckBox chckbxImmobilizer = getChckbxImmobilizer();
		chckbxImmobilizer.setEnabled(true);
		JCheckBox chckbxPortapacchi = getChckbxPortapacchi();
		chckbxPortapacchi.setEnabled(true);
		JCheckBox chckbxAirbag = getChckbxAirbag();
		chckbxAirbag.setEnabled(true);
		JCheckBox chckbxEsp = getChckbxEsp();
		chckbxEsp.setEnabled(true);
		JCheckBox chckbxAlzacristalliElettrici = getChckbxAlzacristalliElettrici();
		chckbxAlzacristalliElettrici.setEnabled(true);
		JCheckBox chckbxNavigatoreSatellitare = getChckbxNavigatoreSatellitare();
		chckbxNavigatoreSatellitare.setEnabled(true);
		JCheckBox chckbxCerchiInLega = getChckbxCerchiInLega();
		chckbxCerchiInLega.setEnabled(true);
		JCheckBox chckbxContrAutomTrazione = getChckbxContrAutomTrazione();
		chckbxContrAutomTrazione.setEnabled(true);
		JCheckBox chckbxChiusuraCentralizzata = getChckbxChiusuraCentralizzata();
		chckbxChiusuraCentralizzata.setEnabled(true);
		JCheckBox chckbxSediliRiscaldati = getChckbxSediliRiscaldati();
		chckbxSediliRiscaldati.setEnabled(true);
		JCheckBox chckbxClima = getChckbxClima();
		chckbxClima.setEnabled(true);
		JCheckBox chckbxAntifurto = getChckbxAntifurto();
		chckbxAntifurto.setEnabled(true);
		JCheckBox chckbxCupolino = getChckbxCupolino();
		chckbxCupolino.setEnabled(false);
		JCheckBox chckbxBauletto = getChckbxBauletto();
		chckbxBauletto.setEnabled(false);
		JCheckBox chckbxAvviamentoAPedale = getChckbxAvviamentoAPedale();
		chckbxAvviamentoAPedale.setEnabled(false);
		JCheckBox chckbxAvviamentoElettrico = getChckbxAvviamentoElettrico();
		chckbxAvviamentoElettrico.setEnabled(false);

		//Modifico le opzioni della combobox Carburante con le opzioni per gli autoveicoli
		JComboBox<String> comboBoxCarburante = getComboBox_Carburante();
		comboBoxCarburante.removeAllItems();
		comboBoxCarburante.setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));

		//Modifico le opzioni della combobox Tipologia con le opzioni per gli autoveicoli
		JComboBox<String> comboBoxTipologia = getComboBox_Tipologia();
		comboBoxTipologia.removeAllItems();
		comboBoxTipologia.setModel(new DefaultComboBoxModel<String>(tipologiaAutoveicoli));

	}


	//Metodo per impostare la form di creazione scheda nella modalità moto/scooter
	private void selezioneMotoScooter() {

		//Modifico le opzioni della combobox Marca con le opzioni per i motoveicoli
		JComboBox<String> comboBoxMarca = getComboBox_Marca();
		comboBoxMarca.removeAllItems();
		comboBoxMarca.setModel(new DefaultComboBoxModel<String>(marcheMotoveicoli));

		//Svuoto la combobox Modello
		JComboBox<String> comboBoxModello = getComboBox_Modello();
		comboBoxModello.removeAllItems();
		comboBoxModello.addItem("Inserire nome modello");

		//Svuoto la combobox Versione
		JComboBox<String> comboBoxVersione = getComboBox_Versione();
		comboBoxVersione.removeAllItems();
		comboBoxVersione.addItem("Inserire la versione");

		//Resetto la combobox Motore
		JComboBox<String> comboBoxMotore = getComboBox_Motore();
		comboBoxMotore.setSelectedIndex(0);

		//Resetto la combobox Cambio
		JComboBox<String> comboBoxCambio = getComboBox_Cambio();
		comboBoxCambio.setSelectedIndex(0);

		//Resetto la combobox Cambio
		JComboBox<String> comboBoxPostiASedere = getComboBox_PostiASedere();
		comboBoxPostiASedere.setSelectedIndex(0);

		//Svuoto alcune textField
		JTextField textFieldKW = getTextField_Kw();
		textFieldKW.setText("");
		JTextField textFieldCV = getTextField_Cv();
		textFieldCV.setText("");
		JTextField textFieldCilindrata = getTextField_Cilindrata();
		textFieldCilindrata.setText("");

		//Disattivo le combobox che non servono nel caso di motoveicolo
		JComboBox<String> comboBoxCarrozzeria = getComboBox_Carrozzeria();
		comboBoxCarrozzeria.setEnabled(false);		
		comboBoxPostiASedere.setEnabled(false);
		JComboBox<String> comboBoxFinitureInterni = getComboBox_FinitureInterni();
		comboBoxFinitureInterni.setEnabled(false);
		JComboBox<String> comboBoxColoreInterni = getComboBox_ColoreInterni();
		comboBoxColoreInterni.setEnabled(false);
		comboBoxMotore.setEnabled(false);

		//Disattivo le checkbox che non servono nel caso di motoveicolo
		JCheckBox chckbxHandicap = getChckbxHandicap();
		chckbxHandicap.setEnabled(false);
		JCheckBox chckbxServosterzo = getChckbxServosterzo();
		chckbxServosterzo.setEnabled(false);
		JCheckBox chckbxSediliSportivi = getChckbxSediliSportivi();
		chckbxSediliSportivi.setEnabled(false);
		JCheckBox chckbxParkDistControl = getChckbxParkDistControl();
		chckbxParkDistControl.setEnabled(false);
		JCheckBox chckbxFreniADisco = getChckbxFreniADisco();
		chckbxFreniADisco.setEnabled(true);
		JCheckBox chckbxRadiolettoreCd = getChckbxRadiolettoreCd();
		chckbxRadiolettoreCd.setEnabled(false);
		JCheckBox chckbxAbs = getChckbxAbs();
		chckbxAbs.setEnabled(true);
		JCheckBox chckbxGancioTraino = getChckbxGancioTraino();
		chckbxGancioTraino.setEnabled(false);
		JCheckBox chckbxVolanteMultifunzione = getChckbxVolanteMultifunzione();
		chckbxVolanteMultifunzione.setEnabled(false);
		JCheckBox chckbxImmobilizer = getChckbxImmobilizer();
		chckbxImmobilizer.setEnabled(false);
		JCheckBox chckbxPortapacchi = getChckbxPortapacchi();
		chckbxPortapacchi.setEnabled(false);
		JCheckBox chckbxAirbag = getChckbxAirbag();
		chckbxAirbag.setEnabled(false);
		JCheckBox chckbxEsp = getChckbxEsp();
		chckbxEsp.setEnabled(false);
		JCheckBox chckbxAlzacristalliElettrici = getChckbxAlzacristalliElettrici();
		chckbxAlzacristalliElettrici.setEnabled(false);
		JCheckBox chckbxNavigatoreSatellitare = getChckbxNavigatoreSatellitare();
		chckbxNavigatoreSatellitare.setEnabled(false);
		JCheckBox chckbxCerchiInLega = getChckbxCerchiInLega();
		chckbxCerchiInLega.setEnabled(false);
		JCheckBox chckbxContrAutomTrazione = getChckbxContrAutomTrazione();
		chckbxContrAutomTrazione.setEnabled(false);
		JCheckBox chckbxChiusuraCentralizzata = getChckbxChiusuraCentralizzata();
		chckbxChiusuraCentralizzata.setEnabled(false);
		JCheckBox chckbxSediliRiscaldati = getChckbxSediliRiscaldati();
		chckbxSediliRiscaldati.setEnabled(false);
		JCheckBox chckbxClima = getChckbxClima();
		chckbxClima.setEnabled(false);
		JCheckBox chckbxAntifurto = getChckbxAntifurto();
		chckbxAntifurto.setEnabled(false);
		JCheckBox chckbxCupolino = getChckbxCupolino();
		chckbxCupolino.setEnabled(true);
		JCheckBox chckbxBauletto = getChckbxBauletto();
		chckbxBauletto.setEnabled(true);
		JCheckBox chckbxAvviamentoAPedale = getChckbxAvviamentoAPedale();
		chckbxAvviamentoAPedale.setEnabled(true);
		JCheckBox chckbxAvviamentoElettrico = getChckbxAvviamentoElettrico();
		chckbxAvviamentoElettrico.setEnabled(true);

		//Modifico le opzioni della combobox Carburante con le opzioni per i motoveicoli
		JComboBox<String> comboBoxCarburante = getComboBox_Carburante();
		comboBoxCarburante.removeAllItems();
		comboBoxCarburante.setModel(new DefaultComboBoxModel<String>(carburantiMotoveicoli));

		//Modifico le opzioni della combobox Tipologia con le opzioni per i motoveicoli
		JComboBox<String> comboBoxTipologia = getComboBox_Tipologia();
		comboBoxTipologia.removeAllItems();
		comboBoxTipologia.setModel(new DefaultComboBoxModel<String>(tipologiaMotoveicoli));

	}

	//Metodo per popolare la combobox Modello veicolo
	//Metodo per popolare la combobox Modello veicolo
	private void popolaModelloVeicolo(String marcaVeicolo, JComboBox<String> currentComboboxModello, JComboBox<String> currentComboboxVersione)  {

		marcaVeicolo = marcaVeicolo.toLowerCase().trim().replace(" ", "-");

		Date now = new Date(); 
		String queryUrl = "http://www.carqueryapi.com/api/0.3/?cmd=getModels&make=" + marcaVeicolo + "&sold_in_us=&_=" + now.getTime();
		Object[] response = null;
		JSONObject json = null;

		String modelloAttuale = null;

		//Rimuovo tutte le selezioni dalle combobox Modello e Versione
		currentComboboxModello.removeAllItems();
		currentComboboxVersione.removeAllItems();

		//Invio la richiesta al DB remoto
		HttpPortalGetConnection getModelloVeicolo = new HttpPortalGetConnection();
		try {
			response = getModelloVeicolo.get("GET della marca veicolo per ottenere il modello", queryUrl, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String responseBody = (String)response[1];

		//Parsing JSON della risposta
		try {
			json = new JSONObject(responseBody);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//Array contenente la lista dei modelli ritornati dalla precedente richiesta
		JSONArray jsonResults = json.getJSONArray("Models");    

		//Popolo la combobox Modello con i risultati ottenuti
		currentComboboxModello.addItem("Seleziona");
		for(int i=0; i<jsonResults.length(); i++) {
			JSONObject currentJson = jsonResults.getJSONObject(i);
			modelloAttuale = currentJson.getString("model_name");
			currentComboboxModello.addItem(modelloAttuale);
		}	


	}



	//Metodo per popolare la combobox Versione veicolo
	private void popolaVersioneVeicolo(String marcaVeicolo, String modelloVeicolo, JComboBox<String> currentComboboxVersione) {

		//Svuoto la lista riempita precedentemente
		listVersioniVeicoli.clear();

		marcaVeicolo = marcaVeicolo.toLowerCase().trim().replace(" ", "-");
		modelloVeicolo = modelloVeicolo.toLowerCase().trim().replace(" ", "+");

		Date now = new Date(); 

		String queryUrl = "http://www.carqueryapi.com/api/0.3/?cmd=getTrims&make=" + marcaVeicolo + "&year=-1&model=" + modelloVeicolo + "&sold_in_us=&full_results=0&_=" + "&sold_in_us=&_=" + now.getTime();
		Object[] response = null;
		JSONObject json = null;

		//La versione veicolo è il risulatato della concatenazione di differenti dati
		String annoFabbricazione = null;
		String nomeVersione = null;
		String nomeModello = null;

		currentComboboxVersione.removeAllItems();

		//Invio la richiesta al server remoto
		HttpPortalGetConnection getVersioneVeicolo = new HttpPortalGetConnection();
		try {
			response = getVersioneVeicolo.get("GET del modello veicolo per ottenere la versione", queryUrl, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String responseBody = (String)response[1];

		//Parsing JSON della risposta
		try {
			json = new JSONObject(responseBody);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//Array contenente la lista dei modelli ritornati dalla precedente richiesta
		JSONArray jsonResults = json.getJSONArray("Trims");

		//Popolo la combobox Versione con i risultati ottenuti
		currentComboboxVersione.addItem("Seleziona");
		for(int i=0; i<jsonResults.length(); i++) {
			JSONObject currentJson = jsonResults.getJSONObject(i);     	
			annoFabbricazione = currentJson.getString("model_year");
			nomeVersione = currentJson.getString("model_trim");
			nomeModello = currentJson.getString("model_name");
			currentComboboxVersione.addItem(annoFabbricazione + " - " +  nomeVersione + " - " + nomeModello);

			//Inserisco un id del modello attuale nella lista 
			listVersioniVeicoli.add(currentJson.getString("model_id"));
		}	

	}


	//Metodo per popolare altri campi info veicolo
	private void popolaInfoVeicolo(String IdModelloVeicolo) {

		Date now = new Date(); 
		Object[] response = null;
		JSONObject json = null;
		JSONArray jsonResults = null;

		//Seleziono i campi che verranno valorizzati
		JComboBox<String> comboboxCarburante = getComboBox_Carburante();
		JComboBox<String> comboboxPostiASedere = getComboBox_PostiASedere();
		JTextField txtFieldCV = getTextField_Cv();
		JTextField txtFieldKW = getTextField_Kw();
		JComboBox<String> comboboxCambio = getComboBox_Cambio();
		JTextField txtFieldCilindrata = getTextField_Cilindrata();
		JTextField txtFieldConsumoMedio = getTextField_ConsumoMedio();
		JComboBox<String> comboboxCarrozzeria = getComboBox_Carrozzeria();
		JComboBox<String> comboboxTrazioneMotore = getComboBox_Motore();

		//Inizializzo le relative variabili
		String tipologiaCarburante;
		String postiASedere;
		String numeroCV;
		String numeroKW;
		String tipologiaCambio;
		String cilindrata;
		String consumoMedio;
		String carrozzeria;
		String trazione;

		//Invio la richiesta al server remoto
		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
		try {
			response = getInfoVeicolo.get("GET delle informazioni veicolo", "http://www.carqueryapi.com/api/0.3/?cmd=getModel&model=" + IdModelloVeicolo + "&_=" + now.getTime(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String responseBody = (String)response[1];

		//Parsing JSON della risposta
		try {
			jsonResults = new JSONArray(responseBody);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Ottengo il JSON relativo al primo elemento del JSONArray precedente
		try {
			json = new JSONObject(jsonResults.getString(0));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tipologiaCarburante = json.getString("model_engine_fuel")!="null"?json.getString("model_engine_fuel"):"";
		postiASedere = json.getString("model_seats")!="null"?json.getString("model_seats"):"";
		numeroCV = json.getString("model_engine_power_hp")!="null"?json.getString("model_engine_power_hp"):"";
		numeroKW = json.getString("model_engine_power_kw")!="null"?json.getString("model_engine_power_kw"):"";
		tipologiaCambio = json.getString("model_transmission_type")!="null"?json.getString("model_transmission_type"):"";
		cilindrata = json.getString("model_engine_cc")!="null"?json.getString("model_engine_cc"):"";
		consumoMedio = json.getString("model_lkm_mixed")!="null"?json.getString("model_lkm_mixed"):"";
		carrozzeria = json.getString("model_body")!="null"?json.getString("model_body"):"";
		trazione = json.getString("model_drive")!="null"?json.getString("model_drive"):"";

		switch (tipologiaCarburante)
		{
		case "Gasoline": 
			comboboxCarburante.setSelectedItem("Benzina");
			break;
		case "Gasoline - Premium": 
			comboboxCarburante.setSelectedItem("Benzina");
			break;
		case "Premium":
			comboboxCarburante.setSelectedItem("Benzina");
			break;
		case "Diesel":
			comboboxCarburante.setSelectedItem("Diesel");
			break;
		case "Electric":
			comboboxCarburante.setSelectedItem("Elettrica");
			break;
		case "Diesel / Electric Hybrid":
			comboboxCarburante.setSelectedItem("Elettrica/Diesel");
			break;
		case "Gasoline / Electric Hybrid":
			comboboxCarburante.setSelectedItem("Elettrica/Benzina");
			break;
		case "LPG":
			comboboxCarburante.setSelectedItem("GPL");
			break;
		case "Natural Gas (CNG)":
			comboboxCarburante.setSelectedItem("Metano");
			break;
		default:
			comboboxCarburante.setSelectedIndex(0);
		}

		if(Integer.parseInt(postiASedere)<11) {
			comboboxPostiASedere.setSelectedItem(postiASedere);
		}

		txtFieldCV.setText(numeroCV);

		txtFieldKW.setText(numeroKW);

		switch (trazione)
		{
		case "Front": 
			comboboxTrazioneMotore.setSelectedItem("Anteriore");
			break;
		case "Rear": 
			comboboxTrazioneMotore.setSelectedItem("Posteriore");
			break;
		case "4WD": 
			comboboxTrazioneMotore.setSelectedItem("4x4");
			break;
		case "AWD": 
			comboboxTrazioneMotore.setSelectedItem("6wd");
			break;
		default:
			comboboxTrazioneMotore.setSelectedIndex(0);
		}

		switch (tipologiaCambio)
		{
		case "Automatic": 
			comboboxCambio.setSelectedItem("Automatico");
			break;
		case "Manual": 
			comboboxCambio.setSelectedItem("Manuale");
			break;
		default:
			comboboxCambio.setSelectedIndex(0);
		}
		if(tipologiaCambio.contains("manual")){comboboxCambio.setSelectedItem("Manuale");}

		txtFieldCilindrata.setText(cilindrata);

		txtFieldConsumoMedio.setText(consumoMedio);	

		switch (carrozzeria)
		{
		case "Coupe": 
			comboboxCarrozzeria.setSelectedItem("Coupè");
			break;
		case "SUV": 
			comboboxCarrozzeria.setSelectedItem("SUV/Fuoristrada");
			break;
		case "Station Wagon": 
			comboboxCarrozzeria.setSelectedItem("Station wagon");
			break;
		case "Van": 
			comboboxCarrozzeria.setSelectedItem("Furgoni/Van");
			break;
		case "Hatchback": 
			comboboxCarrozzeria.setSelectedItem("City car");
			break;
		default:
			comboboxCarrozzeria.setSelectedIndex(0);
		}	

	}


	//Metodo per selezionare una immagine e mostrarla in anteprima
	//Metodo per selezionare una immmagine
	private void selezionaImmagine(JLabel labelImmagine, int imageIndex) {
		JFileChooser dlgFile;

		//Selezione del file immagine
		dlgFile = new JFileChooser();
		if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			File selectedFile = dlgFile.getSelectedFile(); 
			Long fileSize = selectedFile.length();
			BufferedImage img = null;
			String selectedFileName = selectedFile.getName().toLowerCase();   

			if(selectedFile.isFile() && (selectedFileName.endsWith(format) || selectedFileName.endsWith(format2)) && fileSize<=maxFileSize) {   	
				try {
					img = ImageIO.read(selectedFile);

					//L'immagine è associata al file tramite il parametro passato
					switch (imageIndex)
					{
					case 1: 
						imgFile1 = selectedFile;
						break;
					case 2: 
						imgFile2 = selectedFile;
						break;
					case 3: 
						imgFile3 = selectedFile;
						break;
					case 4: 
						imgFile4 = selectedFile;
						break;
					case 5: 
						imgFile5 = selectedFile;
						break;
					case 6: 
						imgFile6 = selectedFile;
						break;
					case 7: 
						imgFile7 = selectedFile;
						break;
					case 8: 
						imgFile8 = selectedFile;
						break;
					case 9: 
						imgFile9 = selectedFile;
						break;
					default:
						imgFile10 = selectedFile;
					}		

				} catch (IOException e) {
					e.printStackTrace();
				}
				Image resizedimg = img.getScaledInstance(70, 50, Image.SCALE_FAST);          
				Icon icoImmagine = new ImageIcon(resizedimg);
				labelImmagine.setIcon(icoImmagine);
			}
			else {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("selezionaImmagine"), "Formato file non riconosciuto", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}



	//Metodo per resettare la form (cliente o veicolo dipendono dal parametro attuale)
	@SuppressWarnings("unchecked")
	private void resettaForm(LinkedList<JComponent> listCampiForm) {

		Color white = new Color(255,255,255);

		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = iteratorListCampiForm.next();

			switch (campoCorrente.getClass().getName())
			{
			case "javax.swing.JTextField": //Campo testuale
				((JTextComponent) campoCorrente).setText("");
				campoCorrente.setEnabled(true);
				campoCorrente.setBackground(white);
				break;
			case "javax.swing.JTextPane": //TextPane
				((JTextComponent) campoCorrente).setText("");
				((JTextComponent) campoCorrente).setEnabled(true);
				campoCorrente.setBackground(white);
				break;
			case "javax.swing.JComboBox": //Select
				if(((JComboBox<String>) campoCorrente).getItemCount()>0) {	//se la checkbox è popolata...
					((JComboBox<String>) campoCorrente).setSelectedIndex(0);
				}
				((JComboBox<String>) campoCorrente).setEnabled(true);
				campoCorrente.setBackground(white);
				break;
			case "javax.swing.JButton": //Pulsante
				((JButton) campoCorrente).setEnabled(true);
				break;
			case "javax.swing.JCheckBox": //Checkbox
				((JCheckBox) campoCorrente).setSelected(false);
				((JCheckBox) campoCorrente).setEnabled(true);
				break;  
			case "javax.swing.JLabel": //Label
				((JLabel) campoCorrente).setIcon(null);
				break;
			case "javax.swing.JRadioButton": //Label
				((JRadioButton) campoCorrente).setEnabled(true);
				break;
			default://
			}
			
			if(listCampiForm.contains(rdbtnAutoveicolo)) {
				getComboBox_Modello().removeAllItems();
				getComboBox_Versione().removeAllItems();
				getComboBox_MeseImmatricolazione().setModel(new DefaultComboBoxModel<String>(comboboxModelMesi));
				getComboBox_AnnoImmatricolazione().setModel(new DefaultComboBoxModel<String>(comboboxModelAnni));
				getComboBox_Carburante().setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));
				getComboBox_Tipologia().setModel(new DefaultComboBoxModel<String>(tipologiaAutoveicoli));
				getComboBox_Carrozzeria().setModel(new DefaultComboBoxModel<String>(comboboxModelCarrozzeria));
				getComboBox_PostiASedere().setModel(new DefaultComboBoxModel<String>(comboboxModelPostiASedere));
				getComboBox_ColoreEsterno().setModel(new DefaultComboBoxModel<String>(comboboxModelColoreEsterno));
				getComboBox_PrecedentiProprietari().setModel(new DefaultComboBoxModel<String>(comboboxModelPrecedentiProprietari));
				getComboBox_FinitureInterni().setModel(new DefaultComboBoxModel<String>(comboboxModelFinitureInterni));
				getComboBox_ColoreInterni().setModel(new DefaultComboBoxModel<String>(comboboxModelColoreInterni));
				getComboBox_Motore().setModel(new DefaultComboBoxModel<String>(comboboxModelMotore));
				getComboBox_Cambio().setModel(new DefaultComboBoxModel<String>(comboboxModelCambio));
				getComboBox_NumeroRapporti().setModel(new DefaultComboBoxModel<String>(comboboxModelNumeroRapporti));
				getComboBox_ClasseEmissioni().setModel(new DefaultComboBoxModel<String>(comboboxModelClasseEmissioni));
			} else {
				formCliente_getModelloVeicolo().removeAllItems();
				formCliente_getVersioneVeicolo().removeAllItems();
				formCliente_getTipologiaCliente().setModel(new DefaultComboBoxModel<String>(tipologiaAutoveicoli));
				formCliente_getColoreVeicolo().setModel(new DefaultComboBoxModel<String>(comboboxModelColoreEsterno));
				formCliente_getTipologiaCarburanteVeicolo().setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));
			}
			
		}	

	}	


	//Popola la lista con tutti i campi della form
	private void popolaListaCampiFormVeicolo() {
		
		listCampiFormVeicolo.add(getRdbtnAutoveicolo());
		listCampiFormVeicolo.add(getRdbtnMotoScooter());
		
		listCampiFormVeicolo.add(getComboBox_Marca());
		listCampiFormVeicolo.add(getComboBox_Modello());
		listCampiFormVeicolo.add(getComboBox_MeseImmatricolazione());
		listCampiFormVeicolo.add(getComboBox_AnnoImmatricolazione());
		listCampiFormVeicolo.add(getComboBox_Carburante());
		listCampiFormVeicolo.add(getComboBox_Tipologia());
		listCampiFormVeicolo.add(getComboBox_Carrozzeria());
		listCampiFormVeicolo.add(getComboBox_PostiASedere());
		listCampiFormVeicolo.add(getComboBox_ColoreEsterno());
		listCampiFormVeicolo.add(getComboBox_PrecedentiProprietari());
		listCampiFormVeicolo.add(getComboBox_FinitureInterni());
		listCampiFormVeicolo.add(getComboBox_ColoreInterni());
		listCampiFormVeicolo.add(getComboBox_Motore());
		listCampiFormVeicolo.add(getComboBox_Cambio());
		listCampiFormVeicolo.add(getComboBox_NumeroRapporti());
		listCampiFormVeicolo.add(getComboBox_ClasseEmissioni());	
		listCampiFormVeicolo.add(getComboBox_Versione());

		listCampiFormVeicolo.add(getTextField_Kw());
		listCampiFormVeicolo.add(getTextField_Cv());
		listCampiFormVeicolo.add(getTextField_Chilometraggio());
		listCampiFormVeicolo.add(getTextField_Prezzo());
		listCampiFormVeicolo.add(getTextField_Cilindrata());
		listCampiFormVeicolo.add(getTextField_ConsumoMedio());
		listCampiFormVeicolo.add(getTextField_YouTubeUrl());
		listCampiFormVeicolo.add(getTextFieldIndirizzo());
		listCampiFormVeicolo.add(getTextFieldTelefonoGenerico());
		listCampiFormVeicolo.add(getTextFieldTelefonoReferente());
		listCampiFormVeicolo.add(getTextFieldEmailReferente());

		listCampiFormVeicolo.add(getChckbxMetallizzato());
		listCampiFormVeicolo.add(getChckbxTrattabile());
		listCampiFormVeicolo.add(getChckbxIvaDeducibile());
		listCampiFormVeicolo.add(getChckbxAbs());
		listCampiFormVeicolo.add(getChckbxAirbag());
		listCampiFormVeicolo.add(getChckbxAntifurto());
		listCampiFormVeicolo.add(getChckbxChiusuraCentralizzata());
		listCampiFormVeicolo.add(getChckbxContrAutomTrazione());
		listCampiFormVeicolo.add(getChckbxEsp());
		listCampiFormVeicolo.add(getChckbxImmobilizer());
		listCampiFormVeicolo.add(getChckbxFreniADisco());
		listCampiFormVeicolo.add(getChckbxCupolino());
		listCampiFormVeicolo.add(getChckbxAlzacristalliElettrici());
		listCampiFormVeicolo.add(getChckbxClima());
		listCampiFormVeicolo.add(getChckbxNavigatoreSatellitare());
		listCampiFormVeicolo.add(getChckbxRadiolettoreCd());
		listCampiFormVeicolo.add(getChckbxParkDistControl());
		listCampiFormVeicolo.add(getChckbxSediliRiscaldati());
		listCampiFormVeicolo.add(getChckbxServosterzo());
		listCampiFormVeicolo.add(getChckbxVolanteMultifunzione());
		listCampiFormVeicolo.add(getChckbxHandicap());
		listCampiFormVeicolo.add(getChckbxCerchiInLega());
		listCampiFormVeicolo.add(getChckbxGancioTraino());
		listCampiFormVeicolo.add(getChckbxPortapacchi());
		listCampiFormVeicolo.add(getChckbxSediliSportivi());
		listCampiFormVeicolo.add(getChckbxBauletto());
		listCampiFormVeicolo.add(getChckbxAvviamentoAPedale());
		listCampiFormVeicolo.add(getChckbxAvviamentoElettrico());

		listCampiFormVeicolo.add(getTextPane_Descrizione());

		listCampiFormVeicolo.add(getLabel_Immagine1());
		listCampiFormVeicolo.add(getLabel_Immagine2());
		listCampiFormVeicolo.add(getLabel_Immagine3());
		listCampiFormVeicolo.add(getLabel_Immagine4());
		listCampiFormVeicolo.add(getLabel_Immagine5());
		listCampiFormVeicolo.add(getLabel_Immagine6());
		listCampiFormVeicolo.add(getLabel_Immagine7());
		listCampiFormVeicolo.add(getLabel_Immagine8());
		listCampiFormVeicolo.add(getLabel_Immagine9());
		listCampiFormVeicolo.add(getLabel_Immagine10());

		listCampiFormVeicolo.add(getBtnImmagine_1());
		listCampiFormVeicolo.add(getBtnImmagine_2());
		listCampiFormVeicolo.add(getBtnImmagine_3());
		listCampiFormVeicolo.add(getBtnImmagine_4());
		listCampiFormVeicolo.add(getBtnImmagine_5());
		listCampiFormVeicolo.add(getBtnImmagine_6());
		listCampiFormVeicolo.add(getBtnImmagine_7());
		listCampiFormVeicolo.add(getBtnImmagine_8());
		listCampiFormVeicolo.add(getBtnImmagine_9());
		listCampiFormVeicolo.add(getBtnImmagine_10());

		listCampiFormVeicolo.add(getRdbtnAutoveicolo());
		listCampiFormVeicolo.add(getRdbtnMotoScooter());
	}


	//Popola la lista con i soli campi obbligatori della form veicolo
	private void popolaListaCampiObbligatoriFormVeicolo() {
		listCampiFormVeicoloObbligatori.add(getComboBox_Marca());
		listCampiFormVeicoloObbligatori.add(getComboBox_Modello());
		listCampiFormVeicoloObbligatori.add(getComboBox_Versione());
		listCampiFormVeicoloObbligatori.add(getComboBox_Tipologia());
		listCampiFormVeicoloObbligatori.add(getComboBox_ColoreEsterno());		

		listCampiFormVeicoloObbligatori.add(getTextField_Prezzo());			
		listCampiFormVeicoloObbligatori.add(getTextFieldIndirizzo());
		listCampiFormVeicoloObbligatori.add(getTextFieldTelefonoGenerico());
		listCampiFormVeicoloObbligatori.add(getTextFieldTelefonoReferente());
		listCampiFormVeicoloObbligatori.add(getTextFieldEmailReferente());
		listCampiFormVeicoloObbligatori.add(getTextFieldRagioneSociale());
		listCampiFormVeicoloObbligatori.add(getTextFieldReferente());

		listCampiFormVeicoloObbligatori.add(getTextPane_Descrizione());

	}


	//Popola la lista con tutti i campi della form
	private void popolaListaCampiFormCliente() {
		
		listCampiFormCliente.add(formCliente_getRdbtnSignore());
		listCampiFormCliente.add(formCliente_getRdbtnSignora());
		
		listCampiFormCliente.add(formCliente_getMarcaVeicolo());
		listCampiFormCliente.add(formCliente_getModelloVeicolo());
		listCampiFormCliente.add(formCliente_getVersioneVeicolo());
		listCampiFormCliente.add(formCliente_getTipologiaCarburanteVeicolo());
		listCampiFormCliente.add(formCliente_getColoreVeicolo());
		listCampiFormCliente.add(formCliente_getTipologiaCliente());

		listCampiFormCliente.add(formCliente_getNome());
		listCampiFormCliente.add(formCliente_getCognome());
		listCampiFormCliente.add(formCliente_getEmail());
		listCampiFormCliente.add(formCliente_getTelefono1());
		listCampiFormCliente.add(formCliente_getTelefono2());
		listCampiFormCliente.add(formCliente_getVia());
		listCampiFormCliente.add(formCliente_getNumeroCivico());
		listCampiFormCliente.add(formCliente_getCAP());
		listCampiFormCliente.add(formCliente_getCitta());
		
	}


	//Popola la lista con i soli campi obbligatori della form cliente
	private void popolaListaCampiObbligatoriFormCliente() {			
		listCampiFormClienteObbligatori.add(formCliente_getNome());
		listCampiFormClienteObbligatori.add(formCliente_getCognome());
		listCampiFormClienteObbligatori.add(formCliente_getEmail());
		listCampiFormClienteObbligatori.add(formCliente_getTelefono1());
		listCampiFormClienteObbligatori.add(formCliente_getVia());
		listCampiFormClienteObbligatori.add(formCliente_getNumeroCivico());
		listCampiFormClienteObbligatori.add(formCliente_getCAP());
		listCampiFormClienteObbligatori.add(formCliente_getCitta());
	}



	//Controlla se la form è compilata corettamente
	@SuppressWarnings("unchecked")
	private boolean isFormValid(LinkedList<JComponent> listCampiForm) {

		boolean isValid = true;
		Color white = new Color(255,255,255);
		Color red = new Color(255,0,0);

		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {

			JComponent campoCorrente = iteratorListCampiForm.next();
			switch (campoCorrente.getClass().getName())
			{
			case "javax.swing.JTextField": //Campo testuale	    	
				if(((JTextField) campoCorrente).getText().trim().equals("")){
					campoCorrente.setBackground(red);
					isValid=false;
				}
				else {
					campoCorrente.setBackground(white);
				}
				break;
			case "javax.swing.JTextPane": //TextPane
				if(((JTextPane) campoCorrente).getText().trim().equals("")){
					campoCorrente.setBackground(red);
					isValid=false;
				}
				else {
					campoCorrente.setBackground(white);
				}
				break;
			case "javax.swing.JComboBox": //Select
				if(((JComboBox<String>) campoCorrente).getSelectedIndex()==0) {	//se la checkbox non è popolata...
					campoCorrente.setBackground(red);
					isValid=false;
				}
				else {
					campoCorrente.setBackground(white);
				}

				break;
			default://
			}
		}

		return isValid;
	}


	//Disegna il pannello dei portali di sincronizzazione nella sua configurazione attiva (scheda selezionata) 
	static void panelInserimentoInActiveMode(JPanel pannelloListaPortali, final SchedaVeicolo scheda, final boolean selectAllSelected) {

		pannelloListaPortali.removeAll();

		//Ciclo ogni oggetto PortaleWeb presente nella lista concatenata e per ognuno aggiorno il sottopannello
		ListIterator<PortaleWeb> iterator = J2Web_UI.listPortaliSincronizzazione.listIterator();
		while(iterator.hasNext()) {	     		
			final PortaleWeb portaleCorrente = iterator.next();
			InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente, scheda, selectAllSelected);
			pannelloListaPortali.add(inserimentoPortale);
		}

		pannelloListaPortali.updateUI();

	}


	//Il nuovo oggetto scheda immobile viene inserito nella struttura dati e salvato nel file .dat relativo a tutte le schede
	static void aggiungiSchedaVeicolo(SchedaVeicolo scheda) {

		//Aggiorno la lista delle schede immobile
		listSchedeVeicolo.add(scheda);

		//Aggiorno il file dat delle schede
		j2web.salvaListaSchedeVeicoloCreate();

	}

	//Il nuovo oggetto scheda cliente viene inserito nella struttura dati e salvato nel file .dat relativo a tutte le schede
	static void aggiungiSchedaCliente(SchedaCliente scheda) {

		//Aggiorno la lista delle schede immobile
		listSchedeCliente.add(scheda);

		//Aggiorno il file dat delle schede
		j2web.salvaListaSchedeClienteCreate();

	}

	//I metodi che espongono elementi della GUI
	//Combobox
	protected static JComboBox<String> getComboBox_Tipologia() {
		return comboBox_Tipologia;
	}
	protected static JComboBox<String> getComboBox_Marca() {
		return comboBox_Marca;
	}
	protected static JComboBox<String> getComboBox_Carrozzeria() {
		return comboBox_Carrozzeria;
	}
	protected static JComboBox<String> getComboBox_PostiASedere() {
		return comboBox_PostiASedere;
	}
	protected static JComboBox<String> getComboBox_FinitureInterni() {
		return comboBox_FinitureInterni;
	}
	protected static JComboBox<String> getComboBox_ColoreInterni() {
		return comboBox_ColoreInterni;
	}
	protected static JCheckBox getChckbxCupolino() {
		return chckbxCupolino;
	}
	protected static JCheckBox getChckbxHandicap() {
		return chckbxHandicap;
	}
	protected static JCheckBox getChckbxServosterzo() {
		return chckbxServosterzo;
	}
	protected static JCheckBox getChckbxSediliSportivi() {
		return chckbxSediliSportivi;
	}
	protected static JCheckBox getChckbxParkDistControl() {
		return chckbxParkDistControl;
	}
	protected static JCheckBox getChckbxFreniADisco() {
		return chckbxFreniADisco;
	}
	protected static JCheckBox getChckbxRadiolettoreCd() {
		return chckbxRadiolettoreCd;
	}
	protected static JCheckBox getChckbxAntifurto() {
		return chckbxAntifurto;
	}
	protected static JCheckBox getChckbxAbs() {
		return chckbxAbs;
	}
	protected static JCheckBox getChckbxGancioTraino() {
		return chckbxGancioTraino;
	}
	protected static JCheckBox getChckbxVolanteMultifunzione() {
		return chckbxVolanteMultifunzione;
	}
	protected static JCheckBox getChckbxImmobilizer() {
		return chckbxImmobilizer;
	}
	protected static JCheckBox getChckbxPortapacchi() {
		return chckbxPortapacchi;
	}
	protected static JCheckBox getChckbxAirbag() {
		return chckbxAirbag;
	}
	protected static JCheckBox getChckbxEsp() {
		return chckbxEsp;
	}
	protected static JCheckBox getChckbxAlzacristalliElettrici() {
		return chckbxAlzacristalliElettrici;
	}
	protected static JCheckBox getChckbxNavigatoreSatellitare() {
		return chckbxNavigatoreSatellitare;
	}
	protected static JCheckBox getChckbxCerchiInLega() {
		return chckbxCerchiInLega;
	}
	protected static JCheckBox getChckbxContrAutomTrazione() {
		return chckbxContrAutomTrazione;
	}
	protected static JCheckBox getChckbxChiusuraCentralizzata() {
		return chckbxChiusuraCentralizzata;
	}
	protected static JCheckBox getChckbxSediliRiscaldati() {
		return chckbxSediliRiscaldati;
	}
	protected static JCheckBox getChckbxClima() {
		return chckbxClima;
	}
	protected static JCheckBox getChckbxAvviamentoElettrico() {
		return chckbxAvviamentoElettrico;
	}
	protected static JCheckBox getChckbxAvviamentoAPedale() {
		return chckbxAvviamentoAPedale;
	}
	protected static JCheckBox getChckbxBauletto() {
		return chckbxBauletto;
	}
	protected static JComboBox<String> getComboBox_Motore() {
		return comboBox_Motore;
	}
	protected static JComboBox<String> getComboBox_Modello() {
		return comboBox_Modello;
	}
	protected static JComboBox<String> getComboBox_Carburante() {
		return comboBox_Carburante;
	}
	protected static JComboBox<String> getComboBox_Versione() {
		return comboBox_Versione;
	}
	protected static JComboBox<String> getComboBox_MeseImmatricolazione() {
		return comboBox_MeseImmatricolazione;
	}
	protected static JComboBox<String> getComboBox_AnnoImmatricolazione() {
		return comboBox_AnnoImmatricolazione;
	}
	protected static JComboBox<String> getComboBox_Cambio() {
		return comboBox_Cambio;
	}
	protected static JComboBox<String> getComboBox_NumeroRapporti() {
		return comboBox_NumeroRapporti;
	}
	protected static JComboBox<String> getComboBox_ColoreEsterno() {
		return comboBox_ColoreEsterno;
	}	
	protected static JComboBox<String> getComboBox_PrecedentiProprietari() {
		return comboBox_PrecedentiProprietari;
	}
	protected static JComboBox<String> getComboBox_ClasseEmissioni() {
		return comboBox_ClasseEmissioni;
	}	

	//Textfield
	protected static JTextField getTextField_YouTubeUrl() {
		return txtField_YouTubeUrl;
	}
	protected static JTextField getTextField_ConsumoMedio() {
		return comboBox_ConsumoMedio;
	}
	protected static JTextField getTextField_Cilindrata() {
		return comboBox_Cilindrata;
	}
	protected static JTextField getTextField_Kw() {
		return txtFieldKw;
	}
	protected static JTextField getTextField_Cv() {
		return txtFieldCv;
	}
	protected static JTextField getTextField_Chilometraggio() {
		return textField_Chilometraggio;
	}
	protected static JTextField getTextField_Prezzo() {
		return textField_Prezzo;
	}		

	//Checkbox
	protected static JCheckBox getChckbxMetallizzato() {
		return chckbxMetallizzato;
	}
	protected static JCheckBox getChckbxTrattabile() {
		return chckbxTrattabile;
	}
	protected static JCheckBox getChckbxIvaDeducibile() {
		return chckbxIvaDeducibile;
	}	

	//Radio button
	protected static JRadioButton getRdbtnAutoveicolo() {
		return rdbtnAutoveicolo;
	}
	protected static JRadioButton getRdbtnMotoScooter() {
		return rdbtnMotoScooter;
	}

	//Label
	protected static JLabel getLabel_Immagine1() {
		return label_Immagine1;
	}
	protected static JLabel getLabel_Immagine5() {
		return label_Immagine5;
	}
	protected static JLabel getLabel_Immagine10() {
		return label_Immagine10;
	}
	protected static JLabel getLabel_Immagine6() {
		return label_Immagine6;
	}
	protected static JLabel getLabel_Immagine2() {
		return label_Immagine2;
	}
	protected static JLabel getLabel_Immagine8() {
		return label_Immagine8;
	}
	protected static JLabel getLabel_Immagine3() {
		return label_Immagine3;
	}
	protected static JLabel getLabel_Immagine4() {
		return label_Immagine4;
	}
	protected static JLabel getLabel_Immagine7() {
		return label_Immagine7;
	}
	protected static JLabel getLabel_Immagine9() {
		return label_Immagine9;
	}

	//Textpane
	protected static JTextPane getTextPane_Descrizione() {
		return textPane_Descrizione;
	}

	//File immagine
	protected static File getFileImmagine1() {
		return imgFile1;
	}
	protected static File getFileImmagine2() {
		return imgFile2;
	}
	protected static File getFileImmagine3() {
		return imgFile3;
	}
	protected static File getFileImmagine4() {
		return imgFile4;
	}
	protected static File getFileImmagine5() {
		return imgFile5;
	}
	protected static File getFileImmagine6() {
		return imgFile6;
	}
	protected static File getFileImmagine7() {
		return imgFile7;
	}
	protected static File getFileImmagine8() {
		return imgFile8;
	}
	protected static File getFileImmagine9() {
		return imgFile9;
	}
	protected static File getFileImmagine10() {
		return imgFile10;
	}

	//Pannelli
	protected static JPanel getPanel_9() {
		return panel_9;
	}
	protected static JPanel getPanel_10() {
		return panel_10;
	}



	//Aggiorna il pannello dei portali di sincronizzazione
	public static void aggiornaPannelloListaPortaliSincronizzazione() {

		JPanel pannelloListaPortali = getPanel_10();

		pannelloListaPortali.removeAll();

		panelInserimentoInDefaultMode(pannelloListaPortali);

		pannelloListaPortali.updateUI();

	}

	//Disegna il pannello dei portali di sincronizzazione nella sua configurazione standard (nessuna scheda selezionata)
	private static void panelInserimentoInDefaultMode(JPanel pannelloListaPortali) {

		//Ciclo tra i portali immobiliari presenti nella lista concatenata e per ognuno creo dei sottopannelli e dei pulsanti (fittizi: non hanno alcuna funzionalità )
		ListIterator<PortaleWeb> iterator = listPortaliSincronizzazione.listIterator();
		while(iterator.hasNext()) {
			final PortaleWeb portaleCorrente = iterator.next();
			InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente);
			pannelloListaPortali.add(inserimentoPortale);
		} 

	}



	protected static JRadioButton formCliente_getRdbtnSignore() {
		return formCliente_rdbtnSignore;
	}
	protected static JRadioButton formCliente_getRdbtnSignora() {
		return formCliente_rdbtnSignora;
	}
	protected static JTextField formCliente_getNome() {
		return formCliente_textFieldNome;
	}
	protected static JTextField formCliente_getCognome() {
		return formCliente_textFieldCognome;
	}
	protected static JTextField formCliente_getEmail() {
		return formCliente_textFieldEmail;
	}
	protected static JTextField formCliente_getTelefono1() {
		return formCliente_textFieldTelefono1;
	}
	protected static JTextField formCliente_getTelefono2() {
		return formCliente_textFieldTelefono2;
	}
	protected static JTextField formCliente_getVia() {
		return formCliente_textFieldVia;
	}
	protected static JTextField formCliente_getNumeroCivico() {
		return formCliente_textFieldNumeroCivico;
	}
	protected static JTextField formCliente_getCAP() {
		return formCliente_textFieldCAP;
	}
	protected static JTextField formCliente_getCitta() {
		return formCliente_textFieldCitta;
	}
	protected static JComboBox<String> formCliente_getMarcaVeicolo() {
		return comboBox_Marca_Cliente;
	}
	protected static JComboBox<String> formCliente_getModelloVeicolo() {
		return comboBox_Modello_Cliente;
	}
	protected static JComboBox<String> formCliente_getVersioneVeicolo() {
		return comboBox_Versione_Cliente;
	}
	protected static JComboBox<String> formCliente_getTipologiaCarburanteVeicolo() {
		return comboBox_TipologiaCarburante_Cliente;
	}
	protected static JComboBox<String> formCliente_getColoreVeicolo() {
		return comboBox_Colore_Cliente;
	}
	protected static JComboBox<String> formCliente_getTipologiaCliente() {
		return comboBoxTipologia_Cliente;
	}

	protected static JPanel getPanel_13() {
		return panel_13;
	}
	protected static JPanel getPanel_4() {
		return panel_4;
	}
	protected static JPanel getPanel_6() {
		return panel_6;
	}

	protected static JPanel getPanel_31() {
		return panel_31;
	}
	protected static JPanel getPanel_15() {
		return panel_15;
	}
	protected static JPanel getPanel_8() {
		return panel_8;
	}
	protected static JTextField getTextFieldRagioneSociale() {
		return textFieldRagioneSociale;
	}
	protected static  JTextField getTextFieldIndirizzo() {
		return textFieldIndirizzo;
	}
	protected static  JTextField getTextFieldTelefonoGenerico() {
		return textFieldTelefonoGenerico;
	}
	protected static  JTextField getTextFieldReferente() {
		return textFieldReferente;
	}
	protected static  JTextField getTextFieldTelefonoReferente() {
		return textFieldTelefonoReferente;
	}
	protected static  JTextField getTextFieldEmailReferente() {
		return textFieldEmailReferente;
	}

	protected JButton getBtnImmagine_1() {
		return btnImmagine1;
	}
	protected JButton getBtnImmagine_2() {
		return btnImmagine2;
	}
	protected JButton getBtnImmagine_3() {
		return btnImmagine3;
	}
	protected JButton getBtnImmagine_4() {
		return btnImmagine4;
	}
	protected JButton getBtnImmagine_5() {
		return btnImmagine5;
	}
	protected JButton getBtnImmagine_6() {
		return btnImmagine6;
	}
	protected JButton getBtnImmagine_7() {
		return btnImmagine7;
	}
	protected JButton getBtnImmagine_8() {
		return btnImmagine8;
	}
	protected JButton getBtnImmagine_9() {
		return btnImmagine9;
	}
	protected JButton getBtnImmagine_10() {
		return btnImmagine10;
	}
}