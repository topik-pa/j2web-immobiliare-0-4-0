/*
 * Il pannello centrale, che contiene la lista delle schede create
 * Ultima modifica: 12 feb 2012
 *
 */

/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ListIterator;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


//Pannello per la creazione delle schede immobile
public class PanelCreazioneSchedeImmobile extends JPanel implements parametriGenerali {
	
	private static final long serialVersionUID = 1L;

	//Tutti i campi della form soggetti ad interazione
	//Campi testuali
  	private static JTextField textFieldCodiceInserzione = new JTextField();
  	private static JTextField textFieldTitoloAnnuncio = new JTextField();
  	private static JTextField textFieldCap = new JTextField();
  	private static JTextField textFieldIndirizzoLocalita = new JTextField();
  	private static JTextField textFieldSuperficieImmobile = new JTextField();
  	private static JTextField textFieldPrezzoImmobile = new JTextField();
  	private static JTextField textFieldImmagine1 = new JTextField();
  	private static JTextField textFieldImmagine2 = new JTextField();
  	private static JTextField textFieldImmagine3 = new JTextField();
  	private static JTextField textFieldImmagine4 = new JTextField();
  	private static JTextField textFieldImmagine5 = new JTextField();
  	private static JTextField textFieldImmagine6 = new JTextField();	
  	private static JTextField textFieldImmagine7 = new JTextField();
  	private static JTextField textFieldImmagine8 = new JTextField();
  	private static JTextField textFieldImmagine9 = new JTextField();
  	private static JTextField textFieldImmagine10 = new JTextField();
  	private static JTextField textFieldNumeroTotalePiani = new JTextField();
  	private static JTextField textFieldAnnoCostruzione = new JTextField(); //18
  	//Textarea
  	private static JTextArea textAreaTestoAnnuncio = new JTextArea(); //1
  	//Combobox
  	private static JComboBox<String> comboBoxRegione = new JComboBox<String>();
  	private static JComboBox<String> comboBoxProvincia = new JComboBox<String>();
  	private static JComboBox<String> comboBoxComune = new JComboBox<String>();
  	private static JComboBox<String> comboBoxCategoriaImmobile = new JComboBox<String>();
  	private static JComboBox<String> comboBoxTipologiaImmobile = new JComboBox<String>();
  	private static JComboBox<String> comboBoxTipologiaContratto = new JComboBox<String>(); //6
  	
  	private static JComboBox<String> comboBoxNumeroLocali = new JComboBox<String>();
  	private static JComboBox<String> comboBoxNumeroCamere = new JComboBox<String>();
  	private static JComboBox<String> comboBoxNumeroBagni = new JComboBox<String>();
  	private static JComboBox<String> comboBoxStatoImmobile = new JComboBox<String>();
  	private static JComboBox<String> comboBoxArredamenti = new JComboBox<String>();
  	private static JComboBox<String> comboBoxPiano = new JComboBox<String>();
  	private static JComboBox<String> comboBoxCertificazioniEnergetiche = new JComboBox<String>();
  	private static JComboBox<String> comboBoxTipologiaRiscaldamento = new JComboBox<String>();
  	private static JComboBox<String> comboBoxClima = new JComboBox<String>();
  	private static JComboBox<String> comboBoxParcheggio = new JComboBox<String>();
  	private static JComboBox<String> comboBoxGiardino = new JComboBox<String>(); //11
  	//Pulsanti
  	private static JButton btnImmagine1 = new JButton("Immagine1");
  	private static JButton btnImmagine2 = new JButton("Immagine2");
  	private static JButton btnImmagine3 = new JButton("Immagine3");
  	private static JButton btnImmagine4 = new JButton("Immagine4");
  	private static JButton btnImmagine5 = new JButton("Immagine5");
  	private static JButton btnImmagine6 = new JButton("Immagine6");
  	private static JButton btnImmagine7 = new JButton("Immagine7");
  	private static JButton btnImmagine8 = new JButton("Immagine8");
  	private static JButton btnImmagine9 = new JButton("Immagine9");
  	private static JButton btnImmagine10 = new JButton("Immagine10"); //10
  	//Checkbox
  	private static JCheckBox chckbxBandaLarga = new JCheckBox("Banda larga");
  	private static JCheckBox chckbxAscensore = new JCheckBox("Ascensore");
  	private static JCheckBox chckbxCasaEcologica = new JCheckBox("Casa ecologica");
  	private static JCheckBox chckbxVicinanzeBus = new JCheckBox("Vicinanze bus");
  	private static JCheckBox chckbxVistaDiPregio = new JCheckBox("Vista di pregio");
  	private static JCheckBox chckbxSatellite = new JCheckBox("Satellite");
  	private static JCheckBox chckbxSistemaDiAllarme = new JCheckBox("Sistema di allarme");
  	private static JCheckBox chckbxCancelloElettrico = new JCheckBox("Cancello elettrico");
  	private static JCheckBox chckbxVicinanzeMetro = new JCheckBox("Vicinanze metro");
  	private static JCheckBox chckbxRampePerDisabili = new JCheckBox("Rampe per disabili");  //10
  	//TOT:56
	
