package converter.collectors.diagrams.smd;

import java.util.ArrayList;
import java.util.List;

import reader.list.Attribute;
import reader.list.Node;

public class SmdTools {

//--Get Methods--------------------------------------------------------------------------------------------------------------------------------------------------//
	public String getAttributeValue (Node node, String attributeName) {
		String value = "";
		for (Attribute attribute : getAttributes(node)) {
			if (attribute.getName().equals(attributeName)) {
				value = attribute.getValue();
			}
		}
		return value;
	}

//--Boolean Methods----------------------------------------------------------------------------------------------------------------------------------------------//
	public boolean nodeNameEquals (Node node, String name) {
		if (getNodeName(node).equals(name)) {
			return true;
		}
		return false;
	}
	
	public boolean isTransition(Node node) {
		if (getNodeName(node).equals("transition")) {
			if (!node.getContent().getAttributes().isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
//--Private Methods----------------------------------------------------------------------------------------------------------------------------------------------//
	private String getNodeName (Node node) {
		String name = "";
		if (node.getContent().getName() != null) {
			name = node.getContent().getName();
		}
		return name;
	}
	
	private List<Attribute> getAttributes (Node node) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		if (!node.getContent().getAttributes().isEmpty()) {
			attributes = node.getContent().getAttributes();
		}
		return attributes;
	}
	
}
