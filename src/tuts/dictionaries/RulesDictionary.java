package tuts.dictionaries;

import java.util.ArrayList;
import java.util.List;

import tuts.facade.RulesFacade;
import tuts.interfaces.Rule;

public class RulesDictionary {

	//Attributes
	private static RulesDictionary uniqueInstance;
	private List<Rule> rules;
	private static RulesFacade rulesFacade;
	
	//Constructor
	private RulesDictionary () {
		rules = new ArrayList<Rule>();
		rulesFacade = new RulesFacade();
	}
	
	//Static Methods
	public static RulesDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new RulesDictionary();
			rulesFacade.init(uniqueInstance);
		}
		return uniqueInstance;
	}
	
	//Public Methods
	public void addRule (Rule rule) {
		rules.add(rule);
	}
	
	public List<Rule> getRules () {
		return rules;
	}
	
}
