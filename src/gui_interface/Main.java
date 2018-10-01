package gui_interface;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Albino Freitas
 */
public class Main extends Application{
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        Process exec;  
        Parent root = FXMLLoader.load(getClass().getResource("interfaces/window.fxml"));;
        
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
