package reader;

import global.exceptions.reader.ReaderException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import reader.list.Node;
import reader.logics.Parser;

public class Reader {

	//Attributes
	private Parser parser;
	
	//Constructor
	public Reader () {
		parser = new Parser();
	}
	
	//Public Methods
	public void read (String xml) throws ReaderException {
		reset();
		try {
			parser.parse(xml);
		}catch (IOException exception) {
			throw new ReaderException("Error during read the file!", exception);
		}catch (ParserConfigurationException exception) {
			throw new ReaderException("Error during configure the parser!", exception);
		}catch (SAXException exception) {
			throw new ReaderException("Error during parsing the file!", exception);
		}
	}

	public List<Node> getOutput() {
		return parser.getList();
	}
	
	//Private Methods
	private void reset () {
		parser = new Parser();
	}

}
