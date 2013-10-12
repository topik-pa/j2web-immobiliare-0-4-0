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
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
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
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
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
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class J2Web_UI implements parametriGenerali{

	private static JFrame imagination_05;
	
	private static JTextField txtFieldKw;
	private static JTextField txtFieldCv;
	private static JTextField textField_Chilometraggio;
	private static JTextField textField_Prezzo;
	private static JTextField txtField_YouTubeUrl;
	private static JTextField comboBox_ConsumoMedio;
	private static JTextField comboBox_Cilindrata;
	
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
	
	private static final ButtonGroup buttonGroup = new ButtonGroup();
	
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
	
	private static JRadioButton rdbtnAutoveicolo;
	private static JRadioButton rdbtnMotoScooter;
	
	private static JLabel label_Immagine1;
	private static JLabel label_Immagine5;
	private static JLabel label_Immagine10;
	private static JLabel label_Immagine6;
	private static JLabel label_Immagine2;
	private static JLabel label_Immagine8;
	private static JLabel label_Immagine3;
	private static JLabel label_Immagine4;
	private static JLabel label_Immagine7;
	private static JLabel label_Immagine9;
	
	private static JTextPane textPane_Descrizione;
	
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
	
	//La struttura dati che contiene le schede veicolo create
  	public static LinkedList<SchedaVeicolo> listSchedeVeicolo = new LinkedList<SchedaVeicolo>();
  	
  	//La struttura dati che contiene le schede cliente create
  	public static LinkedList<SchedaCliente> listSchedeCliente = new LinkedList<SchedaCliente>();
  	
  	//La struttura dati che contiene le schede cliente che fanno match con la scheda veicolo eventualmente selezionata
  	public static LinkedList<SchedaCliente> listSchedeClientiMatch = new LinkedList<SchedaCliente>();
  	
  	//La struttura dati che contiene le schede veicolo che fanno match con la scheda cliente eventualmente selezionata
  	public static LinkedList<SchedaVeicolo> listSchedeVeicoliMatch = new LinkedList<SchedaVeicolo>();
  	
  	//La struttura dati che contiene i portali attivati
  	public static LinkedList<PortaleWeb> listPortaliImmobiliari = new LinkedList<PortaleWeb>();
  	
  	//Serve per raggruppare i radio button in una struttura coerente
  	public static ButtonGroup radioGrpSchede = new ButtonGroup();	
	
  	//Inizializzo la lista buffer che contiene le possibili versioni del veicolo attuale
	LinkedList<String> listVersioniVeicoli = new LinkedList<String>();
	
	//I pannelli lista veicoli inseriti e lista portali di inserimento
	private static JPanel panel_9;
	private static JPanel panel_10;
	private static JTextField formCliente_textFieldNome;
	private static JTextField formCliente_textFieldCognome;
	private static JTextField formCliente_textFieldEmail;
	private static JTextField formCliente_textFieldTelefono1;
	private static JTextField formCliente_textFieldTelefono2;
	private static JTextField formCliente_textFieldVia;
	private static JTextField formCliente_textFieldNumeroCivico;
	private static JTextField formCliente_textFieldCAP;
	private static JTextField formCliente_textFieldCitta;
	private static JComboBox<String> comboBox_Marca_Cliente;
	private static JComboBox<String> comboBox_Modello_Cliente;
	private static JComboBox<String> comboBox_Versione_Cliente;
	private static JRadioButton formCliente_rdbtnSignore;
	private static JRadioButton formCliente_rdbtnSignora;
	private static JComboBox<String> comboBox_TipologiaCliente_Cliente;
	private static JComboBox<String> comboBox_Colore_Cliente;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private static JPanel panel_13;
	private JTable table;
	private static JPanel panel_4;
	private static JPanel panel_6;
	private static JPanel panel_31;
	private static JPanel panel_15;
	private static JPanel panel_8;

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
		initialize(); //GUI e ascoltatori
		selezioneAutoVeicolo(); //la selezione degli autoveicoli è forzata all'avvio
		popolaListaCampiForm();	//i campi della form sono inseriti in una lista concatenata
		popolaListaCampiFormCliente();	//i campi della form cliente sono inseriti in una lista concatenata
		j2web.caricaListaSchedeCreate(); //carica le schede create nelle sessioni precedenti
		j2web.caricaListaSchedeClienteCreate(); //carica le schede create nelle sessioni precedenti
		j2web.inizializzaPortaliAttivi(); //inizializza i portali web attivi
		aggiornaPannelloListaSchedeVeicolo(); //aggiorna il pannello centrale (lista veicoli)
		aggiornaPannelloListaSchedeCliente(); //aggiorna il pannello centrale (lista veicoli)
		aggiornaPannelloListaPortaliSincronizzazione(); //aggiorna il pannello di destra (lista portali attivi)
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*Il JFrame principale*/
		imagination_05 = new JFrame();
		imagination_05.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Ciao ciao...");
				
				//FileUtils.deleteDirectory(<File object of directory>);
				
				/*File folder = new File("temp");
				
				File[] files = folder.listFiles();
			    if(files!=null) { //some JVMs return null for empty dirs
			        for(File f: files) {
			            if(f.isDirectory()) {
			                f.de  deleteFolder(f);
			            } else {
			                f.delete();
			            }
			        }
			    }*/
			}
		});

		imagination_05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		imagination_05.setTitle(nomeGUI);
		imagination_05.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\icon_web.png"));
		imagination_05.setBounds(GUI_bounds[0], GUI_bounds[1], GUI_bounds[2], GUI_bounds[3]);
		imagination_05.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imagination_05.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		imagination_05.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Anagrafica veicolo", new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\icon_car2.png"), panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{437, 0};
		gbl_panel.rowHeights = new int[] {300, 70, 0};
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
		gbl_panel_3.columnWidths = new int[]{400, 200, 200, 0};
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
		gbl_panel_16.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_16.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		rdbtnAutoveicolo = new JRadioButton("Autoveicolo");
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
		rdbtnMotoScooter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Al click sulla radio Moto/Scooter la form si attiva in modalità moto/scooter
				selezioneMotoScooter();
			}
		});
		buttonGroup.add(rdbtnMotoScooter);
		panel_20.add(rdbtnMotoScooter, "6, 2");
		
		JLabel lblMarca = new JLabel("Marca");
		panel_20.add(lblMarca, "2, 4");
		
		JLabel lblModello = new JLabel("Modello");
		panel_20.add(lblModello, "6, 4");
		
		JLabel lblVersione = new JLabel("Versione");
		lblVersione.setToolTipText("Indicare la versione del modello di veicolo selezionato");
		panel_20.add(lblVersione, "10, 4");
		
		comboBox_Marca = new JComboBox<String>();
		comboBox_Marca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					//Selezionando un marca veicolo popolo la combobox Modello veicolo
					String marcaVeicolo = (String) comboBox_Marca.getSelectedItem();
					
					if(!marcaVeicolo.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {
						
						JComboBox<String> comboboxModello =  getComboBox_Modello();
						JComboBox<String> comboboxVersione = getComboBox_Versione();
						
						try {
							popolaModelloVeicolo(marcaVeicolo, comboboxModello, comboboxVersione);
						} catch (HttpCommunicationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
					
				}	
			}
		});
		comboBox_Marca.setModel(new DefaultComboBoxModel<String>(marcheAutoveicoli));
		panel_20.add(comboBox_Marca, "2, 6, fill, default");
		
		comboBox_Modello = new JComboBox<String>();
		comboBox_Modello.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Selezionando un modello veicolo popolo la combobox Versione veicolo
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					String marcaVeicolo = (String) comboBox_Marca.getSelectedItem();
					String modelloVeicolo = (String) comboBox_Modello.getSelectedItem();
					JComboBox<String> comboboxVersione = getComboBox_Versione();
					
					if(modelloVeicolo!=null && !modelloVeicolo.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {
						try {
							popolaVersioneVeicolo(marcaVeicolo, modelloVeicolo, comboboxVersione);
						} catch (HttpCommunicationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}					
					
				}
			}
		});
		comboBox_Modello.setEditable(true);
		panel_20.add(comboBox_Modello, "6, 6, fill, default");
		
		comboBox_Versione = new JComboBox<String>();
		comboBox_Versione.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//Selezionando una versione veicolo popolo vari campi della form veicolo				
				String versioneVeicolo = (String) comboBox_Versione.getSelectedItem();
				if(versioneVeicolo!=null && !versioneVeicolo.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {
					
					int selectedComboboxIndex = comboBox_Versione.getSelectedIndex();
					String selectedModel = listVersioniVeicoli.get(selectedComboboxIndex);
					try {
						popolaInfoVeicolo(selectedModel);
					} catch (HttpCommunicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		comboBox_Versione.setEditable(true);
		comboBox_Versione.setToolTipText("Indicare la versione del modello di veicolo selezionato");
		panel_20.add(comboBox_Versione, "10, 6, fill, default");
		
		JLabel lblDataPrimaImmatricolazione = new JLabel("Data prima immatricolazione");
		panel_20.add(lblDataPrimaImmatricolazione, "2, 8");
		
		JLabel lblCarburante = new JLabel("Carburante");
		panel_20.add(lblCarburante, "10, 8");
		
		comboBox_MeseImmatricolazione = new JComboBox<String>();
		comboBox_MeseImmatricolazione.setModel(new DefaultComboBoxModel<String>(comboboxModelMesi));
		panel_20.add(comboBox_MeseImmatricolazione, "2, 10, fill, default");
		
		comboBox_AnnoImmatricolazione = new JComboBox<String>();
		comboBox_AnnoImmatricolazione.setModel(new DefaultComboBoxModel<String>(comboboxModelAnni));
		panel_20.add(comboBox_AnnoImmatricolazione, "6, 10, fill, default");
		
		comboBox_Carburante = new JComboBox<String>();
		comboBox_Carburante.setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));
		panel_20.add(comboBox_Carburante, "10, 10, fill, default");
		
		JLabel lblTipologia = new JLabel("Tipologia");
		panel_20.add(lblTipologia, "2, 12");
		
		JLabel lblCarrozzeria = new JLabel("Carrozzeria");
		panel_20.add(lblCarrozzeria, "6, 12");
		
		JLabel lblPostiASedere = new JLabel("Posti a sedere");
		panel_20.add(lblPostiASedere, "10, 12");
		
		comboBox_Tipologia = new JComboBox<String>();
		comboBox_Tipologia.setModel(new DefaultComboBoxModel<String>(tipologiaAutoveicoli));
		panel_20.add(comboBox_Tipologia, "2, 14, fill, default");
		
		comboBox_Carrozzeria = new JComboBox<String>();
		comboBox_Carrozzeria.setModel(new DefaultComboBoxModel<String>(comboboxModelCarrozzeria));
		panel_20.add(comboBox_Carrozzeria, "6, 14, fill, default");
		
		comboBox_PostiASedere = new JComboBox<String>();
		comboBox_PostiASedere.setModel(new DefaultComboBoxModel<String>(comboboxModelPostiASedere));
		panel_20.add(comboBox_PostiASedere, "10, 14, fill, default");
		
		JLabel lblPotenzaKw = new JLabel("Potenza (KW/CV)");
		panel_20.add(lblPotenzaKw, "2, 16");
		
		txtFieldKw = new JTextField();
		txtFieldKw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                	e.getComponent().getToolkit().beep();
                    e.consume();
                }
			}
		});
		txtFieldKw.setForeground(Color.LIGHT_GRAY);
		txtFieldKw.setText("KW");
		panel_20.add(txtFieldKw, "2, 18, fill, default");
		txtFieldKw.setColumns(10);
		
		txtFieldCv = new JTextField();
		txtFieldCv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                	e.getComponent().getToolkit().beep();
                    e.consume();
                }
			}
		});
		txtFieldCv.setForeground(Color.LIGHT_GRAY);
		txtFieldCv.setText("CV");
		panel_20.add(txtFieldCv, "6, 18, fill, default");
		txtFieldCv.setColumns(10);
		
		JLabel lblColoreEsterno = new JLabel("Colore esterno");
		panel_20.add(lblColoreEsterno, "2, 20");
		
		comboBox_ColoreEsterno = new JComboBox<String>();
		comboBox_ColoreEsterno.setModel(new DefaultComboBoxModel<String>(comboboxModelColoreEsterno));
		panel_20.add(comboBox_ColoreEsterno, "2, 22, fill, default");
		
		chckbxMetallizzato = new JCheckBox("Metallizzato");
		panel_20.add(chckbxMetallizzato, "6, 22");
		
		JLabel lblPrecedentiProprietari = new JLabel("Precedenti proprietari");
		panel_20.add(lblPrecedentiProprietari, "2, 24");
		
		JLabel lblChilometraggio = new JLabel("Chilometraggio");
		panel_20.add(lblChilometraggio, "6, 24");
		
		comboBox_PrecedentiProprietari = new JComboBox<String>();
		comboBox_PrecedentiProprietari.setModel(new DefaultComboBoxModel<String>(comboboxModelPrecedentiProprietari));
		panel_20.add(comboBox_PrecedentiProprietari, "2, 26, fill, default");
		
		textField_Chilometraggio = new JTextField();
		textField_Chilometraggio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                	e.getComponent().getToolkit().beep();
                    e.consume();
                }
			}
		});
		panel_20.add(textField_Chilometraggio, "6, 26, fill, default");
		textField_Chilometraggio.setColumns(10);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		panel_20.add(lblPrezzo, "2, 28");
		
		textField_Prezzo = new JTextField();
		textField_Prezzo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                	e.getComponent().getToolkit().beep();
                    e.consume();
                }
			}
		});
		panel_20.add(textField_Prezzo, "2, 30, fill, default");
		textField_Prezzo.setColumns(10);
		
		chckbxTrattabile = new JCheckBox("Trattabile");
		panel_20.add(chckbxTrattabile, "6, 30");
		
		chckbxIvaDeducibile = new JCheckBox("IVA deducibile");
		panel_20.add(chckbxIvaDeducibile, "10, 30");
		
		JLabel lblFinitureInterne = new JLabel("Finiture interni");
		panel_20.add(lblFinitureInterne, "2, 32");
		
		JLabel lblColoreInterni = new JLabel("Colore interni");
		panel_20.add(lblColoreInterni, "6, 32");
		
		comboBox_FinitureInterni = new JComboBox<String>();
		comboBox_FinitureInterni.setModel(new DefaultComboBoxModel<String>(comboboxModelFinitureInterni));
		panel_20.add(comboBox_FinitureInterni, "2, 34, fill, default");
		
		comboBox_ColoreInterni = new JComboBox<String>();
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
		panel_21.add(chckbxAbs, "2, 4, fill, default");
		
		chckbxAlzacristalliElettrici = new JCheckBox("Alzacristalli elettrici");
		panel_21.add(chckbxAlzacristalliElettrici, "4, 4, fill, default");
		
		chckbxHandicap = new JCheckBox("Handicap");
		panel_21.add(chckbxHandicap, "6, 4, fill, default");
		
		chckbxAirbag = new JCheckBox("Airbag");
		panel_21.add(chckbxAirbag, "2, 6, fill, default");
		
		chckbxClima = new JCheckBox("Clima");
		panel_21.add(chckbxClima, "4, 6, fill, default");
		
		chckbxCerchiInLega = new JCheckBox("Cerchi in lega");
		panel_21.add(chckbxCerchiInLega, "6, 6, fill, default");
		
		chckbxAntifurto = new JCheckBox("Antifurto");
		panel_21.add(chckbxAntifurto, "2, 8, fill, default");
		
		chckbxNavigatoreSatellitare = new JCheckBox("Navigatore satellitare");
		panel_21.add(chckbxNavigatoreSatellitare, "4, 8, fill, default");
		
		chckbxGancioTraino = new JCheckBox("Gancio traino");
		panel_21.add(chckbxGancioTraino, "6, 8, fill, default");
		
		chckbxChiusuraCentralizzata = new JCheckBox("Chiusura centralizzata");
		panel_21.add(chckbxChiusuraCentralizzata, "2, 10, fill, default");
		
		chckbxRadiolettoreCd = new JCheckBox("Radio/Lettore CD");
		panel_21.add(chckbxRadiolettoreCd, "4, 10, fill, default");
		
		chckbxPortapacchi = new JCheckBox("Portapacchi");
		panel_21.add(chckbxPortapacchi, "6, 10, fill, default");
		
		chckbxContrAutomTrazione = new JCheckBox("Contr. autom. trazione");
		panel_21.add(chckbxContrAutomTrazione, "2, 12, fill, default");
		
		chckbxParkDistControl = new JCheckBox("Park dist. control");
		panel_21.add(chckbxParkDistControl, "4, 12, fill, default");
		
		chckbxSediliSportivi = new JCheckBox("Sedili sportivi");
		panel_21.add(chckbxSediliSportivi, "6, 12, fill, default");
		
		chckbxBauletto = new JCheckBox("Bauletto");
		panel_21.add(chckbxBauletto, "6, 14, fill, default");
		
		chckbxAvviamentoAPedale = new JCheckBox("Avviamento a pedale");
		panel_21.add(chckbxAvviamentoAPedale, "6, 16, fill, default");
		
		chckbxAvviamentoElettrico = new JCheckBox("Avviamento elettrico");
		panel_21.add(chckbxAvviamentoElettrico, "6, 18, fill, default");
		
		chckbxEsp = new JCheckBox("ESP");
		panel_21.add(chckbxEsp, "2, 14, fill, default");
		
		chckbxSediliRiscaldati = new JCheckBox("Sedili riscaldati");
		panel_21.add(chckbxSediliRiscaldati, "4, 14, fill, default");
		
		chckbxImmobilizer = new JCheckBox("Immobilizer");
		panel_21.add(chckbxImmobilizer, "2, 16, fill, default");
		
		chckbxServosterzo = new JCheckBox("Servosterzo");
		panel_21.add(chckbxServosterzo, "4, 16, fill, default");
		
		chckbxFreniADisco = new JCheckBox("Freni a disco");
		panel_21.add(chckbxFreniADisco, "2, 18, fill, default");
		
		chckbxVolanteMultifunzione = new JCheckBox("Volante multifunzione");
		panel_21.add(chckbxVolanteMultifunzione, "4, 18, fill, default");
		
		chckbxCupolino = new JCheckBox("Cupolino");
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
		comboBox_Motore.setModel(new DefaultComboBoxModel<String>(comboboxModelMotore));
		panel_22.add(comboBox_Motore, "2, 4, fill, default");
		
		comboBox_Cambio = new JComboBox<String>();
		comboBox_Cambio.setModel(new DefaultComboBoxModel<String>(comboboxModelCambio));
		panel_22.add(comboBox_Cambio, "6, 4, fill, default");
		
		comboBox_NumeroRapporti = new JComboBox<String>();
		comboBox_NumeroRapporti.setModel(new DefaultComboBoxModel<String>(comboboxModelNumeroRapporti));
		panel_22.add(comboBox_NumeroRapporti, "10, 4, fill, default");
		
		JLabel lblCilindrata = new JLabel("Cilindrata");
		panel_22.add(lblCilindrata, "2, 6");
		
		JLabel lblClasseDiEmissione = new JLabel("Classe di emissione");
		panel_22.add(lblClasseDiEmissione, "6, 6");
		
		JLabel lblConsumoMedio = new JLabel("Consumo medio");
		panel_22.add(lblConsumoMedio, "10, 6");
		
		comboBox_Cilindrata = new JTextField();
		comboBox_Cilindrata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                	e.getComponent().getToolkit().beep();
                    e.consume();
                }
			}
		});
		panel_22.add(comboBox_Cilindrata, "2, 8, fill, default");
		
		comboBox_ClasseEmissioni = new JComboBox<String>();
		comboBox_ClasseEmissioni.setModel(new DefaultComboBoxModel<String>(comboboxModelClasseEmissioni));
		panel_22.add(comboBox_ClasseEmissioni, "6, 8, fill, default");
		
		comboBox_ConsumoMedio = new JTextField();
		comboBox_ConsumoMedio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
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
		
		JButton btnImmagine1 = new JButton("Immagine 1");
		btnImmagine1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine1, imgFile1);
			}
		});
		panel_23.add(btnImmagine1, "2, 2");
		
		label_Immagine1 = new JLabel();
		label_Immagine1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine1, "4, 2, fill, fill");
		
		JButton btnImmagine_2 = new JButton("Immagine 2");
		btnImmagine_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine2, imgFile2);
			}
		});
		panel_23.add(btnImmagine_2, "8, 2");
		
		label_Immagine2 = new JLabel();
		label_Immagine2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine2, "10, 2, fill, fill");
		
		JButton btnImmagine_3 = new JButton("Immagine 3");
		btnImmagine_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine3, imgFile3);
			}
		});
		panel_23.add(btnImmagine_3, "2, 4");
		
		label_Immagine3 = new JLabel();
		label_Immagine3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine3, "4, 4, fill, fill");
		
		JButton btnImmagine_4 = new JButton("Immagine 4");
		btnImmagine_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine4, imgFile4);
			}
		});
		panel_23.add(btnImmagine_4, "8, 4");
		
		label_Immagine4 = new JLabel();
		label_Immagine4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine4, "10, 4, fill, fill");
		
		JButton btnImmagine_5 = new JButton("Immagine 5");
		btnImmagine_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine5, imgFile5);
			}
		});
		panel_23.add(btnImmagine_5, "2, 6");
		
		label_Immagine5 = new JLabel();
		label_Immagine5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine5, "4, 6, fill, fill");
		
		JButton btnImmagine_6 = new JButton("Immagine 6");
		btnImmagine_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine6, imgFile6);
			}
		});
		panel_23.add(btnImmagine_6, "8, 6");
		
		label_Immagine6 = new JLabel();
		label_Immagine6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine6, "10, 6, fill, fill");
		
		JButton btnImmagine_7 = new JButton("Immagine 7");
		btnImmagine_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine7, imgFile7);
			}
		});
		panel_23.add(btnImmagine_7, "2, 8");
		
		label_Immagine7 = new JLabel();
		label_Immagine7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine7, "4, 8, fill, fill");
		
		JButton btnImmagine_8 = new JButton("Immagine 8");
		btnImmagine_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine8, imgFile8);
			}
		});
		panel_23.add(btnImmagine_8, "8, 8");
		
		label_Immagine8 = new JLabel();
		label_Immagine8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine8, "10, 8, fill, fill");
		
		JButton btnImmagine_9 = new JButton("Immagine 9");
		btnImmagine_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selezionaImmagine(label_Immagine9, imgFile9);
			}
		});
		panel_23.add(btnImmagine_9, "2, 10");
		
		label_Immagine9 = new JLabel();
		label_Immagine9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine9, "4, 10, fill, fill");
		
		JButton btnImmagine_10 = new JButton("Immagine 10");
		btnImmagine_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selezionaImmagine(label_Immagine10, imgFile10);
			}
		});
		panel_23.add(btnImmagine_10, "8, 10");
		
		label_Immagine10 = new JLabel();
		label_Immagine10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_23.add(label_Immagine10, "10, 10, fill, fill");
		
		JLabel label_YouTubeUrl = new JLabel("Url video di YouTube");
		panel_23.add(label_YouTubeUrl, "2, 12");
		
		txtField_YouTubeUrl = new JTextField();
		panel_23.add(txtField_YouTubeUrl, "2, 14, fill, fill");
		txtField_YouTubeUrl.setColumns(10);
		
		
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
		
		textPane_Descrizione = new JTextPane();
		textPane_Descrizione.setContentType("text/plain\r\ntext/xml\r\ntext/html");
		panel_24.add(textPane_Descrizione, "2, 4, fill, fill");
		
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
		
		JButton btnResetta = new JButton("Cancella campi");
		btnResetta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Resetta la form
				resettaForm(listCampiForm);
				selezioneAutoVeicolo();
				getRdbtnAutoveicolo().setSelected(true);
			}
		});
		btnResetta.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\refresh.png"));
		panel_18.add(btnResetta);
		
		JPanel panel_19 = new JPanel();
		panel_17.add(panel_19);
		
		final JButton btnInserisci = new JButton("Crea scheda");
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				System.out.print("Creazione della scheda veicolo...");				
				if(isFormValid()) { 
					System.out.println(" ...form valido... ");
					
					//Disabilito i campi della form
					disabilitaCampiForm();
					
					//Istanzio l'oggetto scheda e lo salvo nel file
             	   	SchedaVeicolo schedaVeicolo = new SchedaVeicolo();
             	    aggiungiSchedaVeicolo(schedaVeicolo);
             	 
             	   	//Il pannello centrale viene ridisegnato             	   	
             	   	aggiornaPannelloListaSchedeVeicolo();
             	   
             	    //Il pannello di destra viene ridisegnato
             	    //j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello();
             	    
             	    System.out.print(" fatto." + "\n");
				}
				else {
					System.out.println(" ...form non valido. Scheda non creata.");
					JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("creazioneDellaSchedaImmobile_FormNonValido"), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnInserisci.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\forward.png"));
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
		panel_9.setBorder(new TitledBorder(null, "Lista veicoli inseriti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		panel_10.setBorder(new TitledBorder(null, "Sincronizzazione nei portali Web", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_2.setViewportView(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane_3 = new JScrollPane();
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
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Clienti potenzialmente interessati al veicolo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_3.setViewportView(panel_4);
		panel_4.setBackground(new Color(255, 255, 224));
		
		
		JPanel panelNessunaScedaSelezionata = new JPanel();
        JLabel lblNessunaScedaSelezionata = new JLabel("Non è stata selezionata alcuna scheda.");                
        panelNessunaScedaSelezionata.add(lblNessunaScedaSelezionata);
        panel_4.add(panelNessunaScedaSelezionata);
		
		/*table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"11", "222", "333", "444", "55", "6666"},
				{"aa", "sss", "dd", "ff", "gg", "hhhh"},
			},
			new String[] {
				"Nome", "Cognome", "Cognome", "New column", "New column", "New column"
			}
		));
		panel_4.add(table);*/
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Anagrafica cliente", new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\icon_pilot.png"), panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {437, 0};
		gbl_panel_1.rowHeights = new int[] {300, 70, 0};
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
		
		formCliente_rdbtnSignore = new JRadioButton("Sign.");
		formCliente_rdbtnSignore.setSelected(true);
		buttonGroup_1.add(formCliente_rdbtnSignore);
		panel_29.add(formCliente_rdbtnSignore, "2, 2");
		
		formCliente_rdbtnSignora = new JRadioButton("Sign.ra");
		buttonGroup_1.add(formCliente_rdbtnSignora);
		panel_29.add(formCliente_rdbtnSignora, "4, 2");
		
		JLabel lblNome = new JLabel("Nome");
		panel_29.add(lblNome, "2, 4");
		
		JLabel lblCognome = new JLabel("Cognome");
		panel_29.add(lblCognome, "4, 4");
		
		formCliente_textFieldNome = new JTextField();
		panel_29.add(formCliente_textFieldNome, "2, 6, fill, default");
		formCliente_textFieldNome.setColumns(10);
		
		formCliente_textFieldCognome = new JTextField();
		panel_29.add(formCliente_textFieldCognome, "4, 6, fill, default");
		formCliente_textFieldCognome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		panel_29.add(lblEmail, "2, 8");
		
		JLabel lblTelefono = new JLabel("Telefono 1");
		panel_29.add(lblTelefono, "4, 8, fill, default");
		
		JLabel lblTelefono_1 = new JLabel("Telefono 2");
		panel_29.add(lblTelefono_1, "6, 8");
		
		formCliente_textFieldEmail = new JTextField();
		panel_29.add(formCliente_textFieldEmail, "2, 10, fill, default");
		formCliente_textFieldEmail.setColumns(10);
		
		formCliente_textFieldTelefono1 = new JTextField();
		panel_29.add(formCliente_textFieldTelefono1, "4, 10, fill, default");
		formCliente_textFieldTelefono1.setColumns(10);
		
		formCliente_textFieldTelefono2 = new JTextField();
		panel_29.add(formCliente_textFieldTelefono2, "6, 10, fill, default");
		formCliente_textFieldTelefono2.setColumns(10);
		
		JLabel lblVia = new JLabel("Via");
		panel_29.add(lblVia, "2, 12");
		
		JLabel lblNumero = new JLabel("Numero");
		panel_29.add(lblNumero, "4, 12");
		
		formCliente_textFieldVia = new JTextField();
		panel_29.add(formCliente_textFieldVia, "2, 14, fill, default");
		formCliente_textFieldVia.setColumns(10);
		
		formCliente_textFieldNumeroCivico = new JTextField();
		panel_29.add(formCliente_textFieldNumeroCivico, "4, 14, fill, default");
		formCliente_textFieldNumeroCivico.setColumns(10);
		
		JLabel lblCap = new JLabel("CAP");
		panel_29.add(lblCap, "2, 16");
		
		JLabel lblCitt = new JLabel("Città");
		panel_29.add(lblCitt, "4, 16");
		
		formCliente_textFieldCAP = new JTextField();
		panel_29.add(formCliente_textFieldCAP, "2, 18, fill, default");
		formCliente_textFieldCAP.setColumns(10);
		
		formCliente_textFieldCitta = new JTextField();
		panel_29.add(formCliente_textFieldCitta, "4, 18, fill, default");
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
		comboBox_Marca_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("Marca cliente");

				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					//Selezionando un marca veicolo popolo la combobox Modello veicolo
					String marcaVeicolo = (String) comboBox_Marca_Cliente.getSelectedItem();
					
					if(!marcaVeicolo.equals("Seleziona")) {
						
						JComboBox<String> comboboxModello =  formCliente_getModelloVeicolo();
						JComboBox<String> comboboxVersione = formCliente_getVersioneVeicolo();
						
						try {
							popolaModelloVeicolo(marcaVeicolo, comboboxModello, comboboxVersione);
						} catch (HttpCommunicationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
					
				}	

			}
		});
		comboBox_Marca_Cliente.setModel(new DefaultComboBoxModel<String>(marcheAutoveicoli));
		panel_30.add(comboBox_Marca_Cliente, "2, 4, fill, default");
		
		comboBox_Modello_Cliente = new JComboBox<String>();
		comboBox_Modello_Cliente.setEditable(true);
		comboBox_Modello_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("Modello cliente");
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					
					String marcaVeicolo = (String) comboBox_Marca_Cliente.getSelectedItem();
					String modelloVeicolo = (String) comboBox_Modello_Cliente.getSelectedItem();
					JComboBox<String> comboboxVersione = formCliente_getVersioneVeicolo();
					
					if(modelloVeicolo!=null && !modelloVeicolo.equals("Seleziona")) {
						try {
							popolaVersioneVeicolo(marcaVeicolo, modelloVeicolo, comboboxVersione);
						} catch (HttpCommunicationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}					
					
				}
			}
		});
		panel_30.add(comboBox_Modello_Cliente, "4, 4, fill, default");
		
		comboBox_Versione_Cliente = new JComboBox<String>();
		comboBox_Versione_Cliente.setEditable(true);
		comboBox_Versione_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("Versione cliente");
			}
		});
		panel_30.add(comboBox_Versione_Cliente, "6, 4, fill, default");
		
		comboBox_TipologiaCliente_Cliente = new JComboBox<String>();
		comboBox_TipologiaCliente_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("Carburante cliente");
			}
		});
		
		JLabel lblTipologiaCarburante = new JLabel("Tipologia carburante");
		panel_30.add(lblTipologiaCarburante, "2, 6");
		
		JLabel lblColore = new JLabel("Colore");
		panel_30.add(lblColore, "4, 6");
		comboBox_TipologiaCliente_Cliente.setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));
		panel_30.add(comboBox_TipologiaCliente_Cliente, "2, 8, fill, default");
		
		comboBox_Colore_Cliente = new JComboBox<String>();
		comboBox_Colore_Cliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("Colore cliente");
			}
		});
		comboBox_Colore_Cliente.setModel(new DefaultComboBoxModel<String>(comboboxModelColoreEsterno));
		panel_30.add(comboBox_Colore_Cliente, "4, 8, fill, default");
		
		JPanel panel_26 = new JPanel();
		GridBagConstraints gbc_panel_26 = new GridBagConstraints();
		gbc_panel_26.fill = GridBagConstraints.BOTH;
		gbc_panel_26.gridx = 0;
		gbc_panel_26.gridy = 1;
		panel_12.add(panel_26, gbc_panel_26);
		panel_26.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_27 = new JPanel();
		panel_26.add(panel_27);
		
		JButton btnResetFormCliente = new JButton("Cancella campi");
		btnResetFormCliente.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\refresh.png"));
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
		
		JButton btnCreaSchedaCliente = new JButton("Crea scheda");
		btnCreaSchedaCliente.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\forward.png"));
		btnCreaSchedaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("Creazione della scheda cliente...");				
				if(isFormValid()) { 
					System.out.println(" ...form cliente valido... ");
					
					//Disabilito i campi della form
					//disabilitaCampiForm();
					
					//Istanzio l'oggetto scheda e lo salvo nel file
             	   	SchedaCliente schedaCliente = new SchedaCliente();
             	   	aggiungiScheda(schedaCliente);
             	 
             	   	//Il pannello di destra viene ridisegnato             	   	
             	   	aggiornaPannelloListaSchedeCliente();
             	   
             	    //Il pannello di destra viene ridisegnato
             	    //j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello();
             	    
             	    System.out.print(" fatto." + "\n");
				}
				else {
					System.out.println(" ...form non valido. Scheda cliente non creata.");
					JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("creazioneDellaSchedaImmobile_FormNonValido"), "Errore", JOptionPane.ERROR_MESSAGE);
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
		panel_13.setBorder(new TitledBorder(null, "Lista schede cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Veicoli potenzialmente interessanti per il cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
        JLabel lblNessunaScedaSelezionata2 = new JLabel("Non è stata selezionata alcuna scheda.");                
        panelNessunaScedaSelezionata2.add(lblNessunaScedaSelezionata2);
        panel_6.add(panelNessunaScedaSelezionata2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Incrocio MLS", new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\icon_db.png"), panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {437, 0};
		gbl_panel_2.rowHeights = new int[] {300, 70, 0};
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
		
		JButton btnAggiornaRisultati = new JButton("Aggiorna risultati");
		panel_32.add(btnAggiornaRisultati);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_8 = new GridBagConstraints();
		gbc_scrollPane_8.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_8.gridx = 1;
		gbc_scrollPane_8.gridy = 0;
		panel_7.add(scrollPane_8, gbc_scrollPane_8);
		
		panel_15 = new JPanel();
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
		panel_8.setBorder(new TitledBorder(null, "Dettagli veicolo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
        JLabel lblNessunaScedaSelezionata3 = new JLabel("Non è stata selezionata alcuna scheda.");                
        panelNessunaScedaSelezionata3.add(lblNessunaScedaSelezionata3);
        panel_8.add(panelNessunaScedaSelezionata3);
		
		panel_16.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel_24, rdbtnAutoveicolo, rdbtnMotoScooter, lblMarca, panel_20, lblModello, lblVersione, comboBox_Marca, comboBox_Modello, lblDataPrimaImmatricolazione, lblCarburante, comboBox_MeseImmatricolazione, comboBox_AnnoImmatricolazione, lblTipologia, lblCarrozzeria, lblPostiASedere, comboBox_Tipologia, comboBox_Carrozzeria, comboBox_PostiASedere, lblPotenzaKw, txtFieldKw, txtFieldCv, lblColoreEsterno, comboBox_ColoreEsterno, chckbxMetallizzato, lblPrecedentiProprietari, lblChilometraggio, comboBox_PrecedentiProprietari, textField_Chilometraggio, lblPrezzo, textField_Prezzo, chckbxTrattabile, chckbxIvaDeducibile, lblFinitureInterne, lblColoreInterni, comboBox_FinitureInterni, comboBox_ColoreInterni, lblDescrizionemax, textPane_Descrizione, panel_23, btnImmagine1, label_Immagine1, btnImmagine_2, label_Immagine2, btnImmagine_3, label_Immagine3, btnImmagine_4, label_Immagine4, btnImmagine_5, label_Immagine5, btnImmagine_6, label_Immagine6, btnImmagine_7, label_Immagine7, btnImmagine_8, label_Immagine8, btnImmagine_9, label_Immagine9, btnImmagine_10, label_Immagine10, panel_22, lblNewLabel, lblNewLabel_1, lblNewLabel_2, comboBox_Motore, comboBox_Cambio, comboBox_NumeroRapporti, lblCilindrata, lblClasseDiEmissione, lblConsumoMedio, comboBox_Cilindrata, comboBox_ClasseEmissioni, comboBox_ConsumoMedio, panel_21, lblSicurezza, lblComodit, lblExtra, chckbxAbs, chckbxAlzacristalliElettrici, chckbxHandicap, chckbxAirbag, chckbxClima, chckbxCerchiInLega, chckbxAntifurto, chckbxNavigatoreSatellitare, chckbxGancioTraino, chckbxChiusuraCentralizzata, chckbxRadiolettoreCd, chckbxPortapacchi, chckbxContrAutomTrazione, chckbxParkDistControl, chckbxSediliSportivi, chckbxBauletto, chckbxAvviamentoAPedale, chckbxAvviamentoElettrico, chckbxEsp, chckbxSediliRiscaldati, chckbxImmobilizer, chckbxServosterzo, chckbxFreniADisco, chckbxVolanteMultifunzione, chckbxCupolino}));
		
		JMenuBar menuBar = new JMenuBar();
		imagination_05.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnTest = new JMenu("Menu");
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
		imagination_05.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{imagination_05.getContentPane(), panel, panel_1, panel_2}));
	}
	
	
	//Metodo per aggiornare la UI del pannello schede 
	protected static void aggiornaPannelloListaSchedeVeicolo() {
		
		JPanel pannelloListaSchedeVeicolo = getPanel_9();
		
		pannelloListaSchedeVeicolo.removeAll();
		
		//pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(7));	
		
		if(listSchedeVeicolo.isEmpty()) {
			//Pannello senza schede
    		System.out.println("La lista delle schede veicolo è vuota.");
    		JPanel panelNessunaScheda = new JPanel();
            JLabel lblNessunaScheda = new JLabel("La lista delle schede veicolo è vuota.");                
            panelNessunaScheda.add(lblNessunaScheda);
            pannelloListaSchedeVeicolo.add(panelNessunaScheda);
    	}    	
    	else {
    		//Pannello con schede
    		System.out.println("La lista delle schede veicolo contiene delle schede.");
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
	
	//Metodo per aggiornare la UI del pannello schede 
	protected static void aggiornaPannelloListaSchedeCliente() {
		
		JPanel pannelloListaSchedeCliente = getPanel_13();
		JPanel pannelloListaSchedeClienteMLS = getPanel_31();
		
		
		pannelloListaSchedeCliente.removeAll();
		pannelloListaSchedeClienteMLS.removeAll();
		
		//pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(7));	
		
		if(listSchedeCliente.isEmpty()) {
			//Pannello senza schede
    		System.out.println("La lista delle schede cliente è vuota.");
    		
    		JPanel panelNessunaScheda = new JPanel();
            JLabel lblNessunaScheda = new JLabel("La lista delle schede cliente è vuota.");                
            panelNessunaScheda.add(lblNessunaScheda);
            
            JPanel panelNessunaSchedaMLS = new JPanel();
            JLabel lblNessunaSchedaMLS = new JLabel("La lista delle schede cliente è vuota.");                
            panelNessunaSchedaMLS.add(lblNessunaSchedaMLS);
            
            pannelloListaSchedeCliente.add(panelNessunaScheda);
            pannelloListaSchedeClienteMLS.add(panelNessunaSchedaMLS);
    	}    	
    	else {
    		//Pannello con schede
    		System.out.println("La lista delle schede cliente contiene delle schede.");
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

	//Metodo per disabilitare i campi della form di creazione scheda
	protected void disabilitaCampiForm() {
		// TODO Auto-generated method stub
		
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
		
		//Svuoto la combobox Modello
		JComboBox<String> comboBoxVersione = getComboBox_Versione();
		comboBoxVersione.removeAllItems();
		comboBoxVersione.addItem("Inserire la versione");
			
		//Disattivo le combobox che non servono nel caso di motoveicolo
		JComboBox<String> comboBoxCarrozzeria = getComboBox_Carrozzeria();
		comboBoxCarrozzeria.setEnabled(false);		
		JComboBox<String> comboBoxPostiASedere = getComboBox_PostiASedere();
		comboBoxPostiASedere.setEnabled(false);
		JComboBox<String> comboBoxFinitureInterni = getComboBox_FinitureInterni();
		comboBoxFinitureInterni.setEnabled(false);
		JComboBox<String> comboBoxColoreInterni = getComboBox_ColoreInterni();
		comboBoxColoreInterni.setEnabled(false);
		JComboBox<String> comboBoxMotore = getComboBox_Motore();
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
	private void popolaModelloVeicolo(String marcaVeicolo, JComboBox currentComboboxModello, JComboBox currentComboboxVersione) throws HttpCommunicationException {
		
		marcaVeicolo = marcaVeicolo.toLowerCase().trim().replace(" ", "-");
		
		Date now = new Date(); 
		String queryUrl = "http://www.carqueryapi.com/api/0.3/?cmd=getModels&make=" + marcaVeicolo + "&sold_in_us=&_=" + now.getTime();
		Object[] response = null;
		JSONObject json = null;
		
		String modelloAttuale = null;
		
		currentComboboxModello.removeAllItems();
		currentComboboxVersione.removeAllItems();
		
		HttpPortalGetConnection getModelloVeicolo = new HttpPortalGetConnection();
		try {
			response = getModelloVeicolo.get("GET della marca veicolo per ottenere il modello", queryUrl, true);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	String responseBody = (String)response[1];
        	
		try {
			json = new JSONObject(responseBody);
		} catch (ParseException e) {
			throw new HttpCommunicationException(e);
		}
    	JSONArray jsonResults = json.getJSONArray("Models");    
    	currentComboboxModello.addItem("Seleziona");
    	for(int i=0; i<jsonResults.length(); i++) {
        	JSONObject currentJson = jsonResults.getJSONObject(i);
        	modelloAttuale = currentJson.getString("model_name");
        	currentComboboxModello.addItem(modelloAttuale);
    	}	
		
				
	}
	
	//Metodo per popolare la combobox Versione veicolo
	private void popolaVersioneVeicolo(String marcaVeicolo, String modelloVeicolo, JComboBox currentComboboxVersione) throws HttpCommunicationException {
		
		listVersioniVeicoli.clear();
		
		marcaVeicolo = marcaVeicolo.toLowerCase().trim().replace(" ", "-");
		modelloVeicolo = modelloVeicolo.toLowerCase().trim().replace(" ", "+");
		
		Date now = new Date(); 
		
		String queryUrl = "http://www.carqueryapi.com/api/0.3/?cmd=getTrims&make=" + marcaVeicolo + "&year=-1&model=" + modelloVeicolo + "&sold_in_us=&full_results=0&_=" + "&sold_in_us=&_=" + now.getTime();
		Object[] response = null;
		JSONObject json = null;
		
		//JComboBox<String> comboboxVersione = getComboBox_Versione();
		
		String annoFabbricazione = null;
		String nomeVersione = null;
		String nomeModello = null;
		
		currentComboboxVersione.removeAllItems();
		
		HttpPortalGetConnection getVersioneVeicolo = new HttpPortalGetConnection();

		try {
			response = getVersioneVeicolo.get("GET del modello veicolo per ottenere la versione", queryUrl, true);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	String responseBody = (String)response[1];
        	
		try {
			json = new JSONObject(responseBody);
		} catch (ParseException e) {
			throw new HttpCommunicationException(e);
		}
    	JSONArray jsonResults = json.getJSONArray("Trims");
    	currentComboboxVersione.addItem("Seleziona");
    	for(int i=0; i<jsonResults.length(); i++) {
        	JSONObject currentJson = jsonResults.getJSONObject(i);
        	listVersioniVeicoli.add(currentJson.getString("model_id"));
        	annoFabbricazione = currentJson.getString("model_year");
        	nomeVersione = currentJson.getString("model_trim");
        	nomeModello = currentJson.getString("model_name");
        	currentComboboxVersione.addItem(annoFabbricazione + " - " +  nomeVersione + " - " + nomeModello);
    	}	
    	
	}
	
	//Metodo per popolare la combobox Modello veicolo
	private void popolaInfoVeicolo(String modelloVeicolo) throws HttpCommunicationException {
				
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
				
		String tipologiaCarburante = "";
		String postiASedere = "";
		String numeroCV = "";
		String numeroKW = "";
		String posizioneMotore = "";
		String tipologiaCambio = "";
		String cilindrata = "";
		String consumoMedio = "";
				
		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
		try {
			response = getInfoVeicolo.get("GET delle informazioni veicolo", "http://www.carqueryapi.com/api/0.3/?cmd=getModel&model=" + modelloVeicolo + "&_=" + now.getTime(), true);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	String responseBody = (String)response[1];
    	
    	try {
			jsonResults = new JSONArray(responseBody);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   	
    	try {
			json = new JSONObject(jsonResults.getString(0));
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tipologiaCarburante = json.getString("model_engine_fuel")!="null"?json.getString("model_engine_fuel"):"";
		postiASedere = json.getString("model_seats")!="null"?json.getString("model_seats"):"";
		numeroCV = json.getString("model_engine_power_hp")!="null"?json.getString("model_engine_power_hp"):"";
		numeroKW = json.getString("model_engine_power_kw")!="null"?json.getString("model_engine_power_kw"):"";
		posizioneMotore = json.getString("model_engine_position")!="null"?json.getString("model_engine_position"):"";
		tipologiaCambio = json.getString("model_transmission_type")!="null"?json.getString("model_transmission_type"):"";
		cilindrata = json.getString("model_engine_cc")!="null"?json.getString("model_engine_cc"):"";
		consumoMedio = json.getString("model_lkm_mixed")!="null"?json.getString("model_lkm_mixed"):"";
		
		switch (tipologiaCarburante)
		{
		    case "Gasoline": 
		    	comboboxCarburante.setSelectedItem("Benzina");
		        break;
		    case "Diesel":
		    	comboboxCarburante.setSelectedItem("Diesel");
		        break;
		    default:
		    	comboboxCarburante.setSelectedIndex(0);
		}
		
		comboboxPostiASedere.setSelectedItem(postiASedere);
		
		txtFieldCV.setText(numeroCV);
		
		txtFieldKW.setText(numeroKW);
		
		switch (posizioneMotore)
		{
		    case "Front": 
		    	comboboxCarburante.setSelectedItem("Anteriore");
		        break;
		    default:
		    	comboboxCarburante.setSelectedIndex(0);
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
		
		txtFieldCilindrata.setText(cilindrata);
		
		txtFieldConsumoMedio.setText(consumoMedio);
	
	}

	//Metodo per selezionare una immmagine
	private void selezionaImmagine(JLabel labelImmagine, File immagine) {
		JFileChooser dlgFile;
        
		//Selezione del file immagine
        dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	
        	File selectedFile = dlgFile.getSelectedFile(); 
        	imgFile1 = selectedFile;
        	Long fileSize = selectedFile.length();
        	BufferedImage img = null;
        	String selectedFileName = selectedFile.getName().toLowerCase();   
        	
            if(selectedFile.isFile() && selectedFileName.endsWith(format) && fileSize<=maxFileSize) {   	
                selectedFile.getAbsolutePath();
				try {
					img = ImageIO.read(selectedFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                Image resizedimg = img.getScaledInstance(70, 50, Image.SCALE_FAST);          
                Icon icoImmagine = new ImageIcon(resizedimg);
                labelImmagine.setIcon(icoImmagine);
            }
            else {
            	JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("selezioneFileImmagne_SelezioneNonValida"), "Errore", JOptionPane.ERROR_MESSAGE);
            }	
        }
	}
	
	//Resetta la form
	@SuppressWarnings("unchecked")
	private void resettaForm(LinkedList<JComponent> listCampi) {
		
		ListIterator<JComponent> iteratorListCampiForm = listCampi.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = iteratorListCampiForm.next();
			switch (campoCorrente.getClass().getName())
			{
			    case "javax.swing.JTextField": //Campo testuale
			    	((JTextComponent) campoCorrente).setText("");
			    	campoCorrente.setEnabled(true);
			        break;
			    case "javax.swing.JTextPane": //TextPane
			    	((JTextComponent) campoCorrente).setText("");
			    	((JTextComponent) campoCorrente).setEnabled(true);
			        break;
			    case "javax.swing.JComboBox": //Select
			    	if(((JComboBox<String>) campoCorrente).getItemCount()>0) {	//se la checkbox è popolata...
			    		((JComboBox<String>) campoCorrente).setSelectedIndex(0);
			    	}
			    	((JComboBox<String>) campoCorrente).setEnabled(true);
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
			    default://
			}
		}	
			
	}	
	
	//Popola la lista con i campi della form
	private void popolaListaCampiForm() {
		listCampiForm.add(getComboBox_Marca());
		listCampiForm.add(getComboBox_Modello());
		listCampiForm.add(getComboBox_MeseImmatricolazione());
		listCampiForm.add(getComboBox_AnnoImmatricolazione());
		listCampiForm.add(getComboBox_Carburante());
		listCampiForm.add(getComboBox_Tipologia());
		listCampiForm.add(getComboBox_Carrozzeria());
		listCampiForm.add(getComboBox_PostiASedere());
		listCampiForm.add(getComboBox_ColoreEsterno());
		listCampiForm.add(getComboBox_PrecedentiProprietari());
		listCampiForm.add(getComboBox_FinitureInterni());
		listCampiForm.add(getComboBox_ColoreInterni());
		listCampiForm.add(getComboBox_Motore());
		listCampiForm.add(getComboBox_Cambio());
		listCampiForm.add(getComboBox_NumeroRapporti());
		listCampiForm.add(getComboBox_ClasseEmissioni());	
		listCampiForm.add(getComboBox_Versione());
		
		listCampiForm.add(getTextField_Kw());
		listCampiForm.add(getTextField_Cv());
		listCampiForm.add(getTextField_Chilometraggio());
		listCampiForm.add(getTextField_Prezzo());
		listCampiForm.add(getTextField_Cilindrata());
		listCampiForm.add(getTextField_ConsumoMedio());
		listCampiForm.add(getTextField_YouTubeUrl());
		
		listCampiForm.add(getChckbxMetallizzato());
		listCampiForm.add(getChckbxTrattabile());
		listCampiForm.add(getChckbxIvaDeducibile());
		listCampiForm.add(getChckbxAbs());
		listCampiForm.add(getChckbxAirbag());
		listCampiForm.add(getChckbxAntifurto());
		listCampiForm.add(getChckbxChiusuraCentralizzata());
		listCampiForm.add(getChckbxContrAutomTrazione());
		listCampiForm.add(getChckbxEsp());
		listCampiForm.add(getChckbxImmobilizer());
		listCampiForm.add(getChckbxFreniADisco());
		listCampiForm.add(getChckbxCupolino());
		listCampiForm.add(getChckbxAlzacristalliElettrici());
		listCampiForm.add(getChckbxClima());
		listCampiForm.add(getChckbxNavigatoreSatellitare());
		listCampiForm.add(getChckbxRadiolettoreCd());
		listCampiForm.add(getChckbxParkDistControl());
		listCampiForm.add(getChckbxSediliRiscaldati());
		listCampiForm.add(getChckbxServosterzo());
		listCampiForm.add(getChckbxVolanteMultifunzione());
		listCampiForm.add(getChckbxHandicap());
		listCampiForm.add(getChckbxCerchiInLega());
		listCampiForm.add(getChckbxGancioTraino());
		listCampiForm.add(getChckbxPortapacchi());
		listCampiForm.add(getChckbxSediliSportivi());
		listCampiForm.add(getChckbxBauletto());
		listCampiForm.add(getChckbxAvviamentoAPedale());
		listCampiForm.add(getChckbxAvviamentoElettrico());
		
		listCampiForm.add(getTextPane_Descrizione());
		
		listCampiForm.add(getLabel_Immagine1());
		listCampiForm.add(getLabel_Immagine2());
		listCampiForm.add(getLabel_Immagine3());
		listCampiForm.add(getLabel_Immagine4());
		listCampiForm.add(getLabel_Immagine5());
		listCampiForm.add(getLabel_Immagine6());
		listCampiForm.add(getLabel_Immagine7());
		listCampiForm.add(getLabel_Immagine8());
		listCampiForm.add(getLabel_Immagine9());
		listCampiForm.add(getLabel_Immagine10());
	}
	
	//Popola la lista con i campi della form
	private void popolaListaCampiFormCliente() {
		listCampiFormCliente.add(formCliente_getMarcaVeicolo());
		listCampiFormCliente.add(formCliente_getModelloVeicolo());
		listCampiFormCliente.add(formCliente_getVersioneVeicolo());
		listCampiFormCliente.add(formCliente_getTipologiaCarburanteVeicolo());
		listCampiFormCliente.add(formCliente_getColoreVeicolo());
		
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
	
	//Controlla se la form è compilata corettamente
	private boolean isFormValid() {
		return true;
	}
	
	//Il nuovo oggetto scheda immobile viene inserito nella struttura dati e salvato nel file .dat relativo a tutte le schede
	static void aggiungiSchedaVeicolo(SchedaVeicolo scheda) {
	
		//Aggiorno la lista delle schede immobile
		listSchedeVeicolo.add(scheda);
			
		//Aggiorno il file dat delle schede
		j2web.salvaListaSchedeVeicoloCreate();
		
	}
	
	//Il nuovo oggetto scheda cliente viene inserito nella struttura dati e salvato nel file .dat relativo a tutte le schede
	static void aggiungiScheda(SchedaCliente scheda) {
	
		//Aggiorno la lista delle schede immobile
		listSchedeCliente.add(scheda);
			
		//Aggiorno il file dat delle schede
		j2web.salvaListaSchedeClienteCreate();
		
	}
	
	//I metodi che espongono elementi della GUI
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
	protected static JRadioButton getRdbtnAutoveicolo() {
		return rdbtnAutoveicolo;
	}
	protected static JRadioButton getRdbtnMotoScooter() {
		return rdbtnMotoScooter;
	}
	protected static JComboBox<String> getComboBox_Carburante() {
		return comboBox_Carburante;
	}
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
	protected static JComboBox<String> getComboBox_Versione() {
		return comboBox_Versione;
	}
	protected static JComboBox<String> getComboBox_MeseImmatricolazione() {
		return comboBox_MeseImmatricolazione;
	}
	protected static JComboBox<String> getComboBox_AnnoImmatricolazione() {
		return comboBox_AnnoImmatricolazione;
	}
	protected static JTextField getTextField_Kw() {
		return txtFieldKw;
	}
	protected static JTextField getTextField_Cv() {
		return txtFieldCv;
	}
	protected static JComboBox<String> getComboBox_ColoreEsterno() {
		return comboBox_ColoreEsterno;
	}
	protected static JCheckBox getChckbxMetallizzato() {
		return chckbxMetallizzato;
	}
	protected static JComboBox<String> getComboBox_PrecedentiProprietari() {
		return comboBox_PrecedentiProprietari;
	}
	protected static JTextField getTextField_Chilometraggio() {
		return textField_Chilometraggio;
	}
	protected static JTextField getTextField_Prezzo() {
		return textField_Prezzo;
	}
	protected static JCheckBox getChckbxTrattabile() {
		return chckbxTrattabile;
	}
	protected static JCheckBox getChckbxIvaDeducibile() {
		return chckbxIvaDeducibile;
	}
	protected static JComboBox<String> getComboBox_Cambio() {
		return comboBox_Cambio;
	}
	protected static JComboBox<String> getComboBox_NumeroRapporti() {
		return comboBox_NumeroRapporti;
	}
	protected static JTextField getTextField_ConsumoMedio() {
		return comboBox_ConsumoMedio;
	}
	protected static JComboBox<String> getComboBox_ClasseEmissioni() {
		return comboBox_ClasseEmissioni;
	}
	protected static JTextField getTextField_Cilindrata() {
		return comboBox_Cilindrata;
	}
	protected static JTextField getTextField_YouTubeUrl() {
		return txtField_YouTubeUrl;
	}
	protected static JTextPane getTextPane_Descrizione() {
		return textPane_Descrizione;
	}
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
        ListIterator<PortaleWeb> iterator = listPortaliImmobiliari.listIterator();
        while(iterator.hasNext()) {
        	final PortaleWeb portaleCorrente = iterator.next();
        	InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente);
        	pannelloListaPortali.add(inserimentoPortale);
        } 
		
	}

	//Disegna il pannello dei portali di sincronizzazione nella sua configurazione attiva (scheda selezionata)
	static void panelInserimentoInActiveMode(JPanel pannelloListaPortali, final SchedaVeicolo scheda, final boolean selectAllSelected) {
		
		pannelloListaPortali.removeAll();
      	
		pannelloListaPortali.add(Box.createVerticalStrut(10));

    	//Ciclo ogni oggetto PortaleWeb presente nella lista concatenata e per ognuno aggiorno il sottopannello
        ListIterator<PortaleWeb> iterator = J2Web_UI.listPortaliImmobiliari.listIterator();
     	while(iterator.hasNext()) {	     		
     		final PortaleWeb portaleCorrente = iterator.next();
     		InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente, scheda, selectAllSelected);
     		pannelloListaPortali.add(inserimentoPortale);
     	}
     	 
    	pannelloListaPortali.updateUI();
		
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
		return comboBox_TipologiaCliente_Cliente;
	}
	protected static JComboBox<String> formCliente_getColoreVeicolo() {
		return comboBox_Colore_Cliente;
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
}


//Questa classe definisce tutti i sottopannelli schede veicolo
class PanelSchedaVeicolo extends JPanel {   
	
	JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
	private static final long serialVersionUID = 1L;
	
	SchedaVeicolo scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaVeicolo(final SchedaVeicolo scheda, final LinkedList<SchedaVeicolo> listaSchedeVeicolo, final ButtonGroup radioGrpSchede) {
		 this.scheda = scheda;
		 idScheda = scheda.idScheda;
		 codiceScheda = scheda.codiceScheda;
		 
		 setLayout(new BorderLayout(0, 0));
		 setBorder(new LineBorder(Color.LIGHT_GRAY));
		 setMaximumSize(new Dimension(400, 130));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton("Seleziona scheda");
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
           public void actionPerformed(ActionEvent e) {
               System.out.println("Scheda selezionata: " + scheda.marcaVeicolo); 
               
               //Devo caricare la relativa hashtable contenente i portali in cui è inserita e gli associati codici di inserimento
               scheda.caricaTabellaHash();
               
               //Il pannello di destra (inserimento) deve essere aggiornato
               J2Web_UI.panelInserimentoInActiveMode(pannelloListaPortali, scheda, true);
               
               Component[] wrapperScheda = getParent().getComponents();
               for(int i=0; i<wrapperScheda.length; i++) {
            	   if(wrapperScheda[i].getClass().toString().contains("PanelSchedaVeicolo"))  {
            		   ((JComponent) wrapperScheda[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
            	   }
               }
               
               setBorder(new LineBorder(Color.ORANGE));
               
               matchVeicoloCliente(scheda);
           }
		 });
		 add(schedaRadio, BorderLayout.NORTH);
		 
		 //La label delle schede
		 String labelScheda = scheda.marcaVeicolo + "-" + scheda.modelloVeicolo + "-" + scheda.versioneVeicolo + "-" + scheda.carrozzeriaVeicolo + "-" + scheda.coloreEsternoVeicolo + "-" + scheda.descrizioneVeicolo;
		 if(labelScheda.length()>31) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 30); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.LEFT);
		 //System.out.println("test: " + scheda.imgFile1);
		 
		 BufferedImage imgtest = null;
		 if(scheda.arrayImages[1]!=null) {
			 try {
					imgtest = ImageIO.read(scheda.arrayImages[1]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Image resizedimg = imgtest.getScaledInstance(70, 50, Image.SCALE_FAST);          
	         Icon icoImmagine = new ImageIcon(resizedimg);
	         
			 label.setIcon(new ImageIcon(resizedimg));
		 }
		
		 
		 
		 add(label, BorderLayout.CENTER);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
		 JPanel panel_26 = new JPanel();
		 panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 add(panel_26, BorderLayout.SOUTH);
		 panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
		 //Pulsante "Cancella"
		 btnCancellaScheda = new JButton("Cancella");
		 btnCancellaScheda.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              System.out.println("Cancella scheda veicolo: " + scheda.marcaVeicolo);
              
              //Rimozione di una scheda immobile dalla LinkedList
              ListIterator<SchedaVeicolo> iterator = listaSchedeVeicolo.listIterator();
          		while(iterator.hasNext()) {
	          		SchedaVeicolo schedaCorrente = iterator.next();
	          		//La rimozione avviene confrontando l'id univoco della scheda immobile
	          		if(schedaCorrente.idScheda==idScheda) {
	          			iterator.remove();
	          			System.out.println("Scheda veicolo rimossa dalla linkedlist");
	          		}
          		}
          	
	          	//Aggiorno il file dat delle schede
	          	j2web.salvaListaSchedeVeicoloCreate();
          	          	
	          	//Eliminazione del file dat con la hashtable
	          	File removeFile = new File("./schede/" + idScheda + "-" + idScheda + ".dat");
	          	System.out.println("File da rimuovere: " + removeFile.getName());
	          	if(removeFile.exists()) {
	          		removeFile.setWritable(true);
	          		boolean rimosso = removeFile.delete();
	          		System.out.println("File rimosso: " + removeFile.getName() + " " + rimosso);
	          	}
	          	else {
	          		System.out.println("File non esistente: " + removeFile.getName());
	          	}
          	            	
	          	//Aggiornamento del pannello centrale, la scheda corrente è stata cancellata
	          	//j2web_GUI.panelListaSchedeImmobile.updatePanello();
	          	J2Web_UI.aggiornaPannelloListaSchedeVeicolo();
          	
	          	//Aggiornamento del pannello di destra, la scheda corrente è stata cancellata
	          	//j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello();
	          	J2Web_UI.aggiornaPannelloListaPortaliSincronizzazione();
          	}
		 });
		 panel_26.add(btnCancellaScheda);
       
       //add(new JLabel(labelSpaziatore));
		//Component horizontalGlue = Box.createHorizontalGlue();
		//panel_26.add(horizontalGlue);
       
       
     //Pulsante "Esporta"
	 /*btnEsportaScheda = new JButton("Esporta in XML");
	 btnEsportaScheda.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Esporta scheda: " + scheda.marcaVeicolo);
        
    	            	
    }
 });
	 panel_26.add(btnEsportaScheda);*/
	 }
	
	 private void  matchVeicoloCliente(SchedaVeicolo schedaVeicolo) {
		 
		 JPanel pannelloMatchVeicoloCliente = J2Web_UI.getPanel_4();
		 
		 J2Web_UI.listSchedeClientiMatch.clear();
			
		 pannelloMatchVeicoloCliente.removeAll();
		 
		 //pannelloMatchVeicoloCliente.updateUI();
			
			//pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(7));
		 
		 boolean matchPositivo = false;
		 
		 
			ListIterator<SchedaCliente> iterator = J2Web_UI.listSchedeCliente.listIterator();
	     	while(iterator.hasNext()) {
	     		SchedaCliente schedaCorrente = iterator.next();
	     		System.out.println("test: " + schedaVeicolo.modelloVeicolo + schedaCorrente.modelloVeicoloCliente);
	     		if(schedaVeicolo.modelloVeicolo.equalsIgnoreCase(schedaCorrente.modelloVeicoloCliente)) {
	     			J2Web_UI.listSchedeClientiMatch.add(schedaCorrente);
	     		}
	     		
	     	}
	     	if(!J2Web_UI.listSchedeClientiMatch.isEmpty()){	
	     		matchPositivo = true;
	     	}
			
			
			if(!matchPositivo) {
				//Match negativo
				System.out.println("Non è stato trovato alcun cliente interessato al veicolo.");
	    		JPanel panelNessunMatch = new JPanel();
	            JLabel lblNessunMatch = new JLabel("Non è stato trovato alcun cliente interessato al veicolo.");                
	            panelNessunMatch.add(lblNessunMatch);
	            pannelloMatchVeicoloCliente.add(panelNessunMatch);
	    	}    	
	    	else {
	    		//Match positivo
	    		System.out.println("Trovati potenziali clienti  interessati al veicolo");
	    		
	    		String[][] matrix = new String[J2Web_UI.listSchedeClientiMatch.size()][5];
	    		
	    		for (int row = 0; row < matrix.length; row++) {
	    	       // for (int column = 0; column < matrix[row].length; column++)
	    	            matrix[row][0] = J2Web_UI.listSchedeClientiMatch.get(row).nomeCliente;
	    	            matrix[row][1] = J2Web_UI.listSchedeClientiMatch.get(row).cognomeCliente;
	    	            matrix[row][2] = J2Web_UI.listSchedeClientiMatch.get(row).emailCliente;
	    	            matrix[row][3] = J2Web_UI.listSchedeClientiMatch.get(row).telefono1Cliente;
	    	            matrix[row][4] = J2Web_UI.listSchedeClientiMatch.get(row).telefono2Cliente;
	    	    }
	    		
	    		JTable table = new JTable();
	    		table.setModel(new DefaultTableModel(
	    				matrix,
	    			new String[] {
	    				"Nome", "Cognome", "Email", "Telefono 1", "Telefono 2"
	    			}
	    		));
	    		pannelloMatchVeicoloCliente.add(table);
	    		
	    		
	    		
	    	}
	 }
}



//Questa classe definisce tutti i sottopannelli schede veicolo
class PanelSchedaVeicoloMLS extends JPanel {   
	
	JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
	private static final long serialVersionUID = 1L;
	
	SchedaVeicolo scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaVeicoloMLS(final SchedaVeicolo scheda) {
		 this.scheda = scheda;
		 idScheda = scheda.idScheda;
		 codiceScheda = scheda.codiceScheda;
		 
		 setLayout(new BorderLayout(0, 0));
		 setBorder(new LineBorder(Color.LIGHT_GRAY));
		 setMaximumSize(new Dimension(400, 130));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton("Seleziona scheda");
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 //radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
         public void actionPerformed(ActionEvent e) {
             System.out.println("Scheda selezionata: " + scheda.marcaVeicolo); 
             
             
             Component[] wrapperScheda = getParent().getComponents();
             for(int i=0; i<wrapperScheda.length; i++) {
          	   if(wrapperScheda[i].getClass().toString().contains("PanelSchedaVeicolo"))  {
          		   ((JComponent) wrapperScheda[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
          	   }
             }
             
             setBorder(new LineBorder(Color.ORANGE));
             
             mostraDettagliVeicoloMLS(scheda);
         }
		 });
		 add(schedaRadio, BorderLayout.NORTH);
		 
		 //La label delle schede
		 String labelScheda = scheda.marcaVeicolo + "-" + scheda.modelloVeicolo + "-" + scheda.versioneVeicolo + "-" + scheda.carrozzeriaVeicolo + "-" + scheda.coloreEsternoVeicolo + "-" + scheda.descrizioneVeicolo;
		 if(labelScheda.length()>31) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 30); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.LEFT);
		 
		 BufferedImage imgtest = null;
		 if(scheda.arrayImages[1]!=null) {
			 try {
					imgtest = ImageIO.read(scheda.arrayImages[1]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
			 Image resizedimg = imgtest.getScaledInstance(70, 50, Image.SCALE_FAST);          
	         Icon icoImmagine = new ImageIcon(resizedimg);
	         
			 label.setIcon(new ImageIcon(resizedimg));
		 }
		
		 
		 
		 add(label, BorderLayout.CENTER);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
		 
	 }
	
	 private void  mostraDettagliVeicoloMLS(SchedaVeicolo schedaVeicolo) {
		 
		 JPanel pannelloInfoVeicoloMLS = J2Web_UI.getPanel_8();
		 
		 //J2Web_UI.listSchedeClientiMatch.clear();
			
		 pannelloInfoVeicoloMLS.removeAll();
		 
		 //pannelloMatchVeicoloCliente.updateUI();
			
			//pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(7));
		 
		 
			
	
	    		
	    		System.out.println("Info veicolo selezionato...");
	    		
	    		String[][] matrix = new String[1][3];
	    		
	    		for (int row = 0; row < matrix.length; row++) {
	    	       // for (int column = 0; column < matrix[row].length; column++)
	    	            matrix[row][0] = schedaVeicolo.marcaVeicolo;
	    	            matrix[row][1] = schedaVeicolo.modelloVeicolo;
	    	            matrix[row][2] = schedaVeicolo.versioneVeicolo;
	    	            //matrix[row][3] = J2Web_UI.listSchedeClientiMatch.get(row).telefono1Cliente;
	    	            //matrix[row][4] = J2Web_UI.listSchedeClientiMatch.get(row).telefono2Cliente;
	    	    }
	    		
	    		JTable table = new JTable();
	    		table.setModel(new DefaultTableModel(
	    				matrix,
	    			new String[] {
	    				"Marca", "Modello", "Versione"
	    			}
	    		));
	    		pannelloInfoVeicoloMLS.add(table);
	    		
	    		
	    		
	    	
	 }
}




//Questa classe definisce tutti i sottopannelli schede cliente
class PanelSchedaCliente extends JPanel {   
	//JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
	private static final long serialVersionUID = 1L;
	
	SchedaCliente scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaCliente(final SchedaCliente scheda, final LinkedList<SchedaCliente> listaSchedeCliente, final ButtonGroup radioGrpSchede) {
		 this.scheda = scheda;
		 idScheda = scheda.idSchedaCliente;
		 codiceScheda = scheda.codiceSchedaCliente;
		 
		 setLayout(new BorderLayout(0, 0));
		 setBorder(new LineBorder(Color.LIGHT_GRAY));
		 setMaximumSize(new Dimension(400, 130));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton("Seleziona scheda");
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
           public void actionPerformed(ActionEvent e) {
               System.out.println("Scheda selezionata: " + scheda.marcaVeicoloCliente); 
               
               Component[] test = getParent().getComponents();
               for(int i=0; i<test.length; i++) {
            	   if(test[i].getClass().toString().contains("PanelSchedaCliente"))  {
            		   ((JComponent) test[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
            	   }
               }
               
               setBorder(new LineBorder(Color.ORANGE));
               
               matchClienteVeicolo(scheda);
           }
		 });
		 add(schedaRadio, BorderLayout.NORTH);
		 
		 //La label delle schede
		 String labelScheda = scheda.nomeCliente + "-" + scheda.cognomeCliente + "-" + scheda.telefono1Cliente /*+ "-" + scheda.comune + "-" + scheda.regione + "-" + scheda.testoAnnuncio*/;
		 if(labelScheda.length()>16) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 15); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.LEFT);
		 label.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\imaginationLogo.png"));
		 add(label, BorderLayout.CENTER);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
		 JPanel panel_26 = new JPanel();
		 panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 add(panel_26, BorderLayout.SOUTH);
		 panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
		 //Pulsante "Cancella"
		 btnCancellaScheda = new JButton("Cancella");
		 btnCancellaScheda.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              System.out.println("Cancella scheda cliente: " + scheda.marcaVeicoloCliente);
              
              //Rimozione di una scheda immobile dalla LinkedList
              ListIterator<SchedaCliente> iterator = listaSchedeCliente.listIterator();
          		while(iterator.hasNext()) {
	          		SchedaCliente schedaCorrente = iterator.next();
	          		//La rimozione avviene confrontando l'id univoco della scheda immobile
	          		if(schedaCorrente.idSchedaCliente==idScheda) {
	          			iterator.remove();
	          			System.out.println("Scheda cliente rimossa dalla linkedlist");
	          		}
          		}
          	
	          	//Aggiorno il file dat delle schede
	          	j2web.salvaListaSchedeClienteCreate();
          	          	
	          	
          	            	
	          	//Aggiornamento del pannello centrale, la scheda corrente è stata cancellata
	          	//j2web_GUI.panelListaSchedeImmobile.updatePanello();
	          	J2Web_UI.aggiornaPannelloListaSchedeCliente();
          	
          	}
		 });
		 panel_26.add(btnCancellaScheda);
       
       //add(new JLabel(labelSpaziatore));
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_26.add(horizontalGlue);
       
       
	 }

	 
	 private void  matchClienteVeicolo(SchedaCliente schedaCliente) {
		 
		 JPanel pannelloMatchClienteVeicolo = J2Web_UI.getPanel_6();
		 
		 J2Web_UI.listSchedeVeicoliMatch.clear();
			
		 pannelloMatchClienteVeicolo.removeAll();
		 
		 //pannelloMatchVeicoloCliente.updateUI();
			
			//pannelloListaSchedeVeicolo.add(Box.createVerticalStrut(7));
		 
		 boolean matchPositivo = false;
		 
		 
			ListIterator<SchedaVeicolo> iterator = J2Web_UI.listSchedeVeicolo.listIterator();
	     	while(iterator.hasNext()) {
	     		SchedaVeicolo schedaCorrente = iterator.next();
	     		System.out.println("test: " + schedaCliente.modelloVeicoloCliente + schedaCorrente.modelloVeicolo);
	     		if(schedaCliente.modelloVeicoloCliente.equalsIgnoreCase(schedaCorrente.modelloVeicolo)) {
	     			J2Web_UI.listSchedeVeicoliMatch.add(schedaCorrente);
	     		}
	     		
	     	}
	     	if(!J2Web_UI.listSchedeVeicoliMatch.isEmpty()){	
	     		matchPositivo = true;
	     	}
			
			
			if(!matchPositivo) {
				//Match negativo
				System.out.println("Non è stato trovato alcun veicolo intteressante per il cliente.");
	    		JPanel panelNessunMatch = new JPanel();
	            JLabel lblNessunMatch = new JLabel("Non è stato trovato alcun veicolo intteressante per il cliente.");                
	            panelNessunMatch.add(lblNessunMatch);
	            pannelloMatchClienteVeicolo.add(panelNessunMatch);
	    	}    	
	    	else {
	    		//Match positivo
	    		System.out.println("Trovati potenziali veicoli interessanti per il cliente");
	    		
	    		String[][] matrix = new String[J2Web_UI.listSchedeVeicoliMatch.size()][5];
	    		
	    		for (int row = 0; row < matrix.length; row++) {
	    	       // for (int column = 0; column < matrix[row].length; column++)
	    	            matrix[row][0] = J2Web_UI.listSchedeVeicoliMatch.get(row).marcaVeicolo;
	    	            matrix[row][1] = J2Web_UI.listSchedeVeicoliMatch.get(row).modelloVeicolo;
	    	            matrix[row][2] = J2Web_UI.listSchedeVeicoliMatch.get(row).versioneVeicolo;
	    	            matrix[row][3] = J2Web_UI.listSchedeVeicoliMatch.get(row).coloreEsternoVeicolo;
	    	            matrix[row][4] = J2Web_UI.listSchedeVeicoliMatch.get(row).prezzoVeicolo;
	    	    }
	    		
	    		JTable table = new JTable();
	    		table.setModel(new DefaultTableModel(
	    				matrix,
	    			new String[] {
	    				"Marca", "Modello", "Versione", "Colore", "Prezzo"
	    			}
	    		));
	    		pannelloMatchClienteVeicolo.add(table);
	    		
	    		
	    		
	    	}
	 }
}





