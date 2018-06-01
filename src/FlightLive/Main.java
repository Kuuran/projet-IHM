package FlightLive;

import FlightLive.elements.Airport;
import FlightLive.elements.World;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        World world = new World();


    }


    private String parse_line (String line, World world){
        //todo recuperer les infos de la ligne et créer un aéroport avec, puis ajouter l'aeroport a la liste dans world

    }

    private void parse_airports(World world){

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




}
