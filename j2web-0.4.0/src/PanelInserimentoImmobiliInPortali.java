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
	
		//Pannello per l'inserimento dati nei portali immobiliari
        
		//Elementi del pannello
		JPanel panelControlloPortali;
		JPanel inserimentoPortale;
        JButton btnInserisci, btnVisualizza, btnCancella;
        JButton btnSelezionaTuttiIPortali;

        //Costruttore - definisce la prima visualizzazione del pannello
        public PanelInserimentoImmobiliInPortali() {
        	
        	setBorder(new TitledBorder(null, "Inserimento schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        	
        	add(Box.createVerticalStrut(20));
        	
        	//Pannello controllo portali
            panelControlloPortali = new JPanel();
            panelControlloPortali.setLayout(new BoxLayout(panelControlloPortali, BoxLayout.X_AXIS));  
            
            //Pulsante seleziona tutti 
            btnSelezionaTuttiIPortali = new JButton("Inserisci i portali selezionati");
            btnSelezionaTuttiIPortali.setEnabled(false);
            panelControlloPortali.add(btnSelezionaTuttiIPortali);
            
            panelControlloPortali.add(new JLabel("      ")); 
            
            //Checkbox seleziona tutti
            JCheckBox checkboxSelezionaTutti = new JCheckBox("Seleziona tutti i portali");
            checkboxSelezionaTutti.setEnabled(false);
            panelControlloPortali.add(checkboxSelezionaTutti);      		
            
            add(panelControlloPortali);
            
            add(Box.createVerticalStrut(20));	//spaziatore tra il pannello superiore e i vari pannelli portale         
        	
        	
        	
        	
        	/*--------------------------------------------------------------------------------*/
        	//Gestisco la lista concatenata che contiene le informazioni sui portali immobiliari
        	//E' qui che inserisco i portali abilitati attualmente nel programma
        	
        	//PortaleImmobiliare immobiliarePuntoIt = new ImmobiliarePuntoIt("./img/immobiliarePuntoIt.gif", "1 - immobiliare.it", "001");
        	//listPortaliImmobiliari.add(immobiliarePuntoIt);
        	
        	//PortaleImmobiliare casaPuntoIt = new casaPuntoIt("./img/casaPuntoIt.png", "2 - casa.it", "002");
        	//listPortaliImmobiliari.add(casaPuntoIt);
        	
        	//PortaleImmobiliare bancaDelleCase = new BancaDelleCase("./img/banca_delle_case.gif", "2 - bancadellecase.it", "003");
        	//listPortaliImmobiliari.add(bancaDelleCase);
        	
        	PortaleImmobiliare case24 = new Case24("./images/case24.gif", "1 - case24.it", "001");
        	j2web_GUI.listPortaliImmobiliari.add(case24);   
        	
        	PortaleImmobiliare case24bis = new Case24("./images/case24.gif", "1 - case24.it", "001");
        	j2web_GUI.listPortaliImmobiliari.add(case24bis);
        	
        	PortaleImmobiliare case24tris = new Case24("./images/case24.gif", "1 - case24.it", "001");
        	j2web_GUI.listPortaliImmobiliari.add(case24tris);
        	
        	PortaleImmobiliare case24bis2 = new Case24("./images/case24.gif", "1 - case24.it", "001");
        	j2web_GUI.listPortaliImmobiliari.add(case24bis2);
        	
        	PortaleImmobiliare case24bis3 = new Case24("./images/case24.gif", "1 - case24.it", "001");
        	j2web_GUI.listPortaliImmobiliari.add(case24bis3);
    
        	
        	//PortaleImmobiliare pagineCasa = new PagineCasa("./img/paginecasa.gif", "4 - paginecasa.it", "005");
        	//listPortaliImmobiliari.add(pagineCasa);
        	
        	/*PortaleImmobiliare cuboCasa = new CuboCasa("./img/cubocasa.gif", "5 - cubocasa.it", "005");
        	listPortaliImmobiliari.add(cuboCasa);*/
        	
        	//PortaleImmobiliare caseFvg = new CaseFvg("./img/casefvg.gif", "7 - casefvg.it", "007");
        	//listPortaliImmobiliari.add(caseFvg);
        	/*--------------------------------------------------------------------------------*/
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	//Gestione del pannello - prima visualizzazione di questo pannello
            
            //Ciclo tra i portali immobiliari presenti nella lista concatenata e per ognuno creo dei sottopannelli e dei pulsanti (fittizzi: non hanno alcuna funzionalità )
            ListIterator<PortaleImmobiliare> iterator = j2web_GUI.listPortaliImmobiliari.listIterator();
        	while(iterator.hasNext()) {        		
        		PortaleImmobiliare portaleCorrente = iterator.next();
        		
        		//Pulsante Inserisci
        		btnInserisci = new JButton("Inserisci");
        		btnInserisci.setEnabled(false);
        		
        		//Pulsante Visualizza
        		btnVisualizza = new JButton("Visualizza");
        		btnVisualizza.setEnabled(false);
        		
        		//Pulsante Cancella
         		btnCancella= new JButton("Cancella");
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
                inserimentoPortale.add(new JLabel("  "));             
                JCheckBox checkboxSelezionaPortale = new JCheckBox("Seleziona portale");
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

                       
            //PANNELLO VUOTO
            /*JPanel fakePanel5 = new JPanel();
            fakePanel5.setLayout(new BoxLayout(fakePanel5, BoxLayout.X_AXIS));
            fakePanel5.setBorder(new LineBorder(new Color(184,208,229)));
            fakePanel5.add(new JLabel(new ImageIcon("./img/fake.jpeg")));
            fakePanel5.add(new JLabel("                     "));
            fakePanel5.add(new JLabel("                     "));
            fakePanel5.add(new JLabel("                     "));
            add(fakePanel5);*/

        	for(int i=0; i<1; i++) {
        		//add(Box.createVerticalGlue());
        	}


        }    //Fine costruttore PannelloInserimento
        
        
        
        //Update del pannello, eseguito alla selezione di una scheda sul pannello centrale e dopo l'inserimento/rimozione di una scheda in un portale
        public void updatePanello(final SchedaImmobile scheda, final boolean selectAll) {
        	
        	//Rimuovo tutti i portali dalla lista di inserimento sequenziale
        	j2web_GUI.mapPortaliInserimentoSequenziale.clear();

        	//Rimuovo tutti gli elementi dal pannello
        	removeAll();
        	
        	add(Box.createVerticalStrut(20));
        	
        	
        	//Pannello controllo portali
            panelControlloPortali = new JPanel();
            panelControlloPortali.setLayout(new BoxLayout(panelControlloPortali, BoxLayout.X_AXIS));  
            
            //Pulsante seleziona tutti 
            btnSelezionaTuttiIPortali = new JButton("Inserisci i portali selezionati");
            btnSelezionaTuttiIPortali.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Click: Inserisci i portali selezionati");
                    
                    //Chiamo i metodo di inserimento 
                    /*ListIterator<SchedaImmobile> iterator = listaSchedeImmobile.listIterator();
                	while(iterator.hasNext()) {
                		SchedaImmobile schedaCorrente = (SchedaImmobile)iterator.next();
                		//La rimozione avviene confrontando l'id univoco della scheda immobile
                		if(schedaCorrente.idScheda==idScheda) {
                			iterator.remove();
                			System.out.println("Scheda rimossa dalla linkedlist");
                		}
                	}*/
                }
             });
            panelControlloPortali.add(btnSelezionaTuttiIPortali);
            
            panelControlloPortali.add(new JLabel("      ")); 
            
            //Checkbox seleziona tutti
            final JCheckBox checkboxSelezionaTutti = new JCheckBox("Seleziona tutti i portali");
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
	     		btnInserisci = new JButton("Inserisci");
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
	     		btnVisualizza = new JButton("Visualizza");
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
	     		btnCancella = new JButton("Cancella");
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
                inserimentoPortale.add(new JLabel("  "));             
                final JCheckBox checkboxSelezionaPortale = new JCheckBox("Seleziona portale");
                checkboxSelezionaPortale.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Checkbox: seleziona il portale"); 
                        //Aggiungo il portale alla lista dei portali ad inserimento sequenziale
                        if(checkboxSelezionaPortale.isSelected()) {
                        	if(scheda.isOnThisPortal(portaleCorrente.idPortale)) {
                        		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, true);
                        	}
                        	else {
                        		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, false);
                        	}
                        }
                        else {
                        	j2web_GUI.mapPortaliInserimentoSequenziale.remove(portaleCorrente);                	
                        }
                        System.out.println("tot: " + j2web_GUI.mapPortaliInserimentoSequenziale.size()); 
                    }
       		 	});
                
                if(selectAll) {
                	checkboxSelezionaPortale.setSelected(true);
                	if(scheda.isOnThisPortal(portaleCorrente.idPortale)) {
                		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, true);
                	}
                	else {
                		j2web_GUI.mapPortaliInserimentoSequenziale.put(portaleCorrente, false);
                	}                }
                else {
                	checkboxSelezionaPortale.setSelected(false);
                	j2web_GUI.mapPortaliInserimentoSequenziale.remove(portaleCorrente); 
                }
                inserimentoPortale.add(checkboxSelezionaPortale);      		
                inserimentoPortale.add(btnInserisci);
                inserimentoPortale.add(btnVisualizza);
                inserimentoPortale.add(btnCancella);
                add(inserimentoPortale);
                
                add(Box.createVerticalStrut(20));   //spaziatore tra i pannelli
	     	}
	     	
	     	for(int i=0; i<1; i++) {
        		add(Box.createVerticalGlue());
        	}
	     	 
	     	updateUI();
	     	
	     	System.out.println("TOT: " + j2web_GUI.mapPortaliInserimentoSequenziale.size());
        }
        
        
        //Update del pannello eseguito al momento in cui viene cancellata una scheda sul pannello centrale o inserita una dal pannello Form: nessuna scheda selezionata
        public void updatePanello() {
        	
        	removeAll();
        	
        	add(Box.createVerticalStrut(20));
        	
        	//Pannello controllo portali
            panelControlloPortali = new JPanel();
            panelControlloPortali.setLayout(new BoxLayout(panelControlloPortali, BoxLayout.X_AXIS));  
            
            //Pulsante seleziona tutti 
            btnSelezionaTuttiIPortali = new JButton("Inserisci i portali selezionati");
            btnSelezionaTuttiIPortali.setEnabled(false);
            panelControlloPortali.add(btnSelezionaTuttiIPortali);
            
            panelControlloPortali.add(new JLabel("      ")); 
            
            //Checkbox seleziona tutti
            JCheckBox checkboxSelezionaTutti = new JCheckBox("Seleziona tutti i portali");
            checkboxSelezionaTutti.setEnabled(false);
            panelControlloPortali.add(checkboxSelezionaTutti);      		
            
            add(panelControlloPortali);
            
            add(Box.createVerticalStrut(20));	//spaziatore tra il pannello superiore e i vari pannelli portale 
                          
            ListIterator<PortaleImmobiliare> iterator = j2web_GUI.listPortaliImmobiliari.listIterator();
         	while(iterator.hasNext()) {
         		
         		PortaleImmobiliare portaleCorrente = iterator.next();
         		
         		//Pulsante Inserisci
         		btnInserisci = new JButton("Inserisci");
         		btnInserisci.setEnabled(false);
         		
         		//Pulsante Visualizza
         		btnVisualizza = new JButton("Visualizza");
         		btnVisualizza.setEnabled(false);
         		
         		//Pulsante Cancella
         		btnCancella = new JButton("Cancella");
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
                inserimentoPortale.add(new JLabel("  "));             
                JCheckBox checkboxSelezionaPortale = new JCheckBox("Seleziona portale");
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
         	
         	for(int i=0; i<1; i++) {
        		add(Box.createVerticalGlue());
        	}
         	
         	updateUI();
        	
        }

}   //Fine PannelloInserimento