/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

/**
 * Class to create output files. One with the original input downloaded form the web and one with all the
 * stations after the construction of a StationList
 */
public class TrainGraphWriter {
    /**
     * Method to create output file with the original data
     * @param lineList the list of train line lists with their stations
     */
    public static void createOriginalFile(ArrayList<ArrayList<String>> lineList) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("originalStations.txt"));
            for (ArrayList<String> list : lineList) {
                printWriter.println("\nNew Line\n");
                for (String s : list)
                    printWriter.println(s);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to create output file with the cleaned up Stations their boroughs and the trains that stop there
     * @param stations the list of stations for the graph
     */
    public static void createNewlFile(StationList stations) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("adjustedStations.txt"));
            for (Station station : stations.values()) {
                printWriter.println(station.toString() + " Borough: " + station.getBorough() + "  | "+new TrainList(station.getTrains()).toString());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
