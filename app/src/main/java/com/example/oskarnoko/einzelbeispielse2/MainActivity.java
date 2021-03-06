package com.example.oskarnoko.einzelbeispielse2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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


        btnCalculate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        matrikelnr = txtInput.getText().toString();
                        txtOutput.setText(doCalculation(matrikelnr));
                    }
                }
        );

    }

    public String doCalculation(String matrikelnr){
        String output = "";

        int [] numbers = new int [10];

        for(int i = 0; i <matrikelnr.length(); i++){
            if(matrikelnr.charAt(i) == '0'){
                numbers[0]++;
            }else if(matrikelnr.charAt(i) == '1'){
                numbers[1]++;
            }else if(matrikelnr.charAt(i) == '2'){
                numbers[2]++;
            }else if(matrikelnr.charAt(i) == '3'){
                numbers[3]++;
            }else if(matrikelnr.charAt(i) == '4'){
                numbers[4]++;
            }else if(matrikelnr.charAt(i) == '5'){
                numbers[5]++;
            }else if(matrikelnr.charAt(i) == '6'){
                numbers[6]++;
            }else if(matrikelnr.charAt(i) == '7'){
                numbers[7]++;
            }else if(matrikelnr.charAt(i) == '8'){
                numbers[8]++;
            }else if(matrikelnr.charAt(i) == '9'){
                numbers[9]++;
            }
        }

        for(int i = 0; i<numbers.length; i+=2){
            while(numbers[i]>0){
                numbers[i]--;
                output+= i;
            }
        }
        for(int i = 1; i<numbers.length; i+=2){
            while(numbers[i]>0){
                numbers[i]--;
                output+= i;
            }
        }

        return output;
    }


}
