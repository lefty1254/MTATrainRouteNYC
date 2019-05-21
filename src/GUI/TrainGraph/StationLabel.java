/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.Comparator;

/**
 * Class for the StationLabel objects that are used in DijktrasTrain class
 */
public class StationLabel implements Comparable<StationLabel> {
    /**
     * The station at
     */
    protected Station station;
    /**
     * The cost to get to the station
     */
    protected int cost;
    /**
     * The connecting station
     */
    protected StationLabel from;
    /**
     * Comparator for StationLabel objects
     */
    public static Comparator<StationLabel> comparator = new Comparator<StationLabel>() {
        @Override
        public int compare(StationLabel o1, StationLabel o2) {
            return o1.compareTo(o2);
        }
    };

    /**
     * Initialize Station label with cost
     * @param station the station which the label represents
     * @param cost the cost to get to the station
     */
    public StationLabel(Station station, int cost) {
        this.station = station;
        this.cost = cost;
        this.from = null;
    }

    /**
     * Initializes Station label with cost Infinity
     * @param station the station which the label represents
     */
    public StationLabel(Station station) {
        this.station = station;
        this.from = null;
        cost= Integer.MAX_VALUE;
    }

    /**
     * Getter method for Station
     * @return the station of the label
     */
    public Station getStation() {
        return station;
    }

    public int getCost() {
        return cost;
    }

    public StationLabel getFrom() {
        return from;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setFrom(StationLabel from) {
        this.from = from;
    }

    /**
     * Method to get the concatenated name of the station the label represents
     * @return the concatenated name of the station of the label
     */
    public String getName() {
        return station.getConcStationName();
    }

    /**
     * Ovveride compareTo method of Comparable interface
     * @param o the obect to copare to
     * @return this.cost - o.cost
     */
    @Override
    public int compareTo(StationLabel o) {
        return this.cost-o.cost;
    }

    /**
     * Override equals method to be able to use the remove  method in a queue. It casts the object to a StationLabel
     * objecr and calls the equals method of the object
     * @param obj the object to copmpare to
     * @return true if their equal false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        return this.equals( (StationLabel) obj);
    }

    /**
     * Equals method to check if the labels represent the same station
     * @param compareLabel the label to compare to
     * @return true if they represent the same station otherwise false
     */
    public boolean equals(StationLabel compareLabel) {
        return this.getStation().equals(compareLabel.getStation());
    }
}
