package com.example.roadtomadagascar.service;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import util.SessionUser;

public class IdentificationService {

    public void login(String identifiant, String pass, Context context, final LoginCallback loginCallback) {
        SessionUser sessionUser = SessionUser.getSessionUser();

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
                                JSONObject userData = jsonResponse.getJSONObject("data");
                                String userId = userData.getString("_id");
                                String username = userData.getString("username");
                                // Save user data to session
                                sessionUser.setIdUser(userId);
                                sessionUser.setUsername(username);
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
                    loginCallback.onError("Login failed with status code: " + statusCode);
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
}
