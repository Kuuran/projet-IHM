package FlightLive.graphics;

import FlightLive.elements.Airport;
import FlightLive.elements.Plane;
import FlightLive.elements.World;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Bash {

    private World world;

    public Bash(World world){
        this.world = world;
    }

    public void printPlanes(ArrayList<Plane> planes){
        for(Plane plane : planes){
            System.out.println(plane.toString());
        }
    }

    public void testPrint() throws InterruptedException {
        ArrayList<Plane> planes = new ArrayList<>();
        //Test of planes from an airport (Stockton Metropolitan Airport)
        System.out.println("#######################################\nPlanes from Paris-Orly Airport :\n#######################################");
        for(Airport airport : world.getAirports()){
            if(airport.getName().equals("Paris-Orly Airport")){
                planes = world.getPlanesFromAirport(airport);
            }
        }
        printPlanes(planes);
        planes.clear();

        //Test of planes to an airport (Stockton Metropolitan Airport)
        System.out.println("#######################################\nPlanes to Charles de Gaulle International Airport :\n#######################################");
        for(Airport airport : world.getAirports()){
            if(airport.getName().equals("Charles de Gaulle International Airport")){
                planes = world.getPlanesToAirport(airport);
            }
        }
        printPlanes(planes);
        planes.clear();

        //Test of planes from and to two airports (Stockton Metropolitan Airport - Ketchikan International Airport,Ketchikan)
        System.out.println("#######################################\nPlanes from Paris-Orly Airport to Marseille Provence Airport :\n#######################################");
        for(Airport airport : world.getAirports()){
            if(airport.getName().equals("Paris-Orly Airport")){
                for(Airport airport2 : world.getAirports()){
                    if(airport2.getName().equals("Marseille Provence Airport")){
                        planes = world.getPlanesFromToAirport(airport, airport2);
                    }
                }
            }
        }
        printPlanes(planes);
        planes.clear();

        //todo
    }


}
