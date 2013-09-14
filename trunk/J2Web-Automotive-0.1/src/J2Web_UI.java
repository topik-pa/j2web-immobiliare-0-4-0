import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import org.apache.http.NameValuePair;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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


public class J2Web_UI implements parametriGenerali{

	private static JFrame frmJwebAutomotive;
	private static JTextField txtFieldKw;
	private static JTextField txtFieldCv;
	private static JTextField textField_Chilometraggio;
	private static JTextField textField_Prezzo;
	private static JComboBox comboBox_Versione;
	private static JComboBox comboBox_Marca;
	private static JComboBox comboBox_Modello;
	private static final ButtonGroup buttonGroup = new ButtonGroup();
	private static JComboBox comboBox_Tipologia;
	private static JComboBox comboBox_Carrozzeria;
	private static JComboBox comboBox_PostiASedere;
	private static JComboBox comboBox_FinitureInterni;
	private static JComboBox comboBox_ColoreInterni;
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
	private static JComboBox comboBox_Motore;
	private static JTextField txtField_YouTubeUrl;
	private static JRadioButton rdbtnAutoveicolo;
	private static JRadioButton rdbtnMotoScooter;
	private static JComboBox comboBox_Carburante;
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
	private static JComboBox comboBox_MeseImmatricolazione;
	private static JComboBox comboBox_AnnoImmatricolazione;
	private static JComboBox comboBox_ColoreEsterno;
	private static JCheckBox chckbxMetallizzato;
	private static JComboBox comboBox_PrecedentiProprietari;
	private static JCheckBox chckbxTrattabile;
	private static JCheckBox chckbxIvaDeducibile;
	private static JComboBox comboBox_Cambio;
	private static JComboBox comboBox_NumeroRapporti;
	private static JTextField comboBox_ConsumoMedio;
	private static JComboBox comboBox_ClasseEmissioni;
	private static JTextField comboBox_Cilindrata;
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
	
