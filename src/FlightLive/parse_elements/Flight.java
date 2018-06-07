package FlightLive.parse_elements;

public class Flight {
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