package risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import risk.model.Continent;
import risk.model.Territory;
import risk.model.RisikoGame.GAME_PHASE;
import risk.model.util.FIGURE;



public class RisikoGame {
	
	public enum GAME_PHASE{
		FIRSTTURN, REINFORCEMENT, BATTLE, FINALMOVE;
	}
	
	
	private Player[] players;
	private ArrayList<Territory> territories;
	private ArrayList<Continent> continents;
	private ArrayList<Mission> missions;
	private ArrayList<Card> cards;
	private GAME_PHASE gamePhase;
	private Player currentTurn;
	private int turnCounter;
	
	
	/**
	 * Creates and initializes a RisikoGame
	 * @param players are the players of the game
	 * @param terrFile is the file containing the territories
	 * @param continentsFile is the file containing the continents
	 * @param missionsFile is the file containing the missions
	 * @throws NumberFormatException if there are problems with the parsing
	 * @throws IOException if there is a problem with the file
	 */
	public RisikoGame(Player[] players, String terrFile, String continentsFile, String missionsFile) throws NumberFormatException, IOException {
		this.players = players;
		this.players = shufflePlayers();
		
		giveStarterTanks();
		/* 
		territories = 
		continents = 
		for (Continent c : continents) {
			setContinent(c);	
		}
		
		missions = 
//		
		giveMissions();
		
		cards = 
		shuffleCards();
		*/
		initTerritoryOwners();
		
		
		
		setGamePhase(GAME_PHASE.FIRSTTURN);
		
		turnCounter = 0;
		currentTurn = this.players[turnCounter];
		if(currentTurn.isAI()) {
			nextTurn();
		}
			
	}
	
	
	/**
	 * The games goes to the next turn
	 */
	public void nextTurn(){
		turnCounter++;
		if(turnCounter == players.length) {
			turnCounter = 0;
		}
		
		currentTurn = this.players[turnCounter];
		
		if(currentTurn.isEliminated()) {
			nextTurn();
		}
	}
	
	
	/**
	 * The game goes to the next phase
	 */
	public void nextPhase() {
		switch(gamePhase) {
		case FIRSTTURN:
			gamePhase = GAME_PHASE.REINFORCEMENT; 
			currentTurn = this.players[0];
			turnCounter = 0;
			giveBonus(currentTurn);
			
			break;
		case REINFORCEMENT:
			gamePhase = GAME_PHASE.BATTLE;
			
			break;
		case BATTLE:
			
			gamePhase = GAME_PHASE.FINALMOVE;
			
			break;
		case FINALMOVE:
			
			giveBonus(currentTurn);
			gamePhase = GAME_PHASE.REINFORCEMENT;
			
			break;
		}
	}
	


	
	/**
	 * Moves tanks from a territory to another
	 * @param t1 is the first territory
	 * @param t2 is the second territory
	 * @param n is the number of tanks
	 */
	public void moveTanks(Territory t1, Territory t2, int n) {
		t1.removeTanks(n);
		t2.addTanks(n);
	}
	
	/**
	 * Makes a battle between 2 players
	
	 */
	public void battle(int[] atkResults, int[] defResults, int atk, int def) {
		

	}
	
	/**
	 * A player conquers a territory
	 * @param t1 is the attacking territory
	 * @param t2 is the defending territory
	 */
	public void conquer(Territory t1, Territory t2) {
		boolean t2ContConquered = false;
		if(isOwned(getTerrContinent(t2))) {
			t2ContConquered = true;
		}
		getPlayer(t1.getOwner()).addTerritory();
		getPlayer(t2.getOwner()).removeTerritory();
		getTerritory(t2).setOwner(getTerritory(t1).getOwner());
	
		if(t2ContConquered) {
			getPlayer(t2.getOwner()).removeContinent();
		}
		

	}
	
	public Continent getTerrContinent(Territory ti) {
		for(Continent co : continents) {
			if(co.getName().equals(ti.getContinent())){
				return co;
			}
		}
		return null;
	}
	
	
	
	
	
	
	



	/**
	 * Verifies if the continent is owned completely by a player
	 * @return owned
	 */
	public boolean isOwned(Continent c) {
		int i = 0;
		Player temp = currentTurn;
		for(Territory t : c.getTerritories()) {
			if(i == 0) {
				temp = t.getOwner();
			}
			if(!getTerritory(t).getOwner().equals(temp)) {
				return false;
			}
			i++;
		}
		return true;
	}
	

	
	/**
	 * Verifies if a mission is completed
	 * @return boolean
	 */
	public boolean verifyMission () {
		
		return false;
	}
	

	
	/**
	 * plays the combination of 3 cards and gives bonus
	 * @param c1 is the first card
	 * @param c2 is the second card
	 * @param c3 is the third card
	 */
	public void playCardTris(Card c1, Card c2, Card c3) {
		currentTurn.giveBonusTanks(checkTris(c1, c2, c3));
		currentTurn.playCard(c1);
		currentTurn.playCard(c2);
		currentTurn.playCard(c3);
	}
	
