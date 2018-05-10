package tuts;

import bridge.Formatter;
import bridge.Tools;
import global.exceptions.tuts.NoSequenceDiagramException;
import global.logics.Builder;
import global.structure.TransitionSystem;

import java.util.ArrayList;
import java.util.List;

import tuts.dictionaries.TransitionSystemDictionary;
import tuts.logics.Core;
import tuts.tools.TransitionSystemTools;
import tuts.tools.TutsTools;
import converter.Converter;
import global.tools.Printer;
import java.io.File;
import javax.swing.JOptionPane;

public class Tuts {

    //Attributes
    private List<String> files;
    private TransitionSystemDictionary tsDictionary;
    private Core core;
    private Builder builder;
    private Converter converter;
    private TutsTools tutsTools;
    private TransitionSystemTools tsTools;
    private Tools tools;
    private Formatter formatter;

    //Constructor
    public Tuts() {
        files = new ArrayList<String>();
        tsDictionary = TransitionSystemDictionary.getInstance();
        core = new Core();
        builder = Builder.getInstance();
        converter = new Converter();
        tutsTools = new TutsTools();
        tsTools = new TransitionSystemTools();
        formatter = new Formatter();
        tools = new Tools();
    }

    //Gets
    public TransitionSystemTools getTsTools() {
        return this.tsTools;
    }

    public boolean getHasSequenceDiagram() {
        return tsTools.isThereAnySequenceDiagram();
    }

    //Methods
    public void addFile(String file) {
        files.add(file);
    }

    public void run() {
        tutsTools.tutsReset();
        for (String file : files) {
            converter.run(file);
            tsDictionary.addTransitionSystem(converter.getOutput());
        }
        try {
            System.out.println(tsTools.isThereAnySequenceDiagram());
            tsTools.validateInput();
            core.begin();
        } catch (NoSequenceDiagramException e) {
            e.printStackTrace();
        }
    }

    public void prepareToRun() {
        tutsTools.tutsReset();
        for (String file : files) {
            converter.run(file);
            tsDictionary.addTransitionSystem(converter.getOutput());
        }
    }

    public void prepareToRunError(String erro) {
        tutsTools.tutsReset();
        String result = "";
        for (String file : files) {
            converter.run(file);
            Printer print = new Printer();

            TransitionSystem ts = converter.getOutput();

            String x = print.getTS(ts);
            
            String teste = formatter.reformat(erro);
            String[] array = teste.split(",");
            
            for (int i = 0; i < array.length; i++) {
                if (x.toLowerCase().contains(array[i].toLowerCase())) {

                    File name = new File(file);
                    result += "NOME DO DIAGRAMA: "+name.getName() + "\n";
                    result += "NOME DO ESTADO: " + array[i] + "\n";
                    result += format(ts, array[i]) +"º estado"+"\n \n";
                }
            }
            
            tsDictionary.addTransitionSystem(converter.getOutput());
        }
        System.out.print(tsDictionary);
        
        if(!result.isEmpty()){
            System.out.println(result);
            JOptionPane.showMessageDialog(null, result);
        }

    }

    public int format(TransitionSystem root, String erro) {
        int i = 0;
        for (TransitionSystem state : tools.getAllStates(root)) {
            if (state.getState().getMessage().getContent().toLowerCase().contains(erro.toLowerCase())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void run2() {
        core.begin();
    }

    public TransitionSystem getOutput() {
        return builder.getRoot();
    }

    public void reset() {
        files = new ArrayList<String>();
        tutsTools.tutsReset();
    }

}
