package converter.collectors.guards.structures;

import java.util.ArrayList;
import java.util.List;

public class Expression {
	
	//Attributes
	private List<Term> terms;
	
	//Constructor
	public Expression () {
		terms = new ArrayList<Term>();
	}
	
	//Methods
	public void addOperator (char character) {
		if (terms.size() == 0 || getLastTerm().getNature().equals(TermNature.expression)) {
			Term operator = new Term();
			operator.setNature(TermNature.operator);
			operator.addCharacter(character);
			terms.add(operator);
		}else{
			getLastTerm().addCharacter(character);
		}
	}
	
	public void addExpression (char character) {
		if (terms.size() == 0 || getLastTerm().getNature().equals(TermNature.operator)) {
			Term expression = new Term();
			expression.setNature(TermNature.expression);
			expression.addCharacter(character);
			terms.add(expression);
		}else{
			getLastTerm().addCharacter(character);
		}
	}
	
	public List<Term> getTerms () {
		return terms;
	}
	
	public Term getFirstTerm () {
		return terms.get(0);
	}
	
	public Term getLastTerm () {
		return terms.get(terms.size() - 1);
	}
	
}
