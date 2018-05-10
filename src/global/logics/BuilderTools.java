package global.logics;

import global.structure.Message;
import global.structure.State;
import global.structure.TransitionSystem;

public class BuilderTools {

	//Methods
	public TransitionSystem createRoot () {
		Message message = new Message();
		State state = new State();
		TransitionSystem root = new TransitionSystem();
		state.setMessage(message);
		root.addState(state);
		return root;
	}
	
}
