/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  Class to store graph for the MTATrain data and create graph methods
 */
public class MTATrainGraph {
    /**
     * The custom list of Stations(vertices)
     */
    private StationList stations;
    /**
     *  Map to store the array index of the Stations
     */
    private HashMap<String, Integer> stationNumbers;
    /**
     * A list to know which values is at a which index
     */
    private ArrayList<String> stationKeys;
    /**
     * The graph edge matrix
     */
    private StationConnection [][] connectionsMatrix;


    /**
     * Takes in a station list, initializes the edge matrix, the index list and map, it proceeds fill the map and list
     * by calling the createStationNumbers method. Then to fill the edge matrix it calls the fillConnectionMatrix method
     * @param stations the list of stations for the graph
     */
    public MTATrainGraph(StationList stations) {
        TrainGraphWriter.createNewlFile(stations);
        this.stations = stations;
        int n = stations.size();
        connectionsMatrix = new StationConnection[n][n];
        stationKeys = new ArrayList<>();
        stationNumbers = new HashMap<>();
        createStationNumbers();
        fillConnectionMatrix();
    }

    /**
     * Method to fill the edge matrix by iterating though each Station in the StationList
     * and checking it's connections takes O(n*t) time where n is the number of Stations and t is the number of trains
     */
    private void fillConnectionMatrix() {
        for(Station station : stations.values()) {
            int fromIndex = getStationIndex(station), toIndex;
            ConnectionList connections = station.getConnections();
            for (StationConnection connection : connections.values()) {
                Station toStation = connection.getTo();
                toIndex = getStationIndex(toStation);
                connectionsMatrix[fromIndex][toIndex] = connection;
            }

        }
    }

    /**
     * Method to fill in index map and list
     */
    private void createStationNumbers() {
        int count = 0;
        for (Station station: stations.values()){
            String name = station.getConcStationName();
            stationKeys.add(name);
            stationNumbers.put(name,count++);
        }
    }

    /**
     * Method to get the array index of a station
     * @param station The station to get the index of
     * @return The index of the input Station
     */
    public int getStationIndex(Station station) {
        return stationNumbers.get(station.getConcStationName());
    }

    /**
     * method to get a Station by index
     * @param i the index of the Station to get
     * @return The station at input index
     */
    public Station getStationByIndex(int i) {
        String key = stationKeys.get(i);
        return stations.getStation(key);
    }

    /**
     * Method to get the StationList of the graph
     * @return The graph's StationList
     */
    public StationList getStations() {
        return stations;
    }

    /**
     * Method to get the string of station transfers for the shortest path
     * @param from the station from where to start
     * @param to the station to end at
     * @return the string of station transfers
     */
    public String getShortestPath(Station from, Station to) {
        DijktrasTrain dijktra = new DijktrasTrain(this,from,to);


        return dijktra.getTrainTransfers().toString();

        /*
        TrainTransfers transfers = dijktra.getTrainTransfers();
        return dijktra.toString()+"\n"+transfers.toString();

         */
    }

}
