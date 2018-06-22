package FlightLive.graphics;

import FlightLive.elements.Airport;
import FlightLive.elements.Plane;
import FlightLive.elements.World;
import FlightLive.graphics.Util.*;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Translate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Graphics {
    private double rotationSensitivityX = 0.49, rotationSensitivityY = 0.35, zoomSensitivity = 1.0;
    private double lastMouseX, lastMouseY;
    private double altitude = 2, latitude = 0 , longitude = 0;

    @FXML
    private Label coordinatesLabel;

    @FXML private AnchorPane container3D;
    private SubScene subScene;

    @FXML private javafx.scene.control.ComboBox<String> fromCountry;
    @FXML private javafx.scene.control.ComboBox<String> fromCity;
    @FXML private javafx.scene.control.ComboBox<String> fromAirport;
    @FXML private javafx.scene.control.ComboBox<String> toCountry;
    @FXML private javafx.scene.control.ComboBox<String> toCity;
    @FXML private javafx.scene.control.ComboBox<String> toAirport;
    @FXML private javafx.scene.control.Button launchButton;
    @FXML private javafx.scene.control.ListView<Plane> listView;
    @FXML private TextFlow textFlow;

    private Earth earth;
    private AirportPin[] airportsPins;
    private Group planesGroup = new Group();
    private Planes[] planes;
    private ArrayList<Plane> planess = new ArrayList<>();
    private World world;

    private void displayAiports(ArrayList<Airport> airports, Earth earth) {
        airportsPins = new AirportPin[airports.size()];
        for(int i = 0; i < airports.size(); i++)
            earth.getChildren().add(new AirportPin(airports.get(i)));
    }

    private void displayPlane() throws URISyntaxException {
        planesGroup.getChildren().clear();
        this.planes = new Planes[world.getPlanes().size()];
        for(Plane plane : planess)
            planesGroup.getChildren().add(new Planes(plane));

        listView.getItems().clear();
        listView.getItems().addAll(world.getPlanes());
    }

    @FXML
    public void initialize() throws InterruptedException {

        Group root = new Group();
        subScene = new SubScene(root, 500, 500, true, SceneAntialiasing.BALANCED);
        container3D.getChildren().add(new Region() {
            { getChildren().add(subScene); }

            @Override
            protected void layoutChildren() {
                super.layoutChildren();

                // Redimensionne la sous-scene lorsque le parent change de taille.
                final double width = getWidth();
                final double height = getHeight();
                final Insets insets = getInsets();
                final double contentX = insets.getLeft();
                final double contentY = insets.getTop();
                final double contentW = width - (insets.getLeft() + insets.getRight());
                final double contentH = height - (insets.getTop() + insets.getBottom());
                subScene.relocate(contentX, contentY);
                subScene.setWidth(contentW);
                subScene.setHeight(contentH);
            }
        });

        world = new World();
        world.parse_airports();

        /* Earth */

        try {
            earth = new Earth();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        displayAiports(world.getAirports(), earth);

        LinearPersepectiveCamera camera = new LinearPersepectiveCamera();

        Group universe = new Group();
        universe.getChildren().addAll(earth, camera);

        root.getChildren().add(universe);

        subScene.setOnMousePressed(event -> {
            lastMouseX = event.getX();
            lastMouseY = event.getY();
            if (event.isSecondaryButtonDown()) {
                double zoneRadius = 1;
                Sphere searchZone = new Sphere(zoneRadius);
                searchZone.setMaterial(new PhongMaterial(new Color(1, 0, 0, 0.25)));
                searchZone.getTransforms().add(new Translate(0, 0, -Earth.EarthRadius));
                // latitudeRotation.getChildren().add(searchZone);
                // root.getChildren().add(searchZone);
            }
        });
        subScene.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                latitude -= (lastMouseX - event.getX()) * rotationSensitivityX * 0.1;
                longitude -= (lastMouseY - event.getY()) * rotationSensitivityY * 0.1;
                camera.setAngleX(longitude);
                camera.setAngleY(latitude);
            }
            lastMouseX = event.getX();
            lastMouseY = event.getY();
            coordinatesLabel.setText(GeoCoordinateFormatter.format(latitude, longitude) + '\n' + (int) (altitude + Earth.EarthRadius) * 6371);
        });
        subScene.setOnScroll(event -> {
            if (event.getDeltaY() < 0) {
                if (altitude + zoomSensitivity * 0.1 < 6.27) /* Limite 40 000 km */
                    altitude += zoomSensitivity * 0.1;
            } else if (event.getDeltaY() > 0) {
                if (altitude - zoomSensitivity * 0.1 > 0.1) /* Limite 100 km */
                    altitude -= zoomSensitivity * 0.1;
            }
            camera.setZ(altitude);
            coordinatesLabel.setText(GeoCoordinateFormatter.format(latitude, longitude) + '\n' + (int) (altitude + Earth.EarthRadius) * 6371);
        });

        initializeElements();

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (now % 1000 == 0) {
                    if (!world.isRequesting()) {
                        try {
                            displayPlane();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        subScene.setCamera(camera);
        subScene.setFill(Color.BLACK);

        coordinatesLabel.toFront();
    }

    private void initializeElements() {
        ObservableList<String> countryObservableList = FXCollections.observableArrayList();
        countryObservableList.addAll(world.getCountries());

        fromCountry.setItems(countryObservableList);
        toCountry.setItems(countryObservableList);

        fromAirport.setValue(null);
        toAirport.setValue(null);


        fromCountry.setOnAction(event -> {
            fromCity.getItems().clear();
            for (String city: world.getCitiesInCountry(fromCountry.getValue()))
                    fromCity.getItems().add(city);
            fromCity.setDisable(false);
        });
        toCountry.setOnAction(event -> {
            toCity.getItems().clear();
            for (String city: world.getCitiesInCountry(toCountry.getValue()))
                toCity.getItems().add(city);
            toCity.setDisable(false);
        });

        fromCity.setOnAction(event -> {
            for (Airport airport: world.getAirportsInCity(fromCity.getValue()))
                    fromAirport.getItems().add(airport.getName());
            fromAirport.setDisable(false);
        });
        toCity.setOnAction(event -> {
            toAirport.getItems().clear();
            for (Airport airport: world.getAirportsInCity(toCity.getValue()))
                toAirport.getItems().add(airport.getName());
            toAirport.setDisable(false);
        });

        listView.getSelectionModel().selectedItemProperty().addListener(observable -> {
            Plane plane = listView.getSelectionModel().getSelectedItem();
            textFlow.getChildren().clear();
            textFlow.getChildren().add(new Text(plane.extendedToString()));
        });


        launchButton.setOnAction(event -> {
            planess.clear();
            textFlow.getChildren().clear();
            textFlow.getChildren().add(new Text("Requette en cours..."));
            Airport from = null, to = null;
            if(!(fromAirport.getValue() == null)) {
                for (Airport a : world.getAirports()) {
                    if (a.getName().startsWith(fromAirport.getValue().split(" ")[0])) {
                        from = a;
                    }
                }
            }
            if(!(toAirport.getValue() == null)) {
                for (Airport a : world.getAirports()) {
                    if (a.getName().startsWith(toAirport.getValue().split(" ")[0])) {
                        to = a;
                    }
                }
            }

            if(from != null && to != null){
                try {
                    System.out.println("exec fromto" + from.getName() + to.getName());
                    planess.addAll(world.getPlanesFromToAirport(from, to));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(from != null){
                try {
                    System.out.println("exec from " + from.getName());
                    planess.addAll(world.getPlanesFromAirport(from));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(to != null){
                try {
                    System.out.println("exec to" + to.getName());
                    planess.addAll(world.getPlanesToAirport(to));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}

