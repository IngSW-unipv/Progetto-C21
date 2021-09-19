package risk.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import risk.model.Player;
import risk.model.RisikoGame;
import risk.model.Territory;
import risk.model.util.Color;



class RisikoGameTest {
	private Player Player1;
	private	Player Player2;
	private Territory  territory1;
	private Territory  territory2;
	static String terrFile = "src/risk/asset/territories.txt", continentsFile = "src/risk/asset/continents.txt", missionsFile = "src/risk/asset/missions.txt";
	ArrayList<Player> playersList = new ArrayList<>();
	Player[] playersArr ;
	RisikoGame game;


	@BeforeEach
	public void setUp() throws NumberFormatException, IOException {
		Player1 = new Player("pippo", Color.BLACK, true);
		Player2 = new Player("pluto", Color.GREEN, true);


		playersList.add(Player1);
		playersList.add(Player2);
		playersArr= new Player[playersList.size()];
		playersArr = playersList.toArray(playersArr);


		game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);

	}


	@Test
	public void testBattleWinP1() {
		try {
			setUp();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int atk[]= {6,6,5};
		int def[]= {4,3};

		territory1 = game.getTerritoryRandPlayer(Player1, 3);
		territory2 = game.getTerritoryRandPlayer(Player2, 2);

		game.battle(atk, def, 3, 2, territory1, territory2);
		assertTrue("territorio conquistato da p1", game.getTerritory1(territory2).getOwner().equals(Player1));



	}

	@Test
	public void testBattleWinP2() {
		try {
			setUp();
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int atk[]= {3,2,1};
		int def[]= {4,3};

		territory1 = game.getTerritoryRandPlayer(Player1, 3);
		territory2 = game.getTerritoryRandPlayer(Player2, 2);

		game.battle(atk, def, 3, 2, territory1, territory2);
		assertFalse("ha vinto il p2", game.getTerritory1(territory2).getOwner().equals(Player1));
	}


	@Test
	public void testBattleWinDefender() {
		try {
			setUp();
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int atk[]= {3,2};
		int def[]= {3,2};
		territory1 = game.getTerritoryRandPlayer(Player1, 3);
		territory2 = game.getTerritoryRandPlayer(Player2, 2);

		game.battle(atk, def, 2, 2, territory1, territory2);
		assertFalse("ha vinto la difesa", game.getTerritory1(territory2).getOwner().equals(Player1));

	}

	@Test
	public void testBattleLost1() {
		try {
			setUp();
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int atk[]= {3,3};
		int def[]= {3,2};
		territory1 = game.getTerritoryRandPlayer(Player1, 2);
		territory2 = game.getTerritoryRandPlayer(Player2, 2);

		game.battle(atk, def, 2, 2, territory1, territory2);
		assertEquals("hai perso un carro nel territorio 1", 1, game.getTerritory1(territory1).getTanks());

		assertTrue("hai perso un carro nel territorio 2", game.getTerritory1(territory2).getTanks() == 1);


	}

	@Test
	public void testBattleLostAttacker3vs1() {
		try {
			setUp();
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int atk[]= {5,4,1};
		int def[]= {6};
		territory1 = game.getTerritoryRandPlayer(Player1, 3);
		territory2 = game.getTerritoryRandPlayer(Player2, 1);

		game.battle(atk, def, 2, 1, territory1, territory2);
		assertTrue("hai perso un carro nel territorio 1", game.getTerritory1(territory1).getTanks() == 2);
		assertTrue("territorio 2 con 1 carro", game.getTerritory1(territory2).getTanks() == 1);

	}


}
