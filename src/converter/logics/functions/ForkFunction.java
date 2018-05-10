package converter.logics.functions;

import global.exceptions.converter.ForkException;

import java.util.List;

import converter.logics.interfaces.Function;
import converter.logics.parallel.ParallelTools;
import converter.logics.structures.Element;

public class ForkFunction implements Function{
	
	//Attributes
	private ParallelTools parallelTools;
	private Function multiStateFunction;
	
	//Constructor
	public ForkFunction () {
		parallelTools = new ParallelTools();
		multiStateFunction = new MultiStateFunction();
	}

	//Methods
	@Override
	public void process (Element fork, List<Integer> path) {

		try {
			Element multiElement = parallelTools.solveFork(fork.getContent().getOutgoingTransitions());
			multiStateFunction.process(multiElement, path);
		}catch (ForkException e) {
			e.printStackTrace();
		}
		
	}

}
