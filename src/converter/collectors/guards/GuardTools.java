package converter.collectors.guards;

public class GuardTools {
	
//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------------------//	
	public String getEqualityLeft(String statement) {
		String left = "";
		for (char character : statement.trim().toCharArray()) {
			if (character == '=') {
				break;
			}else{
				left = left + character;
			}
		}
		return left;
	}

	public String getEqualityRight(String statement) {	
		String right = "";
		boolean flag = false;
		for (char character : statement.trim().toCharArray()) {
			if (character == '=') {
				flag = true;
				continue;
			}
			if (flag){
				right = right + character;
			}
		}
		return right;	
	}
	
	public boolean evaluateTrue(String statement) {
		switch (statement.toLowerCase().trim()) {
		case "yes":
			return true;
		case "true":
			return true;
		case "valid":
			return true;
		case "positive":
			return true;
		/*case "ok": //This "ok" word will be comment to run the protoMIRAX study cases
			return true;*/
		default:
			return false;
		}

	}
	
	public boolean evaluateFalse(String statement) {
		switch (statement.toLowerCase().trim()) {
		case "no":
			return true;
		case "else":
			return true;
		case "false":
			return true;
		case "invalid":
			return true;
		case "null":
			return true;
		default:
			return false;
		}

	}

	public String formatStatement(String statement) {
		String output = "";
		for (char character : statement.toCharArray()) {
			if (character != ' ') {
				output = output + character;
			}
		}
		return output;
	}
	
}
