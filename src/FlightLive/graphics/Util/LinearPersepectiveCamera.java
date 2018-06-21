package FlightLive.graphics.Util;

import FlightLive.graphics.Util.Earth;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class LinearPersepectiveCamera extends PerspectiveCamera {
    private Rotate rx = new Rotate(); /* Latitude */
    private Rotate ry = new Rotate(); /* Longitude */
    private Translate tz = new Translate(); /* Altitude */

    public LinearPersepectiveCamera() {
        super(true);

        rx.setAxis(Rotate.X_AXIS);
        ry.setAxis(Rotate.Y_AXIS);

        tz.setZ(-Earth.EarthRadius - 2.91);

        getTransforms().addAll(rx, ry, tz);
    }

    public void setZ(double distance) {
        tz.setZ(-Earth.EarthRadius - distance);
    }

    public void setAngleX(double angle) {
        rx.setAngle(angle);
    }

    public void setAngleY(double angle) {
        ry.setAngle(angle);
    }

    public void resetAngle() {
        rx.setAngle(0);
        ry.setAngle(0);
    }
}