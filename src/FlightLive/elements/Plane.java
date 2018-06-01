package FlightLive.elements;

public class Plane {
    private double altitude;
    private double latitude;
    private double longitude;
    private Airport depart;
    private Airport arrivee;
    private Airport[] escale;

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

    public Airport getDepart() {
        return depart;
    }

    public void setDepart(Airport depart) {
        this.depart = depart;
    }

    public Airport getArrivee() {
        return arrivee;
    }

    public void setArrivee(Airport arrivee) {
        this.arrivee = arrivee;
    }

    public Airport[] getEscale() {
        return escale;
    }

    public void setEscale(Airport[] escale) {
        this.escale = escale;
    }
}