	/**
	 * Checks if the combination of 3 cards is a valid one and gives bonus tanks if possible
	 * @param ca1 is the first card
	 * @param ca2 is the second card
	 * @param ca3 is the third card
	 * @return int, the number of bonus tanks
	 */
	public int checkTris(Card ca1, Card ca2, Card ca3) {
		
		ArrayList<FIGURE> figures = new ArrayList<FIGURE>();
		int bonus = 0;
		figures.add(ca1.getFigure());
		figures.add(ca2.getFigure());
		figures.add(ca3.getFigure());
		for (Territory t: territories) {
			if(!(ca1.getFigure().equals(FIGURE.JOLLY))) {
				if(ca1.getTerritory().equals(t)) {
					if (t.getOwner().equals(currentTurn)) 
						bonus = bonus + 2;
				}
			}
			if(!(ca2.getFigure().equals(FIGURE.JOLLY))) {
				if(ca2.getTerritory().equals(t)) {
					if (t.getOwner().equals(currentTurn)) 
						bonus = bonus + 2;
				}
			}
			if(!(ca3.getFigure().equals(FIGURE.JOLLY))) { 
				if(ca3.getTerritory().equals(t)) {
					if (t.getOwner().equals(currentTurn)) 
						bonus = bonus + 2;
				}
			}	
		}
		
		if(ca1.getFigure() == ca2.getFigure() && ca2.getFigure() == ca3.getFigure()) {
			if(ca1.getFigure() == FIGURE.CANNONE) {
				return 4+bonus;
			} else if (ca1.getFigure() == FIGURE.CAVALIERE) {
				return 8+bonus;
			} else if (ca1.getFigure() == FIGURE.FANTE) {
				return 6+bonus;
			}
		} else if (figures.contains(FIGURE.CANNONE) && figures.contains(FIGURE.FANTE) && figures.contains(FIGURE.CAVALIERE)) {
			return 10+bonus;
		} else if (figures.contains(FIGURE.JOLLY)) {
			return 12+bonus;
		}
		return 0;
	}
	
	/**
	 * Gives starter tanks for each player
	 */
	private void giveStarterTanks() {
		switch(this.players.length) {
		case 3:
			for(Player p : players) {
				p.giveBonusTanks(35);
			}
			break;
		case 4:
			for(Player p : players) {
				p.giveBonusTanks(30);
			}
			break;
		case 5:
			for(Player p : players) {
				p.giveBonusTanks(25);
			}
			break;
		case 6:
			for(Player p : players) {
				p.giveBonusTanks(20);
			}
			break;
		}
	}
	/**
	 * Gives missions for each player
	 */
	private void giveMissions() {
		Mission[] shuffledMissions = shuffleMissions();
		int i = 0;
		for(Player p : players) {
			p.giveMission(shuffledMissions[i]);
			i++;
		}
	}
	
	/**
	 * Shuffles the missions of the missions list
	 * @return array of Missions
	 */
    private Mission[] shuffleMissions() {
    	Mission[] shuffledMissions = new Mission[missions.size()];
    	int k = 0;
    	for(Mission m : missions) {
    		shuffledMissions[k] = m;
    		k++;
    	}
        for (int i = 0; i < missions.size(); i++) {
            int j = i + (int) ((missions.size() - i) * Math.random());
            Mission temp = shuffledMissions[i];
            shuffledMissions[i] = shuffledMissions[j];
            shuffledMissions[j] = temp;
        }
        return shuffledMissions;
    }
	
    /**
     * Shuffles the territories of the territory list
     * @return array of Territories
     */
    private Territory[] shuffleTerritories() {
    	Territory[] shuffledTerritories = new Territory[territories.size()];
    	int k = 0;
    	for(Territory t : territories) {
    		shuffledTerritories[k] = t;
    		k++;
    	}
        for (int i = 0; i < territories.size(); i++) {
            int j = i + (int) ((territories.size() - i) * Math.random());
            Territory temp = shuffledTerritories[i];
            shuffledTerritories[i] = shuffledTerritories[j];
            shuffledTerritories[j] = temp;
        }
        return shuffledTerritories;
    }
    
