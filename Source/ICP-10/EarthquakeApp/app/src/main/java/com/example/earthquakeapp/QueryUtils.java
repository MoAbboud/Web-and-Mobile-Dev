package com.example.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        HttpURLConnection urlConnection = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it
            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse)
                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */

            url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime" +
                    "=2021-01-01&endtime=2021-01-02&limit=30"); //1. TO-DO

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = bf.readLine()) != null) {
                jsonResponse += inputLine; //Storing JSON value in string.
                //2. TO-DO
            }

            bf.close();
            JSONObject json = new JSONObject(jsonResponse); //Assigning JSON value from string
            JSONArray features = json.getJSONArray("features"); //Creating the features array

            for(int i = 0; i< features.length(); i++) {
                JSONObject featureElement = features.getJSONObject(i);
                JSONObject featureProp = featureElement.getJSONObject("properties"); //Referencing properties section
                String place = featureProp.getString("place");
                double mag = featureProp.getDouble("mag");
                long time = featureProp.getLong("time");
                String LinkUrl = featureProp.getString("url");

                Earthquake earthquake = new Earthquake(mag, place, time, LinkUrl);
                earthquakes.add(earthquake);
            } //3. TO-DO

        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}
