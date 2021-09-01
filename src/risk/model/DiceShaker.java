package risk.model;

import java.util.Arrays;



public class DiceShaker {
	
	private Die die1;
	private Die die2;
	private Die die3;
	
	private int[] results;			
//	private int[] defResults;

	/**
	 * Creates a DiceShaker with 3 dice, used for battles
	 */
	public DiceShaker() {		
		die1 = new Die();
		die2 = new Die();
		die3 = new Die();
				
		results = new int[3];
	}
	
	/**
	 * Rolls the dice used
	 * @param atk is the number of dice used by the attacker
	 * @param def is the number of dice used by the defender
	 * @return loss for the attacker and the defender
	 */
	public int[] rollDices(int n) {		
		
//		loss[0] = 0;
//		loss[1] = 0;

		switch(n) {								//switch per tirare 1,2 o 3 dadi dell'attaccante
		case 1:
			results[0] = die1.roll();
			results[1]= 0;
			results[2]= 0;
			break;
		case 2:
			results[0] = die1.roll();
			results[1] = die2.roll();
			results[2]= 0;
			break;
		case 3:
			results[0] = die1.roll();
			results[1] = die2.roll();
			results[2] = die3.roll();
			break;
		}
		
		/**
		 * Sorting the arrays using a for cycle to put them in decreasing order
		 */
		Arrays.sort(results);
		for (int i = 0, j = 3 - 1, tmp; i < j; i++, j--) {
	           tmp = results[i];
	           results[i] = results[j];
	           results[j] = tmp;
	       }


		return results;

	}
}
