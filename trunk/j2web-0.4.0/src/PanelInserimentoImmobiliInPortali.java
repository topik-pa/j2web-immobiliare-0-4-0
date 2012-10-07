/*
 * Il pannello a destra, che contiene la lista dei portali immobiliari in cui inserire le schede immobile
 * Ultima modifica: 5 sett 2010
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
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
public class PanelInserimentoImmobiliInPortali extends JPanel implements parametriGenerali {
	private static final long serialVersionUID = 1L;

	//Elementi del pannello
	PanelInserimentoSequenziale panelInserimentoSequenziale;
	JPanel panelInserimentoPortale;
      
        
    //Costruttore - definisce la prima visualizzazione del pannello
    public PanelInserimentoImmobiliInPortali() {

    	//Inizializzo la lista concatenata che contiene le informazioni sui portali immobiliari
    	inizializzaPortaliAttivi();
    	
    	setBorder(new TitledBorder(null, "Inserimento schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));    	
    	
    	//Visualizzo il pannello nella sua configurazione di default
    	panelInserimentoInDefaultMode();

    }   //Fine costruttore PannelloInserimento
        
  
        //Update del pannello, eseguito alla selezione di una scheda sul pannello centrale e dopo l'inserimento/rimozione di una scheda in un portale
    public void updatePanello(final SchedaImmobile scheda, final boolean selectAllSelected) {
    	
    	//Rimuovo tutti i portali dalle liste sequenziali
    	j2web_GUI.listPortaliInserimentoSequenziale.clear();
    	j2web_GUI.listPortaliCancellazioneSequenziale.clear();
    	//j2web_GUI.mapPortaliInserimentoSequenziale.clear();
    	//j2web_GUI.mapPortaliCancellazioneSequenziale.clear();
    	        	
    	//Rimuovo tutti gli elementi dal pannello
    	removeAll();
    	      	
    	panelInserimentoInActiveMode(scheda, selectAllSelected);
     	 
     	updateUI();
     	
    }
           
    //Update del pannello eseguito al momento in cui viene cancellata una scheda sul pannello centrale o inserita una dal pannello Form: nessuna scheda selezionata
    public void updatePanello() {
    	
    	removeAll();
    	        	
    	panelInserimentoInDefaultMode();
    	        	
     	updateUI();   
     	
    }

    public void inizializzaPortaliAttivi() {
    	//PortaleImmobiliare immobiliarePuntoIt = new ImmobiliarePuntoIt("./img/immobiliarePuntoIt.gif", "1 - immobiliare.it", "001");
    	//listPortaliImmobiliari.add(immobiliarePuntoIt);
    	
    	//PortaleImmobiliare casaPuntoIt = new casaPuntoIt("./img/casaPuntoIt.png", "2 - casa.it", "002");
    	//listPortaliImmobiliari.add(casaPuntoIt);
    	
    	//PortaleImmobiliare bancaDelleCase = new BancaDelleCase("./img/banca_delle_case.gif", "2 - bancadellecase.it", "003");
    	//listPortaliImmobiliari.add(bancaDelleCase);
    	
    	PortaleImmobiliare case24 = new Case24("./images/case24.gif", "1 - case24.it", "001");
    	j2web_GUI.listPortaliImmobiliari.add(case24);   
    	
    	PortaleImmobiliare case24bis = new Case24("./images/case24.gif", "1 - case24.it", "002");
    	j2web_GUI.listPortaliImmobiliari.add(case24bis);
    	
    	PortaleImmobiliare case24tris = new Case24("./images/case24.gif", "1 - case24.it", "003");
    	j2web_GUI.listPortaliImmobiliari.add(case24tris);
    	
    	PortaleImmobiliare case24bis2 = new Case24("./images/case24.gif", "1 - case24.it", "004");
    	j2web_GUI.listPortaliImmobiliari.add(case24bis2);
    	
    	PortaleImmobiliare case24bis3 = new Case24("./images/case24.gif", "1 - case24.it", "005");
    	j2web_GUI.listPortaliImmobiliari.add(case24bis3);
     	
    	//PortaleImmobiliare pagineCasa = new PagineCasa("./img/paginecasa.gif", "4 - paginecasa.it", "005");
    	//listPortaliImmobiliari.add(pagineCasa);
    	
    	/*PortaleImmobiliare cuboCasa = new CuboCasa("./img/cubocasa.gif", "5 - cubocasa.it", "005");
    	listPortaliImmobiliari.add(cuboCasa);*/
    	
    	//PortaleImmobiliare caseFvg = new CaseFvg("./img/casefvg.gif", "7 - casefvg.it", "007");
    	//listPortaliImmobiliari.add(caseFvg);
    }

    public void panelInserimentoInDefaultMode() {
    	
    	add(Box.createVerticalStrut(5));
    	        	
    	//Pannello controllo portali (primo in alto)
    	panelInserimentoSequenziale = new PanelInserimentoSequenziale();     		        
        add(panelInserimentoSequenziale);  
        
        add(Box.createVerticalStrut(10));
 	            
        //Ciclo tra i portali immobiliari presenti nella lista concatenata e per ognuno creo dei sottopannelli e dei pulsanti (fittizzi: non hanno alcuna funzionalità )
        ListIterator<PortaleImmobiliare> iterator = j2web_GUI.listPortaliImmobiliari.listIterator();
        while(iterator.hasNext()) {
        	final PortaleImmobiliare portaleCorrente = iterator.next();
        	InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente);
        	add(inserimentoPortale);
        } 
        
    	add(Box.createVerticalGlue());
    }

    public void panelInserimentoInActiveMode(final SchedaImmobile scheda, final boolean selectAll) {
    	
    	add(Box.createVerticalStrut(5));
    	      	
    	//Pannello controllo portali (primo in alto)
    	panelInserimentoSequenziale = new PanelInserimentoSequenziale(scheda, selectAll);     		        
        add(panelInserimentoSequenziale); 
        
        add(Box.createVerticalStrut(10));

    	//Ciclo ogni oggetto PortaleImmobiliare presente nella lista concatenata e per ognuno aggiorno il sottopannello
        ListIterator<PortaleImmobiliare> iterator = j2web_GUI.listPortaliImmobiliari.listIterator();
     	while(iterator.hasNext()) {	     		
     		final PortaleImmobiliare portaleCorrente = iterator.next();
     		InserimentoPortale inserimentoPortale = new InserimentoPortale(portaleCorrente, scheda, selectAll);
     		add(inserimentoPortale);
     	}
     	
     	//Spazio vuoto in fondo
    	add(Box.createVerticalGlue());        	
    }
      
  //Gestione degli errori
    public static void manageErrors(Exception e, String errorType) {
    	//String errorType = e.getClass().getName();
    	
    	sendErrorMail(readStackTrace(e), errorType);
    	
    	
    	switch (errorType)
		{
		    case "IOException": //Errore di connessione
		    	JOptionPane.showMessageDialog(null, errorType + "durante l'inserimento della scheda.\n Verificare la connessione ad Internet", errorType, JOptionPane.ERROR_MESSAGE);
		        break;
		    case "HttpResponseException":	//Errore di login
		    	JOptionPane.showMessageDialog(null, errorType + "durante l'inserimento della scheda.\n Verificare che il proprio account sia ancora valido per il portale", errorType, JOptionPane.ERROR_MESSAGE);
		    	break;
		    default://Errore generico
		    	JOptionPane.showMessageDialog(null, errorType + "durante l'inserimento della scheda.\n E' stato inviato un rapporto allo sviluppatore del software", errorType, JOptionPane.ERROR_MESSAGE);
		}
    	
        e.printStackTrace();
    }
    
  //Invio mail in caso di errori runtime
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
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
  			e.printStackTrace();
  		} catch (MessagingException e) {
  			JOptionPane.showMessageDialog(null, "Errore durante l'invio del rapporto.", "Errore", JOptionPane.ERROR_MESSAGE);
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
        






class PanelInserimentoSequenziale extends JPanel implements parametriGenerali {
	
	private static final long serialVersionUID = 1L;
	
	JButton btnInserisciTuttiIPortali;
	JButton btnCancellaTuttiIPortali;
	JCheckBox checkboxSelezionaTutti;
	
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
	
	//Pannello inserimento sequenziali nel caso di portale selezionato
	public PanelInserimentoSequenziale(final SchedaImmobile scheda, boolean selectAllSelected) {
		
		btnInserisciTuttiIPortali = new JButton(labelBtnInserisciTutti);
    	btnCancellaTuttiIPortali = new JButton(labelBtnCancellaTutti);
    	checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
    	
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(Box.createVerticalStrut(20));
		
		//Inserisce la scheda in tutti i portali selezionati
		btnInserisciTuttiIPortali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
            	ListIterator<PortaleImmobiliare> iterator = (ListIterator<PortaleImmobiliare>) j2web_GUI.listPortaliInserimentoSequenziale.iterator();
            	while(iterator.hasNext()) {
            		PortaleImmobiliare portaleCorrente = iterator.next();
            		
            		try {
     					System.out.println("Inserisci: " + scheda.codiceInserzione + " in " + portaleCorrente.idPortale);
     					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     					portaleCorrente.inserisciScheda(scheda);
     				}
     				catch (HttpCommunicationException e1 ) {
     					PanelInserimentoImmobiliInPortali.manageErrors(e1, e1.getExceptionType());
     		            return;
     				}
     				finally  {
     					setCursor(Cursor.getDefaultCursor());
     				}
            		
            		/*
            		//Il cursone viene messo in modalità attesa
     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per inserire una scheda immobile, il metoso chiamata sarà quella della sottoclasse effettiva
     				portaleCorrente.inserisciScheda(scheda);
     				//Il cursone viene messo in modalità standard
     				setCursor(Cursor.getDefaultCursor());
     				*/
            		System.out.println("Scheda " + scheda.idScheda + " inserita in: " + portaleCorrente.idPortale);
            	}
            	
                /*for(PortaleImmobiliare currentPortal : j2web_GUI.mapPortaliInserimentoSequenziale.keySet()) {
                	//Se il valore del portle corrente è "false", inserisco il portale
                	if(j2web_GUI.mapPortaliInserimentoSequenziale.get(currentPortal)==false) {
                		//currentPortal.inserisciScheda(scheda);
                		System.out.println("Scheda inserita in: " + currentPortal.idPortale);
                	}
                }*/
                
            }
            
         });
		add(btnInserisciTuttiIPortali);
		    
		//Elimina la scheda da tutti i portali selezionati
        btnCancellaTuttiIPortali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	
            	ListIterator<PortaleImmobiliare> iterator = (ListIterator<PortaleImmobiliare>) j2web_GUI.listPortaliCancellazioneSequenziale.iterator();
            	while(iterator.hasNext()) {
            		PortaleImmobiliare portaleCorrente = iterator.next();
            		
            		try {
            			System.out.println("Scheda " + scheda.idScheda + " cancellata da: " + portaleCorrente.idPortale);
     					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     					portaleCorrente.cancellaScheda(scheda);
     				}
     				catch (HttpCommunicationException e1 ) {
     					PanelInserimentoImmobiliInPortali.manageErrors(e1, e1.getExceptionType());
     		            return;
     				}
     				finally  {
     					setCursor(Cursor.getDefaultCursor());
     				}
            		
            		/*            		
            		 //Il cursone viene messo in modalità attesa
     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per inserire una scheda immobile, il metoso chiamata sarà quella della sottoclasse effettiva
     				portale.cancellaScheda(scheda);
     				//Il cursone viene messo in modalità standard
     				setCursor(Cursor.getDefaultCursor());
            		 */
            		//System.out.println("Scheda " + scheda.idScheda + " cancellata da: " + portaleCorrente.idPortale);
            	}
            	
            	
                /*for(PortaleImmobiliare currentPortal : j2web_GUI.mapPortaliCancellazioneSequenziale.keySet()) {
                	//Se il valore del portle corrente è "false", cancello il portale
                	if(j2web_GUI.mapPortaliCancellazioneSequenziale.get(currentPortal)==false) {
                		//currentPortal.cancellaScheda(scheda);
                		System.out.println("Scheda cancellata da: " + currentPortal.idPortale);
                	}
                }*/  
                
                
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
   



