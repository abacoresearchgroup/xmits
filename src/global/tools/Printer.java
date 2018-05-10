package global.tools;


import global.structure.Guard;
import global.structure.TransitionSystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Printer {

//--Public Methods-------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public void print (TransitionSystem list) {
		System.out.println (createContent(list));
	}
        
        public String getTS(TransitionSystem list){
            return createContent(list);
        }


	public void txtPrint (TransitionSystem list, String outputFileName) {
		
		String content = createContent(list);
		content = content.replaceAll("\n", "\r\n");
		try {

			File file = new File(outputFileName + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.close();

		}catch (Exception e) {
			System.out.println ("Printer Error: Output file coudn't be created!");
			e.printStackTrace();
		}
		
	}

//--Private Methods-----------------------------------------------------------------------------------------------------------------------------------------------//
	private String createContent (TransitionSystem list) {

		String content = "";

		List<TransitionSystem> output = new ArrayList<TransitionSystem>();
		output.add(list);
		int index = 0;
		content = content + "Node Children\n";
		content = content + "---------------------------------------------------------\n";
		content = content + "{";
		while (true) {
			if (index != 0) content = content + list.getNext().size();
			for (TransitionSystem child : list.getNext()) {
				output.add(child);
			}
			try {
				list = output.get(index + 1);
				if (index != 0) content = content + ", ";
				index = index + 1;
			}catch (Exception e) {
				break;
			}
		}
		content = content + "}\n";
		content = content + "---------------------------------------------------------\n";
		content = content + "Transition System Output\n";
		content = content + "---------------------------------------------------------\n";

		int count = 0;

		for (TransitionSystem element : output) {
			if (count > 0) {
				try {
					content = content + "Message: " + element.getState().getMessage().getContent() + "\n";
					for (Guard guard : element.getState().getGuards()) {
						content = content + "Guard: " + guard.getName() + " = " + guard.getValue() + "\n";
					}
					content = content + "---------------------------------------------------------\n";
					count ++;
				}catch (Exception e) {
					System.out.println("Printer Error: Null Pointer");
				}
			}else{
				count ++;
			}
		}

		content = content + "NUMBER OF STATES: " + (count - 1) + "\n";

		return content;
		
	}
        
//--Private Methods-----------------------------------------------------------------------------------------------------------------------------------------------//
	public String createMensage (TransitionSystem list) {

		String content = "";

		List<TransitionSystem> output = new ArrayList<TransitionSystem>();
		output.add(list);
		int index = 0;
		
		while (true) {
			if (index != 0) content = content + list.getNext().size();
			for (TransitionSystem child : list.getNext()) {
				output.add(child);
			}
			try {
				list = output.get(index + 1);
				if (index != 0) content = content + ", ";
				index = index + 1;
			}catch (Exception e) {
				break;
			}
		}
		int count = 0;
                content = "";

		for (TransitionSystem element : output) {
			if (count > 0) {
				try {
					content = content + element.getState().getMessage().getContent()+ " ";
					count ++;
				}catch (Exception e) {
					System.out.println("Printer Error: Null Pointer");
				}
			}else{
				count ++;
			}
		}


		return content.replaceAll("()", "");
		
	}
}
