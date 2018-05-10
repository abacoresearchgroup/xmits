package converter.collectors.diagrams;

import java.util.List;

import reader.list.Node;
import converter.collectors.DiagramLogic;
import converter.collectors.diagrams.activity.ActivityGuardCollector;
import converter.collectors.diagrams.activity.EdgeHandler;
import converter.collectors.diagrams.activity.NodeHandler;
import converter.collectors.transitions.TransitionTools;

public class ActivityDiagramLogic implements DiagramLogic {

	//Attributes
	private NodeHandler nodeHandler;
	private EdgeHandler edgeHandler;
	private TransitionTools transitionTools;
	private ActivityGuardCollector guardCollector;
	
	//Constructor
	public ActivityDiagramLogic () {
		nodeHandler = new NodeHandler();
		edgeHandler = new EdgeHandler();
		transitionTools = new TransitionTools();
		guardCollector = new ActivityGuardCollector();
	}
	
	@Override
	public void process(List<Node> xmi) {
		for (Node node : xmi) {
			if (node.getContent().getName() != null) {
				switch (node.getContent().getName()) {
				case "node":
					nodeHandler.process(node);
					break;
				case "edge" :
					edgeHandler.process(node);
					break;
				}
			}
		}
		
		transitionTools.AddTransitions();
		guardCollector.collect(xmi);
		
	}

}
