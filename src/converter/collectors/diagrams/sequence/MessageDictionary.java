package converter.collectors.diagrams.sequence;

import java.util.Hashtable;

public class MessageDictionary {

	//Attributes
	private static MessageDictionary uniqueInstance;
	private Hashtable<String, String> messages;
	
	//Constructor
	public MessageDictionary () {
		messages = new Hashtable<String, String>();
	}

	//Static Methods
	public static MessageDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new MessageDictionary();
		}
		return uniqueInstance;
	}
	
	//Public Methods
	public void addMessage (String id, String message) {
		messages.put(id, message);
	}
	
	public String getMessageById (String id) {
		return messages.get(id);
	}
	
	public void reset () {
		messages.clear();
	}
	
}
