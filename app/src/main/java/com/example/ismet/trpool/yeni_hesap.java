package com.example.ismet.trpool;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

public class yeni_hesap extends AppCompatActivity {
    Button yenihesap_btn;
    EditText email;
    EditText Adiniz;
    EditText sifre;

    Date dogum;
    EditText meslek;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_hesap);


        yenihesap_btn = (Button) findViewById(R.id.yenihesap_btn);
        email = (EditText) findViewById(R.id.email);
        Adiniz = (EditText) findViewById(R.id.Adiniz);
        sifre = (EditText) findViewById(R.id.sifre);

        meslek = (EditText) findViewById(R.id.meslek);
        yenihesap_btn = (Button) findViewById(R.id.yenihesap_btn);
        yenihesap_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = String.format("http://trpoll.kirmizikaya.net/api/register?isim=" + Adiniz.getText().toString() + "&email=" + email.getText().toString() + "&sifre=" + sifre.getText().toString() + "&sifre_confirmation=" + sifre.getText().toString() + "&meslek=" + meslek.getText().toString());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject data = new JSONObject(response);
                                    JSONObject userDetails = data.getJSONObject("trpoll");
                                    String user_case = userDetails.getString("case");
                                    String mesaj = userDetails.getString("message");

                                    if (user_case=="1"){
                                        Toast.makeText(getApplicationContext(), "kullanııcı kayıt edildi",Toast.LENGTH_SHORT).show();
                                        Intent loginIntent = new Intent(getApplicationContext(), login.class);
                                        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(loginIntent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), mesaj.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Bu mail adresi ile kayıtlı kullanıcı bulunamadı.", Toast.LENGTH_SHORT).show();
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }

        });
                // Request a string response from the provided URL.






    }
}
