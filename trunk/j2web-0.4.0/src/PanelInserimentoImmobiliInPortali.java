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


public class PanelInserimentoImmobiliInPortali extends JPanel {
		private static final long serialVersionUID = 1L;
	
		//Pannello per l'inserimento schede nei portali immobiliari
        
		//Elementi del pannello
		JPanel panelControlloPortali;
		JPanel inserimentoPortale;
        JButton btnInserisci, btnVisualizza, btnCancella;
        JButton btnInserisciTuttiIPortali;
        JButton btnCancellaTuttiIPortali;
        
        String labelBtnInserisciTutti = "<html><p style=\"text-align:center;\">Inserisci scheda <br/>nei portali selezionati</p></html>";
        String labelBtnCancellaTutti = "<html><p style=\"text-align:center;\">Cancella scheda <br/> dai portali selezionati</p></html>";
        String labelCheckboxSelezionaTutti = "<html><div style=\"text-align:center;\">Seleziona tutti</div></html>";
        String labelCheckboxSelezionaPortale = "Seleziona portale";
        String labelBtnInserisci = "Inserisci";
        String labelBtnVisualizza = "Visualizza";
        String labelBtnCancella = "Cancella";
        String labelSpaziatore = "   ";
        
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
            panelControlloPortali = new JPanel();
            panelControlloPortali.setLayout(new BoxLayout(panelControlloPortali, BoxLayout.X_AXIS));  
            
            //Pulsante inserisci tutti i selezionati
            btnInserisciTuttiIPortali = new JButton(labelBtnInserisciTutti);
            btnInserisciTuttiIPortali.setEnabled(false);
            panelControlloPortali.add(btnInserisciTuttiIPortali);
            
            //Pulsante elimina tutti i selezionati
            btnCancellaTuttiIPortali = new JButton(labelBtnCancellaTutti);
            btnCancellaTuttiIPortali.setEnabled(false);
            panelControlloPortali.add(btnCancellaTuttiIPortali);
            
            panelControlloPortali.add(new JLabel(labelSpaziatore)); 
            
            //Checkbox seleziona tutti
            JCheckBox checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
            checkboxSelezionaTutti.setEnabled(false);
            panelControlloPortali.add(checkboxSelezionaTutti);      		
            
            add(panelControlloPortali);
            
