package com.example.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

public class output extends AppCompatActivity {

    TextView out1,out2,out3,out4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        out1=findViewById(R.id.txt1);
        out2=findViewById(R.id.txt2);
        out3=findViewById(R.id.txt3);
        out4=findViewById(R.id.txt4);

        new MyTask().execute();

    }
    private class MyTask extends AsyncTask<Void,Void,Void>{
        String ou1,ou2,ou3,ou4;

        @Override
        protected Void doInBackground(Void... params) {

            URL urL =null;
            Intent mynewIntent = getIntent();

            int InfoReceiveId= mynewIntent.getIntExtra("Id",99);

            try{
                urL = new URL("http://192.168.56.1:8080/Test/mad/mobile/singleEmployee&"+InfoReceiveId);
                HttpURLConnection client = null;
                client=(HttpURLConnection)urL.openConnection();
                client.setRequestMethod("GET");
                int responceCode = client.getResponseCode();
                System.out.println("sending 'GET'request to url :"+urL);
                System.out.println("response code:"+responceCode);

                InputStreamReader myInput =new InputStreamReader(client.getInputStream());

                BufferedReader in =new BufferedReader(myInput);
                String inputline;
                StringBuffer response =new StringBuffer();
                while((inputline= in.readLine())!= null){
                    response.append(inputline);

                }
                in.close();
                System.out.println(response.toString());

                JSONObject obj = new JSONObject(response.toString());
                ou1=""+obj.getInt("id");
                ou2=""+obj.getInt("salary");
                ou3=obj.getString("fname");
                ou4=obj.getString("lname");
            }

             catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }
        @Override
        protected void onPostExecute(Void result) {
            out1.setText(ou2);
            out2.setText(ou3);
            out3.setText(ou2);
            out4.setText(ou1);
            super.onPostExecute(result);
        }
    }
}
