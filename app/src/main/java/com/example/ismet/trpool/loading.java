package com.example.ismet.trpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Thread thread= new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (this) {
                        wait(4000);
                    }
                }
                catch (InterruptedException ex){

                }
                finally {
                    Intent intent=new Intent(loading.this,login.class);
                    startActivity(intent);
                    finish();
                }



            }
        };
        thread.start();
    }
}
