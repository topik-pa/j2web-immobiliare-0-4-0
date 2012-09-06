import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


public class j2web_GUI extends JFrame implements parametriGenerali {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodiceInserzione;
	private JTextField textTitoloAnnuncio;
	private JTextField textFieldCap;
	private JTextField textFieldIndirizzoLocalita;
	private JTextField textSuperficieImmobile;
	private JTextField textFieldPrezzoImmobile;
	private JTextField textFieldImmagine1;
	private JTextField textFieldImmagine2;
	private JTextField textFieldImmagine3;
	private JTextField textFieldImmagine4;
	private JTextField textFieldImmagine5;
	private JTextField textFieldImmagine6;
	private JTextField textFieldNumeroTotalePiani;
	private JTextField textFieldAnnoCostruzione;
	private JTextField textFieldImmagine7;
	private JTextField textFieldImmagine8;
	private JTextField textFieldImmagine9;
	private JTextField textFieldImmagine10;
	
	//Alcuni campi devono essere inseriti al di fuori del costruttore per essere visibili all'intera classe
	private static JComboBox<String> comboBoxProvincia;
	private static JComboBox<String> comboBoxComune;
	private static JComboBox<String> comboBoxCategoriaImmobile;
	private static JComboBox<String> comboBoxTipologiaImmobile;
	


	/**
	 * Create the frame.
	 */
	public j2web_GUI() {
				
	    setIconImage(frameIcon);    
	    setTitle(nomeGUI);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GUI_bounds[0], GUI_bounds[1], GUI_bounds[2], GUI_bounds[3]);
		
		
		/*Menu... in stand-by*/
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
		/*Menu*/
		
		
		
