package converter.collectors.diagrams.smd;

import converter.collectors.guards.GuardDictionary;
import converter.collectors.guards.GuardHandler;
import converter.logics.structures.Transition;
import global.exceptions.converter.InvalidGuardException;
import global.structure.Guard;
import global.structure.GuardValue;
import reader.list.Node;

public class SmdGuardCollector {

	//Attributes
	private GuardDictionary guardDictionary;
	private GuardHandler guardHandler;
	private SmdTools smdTools;
	
	//Constructor
	public SmdGuardCollector () {
		guardDictionary = GuardDictionary.getInstance();
		guardHandler = new GuardHandler();
		smdTools = new SmdTools();
	}
	
	//Public Methods
	public void collect(Node node, Transition lastTransition) {
		try {
			Guard guard = guardHandler.getGuard(smdTools.getAttributeValue(node, "value"));
			processGuard(guard, lastTransition);
		} catch (InvalidGuardException e) {
			e.printStackTrace();
		}
		
	}

	//Private Methods
	private void processGuard(Guard guard, Transition lastTransition) {
		if (guard.getName() != null) {
			storeGuard(guard, lastTransition);
		}
		addGuardToTransition(guard, lastTransition);
	}

	private void addGuardToTransition(Guard guard, Transition lastTransition) {
		if (guard.getValue().equals(GuardValue.DC)) {
			lastTransition.setGuardValue(GuardValue.True);
		}else{
			lastTransition.setGuardValue(guard.getValue());
		}
	}

	private void storeGuard(Guard guard, Transition lastTransition) {
		Guard neoGuard = guard.getClone();
		neoGuard.setValue(GuardValue.DC);
		guardDictionary.addGuard(lastTransition.getIncommingId(), neoGuard);
	}
	
}
