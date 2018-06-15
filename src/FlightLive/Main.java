package FlightLive;

import java.lang.String;

import FlightLive.elements.Airport;
import FlightLive.elements.World;
import FlightLive.parse_elements.FlightList;
import FlightLive.parse_elements.Request;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;
import org.asynchttpclient.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    FlightList flightlist;

    @Override
    public void start(Stage primaryStage) throws Exception {

        flightlist = new FlightList();

        //TimeUnit.SECONDS timeout = 15;

        init_planes();
        while (flightlist.acList.isEmpty()){}

        World world = new World(flightlist);
        parse_airports(world);


       //Exemple pour Aéroport Charles de Gaulle
/*
        java.lang.String ICAO = "ACDG";
        getFlightlist(ICAO);
        flightlist.depuis("From","ACDG"); //Test du filtre
*/








    }


   private void init_planes() {
//Configurer le client http

//Configurer le client http
       DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
               .setConnectTimeout(500)
               .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
               .setKeepAlive(false);
       AsyncHttpClient client = Dsl.asyncHttpClient(clientBuilder);

//Créer une requête de type GET
       BoundRequestBuilder getRequest = client.prepareGet("https://public-api.adsbexchange.com/VirtualRadar/AircraftList.json?fOpQ=Air%20France");


//Exécuter la requête et récupérer le résultat
       getRequest.execute(new AsyncCompletionHandler<Object>() {
           @Override
           public Object onCompleted(Response response) throws Exception {
               System.out.println(response.getResponseBody());

               ObjectMapper mapper = new ObjectMapper();
               mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //Ignorer les champs inutiles
               flightlist = mapper.readValue(response.getResponseBody(), FlightList.class); //Créer l'objet de plus haut niveau dans le dictionnaire json

               return response;
           }
        });
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

    private void getFlightlist(java.lang.String ICAO) {
        Request req = new Request("fICO", ICAO);
        execRequest(req);

    }

    private void parse_airports (World world){

        java.lang.String csvFile = "input/airports.csv";
        BufferedReader br = null;
        java.lang.String line = "";
        java.lang.String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] arrayAirport = line.split(cvsSplitBy);

                Airport airport = new Airport(arrayAirport[0],arrayAirport[1],arrayAirport[2],arrayAirport[3],Double.parseDouble(arrayAirport[4]),Double.parseDouble(arrayAirport[5]));
                world.addAirport(airport);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
