package com.example.oskarnoko.einzelbeispielse2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Button btnCalculate;
    private EditText txtInput;
    private TextView txtOutput;

    public static String matrikelnr;
    public static String receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.buttonOnFirstScreen);
        btnCalculate = (Button) findViewById(R.id.buttonCalculation);
        txtInput = (EditText) findViewById(R.id.mnrTextField);
        txtOutput =  (TextView) findViewById(R.id.textViewServerRespond);



        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        matrikelnr = txtInput.getText().toString();

                        runOnUiThread(new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Client c = new Client();
                                Thread t = new Thread(c);
                                t.start();
                                try {
                                    t.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                txtOutput.setText(receiver);
                            }
                        }));
                    }
                }
        );




    }




}
