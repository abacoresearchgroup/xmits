package converter.logics.functions;

import global.exceptions.converter.TransitionException;

import java.util.List;

import converter.logics.interfaces.Function;
import converter.logics.parallel.Synchrony;
import converter.logics.structures.Element;
import converter.logics.structures.Transition;

public class JoinFunction implements Function{
	
	//Attributes
	private Synchrony synchrony;
	private TransitionFunction transitionFunction;
	
	//Constructor
	public JoinFunction () {
		synchrony = Synchrony.getInstance();
		transitionFunction = new TransitionFunction();
	}

	@Override
	public void process (Element join, List<Integer> path) {
		
		synchrony.addCheckPoint(join.getContent().getLastTransition(), path);
		
		boolean flag = true;
		
		for (Transition incommingTransition : join.getContent().getIncommingTransitions()) {
			if (!synchrony.existis (incommingTransition, path)) {
				flag = false;
			}
		}
		
		if (flag) {
			try {
				transitionFunction.process(join, path);
			} catch (TransitionException e) {
				e.printStackTrace();
			}
		}
		
	}

}