    /**
     * Shuffles the cards of the cards list
     */
    private void shuffleCards() {
    	Card[] shuffledCards = new Card[cards.size()];
    	int k = 0;
    	for(Card c : cards) {
    		shuffledCards[k] = c;
    		k++;
    	}
        for (int i = 0; i < cards.size(); i++) {
            int j = i + (int) ((cards.size() - i) * Math.random());
            Card temp = shuffledCards[i];
            shuffledCards[i] = shuffledCards[j];
            shuffledCards[j] = temp;
        }
        ArrayList<Card> temp = new ArrayList<Card>();
        for(Card c: shuffledCards) {
        	temp.add(c);
        }
        cards = temp;
    }
    
    /**
     * Sets the owner for each territory of the territoryList
     */
    private void initTerritoryOwners() {
        int playerID = 0;
        Territory[] shuffledTerritories = shuffleTerritories();
        for (int i = 0; i < territories.size(); i++) {
            for(Territory t : territories) {
            	if(t.getId() == shuffledTerritories[i].getId()) {
            		t.setOwner(players[playerID]);
            		players[playerID].addTerritory();
            		t.addTanks(1);
            		t.getOwner().placeTank(1);
            	}
            }
            playerID = (playerID + 1) % players.length;
        }
    }
	
    /**
     * Gives bonus tanks to a player depending on territories owned
     * @param pl is the player
     */
    
    //da ricontrollare
	public void giveBonus(Player pl) {

		pl.giveBonusTanks((int)Math.floor(pl.getTerritories()/3));	
		
		for(Continent c : continents) {
			if(isOwned(c)) {
				
					pl.giveBonusTanks(c.getBonus());
				
			}
		}
	}
	
	
	
	/**
	 * Returns a list of all territories
	 * @return ArrayList of Territories
	 */
	public ArrayList<Territory> getTerritories(){
		return territories;
	}
	
	/**
	 * Shuffles the players of the playerList
	 * @return array of players
	 */
    private Player[] shufflePlayers() {
    	Player[] shuffledPlayers = new Player[players.length];
    	int k = 0;
    	for(Player p : players) {
    		shuffledPlayers[k] = p;
    		k++;
    	}
        for (int i = 0; i < players.length; i++) {
            int j = i + (int) ((players.length - i) * Math.random());
            Player temp = shuffledPlayers[i];
            shuffledPlayers[i] = shuffledPlayers[j];
            shuffledPlayers[j] = temp;
        }
        return shuffledPlayers;
    }
	
    /**
     * Returns the player of the current turn of the game
     * @return Player
     */
	public Player getCurrentTurn() {
		return currentTurn;
	}
	
	/**
	 * Returns the current phase of the game
	 * @return gamePhase
	 */
	public GAME_PHASE getGamePhase() {
		return gamePhase;
	}
	
	/**
	 * Sets the current phase of the game
	 * @param gamePhase is the current gamephase
	 */
	public void setGamePhase(GAME_PHASE gamePhase) {
		this.gamePhase = gamePhase;
	}
	
	/**
	 * Adds a tank to a territory
	 * @param t is the territory
	 */
	public void addTerritoryTanks(Territory t) {
		getTerritory(t).addTanks(1);
	}
	
	/**
	 * Returns a territory
	 * @param t is the territory
	 * @return Territory
	 */
	public Territory getTerritory(Territory t) {
		for(Territory te : territories) {
			if(te.getId() == t.getId()) {
				return te;
			}
		}
		return null;
	}
	
	/**
	 * Returns the player
	 * @param p is the player
	 * @return Player
	 */
	public Player getPlayer(Player p) {
		for(Player pl : players) {
			if(pl.getName().equals(p.getName())) {
				return pl;
			}
		}
		return null;
	}
	
	/**
	 * Gives a card to a Player then shuffles them
	 */
	public void giveCard() {
		getPlayer(currentTurn).giveCard(cards.get(0));
		cards.remove(0);
		shuffleCards();
	}
	
	/**
	 * Returns a random territory from owned territories of the current player
	 * @return Territory
	 */
	public Territory getRandomCurrentPlayerTerritory() {
		
		ArrayList<Territory> temp = new ArrayList<Territory>();
		
		for(Territory t : territories) {
			if(t.getOwner().equals(currentTurn)) {
				temp.add(t);
			}
		}
		Random rand = new Random();
		return temp.get(rand.nextInt(temp.size()));
	}
	

	
	private void playerEliminated(Player p) {
		p.setEliminated(true);
	}

}