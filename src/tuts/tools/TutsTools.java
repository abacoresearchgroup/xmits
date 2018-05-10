package tuts.tools;

import tuts.dictionaries.GuardDictionary;
import tuts.dictionaries.TransitionSystemDictionary;
import global.logics.Builder;

public class TutsTools {

	//Attributes
	private Builder builder;
	private GuardDictionary guardDictionary;
	private TransitionSystemDictionary tsDictionary;
	
	//Constructor
	public TutsTools () {
		builder = Builder.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		tsDictionary = TransitionSystemDictionary.getInstance();
	}
	
	//Methods
	public void tutsReset () {
		builder.reset();
		guardDictionary.reset();
		tsDictionary.reset();
	}

}
