package tuts.tools;

import tuts.dictionaries.TransitionSystemDictionary;
import global.exceptions.tuts.NoSequenceDiagramException;
import global.structure.Diagram;
import global.structure.TransitionSystem;

public class TransitionSystemTools {

    //Attributes
    private TransitionSystemDictionary tsDictionary;

    //Constructor
    public TransitionSystemTools() {
        tsDictionary = TransitionSystemDictionary.getInstance();
    }

//--Public Methods----------------------------------------------------------------------------------------------------------------------------------------------//
    public TransitionSystem copyTransitionSystemTree(TransitionSystem transitionSystem) {
        if (transitionSystem.getNext().size() == 0) {
            TransitionSystem copy = transitionSystem.getClone();
            return copy;
        } else {
            TransitionSystem copy = transitionSystem.getClone();
            copy.getNext().clear();
            for (TransitionSystem next : transitionSystem.getNext()) {
                copy.addNext(copyTransitionSystemTree(next));
            }
            return copy;
        }
    }

    public void validateInput() throws NoSequenceDiagramException {
        if (!isThereAnySequenceDiagram()) {
            throw new NoSequenceDiagramException();
        }
    }

    public boolean isThereAnySequenceDiagram() {
        for (TransitionSystem root : tsDictionary.getTsList()) {
            if (root.getDiagram().equals(Diagram.Sequence)) {
                return true;
            }
        }
        return false;
    }
    
    public TransitionSystemDictionary getTSDictionary(){
        return this.tsDictionary;
    }

}
