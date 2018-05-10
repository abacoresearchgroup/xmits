package graphical_interface;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Albino Freitas
 */
public class ApplicationStart extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./view/MainInterface.fxml"));

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
