package global.structure;


import java.util.List;
import java.util.LinkedList;

public class State {
	
	//Attributes
	private Message message;
	private List<Guard> guards;
	
	//Constructor
	public State () {		
		guards = new LinkedList<Guard>();		
	}
	
	public void setMessage (Message message) {		
		this.message = message;		
	}
	
	public void addGuard (Guard guard) {		
		this.guards.add(guard);		
	}
	
	public Message getMessage () {		
		return this.message;		
	}
	
	public List<Guard> getGuards () {		
		return guards;		
	}

	public State getClone() {
		State clone = new State();
		Message cloneMessage = this.message.getClone();
		clone.setMessage(cloneMessage);
		for (Guard guard : this.guards) {
			Guard cloneGuard = guard.getClone();
			clone.addGuard(cloneGuard);
		}
		return clone;
	}

}
