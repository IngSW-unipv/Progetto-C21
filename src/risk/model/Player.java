package risk.model;

import risk.model.util.Color;

public class Player {
	
	private String name;
	private Color color;
	private boolean isAI;
	private boolean eliminated;
	private int tanks;
	
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

	/* TO-STRING for testing */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " [color:" + color.toString() + " AI:" + isAI + "]";
	}
	
}