class InserimentoPortale extends JPanel implements parametriGenerali {
	
	private static final long serialVersionUID = 1L;
	
	JButton btnInserisci;
	JButton btnVisualizza;
	JButton btnCancella;
	
	JCheckBox checkboxSelezionaPortale;
	
	ImageIcon iconPortale;
    JLabel labelPortale;
    
    Border loweredetched;
    TitledBorder title;
	       
    //Pannello inserimento portale nel caso di nessun portale selezionato
	public InserimentoPortale(PortaleImmobiliare portale) {
		
		setLayout(new GridLayout(2,3,5,5));
		
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(loweredetched, portale.valoreLabel);
        title.setTitleJustification(TitledBorder.RIGHT);
        setBorder(title);
		
		btnInserisci = new JButton(labelBtnInserisci);
    	btnVisualizza = new JButton(labelBtnVisualizza);
    	btnCancella= new JButton(labelBtnCancella);
    	checkboxSelezionaPortale = new JCheckBox(labelCheckboxSelezionaPortale);
    	
    	iconPortale = new ImageIcon(portale.urlIcona);
        labelPortale = new JLabel(iconPortale, JLabel.CENTER);   
        	
		//Pulsante Inserisci
		btnInserisci.setEnabled(false);
		
		//Pulsante Visualizza
		btnVisualizza.setEnabled(false);
		
		//Pulsante Cancella
 		btnCancella.setEnabled(false);              
        
        add(labelPortale);
        add(new JLabel(labelSpaziatore));             
        
        checkboxSelezionaPortale.setEnabled(false);
        add(checkboxSelezionaPortale);      		
        
        add(btnInserisci);
        add(btnVisualizza);
        add(btnCancella);
        	
	}
	
