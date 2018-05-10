package converter.collectors;

import global.structure.Diagram;

import java.util.HashMap;

public class DiagramLogicDictionary {
	
	//Attributes
	private static DiagramLogicDictionary uniqueInstance;
	private static DiagramLogicFacade diagramLogicFacade;
	private HashMap<Diagram, DiagramLogic> diagramLogics;	
	
	//Constructor
	private DiagramLogicDictionary () {
		diagramLogics = new HashMap<Diagram, DiagramLogic>();
		diagramLogicFacade = new DiagramLogicFacade();
	}
	
	//Static Methods
	public static DiagramLogicDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new DiagramLogicDictionary();
			diagramLogicFacade.init(uniqueInstance);
		}
		return uniqueInstance;
	}

	//Public Methods
	public void addDiagramLogic (Diagram diagram, DiagramLogic diagramLogic) {
		diagramLogics.put(diagram, diagramLogic);
	}
	
	public DiagramLogic getDiagramLogic (Diagram diagram) {
		return diagramLogics.get(diagram);
	}
	
}
