package FlightLive.elements;

public class Airport {

    private String name;
    private String icao;
    private String country;
    private String city;
    private double latitude;
    private double longitude;


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
