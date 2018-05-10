package converter.collectors.diagrams.sequence;

import java.util.List;

import reader.list.Attribute;
import reader.list.Node;

public class MessageCollector {
	
	//Attributes
	private MessageDictionary messageDictionary;
	
	//Constructor
	public MessageCollector () {
		messageDictionary = MessageDictionary.getInstance();
	}

	//Public Methods
	public void collect (List<Node> xmi) {
		for (Node node : xmi) {
			if (node.getContent().getName().equals("message")) {
				addToMessageDictionary(node);
			}
		}
	}
	
	//Private Methods
	public void addToMessageDictionary (Node node) {
		String id = "";
		String message = "";
		for (Attribute attribute : node.getContent().getAttributes()) {
			switch (attribute.getName()) {
			case "xmi:id":
				id = attribute.getValue();
				break;
			case "name":
				message = attribute.getValue();
			}
		}
		messageDictionary.addMessage(id, message);
	}
	
}
