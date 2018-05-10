package tuts.tools;

import java.util.List;

import tuts.dictionaries.GuardDictionary;
import tuts.dictionaries.TransitionSystemDictionary;
import global.structure.Guard;
import global.structure.GuardValue;
import global.structure.TransitionSystem;

public class GuardTools {

	//Attributes
	private GuardDictionary guardDictionary;
	private TransitionSystemDictionary transitionSystemDictionary;
	
	//Constructor
	public GuardTools () {
		guardDictionary = GuardDictionary.getInstance();
		transitionSystemDictionary = TransitionSystemDictionary.getInstance();
	}
	
//--Public Methods-------------------------------------------------------------------------------------------------------------------------------------------------//
	public void collectGuards() {
		for (TransitionSystem root : transitionSystemDictionary.getTsList()) {
			addGuards(root.getState().getGuards());
		}
	}
	
	public List<Guard> addMissingGuards(List<Guard> guards) {
		for (Guard guard : guardDictionary.getGuardList()) {
			if (!guardIsThere(guard, guards)) {
				guards.add(guard);
			}
		}
		return guards;
	}
	
	public boolean allGuardsAreEqual(List<Guard> firstGuards, List<Guard> secondGuards) {
		for (Guard guard : firstGuards) {
			if (!sameQuantity(guard, firstGuards, secondGuards)) {
				return false;
			}
		}
		return true;
	}

	//--Private Methods------------------------------------------------------------------------------------------------------------------------------------------------//
	private void addGuards(List<Guard> guards) {
		for (Guard guard: guards) {
			if (firstTimeHere(guard)) {
				Guard copy = guard.getClone();
				copy.setValue(GuardValue.DC);
				guardDictionary.addGuard(copy);
			}
		}
	}

	private boolean firstTimeHere(Guard guard) {
		for (Guard stored: guardDictionary.getGuardList()) {
			if (stored.getName().equals(guard.getName())) {
				return false;
			}
		}
		return true;
	}
	
	private boolean guardIsThere(Guard guard, List<Guard> guards) {
		for (Guard stored : guards) {
			if (stored.getName().equals(guard.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean sameQuantity(Guard guard, List<Guard> firstGuards, List<Guard> secondGuards) {
		int quantityOne = 0;
		int quantityTwo = 0;
		for (Guard one : firstGuards) {
			if (guard.matches(one)) {
				quantityOne ++;
			}
		}
		for (Guard two : secondGuards) {
			if (guard.matches(two)) {
				quantityTwo ++;
			}
		}
		return quantityOne == quantityTwo;
	}
	
}
