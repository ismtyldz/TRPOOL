package com.example.ismet.trpool;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class sifre_update extends Fragment implements View.OnClickListener {
    Button duzenle;
    EditText e_sifre;
    EditText sifre;
    EditText sifre_confirmation;
    public sifre_update() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_sifre_update,container,false);
        duzenle = (Button)  rootView.findViewById(R.id.sifre_updateBtn);
        e_sifre = (EditText) rootView.findViewById(R.id.e_sifre);
        sifre = (EditText) rootView.findViewById(R.id.sifre);
        sifre_confirmation = (EditText) rootView.findViewById(R.id.sifre_confirmation);
        duzenle.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.sifre_updateBtn:
                com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
                String url ="http://trpoll.kirmizikaya.net/api/sifreDegistir?id="+bilgiler.id.toString()+"&token="+bilgiler.token.toString()+"&e_sifre="+e_sifre.getText().toString()+"&sifre="+sifre.getText().toString()+"&sifre_confirmation="+sifre_confirmation.getText().toString();
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
                                        Toast.makeText(getContext(), mesaj.toString(), Toast.LENGTH_SHORT).show();
                                        Intent loginIntent = new Intent(getActivity(), MainActivity.class);
                                        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(loginIntent);
                                        getActivity().finish();
                                    }else{
                                        Toast.makeText(getContext(), mesaj.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Kullanıcı Adı veya Şifre yanlış"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            break;
        }
    }

}
