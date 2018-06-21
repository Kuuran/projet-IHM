package FlightLive.graphics.Util;

import java.util.Formatter;

public class GeoCoordinateFormatter {

    static public String format(double latitude, double longitude) {
        String formatString = "%d° %02d\' %02.2f\"";
        if (latitude < 0)
            formatString += "O";
        else if (latitude > 0)
            formatString  += "E";

        formatString += " %d° %02d\' %02.2f\"";

        if (longitude < 0)
            formatString += "S";
        else if (longitude > 0)
            formatString += "N";

        double latDegrees = java.lang.Math.abs(latitude);
        double latMinutes = (latDegrees - (int) latDegrees) * 60;
        double latSecondes = (latMinutes - (int) latMinutes) * 60;
        double lonDegrees = java.lang.Math.abs(longitude);
        double lonMinutes = (lonDegrees - (int) lonDegrees) * 60;
        double lonSecondes = (lonMinutes - (int) lonMinutes) * 60;

        Formatter formatter = new Formatter().format(formatString,
                (int) latDegrees, (int) latMinutes, latSecondes,
                (int) lonDegrees, (int) lonMinutes, lonSecondes);
        return formatter.toString();
    }

}