package FlightLive.parse_elements;

public class Request {
    public String getFiltre() {
        return filtre;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }

    public String getChamp() {
        return champ;
    }

    public void setChamp(String champ) {
        this.champ = champ;
    }

    private String filtre;
    private String champ;

    public Request(String filtre, String champ){
        this.filtre=filtre;
        this.champ=champ;
    }
}
