package FlightLive;

import java.lang.String;

import FlightLive.elements.Airport;
import FlightLive.elements.World;
import FlightLive.graphics.Bash;
import FlightLive.graphics.Graphics;
import FlightLive.parse_elements.FlightList;
import FlightLive.parse_elements.Request;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.asynchttpclient.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

/*
        //bash test
        World world = new World();
        world.parse_airports();
        Bash bash = new Bash(world);
        bash.testPrint();
        exit(0);*/


        Parent root = FXMLLoader.load(Graphics.class.getResource("graphics.fxml"));

        primaryStage.setTitle("Flight Live");
        primaryStage.setScene(new Scene(root, 800, 500, true, SceneAntialiasing.BALANCED));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }



}
