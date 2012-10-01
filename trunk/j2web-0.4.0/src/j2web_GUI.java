import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;


public class j2web_GUI extends JFrame implements parametriGenerali {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	//I tre pannelli principali
	static PanelCreazioneSchedeImmobile panelCreazioneSchedeImmobile;	//SX
	static PannelloListaSchedeImmobili panelListaSchedeImmobile;	//MID
	static PanelInserimentoImmobiliInPortali panelInserimentoImmobiliInPortali;	//DX
	
	//La struttura dati che contiene le schede immobile create
  	public static LinkedList<SchedaImmobile> listSchedeImmobile = new LinkedList<SchedaImmobile>();
  	
  	//La struttura dati che contiene i portali attivati
  	public static LinkedList<PortaleImmobiliare> listPortaliImmobiliari = new LinkedList<PortaleImmobiliare>();
  	
  	//La struttura dati che contiene i portali selezionati per l'inserimento sequenziale
  	public static Map<PortaleImmobiliare, Boolean> mapPortaliInserimentoSequenziale = new Hashtable<PortaleImmobiliare, Boolean>();
	
	/**
	 * Create the frame.
	 */
	public j2web_GUI() {
				
		//Proprietà della GUI
	    setIconImage(frameIcon);    
	    setTitle(nomeGUI);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GUI_bounds[0], GUI_bounds[1], GUI_bounds[2], GUI_bounds[3]);	
		
		/*Menu... in stand-by*/
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar_1.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		/*Menu*/	
		
		//Pannello SX - "Creazione scheda immobile"
		panelCreazioneSchedeImmobile = new PanelCreazioneSchedeImmobile();
		getContentPane().add(panelCreazioneSchedeImmobile);	
		
		//Pannello MID - "Lista schede create"
		panelListaSchedeImmobile = new PannelloListaSchedeImmobili();
		getContentPane().add(new JScrollPane(panelListaSchedeImmobile));
		
		//Pannello DX - "Inserimento schede immobile"
		panelInserimentoImmobiliInPortali = new PanelInserimentoImmobiliInPortali();
		getContentPane().add(new JScrollPane(panelInserimentoImmobiliInPortali));
		
	}
	
}