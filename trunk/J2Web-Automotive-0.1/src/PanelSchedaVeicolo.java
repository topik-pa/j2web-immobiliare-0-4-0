//Questa classe definisce tutti i sottopannelli schede veicolo

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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComponent;


class PanelSchedaVeicolo extends JPanel implements parametriGenerali{   

	//Il pannello di sincronizzazione viene aggiornato dalla selezione di una scheda veicolo
	JPanel pannelloListaPortali = J2Web_UI.getPanel_10();

	private static final long serialVersionUID = 1L;

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
		setMaximumSize(new Dimension(400, 130));

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

		//La label delle schede
		String labelScheda = scheda.marcaVeicolo + " " + scheda.modelloVeicolo + " " + scheda.versioneVeicolo + " - " + scheda.carrozzeriaVeicolo + " " + scheda.coloreEsternoVeicolo;
		String tooltipScheda = labelScheda;
		labelScheda+="                              "; //Aggiungo 30 caratteri spazio
		if(labelScheda.length()>28) {	//è molto probabile che lo sia... :)
			labelScheda = labelScheda.substring(0, 27); 
		}		 
		labelScheda+="...";

		JLabel label = new JLabel(labelScheda);
		Font font = new Font("Monospaced", Font.PLAIN, 11);
		label.setFont(font);
		label.setHorizontalTextPosition(SwingConstants.LEFT);

