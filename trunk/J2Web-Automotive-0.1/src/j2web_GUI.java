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


public class j2web_GUI extends JFrame implements parametriGenerali {
	
	private static final long serialVersionUID = 1L;

	//I tre pannelli principali
	//static PanelCreazioneSchedeImmobile panelCreazioneSchedeImmobile;	//SX
	static PanelListaSchedeImmobili panelListaSchedeImmobile;	//MID
	static PanelInserimentoImmobiliInPortali panelInserimentoImmobiliInPortali;	//DX
	
	//Inizializzo le principali strutture dati
	
	//La struttura dati che contiene le schede immobile create
  	public static LinkedList<SchedaVeicolo> listSchedeImmobile = new LinkedList<SchedaVeicolo>();
  	
  	//La struttura dati che contiene i portali attivati
  	public static LinkedList<PortaleWeb> listPortaliImmobiliari = new LinkedList<PortaleWeb>();
  	
  	//La struttura dati che contiene i portali selezionati per l'inserimento sequenziale
  	public static LinkedList<PortaleWeb> listPortaliInserimentoSequenziale = new LinkedList<PortaleWeb>();
  	
    //La struttura dati che contiene i portali selezionati per l'eliminazione sequenziale
  	public static LinkedList<PortaleWeb> listPortaliCancellazioneSequenziale = new LinkedList<PortaleWeb>();
	
  	
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
						System.out.println("Ordino schede per data di inserimento...");
						//Collections.sort(j2web_GUI.listSchedeImmobile, new IdComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem);
				
				JMenuItem mntmNewMenuItem_1 = new JMenuItem("Codice");
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Ordino schede per codice...");
						//Collections.sort(j2web_GUI.listSchedeImmobile, new CodeComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_1);
				
				JMenuItem mntmNewMenuItem_2 = new JMenuItem("Comune");
				mntmNewMenuItem_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Ordino schede per Comune...");
						//Collections.sort(j2web_GUI.listSchedeImmobile, new CityComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_2);
				
				JMenuItem mntmNewMenuItem_3 = new JMenuItem("Provincia");
				mntmNewMenuItem_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Ordino schede per Provincia...");
						//Collections.sort(j2web_GUI.listSchedeImmobile, new ProvinceComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_3);
				
				JMenuItem mntmNewMenuItem_4 = new JMenuItem("Regione");
				mntmNewMenuItem_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Ordino schede per Regione...");
						//Collections.sort(j2web_GUI.listSchedeImmobile, new RegionComparator());
						j2web_GUI.panelListaSchedeImmobile.updatePanello();
					}
				});
				menu1_menuItem.add(mntmNewMenuItem_4);
				
			JMenuItem menu1_menuItem_2 = new JMenuItem("Elimina tutte");
			menu1_menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Elimino tutte le schede...");				
					int response = JOptionPane.showConfirmDialog(null, MapModalWindowsDialogs.get("menu_ConfermaEliminaTutteLeSchede"), "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.NO_OPTION) {
					    } else if (response == JOptionPane.YES_OPTION) {
					    	//Elimino tutte le schede e salvo su dat
					    	j2web_GUI.listSchedeImmobile.clear();
					    	j2web.salvaListaSchedeCreate();
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
					System.out.println("Seleziono tutti i portali...");
				}
			});
			menu3.add(menu3_menuItem1);
			
			JMenuItem menu3_menuItem2 = new JMenuItem("Inserisci scheda nei selezionati");
			menu3_menuItem2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Inserisco la scheda nei portali selezionati...");
				}
			});
			menu3.add(menu3_menuItem2);
			
			JMenuItem menu3_menuItem3 = new JMenuItem("Elimina scheda dai selezionati");
			menu3_menuItem3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Elimino la scheda dai portali selezionati...");
				}
			});
			menu3.add(menu3_menuItem3);
			getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		/*Menu*/	
		
		//Pannello SX - "Creazione scheda immobile"
		//panelCreazioneSchedeImmobile = new PanelCreazioneSchedeImmobile();
		//getContentPane().add(new JScrollPane(panelCreazioneSchedeImmobile));
		
		//Pannello MID - "Lista schede create"
		panelListaSchedeImmobile = new PanelListaSchedeImmobili();
		getContentPane().add(new JScrollPane(panelListaSchedeImmobile));
		
		//Pannello DX - "Inserimento schede immobile"
		panelInserimentoImmobiliInPortali = new PanelInserimentoImmobiliInPortali();
		getContentPane().add(new JScrollPane(panelInserimentoImmobiliInPortali));
		
	}
	
}