package FlightLive;

import FlightLive.elements.Airport;
import FlightLive.elements.World;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;
import org.asynchttpclient.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        World world = new World();
        //init_planes();
        parse_airports(world);


    }

    /* todo corriger
    private void init_planes() {


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
                World world = mapper.readValue(response.getResponseBody(), World.class); //Créer l'objet de plus haut niveau dans le dictionnaire json

                System.out.println(world.getPlanes()[0].getAltitude());

                return response;
            }
        });

        //return world;
    }
*/

    private void parse_airports (World world){

        String csvFile = "input/airports.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

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
