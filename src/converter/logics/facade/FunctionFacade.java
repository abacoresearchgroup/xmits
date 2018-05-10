package converter.logics.facade;

import converter.logics.dictionaries.FunctionDictionary;
import converter.logics.functions.ConnectionFunction;
import converter.logics.functions.DecisionFunction;
import converter.logics.functions.DefaultFunction;
import converter.logics.functions.ForkFunction;
import converter.logics.functions.JoinFunction;
import converter.logics.functions.MultiStateFunction;
import converter.logics.functions.StateFunction;
import converter.logics.interfaces.Function;
import converter.logics.structures.Type;

public class FunctionFacade {

	//Methods
	public void init (FunctionDictionary functionDictionary) {
		
		Function stateFunction = new StateFunction();
		functionDictionary.addFunction(Type.State, stateFunction);
		
		Function multiStateFunction = new MultiStateFunction();
		functionDictionary.addFunction(Type.MultiState, multiStateFunction);
		
		Function forkFunction = new ForkFunction();
		functionDictionary.addFunction(Type.Fork, forkFunction);
		
		Function joinFunction = new JoinFunction();
		functionDictionary.addFunction(Type.Join, joinFunction);
		
		Function decisionFunction = new DecisionFunction();
		functionDictionary.addFunction(Type.Decision, decisionFunction);
		
		Function connectionFunction = new ConnectionFunction();
		functionDictionary.addFunction(Type.Connection, connectionFunction);
		
		Function defaultFunction = new DefaultFunction();
		functionDictionary.addFunction(Type.Default, defaultFunction);
		
	}
	
}