class PanelSchedaClienteMLS extends JPanel {   
	//JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
	private static final long serialVersionUID = 1L;
	
	SchedaCliente scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;
	
	String labelSpaziatore = "   "; 
	
	 public PanelSchedaClienteMLS(final SchedaCliente scheda, final LinkedList<SchedaCliente> listaSchedeCliente, final ButtonGroup radioGrpSchede) {
		 this.scheda = scheda;
		 idScheda = scheda.idSchedaCliente;
		 codiceScheda = scheda.codiceSchedaCliente;
		 
		 setLayout(new BorderLayout(0, 0));
		 setBorder(new LineBorder(Color.LIGHT_GRAY));
		 setMaximumSize(new Dimension(600, 130));
		 	
		 //Radio button dei sottopannelli
		 schedaRadio = new JRadioButton("Seleziona scheda");
		 //Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		 radioGrpSchede.add(schedaRadio); 
		 //Clicco su una radio button di una scheda
		 schedaRadio.addActionListener(new ActionListener() {			 
           public void actionPerformed(ActionEvent e) {
               System.out.println("Scheda selezionata: " + scheda.marcaVeicoloCliente + " per MLS..."); 
               
               JPanel pannelloInfoVeicoloMLS = J2Web_UI.getPanel_8();
               pannelloInfoVeicoloMLS.removeAll();
               pannelloInfoVeicoloMLS.updateUI();
               
               Component[] test = getParent().getComponents();
               for(int i=0; i<test.length; i++) {
            	   if(test[i].getClass().toString().contains("PanelSchedaCliente"))  {
            		   ((JComponent) test[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
            	   }
               }
               
               setBorder(new LineBorder(Color.ORANGE));
               
               matchClienteVeicoloMLS(scheda);
               
           }
		 });
		 add(schedaRadio, BorderLayout.NORTH);
		 
		 //La label delle schede
		 String labelScheda = scheda.nomeCliente + "-" + scheda.cognomeCliente + "-" + scheda.telefono1Cliente /*+ "-" + scheda.comune + "-" + scheda.regione + "-" + scheda.testoAnnuncio*/;
		 if(labelScheda.length()>16) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 15); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.LEFT);
		 label.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\imaginationLogo.png"));
		 add(label, BorderLayout.CENTER);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
		 JPanel panel_26 = new JPanel();
		 panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 add(panel_26, BorderLayout.SOUTH);
		 panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
       
       //add(new JLabel(labelSpaziatore));
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_26.add(horizontalGlue);
       
       
	 }
	 