		//L'immagine di preview del pannello veicolo
		BufferedImage imgPreview = null;
		if(scheda.arrayImages[1]!=null) {
			try {
				imgPreview = ImageIO.read(scheda.arrayImages[1]);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Image resizedimg = imgPreview.getScaledInstance(70, 50, Image.SCALE_FAST);
			label.setIcon(new ImageIcon(resizedimg));
		}

		add(label, BorderLayout.CENTER);

		//Aggiungo una tooltip
		setToolTipText(tooltipScheda);

		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_26, BorderLayout.SOUTH);
		panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		//Pulsante "Cancella"
		btnCancellaScheda = new JButton("Cancella");
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
				matrix[row][6] = J2Web_UI.listSchedeClientiMatch.get(row).nomeCliente;
				matrix[row][7] = J2Web_UI.listSchedeClientiMatch.get(row).CAPCliente;
				matrix[row][8] = J2Web_UI.listSchedeClientiMatch.get(row).cittaCliente;
			}	
			JTable table = new JTable(matrix, new String[] {
					"Nome", "Cognome", "Email", "Telefono 1", "Telefono 2", "Via", "Numero civico", "CAP", "Città"
			});
			/*table.setModel(new DefaultTableModel(
					matrix,
					new String[] {
							"Nome", "Cognome", "Email", "Telefono 1", "Telefono 2", "Via", "Numero civico", "CAP", "Città"
					}
					));
			table.*/
			pannelloMatchVeicoloCliente.add(table);

		}
	}

	private void  mostraDatiScheda(SchedaVeicolo schedaVeicolo) {

		J2Web_UI.disabilitaCampiForm(listCampiFormVeicolo);

		J2Web_UI.nonUserSelection = true;

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

		J2Web_UI.getTextField_Kw().setText(schedaVeicolo.KWVeicolo);
		J2Web_UI.getTextField_Cv().setText(schedaVeicolo.CVVeicolo);
		J2Web_UI.getTextField_Chilometraggio().setText(schedaVeicolo.chilometraggioVeicolo);
		J2Web_UI.getTextField_Prezzo().setText(schedaVeicolo.prezzoVeicolo);
		J2Web_UI.getTextField_Cilindrata().setText(schedaVeicolo.cilindrataVeicolo);
		J2Web_UI.getTextField_ConsumoMedio().setText(schedaVeicolo.comsumeMedioVeicolo);
		J2Web_UI.getTextField_YouTubeUrl().setText(schedaVeicolo.urlVideoYouTube);
		J2Web_UI.getTextFieldRagioneSociale().setText(schedaVeicolo.ragioneSociale);
		J2Web_UI.getTextFieldReferente().setText(schedaVeicolo.nomeReferente);
		J2Web_UI.getTextFieldIndirizzo().setText(schedaVeicolo.Indirizzo);
		J2Web_UI.getTextFieldTelefonoGenerico().setText(schedaVeicolo.Telefono);
		J2Web_UI.getTextFieldTelefonoReferente().setText(schedaVeicolo.TelefonoReferente);
		J2Web_UI.getTextFieldEmailReferente().setText(schedaVeicolo.emailReferente);

		J2Web_UI.getTextPane_Descrizione().setText(schedaVeicolo.descrizioneVeicolo);	

		if(schedaVeicolo.veicolo=="auto") {
			J2Web_UI.getRdbtnAutoveicolo().setSelected(true);
		}
		else {
			J2Web_UI.getRdbtnMotoScooter().setSelected(false);
		}

		if(schedaVeicolo.coloreMetalizzato) {
			J2Web_UI.getChckbxMetallizzato().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxMetallizzato().setSelected(false);
		}
		if(schedaVeicolo.prezzoTrattabile) {
			J2Web_UI.getChckbxTrattabile().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxTrattabile().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaABS) {
			J2Web_UI.getChckbxAbs().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxAbs().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaAirBag) {
			J2Web_UI.getChckbxAirbag().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxAirbag().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaAntifurto) {
			J2Web_UI.getChckbxAntifurto().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxAntifurto().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaChiusuraCentralizzata) {
			J2Web_UI.getChckbxChiusuraCentralizzata().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxChiusuraCentralizzata().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaContrlAutomTrazione) {
			J2Web_UI.getChckbxContrAutomTrazione().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxContrAutomTrazione().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaESP) {
			J2Web_UI.getChckbxEsp().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxEsp().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaImmobilizer) {
			J2Web_UI.getChckbxImmobilizer().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxImmobilizer().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaFreniADisco) {
			J2Web_UI.getChckbxFreniADisco().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxFreniADisco().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaCupolino) {
			J2Web_UI.getChckbxCupolino().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxCupolino().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaAlzacristalliElettrici) {
			J2Web_UI.getChckbxAlzacristalliElettrici().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxAlzacristalliElettrici().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaClima) {
			J2Web_UI.getChckbxClima().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxClima().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaNavigatoreSattelitare) {
			J2Web_UI.getChckbxNavigatoreSatellitare().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxNavigatoreSatellitare().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaRadioOLettoreCD) {
			J2Web_UI.getChckbxRadiolettoreCd().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxRadiolettoreCd().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaParkDistControl) {
			J2Web_UI.getChckbxParkDistControl().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxParkDistControl().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaServoSterzo) {
			J2Web_UI.getChckbxServosterzo().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxServosterzo().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaSediliRiscaldati) {
			J2Web_UI.getChckbxSediliRiscaldati().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxSediliRiscaldati().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaVolanteMultifunzione) {
			J2Web_UI.getChckbxVolanteMultifunzione().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxVolanteMultifunzione().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaAllestimentoHandicap) {
			J2Web_UI.getChckbxHandicap().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxHandicap().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaCerchiInLega) {
			J2Web_UI.getChckbxCerchiInLega().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxCerchiInLega().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaGancioTraino) {
			J2Web_UI.getChckbxGancioTraino().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxGancioTraino().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaPortaPacchi) {
			J2Web_UI.getChckbxPortapacchi().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxPortapacchi().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaSediliSportivi) {
			J2Web_UI.getChckbxSediliSportivi().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxSediliSportivi().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaBauletto) {
			J2Web_UI.getChckbxBauletto().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxBauletto().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaAvviamentoAPedale) {
			J2Web_UI.getChckbxAvviamentoAPedale().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxAvviamentoAPedale().setSelected(false);
		}
		if(schedaVeicolo.disponibilitaAvviamentoElettrico) {
			J2Web_UI.getChckbxAvviamentoElettrico().setSelected(true);
		}
		else {
			J2Web_UI.getChckbxAvviamentoElettrico().setSelected(false);
		}



		for(int i=1; i<scheda.arrayImages.length; i++) {
			if(scheda.arrayImages[i]!=null && scheda.arrayImages[i].isFile()) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(scheda.arrayImages[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Image resizedimg = img.getScaledInstance(70, 50, Image.SCALE_FAST);
				Icon icoImmagine = new ImageIcon(resizedimg);

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
			else {
				switch (i)
				{
				case 1: 
					J2Web_UI.getLabel_Immagine1().setIcon(null);
					break;
				case 2: 
					J2Web_UI.getLabel_Immagine2().setIcon(null);
					break;
				case 3: 
					J2Web_UI.getLabel_Immagine3().setIcon(null);
					break;
				case 4: 
					J2Web_UI.getLabel_Immagine4().setIcon(null);
					break;
				case 5: 
					J2Web_UI.getLabel_Immagine5().setIcon(null);
					break;
				case 6: 
					J2Web_UI.getLabel_Immagine6().setIcon(null);
					break;
				case 7: 
					J2Web_UI.getLabel_Immagine7().setIcon(null);
					break;
				case 8: 
					J2Web_UI.getLabel_Immagine8().setIcon(null);
					break;
				case 9: 
					J2Web_UI.getLabel_Immagine9().setIcon(null);
					break;
				default:
					J2Web_UI.getLabel_Immagine10().setIcon(null);
				}
			}

		}	

		J2Web_UI.nonUserSelection = false;
	}

	
}