package bridge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import global.structure.TransitionSystem;

public class Bridge {
	
	//Attributes
	private String output;
	private Writer writer;
	private Formatter formatter;

	
	//Constructor
	public Bridge () {
		output = "";
		writer = new Writer();
		formatter = new Formatter();
	}
	
	//Public Methods
	public String getInputString (TransitionSystem root) {
		return generateInputString(root);
	}
	
	public void saveInputFile (TransitionSystem root, String outputFileName) {
		
		String inputString = generateInputString(root);
                inputString = inputString.replaceAll("\n", "\r\n");
                
		try {

			File file = new File(outputFileName + ".smv");
			if (!file.exists()) {
				file.createNewFile();
                                System.out.println("teste xx");
			}

			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(inputString);
			bufferedWriter.close();

			System.out.println (outputFileName + ".smv created successfully");

		}catch (Exception e) {
			System.out.println ("Printer Error: Output file coudn't be created!");
			e.printStackTrace();
		}
	}
	
//--Private Methods-------------------------------------------------------------------------------------------------------------------------------------------//
	private String generateInputString (TransitionSystem root) {
		formatter.format(root);
		output = writer.createHeader();
		output = writer.createVar(output, root);
		output = writer.createAssign(output, root);
		output = writer.createNext(output, root);
		output = writer.createGuards(output, root);
		return output;
	}
	
}
