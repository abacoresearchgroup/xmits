package converter.collectors.diagrams.sequence;

import global.exceptions.converter.InvalidGuardException;
import global.structure.Guard;
import global.structure.GuardValue;

import java.util.List;

import converter.collectors.guards.GuardDictionary;
import converter.collectors.guards.GuardHandler;
import converter.logics.dictionaries.ElementDictionary;
import converter.logics.structures.Element;
import converter.logics.structures.Transition;
import reader.list.Node;

public class SequenceGuardCollector {

	//Attributes
	private ElementDictionary elementDictionary;
	private GuardDictionary guardDictionary;
	private SequenceTools sequenceTools;
	private GuardHandler guardHandler;
	private List<Node> xmi;
	private int iterator;
	
	//Constructor
	public SequenceGuardCollector () {
		elementDictionary = ElementDictionary.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		sequenceTools = new SequenceTools();
		guardHandler = new GuardHandler();
		iterator = 0;
	}
	
//--Public Methods--------------------------------------------------------------------------------------------------------------------------------------------------//
	public void collect(List<Node> xmi) {
		this.xmi = xmi;
		iterateXmi();
		iterator = 0;
	}

//--Private Methods-------------------------------------------------------------------------------------------------------------------------------------------------//
	private void iterateXmi() {
		while (iterator < xmi.size()) {
			if (sequenceTools.isCombinedFragment(xmi.get(iterator))) {
				collectGuard();
			}else{
				iterator ++;
			}
		}
	}

	private void collectGuard() {
		String combinedFragmentId = sequenceTools.getFragmentId(xmi.get(iterator));		
		Element combinedFragment = elementDictionary.getElement(combinedFragmentId);

		int level = xmi.get(iterator).getLevel();
		iterator ++;
		
		while (iterator < xmi.size()) {
			
			if (level == xmi.get(iterator).getLevel()) {
				break;
			}
			
			if (sequenceTools.isCombinedFragment(xmi.get(iterator))) {
				collectGuard();
			}
			
			if (sequenceTools.isSpecification(xmi.get(iterator))) {
				try {
					Guard guard = guardHandler.getGuard(sequenceTools.getGuardValue(xmi.get(iterator)));
					addGuardToTransition(guard, combinedFragment);
					addGuardToDictionary(guard.getClone(), combinedFragmentId);
				} catch (InvalidGuardException e) {
					e.printStackTrace();
				}
			}
			
			iterator ++;
		}
		
		formatTransitionGuards(combinedFragment);
		
	}

	private void addGuardToTransition (Guard guard, Element combinedFragment) {
		String nextFragmentId = sequenceTools.getNextFragmentId(xmi, iterator);
		String nextMessageId = sequenceTools.getNextMessageId(xmi, iterator);
		
		GuardValue guardValue = guard.getValue();
		
		if (guardValue.equals(GuardValue.DC)) {
			guardValue = GuardValue.True;
		}
		
		for (Transition transition : combinedFragment.getContent().getOutgoingTransitions()) {
			if (transition.getOutgoingId().equals(nextMessageId) || transition.getOutgoingId().equals(nextFragmentId)) {
				transition.setGuardValue(guardValue);
				break;
			}
		}
	}
	
	private void formatTransitionGuards (Element combinedFragment) {
		GuardValue guardValue = null;
		
		for (Transition transition : combinedFragment.getContent().getOutgoingTransitions()) {
			if (transition.getGuardValue() != null) {
				guardValue = transition.getGuardValue();
				break;
			}
		}
		
		if (guardValue != null) {
			guardValue = invertGuardValue(guardValue);
		}else{
			guardValue = GuardValue.False;
		}
		
		for (Transition transition : combinedFragment.getContent().getOutgoingTransitions()) {
			if (transition.getGuardValue() == null) {
				transition.setGuardValue(guardValue);
			}
		}
	}
	
//--Guard Tools-------------------------------------------------------------------------------------------------------------------------------------------------//
	private GuardValue invertGuardValue (GuardValue guardValue) {
		if (guardValue.equals(GuardValue.True)) {
			return GuardValue.False;
		}
		return GuardValue.True;
	}
	
	private void addGuardToDictionary(Guard guard, String combinedFragmentId) {
		if (guard.getName() != null) {
			guard.setValue(GuardValue.DC);
			guardDictionary.addGuard(combinedFragmentId, guard);
		}
	}
}
