package FlightLive.elements;

import FlightLive.parse_elements.Flight;
import java.lang.String;
import java.util.ArrayList;

public class Plane {
    private int id;
    private String icao;
    private double altitude;
    private double latitude;
    private double longitude;
    private double speed;
    private double orientation;
    private String type;
    private String depart;
    private String arrivee;
    private ArrayList<String> escale;
    private String operator;

    public Plane (Flight flight){
        this.id = flight.Id;
        this.icao = flight.Icao;
        this.altitude = flight.Alt;
        this.latitude = flight.Lat;
        this.longitude = flight.Long;
        this.speed = flight.Spd;
        this.orientation = flight.Trak;
        this.type = flight.Type;
        this.depart = flight.From;
        this.arrivee = flight.To;

        if(flight.Stops != null) {
            for (java.lang.String str : flight.Stops) {
                if(!str.isEmpty()) {
                   // escale.add(str);
                }
            }
        }

        this.operator = flight.Op;
    }

    @Override
    public String toString(){
        return String.format("%s  ;  %s  ;  %s  :  %s  :  %s.", id, depart, arrivee, type, operator);
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public ArrayList<String> getEscale() {
        return escale;
    }

    public void setEscale(ArrayList<String> escale) {
        this.escale = escale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
