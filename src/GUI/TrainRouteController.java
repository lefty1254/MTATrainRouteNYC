/**
 * @author Eleftherios Troullouris
 */

package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for train_route.fxml
 */
public class TrainRouteController implements Initializable {
    /**
     * The text area of the train_route.fxml
     */
    @FXML
    private TextArea routeTextArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method to change the text of the text area
     * @param text the text for the text area to display
     */
    public void setText(String text) {
        routeTextArea.setEditable(false);

        routeTextArea.setText(text);
    }
}
