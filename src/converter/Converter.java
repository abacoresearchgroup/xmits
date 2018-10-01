package converter;

import converter.collectors.DiagramHandler;
import converter.logics.Core;
import converter.tools.ConverterTools;
import global.exceptions.converter.InvalidXMIException;
import reader.Reader;
import global.logics.Builder;
import global.structure.TransitionSystem;

public class Converter {
	
	/*Converter 6.0.6 Version notes:
	 * Improvements:
	 * Almost full support for all the three diagrams: Sequence, Activity and SMD.
	 * Uses a unified logic for all diagrams.
	 * 
	 * Future works:
	 * Create a unified guard collector
	 * implements decision inside parallels
	 */

	//Attributes
	private Builder builder;
	private Reader reader;
	private Core core;
	private DiagramHandler diagramHandler;
	private ConverterTools converterTools;
	
	//Constructor
	public Converter () {
		builder = Builder.getInstance();
		reader = new Reader();
		core = Core.getInstance();
		diagramHandler = new DiagramHandler();
		converterTools = new ConverterTools();
	}
	
	//Methods
	public void run (String xml) {
		converterTools.reset();
		try {
			reader.read(xml);
			diagramHandler.process(reader.getOutput());
			core.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
        public void prepareToRun(String xml) throws InvalidXMIException, Exception{
            converterTools.reset();
            reader.read(xml);
            diagramHandler.process(reader.getOutput());
            
        }
	
	public TransitionSystem getOutput () {
		return builder.getRoot();
	}
	
	public void reset () {
		converterTools.reset();
	}
	
}
