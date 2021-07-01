package risk.model;

import java.util.ArrayList;


import risk.model.util.Color;

public class Player {
	
	private String name;
	private Color color;
	private boolean isAI;
	private boolean eliminated;
	private int tanks;
	private int continents;
	private int territories;
	private int bonusTanks;
	private ArrayList<Card> cards;
	private Mission mission;
	
	/*
	private Mission mission;
	private ArrayList<Card> cards;
	
	? private int bonusTanks;
	? private int continents;
	? private int territories;  --> Non usiamo un ARRAYLIst ?
	*/
	
	public Player(String name, Color color, boolean isAI) {
		this.name = name;
		this.color = color;
		this.isAI = isAI;
		
		this.eliminated = false;
		this.tanks = 0;
		this.continents = 0;
		this.territories = 0;
		this.bonusTanks=0;
		cards = new ArrayList<Card>();
	}

	/* GETTERS AND SETTERS */	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isAI() {
		return isAI;
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	public boolean isEliminated() {
		return eliminated;
	}

	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}

	public int getTanks() {
		return tanks;
	}

	public void setTanks(int tanks) {
		this.tanks = tanks;
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
	 * Modifies the number of additional tanks the player receives
	 * @param n is the number of tanks added or subtracted
	 */
	public void giveBonusTanks(int n) {
		bonusTanks += n;
	}
	
	public void placeTank(int n) {
		
		this.bonusTanks -= n;
		this.tanks +=n;
		
	}
	
	/**
	 * Gives a mission to the player
	 * @param mission is the mission given
	 */
	public void giveMission(Mission mission) {
		this.mission = mission;
	}
	
	public Mission getMission() {
		return mission;
	}
	
	
	/**
	 * Gives a card to the player
	 * @param c is the card
	 */
	public void giveCard(Card c) {
		cards.add(c);
	}
	
	/**
	 * Removes a card from the player
	 * @param c is the card
	 */
	public void playCard(Card c) {
		cards.remove(c);
	}
	
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	

	/* TO-STRING for testing */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " [color:" + color.toString() + " AI:" + isAI + "]";
	}

	
	
}
