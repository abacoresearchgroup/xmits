package converter.logics.functions;

import global.logics.Builder;
import global.structure.Guard;
import global.structure.Message;
import global.structure.State;
import global.structure.TransitionSystem;
import global.tools.IdGenerator;

import java.util.Hashtable;
import java.util.List;

import converter.collectors.guards.GuardDictionary;
import converter.logics.LogicTools;
import converter.logics.TransitionMemory;
import converter.logics.dictionaries.FunctionDictionary;
import converter.logics.interfaces.Function;
import converter.logics.parallel.ParallelTools;
import converter.logics.parallel.PossibilityGenerator;
import converter.logics.structures.Element;
import converter.logics.structures.Possibility;

public class MultiStateFunction implements Function{

	//Attributes
	private Builder builder;
	private GuardDictionary guardDictionary;
	private FunctionDictionary functionDictionary;
	private LogicTools logicTools;
	private ParallelTools parallelTools;
	private IdGenerator idGenerator;
	private PossibilityGenerator possibilityGenerator;
	private TransitionMemory transitionMemory;
	
	//Constructor
	public MultiStateFunction () {
		builder = Builder.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		functionDictionary = FunctionDictionary.getInstance();
		logicTools = new LogicTools();
		parallelTools = new ParallelTools();
		idGenerator = IdGenerator.getInstance();
		possibilityGenerator = new PossibilityGenerator();
		transitionMemory = TransitionMemory.getInstance();
	}
	
//--Public Methods-----------------------------------------------------------------------------------------------------------------------------------------------------------------//
	@Override
	public void process(Element multiState, List<Integer> path) {
		
		Message message = new Message();
		message.setContent(parallelTools.createMultiStateContent(multiState.getMultiElement()));
		message.setContentId(multiState.getContent().getId());
		
		State state = new State();
		state.setMessage(message);
		for (Guard guard : guardDictionary.getGuardList().values()) {
			state.addGuard(guard);
		}
		
		TransitionSystem transitionSystem = new TransitionSystem();
		transitionSystem.addState(state);
		transitionSystem.setDiagram(multiState.getContent().getDiagram());
		
		builder.addChild(transitionSystem);
		builder.setPosition(transitionSystem);
		
		solve (multiState, path/*pathClone*/);
		
	}

//--Private Methods----------------------------------------------------------------------------------------------------------------------------------------------------------------//
	private void solve(Element multiState, List<Integer> path) {

		TransitionSystem position = builder.getPosition();
		Hashtable<String, Guard> guards = guardDictionary.getGuardListClone();
		String lastTransitionName = transitionMemory.getLastTransitionName();
		
		List<Possibility> possibilities = possibilityGenerator.generate(multiState);
		
		for (Possibility  possibility : possibilities) {			
			List<Integer> pathClone = logicTools.integerListClone(path);
			pathClone.add(idGenerator.getIntegerId());
			for (Element element : possibility.getElements()) {
				builder.setPosition(position);
				guardDictionary.rebuildGuardList(guards);
				transitionMemory.setLastTransitionName(lastTransitionName);
				Function function = functionDictionary.getFunction(element.getType());
 				function.process(element, pathClone);								
			}
		}
		
	}
	
}
