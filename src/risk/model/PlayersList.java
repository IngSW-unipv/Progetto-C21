package risk.model;

import java.util.ArrayList;

public class PlayersList {

	private static ArrayList<Player> players;

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