package gui_interface.controllers;

import gui_interface.beans.ManagerBean;
import gui_interface.interfaces.ShowTS;
import java.io.File;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author Albino Freitas
 */
public class Controller{
    //inicio dos componentes FXML ----------------------------------------------
    @FXML
    private Button addDigrams; //Botão Para Adicionar Diagramas
    
    @FXML
    private Button openModelio; // Botão para chamada do modelio

    @FXML
    private ListView<String> listView; // exibir ao usuários os arquivos selecionados

    @FXML
    private Button deleteDiagram; // Botão para apagar diagramas da lista

    @FXML
    private Button execute; // Botão de Execução

    @FXML
    private TextField error;
    private TextField propriedade;
    //fim dos componentes FXML--------------------------------------------------

    ManagerBean manager;
    private File initialDirectory;  //Guarda o diretório do filechooser

    public Controller() {
        this.listView = new ListView<>();
        manager = new ManagerBean();
    }
    
    @FXML
    private void openModelio(){
        System.out.println("hello");
    }
    
    @FXML
    //Adicionar novos diagramas à lista de execução
    private void addDigrams() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Selecionar Diagramas");
        if(initialDirectory != null && initialDirectory.exists()){
            fileChooser.setInitialDirectory(initialDirectory);
        }
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Arquivos (*.XML), (*.XMI) e (*.UML)", "*.xml", "*.xmi", "*.uml")
        );
                
        processData(fileChooser.showOpenMultipleDialog(null));
    }

    @FXML
    //Deletar diagramas da lista de execução usando botão del
    private void delPress(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            deleteDigram();
        }
    }

    @FXML
    //Deletar diagramas da lista de execução usando botão da interface
    public void deleteDigram() {
        int selectedDiagram = listView.getSelectionModel().getSelectedIndex();
        
        if(selectedDiagram >= 0){
            manager.removeDigram(selectedDiagram);
            listView.getItems().remove(selectedDiagram);
        }else{
            System.out.println("Nenhum diagrama selecionado!");
        }    
    }

    @FXML
    private void execute() {
        if(manager.prepareToRun()){
            if(manager.run()){
                showTS(manager.getStringTS());
            }
        }
    }    

    private void processData(List<File> list) {
        if(list != null){
            list.stream().forEach((file) -> {
                if (manager.getDiagrams().contains(file) || listView.getItems().contains(file.getName())) {
                    System.out.println("Diagrama já adicionado");
                }else{
                    if(manager.isDiagram(file)){
                        listView.getItems().add(file.getName());
                        manager.addOneDiagram(file);
                    }else{
                        System.out.println("Diagrama Inválido");
                    }
                }
            });
            this.initialDirectory = list.get(0).getParentFile();
        }        
    }
    
    public void showTS(String stringTS) {
        ShowTS frame = new ShowTS();
        frame.setTitle("Transition System Output");
        frame.setText(stringTS);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
