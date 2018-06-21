package FlightLive.graphics.Util;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Formatter;

public class Earth extends Group {
    private static final String EARTH_MODEL = "earth.obj";

    private static final String DIFFUSE_MAP = "Earth.Diffuse.July.jpg";
    // private static final String NIGHT_DIFFUSE_MAP = "Earth.Night.Diffuse_21600x10800.jpg";
    private static final String SPECULAR_MAP = "Earth.Specular.jpg";

    public static final double EarthRadius = 1;

    public Earth() throws IOException, URISyntaxException {
        ObjModelImporter importer = new ObjModelImporter();

        /*
        InputStream inputStream = getClass().getResourceAsStream(EARTH_MODEL);
        final byte[] modelTemp = inputStream.readAllBytes();
        final File modelTempFile = File.createTempFile("EARTH_MODEL",".tmp");
        OutputStream outputStream = new FileOutputStream(modelTempFile);
        outputStream.write(modelTemp, 0, modelTemp.length);
        /* Pour load depuis le jar */

        System.out.println(getClass().getResource(EARTH_MODEL).toURI());
        File file = new File(getClass().getResource(EARTH_MODEL).toURI());

        importer.read(file);

        getChildren().addAll(importer.getImport());
        importer.close();
    }

}