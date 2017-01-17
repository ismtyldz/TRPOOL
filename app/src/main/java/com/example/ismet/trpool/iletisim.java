package com.example.ismet.trpool;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ismet.trpool.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class iletisim extends Fragment implements View.OnClickListener {
    EditText isim;
    EditText email;
    EditText msj_konu;
    EditText mesaj;
    Button msj_gonder;
    public iletisim() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_iletisim,container,false);
        msj_gonder=(Button) rootView.findViewById(R.id.msj_gonder);
        isim = (EditText) rootView.findViewById(R.id.isim);
        msj_konu = (EditText) rootView.findViewById(R.id.msj_konu);
        mesaj = (EditText) rootView.findViewById(R.id.mesaj);
        email = (EditText) rootView.findViewById(R.id.email);
        msj_gonder.setOnClickListener(this);
        return rootView;
    }
        @Override
        public void onClick (View view){
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://trpoll.kirmizikaya.net/api/iletisim?id="+bilgiler.id.toString()+"&token="+bilgiler.token.toString()+"&isim=" + isim.getText().toString() + "&email=" + email.getText().toString() + "&konu=" + msj_konu.getText().toString() + "&mesaj=" + mesaj.getText().toString();
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

                            if (user_case == "1") {
                                Toast.makeText(getContext(), mesaj.toString(), Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getContext(), MainActivity.class);
                                startActivity(i);


                            } else {
                                Toast.makeText(getContext(), mesaj.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Bu mail adresi ile kayıtlı kullanıcı bulunamadı.", Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    }


