package FlightLive.elements;

import FlightLive.parse_elements.Flight;
import FlightLive.parse_elements.FlightList;

import java.util.ArrayList;

public class World {

    private ArrayList<String> airports;
    private Plane[] planes;
   // private String lastDv;

    public World(FlightList flightList){
        airports = new ArrayList<>();
        int i =0;
        for (Flight flight : flightList.acList){
            planes[i] = new Plane(flight);
            i++;
        }
    }



    public ArrayList<String> getAirports() {
        return airports;
    }

    public void addAirport(Airport airport){
        airports.add(airport);
    }

    public Plane[] getPlanes() {
        return planes;
    }
/*
    public void addPlane(Plane plane){
        planes.add(plane);
    }*/
}
