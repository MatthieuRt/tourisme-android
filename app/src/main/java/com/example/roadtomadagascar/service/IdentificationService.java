package com.example.roadtomadagascar.service;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.roadtomadagascar.Domains.PlaceDomain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import util.SessionUser;

public class IdentificationService {

    public void login(String identifiant, String pass, Context context, final LoginCallback loginCallback) {
        SessionUser sessionUser = SessionUser.getSessionUser();
        System.out.println("VOICI L IDENTIFIANT    "+identifiant);
        System.out.println("VOICI LE MOT DE PASSE    "+pass);
        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/user/login"; // Remplacez par l'URL de votre API
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create JSON object for the login credentials
        JSONObject jsonCredentials = new JSONObject();
        try {
            jsonCredentials.put("mail", identifiant);
            jsonCredentials.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Process the response from the server
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                                // Login successful
                                System.out.println(jsonResponse);
                                JSONArray success = jsonResponse.getJSONArray("success");
                                JSONObject userData = success.getJSONObject(0);
                                String userId = userData.getString("_id");
                                String username = userData.getString("nom");
                                List<String > list = new ArrayList<>();
                                JSONArray listFavoris = userData.getJSONArray("favoris");
                                for (int i = 0; i < listFavoris.length(); i++) {
                                    String favori = listFavoris.getString(i);
                                    list.add(favori);
                                }
                                // Save user data to session
                                sessionUser.setIdUser(userId);
                                sessionUser.setUsername(username);
                                sessionUser.setListFavoris(list);
                                // Callback to indicate successful login
                                SessionUser.saveSession(context, identifiant);
                                loginCallback.onSuccess();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Callback to indicate login failure with a general error message
                            loginCallback.onError("An error occurred during login.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Check if the error contains a network response
                if (error.networkResponse != null) {
                    int statusCode = error.networkResponse.statusCode;
                    // Callback to indicate login failure with the error message and status code
                    loginCallback.onError("Pseudo et/ou mot de passe erroné(s), veuillez réessayer");
                    sessionUser.setErroMessage(new String(error.networkResponse.data));

                } else {
                    // Callback to indicate login failure with a general error message
                    loginCallback.onError("An error occurred during login.");
                }
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() {
                return jsonCredentials.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public interface LoginCallback {
        void onSuccess();
        void onError(String errorMessage);
    }

    public void inscription(String mail,String nom,String prenom,String password, Context context,final LogupCallback logupCallback){
        SessionUser sessionUser = SessionUser.getSessionUser();

        String url = "https://back-tourisme-git-main-matthieurt.vercel.app/user/logup"; // Remplacez par l'URL de votre API
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Create JSON object for the logup
        JSONObject jsonLogup = new JSONObject();
        try {
            jsonLogup.put("mail", mail);
            jsonLogup.put("nom", nom);
            jsonLogup.put("prenom", prenom);
            jsonLogup.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Process the response from the server
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            // Login successful
                            System.out.println(jsonResponse);
                            JSONObject userData = jsonResponse.getJSONObject("success");
                            String userId = userData.getString("_id");
                            String username = userData.getString("nom");
                            List<String > list = new ArrayList<>();
                            JSONArray listFavoris = userData.getJSONArray("favoris");
                            for (int i = 0; i < listFavoris.length(); i++) {
                                String favori = listFavoris.getString(i);
                                list.add(favori);
                            }
                            // Save user data to session
                            sessionUser.setIdUser(userId);
                            sessionUser.setUsername(username);
                            sessionUser.setListFavoris(list);
                            // Callback to indicate successful login
                            SessionUser.saveSession(context, userId);
                            logupCallback.onSuccess();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Callback to indicate login failure with a general error message
                            logupCallback.onError("An error occurred during login.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Check if the error contains a network response
                if (error.networkResponse != null) {
                    int statusCode = error.networkResponse.statusCode;
                    // Callback to indicate login failure with the error message and status code
                    logupCallback.onError("Login failed with status code: " + statusCode);
                    sessionUser.setErroMessage(new String(error.networkResponse.data));

                } else {
                    // Callback to indicate login failure with a general error message
                    logupCallback.onError("An error occurred during login.");
                }
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() {
                return jsonLogup.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public interface LogupCallback {
        void onSuccess();
        void onError(String errorMessage);
    }
}
