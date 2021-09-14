package risk.test;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import risk.model.Player;
import risk.model.Territory;
import risk.model.util.Color;



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
		territory1.addTanks(20);
		territory1.addTanks(2);
	
	}
	
	
	
	@Test
	public void testConquer() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveTanks() {
		
		
	}
	
}
