/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *  Class to store information of the station (Vertex)
 */
public class Station implements Comparable<Station> {
    /**
     * the list of trains that stop at the Station
     */
    private ArrayList<Train> trains;
    /**
     * the name of the Station
     */
    private String stationName;
    /**
     * the borough + the name of the station without whitespaces or non alphabetical characters
     */
    private String concStationName;
    /**
     * The custom list of StationConnections that this station connects to
     */
    private ConnectionList connections;
    /**
     * The borough this station is at.
     */
    private String borough;

    /**
     *  Sets parameters and initializes lists
     * @param stationName the name of the Station
     * @param concStationName the concatenated name of the station
     * @param borough the borough of the station
     */
    public Station(String stationName, String concStationName, String borough) {
        this.stationName = stationName;
        this.concStationName = concStationName;
        this.borough = borough;
        connections = new ConnectionList();
        trains = new ArrayList<>();
    }

    /**
     * Getter for the list of trains that stop at the Station
     * @return the list of trains connecting to the Station
     */
    public ArrayList<Train> getTrains() {
        return trains;
    }

    /**
     * Getter for station name
     * @return station name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     *  Getter for borough
     * @return the borough of the station
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Getter for list of connections
     * @return the list of edges to the Station
     */
    public ConnectionList getConnections() {
        return connections;
    }

    /**
     * Method to add train to the train list
     * @param train the train to add to the list
     */
    public void addTrain(Train train) {
        if (trains.contains(train))
            return;
        trains.add(train);
    }

    /**
     * Method to get concatenated name
     * @return concatenated station name
     */
    public String getConcStationName() {
        return concStationName;
    }

    /**
     * Method to add connection to the connection list to another station
     * @param to the station this station is connected to
     * @param train the train that connects the stations
     */
    public void addConnection(Station to, Train train) {
        connections.addConnection(this,to, train);
    }
    public StationConnection getConnectionToStation(Station to) {
        return connections.getConnection(to.getConcStationName());
    }

    /**
     * Override java toString method
     * @return the name of the Station
     */
    @Override
    public String toString() {
        return stationName;
    }

    /**
     * Method to check if this station is equal to another station by checking if they both have the same
     * concatenatedName and the same borough
     * @param station the station to check if this station is equal to
     * @return true if they're equal or false if not
     */
    public boolean equals(Station station) {
        if(this.concStationName.equals(station.concStationName)&& this.borough.equals(station.borough))
            return true;
        return false;
    }

    /**
     * Override Comparable interface compare to method so stations can be sorted in a list
     * @param o the station to compare to
     * @return alphabetical ordering greater than 0 if this stations name comes first 0 if they're the same 0 and less than 0 if the station name comes after
     */
    @Override
    public int compareTo(Station o) {
        int i = this.concStationName.compareTo(o.getConcStationName());
        return i;
    }


}
