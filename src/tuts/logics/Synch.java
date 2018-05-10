package tuts.logics;

public class Synch {

	//Attributes
	private static Synch uniqueInstance;
	private int counter;
	
	//Constructor
	private Synch () {
		counter = 0;
	}
	
	//Static Methods
	public static Synch getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new Synch();
		}
		return uniqueInstance;
	}
	
	public synchronized void increaseCounter () {
		counter++;
	}
	
	public synchronized void decreaseCounter () {
		counter--;
	}
	
	public void rest () {
		while (counter > 0) {
			//wait
		}
	}

}
