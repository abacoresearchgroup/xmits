package bridge;

import java.util.List;

import bridge.GuardList.GuardLogics;
import global.structure.Guard;
import global.structure.TransitionSystem;

public class Writer {
	
	//Attributes
	private Tools tools;
	private GuardLogics guardLogics;
	
	//Constructor
	public Writer () {
		tools = new Tools();
		guardLogics = new GuardLogics();
	}

//--Public Methods-------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public String createHeader () {
		String output = "MODULE main\n";
		return output;
	}

	public String createVar (String output, TransitionSystem root) {
		output = output + "\nVAR\n";
		output = output + "\nState: {";
		output = output + listStates(root);
		output = output + "};\n";
		output = output + listGuards(root);
		return output;
	}

	public String createAssign (String output, TransitionSystem root) {
		output = output + "\nASSIGN\n";
		output = output + "\ninit (State):= ";
		output = output + getInitialState(root);
		output = output + ";\n";
		return output;
	}

	public String createNext (String output, TransitionSystem root) {
		output = output + "\nnext (State):= case\n";
		output = output + listTransitions(root);
		output = output + "\nTRUE: State;\n";
		output = output + "esac;\n";
		return output;
	}

	public String createGuards (String output, TransitionSystem root) {
		output = output + guardLogics.listGuardStates(root);
		return output;
	}

	//--Private Methods-------------------------------------------------------------------------------------------------------------------------------------------//
	private String listStates (TransitionSystem root) {
		String states = "";
		List<TransitionSystem> allStates = tools.getAllStates(root);
		allStates = tools.getStateSet(allStates);
		int counter = 1;
		for (TransitionSystem state : allStates) {
			states = states + "\n" + state.getState().getMessage().getContent();
			if (counter != allStates.size()) {
				states = states + ", ";
			}
			counter ++;
		}
		return states;
	}
	
	private String listGuards (TransitionSystem root) {
		String guardList = "\n";
		if (tools.getGuards(root) == null) return guardList;
		for (Guard guard : tools.getGuards(root)) {
			guardList = guardList + guard.getName() + ": {dc,false,true};\n";
		}
		return guardList;
	}

	private String getInitialState (TransitionSystem root) {
		for (TransitionSystem state : tools.getAllStates(root)) {
			if (state.getState().getMessage().getContent() != null) {
				return state.getState().getMessage().getContent();
			}
		}
		return null;
	}
	
	private String listTransitions (TransitionSystem root) {
		String transitions = "";
		for (TransitionSystem state : tools.getAllStates(root)) {
			if (state.getState().getMessage().getContent() != null) {
				for (TransitionSystem next : state.getNext()) {
					transitions = transitions + "State = " + state.getState().getMessage().getContent() + getStateGuards(next).toLowerCase() + ": " + next.getState().getMessage().getContent() + ";\n";
				}
			}
		}
		return transitions;
	}

	private String getStateGuards (TransitionSystem next) {
		String guards = "";
		for (Guard guard : next.getState().getGuards()) {
			guards = guards + " & " + guard.getName() + "=" + guard.getValue();
		}
		return guards;
	}
	
}
