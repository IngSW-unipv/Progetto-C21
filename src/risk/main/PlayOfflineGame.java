//package risk.main;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import risk.model.Player;
//
//public class PlayOfflineGame {
//	int fase; // poi fatelo enum
//	int playerTurn;
//	int scelta;
//	BufferedReader reader;
//	String line;
//	ArrayList<Player> Players;
//	boolean attack;
//	boolean attackWin;
//	public PlayOfflineGame(ArrayList<Player> Players) {
//		fase = 0;
//		playerTurn = 0;
//		scelta = 0;
//		this.Players = Players;
//		attack = true;
//		attackWin = false;
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	}
//
//	void startGame(){
//		
//	 
//	       
//	 
//
//		while(true) {
//			
//
//			switch(fase) {
//			
//			//fase di rifornimento
//			case 1: {
//				//creare una classe dentro player che controlla il possesso di tris
//				//dare per scontato che nei nuovi metodi siano funzioni di player
//				while (Players[playerTurn].Havetris) {
//					System.out.println("hai a disposizione dei tris vuoi utilizzarli? 1 si 0 no");
//					scelta= Integer.parseInt( reader.readLine());
//						if (scelta==1 ) {
//							System.out.println("hai a disposizione LISTA DEI TRIS"); //CREARE UN METODO CHE RESTITUISCA LA LISTA DEI TRIS 
//							scelta= Integer.parseInt( reader.readLine());
//							useTris(scelta); // creare un metodo che usa il tris e assegna tot carri al giocatore
//							
//							}
//						else break;
//						
//					}
//				
//				while(Players[playerTurn].getTanks()) {
//					System.out.println("hai a disposizione "+Players[playerTurn].getTanks() + " carri armati puoi posizionarli in CREARE UN METODO CHE RESTITUISCA I TERRITORI E IL NUMERO DEL TERRITORIO"  );
//					//qui bisogna permettergli di scegliere un territorio con il numero ed il numero di carri da aggiungere su quel territorio
//			
//				}
//				
//				fase++;
//					
//				
//				
//			
//				
//			break;}
//			
//			//fase di attacco
//			case 2: {
//				while (attack!=false) {
//					System.out.println("vuoi attaccare? 1 si 0 no");
//					scelta= Integer.parseInt( reader.readLine());
//					if(scelta==1) {
//						System.out.println("i territori dal quale puoi attacccare sono :  scegli");//funzione che restituisce numero di territorio nome territorio e numero di carri
//						//nb si puo attaccare solo con 2 o piu carri 
//						scelta= Integer.parseInt( reader.readLine());
//						
//						//SELEZIONATO IL TERRITORIO SERVE UNA FUNZIONE CHE MOSTRA TUTTI I TERRITORI ATTACCABILI E LE LORO DIFESE 
//						
//						System.out.println("SELEZIONA CHI ATTACCHI E CON QUANTI CARRI :  scegli");
//						scelta= Integer.parseInt( reader.readLine());
//						
//						//gestire l'attacco
//						//se l'attacco viene vinto imposto attackWin a true (possono esserci piu attacchi quindi deve essere modificabile una volta sola)
//						//in caso di vittoria si possono spostare numero tot di carri - 1 nel nuovo territorio
//					}
//					else  attack=false;
//				}
//				fase++ ;
//				
//				
//			break;}
//			
//			//fase di spostamento
//			// e' solo una bozza , ovviamente mancano tutti i controlli di base ecc
//			case 3:{ //lista di tutti i territori che confinano con un tuo territorio che abbiano 2 o piu carri
//				
//				
//				System.out.println("SELEZIONA territorio da cui spostare ");
//				scelta= Integer.parseInt( reader.readLine());
//				System.out.println("SELEZIONA territorio di arrivo ");
//				scelta= Integer.parseInt( reader.readLine());
//				System.out.println("SELEZIONA numero di carri da spostare ");
//				scelta= Integer.parseInt( reader.readLine());
//			if(attackWin) {//DAI CARTA TERRITORIO
//				
//			}
//				
//			fase++;	
//			break;
//			}
//			
//			
//			// 	COME SI METTEVA IL DEFAULT ?? 
//			// manca l uscita dal ciclo in caso di vittoria
//			
//			case default{ 
//				fase=1;
//				playerTurn++;
//				attackWin=false;
//				attack=true;
//				if(playerTurn>= Players.size()) playerTurn=0;
//				
//				
//				break;
//			}
//			}
//	
//			
//			
//			}
//			
//		}
//
//}
