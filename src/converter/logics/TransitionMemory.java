package converter.logics;

public class TransitionMemory {

	//Attributes
	private static TransitionMemory uniqueInstance;
	private String lastTransitionName;
	
	//Constructor
	private TransitionMemory () {
		lastTransitionName = "";
	}
	
	//Static Methods
	public static TransitionMemory getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new TransitionMemory();
		}
		return uniqueInstance; 
	}
	
	//Public Methods
	public void setLastTransitionName (String name) {
		if (name != null) {
			lastTransitionName = name;
		}
	}
	
	public void clearLastTransitionName () {
		lastTransitionName = "";
	}
	
	public String getLastTransitionName () {
		return lastTransitionName;
	}
	
	public void reset () {
		lastTransitionName = "";
	}
	
}
