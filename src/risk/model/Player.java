package risk.model;

import java.util.ArrayList;

import risk.controller.AttackController;
import risk.controller.GameController;
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

	/*
	 * ? private int continents; //numero di continenti ? private int territories;
	 * //numero di territori --> Ciascun player sa solo quanti continenti ha ma non
	 * sa quali sono, la classe Risiko Game ha le varie associazioni
	 */

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
//		shaker = new DiceShaker();
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

	
	
	
	  public void playTurn() {
	  
			Territory temp = null;
			int counter = 0;
			switch (GameController.getGame().getGamePhase()) {
			case FIRSTTURN:
				temp = GameController.getGame().getRandomCurrentPlayerTerritory();
				if (bonusTanks > 0) {
					while (counter < 3 && bonusTanks > 0) {
						temp.addTanks(1);
						GameController.getInstance()
								.setPhaseTextArea(this.name + " has placed " + 1 + " tanks" + " in " + temp.getName());
						temp.getOwner().placeTank(1); // cambia metti 1 al posto di 21
						counter++;
					}
				}
				counter = 0;
//				GameController.getInstance().nextPhase();
		  //AIRecapSceneController.getInstance() .setText(this.name +
			 //": armata posizionata sul territorio " + temp.getName() + "\n");
			  //GameController.getInstance().firstTurn(); } 
		  break;
		  
	  
		case DRAFT:
	    	while (bonusTanks > 0) { 
	    	temp = GameController.getGame().getRandomCurrentPlayerTerritory();
	    	temp.addTanks(1);
		  	GameController.getInstance().setPhaseTextArea(this.name+
					" has placed "+1+" tanks"+" in "+ temp.getName());
		  	temp.getOwner().placeTank(1); 
	    	}
	    		
//			GameController.getInstance().nextPhase();
	    	  break;
	    
	    	
	    case ATTACK:
	    	
			AttackController at = new AttackController(); // static??
			boolean enter = false;
	    	for (Territory t : GameController.getGame().getTerritories()) { 		//fare metodo per restituire i territori in ordine casuale in 
	    																			//quanto l'arrayList mantiene l'ordine??
			  if (t.getTanks() > 2 && t.getOwner().equals(this)) {
			  GameController.getInstance().setTerritory1(t);
		  } 
	  }
		if (GameController.getInstance().getTerritory1() != null) {
	    	for (Territory t : GameController.getInstance().getTerritory1().getConfinanti()) { 
				if (!t.getOwner().equals(this)) {
					enter = true;
				  GameController.getInstance().setTerritory2(t);
				  System.out.println("t1"+GameController.getInstance().getTerritory1().getName()+"t2 "+GameController.getInstance().getTerritory2().getName());
					at.aiAttack(GameController.getInstance().getTerritory1(),
							GameController.getInstance().getTerritory2());
					
					break;
			  } 
	    	}
	    }
	    	
		if (enter == false) {
			System.out.println("case attack");
			System.out.println("Territorio 1 " + GameController.getInstance().getTerritory1() + "territorio 2 "+ GameController.getInstance().getTerritory2());
		}
		GameController.getInstance().setTerritory1(null);
		GameController.getInstance().setTerritory2(null);
		 

//	    GameController.getInstance().nextPhase();
	    			
	    	
	    	break;
	    	
	    case FORTIFY:
	    	
			Territory t1 = GameController.getInstance().getTerritory1();
			Territory t2 = GameController.getInstance().getTerritory2();
			boolean fortified = false;

			for (Territory t : GameController.getGame().getTerritories()) {
				if (t.getTanks() > 2 && t.getOwner().equals(this)) {
					t1 = t;
					break;
				}
			}
			if (t1 != null) {
				for (Territory t : t1.getConfinanti()) {

					if (t.getOwner().equals(this)) {
						t2 = t;
						GameController.getGame().moveTanks(t1, t2, 1);

						GameController.getInstance()
								.setPhaseTextArea(GameController.getInstance().getCurrentPlayer().getName() + " moved "
										+ 1 + " tanks from " + t1.getName() + " to " + t2.getName());
						break;
					}

				}
			}
			System.out.println("case fortify");
			System.out.println("Territorio 1 " + t1 + "territorio 2 " + t2);

			GameController.getInstance().setTerritory1(null);
			GameController.getInstance().setTerritory2(null);

//			GameController.getInstance().nextPhase();
	    	break;
	  }


	  
	  
	  
	  
	  
	  //	  
//	  for (Territory t : GameController.getInstance().getGame().getTerritories()) { 
//		  if (t.getTanks() > 3 && t.getOwner().equals(this)) {
//			  GameController.getInstance().setTerritory12(t, null);
//		  } 
//	  }
//	   
//	   break;  
	  
	  /*if (GameController.territory1 != null) { for (Territory t :
	  GameController.territory1.getConfinanti()) { if
	  (!t.getOwner().equals(this) && t.getTanks() < 3) {
	  GameController.getInstance().setTerritory2(t);
	  AttackSceneController.aiAttack(); if (t.getTanks() == 0) {
	  GameSceneController.getInstance().getGame().conquer(GameSceneController.
	  territory1, t);
	  GameSceneController.getInstance().getGame().moveTanks(GameSceneController.
	  territory1, GameSceneController.territory2, 1); }
	  AIRecapSceneController.getInstance() .setText(this.name + ": ha attaccato " +
	  GameSceneController.territory2.getName() + "con " +
	  GameSceneController.territory1.getName());
	  GameSceneController.getInstance().updateTanks();
	  GameSceneController.getInstance().setTerritory12(null, null); break; } } }
	  
	  GameSceneController.getInstance().nextTurn();
	  
	  break; }*/
	 
	  }
	 
	/**
	 * @return TRUE if the current player is eliminated, FALSE otherwise
	 */
	public boolean isEliminated() {
		return eliminated;
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
