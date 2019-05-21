/**
 * @author Eleftherios Troullouris
 */

package GUI.TrainGraph;


import java.util.Objects;

/**
 * Class to store the trains
 */
public class Train implements Comparable<Train> {
    /**
     * The name of the train
     */
    private String name;
    /**
     * Boolean to know if it's express or not
     */
    private boolean express;

    /**
     * Creates a train by taking in a line reference and calling the generateName method for that train
     * @param name the line re
     */
    public Train(String name) {
        this.name = generateName(name);
    }

    /**
     * Method to convert the line reference into a name and also sets it to express if it's an express train
     * @param name the line reference to change
     * @return the name after the cleanup
     */
    private String generateName(String name) {

        if(name.charAt(1) == 'd') {
            name = name.substring(0,1);
            express = true;
            return name;
        }
        name = cropName(name);
        if(name.length() == 1) {
            name =name.toUpperCase();
            return name;

        }
        switch(name) {
            case "one":
                name = "1";
                break;
            case "two":
                name = "2";
                break;
            case "three":
                name = "3";
                break;
            case "four":
                name = "4";
                break;
            case "five":
                name = "5";
                break;
            case "six":
                name = "6";
                break;
            case "seven":
                name = "7";
                break;

        }
        return name;
    }

    /**
     * Method to remove line.htm from line reference
     * @param name the line reference to crop
     * @return the line reference with line.htm
     */
    private String cropName(String name) {
        int i = 0;
        while (name.charAt(i) != 'l')
            i++;
        if(i == 0)
            return name.substring(0,1);
        return name.substring(0,i);
    }

    /**
     * Getter for name of train
     * @return name of train
     */
    public String getName() {
        String s = "";
        if (express)
            s = " express";
        return name+s;
    }

    public boolean isExpress() {
        return express;
    }

    /**
     * Override equals method
     * @param o the object to compare to
     * @return return true if their equal false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train)) return false;
        Train train = (Train) o;
        return isExpress() == train.isExpress() &&
                getName().equals(train.getName());
    }

    /**
     * Override hashCode method
     * @return object hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), isExpress());
    }

    /**
     * Override to String method
     * @return name and if express
     */
    @Override
    public String toString() {
        String s;
        if (express)
            s = name + " Express train";
        else
            s = name + " train";

        return s;
    }

    /**
     * Override compareTo method of Comparable interface
     * @param o the train to compare to
     * @return alphaNumerical comparison
     */
    @Override
    public int compareTo(Train o) {
        int n;
        if (equals(o))
            return 0;
        n = this.name.charAt(0)-o.name.charAt(0);
        if(n==0){
            if(this.express)
                n = 1;
            else
                n = -1;
        }

        return n;
    }
}
