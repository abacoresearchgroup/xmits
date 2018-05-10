package test;

import data.Classify;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        Process exec;  
        /*  try {
        exec = Runtime.getRuntime().exec("python "+
        "2+2\n"
        );
        if ( exec.waitFor() == 0)
        System.out.println("Executado.");
        else
        System.out.println("ERRO");
        } catch (IOException e) {
        e.printStackTrace();
        } catch (InterruptedException e) {
        e.printStackTrace();
        }  */
        //Runtime.getRuntime().exec("python C:/Users/Camila/Desktop/teste.py");
        /* Process saida;
        String linha;
        Runtime r = Runtime.getRuntime();
        saida = r.exec("C:/Users/Camila/Desktop/teste.py");
        int sta = saida.waitFor();
        System.out.println(saida.exitValue());
        if (sta == 0) {
        saida.destroy();
        System.out.println("Programa Terminou corretamente: ");
        }*/
        Parent root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));;
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("XMITS");
        primaryStage.show();
       }

    public static void main(String[] args) {
        launch(args);
    }

}
