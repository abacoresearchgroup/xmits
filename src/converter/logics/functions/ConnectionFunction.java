package converter.logics.functions;

import global.exceptions.converter.TransitionException;

import java.util.List;

import converter.logics.interfaces.Function;
import converter.logics.structures.Element;

public class ConnectionFunction implements Function{

	//Attributes
	private TransitionFunction transitionFunction;
		
	//Constructor
	public ConnectionFunction () {
		transitionFunction = new TransitionFunction();
	}

	//Methods
	@Override
	public void process (Element connection, List<Integer> path) {

		try {
			transitionFunction.process(connection, path);
		} catch (TransitionException e) {
			e.printStackTrace();
		}
		
	}

}
