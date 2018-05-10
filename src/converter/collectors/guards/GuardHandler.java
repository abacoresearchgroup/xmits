package converter.collectors.guards;

import converter.collectors.guards.structures.GuardNature;
import global.exceptions.converter.InvalidGuardException;
import global.structure.Guard;
import global.structure.GuardValue;

public class GuardHandler {
	
	//Attributes
	private GuardValidator guardValidator;
	private GuardTools guardTools;
	
	//Constructor
	public GuardHandler () {
		guardValidator = new GuardValidator();
		guardTools = new GuardTools();
	}
	
//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public Guard getGuard (String statement) throws InvalidGuardException {
		statement = guardTools.formatStatement(statement);
		Guard guard = new Guard();
		GuardNature guardNature = guardValidator.validateExpression(statement);
		switch (guardNature) {
		case equality:
			guard = createEqualityGuard(statement);
			break;
		case expression:
			guard = createExpressionGuard(statement);
			break;
		default:
			throw new InvalidGuardException();
		}
		return guard;
	}

//--Private Methods--------------------------------------------------------------------------------------------------------------------------------------------------------------//
	private Guard createEqualityGuard (String statement) throws InvalidGuardException {
		String left = guardTools.getEqualityLeft(statement);
		String right = guardTools.getEqualityRight(statement);
		Guard guard = new Guard();
		
		int keyWords = 0;
		
		if (guardTools.evaluateTrue(left)) {
			guard.setValue(GuardValue.True);
			guard.setName(right);
			keyWords ++;
		}
		else if (guardTools.evaluateFalse(left)) {
			guard.setValue(GuardValue.False);
			guard.setName(right);
			keyWords ++;
		}
		if (guardTools.evaluateTrue(right)) {
			guard.setValue(GuardValue.True);
			guard.setName(left);
			keyWords ++;
		}
		else if (guardTools.evaluateFalse(right)) {
			guard.setValue(GuardValue.False);
			guard.setName(left);
			keyWords ++;
		}
		
		if (keyWords == 0) {
			guard.setName(statement);
		}
		else if (keyWords > 1){
			throw new InvalidGuardException();
		}
		
		return guard;
	}

	private Guard createExpressionGuard (String statement) {
		Guard guard = new Guard();
		
		if (guardTools.evaluateTrue(statement)) {
			guard.setValue(GuardValue.True);
		}
		else if (guardTools.evaluateFalse(statement)) {
			guard.setValue(GuardValue.False);
		}else{
			guard.setName(statement);
		}
		
		return guard;
	}
	
}
