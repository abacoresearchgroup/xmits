package converter.collectors.guards;

import java.util.ArrayList;
import java.util.List;

import converter.collectors.guards.structures.Expression;
import converter.collectors.guards.structures.GuardNature;
import converter.collectors.guards.structures.Operator;
import converter.collectors.guards.structures.Term;
import converter.collectors.guards.structures.TermNature;

public class GuardValidator {
	
//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public GuardNature validateExpression (String statement) {
		if (statement.trim().length() > 0) {
			return getGuardNature(statement);
		}else{
			return GuardNature.invalid;
		}
	}
	
//--Private Methods--------------------------------------------------------------------------------------------------------------------------------------------------------------//
	private GuardNature getGuardNature (String statement) {
		Expression expression = getExpression (statement);
		
		if (!validateBorders(expression)) {
			return GuardNature.invalid;
		}
	
		List<Operator> operators = new ArrayList<Operator>();
		for (Term term : expression.getTerms()) {
			if (term.getNature().equals(TermNature.operator)) {
				switch (term.getTerm().length()) {
				case 1:
					operators.add(getSingleOperator(term.getTerm()));
					break;
				case 2:
					operators.add(getDoubleOperator(term.getTerm()));
					break;
				default:
					return GuardNature.invalid;
				}
			}
		}
		
		if (operators.isEmpty()) {
			return GuardNature.expression;
		}else{
			return validateOperators (operators);
		}
	}
	
	private GuardNature validateOperators (List<Operator> operators) {
		List<Operator> set = new ArrayList<Operator>();
		int junctions = 0;
		for (Operator operator : operators) {
			switch (operator) {
			case and:
				set.clear();
				junctions ++;
				break;
			case or:
				set.clear();
				junctions ++;
				break;
			case error:
				return GuardNature.invalid;
			default:
				set.add(operator);
				if (set.size() > 1) return GuardNature.invalid;
			}
		}
		if (junctions > 0) {
			return GuardNature.expression;
		}else{
			if (operators.get(0).equals(Operator.equal)) {
				return GuardNature.equality;
			}else{
				return GuardNature.expression;
			}
		}
	}

	private Operator getDoubleOperator (String term) {
		switch (term) {
		case "==":
			return Operator.equal;
		case "!=":
			return Operator.different;
		case ">=":
			return Operator.greaterOrEqual;
		case "<=":
			return Operator.lessOrEqual;
		case "&&":
			return Operator.and;
		case "||":
			return Operator.or;
		default :
			return Operator.error;
		}
	}

	private Operator getSingleOperator (String term) {
		switch (term) {
		case "=":
			return Operator.equal;
		case ">":
			return Operator.greaterThen;
		case "<":
			return Operator.lessThen;
		default :
			return Operator.error;
		}
	}

	private boolean validateBorders (Expression expression) {
		if (expression.getFirstTerm().getNature().equals(TermNature.operator)) {
			return false;
		}
		if (expression.getLastTerm().getNature().equals(TermNature.operator)) {
			return false;
		}
		return true;
	}

	private Expression getExpression (String statement) {
		Expression expression = new Expression();
		for (char character : statement.toCharArray()) {
			if ("=!¬><&|".contains(String.valueOf(character))) {
				expression.addOperator(character);
			}else{
				expression.addExpression(character);
			}
		}
		return expression;
	}

}
