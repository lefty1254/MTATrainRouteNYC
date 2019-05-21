/**
 * @author Eleftherios Troullouris
 */

package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that starts the application
 */
public class Main extends Application {
    /**
     * Method with actions to do on open
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("choice_screen.fxml"));
        primaryStage.setTitle("Train Router");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Start application
     * @param args The console arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
