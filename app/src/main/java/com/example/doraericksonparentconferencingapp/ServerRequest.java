package com.example.doraericksonparentconferencingapp;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
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
    private final String SERVER = "";


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
            HttpsURLConnection connection = new HttpsURLConnection(new URL(SERVER + filePath + variableKeys)) {
                @Override
                public String getCipherSuite() {
                    return null;
                }

                @Override
                public Certificate[] getLocalCertificates() {
                    return new Certificate[0];
                }

                @Override
                public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
                    return new Certificate[0];
                }

                @Override
                public void disconnect() {

                }

                @Override
                public boolean usingProxy() {
                    return false;
                }

                @Override
                public void connect() throws IOException {

                }
            };

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

            return jsonResponse;
        } catch (MalformedURLException e) {
            Log.e("Error in ServerRequest.request()", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error in ServerRequest.request()", e.getMessage());
            return null;
        } catch (Exception e) {
            Log.e("Error in ServerRequest.request()", e.getMessage());
            return null;
        }
    }
}