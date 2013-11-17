/*
 * Il pannello a destra, che contiene la lista dei portali immobiliari in cui inserire le schede immobile
 * Ultima modifica: 5 sett 2010
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


//Pannello per l'inserimento schede nei portali immobiliari
public class PanelSicronizzazioneConPortali extends JPanel implements parametriGenerali {

	private static final long serialVersionUID = 1L;

	//PanelInserimentoSequenziale panelInserimentoSequenziale;
	//JPanel panelInserimentoPortale;     

	//Costruttore - definisce la prima visualizzazione del pannello
	public PanelSicronizzazioneConPortali() {

		JPanel pannelloListaPortali = J2Web_UI.getPanel_10();

		setBorder(new TitledBorder(null, "Inserimento schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));    	

		//Visualizzo il pannello nella sua configurazione di default
		panelInserimentoInDefaultMode(pannelloListaPortali);

	}   //Fine costruttore PannelloInserimento

	//Update del pannello, eseguito alla selezione di una scheda sul pannello centrale
	public static void updatePanello(final SchedaVeicolo scheda, final boolean selectAllSelected) {

		JPanel pannelloListaPortali = J2Web_UI.getPanel_10();

		//Rimuovo tutti i portali dalle liste sequenziali
		listPortaliInserimentoSequenziale.clear();
		listPortaliCancellazioneSequenziale.clear();

		pannelloListaPortali.removeAll();

		panelInserimentoInActiveMode(pannelloListaPortali, scheda, selectAllSelected);

		pannelloListaPortali.updateUI();

	}

	//Update del pannello eseguito al momento in cui viene cancellata una scheda sul pannello centrale o inserita una dal pannello Form: nessuna scheda selezionata
	public void updatePanello() {

		JPanel pannelloListaPortali = J2Web_UI.getPanel_10();

		removeAll();

		panelInserimentoInDefaultMode(pannelloListaPortali);

		updateUI();   

	}

	//Pannello disabilitato (nessuna scheda selzionata)
	public static void panelInserimentoInDefaultMode(final JPanel pannelloListaPortali) {

		pannelloListaPortali.removeAll();

		pannelloListaPortali.add(Box.createVerticalStrut(6));

		//Pannello controllo portali (primo in alto)
		//panelInserimentoSequenziale = new PanelInserimentoSequenziale();     		        
		//add(panelInserimentoSequenziale);  

		//add(Box.createVerticalStrut(10));

		//Ciclo tra i portali immobiliari presenti nella lista concatenata e per ognuno creo dei sottopannelli e dei pulsanti (fittizzi: non hanno alcuna funzionalità )
		ListIterator<PortaleWeb> iterator = J2Web_UI.listPortaliSincronizzazione.listIterator();
		while(iterator.hasNext()) {
			final PortaleWeb portaleCorrente = iterator.next();
			InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente);
			pannelloListaPortali.add(inserimentoPortale);
		} 

		pannelloListaPortali.add(Box.createVerticalGlue());

		pannelloListaPortali.updateUI();
	}

	//Pannello abilitato (scheda selzionata)
	public static void panelInserimentoInActiveMode(final JPanel pannelloListaPortali, final SchedaVeicolo scheda, final boolean selectAllSelected) {

		pannelloListaPortali.removeAll();

		pannelloListaPortali.add(Box.createVerticalStrut(6));

		//Pannello controllo portali (primo in alto)
		//panelInserimentoSequenziale = new PanelInserimentoSequenziale(scheda, selectAllSelected);     		        
		//add(panelInserimentoSequenziale); 

		//Ciclo ogni oggetto PortaleWeb presente nella lista concatenata e per ognuno aggiorno il sottopannello
		ListIterator<PortaleWeb> iterator = J2Web_UI.listPortaliSincronizzazione.listIterator();
		while(iterator.hasNext()) {	     		
			final PortaleWeb portaleCorrente = iterator.next();
			InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente, scheda, selectAllSelected);
			pannelloListaPortali.add(inserimentoPortale);
		}

		pannelloListaPortali.add(Box.createVerticalGlue());

		pannelloListaPortali.updateUI();
	}

	//Gestione degli errori di comunicazione con il server remoto
	public static void manageErrorsOnPortalSubmission(Exception e1) {

		String errorType = ((HttpCommunicationException) e1).getCatchedExceptionType();

		switch (errorType)
		{
		case "IOException": //Errore standard GET/POST
			JOptionPane.showMessageDialog(null, errorType + "\n" + MapModalWindowsDialogs.get("manageErrorsOnPortalSubmission_IOException"), errorType, JOptionPane.ERROR_MESSAGE);
			break;
		case "HttpWrongResponseStatusCodeException":	//Errore status code inaspettato
			JOptionPane.showMessageDialog(null, errorType + "\n" + MapModalWindowsDialogs.get("manageErrorsOnPortalSubmission_HttpWrongResponseStatusCodeException"), errorType, JOptionPane.ERROR_MESSAGE);
			break;
		case "HttpWrongResponseHeaderException":	//Errore response header inaspettato
			JOptionPane.showMessageDialog(null, errorType + "\n" + MapModalWindowsDialogs.get("manageErrorsOnPortalSubmission_HttpWrongResponseHeaderException"), errorType, JOptionPane.ERROR_MESSAGE);
			break;
		case "HttpWrongResponseBodyException":	//Errore response body inaspettato
			JOptionPane.showMessageDialog(null, errorType + "\n" + MapModalWindowsDialogs.get("manageErrorsOnPortalSubmission_HttpWrongResponseBodyException"), errorType, JOptionPane.ERROR_MESSAGE);
			break;
		default:	//Altra tipologia di errore 
			JOptionPane.showMessageDialog(null, errorType + "\n" + MapModalWindowsDialogs.get("manageErrorsOnPortalSubmission_Generic"), errorType, JOptionPane.ERROR_MESSAGE);
			break;
		} 

		//Invio a me la mail di avviso errore runtime
		sendErrorMail(readStackTrace(e1), errorType);

		e1.printStackTrace();
	}

	//Invio a me la mail in caso di errori runtime
	static void sendErrorMail(String stackTrace, String errorCode)   {

		final String USERNAME = BACKEND_EMAIL;
		final String PASSWORD = BACKEND_EMAIL_PSW;
		final String RECIPENTS = BACKEND_EMAIL;
		final String SUBJECT = "Runtime error in imagination. Code: " + errorCode;

		final String SMTP_HOST = BACKEND_EMAIL_SMTP_HOST;
		final int SMTP_PORT = BACKEND_EMAIL_SMTP_PORT;
		final Session session = Session.getInstance(System.getProperties(), null);
		final Message msg = new MimeMessage(session);
		final String senderEmail = USERNAME.contains("@") ? USERNAME : (USERNAME + BACKEND_EMAIL_DOMAIN);
		try {
			msg.setFrom(new InternetAddress(senderEmail));
			final Address[] recipientAddresses = InternetAddress.parse(RECIPENTS);
			msg.setRecipients(Message.RecipientType.TO, recipientAddresses);

			msg.setSentDate(new Date());
			msg.setSubject(SUBJECT);
			msg.setText("Errore runtime, see the report: \n\n" + stackTrace);

			final Transport transport = session.getTransport("smtps");
			transport.connect(SMTP_HOST, SMTP_PORT, USERNAME, PASSWORD);
			transport.sendMessage(msg, recipientAddresses);
			transport.close();

		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("sendErrorMail_AddressException"), "AddressException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("sendErrorMail_MessagingException"), "MessagingException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	//printStackTrace into String
	public static String readStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}



//La classe che definisce il pannello per l'inserimento sequenziale
/*class PanelInserimentoSequenziale extends JPanel implements parametriGenerali {

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
	public PanelInserimentoSequenziale() {

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
	public PanelInserimentoSequenziale(final SchedaVeicolo scheda, boolean selectAllSelected) {

		btnInserisciTuttiIPortali = new JButton(labelBtnInserisciTutti);
    	btnCancellaTuttiIPortali = new JButton(labelBtnCancellaTutti);
    	checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(Box.createVerticalStrut(20));

		//Inserisce la scheda in tutti i portali selezionati
		btnInserisciTuttiIPortali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
            	if(!listPortaliInserimentoSequenziale.isEmpty()) {
            		ListIterator<PortaleWeb> iterator = (ListIterator<PortaleWeb>) listPortaliInserimentoSequenziale.iterator();
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
         					PanelSicronizzazioneConPortali.manageErrorsOnPortalSubmission(e1);
         				}
                	}
                	//Mostro il rapporto di inserimento
                	JOptionPane.showMessageDialog(null, "Rapporto di inserimento: \n\nScheda inserita in: \n" + rapportoInserimentiOK + "\n\nScheda non inserita in: \n" + rapportoInserimentiKO);
                	//Solo alla fine aggiorno i pulsanti del pannello inserimento
            		//j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
                	//Il cursone viene messo in modalità standard
     				setCursor(Cursor.getDefaultCursor());
            	}	
            }         
         });
		add(btnInserisciTuttiIPortali);

		//Elimina la scheda da tutti i portali selezionati
        btnCancellaTuttiIPortali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	if(!listPortaliCancellazioneSequenziale.isEmpty()) {
	            	ListIterator<PortaleWeb> iterator = (ListIterator<PortaleWeb>) listPortaliCancellazioneSequenziale.iterator();

	            	//Il cursone viene messo in modalità attesa
	 				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	            	while(iterator.hasNext()) {
	            		PortaleWeb portaleCorrente = iterator.next();         		
	            		try {
	            			System.out.println("Cancellazione della scheda " + scheda.codiceScheda + " da " + portaleCorrente.idPortale);
	     					portaleCorrente.cancellaScheda(scheda, true);
	     				}
	     				catch (HttpCommunicationException e1 ) {
	     					PanelSicronizzazioneConPortali.manageErrorsOnPortalSubmission(e1);
	     				}
	            		System.out.println("Scheda " + scheda.idScheda + " cancellata da: " + portaleCorrente.idPortale);         		            		
	            	}
	            	//Solo alla fine aggiorno i pulsanti del pannello inserimento
	        		//j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
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
        	   //j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, checkboxSelezionaTutti.isSelected());    
           }
  		 });
        add(checkboxSelezionaTutti);

        add(Box.createVerticalStrut(20));

	}

}*/



