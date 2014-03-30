/**
 * Questa classe definisce tutti i sottopannelli schede veicolo
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComponent;


class PanelSchedaVeicolo extends JPanel implements parametriGenerali{   

	private static final long serialVersionUID = 3014910631292246664L;

	//Il pannello di sincronizzazione viene aggiornato dalla selezione di una scheda veicolo
	JPanel pannelloListaPortali = J2Web_UI.getPanel_10();

	SchedaVeicolo scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;

	String labelSpaziatore = "   "; 

	//Costruttore
	public PanelSchedaVeicolo(final SchedaVeicolo scheda, final LinkedList<SchedaVeicolo> listaSchedeVeicolo, final ButtonGroup radioGrpSchede) {

		this.scheda = scheda;
		idScheda = scheda.idScheda;
		codiceScheda = scheda.codiceScheda;

		setLayout(new BorderLayout(0, 0));
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setMaximumSize(new Dimension(400, 150));

		//Radio button dei sottopannelli
		schedaRadio = new JRadioButton("Seleziona scheda veicolo");
		//Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		radioGrpSchede.add(schedaRadio); 
		//Clicco su una radio button di una scheda veicolo
		schedaRadio.addActionListener(new ActionListener() {			 
			public void actionPerformed(ActionEvent e) {
				System.out.println("Scheda veicolo selezionata: " + scheda.codiceScheda); 

				//Devo caricare la relativa hashtable contenente i portali in cui è inserita e gli associati codici di inserimento
				scheda.caricaTabellaHash();

				//Il pannello di destra (sincronizzazione) deve essere aggiornato
				PanelSicronizzazioneConPortali.panelInserimentoInActiveMode(pannelloListaPortali, scheda, true);

				//Coloro il bordo della scheda
				Component[] wrapperScheda = getParent().getComponents();
				for(int i=0; i<wrapperScheda.length; i++) {
					if(wrapperScheda[i].getClass().toString().contains("PanelSchedaVeicolo"))  {
						((JComponent) wrapperScheda[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
					}
				}
				setBorder(new LineBorder(Color.ORANGE));

				//Eseguo un match vaicolo-cliente in locale
				matchVeicoloCliente(scheda);

				//I dati della scheda selezionata sono visualizzati nella form
				mostraDatiScheda(scheda);

				J2Web_UI.getBtnInserisciSchedaVeicolo().setEnabled(false);
			}
		});
		add(schedaRadio, BorderLayout.NORTH);

		String linea1 = scheda.marcaVeicolo + " " + scheda.modelloVeicolo;	
		String linea2 = scheda.versioneVeicolo;if(linea2.length()>45) {linea2 = linea2.substring(0, 44);}
		String linea3 = scheda.carrozzeriaVeicolo + " " + scheda.coloreEsternoVeicolo;if(linea3.length()>45) {linea3 = linea3.substring(0, 44);}

		String labelScheda = "<html><p style='padding:5px;'><strong>" + linea1 + "</strong><br/><i>" + linea2 + "</i><br/>" + linea3 + "</p></html>";
		String tooltipScheda = labelScheda;

		JLabel label = new JLabel(labelScheda);
		Font font = new Font("Monospaced", Font.PLAIN, 11);
		label.setFont(font);
		label.setHorizontalTextPosition(SwingConstants.LEFT);

		//L'immagine di preview del pannello veicolo
		Image resizedimg = new BufferedImage(70,50,BufferedImage.TYPE_INT_ARGB_PRE);
		BufferedImage imgPreview = null;
		if(scheda.arrayImages[1]!=null) {
			try {
				imgPreview = ImageIO.read(scheda.arrayImages[1]);
				resizedimg = imgPreview.getScaledInstance(70, 50, Image.SCALE_FAST);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaSchedeVeicolo_ImmaginiNonDisponibili"), "Impossibile recuperare le immagini delle schede veicolo", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
		}

		add(label, BorderLayout.CENTER);
		add(new JLabel(" ", new ImageIcon(resizedimg), JLabel.RIGHT),BorderLayout.EAST);

		//Aggiungo una tooltip
		setToolTipText(tooltipScheda);

		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_26, BorderLayout.SOUTH);
		panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		//Pulsante "Cancella"
		btnCancellaScheda = new JButton("Elimina scheda");
		btnCancellaScheda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancella scheda veicolo: " + scheda.idScheda);

				//Rimozione di una scheda veicolo dalla LinkedList
				ListIterator<SchedaVeicolo> iterator = listaSchedeVeicolo.listIterator();
				while(iterator.hasNext()) {
					SchedaVeicolo schedaCorrente = iterator.next();
					//La rimozione avviene confrontando l'id univoco della scheda
					if(schedaCorrente.idScheda==idScheda) {
						iterator.remove();
						System.out.println("Scheda veicolo rimossa dalla linkedlist");
					}
				}

				//Aggiorno il file dat delle schede
				j2web.salvaListaSchedeVeicoloCreate();

				//Eliminazione del file dat con la hashtable
				File removeFile = new File("pathSchede" + idScheda + "-" + idScheda + ".dat");
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
				J2Web_UI.aggiornaPannelloListaSchedeVeicolo();

				//Aggiornamento del pannello di destra, la scheda corrente è stata cancellata
				J2Web_UI.aggiornaPannelloListaPortaliSincronizzazione();
			}
		});
		panel_26.add(btnCancellaScheda);

	}

	//Funzione di matching tra veicoli e clienti salvati in locale
	private void  matchVeicoloCliente(SchedaVeicolo schedaVeicolo) {

		//Il pannello inferiore
		JPanel pannelloMatchVeicoloCliente = J2Web_UI.getPanel_4();

		J2Web_UI.listSchedeClientiMatch.clear();

		pannelloMatchVeicoloCliente.removeAll();

		boolean matchPositivo = false;

		//Ciclo tra tutte le schede clienti disponibili e aggiungo ad una lista le schede che corrispondono ai criteri preimpostati
		ListIterator<SchedaCliente> iterator = J2Web_UI.listSchedeCliente.listIterator();
		while(iterator.hasNext()) {
			SchedaCliente schedaCorrente = iterator.next();
			if(
					(schedaVeicolo.marcaVeicolo.equalsIgnoreCase(schedaCorrente.marcaVeicoloCliente) && schedaVeicolo.modelloVeicolo.equalsIgnoreCase(schedaCorrente.modelloVeicoloCliente)) ||
					(schedaVeicolo.versioneVeicolo.equalsIgnoreCase(schedaCorrente.versioneVeicoloCliente)) ||
					(schedaVeicolo.marcaVeicolo.equalsIgnoreCase(schedaCorrente.marcaVeicoloCliente) && schedaVeicolo.carburanteVeicolo.equalsIgnoreCase(schedaCorrente.tipologiaCarburanteVeicoloCliente)) ||
					(schedaVeicolo.marcaVeicolo.equalsIgnoreCase(schedaCorrente.marcaVeicoloCliente) && schedaVeicolo.coloreEsternoVeicolo.equalsIgnoreCase(schedaCorrente.coloreEsternoVeicoloCliente)) ||
					(schedaVeicolo.marcaVeicolo.equalsIgnoreCase(schedaCorrente.marcaVeicoloCliente) && schedaVeicolo.tipologiaVeicolo.equalsIgnoreCase(schedaCorrente.tipologiaVeicoloCliente))

					) {
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

			//Stampo una tabella con i dati dei potenziali clienti interessati
			String[][] matrix = new String[J2Web_UI.listSchedeClientiMatch.size()][9];	
			for (int row = 0; row < matrix.length; row++) {
				matrix[row][0] = J2Web_UI.listSchedeClientiMatch.get(row).nomeCliente;
				matrix[row][1] = J2Web_UI.listSchedeClientiMatch.get(row).cognomeCliente;
				matrix[row][2] = J2Web_UI.listSchedeClientiMatch.get(row).emailCliente;
				matrix[row][3] = J2Web_UI.listSchedeClientiMatch.get(row).telefono1Cliente;
				matrix[row][4] = J2Web_UI.listSchedeClientiMatch.get(row).telefono2Cliente;
				matrix[row][5] = J2Web_UI.listSchedeClientiMatch.get(row).viaCliente;
				matrix[row][6] = J2Web_UI.listSchedeClientiMatch.get(row).numeroCivicoCliente;
				matrix[row][7] = J2Web_UI.listSchedeClientiMatch.get(row).CAPCliente;
				matrix[row][8] = J2Web_UI.listSchedeClientiMatch.get(row).cittaCliente;
			}	
			JTable table = new JTable(matrix, new String[] {
					"Nome", "Cognome", "Email", "Telefono 1", "Telefono 2", "Via", "Numero civico", "CAP", "Città"
			});

			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setFillsViewportHeight(true);

			pannelloMatchVeicoloCliente.add(table.getTableHeader(), BorderLayout.PAGE_START);
			pannelloMatchVeicoloCliente.add(table, BorderLayout.CENTER);

		}
	}

	//Mostra sul pannello form i dati della scheda correntemente selezionata
	private void  mostraDatiScheda(SchedaVeicolo schedaVeicolo) {

		J2Web_UI.disabilitaCampiForm(listCampiFormVeicolo);

		J2Web_UI.nonUserSelection = true;

		//Combobox
		J2Web_UI.getComboBox_Marca().setSelectedIndex(schedaVeicolo.marcaVeicoloIndex);
		J2Web_UI.getComboBox_Modello().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.modelloVeicolo}));
		J2Web_UI.getComboBox_Versione().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.versioneVeicolo}));
		J2Web_UI.getComboBox_MeseImmatricolazione().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.meseImmatricolazioneVeicolo}));
		J2Web_UI.getComboBox_AnnoImmatricolazione().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.annoImmatricolazioneVeicolo}));
		J2Web_UI.getComboBox_Carburante().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.carburanteVeicolo}));
		J2Web_UI.getComboBox_Tipologia().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.tipologiaVeicolo}));
		J2Web_UI.getComboBox_Carrozzeria().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.carrozzeriaVeicolo}));
		J2Web_UI.getComboBox_PostiASedere().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.postiASedereVeicolo}));
		J2Web_UI.getComboBox_ColoreEsterno().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.coloreEsternoVeicolo}));
		J2Web_UI.getComboBox_PrecedentiProprietari().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.numeroPrecedentiProprietariVeicolo}));
		J2Web_UI.getComboBox_FinitureInterni().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.finitureInterneVeicolo}));
		J2Web_UI.getComboBox_ColoreInterni().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.coloreInterniVeicolo}));
		J2Web_UI.getComboBox_Motore().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.tipologiaMotoreVeicolo}));
		J2Web_UI.getComboBox_Cambio().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.tipologiaCambioVeicolo}));
		J2Web_UI.getComboBox_NumeroRapporti().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.numeroRapportiVeicolo}));
		J2Web_UI.getComboBox_ClasseEmissioni().setModel(new DefaultComboBoxModel<String>(new String[]{schedaVeicolo.classeEmissioniVeicolo}));

		//Textfield
		J2Web_UI.getTextField_Kw().setText(schedaVeicolo.KWVeicolo);
		J2Web_UI.getTextField_Cv().setText(schedaVeicolo.CVVeicolo);
		J2Web_UI.getTextField_Chilometraggio().setText(schedaVeicolo.chilometraggioVeicolo);
		J2Web_UI.getTextField_Prezzo().setText(schedaVeicolo.prezzoVeicolo);
		J2Web_UI.getTextField_Cilindrata().setText(schedaVeicolo.cilindrataVeicolo);
		J2Web_UI.getTextField_ConsumoMedio().setText(schedaVeicolo.consumoMedioVeicolo);
		J2Web_UI.getTextField_YouTubeUrl().setText(schedaVeicolo.urlVideoYouTube);
		J2Web_UI.getTextFieldRagioneSociale().setText(schedaVeicolo.ragioneSociale);
		J2Web_UI.getTextFieldReferente().setText(schedaVeicolo.nomeReferente);
		J2Web_UI.getTextFieldIndirizzo().setText(schedaVeicolo.Indirizzo);
		J2Web_UI.getTextFieldTelefonoGenerico().setText(schedaVeicolo.Telefono);
		J2Web_UI.getTextFieldTelefonoReferente().setText(schedaVeicolo.TelefonoReferente);
		J2Web_UI.getTextFieldEmailReferente().setText(schedaVeicolo.emailReferente);

		//Textpane
		J2Web_UI.getTextPane_Descrizione().setText(schedaVeicolo.descrizioneVeicolo);	

		//Checkbox
		J2Web_UI.getRdbtnAutoveicolo().setSelected(schedaVeicolo.veicolo=="auto");
		J2Web_UI.getChckbxMetallizzato().setSelected(schedaVeicolo.coloreMetalizzato);
		//J2Web_UI.getChckbxTrattabile().setSelected(scheda.prezzoTrattabile);
		J2Web_UI.getChckbxAbs().setSelected(scheda.disponibilitaABS);
		J2Web_UI.getChckbxAirbag().setSelected(scheda.disponibilitaAirBag);	
		J2Web_UI.getChckbxAntifurto().setSelected(scheda.disponibilitaAntifurto);	
		J2Web_UI.getChckbxChiusuraCentralizzata().setSelected(scheda.disponibilitaChiusuraCentralizzata);		
		J2Web_UI.getChckbxContrAutomTrazione().setSelected(scheda.disponibilitaContrlAutomTrazione);		
		J2Web_UI.getChckbxEsp().setSelected(scheda.disponibilitaESP);		
		J2Web_UI.getChckbxImmobilizer().setSelected(scheda.disponibilitaImmobilizer);	
		J2Web_UI.getChckbxFreniADisco().setSelected(scheda.disponibilitaFreniADisco);		
		J2Web_UI.getChckbxCupolino().setSelected(scheda.disponibilitaCupolino);		
		J2Web_UI.getChckbxAlzacristalliElettrici().setSelected(scheda.disponibilitaAlzacristalliElettrici);		
		J2Web_UI.getChckbxClima().setSelected(scheda.disponibilitaClima);		
		J2Web_UI.getChckbxNavigatoreSatellitare().setSelected(scheda.disponibilitaNavigatoreSatellitare);		
		J2Web_UI.getChckbxRadiolettoreCd().setSelected(scheda.disponibilitaRadioOLettoreCD);		
		J2Web_UI.getChckbxParkDistControl().setSelected(scheda.disponibilitaParkDistControl);		
		J2Web_UI.getChckbxServosterzo().setSelected(scheda.disponibilitaServoSterzo);		
		J2Web_UI.getChckbxSediliRiscaldati().setSelected(scheda.disponibilitaSediliRiscaldati);	
		J2Web_UI.getChckbxVolanteMultifunzione().setSelected(scheda.disponibilitaVolanteMultifunzione);		
		J2Web_UI.getChckbxHandicap().setSelected(scheda.disponibilitaAllestimentoHandicap);	
		J2Web_UI.getChckbxCerchiInLega().setSelected(scheda.disponibilitaCerchiInLega);		
		J2Web_UI.getChckbxGancioTraino().setSelected(scheda.disponibilitaGancioTraino);	
		J2Web_UI.getChckbxPortapacchi().setSelected(scheda.disponibilitaPortaPacchi);	
		J2Web_UI.getChckbxSediliSportivi().setSelected(scheda.disponibilitaSediliSportivi);		
		J2Web_UI.getChckbxBauletto().setSelected(scheda.disponibilitaBauletto);		
		J2Web_UI.getChckbxAvviamentoAPedale().setSelected(scheda.disponibilitaAvviamentoAPedale);
		J2Web_UI.getChckbxAvviamentoElettrico().setSelected(scheda.disponibilitaAvviamentoElettrico);

		for(int i=1; i<scheda.arrayImages.length; i++) {
			Icon icoImmagine = null;

			if(scheda.arrayImages[i]!=null && scheda.arrayImages[i].isFile()) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(scheda.arrayImages[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Image resizedimg = img.getScaledInstance(70, 50, Image.SCALE_FAST);
				icoImmagine = new ImageIcon(resizedimg);
			}

			switch (i)
			{
			case 1: 
				J2Web_UI.getLabel_Immagine1().setIcon(icoImmagine);
				break;
			case 2: 
				J2Web_UI.getLabel_Immagine2().setIcon(icoImmagine);
				break;
			case 3: 
				J2Web_UI.getLabel_Immagine3().setIcon(icoImmagine);
				break;
			case 4: 
				J2Web_UI.getLabel_Immagine4().setIcon(icoImmagine);
				break;
			case 5: 
				J2Web_UI.getLabel_Immagine5().setIcon(icoImmagine);
				break;
			case 6: 
				J2Web_UI.getLabel_Immagine6().setIcon(icoImmagine);
				break;
			case 7: 
				J2Web_UI.getLabel_Immagine7().setIcon(icoImmagine);
				break;
			case 8: 
				J2Web_UI.getLabel_Immagine8().setIcon(icoImmagine);
				break;
			case 9: 
				J2Web_UI.getLabel_Immagine9().setIcon(icoImmagine);
				break;
			default:
				J2Web_UI.getLabel_Immagine10().setIcon(icoImmagine);
			}	
		}	

		J2Web_UI.nonUserSelection = false;
	}

}