package FlightLive.graphics.Util;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import FlightLive.graphics.Util.Math;
import FlightLive.elements.Plane;
import FlightLive.graphics.Util.Earth;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.io.File;
import java.net.URISyntaxException;

public class Planes extends Group {
    private static final String PLANE_MODEL = "plane.obj";
    private static final ObjModelImporter importer = new ObjModelImporter();
/*
    static {
        importer.read(Plane.class.getResource(PLANE_MODEL));
    }
*/


    public Planes(Plane plane) throws URISyntaxException {
        ObjModelImporter importer = new ObjModelImporter();

        File file = new File(getClass().getResource(PLANE_MODEL).toURI());

        importer.read(file);

        getChildren().addAll(importer.getImport());
        importer.close();

        //super(importer.getImport());

        Point3D pos = Math.geoCoordToEuclideanCoord(plane.getLatitude(), plane.getLongitude());
        getTransforms().add(new Translate(pos.getX(), pos.getY(), pos.getZ()));
    }
}