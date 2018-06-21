package FlightLive.graphics.Util;

import FlightLive.graphics.Util.Math;
import FlightLive.elements.Airport;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

public class AirportPin extends Sphere {

    public AirportPin(Airport airport) {
        super(0.005);
        setMaterial(new PhongMaterial(Color.DARKGREEN));

        Point3D pos = Math.geoCoordToEuclideanCoord(airport.getLatitude(), airport.getLongitude());
        getTransforms().add(new Translate(pos.getX(), pos.getY(), pos.getZ()));
    }
}