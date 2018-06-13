package FlightLive.parse_elements;

public class Flight {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIcao() {
        return Icao;
    }

    public void setIcao(String icao) {
        Icao = icao;
    }

    public float getSpd() {
        return Spd;
    }

    public void setSpd(float spd) {
        Spd = spd;
    }

    public float getTrak() {
        return Trak;
    }

    public void setTrak(float trak) {
        Trak = trak;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getAlt() {
        return Alt;
    }

    public void setAlt(int alt) {
        Alt = alt;
    }

    public int getLat() {
        return Lat;
    }

    public void setLat(int lat) {
        Lat = lat;
    }

    public int getLong() {
        return Long;
    }

    public void setLong(int aLong) {
        Long = aLong;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String[] getStops() {
        return Stops;
    }

    public void setStops(String[] stops) {
        Stops = stops;
    }

    public String getOp() {
        return Op;
    }

    public void setOp(String op) {
        Op = op;
    }

    public int Id; //identifier of aircraft
    public String Icao;
    public float Spd; //Speed
    public float Trak; //Orientation
    public String Type; //Modele de l'ICAO de l'avion
    public int Alt; //Altitude
    public int Lat; //Latitude
    public int Long;
    public String From; //depart
    public String To; //arrivée
    public String[] Stops; //escales
    public String Op; //opérateur

}