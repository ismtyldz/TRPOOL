package com.example.ismet.trpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class sifre_unuttum extends AppCompatActivity {
    EditText mail;
    Button sifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_unuttum);

        mail = (AutoCompleteTextView) findViewById(R.id.email);
        sifre = (Button) findViewById(R.id.sifrebtn);
        sifre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://trpoll.kirmizikaya.net/api/forgetpassword?mail="+mail.getText().toString();
                // Request a string response from the provided URL.
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
                                        Toast.makeText(getApplicationContext(), mesaj.toString(), Toast.LENGTH_SHORT).show();
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



    }
}
