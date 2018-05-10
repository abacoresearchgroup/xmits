package tuts.tools;

import java.util.List;

import tuts.logics.Lane;
import tuts.logics.Track;
import global.logics.Builder;
import global.structure.Guard;
import global.structure.Message;
import global.structure.State;
import global.structure.TransitionSystem;

public class OutputTools {
	
	//Attributes
	private Builder builder;
	private GuardTools guardTools;
	
	//Constructor
	public OutputTools () {
		builder = Builder.getInstance();
		guardTools = new GuardTools();
	}
	
//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------//
	public synchronized TransitionSystem createTuts (Track track) {
		TransitionSystem tuts = new TransitionSystem();
		
		Message message = createMessage(track.getAllLanes());
		State state = createState(track.getActiveLanes().get(0), message);
		tuts.addState(state);
		
		builder.setPosition(track.getPosition());
		builder.addChild(tuts);
		
		return tuts;
	}
	
//--Private Methods-------------------------------------------------------------------------------------------------------------------------------------------//	
	private Message createMessage (List<Lane> lanes) {
		Message message = new Message();
		String content = "(";
		int counter = 1;
		
		for (Lane lane : lanes) {
			if (lane.getCheckeredFlag()) {
				content = content + "-";
			}else{
				content = content + lane.getTransitionSystem().getState().getMessage().getContent();
			}
			if (counter < lanes.size()) {
				content = content + ", ";
			}
			counter ++;
		}
		
		content = content + ")";
		message.setContent(content);
		return message;
	}
	
	private State createState (Lane lane, Message message) {
		State state = new State();
		state.setMessage(message);		
		for (Guard guard : guardTools.addMissingGuards(lane.getTransitionSystem().getState().getGuards())) {
			state.addGuard(guard.getClone());
		}
		return state;
	}
	
}
