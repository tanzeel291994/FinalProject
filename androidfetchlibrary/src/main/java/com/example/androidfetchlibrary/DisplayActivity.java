package com.example.androidfetchlibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class DisplayActivity extends AppCompatActivity {
    static Context context;
    private static ProgressBar spinner;
public DisplayActivity displayActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //String str=getIntent().getStringExtra("joke");
        //Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        context=this;
        displayActivity=this;
      new GetJokeAsyncTask().execute();
    }

    public static class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

        //private static final String LOG_TAG = GetJokeAsyncTask.class.getSimpleName();
        private MyApi myApiService = null;
        @Override
        protected String doInBackground(Void... voids) {

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://2-dot-example-5abb8.appspot.com/_ah/api/");


            MyApi myApiService = builder.build();

            String joke = null;
            Log.i("TAG","in");
            try {
                joke = myApiService.getJoke().execute().getData();
                Log.i("TAG",joke);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TAG",e.toString());
            }

            return joke;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            spinner.setVisibility(View.GONE);
            Toast.makeText(context,s, Toast.LENGTH_LONG).show();

        }

    }
}
