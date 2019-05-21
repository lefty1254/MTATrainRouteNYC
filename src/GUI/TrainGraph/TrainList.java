/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.ArrayList;

/**
 * Custom list of trains
 */
public class TrainList {
    /**
     * List of trains
     */
    private ArrayList<Train> trains;

    /**
     * Initializes new train list
     */
    public TrainList() {
        trains = new ArrayList<>();
    }

    /**
     * Sets the train list to be equal to the input arraylist
     * @param trains the train list to set to
     */
    public TrainList(ArrayList<Train> trains) {
        this.trains = trains;
    }

    /**
     * Method to get the intersection with another list
     * @param otherList the list to get the intersection
     * @return a list of shared trains
     */
    public TrainList intersection(TrainList otherList) {
        TrainList list = new TrainList();
        int size = this.size();
        if(size>otherList.size())
            return otherList.intersection(this);
        for (int i = 0; i < size; i++){
            Train train = this.get(i);
            if(otherList.contains(train))
                list.add(train);
        }
        return list;
    }

    /**
     *
     * @return size of the list
     */
    public int size(){
        return trains.size();
    }

    /**
     * Method to get train at specific index
     * @param i the index
     * @return the train at index
     */
    public Train get(int i) {
        return trains.get(i);
    }

    /**
     * Method to check if the list contains a train
     * @param train the train to check
     * @return true if the train is in the list false otherwise
     */
    public boolean contains(Train train) {
        return trains.contains(train);
    }

    /**
     * Method to add a train to the list
     * @param train the train to add to the list
     */
    public void add(Train train) {
        trains.add(train);
    }

    /**
     * method to check if a list is empty
     * @return true if the list is empty false otherwise
     */
    public boolean isEmpty() {
        return trains.isEmpty();
    }

    /**
     * Override toString method
     * @return a string of trains separated by commas
     */
    @Override
    public String toString() {
        int size = size();
        if(size==1){
            return get(0).toString();
        }
        String s = "";
        for (int i = 0; i < size-1; i++) {
            s += trains.get(i).getName()+", ";
        }
        s += trains.get(size-1).getName() + " trains";
        return s;
    }


}
