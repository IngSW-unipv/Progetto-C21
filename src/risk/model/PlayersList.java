package risk.model;

import java.util.ArrayList;

public class PlayersList {

	public static ArrayList<Player> players; // oppure meglio un array normale?

	/**
	 * Returns the players on the list
	 * @return players
	 */
	public static ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Sets the players in the list
	 * @param list is the array containing the players
	 */
	public static void setPlayers(ArrayList<Player> list) {
		players = new ArrayList<Player>(list);
	}
}