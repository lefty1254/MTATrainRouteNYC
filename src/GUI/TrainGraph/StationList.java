/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.Collection;
import java.util.HashMap;

/**
 * Custom list of Stations using a HashMap
 */
public class StationList {
    /**
     * The map which the Station objects are stored
     */
    private HashMap<String,Station> stations;

    /**
     * Initialized a new HashMap
     */
    public StationList() {
        stations = new HashMap<>();
    }

    /**
     * Method to add station to the map, doesn't allow duplicates
     * @param station the station to add
     */
    public void addStation(Station station) {
        String name = station.getConcStationName();
        if(stations.containsKey(name))
            stations.remove(name);
        stations.put(name,station);
    }

    /**
     * Getter method to retrieve a Station on the list based on it's concatenated name
     * @param station the concatenated name of the station to retrieve
     * @return the station in the list with the concatenated name ore null if there isn't one
     */
    public Station getStation(String station) {
        return stations.get(station);
    }

    /**
     * Method to return the stations in the list as a collection
     * @return the Collection of Stations in the list
     */
    public Collection<Station> values() {
        return stations.values();
    }
    public boolean containsStation(String station) {
        return stations.containsKey(station);
    }

    /**
     * Method to get the size of the list
     * @return the size of the list
     */
    public int size(){
        return stations.size();
    }

    /**
     * Overide toString method
     * @return a string with every connection on a new line
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Station station : stations.values()) {
            for (StationConnection connection : station.getConnections().values()) {
                builder.append(connection.toString()+"\n");
            }
        }
        return builder.toString();
    }
}
