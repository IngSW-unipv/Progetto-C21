package risk.test;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import risk.model.Player;
import risk.model.RisikoGame;
import risk.model.Territory;
import risk.model.util.Color;
import static org.junit.Assert.*;


class RisikoGameTest {
	private Player Player1;
	private	Player Player2;
	private Territory  territory1;
	private Territory  territory2;
	@Before
	public void setUp() {
		Player1 = new Player("pippo", Color.BLACK, true);
		Player1 = new Player("pluto", Color.GREEN, true);
		territory1=new Territory("Italia",10,"europa");
		territory2=new Territory("Germania",12,"europa");
		territory1.addTanks(3);
		territory1.setOwner(Player1);
		territory2.setOwner(Player2);
		territory2.addTanks(2);
		
		

	}
	
	
	
	@Test
	public void testBattle() {
		int atk[]= {6,6,5};
		int def[]= {4,3};
		RisikoGame game=new RisikoGame();
		game.battle(atk,def,3,2,territory1,territory2);
		assertEquals(Player1.getName(), territory2.getOwner().getName());
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

	
	
}
