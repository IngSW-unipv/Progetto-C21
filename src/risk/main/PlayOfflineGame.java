package risk.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import risk.model.Player;

public class PlayOfflineGame {
	int fase; // poi fatelo enum
	int playerTurn;
	int scelta;
	BufferedReader reader;
	String line;
	ArrayList<Player> Players;
	
	public PlayOfflineGame(ArrayList<Player> Players) {
		fase=0;
		playerTurn=0;
		scelta=0;
		this.Players=Players;
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
	}

	void startGame(){
		
	 
	       
	 

		while(true) {
			

			switch(fase) {
			
			//fase di rifornimento
			case 1: {
				//creare una classe dentro player che controlla il possesso di tris
				//dare per scontato che nei nuovi metodi siano funzioni di player
				while (Players[playerTurn].Havetris) {
					System.out.println("hai a disposizione dei tris vuoi utilizzarli? 1 si 0 no");
					scelta= Integer.parseInt( reader.readLine());
						if (scelta==1 ) {
							System.out.println("hai a disposizione LISTA DEI TRIS"); //CREARE UN METODO CHE RESTITUISCA LA LISTA DEI TRIS 
							scelta= Integer.parseInt( reader.readLine());
							useTris(scelta); // creare un metodo che usa il tris e assegna tot carri al giocatore
							
							}
						else break;
						
					}
				
				while(Players[playerTurn].getTanks()) {
					System.out.println("hai a disposizione "+Players[playerTurn].getTanks() + " carri armati puoi posizionarli in CREARE UN METODO CHE RESTITUISCA I TERRITORI E IL NUMERO DEL TERRITORIO"  );
					//qui bisogna permettergli di scegliere un territorio con il numero ed il numero di carri da aggiungere su quel territorio
			
				}
				
				fase++;
					
				
				
			
				
			break;}
			
			//fase di attacco
			case 2: {
				while ()
				
				
			break;}
			
			//fase di spostamento
			case 3:{
				
			
			break;}
			
			
			
			}
			
		}
		
		
	}
}
