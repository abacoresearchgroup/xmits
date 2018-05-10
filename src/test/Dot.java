package test;

import global.structure.TransitionSystem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Dot {

    public void print(TransitionSystem list) {
        System.out.println(createDot(list));
    }

    public String getTS(TransitionSystem list) {
        return createDot(list);
    }

    public void saveFile(String content, String outputFileName) {
        try {

            content = content.replaceAll("\n", "\r\n");
            File file = new File(outputFileName + "_dot.dot");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(content);
            }

            System.out.println(outputFileName + ".dot created successfully");

        } catch (Exception e) {
            System.out.println("Printer Error: Output file coudn't be created!");
        }
    }

    private String createDot(TransitionSystem list) {

        String content = "digraph GraphName{" + "\n";

        String pai = "";

        List<TransitionSystem> output = new ArrayList<>();
        output.add(list);
        int index = 0;

        while (true) {
            if (index != 0) {
                pai = "    " + index; //list.getState().getMessage().getContent();
            }
            for (TransitionSystem child : list.getNext()) {
                output.add(child);
                if (index != 0) {
                    content += pai + " -> " + (output.size() - 1) + ";\n";//child.getState().getMessage().getContent() + "\n";
                }
            }
            try {
                list = output.get(index + 1);
                index = index + 1;
            } catch (Exception e) {
                break;
            }
        }

        content += "\n\n";
        index = 0;

        for (TransitionSystem node : output) {
            if (index != 0) {
                content += "    " + index + " [label=\""
                        + node.getState().getMessage().getContent()
                        + "\"];"
                        + "\n";
            }
            index++;
        }

        content += "}";
        return content;

    }

}
