package FlightLive.elements;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.net.MalformedURLException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class World {

    private ArrayList<Airport> airports;
    private Plane[] planes;
   // private String lastDv;

    public World(){
        airports = new ArrayList<>();
        //planes = new ArrayList<>();
    }



    public ArrayList<Airport> getAirports() {
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
