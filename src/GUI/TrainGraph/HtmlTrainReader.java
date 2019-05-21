/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that downloads the train information from the mta website and stores it into an
 * ArrayList of ArrayList of Strings where each array list of strings contains the information for a specific train.
 */

public class HtmlTrainReader {
    /**
     *  Base url we use to download information
     */
    private final String BASE_URL = "http://web.mta.info/nyct/service/";
    /**
     *  list to save the url links to each train Line
     */
    private ArrayList<String> trainLineReferences;
    /**
     * List where all list of train stations according to train line are stored
     */
    private ArrayList<ArrayList<String>> lines;
    private List<String> stationsList;


    /**
     * Downloads the train line url reference and then downloads each train line intro a list of list of trains
     */
    public HtmlTrainReader() {
        trainLineReferences = new ArrayList<>();
        lines = new ArrayList<>();
        stationsList = new ArrayList<>();
        downloadLineReferences();
        downloadLines();
    }

    /**
     * Getter method to get the list of station per line
     * @return ArrayList of ArrayList of String lines
     */
    public ArrayList<ArrayList<String>> getLines() {
        return lines;
    }

    /**
     * Method to download the url of each train line and add them to the trainLineReferences list
     */
    private void downloadLineReferences()  {
        try {
            Document document = Jsoup.connect(BASE_URL).timeout(0).get();

            Elements allElements =  document.getElementsByTag("a");

            for (Element e : allElements) {
                if (e.childNode(0).hasAttr("src")) {
                    String s = e.attr("href");
                    trainLineReferences.add(s);

                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Method fill the line list by calling downLoadLineStation for each element after the first one in
     * trainLineReferences list
     */
    private void downloadLines() {
        for(int i=1; i< trainLineReferences.size(); i++) {
            lines.add(downloadLineStations(trainLineReferences.get(i)));
        }
    }

    /**
     * Get all the stations for a specific train line and creates an ordered traversal list
     * @param line the train line reference for the train line to download Stations for
     * @return list of stations as Strings
     */
    private ArrayList<String> downloadLineStations(String line) {
        ArrayList<String> list = new ArrayList<>();
        list.add(line);
        //printNewTrain(line);
        try {
            Document document = Jsoup.connect(BASE_URL+line).timeout(0).get();
            Elements allTables = document.getElementsByTag("table");
            Element lineTable = allTables.last();
            Elements entries = lineTable.select("span");
            for(Element e : entries) {
                String s = e.ownText();
                list.add(s);
                stationsList.add(s);
               // System.out.println(s);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }


        return list;
    }

    private void printNewTrain(String line){
        System.out.println();
        System.out.println("NEW TRAIN!!! "+ new Train(line).toString());
        System.out.println();
    }




    public void printLineHTM() {
        for(String htm : trainLineReferences)
            System.out.println(htm);
    }


}