//Classe che definisce il sottopannello per l'inserimento in portale
class InserimentoPortale extends JPanel implements parametriGenerali {

	private static final long serialVersionUID = 1L;

	String labelCheckboxSelezionaPortale = "Seleziona";
	String labelBtnInserisci = "Inserisci";
	String labelBtnVisualizza = "Visualizza";
	String labelBtnCancella = "Cancella";

	String labelSpaziatore = " "; 

	JButton btnInserisci;
	JButton btnVisualizza;
	JButton btnCancella;

	JCheckBox checkboxSelezionaPortale;

	ImageIcon iconPortale;
	JLabel labelPortale;

	Border loweredetched;
	TitledBorder title;

	//Costruttore 1
	//Pannello inserimento portale nel caso di nessun portale selezionato
	public InserimentoPortale(PortaleWeb portale) {

		setLayout(new GridLayout(2,3,5,5));
		setMaximumSize(new Dimension(400, 90));

		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		title = BorderFactory.createTitledBorder(loweredetched, portale.valoreLabel);
		title.setTitleJustification(TitledBorder.RIGHT);
		title.setTitleColor(new Color(0, 0, 0));
		title.setTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 11));
		setBorder(title);

		btnInserisci = new JButton(labelBtnInserisci);
		btnVisualizza = new JButton(labelBtnVisualizza);
		btnCancella= new JButton(labelBtnCancella);
		checkboxSelezionaPortale = new JCheckBox(labelCheckboxSelezionaPortale);

		iconPortale = portale.icon;
		labelPortale = new JLabel(iconPortale, JLabel.LEFT);   

		//Pulsante Inserisci
		btnInserisci.setEnabled(false);

		//Pulsante Visualizza
		btnVisualizza.setEnabled(false);

		//Pulsante Cancella
		btnCancella.setEnabled(false);              

		add(labelPortale);
		add(new JLabel(labelSpaziatore));             

		checkboxSelezionaPortale.setEnabled(false);
		checkboxSelezionaPortale.setVisible(false);
		add(checkboxSelezionaPortale);      		

		add(btnInserisci);
		add(btnVisualizza);
		add(btnCancella);

	}

	//Costruttore 2
	//Pannello inserimento portale nel caso di portale selezionato
	public InserimentoPortale(final PortaleWeb portale, final SchedaVeicolo scheda,  boolean selectAllSelected) {

		final boolean isOnThisPortal = scheda.isOnThisPortal(portale.idPortale);
		setLayout(new GridLayout(2,3,5,5));
		setMaximumSize(new Dimension(400, 90));

		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		title = BorderFactory.createTitledBorder(loweredetched, portale.valoreLabel);
		title.setTitleJustification(TitledBorder.RIGHT);
		title.setTitleColor(new Color(0, 0, 0));
		title.setTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 11));
		setBorder(title);

		//Pulsante Inserisci
		btnInserisci = new JButton(labelBtnInserisci);
		//Controllo se la scheda è inserita nel portale corrente e se il portale è attivo 		
		if(!portale.isActive || isOnThisPortal){
			//Pulsante disabilitato
			btnInserisci.setEnabled(false);			
		}
		else {
			//Pulsante abilitato
			btnInserisci.setEnabled(true);
			btnInserisci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Inserisci: " + scheda.codiceScheda + " in " + portale.idPortale);	     				
					//Il cursone viene messo in modalità attesa
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					//Chiamo il metodo afferente all'oggetto PortaleWeb per inserire una scheda, il metooo chiamato sarà quello della sottoclasse effettiva  				
					try {
						System.out.println("Inserisci: " + scheda.codiceScheda + " in " + portale.idPortale);
						portale.inserisciScheda(scheda, false);
					}
					catch (HttpCommunicationException e1) {
						PanelSicronizzazioneConPortali.manageErrorsOnPortalSubmission(e1);
					} catch (UnsupportedEncodingException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					finally  {
						//Il cursone viene messo in modalità standard
						setCursor(Cursor.getDefaultCursor());
					}
				}
			});
		}


		//Pulsante Visualizza
		btnVisualizza = new JButton(labelBtnVisualizza);
		//Controllo se la scheda è inserita nel portale corrente e se il portale è attivo
		if(portale.isActive && isOnThisPortal) {
			btnVisualizza.setEnabled(true);
			btnVisualizza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Il cursone viene messo in modalità attesa
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					System.out.println("Visualizza: " + scheda.codiceScheda + " in " + portale.idPortale);
					try {
						portale.visualizzaScheda(scheda);
					} catch (HttpCommunicationException e1) {
						PanelSicronizzazioneConPortali.manageErrorsOnPortalSubmission(e1);
					}
					//Il cursone viene messo in modalità standard
					setCursor(Cursor.getDefaultCursor());
				}
			});
		}
		else {
			btnVisualizza.setEnabled(false);

			/*In caso mi serva per dei test
 			btnVisualizza.setEnabled(true);
 			btnVisualizza.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				//Il cursone viene messo in modalità attesa
     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
 					System.out.println("Visualizza: " + scheda.codiceInserzione + " in " + portale.idPortale);
 					try {
						portale.visualizzaScheda(scheda);
					} catch (HttpCommunicationException e1) {
						PanelInserimentoImmobiliInPortali.manageErrorsOnPortalSubmission(e1);
					}
 					//Il cursone viene messo in modalità standard
     				setCursor(Cursor.getDefaultCursor());
     			}
     		});
 			In caso mi serva per dei test*/
		}


		//Pulsante Cancella
		btnCancella = new JButton(labelBtnCancella);
		//Controllo se la scheda è inserita nel portale corrente e se il portale è attivo
		if(portale.isActive && isOnThisPortal){
			btnCancella.setEnabled(true);
			btnCancella.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Il cursone viene messo in modalità attesa
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					try {
						System.out.println("Cancella: " + scheda.codiceScheda + " da " + portale.idPortale);
						portale.cancellaScheda(scheda, false);
					}
					catch (HttpCommunicationException e1 ) {
						PanelSicronizzazioneConPortali.manageErrorsOnPortalSubmission(e1);
					}
					finally  {
						//Il cursone viene messo in modalità standard
						setCursor(Cursor.getDefaultCursor());
					}
				}
			});
		}
		else {
			btnCancella.setEnabled(false);
		}

		iconPortale = portale.icon;
		labelPortale = new JLabel(iconPortale, JLabel.LEFT);

		add(labelPortale);

		add(new JLabel(labelSpaziatore));   

		checkboxSelezionaPortale = new JCheckBox(labelCheckboxSelezionaPortale);
		checkboxSelezionaPortale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Checkbox: seleziona il portale"); 
				//Aggiungo il portale alla lista dei portali ad inserimento o cancellazione sequenziale
				//checkbox spuntata
				if(checkboxSelezionaPortale.isSelected()) {
					if(isOnThisPortal) {
						listPortaliCancellazioneSequenziale.add(portale);

						System.out.println("listPortaliCancellazioneSequenziale"); 
						Iterator<PortaleWeb> iterator = listPortaliCancellazioneSequenziale.iterator();
						while(iterator.hasNext()) {
							PortaleWeb portaleCorrente = iterator.next();
							System.out.println("Elemento: " + portaleCorrente.idPortale);
						}
						System.out.println("/listPortaliCancellazioneSequenziale");

					}
					else {
						listPortaliInserimentoSequenziale.add(portale);

						System.out.println("listPortaliInserimentoSequenziale"); 
						Iterator<PortaleWeb> iterator = listPortaliInserimentoSequenziale.iterator();
						while(iterator.hasNext()) {
							PortaleWeb portaleCorrente = iterator.next();
							System.out.println("Elemento: " + portaleCorrente.idPortale);
						}
						System.out.println("/listPortaliInserimentoSequenziale"); 

					}
				}
				//checkbox non spuntata
				else {
					if(listPortaliInserimentoSequenziale.contains(portale)) {
						listPortaliInserimentoSequenziale.remove(portale);

						System.out.println("listPortaliInserimentoSequenziale"); 
						Iterator<PortaleWeb> iterator = listPortaliInserimentoSequenziale.iterator();
						while(iterator.hasNext()) {
							PortaleWeb portaleCorrente = iterator.next();
							System.out.println("Elemento: " + portaleCorrente.idPortale);
						}
						System.out.println("/listPortaliInserimentoSequenziale");

					}
					else {
						listPortaliCancellazioneSequenziale.remove(portale);

						System.out.println("listPortaliCancellazioneSequenziale"); 
						Iterator<PortaleWeb> iterator = listPortaliCancellazioneSequenziale.iterator();
						while(iterator.hasNext()) {
							PortaleWeb portaleCorrente = iterator.next();
							System.out.println("Elemento: " + portaleCorrente.idPortale);
						}
						System.out.println("/listPortaliCancellazioneSequenziale");

					}
				}
			}
		});

		//Se è stata selezionata la checkbox select all
		if(selectAllSelected) {
			checkboxSelezionaPortale.setSelected(true);
			if(isOnThisPortal) {
				listPortaliCancellazioneSequenziale.add(portale);

				System.out.println("listPortaliCancellazioneSequenziale"); 
				Iterator<PortaleWeb> iterator = listPortaliCancellazioneSequenziale.iterator();
				while(iterator.hasNext()) {
					PortaleWeb portaleCorrente = iterator.next();
					System.out.println("Elemento: " + portaleCorrente.idPortale);
				}
				System.out.println("/listPortaliCancellazioneSequenziale");

			}
			else {
				listPortaliInserimentoSequenziale.add(portale);

				System.out.println("listPortaliInserimentoSequenziale"); 
				Iterator<PortaleWeb> iterator = listPortaliInserimentoSequenziale.iterator();
				while(iterator.hasNext()) {
					PortaleWeb portaleCorrente = iterator.next();
					System.out.println("Elemento: " + portaleCorrente.idPortale);
				}
				System.out.println("/listPortaliInserimentoSequenziale");

			}       
		}
		else {
			checkboxSelezionaPortale.setSelected(false);
			if(listPortaliInserimentoSequenziale.contains(portale)) {
				listPortaliInserimentoSequenziale.remove(portale);

				System.out.println("listPortaliInserimentoSequenziale"); 
				Iterator<PortaleWeb> iterator = listPortaliInserimentoSequenziale.iterator();
				while(iterator.hasNext()) {
					PortaleWeb portaleCorrente = iterator.next();
					System.out.println("Elemento: " + portaleCorrente.idPortale);
				}
				System.out.println("/listPortaliInserimentoSequenziale");

			}
			else {
				listPortaliCancellazioneSequenziale.remove(portale);

				System.out.println("listPortaliInserimentoSequenziale"); 
				Iterator<PortaleWeb> iterator = listPortaliInserimentoSequenziale.iterator();
				while(iterator.hasNext()) {
					PortaleWeb portaleCorrente = iterator.next();
					System.out.println("Elemento: " + portaleCorrente.idPortale);
				}
				System.out.println("/listPortaliInserimentoSequenziale");

			}
		}
		checkboxSelezionaPortale.setVisible(false);
		add(checkboxSelezionaPortale);      		
		add(btnInserisci);
		add(btnVisualizza);
		add(btnCancella);
	}

}   //Fine PannelloInserimento