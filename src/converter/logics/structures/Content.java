package converter.logics.structures;

import global.structure.Diagram;

import java.util.ArrayList;
import java.util.List;

public class Content {
	
	//Attributes
	private String id;
	private String name;
	private Diagram diagram;
	private List<Transition> incommingTransitions;
	private List<Transition> outgoingTransitions;
	private Transition lastTransition;
	
	//Constructor
	public Content () {
		incommingTransitions = new ArrayList<Transition>();
		outgoingTransitions = new ArrayList<Transition>();
	}

	//Methods
	public void setId (String id) {
		this.id = id;
	}
	
	public String getId () {
		return id;
	}

	public void setName (String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
	
	public void setDiagram (Diagram diagram) {
		this.diagram = diagram;
	}
	
	public Diagram getDiagram () {
		return diagram;
	}
	
	public void addIncommingTransition (Transition incommingTransition) {
		incommingTransitions.add(incommingTransition);
	}
	
	public List<Transition> getIncommingTransitions () {
		return incommingTransitions;
	}
	
	public void addOutgoingTransition (Transition outgoingTransition) {
		outgoingTransitions.add(outgoingTransition);
	}
	
	public List<Transition> getOutgoingTransitions () {
		return outgoingTransitions;
	}
	
	public void setLastTransition (Transition lastTransition) {
		this.lastTransition = lastTransition;
	}
	
	public Transition getLastTransition () {
		return lastTransition;
	}
	
	public Content getClone () {
		Content clone = new Content();
		clone.setId(id);
		clone.setName(name);
		clone.setDiagram(diagram);
		clone.setLastTransition(lastTransition);
		for (Transition incommingTransition : incommingTransitions)
			clone.addIncommingTransition(incommingTransition.getClone());
		for (Transition outgoingTransition : outgoingTransitions)
			clone.addOutgoingTransition(outgoingTransition);
		return clone;
	}
	
}
