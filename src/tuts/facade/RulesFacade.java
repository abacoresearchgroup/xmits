package tuts.facade;

import tuts.dictionaries.RulesDictionary;
import tuts.interfaces.Rule;
import tuts.logics.rules.FinalRule;
import tuts.logics.rules.FirstRule;
import tuts.logics.rules.SecondRule;
import tuts.logics.rules.ThirdRule;

public class RulesFacade {
	
	//Methods
	public void init (RulesDictionary rulesDictionary) {
		
		Rule firstRule = new FirstRule();
		rulesDictionary.addRule(firstRule);
		
		Rule secondRule = new SecondRule();
		rulesDictionary.addRule(secondRule);
		
		Rule thirdRule = new ThirdRule();
		rulesDictionary.addRule(thirdRule);
		
		Rule finalRule = new FinalRule();
		rulesDictionary.addRule(finalRule);
	}

}
