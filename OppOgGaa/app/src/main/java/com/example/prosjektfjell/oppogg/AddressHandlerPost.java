package com.example.prosjektfjell.oppogg;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarEntry;

/**
 * Created by helt0 on 04.11.2016.
 */

public class AddressHandlerPost {

    private static final String TAG = AddressHandlerPost.class.getSimpleName();

    public AddressHandlerPost(){

    }

    public String makePostCall(String reqURL){

        String request = null;
        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            DataOutputStream dStream = new DataOutputStream(conn.getOutputStream());
            dStream.writeBytes(request);




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return request;
    }

}
