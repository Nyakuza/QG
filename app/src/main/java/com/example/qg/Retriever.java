package com.example.qg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// This send the url request to the server and retrieves the response, returning it as a string to the QuestionActivity

public class Retriever {
    static String stream = null;

    public Retriever() {

    }

    public String getHTTPData(String urlString)
    {

        try {
            URL url = new URL(urlString);

            //opening connection
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            if (urlConnection.getResponseCode() == 200) {

                //reading the input stream
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                //appending each new line to the stream string
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                stream = sb.toString();

                //closing connection
                urlConnection.disconnect();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;

    }
}
