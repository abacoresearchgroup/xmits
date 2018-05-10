package converter.collectors.diagrams;

import java.util.List;

import reader.list.Node;
import converter.collectors.DiagramLogic;
import converter.collectors.diagrams.sequence.ElementCollector;
import converter.collectors.diagrams.sequence.MessageCollector;
import converter.collectors.diagrams.sequence.SequenceGuardCollector;
import converter.collectors.diagrams.sequence.TransitionCollector;
import converter.collectors.transitions.TransitionTools;

public class SequenceDiagramLogic implements DiagramLogic{

	//Attributes
	private MessageCollector messageCollector;
	private TransitionCollector transitionCollector;
	private ElementCollector elementCollector;
	private TransitionTools transitionTools;
	private SequenceGuardCollector sequenceGuardCollector;
	
	//Constructor
	public SequenceDiagramLogic () {
		messageCollector = new MessageCollector();
		transitionCollector = new TransitionCollector();
		elementCollector = new ElementCollector();
		transitionTools = new TransitionTools();
		sequenceGuardCollector = new SequenceGuardCollector();
	}
	
	@Override
	public void process(List<Node> xmi) {	
		messageCollector.collect(xmi);
		transitionCollector.collect(xmi);
		elementCollector.collect(xmi);
		transitionTools.AddTransitions();
		sequenceGuardCollector.collect(xmi);
	}

}
