package com.example.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngo=findViewById(R.id.btngo);

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText in1 =(EditText) findViewById(R.id.editText);
                Intent myIntnet =new Intent(getApplicationContext(),output.class);
                myIntnet.putExtra("Id",Integer.parseInt(in1.getText().toString()));

                startActivity(myIntnet);
            }

        });
    }



}
