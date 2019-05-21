/**
 * @author Eleftherios Troullouris
 */



package GUI.TrainGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Class to find the shortest path to get to stations.
 */
public class DijktrasTrain {
    /**
     * The graph for the shortest path problem.
     */
    private MTATrainGraph graph;
    /**
     * PriorityQueue for the shortest path problem.
     */
    private PriorityQueue<StationLabel> labelQ;
    /**
     *  A map to store the labels with remove from the PriorityQueue.
     */
    private HashMap<String,StationLabel> visited;
    /**
     *  The origin Station for the shortest path problem.
     */
    private Station origin;
    /**
     *  The destination Station shortest path problem.
     */
    private Station destination;
    /**
     *  The ordered list of Station objects that is the shortest path.
     */
    private ArrayList<Station> route;
    /**
     * Boolean that stores if there's a route or not
     */
    private boolean noRoute;
    
    /**
     *  Constructor for DijktrasTrain Object that creates a shortest path by calling the findShortestPath method.
     * @param graph The graph to for which find the shortest path.
     * @param origin The station you start at.
     * @param destination The station you want to go to.
     */
    public DijktrasTrain(MTATrainGraph graph, Station origin, Station destination) {
        this.graph = graph;
        findShortestPath(origin, destination);
    }
    
    /**
     * Method to find and create a route for the shortest Path by calling createQueue, visitPaths and createRoute methods.
     * @param origin The station to start.
     * @param destination The station  you want to go to.
     */
    protected void findShortestPath(Station origin, Station destination) {
        route = new ArrayList<>();
        this.origin= origin;
        this.destination=destination;
        noRoute = false;
        createQueue();
        visitPaths();
        createRoute(visited.get(destination.getConcStationName()));
    }
    
    /**
     * This method removes StationLabel objects from the priority queue by minimum cost and updates the cost of the neighbors Stations
     */
    private void visitPaths() {
        while (labelQ.size()>0) {
            StationLabel labelFrom = labelQ.poll();
            updateNeighbors(labelFrom);
        }
    }
    
    /**
     * This method initializes the priority queue by first creating the origin StationLabel with a cost of 0 and adds it to the PriorityQueue. After it creates Labels
     * for every other Station that exist in the graph and adds them to the PriorityQueue
     */
    private void createQueue() {
        StationList list = graph.getStations();
        labelQ = new PriorityQueue<>(list.size(),StationLabel.comparator);
        visited = new HashMap<>();
        StationLabel label = new StationLabel(origin,0);
        labelQ.add(label);
        for (Station station : list.values()) {
            if (!station.equals(origin)) {
                label = new StationLabel(station);
                labelQ.add(label);
            }
        }

    }
    
    /**
     * Creates a route by recursively calling itself Using a StationLabel object until we reach the StationLabel for the
     * origin of the shortest Path and then it starts to add  each station to the route ArrayList of Station objects
     * @param label StationLabel node
     */
    private void createRoute(StationLabel label) {
        if(label.cost == Integer.MAX_VALUE) {
            noRoute = true;
            return;
        }
        if(label.station.equals(origin)) {
            route.add(label.station);
            return;
        }
        createRoute(label.from);
        route.add(label.station);

    }

    /**
     * Method to update the cost to get to the neighbors of a StationLabel if they have not already been visited by
     * iterating through the connections of the Station of the StationLabel and for every connected Station that is still in the
     * PriorityQueue and update the cost if a shorter path is found
     * @param labelFrom
     */
    private void updateNeighbors(StationLabel labelFrom) {
        visited.put(labelFrom.getName(),labelFrom);
        if (labelFrom.cost == Integer.MAX_VALUE)
            return;
        ConnectionList connections = labelFrom.getStation().getConnections();
        for (StationConnection connection : connections.values()) {
            Station stationTo = connection.getTo();
            if (!visitedStation(stationTo.getConcStationName())){
                StationLabel labelTo = getStationLabel(stationTo);
                int newCost = labelFrom.cost + connection.weight;
                if (newCost < labelTo.cost) {
                    labelTo.cost = newCost;
                    labelTo.from = labelFrom;
                    setLabel(labelTo);
                }
            }
        }
    }
    /**
     *Method to change the values of a label in the PriorityQueue
     * @param label the label to be updated
     */
    private void setLabel(StationLabel label) {
        labelQ.remove(label);
        labelQ.add(label);
    }
    /**
     * Method to get a StationLabel from the Priority Queue that contains a specific station
     * @param station the Station that the label should contain
     * @return The station
     */
    private StationLabel getStationLabel(Station station) {
        Iterator<StationLabel> iterator = labelQ.iterator();
        while (iterator.hasNext()) {
            StationLabel label = iterator.next();
            if (station.equals(label.station))
                return label;
        }
        return null;
    }

    /**
     * Method to check if a station is in the PriorityQueue
     * @param name the concatenated name of the Station
     * @return True if it's not in the PriorityQueue, false if it is
     */
    private boolean visitedStation(String name) {
        return visited.containsKey(name);
    }

    /**
     * Method to create TrainTransfers for the route of a shortest Path
     * @return Shortest path TrainTransfers
     */
    public TrainTransfers getTrainTransfers(){
        return new TrainTransfers(route);

    }

    /**
     * Overide toString method DijktrasTrain class that creates a string with every station you have to visit to and it's
     * trains on each line until it reaches the destination.
     * @return A string with every station you need to visit to get to your destination
     */
    @Override
    public String toString() {
        String s;
        if(noRoute) {
            return s="No route from " + origin.toString() + " to " + destination.toString();
        }
        StringBuilder builder = new StringBuilder();
        int size = route.size();
        Station station;

        for (int i = 0; i < size-1; i++) {
            station = route.get(i);
            s = station.getStationName()+" ----> ";
            builder.append(s);
        }
        station = route.get(size-1);
        builder.append(station.getStationName());

        return builder.toString();
    }

    public ArrayList<Station> getRoute() {
        return route;
    }
}
