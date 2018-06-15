package FlightLive.elements;

public class Airport {

    private java.lang.String name;
    private java.lang.String icao;
    private java.lang.String country;
    private java.lang.String city;
    private double latitude;
    private double longitude;

    public Airport(java.lang.String name, java.lang.String city, java.lang.String country, java.lang.String icae, double latitude, double longitude) {
        this.name = name;
        this.icao = icao;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getIcao() {
        return icao;
    }

    public void setIcao(java.lang.String icao) {
        this.icao = icao;
    }

    public java.lang.String getCountry() {
        return country;
    }

    public void setCountry(java.lang.String country) {
        this.country = country;
    }

    public java.lang.String getCity() {
        return city;
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
