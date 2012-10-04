import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
  	
    //La struttura dati che contiene i portali selezionati per l'eliminazione sequenziale
  	public static Map<PortaleImmobiliare, Boolean> mapPortaliCancellazioneSequenziale = new Hashtable<PortaleImmobiliare, Boolean>();
	
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
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu1 = new JMenu("J2Web");
		menuBar.add(menu1);
		
		JMenuItem menu1_menuItem1 = new JMenuItem("Chiudi");
		menu1_menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Click: Chiudi programma");
				System.exit(0);
			}
		});
		menu1.add(menu1_menuItem1);
		
		JMenu menu2 = new JMenu("Schede");
		menuBar.add(menu2);
		
		JMenuItem menu1_menuItem = new JMenuItem("Ordina per...");
		menu2.add(menu1_menuItem);
		
		JMenu menu3 = new JMenu("Portali");
		menuBar.add(menu3);
		
		JMenuItem menu3_menuItem1 = new JMenuItem("Elimina tutti");
		menu3.add(menu3_menuItem1);
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