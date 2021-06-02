package risk.model;

import risk.model.util.Color;

public class Player {
	
	private String name;
	private Color color;
	private boolean isAI;
	private boolean eliminated;
	
	/*
	private Mission mission;
	private ArrayList<Card> cards;
	
	? private int tanks;
	? private int bonusTanks;
	? private int continents;
	? private int territories;
	*/
	
	public Player(String name, Color color, boolean isAI) {
		this.name = name;
		this.color = color;
		this.isAI = isAI;
		this.eliminated = false;
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

	/* TO-STRING for testing */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " [color:" + color.toString() + " AI:" + isAI + "]";
	}
	
}
