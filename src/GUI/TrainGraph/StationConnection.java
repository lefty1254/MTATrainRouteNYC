/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.ArrayList;

/**
 * Class to create the edges of the graph
 */
public class StationConnection {
    /**
     * The station to start
     */
    private Station from;
    /**
     * The station to go to
     */
    private Station to;
    /**
     * The cost to travel
     */
    public final int weight = 1;
    /**
     * The list of trains that travel through the connection
     */
    private ArrayList<Train> trains;

    /**
     * Sets parameters initializes the train list and add the train
     * @param from the station to start
     * @param to the station to go to
     * @param train the train that travels through the connection
     */
    public StationConnection(Station from, Station to, Train train) {
        this.from = from;
        this.to = to;
        trains = new ArrayList<>();
        trains.add(train);
    }


    /**
     * Method to add train to the train list
     * @param train the train to be added
     */
    public void addTrain(Train train) {
        trains.add(train);

    }

    /**
     * Getter method for station the connection goes to
     * @return the station the connection goes to
     */
    public Station getTo() {
        return to;
    }

    /**
     * Method to get the list of trains that travel through the connection
     * @return the list of trains that travel through the connection
     */
    public ArrayList<Train> getTrains() {
        return trains;
    }

    /**
     * Override toString method
     * @return From: (Start Station) | to: (End Station) | trains: (trains)
     */
    @Override
    public String toString() {
        return "From: " + from.toString() + " | to: "+ to.toString() + " | trains: " + trains.toString();
    }
}
