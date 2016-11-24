package com.example.prosjektfjell.oppogg;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;


/**
 * Created by helt0 on 04.11.2016.
 */

public class AddressHandlerPost {

    private static final String TAG = AddressHandlerPost.class.getSimpleName();

    private String charset = "UTF-8";
    private HttpURLConnection httpURLConnection;
    private DataOutputStream dataOutputStream;
    private StringBuilder result;
    private URL urlObj;
    private JSONObject jsonObject = null;
    private StringBuilder stringBuilder;
    private String paramsString;


    public JSONObject makeHttpRequest(String url, String method,HashMap<String, String> params) {

        stringBuilder = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0) {
                    stringBuilder.append("&");
                }

                stringBuilder.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), charset));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }


        if (method.equals("POST")) {
            // request method is POST
            try {

                urlObj = new URL(url);

                httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Accept-Charset", charset);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.connect();

                paramsString = stringBuilder.toString();

                dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(paramsString);
                dataOutputStream.flush();
                dataOutputStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            //Receive the response from the server
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            result = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.d("JSON Parser", "result: " + result.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


        httpURLConnection.disconnect();

        // try parse the string to a JSON object
        try {

            jsonObject = new JSONObject(result.toString());

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON Object
        return jsonObject;

    }
}
