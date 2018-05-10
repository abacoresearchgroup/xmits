package tuts.logics.qualifying;

public class Iterator {
	
	//Attributes
	private int iterator;
	
	//Constructor
	public Iterator () {
		iterator = 0;
	}
	
	//Methods
	public void iterate () {
		iterator ++;
	}

	public void reset () {
		iterator = 0;
	}
	
	public int getIterator () {
		return iterator;
	}
	
}