	 private void  matchClienteVeicoloMLS(SchedaCliente schedaCliente) {
		 
		 JPanel pannelloMatchClienteVeicoloMLS = J2Web_UI.getPanel_15();
		 
		 pannelloMatchClienteVeicoloMLS.removeAll();
		 
		  Connection con = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;

	        String url = "jdbc:mysql://localhost:3306/veicoli";
	        String user = "testuser";
	        String password = "test623";
	        
	        
	        try {
	            
	            con = DriverManager.getConnection(url, user, password);
	            pst = con.prepareStatement("SELECT * FROM Autoveicoli WHERE Marca = " + "'" + scheda.marcaVeicoloCliente + "'");
	            rs = pst.executeQuery();

	            while (rs.next()) {
	                System.out.print(rs.getInt(1));
	                System.out.print(": ");
	                System.out.println(rs.getString(2));
	                System.out.print(rs.getString(3));
	                System.out.print(": ");
	                System.out.println(rs.getString(4));
	                
	                
	                SchedaVeicolo schedaVeicoloMLS = new SchedaVeicolo(rs);
	                
	                PanelSchedaVeicoloMLS panelSchedaVeicoloMLS = new PanelSchedaVeicoloMLS(schedaVeicoloMLS);
	                
	                pannelloMatchClienteVeicoloMLS.add(panelSchedaVeicoloMLS);
	                
	            }

	        } catch (SQLException ex) {
	                Logger lgr = Logger.getLogger(PanelSchedaClienteMLS.class.getName());
	                lgr.log(Level.SEVERE, ex.getMessage(), ex);

	        } finally {

	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (pst != null) {
	                    pst.close();
	                }
	                if (con != null) {
	                    con.close();
	                }

	            } catch (SQLException ex) {
	                Logger lgr = Logger.getLogger(PanelSchedaClienteMLS.class.getName());
	                lgr.log(Level.WARNING, ex.getMessage(), ex);
	            }
	        }
	        
