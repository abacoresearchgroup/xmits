package converter.logics;

import global.exceptions.converter.StartException;
import global.exceptions.converter.TransitionException;
import global.logics.Builder;
import global.structure.Guard;

import java.util.ArrayList;
import java.util.List;

import converter.collectors.guards.GuardDictionary;
import converter.logics.functions.TransitionFunction;
import converter.logics.parallel.Synchrony;
import converter.logics.structures.Element;

public class Core {
	
	//Attributes
	private static Core uniqueInstance;
	private Builder builder;
	private GuardDictionary guardDictionary;
	private Element startElement;
	private TransitionFunction transitionFunction;
	private Synchrony synchrony;
	
	//Constructor
	private Core () {
		builder = Builder.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		transitionFunction = new TransitionFunction();
		synchrony = Synchrony.getInstance();
	}
	
//--Static methods-------------------------------------------------------------------------------------------------------------------------------------------------------//
	public static Core getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new Core();
		}
		return uniqueInstance;
	}
	
//--Public methods-------------------------------------------------------------------------------------------------------------------------------------------------------//
	public void setStartElement (Element startElement) {
		this.startElement = startElement;
	}
	
	public void start () throws StartException {
		if (startElement == null) {
			throw new StartException();
		}else{
			init();
			List<Integer> path = new ArrayList<Integer>();
			try {
				transitionFunction.process(startElement, path);
			} catch (TransitionException e) {
				e.printStackTrace();
			}
			startElement = null;
		}
	}
	
//--Private methods-------------------------------------------------------------------------------------------------------------------------------------------------------//
	private void init () {
		synchrony.reset();
		builder.getRoot().setDiagram(startElement.getContent().getDiagram());
		for (Guard guard : guardDictionary.getGuardListClone().values()) {
			builder.getRoot().getState().addGuard(guard);
		}
	}
	
}
