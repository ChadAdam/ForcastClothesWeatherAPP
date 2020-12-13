package com.demo.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.widget.Toast;

import com.demo.weather.R.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PreferenceUtils {
    /*

     * Human readable location string, provided by the API.  Because for styling,
     * "Mountain View" is more recognizable than 94043.
     */
    public static final String PREF_CITY_NAME = "city_name";


    /*
     * In order to uniquely pinpoint the location on the map when we launch the
     * map intent, we store the latitude and longitude.
     */
    public static final String PREF_COORD_LAT = "coord_lat";
    public static final String PREF_COORD_LONG = "coord_long";

    /*
     * Before you implement methods to return your REAL preference for location,
     * we provide some default values to work with.
     */
    private static final String DEFAULT_WEATHER_LOCATION = "New York City";
    private static final double[] DEFAULT_WEATHER_COORDINATES = {40.7128, 74.0060};

    private static final String DEFAULT_MAP_LOCATION =
            "1600 Amphitheatre Parkway, Mountain View, CA 94043";

    /**
     * Helper method to handle setting location details in Preferences (City Name, Latitude,
     * Longitude)
     *
     * @param c        Context used to get the SharedPreferences
     * @param lat      The latitude of the city
     * @param lon      The longitude of the city
     */
    static public void setLocationDetails(Context c, double lat, double lon) {
        SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sp.edit();

        editor.putLong(PREF_COORD_LAT, Double.doubleToRawLongBits(lat));
        editor.putLong(PREF_COORD_LONG, Double.doubleToRawLongBits(lon));
        editor.apply();
    }
    static public void setDefClothes(Context c , int head , int body , int arms , int legs){
        SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("h", head);
        editor.putInt("b", body);
        editor.putInt("a", arms);
        editor.putInt("l", legs);
        editor.apply();

    }
    static public void resetDefClothes(Context c){
        SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("h");
        editor.remove("b");
        editor.remove("a");
        editor.remove("l");
        editor.apply();

    }

    static public String setSeason(Context c , String date){
        int indexStart = date.indexOf(",")+2;
        String month = date.substring(indexStart);
        DateFormat df = new SimpleDateFormat("MMM");
        DateFormat dateFormat = new SimpleDateFormat("M");
        try {
            Date month_date = df.parse(month);
            String newDateString = dateFormat.format(month_date);

           double season =  DateUtils.getSeason(c , Integer.valueOf(newDateString));
            return getStringSeason( c, season);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

public static String getStringSeason(Context c, double season){
    if(season >=1 && season<2){
        Toast.makeText(c , "Fall" , Toast.LENGTH_LONG).show();
        return "Fall";

    }
    else if (season>=2 && season<3){Toast.makeText(c , "Winter" , Toast.LENGTH_LONG).show();
    return "Winter";}
    else if (season>=3 && season<4){Toast.makeText(c , "Spring", Toast.LENGTH_LONG).show();
    return  "Spring";}
    else if (season>=4){Toast.makeText(c , "Summer", Toast.LENGTH_LONG).show();
    return "Summer";}
    return null;
}

    /**
     * Helper method t
     * o handle setting a new location in preferences.  When this happens
     * the database may need to be cleared.
     *
     * @param c               Context used to get the SharedPreferences
     * @param locationSetting The location string used to request updates from the server.
     * @param lat             The latitude of the city
     * @param lon             The longitude of the city
     */
    static public void setLocation(Context c, String locationSetting, double lat, double lon) {
        /** This will be implemented in a future lesson **/
    }

    /**
     * Resets the stored location coordinates.
     *
     * @param c Context used to get the SharedPreferences
     */
    static public void resetLocationCoordinates(Context c) {
        SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sp.edit();

        editor.remove(PREF_COORD_LAT);
        editor.remove(PREF_COORD_LONG);
        editor.apply();
    }



    /**
     * Returns the location currently set in Preferences. The default location this method
     * will return is "94043,USA", which is Mountain View, California. Mountain View is the
     * home of the headquarters of the Googleplex!
     *
     * @param context Context used to get the SharedPreferences
     * @return Location The current user has set in SharedPreferences. Will default to
     * "94043,USA" if SharedPreferences have not been implemented yet.
     */
    public static String getPreferredWeatherLocation(Context context) {
        /** This will be implemented in a future lesson **/
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String val = sharedPreferences.getString(context.getString(string.edit_text_key), DEFAULT_WEATHER_LOCATION);
        return val;
        //return getDefaultWeatherLocation();


    }
    public static ArrayList<Integer> getDefClothes(Context context){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        arr.add(sharedPreferences.getInt("h" , 0));
        arr.add(sharedPreferences.getInt("b" , 0));
        arr.add(sharedPreferences.getInt("a" , 0));
        arr.add(sharedPreferences.getInt("l" , 0));
        return  arr;
    }

    /**
     * Returns true if the user has selected metric temperature display.
     *
     * @param context Context used to get the SharedPreferences
     * @return true If metric display should be used
     */
    public static boolean isMetric(Context context) {
        /** This will be implemented in a future lesson **/
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String val = sharedPreferences.getString(context.getString(string.list_key), "");
        if(val.equals("Cels")) return true;
        return false;
    }

    /**
     * Returns the location coordinates associated with the location.  Note that these coordinates
     * may not be set, which results in (0,0) being returned. (conveniently, 0,0 is in the middle
     * of the ocean off the west coast of Africa)
     *
     * @param context Used to get the SharedPreferences
     * @return An array containing the two coordinate values.
     */
    public static double[] getLocationCoordinates(Context context) {
        SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(context);

        double[] preferredCoordinates = new double[2];

        /*
         * This is a hack we have to resort to since you can't store doubles in SharedPreferences.
         *
         * Double.doubleToLongBits returns an integer corresponding to the bits of the given
         * IEEE 754 double precision value.
         *
         * Double.longBitsToDouble does the opposite, converting a long (that represents a double)
         * into the double itself.
         */
        preferredCoordinates[0] = Double
                .longBitsToDouble(sp.getLong(PREF_COORD_LAT, Double.doubleToRawLongBits(0.0)));
        preferredCoordinates[1] = Double
                .longBitsToDouble(sp.getLong(PREF_COORD_LONG, Double.doubleToRawLongBits(0.0)));

        return preferredCoordinates;
    }

    /**
     * Returns true if the latitude and longitude values are available. The latitude and
     * longitude will not be available until the lesson where the PlacePicker API is taught.
     *
     * @param context used to get the SharedPreferences
     * @return true if lat/long are set
     */
    public static boolean isLocationLatLonAvailable(Context context) {
        SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(context);

        boolean spContainLatitude = sp.contains(PREF_COORD_LAT);
        boolean spContainLongitude = sp.contains(PREF_COORD_LONG);

        boolean spContainBothLatitudeAndLongitude = false;
        if (spContainLatitude && spContainLongitude) {
            spContainBothLatitudeAndLongitude = true;
        }

        return spContainBothLatitudeAndLongitude;
    }

    private static String getDefaultWeatherLocation() {
        /** This will be implemented in a future lesson **/
        return DEFAULT_WEATHER_LOCATION;
    }

    public static double[] getDefaultWeatherCoordinates() {
        /** This will be implemented in a future lesson **/
        return DEFAULT_WEATHER_COORDINATES;
    }
    public static long getLastNotificationTimeInMillis(Context context) {
        /* Key for accessing the time at which Sunshine last displayed a notification */
        String lastNotificationKey = "get_last_prefer";

        /* As usual, we use the default SharedPreferences to access the user's preferences */
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        /*
         * Here, we retrieve the time in milliseconds when the last notification was shown. If
         * SharedPreferences doesn't have a value for lastNotificationKey, we return 0. The reason
         * we return 0 is because we compare the value returned from this method to the current
         * system time. If the difference between the last notification time and the current time
         * is greater than one day, we will show a notification again. When we compare the two
         * values, we subtract the last notification time from the current system time. If the
         * time of the last notification was 0, the difference will always be greater than the
         * number of milliseconds in a day and we will show another notification.
         */
        long lastNotificationTime = sp.getLong(lastNotificationKey, 0);

        return lastNotificationTime;
    }

    public static void saveLastNotificationTime(Context context, long timeOfNotification) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        String lastNotificationKey = "get_last_prefer";
        editor.putLong(lastNotificationKey, timeOfNotification);
        editor.apply();
    }
    public static boolean areNotificationsEnabled(Context context){
        String displayNotificationKey = "enable_notifications";
        boolean shouldDisplayNotificationsByDefault = true;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean shouldDisplayNotifications = sp
                .getBoolean(displayNotificationKey, shouldDisplayNotificationsByDefault);

        return shouldDisplayNotifications;
    }
    public static long getEllapsedTimeSinceLastNotification(Context context) {
        long lastNotificationTimeMillis =
                PreferenceUtils.getLastNotificationTimeInMillis(context);
        long timeSinceLastNotification = System.currentTimeMillis() - lastNotificationTimeMillis;
        return timeSinceLastNotification;
    }
}
