package risk.model;



import java.io.IOException;
import java.util.ArrayList;

import risk.controller.AttackController;
import risk.controller.GameController;
import risk.controller.SoundController;
/*import java.io.IOException;*/
import risk.model.util.Color;

public class Player {

	private String name;
	private Color color;
	private boolean isAI;
	private boolean eliminated;
	private int tanks;
	private int bonusTanks;
	private int continents;
	private int territories;
	private DiceShaker shaker;
	private ArrayList<Card> cards;
	private Mission mission;

	

	

	/**
	 * Creates a new player
	 * 
	 * @param name  is the name of the player
	 * @param color is the color chosen by the player
	 */
	public Player(String name, Color color, boolean isAI) {
		this.name = name;
		this.color = color;
		this.isAI = isAI;

		this.eliminated = false;
		this.tanks = 0;
		this.continents = 0;
		this.territories = 0;
		this.bonusTanks = 0;
		cards = new ArrayList<Card>();
	}

	/* GETTERS AND SETTERS */

	/**
	 * Gives a mission to the player
	 * 
	 * @param mission is the mission given
	 */
	public void giveMission(Mission mission) {
		this.mission = mission;
	}

	public Mission getMission() {
		return mission;
	}

	/**
	 * Gives tanks to the player
	 * 
	 * @param newTanks is the number of tanks added
	 */
	public void addTanks(int newTanks) {
		tanks += newTanks;
	}

	/**
	 * Removes tanks from the player
	 * 
	 * @param lostTanks is the number of tanks lost
	 */
	public void removeTanks(int lostTanks) {
		tanks -= lostTanks;
	}

	/**
	 * Modifies the number of additional tanks the player receives
	 * 
	 * @param n is the number of tanks added or subtracted
	 */
	public void giveBonusTanks(int n) {
		bonusTanks += n;
	}

	/**
	 * Returns the number of bonus tanks the player gets each turn
	 * 
	 * @return bonusTanks
	 */
	public int getBonusTanks() {
		return bonusTanks;
	}

	/**
	 * Returns the name of the player
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the color chosen by the player
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns the number of tanks that the player has
	 * 
	 * @return tanks
	 */
	public int getTanks() {
		return tanks;
	}

	/**
	 * Returns the number of continents owned by the player
	 * 
	 * @return continents
	 */
	public int getContinents() {
		return continents;
	}

	/**
	 * Adds a continent to the counter of the ones owned
	 */
	public void addContinents() {
		this.continents += 1;
	}

	/**
	 * Removes a continent from the counter of the ones owned
	 */
	public void removeContinent() {
		this.continents -= 1;
	}

	/**
	 * Sets the continent counter to zero
	 */
	public void zeroContinents() {
		this.continents = 0;
	}

	/**
	 * Returns the number of territories owned by the player
	 * 
	 * @return territories
	 */
	public int getTerritories() {
		return territories;
	}

	/**
	 * Adds a territory to the number of territories owned by the player
	 */
	public void addTerritory() {
		this.territories += 1;
	}

	/**
	 * Removes a territory to the number of territories owned by the player
	 */
	public void removeTerritory() {
		this.territories -= 1;
	}

	/**
	 * Returns the description of the mission of the player
	 * 
	 * @return mission.toString()
	 */
	public String getMissionDescription() {
		return mission.toString();
	}

	/**
	 * Returns the name of the color chosen by the player
	 * 
	 * @return Color
	 */
	public String getColorName() {
		switch (this.color) {
		case GREEN:
			return "Green";
		case YELLOW:
			return "Yellow";
		case RED:
			return "Red";
		case PINK:
			return "Pink";
		case BLUE:
			return "Blue";
		case BLACK:
			return "Black";
		}
		return null;
	}
	
	
	

	/**
	 * Places the tanks owned by the player
	 * 
	 * @param n is the number of tanks placed
	 */
	public void placeTank(int n) {

		this.bonusTanks -= n;
		this.tanks += n;

	}

	/**
	 * Gives a card to the player
	 * 
	 * @param c is the card
	 */
	public void giveCard(Card c) {
		cards.add(c);
	}