	LinkedList<String> listVersioniVeicoli = new LinkedList<String>();

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
					J2Web_UI imagination = new J2Web_UI();
					imagination.frmJwebAutomotive.setVisible(true);
					
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
		initialize();
		selezioneAutoVeicolo();
		popolaListaCampiForm();
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
		frmJwebAutomotive.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\icon_web.png"));
		frmJwebAutomotive.setBounds(100, 100, 1024, 600);
		frmJwebAutomotive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJwebAutomotive.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmJwebAutomotive.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
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
		gbl_panel_3.columnWidths = new int[]{500, 143, 143, 0};
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
				selezioneAutoVeicolo();
			}
		});
		buttonGroup.add(rdbtnAutoveicolo);
		panel_20.add(rdbtnAutoveicolo, "2, 2");
		
		rdbtnMotoScooter = new JRadioButton("Moto/Scooter");
		rdbtnMotoScooter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
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
		
		comboBox_Marca = new JComboBox();
		comboBox_Marca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("marca");
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String marcaVeicolo = (String) comboBox_Marca.getSelectedItem();
					
					if(!marcaVeicolo.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {
						try {
							popolaModelloVeicolo(marcaVeicolo);
						} catch (HttpCommunicationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
				
			}
		});
		comboBox_Marca.setModel(new DefaultComboBoxModel(marcheAutoveicoli));
		panel_20.add(comboBox_Marca, "2, 6, fill, default");
		
		comboBox_Modello = new JComboBox();
		comboBox_Modello.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("modello");
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					String marcaVeicolo = (String) comboBox_Marca.getSelectedItem();
					String modelloVeicolo = (String) comboBox_Modello.getSelectedItem();
					
					if(modelloVeicolo!=null && !modelloVeicolo.equals("Seleziona") && rdbtnAutoveicolo.isSelected()) {
						try {
							popolaVersioneVeicolo(marcaVeicolo, modelloVeicolo);
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
		
		comboBox_Versione = new JComboBox();
		comboBox_Versione.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				System.out.println("versione");
				
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
		
		comboBox_MeseImmatricolazione = new JComboBox();
		comboBox_MeseImmatricolazione.setModel(new DefaultComboBoxModel(new String[] {"Mese", "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"}));
		panel_20.add(comboBox_MeseImmatricolazione, "2, 10, fill, default");
		
		comboBox_AnnoImmatricolazione = new JComboBox();
		comboBox_AnnoImmatricolazione.setModel(new DefaultComboBoxModel(new String[] {"Anno", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"}));
		panel_20.add(comboBox_AnnoImmatricolazione, "6, 10, fill, default");
		
		comboBox_Carburante = new JComboBox();
		comboBox_Carburante.setModel(new DefaultComboBoxModel(carburantiAutoveicoli));
		panel_20.add(comboBox_Carburante, "10, 10, fill, default");
		
		JLabel lblTipologia = new JLabel("Tipologia");
		panel_20.add(lblTipologia, "2, 12");
		
		JLabel lblCarrozzeria = new JLabel("Carrozzeria");
		panel_20.add(lblCarrozzeria, "6, 12");
		
		JLabel lblPostiASedere = new JLabel("Posti a sedere");
		panel_20.add(lblPostiASedere, "10, 12");
		
		comboBox_Tipologia = new JComboBox();
		comboBox_Tipologia.setModel(new DefaultComboBoxModel(tipologiaAutoveicoli));
		panel_20.add(comboBox_Tipologia, "2, 14, fill, default");
		
		comboBox_Carrozzeria = new JComboBox();
		comboBox_Carrozzeria.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "City car", "Cabrio", "Coupé", "SUV/Fuoristrada", "Station wagon", "Berlina", "Altro", "Monovolume", "Furgoni/Van"}));
		panel_20.add(comboBox_Carrozzeria, "6, 14, fill, default");
		
		comboBox_PostiASedere = new JComboBox();
		comboBox_PostiASedere.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
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
		
		comboBox_ColoreEsterno = new JComboBox();
		comboBox_ColoreEsterno.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "Arancione", "Argento", "Beige", "Bianco", "Blu/Azzurro", "Bronzo", "Giallo", "Grigio", "Lilla", "Marrone", "Nero", "Oro", "Rosso", "Verde"}));
		panel_20.add(comboBox_ColoreEsterno, "2, 22, fill, default");
		
		chckbxMetallizzato = new JCheckBox("Metallizzato");
		panel_20.add(chckbxMetallizzato, "6, 22");
		
		JLabel lblPrecedentiProprietari = new JLabel("Precedenti proprietari");
		panel_20.add(lblPrecedentiProprietari, "2, 24");
		
		JLabel lblChilometraggio = new JLabel("Chilometraggio");
		panel_20.add(lblChilometraggio, "6, 24");
		
		comboBox_PrecedentiProprietari = new JComboBox();
		comboBox_PrecedentiProprietari.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
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
		
		comboBox_FinitureInterni = new JComboBox();
		comboBox_FinitureInterni.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "Alcantara", "Pelle parziale", "Pelle scamosciata", "Pelle totale", "Stoffa", "Altro"}));
		panel_20.add(comboBox_FinitureInterni, "2, 34, fill, default");
		
		comboBox_ColoreInterni = new JComboBox();
		comboBox_ColoreInterni.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "Beige", "Grigio", "Marrone", "Nero", "Altro"}));
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
		
		comboBox_Motore = new JComboBox();
		comboBox_Motore.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "4x4", "6wd", "Anteriore", "Posteriore"}));
		panel_22.add(comboBox_Motore, "2, 4, fill, default");
		
		comboBox_Cambio = new JComboBox();
		comboBox_Cambio.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "Automatico", "Manuale", "Semiautomatico", "Nessuno"}));
		panel_22.add(comboBox_Cambio, "6, 4, fill, default");
		
		comboBox_NumeroRapporti = new JComboBox();
		comboBox_NumeroRapporti.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "3", "4", "5", "6", "7"}));
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
		
		comboBox_ClasseEmissioni = new JComboBox();
		comboBox_ClasseEmissioni.setModel(new DefaultComboBoxModel(new String[] {"Seleziona", "Euro 1", "Euro 2", "Euro 3", "Euro 4", "Euro 5", "Euro 6"}));
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
				resettaForm();
			}
		});
		btnResetta.setIcon(new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\refresh.png"));
		panel_18.add(btnResetta);
		
		JPanel panel_19 = new JPanel();
		panel_17.add(panel_19);
		
		JButton btnInserisci = new JButton("Crea scheda");
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Funzionaliità non ancora gestita", "Errore", JOptionPane.WARNING_MESSAGE);
				
				System.out.print("Creazione della scheda veicolo...");				
				if(isFormValid()) { 
					System.out.println(" ...form valido... ");
					
					//Disabilito i campi della form
					disabilitaCampiForm();
					
					//Istanzio l'oggetto scheda e lo salvo nel file
             	   	SchedaVeicolo schedaVeicolo = new SchedaVeicolo();
             	   	aggiungiScheda(schedaVeicolo);
             	 
             	   	//Il pannello centrale viene ridisegnato
             	    //j2web_GUI.panelListaSchedeImmobile.updatePanello();
             	   
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
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Lista veicoli inseriti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setViewportView(panel_9);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 0;
		panel_3.add(scrollPane_2, gbc_scrollPane_2);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Pubblicazione sui portali Web", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_2.setViewportView(panel_10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		panel.add(scrollPane_3, gbc_scrollPane_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Risultato incrocio Veicolo/Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_3.setViewportView(panel_4);
		panel_4.setBackground(new Color(255, 255, 224));
		
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
		
		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridx = 1;
		gbc_scrollPane_5.gridy = 0;
		panel_5.add(scrollPane_5, gbc_scrollPane_5);
		
		JPanel panel_13 = new JPanel();
		scrollPane_5.setViewportView(panel_13);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridx = 0;
		gbc_scrollPane_6.gridy = 1;
		panel_1.add(scrollPane_6, gbc_scrollPane_6);
		
		JPanel panel_6 = new JPanel();
		scrollPane_6.setViewportView(panel_6);
		panel_6.setBackground(new Color(255, 255, 224));
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Incrocio anagrafiche", new ImageIcon("C:\\Documents and Settings\\user\\workspace\\j2web-automotive-0.1\\images\\icon_db.png"), panel_2, null);
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
		
		JScrollPane scrollPane_9 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_9 = new GridBagConstraints();
		gbc_scrollPane_9.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_9.gridx = 0;
		gbc_scrollPane_9.gridy = 1;
		panel_2.add(scrollPane_9, gbc_scrollPane_9);
		
		JPanel panel_8 = new JPanel();
		scrollPane_9.setViewportView(panel_8);
		panel_8.setBackground(new Color(255, 255, 224));
		
		panel_16.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel_24, rdbtnAutoveicolo, rdbtnMotoScooter, lblMarca, panel_20, lblModello, lblVersione, comboBox_Marca, comboBox_Modello, lblDataPrimaImmatricolazione, lblCarburante, comboBox_MeseImmatricolazione, comboBox_AnnoImmatricolazione, lblTipologia, lblCarrozzeria, lblPostiASedere, comboBox_Tipologia, comboBox_Carrozzeria, comboBox_PostiASedere, lblPotenzaKw, txtFieldKw, txtFieldCv, lblColoreEsterno, comboBox_ColoreEsterno, chckbxMetallizzato, lblPrecedentiProprietari, lblChilometraggio, comboBox_PrecedentiProprietari, textField_Chilometraggio, lblPrezzo, textField_Prezzo, chckbxTrattabile, chckbxIvaDeducibile, lblFinitureInterne, lblColoreInterni, comboBox_FinitureInterni, comboBox_ColoreInterni, lblDescrizionemax, textPane_Descrizione, panel_23, btnImmagine1, label_Immagine1, btnImmagine_2, label_Immagine2, btnImmagine_3, label_Immagine3, btnImmagine_4, label_Immagine4, btnImmagine_5, label_Immagine5, btnImmagine_6, label_Immagine6, btnImmagine_7, label_Immagine7, btnImmagine_8, label_Immagine8, btnImmagine_9, label_Immagine9, btnImmagine_10, label_Immagine10, panel_22, lblNewLabel, lblNewLabel_1, lblNewLabel_2, comboBox_Motore, comboBox_Cambio, comboBox_NumeroRapporti, lblCilindrata, lblClasseDiEmissione, lblConsumoMedio, comboBox_Cilindrata, comboBox_ClasseEmissioni, comboBox_ConsumoMedio, panel_21, lblSicurezza, lblComodit, lblExtra, chckbxAbs, chckbxAlzacristalliElettrici, chckbxHandicap, chckbxAirbag, chckbxClima, chckbxCerchiInLega, chckbxAntifurto, chckbxNavigatoreSatellitare, chckbxGancioTraino, chckbxChiusuraCentralizzata, chckbxRadiolettoreCd, chckbxPortapacchi, chckbxContrAutomTrazione, chckbxParkDistControl, chckbxSediliSportivi, chckbxBauletto, chckbxAvviamentoAPedale, chckbxAvviamentoElettrico, chckbxEsp, chckbxSediliRiscaldati, chckbxImmobilizer, chckbxServosterzo, chckbxFreniADisco, chckbxVolanteMultifunzione, chckbxCupolino}));
		
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
	
	
	protected void disabilitaCampiForm() {
		// TODO Auto-generated method stub
		
	}

	private void selezioneAutoVeicolo() {
				
		//Modifico le opzioni della combobox Marca con le opzioni per i autoveicoli
		JComboBox comboBoxMarca = getComboBox_Marca();
		comboBoxMarca.removeAllItems();
		comboBoxMarca.setModel(new DefaultComboBoxModel<String>(marcheAutoveicoli));
		
		//Svuoto la combobox Modello
		JComboBox comboBoxModello = getComboBox_Modello();
		comboBoxModello.removeAllItems();
		
		//Attivo le combobox che servono nel caso di autoveicolo
		JComboBox comboBoxCarrozzeria = getComboBox_Carrozzeria();
		comboBoxCarrozzeria.setEnabled(true);		
		JComboBox comboBoxPostiASedere = getComboBox_PostiASedere();
		comboBoxPostiASedere.setEnabled(true);
		JComboBox comboBoxFinitureInterni = getComboBox_FinitureInterni();
		comboBoxFinitureInterni.setEnabled(true);
		JComboBox comboBoxColoreInterni = getComboBox_ColoreInterni();
		comboBoxColoreInterni.setEnabled(true);
		JComboBox comboBoxMotore = getComboBox_Motore();
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
		JComboBox comboBoxCarburante = getComboBox_Carburante();
		comboBoxCarburante.removeAllItems();
		comboBoxCarburante.setModel(new DefaultComboBoxModel<String>(carburantiAutoveicoli));
		
		//Modifico le opzioni della combobox Tipologia con le opzioni per gli autoveicoli
		JComboBox comboBoxTipologia = getComboBox_Tipologia();
		comboBoxTipologia.removeAllItems();
		comboBoxTipologia.setModel(new DefaultComboBoxModel<String>(tipologiaAutoveicoli));
	}
	
	private void selezioneMotoScooter() {
				
		//Modifico le opzioni della combobox Marca con le opzioni per i motoveicoli
		JComboBox comboBoxMarca = getComboBox_Marca();
		comboBoxMarca.removeAllItems();
		comboBoxMarca.setModel(new DefaultComboBoxModel<String>(marcheMotoveicoli));
		
		//Svuoto la combobox Modello
		JComboBox comboBoxModello = getComboBox_Modello();
		comboBoxModello.removeAllItems();
		comboBoxModello.addItem("Inserire nome modello");
			
		//Disattivo le combobox che non servono nel caso di motoveicolo
		JComboBox comboBoxCarrozzeria = getComboBox_Carrozzeria();
		comboBoxCarrozzeria.setEnabled(false);		
		JComboBox comboBoxPostiASedere = getComboBox_PostiASedere();
		comboBoxPostiASedere.setEnabled(false);
		JComboBox comboBoxFinitureInterni = getComboBox_FinitureInterni();
		comboBoxFinitureInterni.setEnabled(false);
		JComboBox comboBoxColoreInterni = getComboBox_ColoreInterni();
		comboBoxColoreInterni.setEnabled(false);
		JComboBox comboBoxMotore = getComboBox_Motore();
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
		JComboBox comboBoxCarburante = getComboBox_Carburante();
		comboBoxCarburante.removeAllItems();
		comboBoxCarburante.setModel(new DefaultComboBoxModel<String>(carburantiMotoveicoli));
		
		//Modifico le opzioni della combobox Tipologia con le opzioni per i motoveicoli
		JComboBox comboBoxTipologia = getComboBox_Tipologia();
		comboBoxTipologia.removeAllItems();
		comboBoxTipologia.setModel(new DefaultComboBoxModel<String>(tipologiaMotoveicoli));
		
	}
	
	
	private void popolaModelloVeicolo(String marcaVeicolo) throws HttpCommunicationException {
		
		marcaVeicolo = marcaVeicolo.toLowerCase().trim().replace(" ", "-");
		
		Date now = new Date(); 
		Object[] response = null;
		JSONObject json = null;
		
		JComboBox comboboxModello = getComboBox_Modello();
		JComboBox comboboxVersione = getComboBox_Versione();
		String modelloAttuale = null;
		
		comboboxModello.removeAllItems();
		comboboxVersione.removeAllItems();
		
		HttpPortalGetConnection getModelloVeicolo = new HttpPortalGetConnection();
		try {
			response = getModelloVeicolo.get("GET della marca veicolo per ottenere il modello", "http://www.carqueryapi.com/api/0.3/?cmd=getModels&make=" + marcaVeicolo + "&sold_in_us=&_=" + now.getTime(), true);
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
    	comboboxModello.addItem("Seleziona");
    	for(int i=0; i<jsonResults.length(); i++) {
        	JSONObject currentJson = jsonResults.getJSONObject(i);
        	modelloAttuale = currentJson.getString("model_name");
        	comboboxModello.addItem(modelloAttuale);
    	}	
		
				
	}
	
	
	private void popolaVersioneVeicolo(String marcaVeicolo, String modelloVeicolo) throws HttpCommunicationException {
		
		listVersioniVeicoli.clear();
		
		marcaVeicolo = marcaVeicolo.toLowerCase().trim().replace(" ", "-");
		modelloVeicolo = modelloVeicolo.toLowerCase().trim().replace(" ", "+");
		Date now = new Date(); 
		Object[] response = null;
		JSONObject json = null;
		
		JComboBox comboboxVersione = getComboBox_Versione();
		String versioneAttuale = null;
		
		String annoFabbricazione = null;
		String nomeVersione = null;
		String nomeModello = null;
		
		comboboxVersione.removeAllItems();
		
		HttpPortalGetConnection getVersioneVeicolo = new HttpPortalGetConnection();

		try {
			response = getVersioneVeicolo.get("GET del modello veicolo per ottenere la versione", "http://www.carqueryapi.com/api/0.3/?cmd=getTrims&make=" + marcaVeicolo + "&year=-1&model=" + modelloVeicolo + "&sold_in_us=&full_results=0&_=" + "&sold_in_us=&_=" + now.getTime(), true);
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
    	comboboxVersione.addItem("Seleziona");
    	for(int i=0; i<jsonResults.length(); i++) {
        	JSONObject currentJson = jsonResults.getJSONObject(i);
        	listVersioniVeicoli.add(currentJson.getString("model_id"));
        	annoFabbricazione = currentJson.getString("model_year");
        	nomeVersione = currentJson.getString("model_trim");
        	nomeModello = currentJson.getString("model_name");
        	comboboxVersione.addItem(annoFabbricazione + " - " +  nomeVersione + " - " + nomeModello);
    	}	
    	
	}
	
	
	private void popolaInfoVeicolo(String modelloVeicolo) throws HttpCommunicationException {
				
		Date now = new Date(); 
		Object[] response = null;
		JSONObject json = null;
		JSONArray jsonResults = null;
		
		JComboBox comboboxCarburante = getComboBox_Carburante();
		JComboBox comboboxPostiASedere = getComboBox_PostiASedere();
		JTextField txtFieldCV = getTextField_Cv();
		JTextField txtFieldKW = getTextField_Kw();
		JComboBox comboboxMotore = getComboBox_Motore();
		JComboBox comboboxCambio = getComboBox_Cambio();
		JTextField txtFieldCilindrata = getTextField_Cilindrata();
		JTextField txtFieldConsumoMedio = getTextField_ConsumoMedio();
		
		
		
		String versioneAttuale = null;
		
		String tipologiaCarburante = null;
		String postiASedere = null;
		String numeroCV = null;
		String numeroKW = null;
		String posizioneMotore = null;
		String tipologiaCambio = null;
		String cilindrata = null;
		String consumoMedio = null;
		
		//comboboxVersione.removeAllItems();
		
		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
		try {
			response = getInfoVeicolo.get("GET delle informazioni veicolo", "http://www.carqueryapi.com/api/0.3/?cmd=getModel&model=" + modelloVeicolo + "&_=" + now.getTime(), true);
		} catch (IOException e) {
			throw new HttpCommunicationException(e);
		}
    	String responseBody = (String)response[1];
    	
    	/*try {
			json = new JSONObject(responseBody);
		} catch (ParseException e) {
			throw new HttpCommunicationException(e);
		}*/
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
    	 //JSONArray newjson = jsonResults.getJSONArray(0);
        	
		//json = new JSONObject(responseBody);
		//JSONArray jsonResults = json.getJSONArray(responseBody);
			
		tipologiaCarburante = json.getString("model_engine_fuel");
		postiASedere = json.getString("model_seats");
		numeroCV = json.getString("model_engine_power_hp");
		numeroKW = json.getString("model_engine_power_kw");
		posizioneMotore = json.getString("model_engine_position");
		tipologiaCambio = json.getString("model_transmission_type");
		cilindrata = json.getString("model_engine_cc");
		consumoMedio = json.getString("model_lkm_mixed");
		
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

	
	private void selezionaImmagine(JLabel labelImmagine, File immagine) {
		JFileChooser dlgFile;
        String absPath;
        
        //Selezione del file immagine
        dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = dlgFile.getSelectedFile(); 
        	immagine = selectedFile;
        	Long fileSize = selectedFile.length();
        	BufferedImage img = null;
        	String selectedFileName = selectedFile.getName().toLowerCase();   
        	
            if(selectedFile.isFile() && selectedFileName.endsWith(format) && fileSize<=maxFileSize) {   	
                absPath = selectedFile.getAbsolutePath();
				try {
					img = ImageIO.read(selectedFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                Image resizedimg = img.getScaledInstance(70, 50, img.SCALE_FAST);          
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
	private void resettaForm() {
		
		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = (JComponent)iteratorListCampiForm.next();
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
		
		selezioneAutoVeicolo();
		getRdbtnAutoveicolo().setSelected(true);
	}	
	
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
	
	
	private boolean isFormValid() {
		return true;
	}
	
	//Il nuovo oggetto scheda immobile viene inserito nella struttura dati e salvato nel file .dat relativo a tutte le schede
	static void aggiungiScheda(SchedaVeicolo scheda) {
	
		//Aggiorno la lista delle schede immobile
		listSchedeVeicolo.add(scheda);
			
		//Aggiorno il file dat delle schede
		j2web.salvaListaSchedeImmobiliCreate();
		
	}
	
	
	protected static JComboBox getComboBox_Tipologia() {
		return comboBox_Tipologia;
	}
	protected static JComboBox getComboBox_Marca() {
		return comboBox_Marca;
	}
	protected static JComboBox getComboBox_Carrozzeria() {
		return comboBox_Carrozzeria;
	}
	protected static JComboBox getComboBox_PostiASedere() {
		return comboBox_PostiASedere;
	}
	protected static JComboBox getComboBox_FinitureInterni() {
		return comboBox_FinitureInterni;
	}
	protected static JComboBox getComboBox_ColoreInterni() {
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
	protected static JComboBox getComboBox_Motore() {
		return comboBox_Motore;
	}
	protected static JComboBox getComboBox_Modello() {
		return comboBox_Modello;
	}
	
	protected static JRadioButton getRdbtnAutoveicolo() {
		return rdbtnAutoveicolo;
	}
	protected static JRadioButton getRdbtnMotoScooter() {
		return rdbtnMotoScooter;
	}
	protected static JComboBox getComboBox_Carburante() {
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
	protected static JComboBox getComboBox_Versione() {
		return comboBox_Versione;
	}
	protected static JComboBox getComboBox_MeseImmatricolazione() {
		return comboBox_MeseImmatricolazione;
	}
	protected static JComboBox getComboBox_AnnoImmatricolazione() {
		return comboBox_AnnoImmatricolazione;
	}
	protected static JTextField getTextField_Kw() {
		return txtFieldKw;
	}
	protected static JTextField getTextField_Cv() {
		return txtFieldCv;
	}
	protected static JComboBox getComboBox_ColoreEsterno() {
		return comboBox_ColoreEsterno;
	}
	protected static JCheckBox getChckbxMetallizzato() {
		return chckbxMetallizzato;
	}
	protected static JComboBox getComboBox_PrecedentiProprietari() {
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
	protected static JComboBox getComboBox_Cambio() {
		return comboBox_Cambio;
	}
	protected static JComboBox getComboBox_NumeroRapporti() {
		return comboBox_NumeroRapporti;
	}
	protected static JTextField getTextField_ConsumoMedio() {
		return comboBox_ConsumoMedio;
	}
	protected static JComboBox getComboBox_ClasseEmissioni() {
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
}
