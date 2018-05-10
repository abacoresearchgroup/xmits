package global.structure;

import java.util.LinkedList;
import java.util.List;

public class TransitionSystem {

    //Attributes
    private State state;
    private Diagram diagram;
    private List<TransitionSystem> next;

    //Constructor
    public TransitionSystem() {
        next = new LinkedList<TransitionSystem>();
    }

    //Methods
    public void addState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public Diagram getDiagram() {
        return this.diagram;
    }

    public void addNext(TransitionSystem next) {
        this.next.add(next);
    }

    public List<TransitionSystem> getNext() {
        return this.next;
    }

    public TransitionSystem getClone() {
        TransitionSystem clone = new TransitionSystem();
        State cloneState = this.state.getClone();
        clone.addState(cloneState);
        clone.setDiagram(this.diagram);
        for (TransitionSystem n : next) {
            clone.addNext(n);
        }
        return clone;
    }

}
