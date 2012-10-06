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
import java.util.ListIterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


//Pannello per l'inserimento schede nei portali immobiliari
public class PanelInserimentoImmobiliInPortali extends JPanel {
	private static final long serialVersionUID = 1L;

	//Elementi del pannello
	PanelInserimentoSequenziale panelInserimentoSequenziale;
	JPanel panelInserimentoPortale;
      
        
    //Costruttore - definisce la prima visualizzazione del pannello
    public PanelInserimentoImmobiliInPortali() {

    	//Gestisco la lista concatenata che contiene le informazioni sui portali immobiliari
    	inizializzaPortaliAttivi();
    	
    	setBorder(new TitledBorder(null, "Inserimento schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));    	
    	
    	//Visualizzo il pannello nella sua configurazione di default
    	panelInserimentoInDefaultMode();

    }   //Fine costruttore PannelloInserimento
        
  
        //Update del pannello, eseguito alla selezione di una scheda sul pannello centrale e dopo l'inserimento/rimozione di una scheda in un portale
    public void updatePanello(final SchedaImmobile scheda, final boolean selectAll) {
    	
    	//Rimuovo tutti i portali dalle liste sequenziali
    	j2web_GUI.mapPortaliInserimentoSequenziale.clear();
    	j2web_GUI.mapPortaliCancellazioneSequenziale.clear();
    	        	
    	//Rimuovo tutti gli elementi dal pannello
    	removeAll();
    	      	
    	panelInserimentoInActiveMode(scheda, selectAll);
     	 
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
    	
    	add(Box.createVerticalStrut(20));
    	        	
    	//Pannello controllo portali (primo in alto)
    	panelInserimentoSequenziale = new PanelInserimentoSequenziale();     		        
        add(panelInserimentoSequenziale);  
        
        add(Box.createVerticalStrut(20));
 	            
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
    	
    	add(Box.createVerticalStrut(20));
    	      	
    	//Pannello controllo portali (primo in alto)
    	panelInserimentoSequenziale = new PanelInserimentoSequenziale(scheda, selectAll);     		        
        add(panelInserimentoSequenziale); 
        
        add(Box.createVerticalStrut(20));

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
        
}
        






class PanelInserimentoSequenziale extends JPanel implements parametriGenerali {
	
	private static final long serialVersionUID = 1L;
	
	JButton btnInserisciTuttiIPortali;
	JButton btnCancellaTuttiIPortali;
	JCheckBox checkboxSelezionaTutti;
	
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
	
	public PanelInserimentoSequenziale(final SchedaImmobile scheda, boolean selectAll) {
		
		btnInserisciTuttiIPortali = new JButton(labelBtnInserisciTutti);
    	btnCancellaTuttiIPortali = new JButton(labelBtnCancellaTutti);
    	checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
    	
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(Box.createVerticalStrut(20));
		
		//Inserisce la scheda in tutti i portali selezionati
		btnInserisciTuttiIPortali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                for(PortaleImmobiliare currentPortal : j2web_GUI.mapPortaliInserimentoSequenziale.keySet()) {
                	if(j2web_GUI.mapPortaliInserimentoSequenziale.get(currentPortal)==false) {
                		//currentPortal.inserisciScheda(scheda);
                		System.out.println("Scheda inserita in: " + currentPortal.idPortale);
                	}
                }
                
            }
         });
		add(btnInserisciTuttiIPortali);
		    
		//Elimina la scheda da tutti i portali selezionati
        btnCancellaTuttiIPortali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {           
                for(PortaleImmobiliare currentPortal : j2web_GUI.mapPortaliCancellazioneSequenziale.keySet()) {
                	if(j2web_GUI.mapPortaliInserimentoSequenziale.get(currentPortal)==false) {
                		//currentPortal.inserisciScheda(scheda);
                		System.out.println("Scheda cancellata da: " + currentPortal.idPortale);
                	}
                }            
            }
         });
        add(btnCancellaTuttiIPortali);
		
        add(new JLabel(labelSpaziatore)); 
        
        //Checkbox seleziona tutti
        final JCheckBox checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
        checkboxSelezionaTutti.setSelected(selectAll);
        checkboxSelezionaTutti.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
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
	
	public InserimentoPortale(final PortaleImmobiliare portale, final SchedaImmobile scheda,  boolean selectAll) {
		
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
 			//Pulsante abilitato
 			btnInserisci.setEnabled(false);
 			btnInserisci.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				System.out.println("Inserisci: " + scheda.codiceInserzione);	     				
     				//Il cursone viene messo in modalità attesa
     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per inserire una scheda immobile, il metoso chiamata sarà quella della sottoclasse effettiva
     				portale.inserisciScheda(scheda);
     				//Il cursone viene messo in modalità standard
     				setCursor(Cursor.getDefaultCursor());
     			}
     		});
 		}
 		else {
 			//Pulsante disabilitato
 			btnInserisci.setEnabled(false);
 		}
 		
 			     		
 		//Pulsante Visualizza
 		btnVisualizza = new JButton(labelBtnVisualizza);
 		//Controllo se la scheda è inserita nel portale corrente
 		if(isOnThisPortal) {
 			btnVisualizza.setEnabled(true);
 			btnVisualizza.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				//Il cursone viene messo in modalità attesa
     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				System.out.println("Visualizza: " + scheda.codiceInserzione);
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per visualizzare una scheda immobile
     				portale.visualizzaScheda(scheda);
     				//Il cursone viene messo in modalità standard
     				setCursor(Cursor.getDefaultCursor());
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
     				System.out.println("Cancella: " + scheda.codiceInserzione);
     				//Il cursone viene messo in modalità attesa
     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per cancellare una scheda immobile
     				portale.cancellaScheda(scheda);
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
                //Aggiungo il portale alla lista dei portali ad inserimento sequenziale
                if(checkboxSelezionaPortale.isSelected()) {
                	if(isOnThisPortal) {
                		j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, true);
                		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, false);
                	}
                	else {
                		j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, false);
                		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, true);
                	}
                }
                else {
                	j2web_GUI.mapPortaliInserimentoSequenziale.remove(portale);
                	j2web_GUI.mapPortaliCancellazioneSequenziale.remove(portale);
                }
            }
	 	});
        
        if(selectAll) {
        	checkboxSelezionaPortale.setSelected(true);
        	if(isOnThisPortal) {
        		j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, true);
        		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, false);
        	}
        	else {
        		j2web_GUI.mapPortaliInserimentoSequenziale.put(portale, false);
        		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portale, true);
        		}       
        	}
        else {
        	checkboxSelezionaPortale.setSelected(false);
        	j2web_GUI.mapPortaliInserimentoSequenziale.remove(portale);
        	j2web_GUI.mapPortaliCancellazioneSequenziale.remove(portale);
        }
        add(checkboxSelezionaPortale);      		
        add(btnInserisci);
        add(btnVisualizza);
        add(btnCancella);
	}
	
}   //Fine PannelloInserimento