	//Pannello inserimento portale nel caso di portale selezionato
	public InserimentoPortale(final PortaleImmobiliare portale, final SchedaImmobile scheda,  boolean selectAllSelected) {
		
		final boolean isOnThisPortal = scheda.isOnThisPortal(portale.idPortale);
		setLayout(new GridLayout(2,3,5,5));
		
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(loweredetched, portale.valoreLabel);
        title.setTitleJustification(TitledBorder.RIGHT);
        setBorder(title);
		
		//Pulsante Inserisci
 		btnInserisci = new JButton(labelBtnInserisci);
 		//Controllo se la scheda è inserita nel portale corrente     		
 		if(isOnThisPortal){
 			//Pulsante disabilitato
 			btnInserisci.setEnabled(false);			
 		}
 		else {
 			//Pulsante abilitato
 			btnInserisci.setEnabled(true);
 			btnInserisci.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				//System.out.println("Inserisci: " + scheda.codiceInserzione + " in " + portale.idPortale);	     				
     				//Il cursone viene messo in modalità attesa
     				//setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per inserire una scheda immobile, il metoso chiamata sarà quella della sottoclasse effettiva
     				
     				try {
     					System.out.println("Inserisci: " + scheda.codiceInserzione + " in " + portale.idPortale);
     					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     					portale.inserisciScheda(scheda);
     				}
     				catch (HttpCommunicationException e1 ) {
     					PanelInserimentoImmobiliInPortali.manageErrors(e1, e1.getExceptionType());
     		            return;
     				}
     				finally  {
     					setCursor(Cursor.getDefaultCursor());
     				}
     				
     				//portale.inserisciScheda(scheda);
     				//Il cursone viene messo in modalità standard
     				//setCursor(Cursor.getDefaultCursor());
     			}
     		});
 		}
 		
 			     		
 		//Pulsante Visualizza
 		btnVisualizza = new JButton(labelBtnVisualizza);
 		//Controllo se la scheda è inserita nel portale corrente
 		if(isOnThisPortal) {
 			btnVisualizza.setEnabled(true);
 			btnVisualizza.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				//Il cursone viene messo in modalità attesa
     				//setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				//System.out.println("Visualizza: " + scheda.codiceInserzione + " in " + portale.idPortale);
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per visualizzare una scheda immobile
     				//portale.visualizzaScheda(scheda);
     				//Il cursone viene messo in modalità standard
     				//setCursor(Cursor.getDefaultCursor());
     				
     				try {
     					System.out.println("Visualizza: " + scheda.codiceInserzione + " in " + portale.idPortale);
     					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     					portale.visualizzaScheda(scheda);
     				}
     				catch (HttpCommunicationException e1 ) {
     					PanelInserimentoImmobiliInPortali.manageErrors(e1, e1.getExceptionType());
     		            return;
     				}
     				finally  {
     					setCursor(Cursor.getDefaultCursor());
     				}
     			}
     		});
 		}
 		else {
 			btnVisualizza.setEnabled(false);
 		}
 		
 		
 		//Pulsante Cancella
 		btnCancella = new JButton(labelBtnCancella);
 		//Controllo se la scheda è inserita nel portale corrente
 		if(isOnThisPortal){
 			btnCancella.setEnabled(true);
 			btnCancella.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				
     				try {
     					System.out.println("Cancella: " + scheda.codiceInserzione + " da " + portale.idPortale);
     					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     					portale.cancellaScheda(scheda);
     				}
     				catch (HttpCommunicationException e1 ) {
     					PanelInserimentoImmobiliInPortali.manageErrors(e1, e1.getExceptionType());
     		            return;
     				}
     				finally  {
     					setCursor(Cursor.getDefaultCursor());
     				}
     				
     				System.out.println("Cancella: " + scheda.codiceInserzione + " da " + portale.idPortale);
     				//Il cursone viene messo in modalità attesa
     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per cancellare una scheda immobile
     				//portale.cancellaScheda(scheda);
     				//Il cursone viene messo in modalità standard
     				setCursor(Cursor.getDefaultCursor());
     			}
     		});
 		}
 		else {
 			btnCancella.setEnabled(false);
 		}
 				
		iconPortale = new ImageIcon(portale.urlIcona);
        labelPortale = new JLabel(iconPortale, JLabel.CENTER);
    
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
                		//j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, true);
                		//j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, false);
                		j2web_GUI.listPortaliCancellazioneSequenziale.add(portale);
                	}
                	else {
                		//j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, false);
                		//j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, true);
                		j2web_GUI.listPortaliInserimentoSequenziale.add(portale);
                	}
                }
                //checkbox non spuntata
                else {
                	if(j2web_GUI.listPortaliInserimentoSequenziale.contains(portale)) {
                		j2web_GUI.listPortaliInserimentoSequenziale.remove(portale);
                	}
                	else {
                		j2web_GUI.listPortaliCancellazioneSequenziale.remove(portale);
                	}
                	
                	/*if(j2web_GUI.mapPortaliInserimentoSequenziale.containsKey(portale)) {
                		j2web_GUI.mapPortaliInserimentoSequenziale.remove(portale);
                	}
                	else {
                		j2web_GUI.mapPortaliCancellazioneSequenziale.remove(portale);
                	}*/
                }
            }
	 	});
        
        //Se è stata selezionata la checkbox select all
        if(selectAllSelected) {
        	checkboxSelezionaPortale.setSelected(true);
        	if(isOnThisPortal) {
        		//j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, true);
        		//j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, false);
        		j2web_GUI.listPortaliCancellazioneSequenziale.add(portale);
        	}
        	else {
        		//j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, false);
        		//j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, true);
        		j2web_GUI.listPortaliInserimentoSequenziale.add(portale);
        		}       
        	}
        else {
        	checkboxSelezionaPortale.setSelected(false);
        	if(j2web_GUI.listPortaliInserimentoSequenziale.contains(portale)) {
        		j2web_GUI.listPortaliInserimentoSequenziale.remove(portale);
        	}
        	else {
        		j2web_GUI.listPortaliCancellazioneSequenziale.remove(portale);
        	}
        }
        add(checkboxSelezionaPortale);      		
        add(btnInserisci);
        add(btnVisualizza);
        add(btnCancella);
	}
	
	
	
}   //Fine PannelloInserimento