	        pannelloMatchClienteVeicoloMLS.updateUI();
		 
		 
	 }

}




//La classe che definisce il pannello per l'inserimento sequenziale
class PanelInserimentoSequenzialeSchede extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	String labelBtnInserisciTutti = "<html><p style=\"text-align:center;\">Inserisci scheda <br/>nei portali selezionati</p></html>";
	String labelBtnCancellaTutti = "<html><p style=\"text-align:center;\">Cancella scheda <br/> dai portali selezionati</p></html>";
		String labelCheckboxSelezionaTutti = "<html><div style=\"text-align:center;\">Seleziona tutti</div></html>";
	
	JButton btnInserisciTuttiIPortali;
	JButton btnCancellaTuttiIPortali;
	JCheckBox checkboxSelezionaTutti;
	
	String labelSpaziatore = "   "; 
	
	//Costruttore 1
	//Pannello inserimento sequenziali nel caso di nessun portale selezionato
	public PanelInserimentoSequenzialeSchede() {
		
		btnInserisciTuttiIPortali = new JButton(labelBtnInserisciTutti);
		btnCancellaTuttiIPortali = new JButton(labelBtnCancellaTutti);
		checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
  	
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));  
		
		add(Box.createVerticalStrut(20));
      
	      //Pulsante inserisci tutti i selezionati
	      btnInserisciTuttiIPortali.setEnabled(false);
	      add(btnInserisciTuttiIPortali);
      
      //Pulsante elimina tutti i selezionati
      btnCancellaTuttiIPortali.setEnabled(false);
      add(btnCancellaTuttiIPortali);
      
      add(new JLabel(labelSpaziatore)); 
      
      //Checkbox seleziona tutti              
      checkboxSelezionaTutti.setEnabled(false);
      add(checkboxSelezionaTutti);      		
                      
      add(Box.createVerticalStrut(20));	//spaziatore tra il pannello superiore e i vari pannelli portale
      
	}
	
	//Costruttore 2
	//Pannello inserimento sequenziali nel caso di portale selezionato
	public PanelInserimentoSequenzialeSchede(final SchedaVeicolo scheda, boolean selectAllSelected) {
		
		btnInserisciTuttiIPortali = new JButton(labelBtnInserisciTutti);
  	btnCancellaTuttiIPortali = new JButton(labelBtnCancellaTutti);
  	checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
  	
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(Box.createVerticalStrut(20));
		
		//Inserisce la scheda in tutti i portali selezionati
		btnInserisciTuttiIPortali.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {  
          	if(!j2web_GUI.listPortaliInserimentoSequenziale.isEmpty()) {
          		ListIterator<PortaleWeb> iterator = (ListIterator<PortaleWeb>) j2web_GUI.listPortaliInserimentoSequenziale.iterator();
              	String rapportoInserimentiOK = "";
              	String rapportoInserimentiKO = "";
              	//Il cursone viene messo in modalità attesa
   				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
              	while(iterator.hasNext()) {
              		PortaleWeb portaleCorrente = iterator.next();          		
              		try {
       					System.out.println("Inserimento della scheda " + scheda.codiceScheda + " in " + portaleCorrente.idPortale);
       					boolean schedaInserita = portaleCorrente.inserisciScheda(scheda, true);
       					if(schedaInserita) {
       						System.out.println("Scheda " + scheda.idScheda + " inserita in: " + portaleCorrente.idPortale);
       						rapportoInserimentiOK += "\n   " + portaleCorrente.idPortale;
       					}
       					else {
       						System.out.println("Scheda " + scheda.idScheda + "NON inserita in: " + portaleCorrente.idPortale);
       						rapportoInserimentiKO += "\n   " + portaleCorrente.idPortale;
       					} 					
       				}
       				catch (HttpCommunicationException | UnsupportedEncodingException e1 ) { 
       					PanelInserimentoImmobiliInPortali.manageErrorsOnPortalSubmission(e1);
       				}
              	}
              	//Mostro il rapporto di inserimento
              	JOptionPane.showMessageDialog(null, "Rapporto di inserimento: \n\nScheda inserita in: \n" + rapportoInserimentiOK + "\n\nScheda non inserita in: \n" + rapportoInserimentiKO);
              	//Solo alla fine aggiorno i pulsanti del pannello inserimento
          		j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
              	//Il cursone viene messo in modalità standard
   				setCursor(Cursor.getDefaultCursor());
          	}	
          }         
       });
		add(btnInserisciTuttiIPortali);
		    
		//Elimina la scheda da tutti i portali selezionati
      btnCancellaTuttiIPortali.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) { 
          	if(!j2web_GUI.listPortaliCancellazioneSequenziale.isEmpty()) {
	            	ListIterator<PortaleWeb> iterator = (ListIterator<PortaleWeb>) j2web_GUI.listPortaliCancellazioneSequenziale.iterator();
	            	
	            	//Il cursone viene messo in modalità attesa
	 				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	            	while(iterator.hasNext()) {
	            		PortaleWeb portaleCorrente = iterator.next();         		
	            		try {
	            			System.out.println("Cancellazione della scheda " + scheda.codiceScheda
	            					+ " da " + portaleCorrente.idPortale);
	     					portaleCorrente.cancellaScheda(scheda, true);
	     				}
	     				catch (HttpCommunicationException e1 ) {
	     					PanelInserimentoImmobiliInPortali.manageErrorsOnPortalSubmission(e1);
	     				}
	            		System.out.println("Scheda " + scheda.idScheda + " cancellata da: " + portaleCorrente.idPortale);         		            		
	            	}
	            	//Solo alla fine aggiorno i pulsanti del pannello inserimento
	        		j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
	            	//Il cursone viene messo in modalità standard
	 				setCursor(Cursor.getDefaultCursor());
          	}
          }
       });
      add(btnCancellaTuttiIPortali);
		
      add(new JLabel(labelSpaziatore)); 
      
      //Checkbox seleziona tutti
      final JCheckBox checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
      checkboxSelezionaTutti.setSelected(selectAllSelected);
      checkboxSelezionaTutti.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
      	   //Se la checkbox viene spuntata, ridisegno il pannello  passando "true" 
      	   j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, checkboxSelezionaTutti.isSelected());    
         }
		 });
      add(checkboxSelezionaTutti);
      
      add(Box.createVerticalStrut(20));
		
	}

}
