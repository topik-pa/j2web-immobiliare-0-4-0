import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;*/


public class j2web_GUI extends JFrame implements parametriGenerali {
	
	private static final long serialVersionUID = 1L;

	//I tre pannelli principali
	static PanelCreazioneSchedeImmobile panelCreazioneSchedeImmobile;	//SX
	static PanelListaSchedeImmobili panelListaSchedeImmobile;	//MID
	static PanelInserimentoImmobiliInPortali panelInserimentoImmobiliInPortali;	//DX
	
	//Inizializzo le principali strutture dati
	
	//La struttura dati che contiene le schede immobile create
  	public static LinkedList<SchedaImmobile> listSchedeImmobile = new LinkedList<SchedaImmobile>();
  	
  	//La struttura dati che contiene i portali attivati
  	public static LinkedList<PortaleImmobiliare> listPortaliImmobiliari = new LinkedList<PortaleImmobiliare>();
  	
  	//La struttura dati che contiene i portali selezionati per l'inserimento sequenziale
  	public static LinkedList<PortaleImmobiliare> listPortaliInserimentoSequenziale = new LinkedList<PortaleImmobiliare>();
  	
    //La struttura dati che contiene i portali selezionati per l'eliminazione sequenziale
  	public static LinkedList<PortaleImmobiliare> listPortaliCancellazioneSequenziale = new LinkedList<PortaleImmobiliare>();
	
	/**
	 * Create the frame.
	 */
	public j2web_GUI() {
				
		//Proprietà della GUI
	    setIconImage(frameIcon);    
	    setTitle(nomeGUI);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GUI_bounds[0], GUI_bounds[1], GUI_bounds[2], GUI_bounds[3]);	
		
		/*Menu*/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu1 = new JMenu("J2Web");
		menuBar.add(menu1);
		
			JMenuItem menu1_menuItem1 = new JMenuItem("Chiudi");
			menu1_menuItem1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Menu - Chiudi J2Web");
					System.exit(0);
				}
			});
			menu1.add(menu1_menuItem1);
		
		JMenu menu2 = new JMenu("Schede");
		menuBar.add(menu2);
		
			JMenu menu1_menuItem = new JMenu("Ordina per");
			menu2.add(menu1_menuItem);
		
				JMenuItem mntmNewMenuItem = new JMenuItem("Data di inserimento");
				mntmNewMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Menu - Ordina schede per data di inserimento");
						Collections.sort(j2web_GUI.listSchedeImmobile, new IdComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem);
				
				JMenuItem mntmNewMenuItem_1 = new JMenuItem("Codice");
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Menu - Ordina schede per codice");
						Collections.sort(j2web_GUI.listSchedeImmobile, new CodeComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_1);
				
				JMenuItem mntmNewMenuItem_2 = new JMenuItem("Comune");
				mntmNewMenuItem_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Menu - Ordina schede per Comune");
						Collections.sort(j2web_GUI.listSchedeImmobile, new CityComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_2);
				
				JMenuItem mntmNewMenuItem_3 = new JMenuItem("Provincia");
				mntmNewMenuItem_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Menu - Ordina schede per Provincia");
						Collections.sort(j2web_GUI.listSchedeImmobile, new ProvinceComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_3);
				
				JMenuItem mntmNewMenuItem_4 = new JMenuItem("Regione");
				mntmNewMenuItem_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Menu - Ordina schede per Regione");
						Collections.sort(j2web_GUI.listSchedeImmobile, new RegionComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_4);
				
			JMenuItem menu1_menuItem_2 = new JMenuItem("Elimina tutte");
			menu1_menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Menu - Elimina tutte le schede");
					
					int response = JOptionPane.showConfirmDialog(null, ModalWindowsDialogs[0], "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.NO_OPTION) {
					    } else if (response == JOptionPane.YES_OPTION) {
					    	//Elimino tutte le schede e salvo su dat
					    	j2web_GUI.listSchedeImmobile.clear();
					    	j2web.salvaListPortaliSuDat(datFilePath, j2web_GUI.listSchedeImmobile);
							/*try {
							   File file = new File(datFilePath);
						    	if(file.exists()) {
						    		System.out.println("File .dat schede trovato.");
						    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
										outputFile.writeObject(j2web_GUI.listSchedeImmobile);
										outputFile.close();
						    	}
						    	else {
										FileOutputStream newFile = new FileOutputStream(datFilePath);
										ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
										outputFile.writeObject(j2web_GUI.listSchedeImmobile);
										outputFile.close();
										System.out.println("File .dat schede non trovato. Creazione del file...: " + newFile.toString());
						    	}
								} catch (FileNotFoundException e0) {
						            JOptionPane.showMessageDialog(null, "File .dat schede non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
						            e0.printStackTrace();
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, "Impossibile accedere al file .dat schede: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								}*/
							j2web_GUI.panelListaSchedeImmobile.updatePanello();
							
					    } else if (response == JOptionPane.CLOSED_OPTION) {
					    	
					    }
				}
			});
			menu2.add(menu1_menuItem_2);
		
		JMenu menu3 = new JMenu("Portali");
		menu3.setEnabled(false); //Disattivato per sviluppi futuri
		menuBar.add(menu3);
		
			JMenuItem menu3_menuItem1 = new JMenuItem("Seleziona tutti");
			menu3_menuItem1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Menu - Seleziona tutti");
				}
			});
			menu3.add(menu3_menuItem1);
			
			JMenuItem menu3_menuItem2 = new JMenuItem("Inserisci scheda nei selezionati");
			menu3_menuItem2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Menu - Inserisci in selezionati");
				}
			});
			menu3.add(menu3_menuItem2);
			
			JMenuItem menu3_menuItem3 = new JMenuItem("Elimina scheda dai selezionati");
			menu3_menuItem3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Menu - Elimina in selezionati");
				}
			});
			menu3.add(menu3_menuItem3);
			getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		/*Menu*/	
		
		//Pannello SX - "Creazione scheda immobile"
		panelCreazioneSchedeImmobile = new PanelCreazioneSchedeImmobile();
		getContentPane().add(panelCreazioneSchedeImmobile);	
		
		//Pannello MID - "Lista schede create"
		panelListaSchedeImmobile = new PanelListaSchedeImmobili();
		getContentPane().add(new JScrollPane(panelListaSchedeImmobile));
		
		//Pannello DX - "Inserimento schede immobile"
		panelInserimentoImmobiliInPortali = new PanelInserimentoImmobiliInPortali();
		getContentPane().add(new JScrollPane(panelInserimentoImmobiliInPortali));
		
	}
	
}