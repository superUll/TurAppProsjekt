package com.example.prosjektfjell.oppogg;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by oleandreheggdal on 29.10.2016.
 */

public class AddressHandler {

    private static final String TAG = AddressHandler.class.getSimpleName();

    private String charset = "UTF-8";
    private HttpURLConnection httpURLConnection;
    private DataOutputStream dataOutputStream;
    private StringBuilder result;
    private URL urlObj;
    private JSONArray jsonArray = null;
    private StringBuilder stringBuilder;
    private String paramsString;



    public AddressHandler() {
    }

    public JSONArray makeServiceCall(String url, String method, ArrayList<HashMap<String, String>> paramsl) {
        //String response = null;
        stringBuilder = new StringBuilder();
//        int i = 0;
//        for (String key : params.get(i).keySet()) {
//            try {
//                if (i != 0) {
//                    stringBuilder.append("&");
//                }
//                stringBuilder.append(key).append("=").append(URLEncoder.encode(params.get(i).get(key), charset));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            i++;
//        }
        if (method.equals("GET")) {
            if (stringBuilder.length() != 0) {
                url += "?" + stringBuilder.toString();
            }
            try {
                urlObj = new URL(url);
                httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Accept-Charset", charset);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.connect();
                // read the response
                //InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                //response = convertStreamToString(in);

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }

        } else if (method.equals("POST")) {
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


            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
        try{
            //receive the response from the server
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            result = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
                result.append(line);
            }
            Log.d("Json Parer", "result: " + result.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        // try to pase the string to a JSON object
        try{
            jsonArray = new JSONArray(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString() );
        }
        return jsonArray;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
