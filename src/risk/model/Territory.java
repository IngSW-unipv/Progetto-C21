package risk.model;

import java.util.ArrayList;


public class Territory {

	private String name;
	private Player owner;
	private int tanks;
	private ArrayList<Territory> confinanti;
	private int id;
	private String continent;


	
	

	/**
	 * Creates a Territory
	 * @param name of the Territory
	 * @param id number of the Territory
	 * @param continent in which the Territory is
	 */
	public Territory(String name, int id, String continent) {
		this.name = name;
		tanks = 0;
		this.id = id;
		this.continent = continent;	
	}

	
	
	public void setTanks(int tanks) {
		this.tanks = tanks;
	}



	/**
	 * Sets the borders of a territory through an array list
	 * 
	 * @param confinanti is the list of territories in the borders
	 */
	public void setConfinanti(ArrayList<Territory> confinanti) {
		this.confinanti = confinanti;
	}

	/**
	 * Verifies if two territories are directly near each other
	 * 
	 * @param t is the second territory
	 * @return boolean
	 */
	public boolean isConfinante(Territory t) {

		for (Territory t1 : confinanti) {
			if (t1.getId() == t.getId())
				return true;
		}
		return false;
	}

	/**
	 * Adds tanks to the territory
	 * 
	 * @param newTanks is the number of tanks added
	 */
	public void addTanks(int newTanks) {
		tanks = getTanks() + newTanks;
	}

	/**
	 * Removes tanks from the territory
	 * 
	 * @param lostTanks is the number of tanks to remove
	 */
	public void removeTanks(int lostTanks) {
		tanks = getTanks() - lostTanks;
	}

	/**
	 * Changes the owner of the territory
	 * 
	 * @param owner is the new owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean isAttaccabileFrom(Territory t) {

		for (Territory t1 : t.getConfinanti()) {

			if (t1.getId() == this.id) {
				if (!t.getOwner().equals(this.owner)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public boolean isSpostabileFrom(Territory t) {

		for (Territory t1 : t.getConfinanti()) {

			if (t1.getId() == this.id) {
				if (t.getOwner().equals(this.owner) && t.getTanks() > 1) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Returns an array with all the territories on the border
	 * 
	 * @return confinanti
	 */
	public ArrayList<Territory> getConfinanti() {
		return confinanti;
	}

	/**
	 * Returns the number of tanks on the territory
	 * 
	 * @return tanks
	 */
	public int getTanks() {
		return tanks;
	}

	/**
	 * Returns the owner of the territory
	 * 
	 * @return owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Returns the name of the territory
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the ID of the territory
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name + "\n";
	}

	/**
	 * Prints the borders of the territory
	 */
	public void printConfini() {
		System.out.println("Confini di " + this.name + ": ");

		for (Territory t : confinanti) {
			System.out.println(t.toString());
		}
	}


	/**
	 * @return the continent's name
	 */
	public String getContinentName() {
		return continent;
	}
	

}