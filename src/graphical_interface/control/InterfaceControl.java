package graphical_interface.control;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

/**
 * This class is responsible for control and manipulation of the GUI (graphical user interface)
 * 
 * @author Albino Freitas
 */
public class InterfaceControl implements Initializable{

    //FXML's components --------------------------------------------------------
    @FXML
    private Button butttonAddDiagram; //This button call the method "buttonAddDigram"

    @FXML
    private ListView<String> listViewFilePaths; //This component is used to show the files that were selected 

    @FXML
    private Button buttonDel; //Button for delete the diagram that is selected

    @FXML
    private CheckBox checkBoxHasSeqDiagram; //This checkbox is used to define whether has sequence diagram

    @FXML
    private CheckBox checkBoxCreateFiles; //This checkbox is used to define whether the user would like create the output files

    @FXML
    private Button buttonRun; //This button is used to run the application
    //--------------------------------------------------------------------------
    
    //Global Variables ---------------------------------------------------------
    //This variable define whether the output files will be created
    private final boolean createOutputFiles  = false;
    //This variable define whether exists sequence diagram in the list
    private final boolean hasSequenceDiagram = false;
    //This variable saves the names of the diagrams in a list
    private final ObservableList<String> digramsList         = FXCollections.observableArrayList();
    //this variable saves the directories of the diagrams in a list
    private final ObservableList<String> directoriesDiagrams = FXCollections.observableArrayList();
    private String saveOutputPath; // Guarda o caminho de saída
    private File   saveInitialPath;  //Guarda o diretório
    //--------------------------------------------------------------------------
    
    //Methods FXML
    /**
     *  This method is called when the user click to the button "Adicionar Diagramas"
     */
    @FXML
    private void buttonAddDiagram(){
        if(hasSequenceDiagram){
            //seleção multipla de diagramas
        }else{
            //seleção unica de diagrama
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewFilePaths.setItems(digramsList);
    }
    
}
