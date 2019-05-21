/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;

import java.util.ArrayList;

/**
 * Class to clean up the list of lines, add the stations to a Station list and create a graph
 */
public class TrainGraphParser {


    /**
     * Method to create a train graph based on an array list of train lines with the stations for each line
     * @param lines the list of lines
     * @return A graph with all the station and connections for the lines
     */
    public static MTATrainGraph parseLineListToGraph(ArrayList<ArrayList<String>> lines) {
        TrainGraphWriter.createOriginalFile(lines);

        StationList stations = new StationList();


        for(int i=0; i < lines.size(); i++) {
            ArrayList<String> line = lines.get(i);
            Train train = new Train(line.get(0));
            String borough = line.get(3);
            String stationName = line.get(4);
            stationName = cleanStationName(stationName);
            String concStationName = concatenateStationName(stationName,borough);
            Station fromStation = stations.getStation(concStationName), toStation;


            if(fromStation == null) {
                fromStation = new Station(stationName,concStationName,borough);
            }
            fromStation.addTrain(train);
            for (int j = 5; j < line.size(); j++) {
                stationName = line.get(j);
                concStationName = concatenateStationName(stationName,borough);
                if (!concStationName.isEmpty()) {
                    String isBorough = getBorough(stationName);
                    if(isBorough != null)
                        borough = isBorough;
                    else {
                        toStation = stations.getStation(concStationName);
                        if(toStation == null) {
                            stationName = cleanStationName(stationName);
                            toStation = new Station(stationName,concStationName,borough);
                        }
                        toStation.addTrain(train);
                        toStation.addConnection(fromStation,train);
                        fromStation.addConnection(toStation,train);
                        stations.addStation(fromStation);
                        fromStation = toStation;
                    }
                }

            }
            stations.addStation(fromStation);

        }

        return new MTATrainGraph(stations);
    }

    /**
     * Method to clean up unwanted strings anc concatenate the borough with the name and no white spaces
     * @param stationName the name to concatenate
     * @param borough the borough to concatenate
     * @return concatenated string of borough and name
     */
    private static String concatenateStationName(String stationName, String borough) {
        String s = concatenateStationName(stationName);
        if (s.isEmpty())
            return s;
        if(s.length() > 5)
            if (s.substring(0,5).equals("Until") || s.substring(0,4).equals("Only"))
                return "";
        return borough+s;
    }

    /**
     * Method to clean the station to name by removing all / whitespaces and - at the end of the string
     * and replace all / that are followed by characters with -
     * @param stationName the name to clean
     * @return the clean up station name
     */
    private static String cleanStationName(String stationName) {
        int length = stationName.length();
        if (length == 0)
            return stationName;
        if(stationName.charAt(length-1) == '/')
            stationName = stationName.substring(0,length-1);

        stationName= stationName.replaceAll("/","-");
        length = stationName.length()-1;
        if (length>=0) {
            if (stationName.charAt(length) == '-')
                stationName = stationName.substring(0, length);
            int i = 0;
            if (stationName.charAt(0) == '-')
                i = 1;
            while (stationName.charAt(i) == ' ')
                i++;
            length = stationName.length();
            stationName = stationName.substring(i, length);
        }
        stationName = cleanEnd(stationName);


        return stationName;
    }

    /**
     * Method to clean remove all whitespaces or - ate the end of a string
     * @param stationName the string to clean
     * @return the cleaned up string
     */
    private static String cleanEnd(String stationName) {
        int i = stationName.length()-1;
        char c = stationName.charAt(i);
        while ((c == '-' || c == ' ') && i>0)
            c = stationName.charAt(--i);
        return stationName.substring(0,i+1);
    }

    /**
     * Method to return a borough if the string is a borough
     * @param s the string to input
     * @return the borough in upperCase letters
     */
    private static String getBorough(String s){
        s = s.toUpperCase();
        int length = s.length();
        if(length<5)
            return null;
        String borough = s.substring(0,5);
        if (borough.equals("BRONX"))
            return borough;
        if(length < 6 )
            return null;
        if (borough.equals("QUEENS"))
            return borough;
        if (length<8)
            return null;
        borough = s.substring(0,8);
        if (borough.equals("BROOKLYN"))
            return borough;
        if (length<9)
            return null;
        borough = s.substring(0,9);
        if (borough.equals("MANHATTAN"))
            return borough;

        return null;

    }

    /**
     * Method to remove all whitespaces / - from a string and remove everything followed by a (
     * @param stationName the string to clean
     * @return the cleaned up string
     */
    private static String concatenateStationName(String stationName) {
        String concName = stationName.replaceAll("/","");
        concName = concName.replaceAll(" ","");
        concName = concName.replaceAll("-","");
        if(concName.contains("(")) {
            for (int i = 0; i < concName.length(); i++) {
                if (concName.charAt(i) == '(') {
                    concName = concName.substring(0, i);
                    break;
                }
            }
        }
        return concName;
    }


}
