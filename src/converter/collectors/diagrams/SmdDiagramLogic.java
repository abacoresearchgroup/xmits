package converter.collectors.diagrams;

import java.util.List;

import reader.list.Node;
import converter.collectors.DiagramLogic;
import converter.collectors.diagrams.smd.ElementCollector;
import converter.collectors.diagrams.smd.TransitionCollector;
import converter.collectors.transitions.TransitionTools;

public class SmdDiagramLogic implements DiagramLogic {
	
	//Attributes
	private ElementCollector elementCollector;
	private TransitionCollector transitionCollector;
	private TransitionTools transitionTools;
	
	//Constructor
	public SmdDiagramLogic () {
		elementCollector = new ElementCollector();
		transitionCollector = new TransitionCollector();
		transitionTools = new TransitionTools();
	}

	@Override
	public void process(List<Node> xmi) {
		elementCollector.colect(xmi);
		transitionCollector.collect(xmi);
		transitionTools.AddTransitions();
	}

}
