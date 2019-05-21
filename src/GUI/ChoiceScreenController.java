/**
 * @author Eleftherios Troullouris
 */

package GUI;

import GUI.TrainGraph.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class for the choice_screen.fxml
 */

public class ChoiceScreenController implements Initializable {
    /**
     * List of station that are in Bronx
     */
    protected ArrayList<Station> bronxList;
    /**
     * List of station that are in  Brooklyn
     */
    protected ArrayList<Station> brooklynList;
    /**
     * List of station that are in queens
     */
    protected ArrayList<Station> queensList;
    /**
     * List of station that are in Manhattan
     */
    protected ArrayList<Station> manhattanList;
    /**
     * The graph to work with
     */
    private MTATrainGraph graph;
    /**
     * An observable list with all the boroughs
     */
    private ObservableList<String> boroughList = FXCollections.observableArrayList("BRONX","QUEENS","BROOKLYN","MANHATTAN");

    /**
     * Method to fill in borough list and sort them alphabetically
     */
    private void createBoroughList() {
        bronxList = new ArrayList<>();
        brooklynList = new ArrayList<>();
        queensList = new ArrayList<>();
        manhattanList = new ArrayList<>();
        for (Station station : graph.getStations().values()) {
            addToBoroughs(station);
        }
        bronxList.sort((station, o) -> station.compareTo(o));
        queensList.sort((station, o) -> station.compareTo(o));
        brooklynList.sort((station, o) -> station.compareTo(o));
        manhattanList.sort((station, o) -> station.compareTo(o));

    }

    /**
     * Method to add a station to the right borough list
     * @param station the station to add to a borough list
     */
    private void addToBoroughs(Station station) {
        String borough = station.getBorough();
        switch (borough) {
            case "BRONX":
                bronxList.add(station);
                break;
            case "QUEENS":
                queensList.add(station);
                break;
            case "BROOKLYN":
                brooklynList.add(station);
                break;
            case "MANHATTAN":
                manhattanList.add(station);
        }

    }


    /**
     * The choice box for which borough to choose origin station from
     */
    @FXML
    private ChoiceBox<String> originBoroughBox;
    /**
     * The choice box for which station to start at
     */
    @FXML
    private ChoiceBox<Station> originStationBox;
    /**
     * The choice box for which borough to choose destination station
     */
    @FXML
    private ChoiceBox<String> destBoroughBox;
    /**
     * The choice box for which station to go to
     */
    @FXML
    private ChoiceBox<Station> destStationBox;

    /**
     * Method that's called when the button is clicked that calculated the shortest path from the two stations
     * chosen in the window and opens a new train_route.fxml window with a string of the TrainTransfers
     */
    @FXML
    void getShortestPathButtonAction(ActionEvent event) {
        Station fromStation = originStationBox.getValue();
        Station toStation = destStationBox.getValue();
        String s;
        s = graph.getShortestPath(fromStation,toStation);
        //s = graph.getStations().toString();
        System.out.println(s);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("train_route.fxml"));
        try {
            loader.load();


        } catch (IOException e) {
            Logger.getLogger(ChoiceScreenController.class.getName()).log(Level.SEVERE,null,e);

        }
        TrainRouteController routeController = loader.getController();
        routeController.setText(s);

        Parent p = loader.getRoot();

        Stage stage = new Stage();
        stage.setScene(new Scene(p,800,400));
        stage.showAndWait();


    }

    /**
     * Override initialize method, the tasks to do when the class is called
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graph = TrainGraphParser.parseLineListToGraph(new HtmlTrainReader().getLines());
        createBoroughList();
        originBoroughBox.setItems(boroughList);
        originBoroughBox.setValue("BRONX");
        destBoroughBox.setItems(boroughList);
        destBoroughBox.setValue("BRONX");
        changeOriginStationBox(bronxList);
        changeDestStationBox(bronxList);

        originBoroughBox.getSelectionModel().selectedItemProperty().addListener( ((observable, oldValue, newValue) -> updateOriginStationBox(newValue)) );
        destBoroughBox.getSelectionModel().selectedItemProperty().addListener( ((observable, oldValue, newValue) -> updateDestStationBox(newValue)) );

    }

    /**
     * Method to change the choice list in the origin station choice box based on the origin borough selected
     * @param borough the borough selected
     */
    private void updateOriginStationBox(String borough) {
        switch (borough) {
            case "BRONX":
                changeOriginStationBox(bronxList);
                break;
            case "QUEENS":
                changeOriginStationBox(queensList);
                break;
            case "BROOKLYN":
                changeOriginStationBox(brooklynList);
                break;
            case "MANHATTAN":
                changeOriginStationBox(manhattanList);
        }
    }

    /**
     * Method to change the choice list in the destination station choice box based on the destination borough selected
     * @param borough the borough selected
     */
    private void updateDestStationBox(String borough) {
        switch (borough) {
            case "BRONX":
                changeDestStationBox(bronxList);
                break;
            case "QUEENS":
                changeDestStationBox(queensList);
                break;
            case "BROOKLYN":
                changeDestStationBox(brooklynList);
                break;
            case "MANHATTAN":
                changeDestStationBox(manhattanList);
        }
    }

    /**
     * Method to change destination station choice box based on an ArrayList of stations
     * @param list the list to fill the choice box with
     */
    private void changeDestStationBox(ArrayList<Station> list) {
        destStationBox.setValue(null);
        destStationBox.setItems(getObsList(list));
        destStationBox.setValue(list.get(0));
    }

    /**
     *Method to change destination station choice box based on an ArrayList of stations
     *@param list the list to fill the choice box with
     */
    private void changeOriginStationBox(ArrayList<Station> list) {
        originStationBox.setValue(null);
        originStationBox.setItems(getObsList(list));
        originStationBox.setValue(list.get(0));
    }

    /**
     * Method to convert an ArrayList to an ObservableList
     * @param stationList the ArrayList to convert
     * @return the ObservableList of the ArrayList
     */
    private ObservableList<Station> getObsList(ArrayList<Station> stationList) {
        /*ArrayList<String> list = new ArrayList<>();
        for (Station station : stationList) {
            list.add(station.toString());
        }*/
        return FXCollections.observableList(stationList);

    }


}
