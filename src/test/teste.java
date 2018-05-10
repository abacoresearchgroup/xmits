package test;

import tuts.Tuts;

/**
 *
 * @author Albino Freitas
 */
public class teste {

    public static void main(String... args) {
        Tuts tuts = new Tuts();

        tuts.addFile("C:/Scenarios/Scenario Nine/ScenarioNineActivity.uml");
        tuts.addFile("C:/Scenarios/Scenario Nine/ScenarioNineSMD.uml");
        tuts.addFile("C:/Scenarios/Scenario Nine/ScenarioNineSequence.uml");
        
        
        tuts.prepareToRun();
        System.out.println(tuts.getHasSequenceDiagram());
        tuts.run2();
        
    }
}
