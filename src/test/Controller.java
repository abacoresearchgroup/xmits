package test;

import bridge.Bridge;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import tuts.Tuts;
import global.tools.Printer;
import converter.Converter;
import data.Classify;
import global.structure.TransitionSystem;
import java.util.Date;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

    //Componentes Arquivo FXML -------------------------------------------------
    @FXML
    private Button btAddDiagram; //Botão Para Add Diagramas

    @FXML
    private ListView<String> lvFilePaths; //ListView

    @FXML
    private Button btDel; //Botão para apagar diagramas da lista

    @FXML
    private CheckBox cbHasSeqDiagram; //Checkbox para informar caso haja diagrama de sequência

    @FXML
    private CheckBox cbMakeFiles; //Checbox para verificar se deve gerar arquivos de saida

    @FXML
    private Button btRun; //Botão de Execução

    @FXML
    private TextField error;
    private TextField propriedade;
    //--------------------------------------------------------------------------

    boolean gerarArquivo = false; // Decide se vai ser ou não salvo os arquivos de saida
    private boolean sequenceDiagram = false; //Determina se existe diagrama de sequência
    final ObservableList<String> listItems = FXCollections.observableArrayList();
    final ObservableList<String> directoryFiles = FXCollections.observableArrayList();
    private String outputPath; // Guarda o caminho de saída
    private File savePath;  //Guarda o diretório

    @FXML
    private void addAction(ActionEvent action) {
        FileChooser.ExtensionFilter extension;
        extension = new FileChooser.ExtensionFilter("Arquivos (*.XML), (*.XMI) e (*.UML)", "*.xml", "*.xmi", "*.uml");

        FileChooser fcFileChooser = new FileChooser();
        fcFileChooser.getExtensionFilters().add(extension);
        fcFileChooser.setInitialDirectory(savePath);
        fcFileChooser.setTitle("Selecione os Diagramas");

        if (sequenceDiagram) {
            List<File> list = fcFileChooser.showOpenMultipleDialog(null);
            if (list != null) {
                list.stream().forEach((file) -> {
                    if (listItems.indexOf(file.getName()) < 0) {
                        listItems.add(file.getName());
                        directoryFiles.add(file.getAbsolutePath().replaceAll("\\\\", "/"));
                    } else {
                        JOptionPane.showMessageDialog(null, "O diagrama \"" + file.getName() + "\" já foi adicionado à lista");
                    }
                    savePath = file.getParentFile();
                });
            }
        } else if (directoryFiles.isEmpty()) {
            File oneFile = fcFileChooser.showOpenDialog(null);
            if (oneFile != null) {
                listItems.add(oneFile.getName());
                directoryFiles.add(oneFile.getAbsolutePath().replaceAll("\\\\", "/"));
                savePath = oneFile.getParentFile();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Para inserir mais diagramas marque a caixa de seleção (Diagrama Sequência)");
        }
    }

    @FXML
    private void delPress(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            deleteAction(new ActionEvent());
        }
    }

    @FXML
    public void deleteAction(ActionEvent action) {
        int selectedItem = lvFilePaths.getSelectionModel().getSelectedIndex();

        //Tratamento caso não haja nenhum item ná lista
        if (selectedItem >= 0) {
            listItems.remove(selectedItem);
            directoryFiles.remove(selectedItem);
        }
    }

    @FXML
    private void geraArquivo(ActionEvent action) {
        gerarArquivo = !gerarArquivo;

        if (gerarArquivo) {
            DirectoryChooser fcFolderChooser = new DirectoryChooser();
            fcFolderChooser.setTitle("Selecione o Caminho de Saida");
            File file = fcFolderChooser.showDialog(null);

            if (file != null) {
                outputPath = file.getAbsolutePath().replaceAll("\\\\", "/");
                outputPath += "/XMITS";
                gerarArquivo = true;
            } else {
                gerarArquivo = false;
                cbMakeFiles.setSelected(gerarArquivo);
            }
        }
    }

    @FXML
    private void sequenceDiagram(ActionEvent action) {
        sequenceDiagram = !sequenceDiagram;
    }

    @FXML
    private void runAction(ActionEvent action) throws Exception {
        Printer print = new Printer();
        String stringTS;
        TransitionSystem ts;
       
        if (!directoryFiles.isEmpty()) {
            String erro = error.getText().toString();              
        

            if (sequenceDiagram) {
                Tuts tuts = new Tuts();

                directoryFiles.forEach((dir) -> {
                    tuts.addFile(dir);
                });
                if (!erro.isEmpty()) {
                    tuts.prepareToRunError(erro);
                } else {
                    tuts.prepareToRun();
                }
                if (tuts.getHasSequenceDiagram()) {
                    tuts.run2();
                    ts = tuts.getOutput();
                    stringTS = print.getTS(tuts.getOutput());
                } else {
                    JOptionPane.showMessageDialog(null, "Não há diagrama de sequência");
                    throw new Exception("Não há diagrama de sequência");
                }

            } else {
                Converter converter = new Converter();

                converter.run(directoryFiles.get(0));

                ts = converter.getOutput();
                stringTS = print.getTS(ts);
            }
            if (erro.isEmpty()) {
                showTS(stringTS);
            }
            String palavras = print.createMensage(ts);
            /*palavras = palavras.replaceAll(Pattern.quote("("), "");
            palavras = palavras.replaceAll(Pattern.quote(")"), "");
            palavras = palavras.replaceAll(Pattern.quote(","), "");
            palavras = palavras.replaceAll("_", " ");
            //palavras = palavras.replaceAll("-", " ");
            
            palavras = palavras.replaceAll(",", "");
            
            System.out.println(palavras);
            String[] array = palavras.split(" ");
            Classify classify = new Classify();
            classify.gravar(palavras);*/
             if (erro.isEmpty()) {
            if (gerarArquivo) {
                saveFiles(ts);
            }
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "Desculpe. Você precisa inserir ao menos um diagrama para executar");
        }
    }

    public void showTS(String stringTS) {
        ShowTSInterface frame = new ShowTSInterface();
        frame.setTitle("Transition System Output");
        frame.setText(stringTS);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void saveFiles(TransitionSystem list) {
        Bridge bridge = new Bridge();
        Printer print = new Printer();
        Dot dot = new Dot();

        long mili = new Date().getTime();

        dot.saveFile(dot.getTS(list), outputPath + "_" + mili);
        createImage(outputPath + "_" + mili + "_dot.dot", mili);//dot
        bridge.saveInputFile(list, outputPath + "_" + mili);//smv
        print.txtPrint(list, outputPath + "_" + mili);//txt
    }

    private void createImage(String input, long mili) {
        GraphViz gv = new GraphViz();
        gv.readSource(input);
        new File(input).delete();

        String exacutable = gv.getExacutable();
        File test = new File(exacutable);

        if (test.exists()) {

            String type = "pdf";

            String repesentationType = "dot";

            File out = new File(outputPath + "_" + mili + "." + type);
            gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);
        } else {
            JOptionPane.showMessageDialog(null, "Desculpa, não foi possível localizar o graphviz");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvFilePaths.setItems(listItems);
    }
}
