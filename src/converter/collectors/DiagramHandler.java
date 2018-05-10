package converter.collectors;

import global.exceptions.converter.InvalidXMIException;
import global.exceptions.converter.NotSupportedDiagramException;
import global.structure.Diagram;

import java.util.List;

import reader.list.Node;

public class DiagramHandler {

	//Attributes
	private Diagram diagram;
	private DiagramTools diagramTools;
	private DiagramLogicDictionary diagramLogicDictionary;
	
	//Constructor
	public DiagramHandler () {
		diagramTools = new DiagramTools();
		diagramLogicDictionary = DiagramLogicDictionary.getInstance();
	}
	
	public void process(List<Node> xmi) throws InvalidXMIException, NotSupportedDiagramException {
		diagram = diagramTools.identifyDiagram(xmi);
		DiagramLogic diagramLogic = diagramLogicDictionary.getDiagramLogic(diagram);
		diagramLogic.process(xmi);
	}

}
