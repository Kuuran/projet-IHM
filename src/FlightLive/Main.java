package FlightLive;

import FlightLive.elements.Airport;
import FlightLive.elements.World;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        World world = new World();
        parse_airports(world);


    }


    private void parse_airports (World world){
        //todo recuperer les infos de la ligne et créer un aéroport avec, puis ajouter l'aeroport a la liste dans world


        String csvFile = "input/airports.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] arrayAirport = line.split(cvsSplitBy);
                System.out.println(line);

                Airport airport = new Airport(arrayAirport[0],arrayAirport[1],arrayAirport[2],arrayAirport[3],Double.parseDouble(arrayAirport[4]),Double.parseDouble(arrayAirport[5]));
                System.out.println(airport.getCity());
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

    /* private void parse_airports(World world){

        //todo modifier pour que ça lise bien et envoyer la ligne lue a parse_line()

        try {
            FileReader file = new FileReader("name_fichier.ext");
            BufferedReader bufRead = new BufferedReader(file);

            String line = bufRead.readLine();
            while ( line != null) {
                String[] array = line.split(",");

                int id = Integer.parseInt(array[0]);
                float val = Float.parseFloat(array[6]);

                line = bufRead.readLine();
            }

            bufRead.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

*/


}
