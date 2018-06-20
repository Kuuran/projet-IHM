package FlightLive.elements;

import FlightLive.parse_elements.Flight;
import FlightLive.parse_elements.FlightList;
import FlightLive.parse_elements.Request;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.layout.Pane;
import org.asynchttpclient.*;

import java.util.ArrayList;

public class World {

    private ArrayList<Airport> airports;
    private FlightList flightlist;

    public World(){
        airports = new ArrayList<>();
        flightlist = new FlightList();
    }



    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public void addAirport(Airport airport){
        airports.add(airport);
    }

    public ArrayList<Airport> getAirportsInCity(String city){
        ArrayList<Airport> result = new ArrayList<>();

        for (Airport airport : airports){
            if(airport.getCity().equals(city)){
                result.add(airport);
            }
        }

        return result;
    }

    public ArrayList<String> getCitiesInCountry(String country){
        ArrayList<String> result = new ArrayList<>();

        for (Airport airport : airports){
            if(airport.getCountry().equals(country)){
                result.add(airport.getCity());
            }
        }

        return result;
    }

    public ArrayList<Plane> getPlanesFromToAirport(Airport from, Airport to){
        ArrayList<Plane> planes = new ArrayList<>();
        ArrayList<Plane> buffer = getPlanesFromAirport(from);

        for(Plane plane : buffer){
            if(plane.getArrivee().equals(to.getIcao())){
                planes.add(plane);
            }
        }
        return planes;
    }

    public ArrayList<Plane> getPlanesToAirport(Airport airport){
        ArrayList<Plane> planes = new ArrayList<>();
        Request req = new Request("fAir", airport.getIcao());
        execRequest(req);

        for (Flight flight : flightlist.acList){
            if(flight.getTo().equals(airport.getIcao())){
                planes.add(new Plane(flight));
            }
        }

        flightlist.clear();
        return planes;
    }

    public ArrayList<Plane> getPlanesFromAirport(Airport airport){
        ArrayList<Plane> planes = new ArrayList<>();
        Request req = new Request("fAir", airport.getIcao());
        execRequest(req);

        for (Flight flight : flightlist.acList){
            if(flight.getFrom().equals(airport.getIcao())){
                planes.add(new Plane(flight));
            }
        }

        flightlist.clear();
        return planes;
    }

    private java.lang.String request(Request req){ //Formalisation de la requete
        return ("https://public-api.adsbexchange.com/VirtualRadar/AircraftList.json?"+req.getFiltre()+"Q="+req.getChamp()); //.json?fAirS=XXX

    }

    public void execRequest(Request req){

//Configurer le client http
        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
                .setConnectTimeout(500)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .setKeepAlive(false);
        AsyncHttpClient client = Dsl.asyncHttpClient(clientBuilder);

//Créer une requête de type GET
        BoundRequestBuilder getRequest = client.prepareGet(request(req));


//Exécuter la requête et récupérer le résultat
        getRequest.execute(new AsyncCompletionHandler<Object>() {
            @Override
            public Object onCompleted(Response response) throws Exception {
                System.out.println(response.getResponseBody());

                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //Ignorer les champs inutiles
                flightlist = mapper.readValue(response.getResponseBody(), FlightList.class); //Créer l'objet de plus haut niveau dans le dictionnaire json





                //System.out.println(world.getPlanes()[0].getAltitude());

                return response;
            }

        });
    }

}
