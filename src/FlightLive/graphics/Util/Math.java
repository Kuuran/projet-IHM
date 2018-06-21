package FlightLive.graphics.Util;

import javafx.geometry.Point3D;

public class Math {
    public static Point3D geoCoordToEuclideanCoord(double lat, double lon) {
        double lat_ = java.lang.Math.toRadians(lat - 0.2);
        double lon_ = java.lang.Math.toRadians(lon + 2.8);

        double x = java.lang.Math.cos(lat_) * java.lang.Math.cos(lon_);
        double y = java.lang.Math.cos(lat_) * java.lang.Math.sin(lon_);
        double z = java.lang.Math.sin(lat_);

        return new Point3D(-y, -z, x);
    }

}