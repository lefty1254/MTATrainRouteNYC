/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.ArrayList;

/**
 *Class that creates teh necessary train transfers for a route by taking the least amount of trains
 *
 */
public class TrainTransfers {
    /**
     * list of list of trains to transfer to
     */
    private ArrayList<TrainList> trainTransfers;
    /**
     * list o stations to transfer to
     */
    private ArrayList<Station> stationTransfers;
    /**
     * the list of al the stations in the route
     */
    protected ArrayList<Station> route;

    /**
     * Sets the route to the input list of stations,initializes train transfer list and station transfer list and
     * fills teh two lists
     * @param stations the route of stations to take
     */
    public TrainTransfers(ArrayList<Station> stations) {
        if (stations.size() > 1) {
            route = stations;
            trainTransfers = new ArrayList<>();
            stationTransfers = new ArrayList<>();
            createTransfers();
        }
    }

    /**
     * Method to fill the train and station transfers list by checking which station does not contain any trains
     * that the station transferred from contains
     *
     */
    private void createTransfers() {

        Station transferStation = route.get(0), fromStation, toStation = null;

        TrainList fromList = new TrainList(transferStation.getTrains()),toList,tempList;
        stationTransfers.add(transferStation);

        for (int i = 1; i < route.size(); i++) {
            fromStation = route.get(i-1);
            toStation = route.get(i);
            toList = new TrainList(toStation.getTrains());
            tempList = fromList.intersection(toList);
            if(tempList.isEmpty()){
                stationTransfers.add(fromStation);
                trainTransfers.add(fromList);
                transferStation=fromStation;
                fromList = new  TrainList(transferStation.getTrains());
                tempList = fromList.intersection(toList);
            }
            fromList = tempList;

        }
        stationTransfers.add(toStation);
        trainTransfers.add(fromList);
    }

    /**
     * Override toString
     * @return a string with a line for every transfer containing the origin station, the trains to take and the destination station
     */
    @Override
    public String toString() {
        if (route == null)
            return "No route";
        StringBuilder builder = new StringBuilder();
        int size = trainTransfers.size();

        TrainList trainList = trainTransfers.get(0);
        Station transferStation = stationTransfers.get(0);
        String s;
        if(size == 1) {
            trainList = trainTransfers.get(size-1);
            builder.append("From "+transferStation.toString());
            if (trainList.size()==1)
                s = " transfer to the "+trainList.toString();
            else
                s = " transfer to any of "+trainList.toString();
            transferStation = stationTransfers.get(size);
            builder.append(s+" to reach your destination " + transferStation.toString()+"\n");
            return builder.toString();
        }
        builder.append("From station "+transferStation.toString());

        if(trainList.size() == 1)
            s = " take the ";
        else
            s = " take any of ";
        transferStation = stationTransfers.get(1);
        builder.append(s+trainList.toString()+" to "+transferStation.toString()+"\n");
        for (int i = 1; i<size-1; i++) {
            trainList = trainTransfers.get(i);
            builder.append("At "+transferStation.toString()+ " transfer to ");
            if(trainList.size() == 1)
                s = " the ";
            else
                s = " any of ";
            transferStation = stationTransfers.get(i+1);
            builder.append(s+trainList.toString()+ " toward "+transferStation.toString() + "\n");

        }
        trainList = trainTransfers.get(size-1);
        builder.append("From "+transferStation.toString());
        if (trainList.size()==1)
            s = " transfer to the "+trainList.toString();
        else
            s = " transfer to any of "+trainList.toString();
        transferStation = stationTransfers.get(size);
        builder.append(s+" to reach your destination " + transferStation.toString()+"\n");


        return builder.toString();
    }
}