	/**
	 * Removes a card from the player
	 * 
	 * @param c is the card
	 */
	public void playCard(Card c) {
		cards.remove(c);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * Verifies if two players are equal
	 * 
	 * @param p is the second player
	 * @return boolean
	 */
	public boolean equals(Player p) {
		if (p.getName() == this.name)
			return true;
		else
			return false;
	}

	/**
	 * Rolls the dice of the player
	 * 
	 * @param n is the number of dice
	 * @return the results in an int array
	 */
	public int[] rollDices(int n) {
		return shaker.rollDices(n);
	}

	
	
	// fare che ritorna vero se ha vinto falso altrimenti; <-----------------------------
	  public void playTurnAI() {
	  
			Territory temp = null;

			int counter = 0;
			switch (GameController.game.getGamePhase()) {
			case FIRSTTURN:
				System.out.println(this.name+" "+this.getMissionDescription());
				temp = GameController.game.getRandomCurrentPlayerTerritory();
				if (bonusTanks > 0) {
					while (counter < 3 && bonusTanks > 0) {
						temp.addTanks(1);
						GameController.getInstance()
								.setPhaseTextArea(this.name + " has placed " + 1 + " tanks" + " in " + temp.getName());
						temp.getOwner().placeTank(1);
						counter++;
						temp = GameController.game.getRandomCurrentPlayerTerritory();
					}
				}
				counter = 0;
		  break;
		  
	  
		case DRAFT:
			System.out.println(this.name);
	    	while (bonusTanks > 0) { 
	    	temp = GameController.game.getRandomCurrentPlayerTerritory();
	    	temp.addTanks(1);
		  	GameController.getInstance().setPhaseTextArea(this.name+
					" has placed "+1+" tanks"+" in "+ temp.getName());
		  	temp.getOwner().placeTank(1);
			  	if (GameController.game.verifyMission()) {
					try {
						GameController.getInstance().stopMusic();
						GameController.getInstance().windowLoader("/risk/view/fxml/WinScene.fxml", "Win", true, true);
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	    	}
	    	  break;
	    
	    	
	    case ATTACK:
			AttackController at = new AttackController(); 
			int attackCounter = 0;	
			while(tankAverage() > 2.4 && attackCounter < 3 ) {
				
				Territory[] temp1 = findAttackerandDefender();
				
				
			if (temp1[0] != null && temp1[1] != null) {
					at.aiAttack(temp1[0],
							temp1[1]);
					if (GameController.game.verifyMission()) {
						try {
							GameController.getInstance().stopMusic();
							GameController.getInstance().windowLoader("/risk/view/fxml/WinScene.fxml", "Win", true, true);
							break;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					attackCounter++;
		    }
			else break;
		}
	    	break;
	    	
	    case FORTIFY:
	    	
	    	Territory[] moving = findMovingTerritories();
	    	
	    	
	    	if(moving[0] != null && moving[1] != null) {
	    		
	    		GameController.game.moveTanks(moving[0],moving[1], 1);

				GameController.getInstance()
						.setPhaseTextArea(GameController.getInstance().getCurrentPlayer().getName() + " moved "
								+ 1 + " tanks from " + moving[0].getName() + " to " +moving[1].getName());
					if (GameController.game.verifyMission()) {
						try {
							GameController.getInstance().stopMusic();
							GameController.getInstance().windowLoader("/risk/view/fxml/WinScene.fxml", "Win", true, true);
							break;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
	    		}
	    	
			
			
	    	break;
			}


	  }
	 
	/**
	 * @return TRUE if the current player is eliminated, FALSE otherwise
	 */
	public boolean isEliminated() {
		return eliminated;
	}
	
	public Territory[] findAttackerandDefender() {
		
		Territory[] attdef = new Territory[2];
		
		for (Territory t : GameController.game.getTerritories()) {
			
			if (t.getTanks() > 2 && t.getOwner().equals(this)) {
				
				if(numberOfWeakTerritories(t) != null) {
					System.out.println("trovato uno con weak vicino");
					attdef[0] = t;
					attdef[1] = numberOfWeakTerritories(t);
					return attdef;
				}
				
			}
			
		}

			if (mostOccupiedTerritory().getTanks() > 2 && (attackableTerritories(mostOccupiedTerritory()) > 0)) {
				System.out.println("trovato quello con il max dei tank");
				attdef[0] = mostOccupiedTerritory();
				attdef[1] = worstDefender(attdef[0]);
				
			}
				else {
				for(Territory t : GameController.game.getTerritories()) {
					
					if(t.getOwner().equals(this) && t.getTanks() > 2) {
						
						for(Territory t1 : t.getConfinanti()) {
							
							if(!t1.getOwner().equals(this)) {
								System.out.println("trovato uno random");
								attdef[0] = t;
								attdef[1] = t1;
								return attdef;
							}
						}
						
					}
				}
			} 
			
			
			
			

		return attdef;
	}
	
	public Territory[] findMovingTerritories() {
		
		Territory[] t1t2 = new Territory[2];
		
		for (Territory t : GameController.game.getTerritories()) {
			
			if (t.getTanks() > 2 && t.getOwner().equals(this)) {
				
				for(Territory t1 : t.getConfinanti()) {
					
					if(t1.getOwner().equals(this)) {
						t1t2[0] = t;
						t1t2[1] = t1;
						return t1t2;
					}
				}
				
			}
			
		}

			
		return t1t2;
	}
	
	public Territory worstDefender(Territory territory) {
		
		for(Territory t : territory.getConfinanti()) {
			
			if(! t.getOwner().equals(this)) {
				
				if(t.getTanks() == 1) return t;
				else return t;
			}
		}
		return null;
	}
	
	public int attackableTerritories(Territory territory) {
		int c = 0;
		for(Territory t : territory.getConfinanti()) {
			if(! t.getOwner().equals(this)) c++;
		}
		return c;
	}
	
	//return del primo territorio da attaccare con 1 carro solo
	public Territory numberOfWeakTerritories(Territory territory) {
		
		
		for(Territory t : territory.getConfinanti()) {
			
			if(!(t.getOwner().equals(this)) && t.getTanks() == 1) return t;
		}
		
		return null;
	}
	
	public Territory mostOccupiedTerritory() {
		
		Territory max = null;
		
		for(Territory t1: GameController.game.getTerritories()) {
			if(t1.getOwner().equals(this)) {
				max = t1;
				break;
			}
		}
		
		for(Territory t: GameController.game.getTerritories()) {
			if(t.getOwner().equals(this)) {
				
				if(t.getTanks() >= max.getTanks()) max = t;
				
			}
		}
		return max;
	}
	
	public double tankAverage() {
		int totalTanks = 0;
		for(Territory t : GameController.game.getTerritories()) {
			if(t.getOwner().equals(this))totalTanks += t.getTanks();
		}
		return  ((double)totalTanks/this.getTerritories());
	}
	
	public void useCards() {
		int bonus=0;
		int a = 0;
		
		
			
			if(this.cards.size() == 3) {
				
				bonus = GameController.game.checkTris(cards.get(0),cards.get(1),cards.get(2));
				
				if(bonus > 0 ) {
					playCard(cards.get(0));
					playCard(cards.get(1));
					playCard(cards.get(2));
				}
				
				
			}
			if(this.cards.size() == 4) {
				
				a = GameController.game.checkTris(cards.get(0),cards.get(1),cards.get(3));
				if(a > 0 ) {
					playCard(cards.get(0));
					playCard(cards.get(1));
					playCard(cards.get(3));
					bonus = a;
				}else {
					a = GameController.game.checkTris(cards.get(0),cards.get(2),cards.get(3));
					if(a > 0 ) {
						playCard(cards.get(0));
						playCard(cards.get(2));
						playCard(cards.get(3));
						bonus = a;
					}
				}
				if(a == 0) {
					a = GameController.game.checkTris(cards.get(1),cards.get(2),cards.get(3));
					if(a > 0 ) {
						playCard(cards.get(1));
						playCard(cards.get(2));
						playCard(cards.get(3));
						bonus = a;
					}
				}
				
				
				
			}
			if(this.cards.size() == 5) {
				a = 0;
				
				
				a = GameController.game.checkTris(cards.get(0),cards.get(1),cards.get(4));
				if(a > 0 ) {
					playCard(cards.get(0));
					playCard(cards.get(1));
					playCard(cards.get(4));
					bonus = a;
				}else {
					a = GameController.game.checkTris(cards.get(0),cards.get(2),cards.get(4));
					if(a > 0 ) {
						playCard(cards.get(0));
						playCard(cards.get(2));
						playCard(cards.get(4));
						bonus = a;
					}
				}
				if(a == 0) {
					a = GameController.game.checkTris(cards.get(0),cards.get(3),cards.get(4));
					if(a > 0 ) {
						playCard(cards.get(0));
						playCard(cards.get(3));
						playCard(cards.get(4));
						bonus = a;
					}
				}
				if(a == 0) {
					a = GameController.game.checkTris(cards.get(1),cards.get(2),cards.get(4));
					if(a > 0 ) {
						playCard(cards.get(1));
						playCard(cards.get(2));
						playCard(cards.get(4));
						bonus = a;
					}
				}
				if(a == 0) {
					a = GameController.game.checkTris(cards.get(1),cards.get(3),cards.get(4));
					if(a > 0 ) {
						playCard(cards.get(1));
						playCard(cards.get(3));
						playCard(cards.get(4));
						bonus = a;
					}
				}
				if(a == 0) {
					a = GameController.game.checkTris(cards.get(2),cards.get(3),cards.get(4));
					if(a > 0 ) {
						playCard(cards.get(2));
						playCard(cards.get(3));
						playCard(cards.get(4));
						bonus = a;
					}
				}
			
		}
		if(bonus > 0 ) {
			giveBonusTanks(bonus);
			System.out.println("BONUS CARTE");
			GameController.getInstance().setPhaseTextArea(GameController.game.getCurrentTurn().getName() + " received " + bonus + " bonus armies");
			GameController.getInstance().updateCardsNumber();
			}
		
	}
	/**
	 * @param eliminated the current player
	 */
	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}

	/* TO-STRING for testing */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " [color:" + color.toString() + " AI:" + isAI + "]";
	}

	/**
	 * @return TRUE if is an AI player, FALSE otherwise
	 */
	public boolean isAI() {
		return isAI;
	}

}
