package FlightLive.elements;

import FlightLive.parse_elements.Flight;
import FlightLive.parse_elements.FlightList;
import FlightLive.parse_elements.Request;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.layout.Pane;
import org.asynchttpclient.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class World {

    private ArrayList<Airport> airports;
    private FlightList flightlist;
    private boolean requesting;

    ArrayList<Plane> planes;

    public World(){
        airports = new ArrayList<>();
        flightlist = new FlightList();
        planes = new ArrayList<>();
        requesting = false;
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

    public ArrayList<Plane> getPlanesFromToAirport(Airport from, Airport to) throws InterruptedException {
        ArrayList<Plane> buffer = getPlanesFromAirport(from);
        ArrayList<Plane> result = new ArrayList<>();

        for(Plane plane : buffer){
            if(plane.getArrivee().startsWith(to.getIcao())){
                result.add(plane);
            }
        }
        return result;
    }

    public ArrayList<Plane> getPlanesToAirport(Airport airport) throws InterruptedException {
        planes.clear();
        ArrayList<Plane> result = new ArrayList<>();
        Request req = new Request("fAir", airport.getIcao());
        requesting = true;
        execRequest(req);

        while(requesting){System.out.print("");}

        for (Plane plane : planes) {
            if (plane.getArrivee().startsWith(airport.getIcao())) {
                result.add(plane);
            }
        }
        return result;
    }

    public ArrayList<Plane> getPlanesFromAirport(Airport airport) throws InterruptedException {
        planes.clear();
        ArrayList<Plane> result = new ArrayList<>();
        Request req = new Request("fAir", airport.getIcao());
        requesting = true;
        execRequest(req);

        while(requesting){System.out.print("");}

        for (Plane plane : planes) {
            if (plane.getDepart().startsWith(airport.getIcao())) {
                result.add(plane);
            }
        }
        return result;
    }

    public ArrayList<Plane> getPlanesFromToCity(String from, String to) throws InterruptedException {

        ArrayList<Airport> listeAirport = getAirportsInCity(to);
        ArrayList<Plane> buffer = getPlanesFromCity(from);
        ArrayList<Plane> result = new ArrayList<>();


        for(Plane plane : buffer){
            for(int i=0; i<listeAirport.size();i++)
            if(plane.getArrivee().startsWith(listeAirport.get(i).getIcao())){
                result.add(plane);
            }
        }
        return result;
    }
        public ArrayList<Plane> getPlanesFromCity(String city) throws InterruptedException {
        planes.clear();
        ArrayList<Plane> result = new ArrayList<>();
        ArrayList<Airport> listeAirport = getAirportsInCity(city);
        for (int i=0;i<listeAirport.size();i++) {
            Request req = new Request("fAir", listeAirport.get(i).getIcao());
            requesting = true;
            execRequest(req);
            while (requesting) {
                System.out.print("");
            }

            for (Plane plane : planes) {
                if (plane.getDepart().startsWith(listeAirport.get(i).getIcao())) {
                    result.add(plane);
                }
            }
        }
            return result;


        }
    public ArrayList<Plane> getPlanesToCity(String city) throws InterruptedException {
        planes.clear();
        ArrayList<Plane> result = new ArrayList<>();
        ArrayList<Airport> listeAirport = getAirportsInCity(city);
        for (int i=0;i<listeAirport.size();i++) {
            Request req = new Request("fAir", listeAirport.get(i).getIcao());
            requesting = true;
            execRequest(req);
            while (requesting) {
                System.out.print("");
            }

            for (Plane plane : planes) {
                if (plane.getArrivee().startsWith(listeAirport.get(i).getIcao())) {
                    result.add(plane);
                }
            }
        }
        return result;


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
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //Ignorer les champs inutiles
                flightlist = mapper.readValue(response.getResponseBody(), FlightList.class); //Créer l'objet de plus haut niveau dans le dictionnaire json

                for(Flight flight : flightlist.acList){
                    planes.add(new Plane(flight));
                }
                requesting = false;
                flightlist.clear();
                return response;
            }

        });
    }

}