	//Costruttore del pannello
	public PanelCreazioneSchedeImmobile() {
		
		//Proprieta del  pannello
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Creazione schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//Sottopannello a schede (tab)
		JTabbedPane tabbedPaneCreazioneSchede = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPaneCreazioneSchede, BorderLayout.NORTH);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*-----------------------------------------------------------------------------------------*/
		//Tab "Dati obbligatori"
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
				RowSpec.decode("2dlu:grow(4)"),
				RowSpec.decode("default:grow"),}));
		
		//Codice inserzione
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
		
		mapCampiForm.put("textFieldCodiceInserzione", textFieldCodiceInserzione);
		listCampiForm.add(textFieldCodiceInserzione);
		listCampiFormObbligatori.add(textFieldCodiceInserzione);
		panelCodiceInserzione.add(textFieldCodiceInserzione, "2, 1, left, default");
		textFieldCodiceInserzione.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldCodiceInserzione.setToolTipText("Inserire un codice per identificare univocamente l'annuncio");
		textFieldCodiceInserzione.setColumns(10);
		textFieldCodiceInserzione.addKeyListener(new KeyAdapter() {   
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')  || (c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) ||  (c == KeyEvent.VK_MINUS)  || (c == KeyEvent.VK_DELETE)) || textFieldCodiceInserzione.getText().length()>=maxCaratteri.get("txtCodiceInserzione")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		lblCodiceInserzione.setLabelFor(textFieldCodiceInserzione);
		
		//Titolo annuncio
		JPanel panelTitoloAnnuncio = new JPanel();
		panelTabDatiObbligatori.add(panelTitoloAnnuncio, "1, 4, fill, fill");
		panelTitoloAnnuncio.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));
		
		JLabel lblTitoloAnnuncio = new JLabel("Titolo annuncio");
		panelTitoloAnnuncio.add(lblTitoloAnnuncio, "1, 1, fill, fill");
		
		mapCampiForm.put("textFieldTitoloAnnuncio", textFieldTitoloAnnuncio);
		listCampiForm.add(textFieldTitoloAnnuncio);
		listCampiFormObbligatori.add(textFieldTitoloAnnuncio);
		lblTitoloAnnuncio.setLabelFor(textFieldTitoloAnnuncio);
		panelTitoloAnnuncio.add(textFieldTitoloAnnuncio, "2, 1, left, default");
		textFieldTitoloAnnuncio.setToolTipText("Inserire un titolo per l'annuncio");
		textFieldTitoloAnnuncio.addKeyListener(new KeyAdapter() {     
            public void keyTyped(KeyEvent e) {
                if (textFieldTitoloAnnuncio.getText().length()>=150) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		textFieldTitoloAnnuncio.setColumns(23);
		
		//Regione-Provincia-Comune
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
		
		mapCampiForm.put("comboBoxRegione", comboBoxRegione);
		listCampiForm.add(comboBoxRegione);
		listCampiFormObbligatori.add(comboBoxRegione);
		comboBoxRegione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Select: Seleziona Regione");
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
		
		mapCampiForm.put("comboBoxProvincia", comboBoxProvincia);
		listCampiForm.add(comboBoxProvincia);
		listCampiFormObbligatori.add(comboBoxProvincia);
		comboBoxProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Select: Seleziona Provincia");
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
		
		mapCampiForm.put("comboBoxComune", comboBoxComune);
		listCampiForm.add(comboBoxComune);
		listCampiFormObbligatori.add(comboBoxComune);
		lblComune.setLabelFor(comboBoxComune);
		panelRegProvCom.add(comboBoxComune, "2, 5, left, default");
		comboBoxComune.setToolTipText("Selezionare il comune");
		
		//CAP
		JPanel panelCap = new JPanel();
		panelTabDatiObbligatori.add(panelCap, "1, 8, fill, fill");
		panelCap.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JLabel lblCap = new JLabel("CAP");
		panelCap.add(lblCap, "1, 1, fill, fill");
		
		mapCampiForm.put("textFieldCap", textFieldCap);
		listCampiForm.add(textFieldCap);
		listCampiFormObbligatori.add(textFieldCap);
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
		
		//Indirizzo
		JPanel panelIndirizzo = new JPanel();
		panelTabDatiObbligatori.add(panelIndirizzo, "1, 10, fill, fill");
		panelIndirizzo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("50dlu:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));
		
		JLabel lblIndirizzoLocalita = new JLabel("Indirizzo/Località");
		panelIndirizzo.add(lblIndirizzoLocalita, "1, 1, fill, fill");
		
		mapCampiForm.put("textFieldIndirizzoLocalita", textFieldIndirizzoLocalita);
		listCampiForm.add(textFieldIndirizzoLocalita);
		listCampiFormObbligatori.add(textFieldIndirizzoLocalita);
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
		
		//Testo scheda
		JLabel lblTestoDellaScheda = new JLabel("Testo dell'annuncio");
		lblTestoDellaScheda.setToolTipText("");
		panelTabDatiObbligatori.add(lblTestoDellaScheda, "1, 12, default, fill");
		
		JScrollPane scrollPaneTestoScheda = new JScrollPane();
		panelTabDatiObbligatori.add(scrollPaneTestoScheda, "1, 13, fill, fill");
		
		mapCampiForm.put("textAreaTestoAnnuncio", textAreaTestoAnnuncio);
		listCampiForm.add(textAreaTestoAnnuncio);
		listCampiFormObbligatori.add(textAreaTestoAnnuncio);
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
		
		//Categoria e tipologia di immobile
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
		
		mapCampiForm.put("comboBoxCategoriaImmobile", comboBoxCategoriaImmobile);
		listCampiForm.add(comboBoxCategoriaImmobile);
		listCampiFormObbligatori.add(comboBoxCategoriaImmobile);
		comboBoxCategoriaImmobile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Select: Seleziona Categoria immobile");
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
		
		mapCampiForm.put("comboBoxTipologiaImmobile", comboBoxTipologiaImmobile);
		listCampiForm.add(comboBoxTipologiaImmobile);
		listCampiFormObbligatori.add(comboBoxTipologiaImmobile);
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
		
		//Superficie immobile
		JLabel lblSuperficieImmmobile = new JLabel("Superficie (mq)");
		panelSuperficie.add(lblSuperficieImmmobile, "1, 1, fill, fill");
		
		mapCampiForm.put("textFieldSuperficieImmobile", textFieldSuperficieImmobile);
		listCampiForm.add(textFieldSuperficieImmobile);
		listCampiFormObbligatori.add(textFieldSuperficieImmobile);
		lblSuperficieImmmobile.setLabelFor(textFieldSuperficieImmobile);
		panelSuperficie.add(textFieldSuperficieImmobile, "2, 1, left, default");
		textFieldSuperficieImmobile.setToolTipText("Inserire la superficie dell'immobile in metri quadrati");
		textFieldSuperficieImmobile.setColumns(10);
		textFieldSuperficieImmobile.addKeyListener(new KeyAdapter() {     //Ascoltatore interno al JTextField per impedire l'immissione di caratteri non validi
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))  || textFieldSuperficieImmobile.getText().length()>=maxCaratteri.get("textSuperficieImmobile")) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		
		//Tipologia contratto
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
		
		mapCampiForm.put("comboBoxTipologiaContratto", comboBoxTipologiaContratto);
		listCampiForm.add(comboBoxTipologiaContratto);
		listCampiFormObbligatori.add(comboBoxTipologiaContratto);
		comboBoxTipologiaContratto.setModel(new DefaultComboBoxModel<String>(arrayTipologieContratto));
		lblTipologiaContratto.setLabelFor(comboBoxTipologiaContratto);
		comboBoxTipologiaContratto.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelTipologiaContratto.add(comboBoxTipologiaContratto, "2, 1, left, default");
		comboBoxTipologiaContratto.setToolTipText("");
		
		//Prezzo
		JPanel panelPrezzo = new JPanel();
		panelTabDatiObbligatori.add(panelPrezzo, "1, 21, fill, fill");
		panelPrezzo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("121px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.MIN_ROWSPEC,}));
		
		JLabel lblPrezzoImmobile = new JLabel("Prezzo");
		panelPrezzo.add(lblPrezzoImmobile, "1, 1, fill, fill");
		
		mapCampiForm.put("textFieldPrezzoImmobile", textFieldPrezzoImmobile);
		listCampiForm.add(textFieldPrezzoImmobile);
		listCampiFormObbligatori.add(textFieldPrezzoImmobile);
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
		
		//Pulsanti Creazione e Resetta 
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
				System.out.print("Creazione della scheda immobile...");				
				if(isFormValid()) { 
					System.out.println(" ...form valido... ");
					
					//Disabilito i campi della form
					disabilitaCampiForm();
					
					//Istanzio l'oggetto schda e lo salvo nel file
             	   	SchedaImmobile schedaImmobile = new SchedaImmobile();
             	   	aggiungiScheda(schedaImmobile);
             	 
             	   	//Il pannello centrale viene ridisegnato
             	    j2web_GUI.panelListaSchedeImmobile.updatePanello();
             	   
             	    //Il pannello di destra viene ridisegnato
             	    j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello();
             	    
             	    System.out.print(" fatto." + "\n");
				}
				else {
					System.out.println(" ...form non valido. Scheda non creata.");
					JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("creazioneDellaSchedaImmobile_FormNonValido"), "Errore", JOptionPane.ERROR_MESSAGE);
				}
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
				System.out.print("Resetto la form...");
				resettaForm();
				System.out.print(" fatto." + "\n");
			}
		});
		btnResettaPannelloCreazioneScheda.setToolTipText("Resetta il pannello di creazione scheda");
		panelPulsantiCreazioneScheda.add(btnResettaPannelloCreazioneScheda, "3, 1");
		/*-----------------------------------------------------------------------------------------*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*-----------------------------------------------------------------------------------------*/
		//Tab "Immagini"
		JPanel panelTabImmagini = new JPanel();
		tabbedPaneCreazioneSchede.addTab("Immagini", null, panelTabImmagini, null);
		panelTabImmagini.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(54dlu;default)"),
				ColumnSpec.decode("max(12dlu;default)"),
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
		
		//Pulsante immmagine 1
		mapCampiForm.put("btnImmagine1", btnImmagine1);
		listCampiForm.add(btnImmagine1);
		btnImmagine1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 1");
				selezionaImmagine(textFieldImmagine1);
			}
		});
		btnImmagine1.setToolTipText("Inserimento immagine 1");
		panelTabImmagini.add(btnImmagine1, "1, 2");
		
		mapCampiForm.put("textFieldImmagine1", textFieldImmagine1);
		listCampiForm.add(textFieldImmagine1);
		textFieldImmagine1.setToolTipText("Inserimento immagine 1");
		panelTabImmagini.add(textFieldImmagine1, "3, 2, left, default");
		textFieldImmagine1.setColumns(23);
		
		//Pulsante immmagine 2
		mapCampiForm.put("btnImmagine2", btnImmagine2);
		listCampiForm.add(btnImmagine2);
		btnImmagine2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 2");
				selezionaImmagine(textFieldImmagine2);
			}
		});
		btnImmagine2.setToolTipText("Inserimento immagine 2");
		panelTabImmagini.add(btnImmagine2, "1, 4, default, fill");
		
		mapCampiForm.put("textFieldImmagine2", textFieldImmagine2);
		listCampiForm.add(textFieldImmagine2);
		textFieldImmagine2.setToolTipText("Inserimento immagine 2");
		panelTabImmagini.add(textFieldImmagine2, "3, 4, left, default");
		textFieldImmagine2.setColumns(23);
		
		//Pulsante immmagine 3
		mapCampiForm.put("btnImmagine3", btnImmagine3);
		listCampiForm.add(btnImmagine3);
		btnImmagine3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 3");
				selezionaImmagine(textFieldImmagine3);
			}
		});
		btnImmagine3.setToolTipText("Inserimento immagine 3");
		panelTabImmagini.add(btnImmagine3, "1, 6, default, fill");
		
		mapCampiForm.put("textFieldImmagine3", textFieldImmagine3);
		listCampiForm.add(textFieldImmagine3);
		textFieldImmagine3.setToolTipText("Inserimento immagine 3");
		panelTabImmagini.add(textFieldImmagine3, "3, 6, left, default");
		textFieldImmagine3.setColumns(23);
		
		//Pulsante immmagine 4
		mapCampiForm.put("btnImmagine4", btnImmagine4);
		listCampiForm.add(btnImmagine4);
		btnImmagine4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 4");
				selezionaImmagine(textFieldImmagine4);
			}
		});
		btnImmagine4.setToolTipText("Inserimento immagine 4");
		panelTabImmagini.add(btnImmagine4, "1, 8, default, fill");
		
		mapCampiForm.put("textFieldImmagine4", textFieldImmagine4);
		listCampiForm.add(textFieldImmagine4);
		textFieldImmagine4.setToolTipText("Inserimento immagine 4");
		panelTabImmagini.add(textFieldImmagine4, "3, 8, left, default");
		textFieldImmagine4.setColumns(23);
		
		//Pulsante immmagine 5
		mapCampiForm.put("btnImmagine5", btnImmagine5);
		listCampiForm.add(btnImmagine5);
		btnImmagine5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 5");
				selezionaImmagine(textFieldImmagine5);
			}
		});
		btnImmagine5.setToolTipText("Inserimento immagine 5");
		panelTabImmagini.add(btnImmagine5, "1, 10, default, fill");
		
		mapCampiForm.put("textFieldImmagine5", textFieldImmagine5);
		listCampiForm.add(textFieldImmagine5);
		textFieldImmagine5.setToolTipText("Inserimento immagine 5");
		panelTabImmagini.add(textFieldImmagine5, "3, 10, left, default");
		textFieldImmagine5.setColumns(23);
		
		//Pulsante immmagine 6
		mapCampiForm.put("btnImmagine6", btnImmagine6);
		listCampiForm.add(btnImmagine6);
		btnImmagine6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 6");
				selezionaImmagine(textFieldImmagine6);
			}
		});
		btnImmagine6.setToolTipText("Inserimento immagine 6");
		panelTabImmagini.add(btnImmagine6, "1, 12, default, fill");
		
		mapCampiForm.put("textFieldImmagine6", textFieldImmagine6);
		listCampiForm.add(textFieldImmagine6);
		textFieldImmagine6.setToolTipText("Inserimento immagine 6");
		panelTabImmagini.add(textFieldImmagine6, "3, 12, left, default");
		textFieldImmagine6.setColumns(23);
		
		//Pulsante immmagine 7
		mapCampiForm.put("btnImmagine7", btnImmagine7);
		listCampiForm.add(btnImmagine7);
		btnImmagine7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 7");
				selezionaImmagine(textFieldImmagine7);
			}
		});
		btnImmagine7.setToolTipText("Inserimento immagine 7");
		panelTabImmagini.add(btnImmagine7, "1, 14");
		
		mapCampiForm.put("textFieldImmagine7", textFieldImmagine7);
		listCampiForm.add(textFieldImmagine7);
		textFieldImmagine7.setToolTipText("Inserimento immagine 7");
		panelTabImmagini.add(textFieldImmagine7, "3, 14, left, default");
		textFieldImmagine7.setColumns(23);
		
		//Pulsante immmagine 8
		mapCampiForm.put("btnImmagine8", btnImmagine8);
		listCampiForm.add(btnImmagine8);
		btnImmagine8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 8");
				selezionaImmagine(textFieldImmagine8);
			}
		});
		btnImmagine8.setToolTipText("Inserimento immagine 8");
		panelTabImmagini.add(btnImmagine8, "1, 16");
		
		mapCampiForm.put("textFieldImmagine8", textFieldImmagine8);
		listCampiForm.add(textFieldImmagine8);
		textFieldImmagine8.setToolTipText("Inserimento immagine 8");
		panelTabImmagini.add(textFieldImmagine8, "3, 16, left, default");
		textFieldImmagine8.setColumns(23);
		
		//Pulsante immmagine 9
		mapCampiForm.put("btnImmagine9", btnImmagine9);
		listCampiForm.add(btnImmagine9);
		btnImmagine9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 9");
				selezionaImmagine(textFieldImmagine9);
			}
		});
		btnImmagine9.setToolTipText("Inserimento immagine 9");
		panelTabImmagini.add(btnImmagine9, "1, 18");
		
		mapCampiForm.put("textFieldImmagine9", textFieldImmagine9);
		listCampiForm.add(textFieldImmagine9);
		textFieldImmagine9.setToolTipText("Inserimento immagine 9");
		panelTabImmagini.add(textFieldImmagine9, "3, 18, left, default");
		textFieldImmagine9.setColumns(23);
		
		//Pulsante immmagine 10
		mapCampiForm.put("btnImmagine10", btnImmagine10);
		listCampiForm.add(btnImmagine10);
		btnImmagine10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button - Immagine 10");
				selezionaImmagine(textFieldImmagine10);
			}
		});
		btnImmagine10.setToolTipText("Inserimento immagine 10");
		panelTabImmagini.add(btnImmagine10, "1, 20");
		
		mapCampiForm.put("textFieldImmagine10", textFieldImmagine10);
		listCampiForm.add(textFieldImmagine10);
		textFieldImmagine10.setToolTipText("Inserimento immagine 10");
		panelTabImmagini.add(textFieldImmagine10, "3, 20, left, default");
		textFieldImmagine10.setColumns(23);
		/*-----------------------------------------------------------------------------------------*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*-----------------------------------------------------------------------------------------*/
		//Tab "Dati secondari"
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
		
		//Numero locali
		JLabel lblNumeroLocali = new JLabel("Numero locali");
		lblNumeroLocali.setToolTipText("Selezionare il numero dei locali dell'immobile");
		panelTabDatiSecondari.add(lblNumeroLocali, "1, 2, left, default");
		
		mapCampiForm.put("comboBoxNumeroLocali", comboBoxNumeroLocali);
		listCampiForm.add(comboBoxNumeroLocali);
		comboBoxNumeroLocali.setModel(new DefaultComboBoxModel<String>(arrayNumeroLocali));
		comboBoxNumeroLocali.setToolTipText("Selezionare il numero dei locali dell'immobile");
		lblNumeroLocali.setLabelFor(comboBoxNumeroLocali);
		panelTabDatiSecondari.add(comboBoxNumeroLocali, "2, 2, left, default");
		
		//Numero camere
		JLabel lblNumeroCamere = new JLabel("Numero camere da letto");
		lblNumeroCamere.setToolTipText("Selezionare il numero di camere da letto disponibili");
		panelTabDatiSecondari.add(lblNumeroCamere, "1, 4");
		
		mapCampiForm.put("comboBoxNumeroCamere", comboBoxNumeroCamere);
		listCampiForm.add(comboBoxNumeroCamere);
		comboBoxNumeroCamere.setModel(new DefaultComboBoxModel<String>(arrayNumeroCamere));
		comboBoxNumeroCamere.setToolTipText("Selezionare il numero di camere da letto disponibili");
		lblNumeroCamere.setLabelFor(comboBoxNumeroCamere);
		panelTabDatiSecondari.add(comboBoxNumeroCamere, "2, 4, left, default");
		
		//Numero bagni
		JLabel lblNumeroBagni = new JLabel("Numero bagni");
		lblNumeroBagni.setToolTipText("Selezionare il numero di bagni disponibili");
		panelTabDatiSecondari.add(lblNumeroBagni, "1, 6");
		
		mapCampiForm.put("comboBoxNumeroBagni", comboBoxNumeroBagni);
		listCampiForm.add(comboBoxNumeroBagni);
		comboBoxNumeroBagni.setModel(new DefaultComboBoxModel<String>(arrayNumeroBagni));
		comboBoxNumeroBagni.setToolTipText("Selezionare il numero di bagni disponibili");
		lblNumeroBagni.setLabelFor(comboBoxNumeroBagni);
		panelTabDatiSecondari.add(comboBoxNumeroBagni, "2, 6, left, default");
		
		//Stato dell'immmobile
		JLabel lblStatoImmobile = new JLabel("Stato dell'immobile");
		lblStatoImmobile.setToolTipText("Selezionare lo stato attuale dell'immobile");
		panelTabDatiSecondari.add(lblStatoImmobile, "1, 8");
		
		mapCampiForm.put("comboBoxStatoImmobile", comboBoxStatoImmobile);
		listCampiForm.add(comboBoxStatoImmobile);
		comboBoxStatoImmobile.setModel(new DefaultComboBoxModel<String>(arrayStatoImmobile));
		comboBoxStatoImmobile.setToolTipText("Selezionare lo stato attuale dell'immobile");
		lblStatoImmobile.setLabelFor(comboBoxStatoImmobile);
		panelTabDatiSecondari.add(comboBoxStatoImmobile, "2, 8");
		
		//Arredamenti
		JLabel lblArredamenti = new JLabel("Arredamenti");
		lblArredamenti.setToolTipText("Selezionare la condizione attuale dell'arredamento");
		panelTabDatiSecondari.add(lblArredamenti, "1, 10");
		
		mapCampiForm.put("comboBoxArredamenti", comboBoxArredamenti);
		listCampiForm.add(comboBoxArredamenti);
		comboBoxArredamenti.setModel(new DefaultComboBoxModel<String>(arrayArredamenti));
		comboBoxArredamenti.setToolTipText("Selezionr la condizione attuale dell'arredamento");
		lblArredamenti.setLabelFor(comboBoxArredamenti);
		panelTabDatiSecondari.add(comboBoxArredamenti, "2, 10");
		
		//Piano
		JLabel lblPiano = new JLabel("Piano");
		lblPiano.setToolTipText("Selezionare il piano in cui è situato l'immobile");
		panelTabDatiSecondari.add(lblPiano, "1, 12");
		
		mapCampiForm.put("comboBoxPiano", comboBoxPiano);
		listCampiForm.add(comboBoxPiano);
		comboBoxPiano.setModel(new DefaultComboBoxModel<String>(arrayPiano));
		comboBoxPiano.setToolTipText("Selezionare il piano in cui è situato l'immobile");
		lblPiano.setLabelFor(comboBoxPiano);
		panelTabDatiSecondari.add(comboBoxPiano, "2, 12");
		
		//Tot piani
		JLabel lblNumeroTotalePiani = new JLabel("Numero totale di piani");
		lblNumeroTotalePiani.setToolTipText("Indicare il numero totale dei piani dell'immobile");
		panelTabDatiSecondari.add(lblNumeroTotalePiani, "1, 14");
		lblNumeroTotalePiani.setLabelFor(textFieldNumeroTotalePiani);
		
		mapCampiForm.put("textFieldNumeroTotalePiani", textFieldNumeroTotalePiani);
		listCampiForm.add(textFieldNumeroTotalePiani);
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
		
		//Certificazioni energetiche
		JLabel lblCertificazioniEnergetiche = new JLabel("Certificazioni energetiche");
		lblCertificazioniEnergetiche.setToolTipText("Selezionre la certificazione energetica");
		panelTabDatiSecondari.add(lblCertificazioniEnergetiche, "1, 16");
		
		mapCampiForm.put("comboBoxCertificazioniEnergetiche", comboBoxCertificazioniEnergetiche);
		listCampiForm.add(comboBoxCertificazioniEnergetiche);
		comboBoxCertificazioniEnergetiche.setModel(new DefaultComboBoxModel<String>(arrayCertificazioniEnergetiche));
		comboBoxCertificazioniEnergetiche.setToolTipText("Selezionre la certificazione energetica");
		lblCertificazioniEnergetiche.setLabelFor(comboBoxCertificazioniEnergetiche);
		panelTabDatiSecondari.add(comboBoxCertificazioniEnergetiche, "2, 16");
		
		//Tipologia riscaldamento
		JLabel lbTipologiaRiscaldamento = new JLabel("Tipologia riscaldamento");
		lbTipologiaRiscaldamento.setToolTipText("Selezionare la tipologia di riscaldamento dell'immobile");
		panelTabDatiSecondari.add(lbTipologiaRiscaldamento, "1, 18");
		
		mapCampiForm.put("comboBoxTipologiaRiscaldamento", comboBoxTipologiaRiscaldamento);
		listCampiForm.add(comboBoxTipologiaRiscaldamento);
		comboBoxTipologiaRiscaldamento.setModel(new DefaultComboBoxModel<String>(arrayTipologieRiscaldamento));
		comboBoxTipologiaRiscaldamento.setToolTipText("Selezionare la tipologia di riscaldamento dell'immobile");
		panelTabDatiSecondari.add(comboBoxTipologiaRiscaldamento, "2, 18");
		
		//Clima
		JLabel lblClima = new JLabel("Clima");
		lblClima.setToolTipText("Selezionare la tipologia di climatizzazione dell'immobile");
		panelTabDatiSecondari.add(lblClima, "1, 20");
		
		mapCampiForm.put("comboBoxClima", comboBoxClima);
		listCampiForm.add(comboBoxClima);
		comboBoxClima.setModel(new DefaultComboBoxModel<String>(arrayClima));
		comboBoxClima.setToolTipText("Selezionare la tipologia di climatizzazione dell'immobile");
		panelTabDatiSecondari.add(comboBoxClima, "2, 20");
		
		//Parcheggio
		JLabel lblParcheggio = new JLabel("Parcheggio");
		lblParcheggio.setToolTipText("Selezionare la tipologia di parcheggio disponibile");
		panelTabDatiSecondari.add(lblParcheggio, "1, 22");
		
		mapCampiForm.put("comboBoxParcheggio", comboBoxParcheggio);
		listCampiForm.add(comboBoxParcheggio);
		comboBoxParcheggio.setModel(new DefaultComboBoxModel<String>(arrayParcheggio));
		comboBoxParcheggio.setToolTipText("Selezionare la tipologia di parcheggio disponibile");
		panelTabDatiSecondari.add(comboBoxParcheggio, "2, 22");
		lblParcheggio.setLabelFor(comboBoxParcheggio);
		
		//Anno costruzione
		JLabel lblAnnoCostruzione = new JLabel("Anno costruzione");
		lblAnnoCostruzione.setToolTipText("Inserire l'anno di costruzione dell'immobile");
		panelTabDatiSecondari.add(lblAnnoCostruzione, "1, 24");
		
		mapCampiForm.put("textFieldAnnoCostruzione", textFieldAnnoCostruzione);
		listCampiForm.add(textFieldAnnoCostruzione);
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
		
		//Giardino
		JLabel lblGiardino = new JLabel("Giardino");
		lblGiardino.setToolTipText("Selezionare la tipologia di giardino disponibile");
		panelTabDatiSecondari.add(lblGiardino, "1, 26");
		
		mapCampiForm.put("comboBoxGiardino", comboBoxGiardino);
		listCampiForm.add(comboBoxGiardino);
		comboBoxGiardino.setModel(new DefaultComboBoxModel<String>(arrayGiardino));
		comboBoxGiardino.setToolTipText("Selezionare la tipologia di giardino disponibile");
		panelTabDatiSecondari.add(comboBoxGiardino, "2, 26");
		
		//Banda larga
		mapCampiForm.put("chckbxBandaLarga", chckbxBandaLarga);
		listCampiForm.add(chckbxBandaLarga);
		chckbxBandaLarga.setToolTipText("Banda larga");
		panelTabDatiSecondari.add(chckbxBandaLarga, "1, 30");
		
		//Satellite
		mapCampiForm.put("chckbxSatellite", chckbxSatellite);
		listCampiForm.add(chckbxSatellite);
		chckbxSatellite.setToolTipText("Satellite");
		panelTabDatiSecondari.add(chckbxSatellite, "2, 30");
		
		//Ascensore
		mapCampiForm.put("chckbxAscensore", chckbxAscensore);
		listCampiForm.add(chckbxAscensore);
		chckbxAscensore.setToolTipText("Ascensore");
		panelTabDatiSecondari.add(chckbxAscensore, "1, 32");
		
		//Sistema di allarme
		mapCampiForm.put("chckbxSistemaDiAllarme", chckbxSistemaDiAllarme);
		listCampiForm.add(chckbxSistemaDiAllarme);
		chckbxSistemaDiAllarme.setToolTipText("Sistema di allarme");
		panelTabDatiSecondari.add(chckbxSistemaDiAllarme, "2, 32");
		
		//Casa ecologica
		mapCampiForm.put("chckbxCasaEcologica", chckbxCasaEcologica);
		listCampiForm.add(chckbxCasaEcologica);
		chckbxCasaEcologica.setToolTipText("Casa ecologica");
		panelTabDatiSecondari.add(chckbxCasaEcologica, "1, 34");
		
		//Cancello elettrico
		mapCampiForm.put("chckbxCancelloElettrico", chckbxCancelloElettrico);
		listCampiForm.add(chckbxCancelloElettrico);
		chckbxCancelloElettrico.setToolTipText("Cancello elettrico");
		panelTabDatiSecondari.add(chckbxCancelloElettrico, "2, 34");
		
		//Vicinanze bus
		mapCampiForm.put("chckbxVicinanzeBus", chckbxVicinanzeBus);
		listCampiForm.add(chckbxVicinanzeBus);
		chckbxVicinanzeBus.setToolTipText("Vicinanze bus");
		panelTabDatiSecondari.add(chckbxVicinanzeBus, "1, 36");
		
		//Vicinanze metro
		mapCampiForm.put("chckbxVicinanzeMetro", chckbxVicinanzeMetro);
		listCampiForm.add(chckbxVicinanzeMetro);
		chckbxVicinanzeMetro.setToolTipText("Vicinanze metro");
		panelTabDatiSecondari.add(chckbxVicinanzeMetro, "2, 36");
		
		//Vista di pregio
		mapCampiForm.put("chckbxVistaDiPregio", chckbxVistaDiPregio);
		listCampiForm.add(chckbxVistaDiPregio);
		chckbxVistaDiPregio.setToolTipText("Vista di pregio");
		panelTabDatiSecondari.add(chckbxVistaDiPregio, "1, 38");
		
		//Rampe per disabili
		mapCampiForm.put("chckbxRampePerDisabili", chckbxRampePerDisabili);
		listCampiForm.add(chckbxRampePerDisabili);
		chckbxRampePerDisabili.setToolTipText("Rampe per disabili");
		panelTabDatiSecondari.add(chckbxRampePerDisabili, "2, 38");
		/*-----------------------------------------------------------------------------------------*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
    }	//Fine costruttore 
	
	
	//Popola la combobox Provincia quando viene modificata la Regione
	static void popolaComboBoxProvincia(String regione) {
		//Rimuovo tutte le opzioni della select Provincia
		//comboBoxProvincia.removeAllItems();
		//Rimuovo tutte le opzioni della select Comune
		comboBoxComune.removeAllItems();
		if(!regione.equals(arrayRegioni[0])) {
			comboBoxProvincia.setModel(new DefaultComboBoxModel<String>(regioneProvincie.get(regione)));
		}	
	}
	
	//Popola la combobox Comune quando viene modificata la Provincia
	static void popolaComboBoxComune(String provincia) {
		if(!provincia.equals(arrayProvincie[0])) {
			comboBoxComune.setModel(new DefaultComboBoxModel<String>(provinciaComuni.get(provincia)));
		}
	}
	
	//Popola la combobox Categoria immobile quando viene modificata la Tipologia immobile
	static void popolaComboBoxTipologiaImmobile(String categoria) {
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
            	MapModalWindowsDialogs.get("selezioneFileImmagne_SelezioneNonValida");
            }	
        }
	}

	//Resetta la form
	@SuppressWarnings("unchecked")
	static void resettaForm() {
		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = (JComponent)iteratorListCampiForm.next();
			switch (campoCorrente.getClass().getName())
			{
			    case "javax.swing.JTextField": //Campo testuale
			    	((JTextComponent) campoCorrente).setText("");
			    	campoCorrente.setEnabled(true);
			        break;
			    case "javax.swing.JTextArea": //Text Area
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
			    default://
			}
		}	
	}
		
	//Disabilita i campi della form
	static void disabilitaCampiForm() {
		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = (JComponent)iteratorListCampiForm.next();
			campoCorrente.setEnabled(false);
		}
	}
		
	//Verifica la validità della form
	@SuppressWarnings("unchecked")
	static boolean isFormValid() {
		
		String comboContent;
		
		ListIterator<JComponent> iteratorListCampiFormObbligatori = listCampiFormObbligatori.listIterator();
		while(iteratorListCampiFormObbligatori.hasNext()) {
			JComponent campoCorrente = (JComponent)iteratorListCampiFormObbligatori.next();
			switch (campoCorrente.getClass().getName())
			{
			    case "javax.swing.JTextField": //Campo testuale
			    	if(((JTextComponent) campoCorrente).getText().isEmpty()) {
			    		return false;
					}
			        break;
			    case "javax.swing.JTextArea": //Text Area
			    	if(((JTextArea) campoCorrente).getText().isEmpty()) {
			    		return false;
					}
			        break;
			    case "javax.swing.JComboBox": //Select		    	
			    	comboContent = (String)((JComboBox<String>) campoCorrente).getSelectedItem();
					if(comboContent.isEmpty() || comboContent.contains("Seleziona ")) { //Attenzione: il controllo viene effettuato sulla parola "Seleziona "
						return false;
					}
			        break;
			}	
		}
		return true;	
	}

	//Il nuovo oggetto scheda immobile viene inserito nella struttura dati e salvato nel file .dat relativo a tutte le schede
	static void aggiungiScheda(SchedaImmobile scheda) {
	
		//Aggiorno la lista delle schede immobile
		j2web_GUI.listSchedeImmobile.add(scheda);
			
		//Aggiorno il file dat delle schede
		j2web.salvaListaSchedeImmobiliCreate();
		
	}
		
	// Metodo per ricompilare i dati della form con quelli presenti in una scheda appena viene selezionata
	public void mostraSchedaSalvata(SchedaImmobile scheda) {
		
		resettaForm();		
		
		//Campi testuali
	  	textFieldCodiceInserzione.setText(scheda.codiceInserzione);
	  	textFieldTitoloAnnuncio.setText(scheda.titoloAnnuncio);
	  	textFieldCap.setText(scheda.cap);
	  	textFieldIndirizzoLocalita.setText(scheda.indirizzoLocalita);
	  	textFieldSuperficieImmobile.setText(scheda.superficieImmobile);
	  	textFieldPrezzoImmobile.setText(scheda.prezzoImmobile);
	  	
	  	if(scheda.arrayImages[0]!=null) {
	  		textFieldImmagine1.setText(scheda.arrayImages[0].getPath());
	  	}
	  	if(scheda.arrayImages[1]!=null) {
	  		textFieldImmagine2.setText(scheda.arrayImages[1].getPath());
	  	}
	  	if(scheda.arrayImages[2]!=null) {
	  		textFieldImmagine3.setText(scheda.arrayImages[2].getPath());
	  	}
	  	if(scheda.arrayImages[3]!=null) {
	  		textFieldImmagine4.setText(scheda.arrayImages[3].getPath());
	  	}
	  	if(scheda.arrayImages[4]!=null) {
	  		textFieldImmagine5.setText(scheda.arrayImages[4].getPath());
	  	}
	  	if(scheda.arrayImages[5]!=null) {
	  		textFieldImmagine6.setText(scheda.arrayImages[5].getPath());
	  	}
	  	if(scheda.arrayImages[6]!=null) {
	  		textFieldImmagine7.setText(scheda.arrayImages[6].getPath());
	  	}
	  	if(scheda.arrayImages[7]!=null) {
	  		textFieldImmagine8.setText(scheda.arrayImages[7].getPath());
	  	}
	  	if(scheda.arrayImages[8]!=null) {
	  		textFieldImmagine9.setText(scheda.arrayImages[8].getPath());
	  	}
	  	if(scheda.arrayImages[9]!=null) {
	  		textFieldImmagine10.setText(scheda.arrayImages[9].getPath());
	  	}
	  	
	  	textFieldNumeroTotalePiani.setText(scheda.numeroTotalePiani);
	  	textFieldAnnoCostruzione.setText(scheda.annoCostruzione);//18
	  	//Textarea
	  	textAreaTestoAnnuncio.setText(scheda.testoAnnuncio); //1
	  	//Combobox
	  	comboBoxRegione.setSelectedIndex(scheda.comboBoxRegioneIndex);
	  	comboBoxProvincia.setSelectedIndex(scheda.comboBoxProvinciaIndex);
	  	comboBoxComune.setSelectedIndex(scheda.comboBoxComuneIndex);
	  	comboBoxCategoriaImmobile.setSelectedIndex(scheda.comboBoxCategoriaImmobileIndex);
	  	comboBoxTipologiaImmobile.setSelectedIndex(scheda.comboBoxTipologiaImmobileIndex);
	  	comboBoxTipologiaContratto.setSelectedIndex(scheda.comboBoxTipologiaContrattoIndex); //6
	  	
	  	comboBoxNumeroLocali.setSelectedIndex(scheda.comboBoxNumeroLocaliIndex);
	  	comboBoxNumeroCamere.setSelectedIndex(scheda.comboBoxNumeroCamereIndex);
	  	comboBoxNumeroBagni.setSelectedIndex(scheda.comboBoxNumeroBagniIndex);
	  	comboBoxStatoImmobile.setSelectedIndex(scheda.comboBoxStatoImmobileIndex);
	  	comboBoxArredamenti.setSelectedIndex(scheda.comboBoxArredamentiIndex);
	  	comboBoxPiano.setSelectedIndex(scheda.comboBoxPianoIndex);
	  	comboBoxCertificazioniEnergetiche.setSelectedIndex(scheda.comboBoxCertificazioniEnergeticheIndex);
	  	comboBoxTipologiaRiscaldamento.setSelectedIndex(scheda.comboBoxTipologiaRiscaldamentoIndex);
	  	comboBoxClima.setSelectedIndex(scheda.comboBoxClimaIndex);
	  	comboBoxParcheggio.setSelectedIndex(scheda.comboBoxParcheggioIndex);
	  	comboBoxGiardino.setSelectedIndex(scheda.comboBoxGiardinoIndex); //11
	  	
	  	//Checkbox
	  	chckbxBandaLarga.setSelected(scheda.bandaLarga);
	  	chckbxAscensore.setSelected(scheda.ascensore);
	  	chckbxCasaEcologica.setSelected(scheda.casaEcologica);
	  	chckbxVicinanzeBus.setSelected(scheda.vicinanzeBus);
	  	chckbxVistaDiPregio.setSelected(scheda.vistaDiPregio);
	  	chckbxSatellite.setSelected(scheda.satellite);
	  	chckbxSistemaDiAllarme.setSelected(scheda.sistemaDiAllarme);
	  	chckbxCancelloElettrico.setSelected(scheda.cancelloElettrico);
	  	chckbxVicinanzeMetro.setSelected(scheda.vicinanzeMetro);
	  	chckbxRampePerDisabili.setSelected(scheda.rampePerDisabili);
	  	//TOT:56

	    disabilitaCampiForm();	    
	    
	}   //Fine metodo mostraSchedaSalvata
        

}   //Fine 