package gui_interface.beans;

import converter.Converter;
import global.structure.TransitionSystem;
import global.tools.Printer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import tuts.Tuts;

/**
 *
 * @author Albino Freitas
 */
public class ManagerBean {
    
    private final List<File> diagrams;
    private TransitionSystem ts;
    private String stringTS;
    private Tuts tuts;
    Printer print;
    private String errorMessage;

    public ManagerBean() {
        this.diagrams = new ArrayList<>();
        this.tuts = new Tuts();
        this.print = new Printer();
    }

    public List<File> getDiagrams() {
        return diagrams;
    }

    public void setDiagrams(List<File> diagrams) {
        this.diagrams.addAll(diagrams);
    }
    
    public void addOneDiagram(File file){
        this.diagrams.add(file);
    }
    
    public void removeDigram(int i){
        this.diagrams.remove(i);
    }
    
    public boolean isDiagram(File file){
        try{
            Converter converter = new Converter();
            converter.prepareToRun(file.getAbsolutePath());
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public TransitionSystem getTs() {
        return ts;
    }

    public void setTs(TransitionSystem ts) {
        this.ts = ts;
    }

    public String getStringTS() {
        return stringTS;
    }

    public void setStringTS(String stringTS) {
        this.stringTS = stringTS;
    }

    public Tuts getTuts() {
        return tuts;
    }

    public void setTuts(Tuts tuts) {
        this.tuts = tuts;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public boolean prepareToRun(){
        boolean canRun = true;
        
        if(this.diagrams.isEmpty()){
            this.errorMessage = "Nenhum diagrama adicionado";
            canRun = false;
        }else{
            this.diagrams.forEach(file -> {
                this.tuts.addFile(file.getAbsolutePath());
            });
            this.tuts.prepareToRun();
        
            if(this.diagrams.size() > 1){
                if(!tuts.getHasSequenceDiagram()){
                   canRun = false;
                }
            }
        }
        
        return canRun;
    }
    
    public boolean run () {
        try{
            tuts.run2();
            this.ts = tuts.getOutput();

            this.stringTS = this.print.getTS(ts);
            return true;
        }catch(Exception e){
            this.errorMessage = e.getMessage();
            return false;
        }
    }
}
