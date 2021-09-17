package risk.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;



import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;


import risk.model.Player;
import risk.model.PlayersList;
import risk.model.RisikoGame;
import risk.model.Territory;
import risk.model.util.Color;



class RisikoGameTest {
	private Player Player1;
	private	Player Player2;
	private Territory  territory1;
	private Territory  territory2;
	static String terrFile = "src/risk/asset/territories.txt", continentsFile = "src/risk/asset/continents.txt", missionsFile = "src/risk/asset/missions.txt";
	ArrayList<Player> playersList = new ArrayList();
	Player[] playersArr ;
	
	@Before
	public void setUp() {
		Player1 = new Player("pippo", Color.BLACK, true);
		Player2 = new Player("pluto", Color.GREEN, true);
		
		territory1.addTanks(3);
		territory1.setOwner(Player1);
		territory2.setOwner(Player2);
		territory2.addTanks(2);
		playersList.add(Player1);
		playersList.add(Player2);
		playersArr= new Player[playersList.size()];
				playersArr = playersList.toArray(playersArr);

	}
	
	
	
	@Test
	public void testBattle() {
		setUp();
		int atk[]= {6,6,5};
		int def[]= {4,3};
		RisikoGame game;
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			territory1=game.getTerritoryRandPlayer(Player1,3);
			territory2=game.getTerritoryRandPlayer(Player2,2);
			
			game.battle(atk,def,3,2,territory1,territory2);
			assertEquals(0,0);
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

	
	
}
