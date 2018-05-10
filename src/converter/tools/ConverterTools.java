package converter.tools;

import converter.collectors.guards.GuardDictionary;
import converter.logics.TransitionMemory;
import global.logics.Builder;
import global.tools.IdGenerator;

public class ConverterTools {
	
	//Attributes
	private Builder builder;
	private GuardDictionary guardDictionary;
	private IdGenerator idGenerator;
	private TransitionMemory transitionMemory;
	
	//Constructor
	public ConverterTools () {
		builder = Builder.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		idGenerator = IdGenerator.getInstance();
		transitionMemory = TransitionMemory.getInstance();
	}
	
	//Methods
	public void reset () {
		builder.reset();
		guardDictionary.reset();
		idGenerator.reset();
		transitionMemory.reset();
	}
}
