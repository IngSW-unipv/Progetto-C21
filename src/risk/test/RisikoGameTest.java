package risk.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
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
	ArrayList<Player> playersList = new ArrayList();
	Player[] playersArr ;
	
	@BeforeEach
	public void setUp() {
		Player1 = new Player("pippo", Color.BLACK, true);
		Player2 = new Player("pluto", Color.GREEN, true);
		

		playersList.add(Player1);
		playersList.add(Player2);
		playersArr= new Player[playersList.size()];
				playersArr = playersList.toArray(playersArr);

	}
	
	
	
	@Test
	public void testBattleWinP1() {
		setUp();
		int atk[]= {6,6,5};
		int def[]= {4,3};
		RisikoGame game;
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			territory1=game.getTerritoryRandPlayer(Player1,3);
			territory2=game.getTerritoryRandPlayer(Player2,2);
			
			game.battle(atk,def,3,2,territory1,territory2);
			assertTrue("territorio conquistato da p1",game.getTerritory1(territory2).getOwner().equals(Player1));
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
@Test
	public void testBattleWinP2() {
		setUp();
		int atk[]= {3,2,1};
		int def[]= {4,3};
		RisikoGame game;
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			territory1=game.getTerritoryRandPlayer(Player1,3);
			territory2=game.getTerritoryRandPlayer(Player2,2);
			
			game.battle(atk,def,3,2,territory1,territory2);
			assertFalse("ha vinto il p2",game.getTerritory1(territory2).getOwner().equals(Player1));
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	@Test
	public void testBattleWinDefender() {
		setUp();
		int atk[]= {3,2};
		int def[]= {3,2};
		RisikoGame game;
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			territory1=game.getTerritoryRandPlayer(Player1,3);
			territory2=game.getTerritoryRandPlayer(Player2,2);
			
			game.battle(atk,def,2,2,territory1,territory2);
			assertFalse("ha vinto la difesa",game.getTerritory1(territory2).getOwner().equals(Player1));
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void testBattleLost1() {
		setUp();
		int atk[]= {3,3};
		int def[]= {3,2};
		RisikoGame game;
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			territory1=game.getTerritoryRandPlayer(Player1,2);
			territory2=game.getTerritoryRandPlayer(Player2,2);
			
			game.battle(atk,def,2,2,territory1,territory2);
			assertTrue("hai perso un carro nel territorio 1",game.getTerritory1(territory1).getTanks()==1);
			assertTrue("hai perso un carro nel territorio 2",game.getTerritory1(territory2).getTanks()==1);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testBattleLostAttacker3vs1() {
		setUp();
		int atk[]= {5,4,1};
		int def[]= {6};
		RisikoGame game;
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			territory1=game.getTerritoryRandPlayer(Player1,3);
			territory2=game.getTerritoryRandPlayer(Player2,1);
			
			game.battle(atk,def,2,1,territory1,territory2);
			assertTrue("hai perso un carro nel territorio 1",game.getTerritory1(territory1).getTanks()==2);
			assertTrue("territorio 2 con 1 carro",game.getTerritory1(territory2).getTanks()==1);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
