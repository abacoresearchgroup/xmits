package bridge;

import global.structure.Guard;
import global.structure.TransitionSystem;
import java.util.regex.Pattern;

public class Formatter {

    //Attributes
    private Tools tools;

    //Constructor
    public Formatter() {
        tools = new Tools();
    }

    //Public Methods
    public void format(TransitionSystem root) {
        System.out.println(tools.getAllStates(root).size());
        for (TransitionSystem state : tools.getAllStates(root)) {
            System.out.println(state.getState().getMessage().getContent());
            String content = state.getState().getMessage().getContent();
            content = format(content);
            state.getState().getMessage().setContent(content);
            formatGuards(state);
        }
    }

    //Private Methods
    private void formatGuards(TransitionSystem state) {
        for (Guard guard : state.getState().getGuards()) {
            String guardName = guard.getName();
            guardName = format(guardName);
            guard.setName(guardName);
        }
    }

    private String format (String message) {
		String output = "_";
		for (char character : message.toLowerCase().toCharArray()) {
			switch (character) {
//Values-------------------------------------------------------------------------------------------------------------------------------------------------------------
			case 'á':
				output = output + "a";
				break;
			case 'à':
				output = output + "a";
				break;
			case 'ä':
				output = output + "a";
				break;
			case 'â':
				output = output + "a";
				break;
			case 'ã':
				output = output + "a";
				break;
			case 'é':
				output = output + "e";
				break;
			case 'è':
				output = output + "e";
				break;
			case 'ë':
				output = output + "e";
				break;
			case 'ê':
				output = output + "e";
				break;
			case 'ẽ':
				output = output + "e";
				break;
			case 'í':
				output = output + "i";
				break;
			case 'ì':
				output = output + "i";
				break;
			case 'ï':
				output = output + "i";
				break;
			case 'î':
				output = output + "i";
				break;
			case 'ĩ':
				output = output + "i";
				break;
			case 'ó':
				output = output + "o";
				break;
			case 'ò':
				output = output + "o";
				break;
			case 'ö':
				output = output + "o";
				break;
			case 'ô':
				output = output + "o";
				break;
			case 'õ':
				output = output + "o";
				break;
			case 'ú':
				output = output + "u";
				break;
			case 'ù':
				output = output + "u";
				break;
			case 'ü':
				output = output + "u";
				break;
			case 'û':
				output = output + "u";
				break;
			case 'ũ':
				output = output + "u";
				break;
			case 'ý':
				output = output + "y";
				break;
			case 'ỳ':
				output = output + "y";
				break;
			case 'ÿ':
				output = output + "y";
				break;
			case 'ŷ':
				output = output + "y";
				break;
			case 'ỹ':
				output = output + "y";
				break;
//Consonants-------------------------------------------------------------------------------------------------------------------------------------------------------------
			case 'ç':
				output = output + "c";
				break;
			case 'ñ':
				output = output + "n";
				break;
//Special Characters-------------------------------------------------------------------------------------------------------------------------------------------------------------
			case '(':
				output = output + "$_$";
				break;
			case ')':
				output = output + "$_$";
				break;
			case '[':
				output = output + "#";
				break;
			case ']':
				output = output + "#";
				break;
			case ',':
				output = output + "$coma$";
				break;
			case ':':
				output = output + "$dots$";
				break;
			case '+':
				output = output + "$plus$";
				break;
			case '-':
				output = output + "$minus$";
				break;
			case '*':
				output = output + "$asterisk$";
				break;
			case '/':
				output = output + "$slash$";
				break;
			case '%':
				output = output + "$percent$";
				break;
			case '<':
				output = output + "$lessthen$";
				break;
			case '>':
				output = output + "$greaterthen$";
				break;
			case '=':
				output = output + "$equals$";
				break;
			case '&':
				output = output + "$and$";
				break;
			case '!':
				output = output + "$not$";
				break;
			case ' ':
				output = output + "__";
				break;
			default:
				output = output + character;
			}
		}
		return output;
	}


    public String reformat(String message) {
        message = message.replaceAll(Pattern.quote("$coma$"), ",");
        message = message.replaceAll(Pattern.quote("__"), " ");
        message = message.replaceAll(Pattern.quote("_$"), "");
        message = message.replaceAll(Pattern.quote("$plus$"), "+");
        message = message.replaceAll(Pattern.quote("$minus$"), "-");
        message = message.replaceAll(Pattern.quote("$asterisk$"), "*");
        message = message.replaceAll(Pattern.quote("$percent$"), "%");
        message = message.replaceAll(Pattern.quote("$lessthen$"), "<");
        message = message.replaceAll(Pattern.quote("$greaterthen$"), ">");
        message = message.replaceAll(Pattern.quote("$equals$"), "=");
        message = message.replaceAll(Pattern.quote("$and$"), "&");
        message = message.replaceAll(Pattern.quote("$slash$"), "/");    
        message = message.replaceAll(Pattern.quote("$not$"), "!");
        message = message.replaceAll(Pattern.quote("$dots$"), ":");
        message = message.replaceAll(Pattern.quote(", "), ",");
        message = message.replaceAll(Pattern.quote(",-"), "");
        message = message.replaceAll(Pattern.quote("$"), "");

        return message;
    }

}
