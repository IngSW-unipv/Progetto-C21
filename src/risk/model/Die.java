package risk.model;

public class Die {
	
	private final int MAX = 6;  

	private int faceValue;  
	
	/**
	 * Creates a die
	 */
	public Die() {
		faceValue = 1;
	}

	/**
	 * Alternative constructor to create a due (inutile????)
	 * 
	 * @param value is used to set the face showing on the die
	 */
	public Die(int value) {
	   faceValue = value;
	}
	
	/**
	 * Rolls the die generating a number between 1 and 6
	 * @return faceValue
	 */
	public int roll() {				
		faceValue = (int)(Math.random() * MAX) + 1;
	    return faceValue;
	}
	
}