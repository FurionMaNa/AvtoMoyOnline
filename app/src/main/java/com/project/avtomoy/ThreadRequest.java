package com.project.avtomoy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ThreadRequest extends AsyncTask<String,Void,String> {

    private String URL_API="https://www.avtomoy.online/api";
    byte[] data = null;
    InputStream is = null;
    String resultString = null;
    public ProgressDialog dialog;
    Context ctx;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            switch (strings[0]){
                case "login":
                    URL_API+="/login";
                    break;
                case "forget-password":
                    URL_API+="/forget-password";
                    break;
                case "registration":
                    URL_API+="/registration";
                    break;
                case "my-cars-page":
                    URL_API+="/my-cars-page";
                    break;
                case "history-page":
                    URL_API+="/history-page?"+strings[2];
                    break;
                case "get-services":
                    URL_API+="/get-services";
                    break;
                case "get-services2":
                    URL_API+="/get-services?carWashId="+strings[2];
                    break;
                case "get-add-services":
                    URL_API+="/get-add-services";
                    break;
                case "get-add-services2":
                    URL_API+="/get-add-services?carWashId="+strings[2];
                    break;
                case "get-time-and-price":
                    URL_API+="/get-time-and-price?services="+strings[2];
                    break;
                case "get-select-map-periods":
                    URL_API+="/get-select-map-periods?"+strings[2];
                    break;
                case "get-avaible-times":
                    URL_API+="/get-available-times?"+strings[2];
                    break;
                case "get-car-washes-by-filter":
                    URL_API+="/get-car-washes-by-filter?"+strings[2];
                    break;
                case "signup-car-wash":
                    URL_API+="/signup-car-wash";
                    break;
                case "carwash-contacts-page":
                    URL_API+="/carwash-contacts-page?"+strings[2];
                    break;
                case "carwash-contacts-page2":
                    URL_API+="/carwash-contacts-page";
                    break;
                case "chat-inbox-page":
                    URL_API+="/chat-inbox-page";
                    break;
                case "remove-transport":
                    URL_API+="/remove-transport";
                    break;
                case "add-transport":
                    URL_API+="/add-transport";
                    break;
                case "change-transport":
                    URL_API+="/change-transport";
                    break;
                case "carwash-sales-page":
                    URL_API+="/carwash-sales-page?"+strings[2];
                    break;
                case "is-free":
                    URL_API+="/is-free";
                    break;
                case "carwash-comfort-page":
                    URL_API+="/carwash-comfort-page?"+strings[2];
                    break;
                case "get-available-dates":
                    URL_API+="/get-available-dates?carWashId="+strings[2];
                    break;
                case "change-settings":
                    URL_API+="/change-settings";
                    break;
                case "get-signed-record":
                    URL_API+="/get-signed-record";
                    break;
                case "cancel-record":
                    URL_API+="/cancel-record";
                    break;
                case "complete-record":
                    URL_API+="/complete-record";
                    break;
                case "about-car-wash":
                    URL_API+="/about-car-wash?"+strings[2];
                    break;
                case "advert-viewed":
                    URL_API+="/advert-viewed";
                    break;
                case "get-mailing":
                    URL_API+="/get-mailing";
                    break;
                case "email-exist":
                    URL_API+="/email-exist";
                    break;
                case "settings-page":
                    URL_API+="/settings-page";
                    break;
            }
            URL url = new URL(URL_API.replace(" ", ""));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout (500000);
            if((strings[0].equals("settings-page"))||(strings[0].equals("my-cars-page"))||(strings[0].equals("get-signed-record"))||(strings[0].equals("history-page"))||(strings[0].equals("get-services2"))||(strings[0].equals("get-add-services2"))||(strings[0].equals("get-time-and-price"))||(strings[0].equals("get-select-map-periods"))||(strings[0].equals("get-avaible-times"))||(strings[0].equals("get-car-washes-by-filter"))||(strings[0].equals("carwash-contacts-page2"))||(strings[0].equals("chat-inbox-page"))||(strings[0].equals("is-free"))||(strings[0].equals("carwash-contacts-page"))||(strings[0].equals("carwash-sales-page"))||(strings[0].equals("carwash-comfort-page"))||(strings[0].equals("get-available-dates"))||(strings[0].equals("about-car-wash"))||(strings[0].equals("get-mailing"))){

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + strings[1]);
                conn.setRequestProperty("Content-Length", "" );
            }else {
                if((strings[0].equals("get-services"))||(strings[0].equals("get-add-services"))||(strings[0].equals("signup-car-wash"))||(strings[0].equals("remove-transport"))||(strings[0].equals("add-transport"))||(strings[0].equals("change-transport"))||(strings[0].equals("change-settings"))||(strings[0].equals("cancel-record"))||(strings[0].equals("complete-record"))||(strings[0].equals("advert-viewed"))){
                    conn.setRequestProperty("Authorization", "Bearer " + strings[1]);
                    conn.setRequestProperty("Content-Length", "" + Integer.toString(strings[2].getBytes().length));
                }else {
                    conn.setRequestProperty("Content-Length", "" + Integer.toString(strings[1].getBytes().length));
                }
            }
            conn.setReadTimeout (500000);
            OutputStream os = conn.getOutputStream();
            if((strings[0].equals("get-services"))||(strings[0].equals("get-add-services"))||(strings[0].equals("signup-car-wash"))||(strings[0].equals("remove-transport"))||(strings[0].equals("add-transport"))||(strings[0].equals("change-transport"))||(strings[0].equals("change-settings"))||(strings[0].equals("cancel-record"))||(strings[0].equals("complete-record"))||(strings[0].equals("advert-viewed"))){
                data = strings[2].getBytes("UTF-8");
            }else {
                data = strings[1].getBytes("UTF-8");
            }
            os.write(data);
            data = null;
            conn.connect();
            int responseCode= conn.getResponseCode();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if(strings[0].equals("get-signed-record")) {
                Log.i("MyLog","get-signed-record");
            }
            is =  new BufferedInputStream(conn.getInputStream());
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            data = baos.toByteArray();
            resultString = new String(data, "UTF-8");
            return resultString;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
