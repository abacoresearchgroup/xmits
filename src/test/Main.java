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
