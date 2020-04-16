package org.kasapbasi.week9downloadintcont;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;



public class MainActivity extends AppCompatActivity {

    public class myTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                InputStream is = con.getInputStream();
                InputStreamReader sr= new InputStreamReader(is);
                int data = sr.read();
                StringBuilder sb=new StringBuilder("");
                while(data!=-1) {
                    sb.append((char)data);
                    data=sr.read();
                }

                return sb.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
       return  strings[0] ;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTask mt= new myTask();

        try {
          String str=mt.execute("https://ticaret.edu.tr/").get();
          Log.d("WEB",str);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
