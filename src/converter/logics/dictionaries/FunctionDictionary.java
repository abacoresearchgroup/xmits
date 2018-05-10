package converter.logics.dictionaries;

import java.util.HashMap;

import converter.logics.facade.FunctionFacade;
import converter.logics.interfaces.Function;
import converter.logics.structures.Type;

public class FunctionDictionary {

	//Attributes
	private static FunctionDictionary uniqueInstance;
	private static FunctionFacade functionFacade;
	private HashMap<Type, Function> functions;
	
	//Constructor
	private FunctionDictionary	() {
		functions = new HashMap<Type, Function>();
		functionFacade = new FunctionFacade();
	}
	
	//Static Methods
	public static FunctionDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new FunctionDictionary();
			functionFacade.init(uniqueInstance);
		}
		return uniqueInstance;
	}
	
	//Public Methods
	public void addFunction (Type type, Function function) {
		functions.put(type, function);
	}
	
	public Function getFunction (Type type) {
		Function function = functions.get(type);
		if (function == null) {
			function = functions.get(Type.Default);
		}
		return function;
	}
	
}
