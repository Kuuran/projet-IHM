package FlightLive.elements;

import java.util.ArrayList;

public class World {

    private ArrayList<Airport> airports;
    private ArrayList<Plane> planes;

    public World(){
        airports = new ArrayList<>();
        planes = new ArrayList<>();
    }



    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public void addAirport(Airport airport){
        airports.add(airport);
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public void addPlane(Plane plane){
        planes.add(plane);
    }
}
