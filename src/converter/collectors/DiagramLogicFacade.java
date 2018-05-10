package converter.collectors;

import global.structure.Diagram;
import converter.collectors.diagrams.ActivityDiagramLogic;
import converter.collectors.diagrams.SequenceDiagramLogic;
import converter.collectors.diagrams.SmdDiagramLogic;

public class DiagramLogicFacade {

	//Methods
	public void init(DiagramLogicDictionary diagramLogicDictionary) {
		
		DiagramLogic activityDiagramLogic = new ActivityDiagramLogic();
		diagramLogicDictionary.addDiagramLogic(Diagram.Activity, activityDiagramLogic);
		
		DiagramLogic sequenceDiagramLogic = new SequenceDiagramLogic();
		diagramLogicDictionary.addDiagramLogic(Diagram.Sequence, sequenceDiagramLogic);
		
		DiagramLogic smdDiagramLogic = new SmdDiagramLogic();
		diagramLogicDictionary.addDiagramLogic(Diagram.SMD, smdDiagramLogic);
		
	}

}
