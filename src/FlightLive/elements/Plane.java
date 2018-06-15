package FlightLive.elements;

import FlightLive.parse_elements.Flight;

public class Plane {
    private int id;
    private java.lang.String icao;
    private double altitude;
    private double latitude;
    private double longitude;
    private double speed;
    private double orientation;
    private java.lang.String type;
    private java.lang.String depart;
    private java.lang.String arrivee;
    private java.lang.String[] escale;
    private java.lang.String operator;

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

        int i = 0;
        for (java.lang.String str : flight.Stops){
            this.escale[i] = str;
            i++;
        }

        this.operator = flight.Op;
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

    public java.lang.String getDepart() {
        return depart;
    }

    public void setDepart(java.lang.String depart) {
        this.depart = depart;
    }

    public java.lang.String getArrivee() {
        return arrivee;
    }

    public void setArrivee(java.lang.String arrivee) {
        this.arrivee = arrivee;
    }

    public java.lang.String[] getEscale() {
        return escale;
    }

    public void setEscale(java.lang.String[] escale) {
        this.escale = escale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getIcao() {
        return icao;
    }

    public void setIcao(java.lang.String icao) {
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

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public java.lang.String getOperator() {
        return operator;
    }

    public void setOperator(java.lang.String operator) {
        this.operator = operator;
    }
}
