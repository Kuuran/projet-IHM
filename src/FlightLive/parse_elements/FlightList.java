package FlightLive.parse_elements;

import java.util.ArrayList;

public class FlightList {

    public ArrayList<Flight>acList; //List of planes

    public FlightList(){
        acList = new ArrayList<>();
    }

    public void clear(){
        acList.clear();
    }

    public static boolean contains(String[] arr, String item) {
        for (int i=0; i<arr.length; i++) {
            if (item == arr[i]) {
                return true;
            }
        }
        return false;
    }


}