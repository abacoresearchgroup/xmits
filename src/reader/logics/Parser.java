package reader.logics;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import reader.list.Attribute;
import reader.list.Body;
import reader.list.Content;
import reader.list.Node;
import reader.list.Tag;

public class Parser extends DefaultHandler{
	
	//Attributes
	private List<Node> list;
	private int level;
	
	
	//Constructor
	public Parser () {
		list = new LinkedList<Node>();
	}
	
	//Methods
	
	//Parser
	public void parse (String xml) throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(xml, this);
	}
	
	//SAX methods
	public void startDocument () {
		level = 0;
	}
	
	public void endDocument () {
		//Empty
	}
	
	public void startElement (String uri, String localName, String qName, Attributes attributes) {
		Node node = new Node();
		Content tag = new Tag();
		tag.setName(qName);
		for (int i = 0; i < attributes.getLength(); i ++) {
			Attribute attribute = new Attribute();
			attribute.setName(attributes.getQName(i));
			attribute.setValue(attributes.getValue(i));
			tag.addAttribute(attribute);
		}
		node.setContent(tag);
		node.setLevel(level);
		level = level + 1;
		list.add(node);
	}
	
	public void endElement (String uri, String localName, String tag) {
		boolean newTag = false;
		if (list.get(list.size() - 1).getContent() instanceof Tag) {
			if (list.get(list.size() - 1).getContent().getName().equals(tag) && !list.get(list.size() - 1).getContent().getClose()) {
				list.get(list.size() - 1).getContent().setClose();
				level = level - 1;
			}else{
				newTag = true;
			}
		}else{
			newTag = true;
		}
		if (newTag) {
			Node node = new Node();
			Content closeTag = new Tag();
			closeTag.setName(tag);
			closeTag.setClose();
			node.setContent(closeTag);
			level = level - 1;
			node.setLevel(level);
			list.add(node);
		}
	}
	
	public void characters (char[] ch, int start, int length) {
		if (!String.copyValueOf(ch, start, length).trim().equals("")) {
			Node node = new Node();
			Content body = new Body();
			body.setContent(String.copyValueOf(ch, start, length).trim());
			node.setContent(body);
			node.setLevel(level);
			list.add(node);
		}
	}
	
	//List methods
	public List<Node> getList () {
		return list;
	}
	
}
