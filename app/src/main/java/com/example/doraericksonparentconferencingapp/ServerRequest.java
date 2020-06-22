package com.example.doraericksonparentconferencingapp;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

/**
 * <h3>ServerRequest class</h3>
 * Defines the main path to the server and a function used to handel server
 * requests.
 * References:
 *      https://www.programcreek.com/java-api-examples/javax.net.ssl.HttpsURLConnection
 *          Brought URLConnection methods such as .setConnectTimeout() to my attention.
 *      https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 *          This website discusses how to use URL objects and how to handel http requests.
 *
 * @author Caleb Fullmer
 * @since June 16, 2020
 * @version 1.0
 */
public class ServerRequest {
    //Put main path to server here. Should direct request to head server file.
    // This is just a mock server. It just ensures that the server requests connects to
    // something.
    private final String SERVER = "https://run.mocky.io/v3/1c5c80fb-ca58-4283-a3e8-adeb44f98ce5";


    /**
     * <h3>ServerRequest()</h3>
     * Default Constructor, does nothing.
     */
    public ServerRequest() { }


    /**
     * <h3>request(String filePath, String variableKeys)</h3>
     * Handles server requests to the main webserver. Returns the requested JSON string.
     * If an error occurred or the return string was null or empty, null is returned.
     * @param filePath (Type: String, the path from the server root to the file)
     * @param variableKeys (Type: String, the key value pairs to send to the server)
     * @return jsonStr (Type: String, the requested Json in string format)
     */
    public String request(String filePath, String variableKeys) {
        if ((filePath == null) || filePath.equals("")) {
            return null;
        }

        try {
            String charset = null;
            String jsonResponse = "";
            String responseHeader = "";
            InputStream response = null;
            //Preferably this will be a secure HttpsURLConnection if prototype is accepted.
            URLConnection connection = new URL(SERVER + filePath + variableKeys).openConnection();

            //Connection will timeout if it takes more than 45 seconds.
            connection.setConnectTimeout(45000);
            connection.connect();
            response = connection.getInputStream();

            //Get the charset used by the API.
            //Copied from assignment 06 basic weather app, submission.
            for (String index : responseHeader.split(" ")) {
                if (index.startsWith("charset=")) {
                    charset = index.split("=", 2)[1];
                    break;
                }
            }

            if ((charset == null) || (charset.equals(""))) {
                charset = "UTF-8";
            }

            //Reads the response data into a string.
            //Copied from assignment 06 basic weather app, submission.
            BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset));
            for (String newline = reader.readLine(); newline != null; ) {
                jsonResponse += newline;
                if (newline != null) {
                    newline = reader.readLine();
                }
            }

            //Just a test to ensure the connection to the mock server was successfull.
            if (!jsonResponse.equals("This just returns if the response worked correctly.")) {
                Log.d("ServerRequest", "Incorrect response");
                throw new Exception("Unexpected Server Response");
            }

            return jsonResponse;
        } catch (MalformedURLException e) {
            Log.e("ServerRequest.request()", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("ServerRequest.request()", e.getMessage());
            return null;
        } catch (Exception e) {
            Log.e("ServerRequest.request()", e.getMessage());
            return null;
        }
    }
}