            add(Box.createVerticalStrut(20));	//spaziatore tra il pannello superiore e i vari pannelli portale 
            //Pannello controllo portali (primo in alto)
        	
     	            
            //Ciclo tra i portali immobiliari presenti nella lista concatenata e per ognuno creo dei sottopannelli e dei pulsanti (fittizzi: non hanno alcuna funzionalità )
            ListIterator<PortaleImmobiliare> iterator = j2web_GUI.listPortaliImmobiliari.listIterator();
        	while(iterator.hasNext()) {        		
        		PortaleImmobiliare portaleCorrente = iterator.next();
        		
        		//Pulsante Inserisci
        		btnInserisci = new JButton(labelBtnInserisci);
        		btnInserisci.setEnabled(false);
        		
        		//Pulsante Visualizza
        		btnVisualizza = new JButton(labelBtnVisualizza);
        		btnVisualizza.setEnabled(false);
        		
        		//Pulsante Cancella
         		btnCancella= new JButton(labelBtnCancella);
         		btnCancella.setEnabled(false);
         		
         		        		
        		//Pannello
        		ImageIcon iconPortale = new ImageIcon(portaleCorrente.urlIcona);
                JLabel labelPortale = new JLabel(iconPortale, JLabel.CENTER);
                inserimentoPortale = new JPanel();
                inserimentoPortale.setLayout(new GridLayout(2,3,5,5));
                
                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                TitledBorder title = BorderFactory.createTitledBorder(loweredetched, portaleCorrente.valoreLabel);
                title.setTitleJustification(TitledBorder.RIGHT);
                inserimentoPortale.setBorder(title);    
                
                inserimentoPortale.add(labelPortale);
                inserimentoPortale.add(new JLabel(labelSpaziatore));             
                JCheckBox checkboxSelezionaPortale = new JCheckBox(labelCheckboxSelezionaPortale);
                checkboxSelezionaPortale.setEnabled(false);
                inserimentoPortale.add(checkboxSelezionaPortale);      		
                inserimentoPortale.add(btnInserisci);
                //inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnVisualizza);
                //inserimentoPortale.add(new JLabel("  "));
                inserimentoPortale.add(btnCancella);
                add(inserimentoPortale);
                
                add(Box.createVerticalStrut(20));   //spaziatore tra i pannelli
        	}        	
        		add(Box.createVerticalGlue());
        }

        public void panelInserimentoInActiveMode(final SchedaImmobile scheda, final boolean selectAll) {
        	
        	//Disegno il pannello nella configurazione attiva
        	add(Box.createVerticalStrut(20));
        	   	
        	//Pannello controllo portali
            panelControlloPortali = new JPanel();
            panelControlloPortali.setLayout(new BoxLayout(panelControlloPortali, BoxLayout.X_AXIS));  
            
            //Pulsante inserisci tutti i selezionati
            btnInserisciTuttiIPortali = new JButton(labelBtnInserisciTutti);
            btnInserisciTuttiIPortali.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Click: Inserisci i portali selezionati");
            
                    for(PortaleImmobiliare currentPortal : j2web_GUI.mapPortaliInserimentoSequenziale.keySet()) {
                    	System.out.println("Inserimento");
                    	if(j2web_GUI.mapPortaliInserimentoSequenziale.get(currentPortal)==false) {
                    		//currentPortal.inserisciScheda(scheda);
                    		System.out.println("Scheda inserita in: " + currentPortal.idPortale);
                    	}
                    }
                    
                }
             });
            panelControlloPortali.add(btnInserisciTuttiIPortali);
            
            //Pulsante elimina tutti i selezionati
            btnCancellaTuttiIPortali = new JButton(labelBtnCancellaTutti);
            btnCancellaTuttiIPortali.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Click: Elimina i portali selezionati");
       
                    for(PortaleImmobiliare currentPortal : j2web_GUI.mapPortaliCancellazioneSequenziale.keySet()) {
                    	System.out.println("Cancellazione");
                    	if(j2web_GUI.mapPortaliInserimentoSequenziale.get(currentPortal)==false) {
                    		//currentPortal.inserisciScheda(scheda);
                    		System.out.println("Scheda cancellata da: " + currentPortal.idPortale);
                    	}
                    }
                    
                }
             });
            panelControlloPortali.add(btnCancellaTuttiIPortali);
            
            panelControlloPortali.add(new JLabel(labelSpaziatore)); 
            
            //Checkbox seleziona tutti
            final JCheckBox checkboxSelezionaTutti = new JCheckBox(labelCheckboxSelezionaTutti);
            if(selectAll) {
            	checkboxSelezionaTutti.setSelected(true);
            }
            else {
            	checkboxSelezionaTutti.setSelected(false);
            }
            checkboxSelezionaTutti.addActionListener(new ActionListener() {
      			 //Clicco su una radio button di una scheda
	               public void actionPerformed(ActionEvent e) {
	                   System.out.println("Checkbox: seleziona tutti i portali");
	                   if(checkboxSelezionaTutti.isSelected()) {
	                	   updatePanello(scheda, true);
	                   }
	                   else {
	                	   updatePanello(scheda, false);
	                   }
	                   
	               }
      		 });
            panelControlloPortali.add(checkboxSelezionaTutti);      		
            
            add(panelControlloPortali);
            
            add(Box.createVerticalStrut(20));	//spaziatore tra il pannello superiore e i vari pannelli portale        	
        	
	    	//Ciclo ogni oggetto PortaleImmobiliare presente nella lista concatenata e per ognuno aggiorno il sottopannello
	    	ListIterator<PortaleImmobiliare> iterator = j2web_GUI.listPortaliImmobiliari.listIterator();
	     	while(iterator.hasNext()) {
	     		
	     		final PortaleImmobiliare portaleCorrente = iterator.next();
	     		
	     		//Pulsante Inserisci
	     		btnInserisci = new JButton(labelBtnInserisci);
	     		//Controllo se la scheda è inserita nel portale corrente
	     		if(scheda.isOnThisPortal(portaleCorrente.idPortale)){
	     			//Pulsante abilitato
	     			btnInserisci.setEnabled(false);
	     		}
	     		else {
	     			//Pulsante disabilitato
	     			btnInserisci.setEnabled(true);
	     		}
	     		btnInserisci.addActionListener(new ActionListener() {
	     			public void actionPerformed(ActionEvent e) {
	     				System.out.println("Inserisci: " + scheda.codiceInserzione);	     				
	     				//Il cursone viene messo in modalità attesa
	     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per inserire una scheda immobile, il metoso chiamata sarà quella della sottoclasse effettiva
	     				portaleCorrente.inserisciScheda(scheda);
	     				//Il cursone viene messo in modalità standard
	     				setCursor(Cursor.getDefaultCursor());
	     			}
	     		});
	     			     		
	     		//Pulsante Visualizza
	     		btnVisualizza = new JButton(labelBtnVisualizza);
	     		//Controllo se la scheda è inserita nel portale corrente
	     		if(scheda.isOnThisPortal(portaleCorrente.idPortale)) {
	     			btnVisualizza.setEnabled(true);
	     		}
	     		else {
	     			btnVisualizza.setEnabled(false);
	     		}
	     		btnVisualizza.addActionListener(new ActionListener() {
	     			public void actionPerformed(ActionEvent e) {
	     				//Il cursone viene messo in modalità attesa
	     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	     				System.out.println("Visualizza: " + scheda.codiceInserzione);
	     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per visualizzare una scheda immobile
	     				portaleCorrente.visualizzaScheda(scheda);
	     				//Il cursone viene messo in modalità standard
	     				setCursor(Cursor.getDefaultCursor());
	     			}
	     		});
	     		
	     		//Pulsante Cancella
	     		btnCancella = new JButton(labelBtnCancella);
	     		//Controllo se la scheda è inserita nel portale corrente
	     		if(scheda.isOnThisPortal(portaleCorrente.idPortale)){
	     			btnCancella.setEnabled(true);
	     		}
	     		else {
	     			btnCancella.setEnabled(false);
	     		}
	     		btnCancella.addActionListener(new ActionListener() {
	     			public void actionPerformed(ActionEvent e) {
	     				System.out.println("Cancella: " + scheda.codiceInserzione);
	     				//Il cursone viene messo in modalità attesa
	     				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	     				//Chiamo il metodo afferente all'oggetto PortaleImmobiliare per cancellare una scheda immobile
	     				portaleCorrente.cancellaScheda(scheda);
	     				//Il cursone viene messo in modalità standard
	     				setCursor(Cursor.getDefaultCursor());
	     			}
	     		});
	     		
	     		//Pannello
        		ImageIcon iconPortale = new ImageIcon(portaleCorrente.urlIcona);
                JLabel labelPortale = new JLabel(iconPortale, JLabel.CENTER);
                inserimentoPortale = new JPanel();
                inserimentoPortale.setLayout(new GridLayout(2,3,5,5));
                
                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                TitledBorder title = BorderFactory.createTitledBorder(loweredetched, portaleCorrente.valoreLabel);
                title.setTitleJustification(TitledBorder.RIGHT);
                inserimentoPortale.setBorder(title);    
                
                inserimentoPortale.add(labelPortale);
                inserimentoPortale.add(new JLabel(labelSpaziatore));             
                final JCheckBox checkboxSelezionaPortale = new JCheckBox(labelCheckboxSelezionaPortale);
                checkboxSelezionaPortale.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Checkbox: seleziona il portale"); 
                        //Aggiungo il portale alla lista dei portali ad inserimento sequenziale
                        if(checkboxSelezionaPortale.isSelected()) {
                        	if(scheda.isOnThisPortal(portaleCorrente.idPortale)) {
                        		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, true);
                        		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portaleCorrente, false);
                        	}
                        	else {
                        		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, false);
                        		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portaleCorrente, true);
                        	}
                        }
                        else {
                        	j2web_GUI.mapPortaliInserimentoSequenziale.remove(portaleCorrente);
                        	j2web_GUI.mapPortaliCancellazioneSequenziale.remove(portaleCorrente);
                        }
                        System.out.println("tot: " + j2web_GUI.mapPortaliInserimentoSequenziale.size()); 
                    }
       		 	});
                
                if(selectAll) {
                	checkboxSelezionaPortale.setSelected(true);
                	if(scheda.isOnThisPortal(portaleCorrente.idPortale)) {
                		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, true);
                		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portaleCorrente, false);
                	}
                	else {
                		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, false);
                		j2web_GUI.mapPortaliCancellazioneSequenziale.put(portaleCorrente, true);
                		}       
                	}
                else {
                	checkboxSelezionaPortale.setSelected(false);
                	j2web_GUI.mapPortaliInserimentoSequenziale.remove(portaleCorrente);
                	j2web_GUI.mapPortaliCancellazioneSequenziale.remove(portaleCorrente);
                }
                inserimentoPortale.add(checkboxSelezionaPortale);      		
                inserimentoPortale.add(btnInserisci);
                inserimentoPortale.add(btnVisualizza);
                inserimentoPortale.add(btnCancella);
                add(inserimentoPortale);
                
                add(Box.createVerticalStrut(20));   //spaziatore tra i pannelli
	     	}	     	
        		add(Box.createVerticalGlue());
        	//Disegno il pannello nella configurazione attiva
        	
        }
}   //Fine PannelloInserimento