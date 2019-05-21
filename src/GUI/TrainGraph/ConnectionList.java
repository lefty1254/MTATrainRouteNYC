/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Custom List that Stores StationConnectionObjects
 */

public class ConnectionList {

    /**
     * HashMap that stores the StationConnection objects
     */

    private HashMap<String, StationConnection> connections;
    
    /**
     * Constructor for ConnectionList, initializes the HashMap
     */
    public ConnectionList() {
        connections=new HashMap<>();
    }
    
    /**
     * method to add a StationConnection to the ConnectionList or if the connection exist to add a new Train to the connection
     * @param from the Station object where you start
     * @param to the Station object this connects to
     * @param train the train line that connects the station
     */
    public void addConnection(Station from, Station to ,Train train) {
        String toName = to.getConcStationName();
        StationConnection connection = connections.get(toName);
        if (connection != null) {

            connection.addTrain(train);
            connections.remove(toName);
            connections.put(toName,connection);
        }
        else {
            connection = new StationConnection(from,to,train);
            connections.put(toName,connection);
        }
    }
    
    /**
     *Getter for Connections HashMap
     * @return HashMap with key String and value Connections
     */
    public HashMap<String, StationConnection> getConnections() {
        return connections;
    }
    
    
    public Collection<StationConnection> values(){
        return connections.values();
    }
    
    /**
     *
     * @param key The input String to get the value from the list
     * @return Returns StationConnection object
     */
    public StationConnection getConnection(String key) {
        return connections.get(key);
    }
    
    
    public ArrayList<Train> getConnectionTrains(String key) {
        return connections.get(key).getTrains();
    }
}