		JPanel panelCreazioneScheda = new JPanel();
		panelCreazioneScheda.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Creazione schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelCreazioneScheda);
		panelCreazioneScheda.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPaneCreazioneSchede = new JTabbedPane(JTabbedPane.TOP);
		panelCreazioneScheda.add(tabbedPaneCreazioneSchede, BorderLayout.NORTH);
		
		JPanel panelTabDatiObbligatori = new JPanel();
		tabbedPaneCreazioneSchede.addTab("Dati obbligatori", null, panelTabDatiObbligatori, null);
		panelTabDatiObbligatori.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("235px:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("100px:grow(5)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				RowSpec.decode("7dlu:grow(14)"),
				RowSpec.decode("default:grow"),}));
		
		JPanel panelCodiceInserzione = new JPanel();
		panelTabDatiObbligatori.add(panelCodiceInserzione, "1, 2, fill, fill");
		panelCodiceInserzione.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("fill:20px"),}));
		
		JLabel lblCodiceInserzione = new JLabel("Codice inserzione");
		lblCodiceInserzione.setToolTipText("");
		panelCodiceInserzione.add(lblCodiceInserzione, "1, 1, left, fill");
		
		txtCodiceInserzione = new JTextField();
		panelCodiceInserzione.add(txtCodiceInserzione, "2, 1, left, default");
		txtCodiceInserzione.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodiceInserzione.setToolTipText("Inserire un codice per identificare univocamente l'annuncio");
		txtCodiceInserzione.setColumns(10);
		txtCodiceInserzione.addKeyListener(new KeyAdapter() {   
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')  || (c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) ||  (c == KeyEvent.VK_MINUS)  || (c == KeyEvent.VK_DELETE)) || txtCodiceInserzione.getText().length()>=maxCaratteri.get("txtCodiceInserzione")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		lblCodiceInserzione.setLabelFor(txtCodiceInserzione);
		
		JPanel panelTitoloAnnuncio = new JPanel();
		panelTabDatiObbligatori.add(panelTitoloAnnuncio, "1, 4, fill, fill");
		panelTitoloAnnuncio.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));
		
		JLabel lblTitoloAnnuncio = new JLabel("Titolo annuncio");
		panelTitoloAnnuncio.add(lblTitoloAnnuncio, "1, 1, fill, fill");
		
		textTitoloAnnuncio = new JTextField();
		lblTitoloAnnuncio.setLabelFor(textTitoloAnnuncio);
		panelTitoloAnnuncio.add(textTitoloAnnuncio, "2, 1, left, default");
		textTitoloAnnuncio.setToolTipText("Inserire un titolo per l'annuncio");
		textTitoloAnnuncio.addKeyListener(new KeyAdapter() {     
            public void keyTyped(KeyEvent e) {
                if (textTitoloAnnuncio.getText().length()>=150) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		textTitoloAnnuncio.setColumns(23);
		
		JPanel panelRegProvCom = new JPanel();
		panelTabDatiObbligatori.add(panelRegProvCom, "1, 6, fill, fill");
		panelRegProvCom.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),}));
		
		JLabel lblRegione = new JLabel("Regione");
		panelRegProvCom.add(lblRegione, "1, 1, fill, fill");
		
		final JComboBox<String> comboBoxRegione = new JComboBox<String>();
		comboBoxRegione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Select: Seleziona Regione");
				//Chiamo il metodo per popolare la combobox delle provincie
				popolaComboBoxProvincia((String)comboBoxRegione.getSelectedItem());				
			}
		});
		comboBoxRegione.setModel(new DefaultComboBoxModel<String>(arrayRegioni));
		lblRegione.setLabelFor(comboBoxRegione);
		panelRegProvCom.add(comboBoxRegione, "2, 1, left, default");
		comboBoxRegione.setToolTipText("Selezionare la regione");
		
		JLabel lblProvincia = new JLabel("Provincia");
		panelRegProvCom.add(lblProvincia, "1, 3, fill, fill");
		lblProvincia.setToolTipText("Selezionare la provincia");
		
		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Select: Seleziona Provincia");
				//Chiamo il metodo per popolare la combobox dei comuni
				popolaComboBoxComune((String)comboBoxProvincia.getSelectedItem());
			}
		});
		lblProvincia.setLabelFor(comboBoxProvincia);
		panelRegProvCom.add(comboBoxProvincia, "2, 3, left, default");
		comboBoxProvincia.setToolTipText("Selezionare la provincia");
		
		
		JLabel lblComune = new JLabel("Comune");
		panelRegProvCom.add(lblComune, "1, 5, fill, fill");
		lblComune.setToolTipText("Selezionare il comune");
		
		
		comboBoxComune = new JComboBox<String>();
		comboBoxComune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Select: Seleziona Comune");
			}
		});
		lblComune.setLabelFor(comboBoxComune);
		panelRegProvCom.add(comboBoxComune, "2, 5, left, default");
		comboBoxComune.setToolTipText("Selezionare il comune");
		
		JPanel panelCap = new JPanel();
		panelTabDatiObbligatori.add(panelCap, "1, 8, fill, fill");
		panelCap.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JLabel lblCap = new JLabel("CAP");
		panelCap.add(lblCap, "1, 1, fill, fill");
		
		textFieldCap = new JTextField();
		lblCap.setLabelFor(textFieldCap);
		panelCap.add(textFieldCap, "2, 1, left, default");
		textFieldCap.setToolTipText("Inserire il CAP");
		textFieldCap.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || textFieldCap.getText().length()>=maxCaratteri.get("textFieldCap")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		textFieldCap.setColumns(10);
		
		JPanel panelIndirizzo = new JPanel();
		panelTabDatiObbligatori.add(panelIndirizzo, "1, 10, fill, fill");
		panelIndirizzo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("50dlu:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));
		
		JLabel lblIndirizzoLocalita = new JLabel("Indirizzo/Località");
		panelIndirizzo.add(lblIndirizzoLocalita, "1, 1, fill, fill");
		
		textFieldIndirizzoLocalita = new JTextField();
		lblIndirizzoLocalita.setLabelFor(textFieldIndirizzoLocalita);
		panelIndirizzo.add(textFieldIndirizzoLocalita, "2, 1, left, default");
		textFieldIndirizzoLocalita.setToolTipText("Specificare un indirizzo completo o la località dell'immobile");
		textFieldIndirizzoLocalita.setColumns(23);
		textFieldIndirizzoLocalita.addKeyListener(new KeyAdapter() {     
            public void keyTyped(KeyEvent e) {
                if (textFieldIndirizzoLocalita.getText().length()>=maxCaratteri.get("textFieldIndirizzoLocalita")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		
		
		JLabel lblTestoDellaScheda = new JLabel("Testo dell'annuncio");
		lblTestoDellaScheda.setToolTipText("");
		panelTabDatiObbligatori.add(lblTestoDellaScheda, "1, 12, default, fill");
		
		JScrollPane scrollPaneTestoScheda = new JScrollPane();
		panelTabDatiObbligatori.add(scrollPaneTestoScheda, "1, 13, fill, fill");
		
		final JTextArea textAreaTestoAnnuncio = new JTextArea();
		textAreaTestoAnnuncio.setTabSize(4);
		lblTestoDellaScheda.setLabelFor(textAreaTestoAnnuncio);
		textAreaTestoAnnuncio.setToolTipText("Inserire il testo dell'annuncio");
		textAreaTestoAnnuncio.addKeyListener(new KeyAdapter() {     
            public void keyTyped(KeyEvent e) {
                if (textAreaTestoAnnuncio.getText().length()>=maxCaratteri.get("textAreaTestoAnnuncio")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		scrollPaneTestoScheda.setViewportView(textAreaTestoAnnuncio);
		
		JPanel panelCategoriaTipologia = new JPanel();
		panelTabDatiObbligatori.add(panelCategoriaTipologia, "1, 15, fill, default");
		panelCategoriaTipologia.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("50dlu:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),}));
		
		JLabel lblCategoriaImmobile = new JLabel("Categoria immobile");
		panelCategoriaTipologia.add(lblCategoriaImmobile, "1, 1, fill, fill");
		
		
		comboBoxCategoriaImmobile = new JComboBox<String>();
		comboBoxCategoriaImmobile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Select: Seleziona Categoria immobile");
				//Chiamo il metodo per popolare la combobox delle provincie
				popolaComboBoxTipologiaImmobile((String)comboBoxCategoriaImmobile.getSelectedItem());	
			}
		});
		comboBoxCategoriaImmobile.setModel(new DefaultComboBoxModel<String>(arrayCategorieImmobili ));
		lblCategoriaImmobile.setLabelFor(comboBoxCategoriaImmobile);
		comboBoxCategoriaImmobile.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelCategoriaTipologia.add(comboBoxCategoriaImmobile, "2, 1, left, default");
		comboBoxCategoriaImmobile.setToolTipText("Selezionare la categoria dell'immobile");
		
		JLabel lblTipologiaImmobile = new JLabel("Tipologia immobile");
		panelCategoriaTipologia.add(lblTipologiaImmobile, "1, 3, fill, fill");
		
		
		comboBoxTipologiaImmobile = new JComboBox<String>();
		//comboBoxTipologiaImmobile.setModel(new DefaultComboBoxModel<String>(arrayCategorieImmobili ));
		lblTipologiaImmobile.setLabelFor(comboBoxTipologiaImmobile);
		comboBoxTipologiaImmobile.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelCategoriaTipologia.add(comboBoxTipologiaImmobile, "2, 3, left, default");
		comboBoxTipologiaImmobile.setToolTipText("Selezionare la tipologia di immobile");
		
		JPanel panelSuperficie = new JPanel();
		panelTabDatiObbligatori.add(panelSuperficie, "1, 17, fill, fill");
		panelSuperficie.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));
		
		
		JLabel lblSuperficieImmmobile = new JLabel("Superficie (mq)");
		panelSuperficie.add(lblSuperficieImmmobile, "1, 1, fill, fill");
		
		textSuperficieImmobile = new JTextField();
		lblSuperficieImmmobile.setLabelFor(textSuperficieImmobile);
		panelSuperficie.add(textSuperficieImmobile, "2, 1, left, default");
		textSuperficieImmobile.setToolTipText("Inserire la superficie dell'immobile in metri quadrati");
		textSuperficieImmobile.setColumns(10);
		textSuperficieImmobile.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))  || textSuperficieImmobile.getText().length()>=maxCaratteri.get("textSuperficieImmobile")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		
		JPanel panelTipologiaContratto = new JPanel();
		panelTabDatiObbligatori.add(panelTipologiaContratto, "1, 19, fill, fill");
		panelTipologiaContratto.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));
		
		JLabel lblTipologiaContratto = new JLabel("Tipologia contratto");
		panelTipologiaContratto.add(lblTipologiaContratto, "1, 1, fill, fill");
		lblTipologiaContratto.setToolTipText("");
		
		
		JComboBox<String> comboBoxTipologiaContratto = new JComboBox<String>();
		comboBoxTipologiaContratto.setModel(new DefaultComboBoxModel<String>(arrayTipologieContratto));
		lblTipologiaContratto.setLabelFor(comboBoxTipologiaContratto);
		comboBoxTipologiaContratto.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelTipologiaContratto.add(comboBoxTipologiaContratto, "2, 1, left, default");
		comboBoxTipologiaContratto.setToolTipText("");
		
		JPanel panelPrezzo = new JPanel();
		panelTabDatiObbligatori.add(panelPrezzo, "1, 21, fill, fill");
		panelPrezzo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.MIN_ROWSPEC,}));
		
		JLabel lblPrezzoImmobile = new JLabel("Prezzo");
		panelPrezzo.add(lblPrezzoImmobile, "1, 1, fill, fill");
		
		textFieldPrezzoImmobile = new JTextField();
		lblPrezzoImmobile.setLabelFor(textFieldPrezzoImmobile);
		panelPrezzo.add(textFieldPrezzoImmobile, "2, 1, left, default");
		textFieldPrezzoImmobile.setToolTipText("Inserire il prezzo di vendita o affitto dell'immobile");
		textFieldPrezzoImmobile.setColumns(10);
		textFieldPrezzoImmobile.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || textFieldPrezzoImmobile.getText().length()>=maxCaratteri.get("textFieldPrezzoImmobile")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		
		JPanel panelPulsantiCreazioneScheda = new JPanel();
		panelTabDatiObbligatori.add(panelPulsantiCreazioneScheda, "1, 23, fill, fill");
		panelPulsantiCreazioneScheda.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("81px:grow"),
				ColumnSpec.decode("20px:grow"),
				ColumnSpec.decode("81px:grow"),},
			new RowSpec[] {
				RowSpec.decode("33px"),}));
		
		JButton btnCreaScheda = new JButton("Crea scheda");
		btnCreaScheda.setIcon(icoProcedi);
		btnCreaScheda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Click: Crea scheda");
			}
		});
		btnCreaScheda.setToolTipText("Crea la scheda e la inserisce nela pannello schede");
		panelPulsantiCreazioneScheda.add(btnCreaScheda, "1, 1");
		
		JLabel label = new JLabel("");
		panelPulsantiCreazioneScheda.add(label, "2, 1, fill, fill");
		
		JButton btnResettaPannelloCreazioneScheda = new JButton("Resetta");
		btnResettaPannelloCreazioneScheda.setIcon(icoResetta);
		btnResettaPannelloCreazioneScheda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Click: Resetta");
			}
		});
		btnResettaPannelloCreazioneScheda.setToolTipText("Resetta il pannello di creazione scheda");
		panelPulsantiCreazioneScheda.add(btnResettaPannelloCreazioneScheda, "3, 1");
		
		JPanel panelTabImmagini = new JPanel();
		tabbedPaneCreazioneSchede.addTab("Immagini", null, panelTabImmagini, null);
		panelTabImmagini.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(54dlu;default)"),
				ColumnSpec.decode("max(0dlu;min)"),
				ColumnSpec.decode("right:80px:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
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
				RowSpec.decode("25px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnImmagine1 = new JButton("Immagine1");
		btnImmagine1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 1");
				selezionaImmagine(textFieldImmagine1);
			}
		});
		btnImmagine1.setToolTipText("Inserimento immagine 1");
		panelTabImmagini.add(btnImmagine1, "1, 2");
		
		textFieldImmagine1 = new JTextField();
		textFieldImmagine1.setToolTipText("Inserimento immagine 1");
		panelTabImmagini.add(textFieldImmagine1, "3, 2, left, default");
		textFieldImmagine1.setColumns(23);
		
		JButton btnImmagine2 = new JButton("Immagine 2");
		btnImmagine2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 2");
				selezionaImmagine(textFieldImmagine2);
			}
		});
		btnImmagine2.setToolTipText("Inserimento immagine 2");
		panelTabImmagini.add(btnImmagine2, "1, 4, default, fill");
		
		textFieldImmagine2 = new JTextField();
		textFieldImmagine2.setToolTipText("Inserimento immagine 2");
		panelTabImmagini.add(textFieldImmagine2, "3, 4, left, default");
		textFieldImmagine2.setColumns(23);
		
		JButton btnImmagine3 = new JButton("Immagine 3");
		btnImmagine3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 3");
				selezionaImmagine(textFieldImmagine3);
			}
		});
		btnImmagine3.setToolTipText("Inserimento immagine 3");
		panelTabImmagini.add(btnImmagine3, "1, 6, default, fill");
		
		textFieldImmagine3 = new JTextField();
		textFieldImmagine3.setToolTipText("Inserimento immagine 3");
		panelTabImmagini.add(textFieldImmagine3, "3, 6, left, default");
		textFieldImmagine3.setColumns(23);
		
		JButton btnImmagine4 = new JButton("Immagine 4");
		btnImmagine4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 4");
				selezionaImmagine(textFieldImmagine4);
			}
		});
		btnImmagine4.setToolTipText("Inserimento immagine 4");
		panelTabImmagini.add(btnImmagine4, "1, 8, default, fill");
		
		textFieldImmagine4 = new JTextField();
		textFieldImmagine4.setToolTipText("Inserimento immagine 4");
		panelTabImmagini.add(textFieldImmagine4, "3, 8, left, default");
		textFieldImmagine4.setColumns(23);
		
		JButton btnImmagine5 = new JButton("Immagine 5");
		btnImmagine5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 5");
				selezionaImmagine(textFieldImmagine5);
			}
		});
		btnImmagine5.setToolTipText("Inserimento immagine 5");
		panelTabImmagini.add(btnImmagine5, "1, 10, default, fill");
		
		textFieldImmagine5 = new JTextField();
		textFieldImmagine5.setToolTipText("Inserimento immagine 5");
		panelTabImmagini.add(textFieldImmagine5, "3, 10, left, default");
		textFieldImmagine5.setColumns(23);
		
		JButton btnImmagine6 = new JButton("Immagine 6");
		btnImmagine6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 6");
				selezionaImmagine(textFieldImmagine6);
			}
		});
		btnImmagine6.setToolTipText("Inserimento immagine 6");
		panelTabImmagini.add(btnImmagine6, "1, 12, default, fill");
		
		textFieldImmagine6 = new JTextField();
		textFieldImmagine6.setToolTipText("Inserimento immagine 6");
		panelTabImmagini.add(textFieldImmagine6, "3, 12, left, default");
		textFieldImmagine6.setColumns(23);
		
		JButton btnImmagine7 = new JButton("Immagine 7");
		btnImmagine7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 7");
				selezionaImmagine(textFieldImmagine7);
			}
		});
		btnImmagine7.setToolTipText("Inserimento immagine 7");
		panelTabImmagini.add(btnImmagine7, "1, 14");
		
		textFieldImmagine7 = new JTextField();
		textFieldImmagine7.setToolTipText("Inserimento immagine 7");
		panelTabImmagini.add(textFieldImmagine7, "3, 14, left, default");
		textFieldImmagine7.setColumns(23);
		
		JButton btnImmagine8 = new JButton("Immagine 8");
		btnImmagine8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 8");
				selezionaImmagine(textFieldImmagine8);
			}
		});
		btnImmagine8.setToolTipText("Inserimento immagine 8");
		panelTabImmagini.add(btnImmagine8, "1, 16");
		
		textFieldImmagine8 = new JTextField();
		textFieldImmagine8.setToolTipText("Inserimento immagine 8");
		panelTabImmagini.add(textFieldImmagine8, "3, 16, left, default");
		textFieldImmagine8.setColumns(23);
		
		JButton btnImmagine9 = new JButton("Immagine 9");
		btnImmagine9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 9");
				selezionaImmagine(textFieldImmagine9);
			}
		});
		btnImmagine9.setToolTipText("Inserimento immagine 9");
		panelTabImmagini.add(btnImmagine9, "1, 18");
		
		textFieldImmagine9 = new JTextField();
		textFieldImmagine9.setToolTipText("Inserimento immagine 9");
		panelTabImmagini.add(textFieldImmagine9, "3, 18, left, default");
		textFieldImmagine9.setColumns(23);
		
		JButton btImmagine10 = new JButton("Immagine 10");
		btImmagine10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click: Immmagine 10");
				selezionaImmagine(textFieldImmagine10);
			}
		});
		btImmagine10.setToolTipText("Inserimento immagine 10");
		panelTabImmagini.add(btImmagine10, "1, 20");
		
		textFieldImmagine10 = new JTextField();
		textFieldImmagine10.setToolTipText("Inserimento immagine 10");
		panelTabImmagini.add(textFieldImmagine10, "3, 20, left, default");
		textFieldImmagine10.setColumns(23);
		
		JPanel panelTabDatiSecondari = new JPanel();
		tabbedPaneCreazioneSchede.addTab("Dati secondari", null, panelTabDatiSecondari, null);
		panelTabDatiSecondari.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("75px:grow"),
				ColumnSpec.decode("50dlu:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
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
		
		JLabel lblNumeroLocali = new JLabel("Numero locali");
		lblNumeroLocali.setToolTipText("Selezionare il numero dei locali dell'immobile");
		panelTabDatiSecondari.add(lblNumeroLocali, "1, 2, left, default");
		
		JComboBox<String> comboBoxNumeroLocali = new JComboBox<String>();
		comboBoxNumeroLocali.setModel(new DefaultComboBoxModel<String>(arrayNumeroLocali));
		comboBoxNumeroLocali.setToolTipText("Selezionare il numero dei locali dell'immobile");
		lblNumeroLocali.setLabelFor(comboBoxNumeroLocali);
		panelTabDatiSecondari.add(comboBoxNumeroLocali, "2, 2, left, default");
		
		JLabel lblNumeroCamere = new JLabel("Numero camere da letto");
		lblNumeroCamere.setToolTipText("Selezionare il numero di camere da letto disponibili");
		panelTabDatiSecondari.add(lblNumeroCamere, "1, 4");
		
		JComboBox<String> comboBoxNumeroCamere = new JComboBox<String>();
		comboBoxNumeroCamere.setModel(new DefaultComboBoxModel<String>(arrayNumeroCamere));
		comboBoxNumeroCamere.setToolTipText("Selezionare il numero di camere da letto disponibili");
		lblNumeroCamere.setLabelFor(comboBoxNumeroCamere);
		panelTabDatiSecondari.add(comboBoxNumeroCamere, "2, 4, left, default");
		
		JLabel lblNumeroBagni = new JLabel("Numero bagni");
		lblNumeroBagni.setToolTipText("Selezionare il numero di bagni disponibili");
		panelTabDatiSecondari.add(lblNumeroBagni, "1, 6");
		
		JComboBox<String> comboBoxNumeroBagni = new JComboBox<String>();
		comboBoxNumeroBagni.setModel(new DefaultComboBoxModel<String>(arrayNumeroBagni));
		comboBoxNumeroBagni.setToolTipText("Selezionare il numero di bagni disponibili");
		lblNumeroBagni.setLabelFor(comboBoxNumeroBagni);
		panelTabDatiSecondari.add(comboBoxNumeroBagni, "2, 6, left, default");
		
		JLabel lblStatoImmobile = new JLabel("Stato dell'immobile");
		lblStatoImmobile.setToolTipText("Selezionare lo stato attuale dell'immobile");
		panelTabDatiSecondari.add(lblStatoImmobile, "1, 8");
		
		JComboBox<String> comboBoxStatoImmobile = new JComboBox<String>();
		comboBoxStatoImmobile.setModel(new DefaultComboBoxModel<String>(arrayStatoImmobile));
		comboBoxStatoImmobile.setToolTipText("Selezionare lo stato attuale dell'immobile");
		lblStatoImmobile.setLabelFor(comboBoxStatoImmobile);
		panelTabDatiSecondari.add(comboBoxStatoImmobile, "2, 8");
		
		JLabel lblArredamenti = new JLabel("Arredamenti");
		lblArredamenti.setToolTipText("Selezionr la condizione attuale dell'arredamento");
		panelTabDatiSecondari.add(lblArredamenti, "1, 10");
		
		JComboBox<String> comboBoxArredamenti = new JComboBox<String>();
		comboBoxArredamenti.setModel(new DefaultComboBoxModel<String>(arrayArredamenti));
		comboBoxArredamenti.setToolTipText("Selezionr la condizione attuale dell'arredamento");
		lblArredamenti.setLabelFor(comboBoxArredamenti);
		panelTabDatiSecondari.add(comboBoxArredamenti, "2, 10");
		
		JLabel lblPiano = new JLabel("Piano");
		lblPiano.setToolTipText("Selezionare il piano in cui è situato l'immobile");
		panelTabDatiSecondari.add(lblPiano, "1, 12");
		
		JComboBox<String> comboBoxPiano = new JComboBox<String>();
		comboBoxPiano.setModel(new DefaultComboBoxModel<String>(arrayPiano));
		comboBoxPiano.setToolTipText("Selezionare il piano in cui è situato l'immobile");
		lblPiano.setLabelFor(comboBoxPiano);
		panelTabDatiSecondari.add(comboBoxPiano, "2, 12");
		
		JLabel lblNumeroTotalePiani = new JLabel("Numero totale di piani");
		lblNumeroTotalePiani.setToolTipText("Indicare il numero totale dei piani dell'immobile");
		panelTabDatiSecondari.add(lblNumeroTotalePiani, "1, 14");
		lblNumeroTotalePiani.setLabelFor(textFieldNumeroTotalePiani);
		
		textFieldNumeroTotalePiani = new JTextField();
		textFieldNumeroTotalePiani.setToolTipText("Indicare il numero totale dei piani dell'immobile");
		panelTabDatiSecondari.add(textFieldNumeroTotalePiani, "2, 14, left, default");
		textFieldNumeroTotalePiani.setColumns(10);
		textFieldNumeroTotalePiani.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || textFieldNumeroTotalePiani.getText().length()>=maxCaratteri.get("textFieldNumeroTotalePiani")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		
		JLabel lblCertificazioniEnergetiche = new JLabel("Certificazioni energetiche");
		lblCertificazioniEnergetiche.setToolTipText("Selezionre la certificazione energetica");
		panelTabDatiSecondari.add(lblCertificazioniEnergetiche, "1, 16");
		
		JComboBox<String> comboBoxCertificazioniEnergetiche = new JComboBox<String>();
		comboBoxCertificazioniEnergetiche.setModel(new DefaultComboBoxModel<String>(arrayCertificazioniEnergetiche));
		comboBoxCertificazioniEnergetiche.setToolTipText("Selezionre la certificazione energetica");
		lblCertificazioniEnergetiche.setLabelFor(comboBoxCertificazioniEnergetiche);
		panelTabDatiSecondari.add(comboBoxCertificazioniEnergetiche, "2, 16");
		
		JLabel lbTipologiaRiscaldamento = new JLabel("Tipologia riscaldamento");
		lbTipologiaRiscaldamento.setToolTipText("Selezionare la tipologia di riscaldamento dell'immobile");
		panelTabDatiSecondari.add(lbTipologiaRiscaldamento, "1, 18");
		
		JComboBox<String> comboBoxTipologiaRiscaldamento = new JComboBox<String>();
		comboBoxTipologiaRiscaldamento.setModel(new DefaultComboBoxModel<String>(arrayTipologieRiscaldamento));
		comboBoxTipologiaRiscaldamento.setToolTipText("Selezionare la tipologia di riscaldamento dell'immobile");
		panelTabDatiSecondari.add(comboBoxTipologiaRiscaldamento, "2, 18");
		
		JLabel lblClima = new JLabel("Clima");
		lblClima.setToolTipText("Selezionare la tipologia di climatizzazione dell'immobile");
		panelTabDatiSecondari.add(lblClima, "1, 20");
		
		JComboBox<String> comboBoxClima = new JComboBox<String>();
		comboBoxClima.setModel(new DefaultComboBoxModel<String>(arrayClima));
		comboBoxClima.setToolTipText("Selezionare la tipologia di climatizzazione dell'immobile");
		panelTabDatiSecondari.add(comboBoxClima, "2, 20");
		
		JLabel lblParcheggio = new JLabel("Parcheggio");
		lblParcheggio.setToolTipText("Selezionare la tipologia di parcheggio disponibile");
		panelTabDatiSecondari.add(lblParcheggio, "1, 22");
		
		JComboBox<String> comboBoxParcheggio = new JComboBox<String>();
		comboBoxParcheggio.setModel(new DefaultComboBoxModel<String>(arrayParcheggio));
		comboBoxParcheggio.setToolTipText("Selezionare la tipologia di parcheggio disponibile");
		panelTabDatiSecondari.add(comboBoxParcheggio, "2, 22");
		lblParcheggio.setLabelFor(comboBoxParcheggio);
		
		JLabel lblAnnoCostruzione = new JLabel("Anno costruzione");
		lblAnnoCostruzione.setToolTipText("Inserire l'anno di costruzione dell'immobile");
		panelTabDatiSecondari.add(lblAnnoCostruzione, "1, 24");
		
		textFieldAnnoCostruzione = new JTextField();
		textFieldAnnoCostruzione.setToolTipText("Inserire l'anno di costruzione dell'immobile");
		lblAnnoCostruzione.setLabelFor(textFieldAnnoCostruzione);
		panelTabDatiSecondari.add(textFieldAnnoCostruzione, "2, 24, left, default");
		textFieldAnnoCostruzione.setColumns(10);
		textFieldAnnoCostruzione.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) || textFieldAnnoCostruzione.getText().length()>=maxCaratteri.get("textFieldAnnoCostruzione")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		
		JLabel lblGiardino = new JLabel("Giardino");
		lblGiardino.setToolTipText("Selezionare la tipologia di giardino disponibile");
		panelTabDatiSecondari.add(lblGiardino, "1, 26");
		
		JComboBox<String> comboBoxGiardino = new JComboBox<String>();
		comboBoxGiardino.setModel(new DefaultComboBoxModel<String>(arrayGiardino));
		comboBoxGiardino.setToolTipText("Selezionare la tipologia di giardino disponibile");
		panelTabDatiSecondari.add(comboBoxGiardino, "2, 26");
		
		JCheckBox chckbxBandaLarga = new JCheckBox("Banda larga");
		chckbxBandaLarga.setToolTipText("Banda larga");
		panelTabDatiSecondari.add(chckbxBandaLarga, "1, 30");
		
		JCheckBox chckbxSatellite = new JCheckBox("Satellite");
		chckbxSatellite.setToolTipText("Satellite");
		panelTabDatiSecondari.add(chckbxSatellite, "2, 30");
		
		JCheckBox chckbxAscensore = new JCheckBox("Ascensore");
		chckbxAscensore.setToolTipText("Ascensore");
		panelTabDatiSecondari.add(chckbxAscensore, "1, 32");
		
		JCheckBox chckbxAllarme = new JCheckBox("Sistema di allarme");
		chckbxAllarme.setToolTipText("Sistema di allarme");
		panelTabDatiSecondari.add(chckbxAllarme, "2, 32");
		
		JCheckBox chckbxCasaEcologica = new JCheckBox("Casa ecologica");
		chckbxCasaEcologica.setToolTipText("Casa ecologica");
		panelTabDatiSecondari.add(chckbxCasaEcologica, "1, 34");
		
		JCheckBox chckbxCancelloElettrico = new JCheckBox("Cancello elettrico");
		chckbxCancelloElettrico.setToolTipText("Cancello elettrico");
		panelTabDatiSecondari.add(chckbxCancelloElettrico, "2, 34");
		
		JCheckBox chckbxVicinanzeBus = new JCheckBox("Vicinanze bus");
		chckbxVicinanzeBus.setToolTipText("Vicinanze bus");
		panelTabDatiSecondari.add(chckbxVicinanzeBus, "1, 36");
		
		JCheckBox chckbxVicinanzeMetro = new JCheckBox("Vicinanze metro");
		chckbxVicinanzeMetro.setToolTipText("Vicinanze metro");
		panelTabDatiSecondari.add(chckbxVicinanzeMetro, "2, 36");
		
		JCheckBox chckbxVistaDiPregio = new JCheckBox("Vista di pregio");
		chckbxVistaDiPregio.setToolTipText("Vista di pregio");
		panelTabDatiSecondari.add(chckbxVistaDiPregio, "1, 38");
		
		JCheckBox chckbxRampeDisabili = new JCheckBox("Rampe per disabili");
		chckbxRampeDisabili.setToolTipText("Rampe per disabili");
		panelTabDatiSecondari.add(chckbxRampeDisabili, "2, 38");
		
		JPanel panelListaSchedeImmobile = new JPanel();
		getContentPane().add(panelListaSchedeImmobile);
		panelListaSchedeImmobile.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPaneListaSchedeImmobile = new JScrollPane();
		scrollPaneListaSchedeImmobile.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Schede immobile create", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelListaSchedeImmobile.add(scrollPaneListaSchedeImmobile);
		
		JPanel panelInserimentoSchedeImmobile = new JPanel();
		getContentPane().add(panelInserimentoSchedeImmobile);
		panelInserimentoSchedeImmobile.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPaneInserimentoSchede = new JScrollPane();
		scrollPaneInserimentoSchede.setViewportBorder(new TitledBorder(null, "Inserimento schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInserimentoSchedeImmobile.add(scrollPaneInserimentoSchede);
	}
	
	//Popola la combobox Provincia quando viene modificata la Regione
	static void popolaComboBoxProvincia(String regione) {
		//Rimuovo tutte le opzioni della select Provincia
		comboBoxComune.removeAllItems();
		if(!regione.equals("Seleziona")) {
			System.out.println("Regione: " + regione);
			comboBoxProvincia.setModel(new DefaultComboBoxModel<String>(regioneProvincie.get(regione)));
		}	
	}
	
	
	//Popola la combobox Comune quando viene modificata la Provincia
	static void popolaComboBoxComune(String provincia) {
		if(!provincia.equals("Seleziona")) {
			System.out.println("Provincia: " + provincia);
			comboBoxComune.setModel(new DefaultComboBoxModel<String>(provinciaComuni.get(provincia)));
		}
	}
	
	//Popola la combobox Categoria immobile quando viene modificata la Tipologia immobile
	static void popolaComboBoxTipologiaImmobile(String categoria) {
			System.out.println("Categoria: " + categoria);
			switch (categoria)
        	{
    	    case "Residenziale":
    	    	comboBoxTipologiaImmobile.setModel(new DefaultComboBoxModel<String>(arrayTipologieImmobiliResidenziali));
    	        break;
    	    case "Commerciale":
    	    	comboBoxTipologiaImmobile.setModel(new DefaultComboBoxModel<String>(arrayTipologieImmobiliCommerciali));
    	    	break;
    	    case "Industriale":
    	    	comboBoxTipologiaImmobile.setModel(new DefaultComboBoxModel<String>(arrayTipologieImmobiliIndustriali));
    	    	break;
    	    default:
    	    	comboBoxTipologiaImmobile.removeAllItems();            	    	
        	}
	}
	
	//Seleziona e carica le immagini nella form
	static void selezionaImmagine(JTextField relatedTextField) {
		JFileChooser dlgFile;
        String absPath;
        
        //Selezione del file immagine
        dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	//Controllo il formato del file
        	File selectedFile = dlgFile.getSelectedFile(); 
        	Long fileSize = selectedFile.length();                    	
        	String selectedFileName = selectedFile.getName().toLowerCase();                   	
            if(selectedFile.isFile() && selectedFileName.endsWith(format) && fileSize<=maxFileSize) {
                absPath = selectedFile.getAbsolutePath();
                relatedTextField.setText(absPath);
                relatedTextField.setEnabled(false);
            }
            else {
            	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
            }	
        }
	}

}
