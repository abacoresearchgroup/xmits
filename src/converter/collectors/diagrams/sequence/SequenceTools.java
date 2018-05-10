package converter.collectors.diagrams.sequence;

import java.util.List;

import reader.list.Attribute;
import reader.list.Node;

public class SequenceTools {

//--Get Begin and Get End Methods----------------------------------------------------------------------------------------------------------------------------------//
	public String getStartId (List<Node> xmi) {
		String startId = "";
		for (Node node : xmi) {
			if (getNodeName(node).equals("packagedElement")) {
				startId = getIdFromAttributes(node.getContent().getAttributes());
				break;
			}
		}
		return startId;
	}
	
	public String getFinalId(List<Node> xmi) {
		String finalId = "";
		for (Node node : xmi) {
			if (getNodeName(node).equals("uml:Model")) {
				finalId = getIdFromAttributes(node.getContent().getAttributes());
				break;
			}
		}
		return finalId;
	}

//--Get Methods-------------------------------------------------------------------------------------------------------------------------------------------------//
	public String getNodeName (Node node) {
		String name = "";
		if (node.getContent().getName() != null) {
			name = node.getContent().getName();
		}
		return name;
	}
	
	public String getFragmentType (Node node) {
		String type = "";
		for (Attribute attribute : node.getContent().getAttributes()) {
			if (attribute.getName().equals("xmi:type")) {
				type = attribute.getValue();
				break;
			}
		}
		return type;
	}
	
	public String getMessageId (Node node) {
		String messageId = "";
		for (Attribute attribute : node.getContent().getAttributes()) {
			if (attribute.getName().equals("message")) {
				messageId = attribute.getValue();
				break;
			}
		}
		return messageId;
	}
	
	public String getFragmentId(Node node) {
		return getIdFromAttributes(node.getContent().getAttributes());
	}
	
	public String getFirstOperandId(int iterator, List<Node> xmi) {
		String id = "";
		for (int i = iterator; i < xmi.size(); i++) {
			if (getNodeName(xmi.get(i)).equals("operand")) {
				id = getIdFromAttributes(xmi.get(i).getContent().getAttributes());
				break;
			}
		}
		return id;
	}
	
	public String getInteractionOperator(Node node) {
		String interactionOperator = "";
		for (Attribute attribute : node.getContent().getAttributes()) {
			if (attribute.getName().equals("interactionOperator")) {
				interactionOperator = attribute.getValue();
				break;
			}
		}
		return interactionOperator;
	}
	
	public String getGuardValue(Node node) {
		String guard = "";
		for (Attribute attribute : node.getContent().getAttributes()) {
			if (attribute.getName().equals("value")) {
				guard = attribute.getValue();
				break;
			}
		}
		return guard;
	}

//--Get Next Methods-------------------------------------------------------------------------------------------------------------------------------------------------//
	public String getNextFragmentId(List<Node> xmi, int iterator) {
		String nextFragmentId = "";
		for (int i = iterator; i < xmi.size(); i++) {
			if (isFragment(xmi.get(i))) {
				nextFragmentId = getFragmentId(xmi.get(i));
				break;
			}
		}
		return nextFragmentId;
	}
	
	public String getNextMessageId(List<Node> xmi, int iterator) {
		String nextMessageId = "";
		for (int i = iterator; i < xmi.size(); i++) {
			if (isMessage(xmi.get(i))) {
				nextMessageId = getMessageFromAttributes(xmi.get(i).getContent().getAttributes());
				break;
			}
		}
		return nextMessageId;
	}

	//--Boolean Methods----------------------------------------------------------------------------------------------------------------------------------------------------//
	public boolean isClose(Node node) {
		return node.getContent().getClose();
	}
	
	public boolean isCombinedFragment(Node node) {
		if (getNodeName(node).equals("fragment")) {
			if (getFragmentType(node).equals("uml:CombinedFragment")) {
				return true;
			}
		}
		return false;
	}

	public boolean isSpecification(Node node) {
		if (getNodeName(node).equals("specification")) {
			return true;
		}
		return false;
	}
	
	public boolean isFragment(Node node) {
		if(getNodeName(node).equals("fragment")) {
			return true;
		}
		return false;
	}
	
	public boolean isMessage (Node node) {
		if (isFragment(node)) {
			if (getFragmentType(node).equals("uml:MessageOccurrenceSpecification")) {
				return true;
			}
		}
		return false;
	}
	
//--Private Methods-----------------------------------------------------------------------------------------------------------------------------------------------//
	private String getIdFromAttributes (List<Attribute> attributes) {
		String id = "";
		for (Attribute attribute : attributes) {
			if (attribute.getName().equals("xmi:id")) {
				id = attribute.getValue();
				break;
			}
		}
		return id;
	}

	private String getMessageFromAttributes(List<Attribute> attributes) {
		String message = "";
		for (Attribute attribute : attributes) {
			if (attribute.getName().equals("message")) {
				message = attribute.getValue();
				break;
			}
		}
		return message;
	}
	
}
