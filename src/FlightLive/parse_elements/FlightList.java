package FlightLive.parse_elements;

import java.util.ArrayList;

public class FlightList {


    public void getAcList() {
        for (int i=0; i<acList.size();i++){
            System.out.println(this.acList.get(i));
        }
    }

    public void setAcList(ArrayList<Flight> acList) {
        this.acList = acList;
    }

    public ArrayList<Flight>acList; //List of planes





    public static boolean contains(String[] arr, String item) {
        for (int i=0; i<arr.length; i++) {
            if (item == arr[i]) {
                return true;
            }
        }
        return false;
    }

    public void depuis(String Filtre,String ICAO){
        ArrayList<Flight> list=new ArrayList<>();
        switch (Filtre){
            case "From": for (int i=0; i<this.acList.size();i++){
                if (this.acList.get(i).From==ICAO){
                    list.add(acList.get(i));
                }
                break;
            }
            case "To": for (int i=0; i<this.acList.size();i++){
                if (this.acList.get(i).To==ICAO){
                    list.add(acList.get(i));
                }
                break;
            }
            case "Stops": for (int i=0; i<this.acList.size();i++){
                if (contains(this.acList.get(i).Stops,ICAO)){
                    list.add(acList.get(i));
                }
                break;
            }
            default:
                System.out.println("First argument can only contain 'From','To', or 'Stop'");

        }

    }



}