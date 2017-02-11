package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Joke;
import com.example.androidfetchlibrary.DisplayActivity;
import com.example.user.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Joke joke;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joke=new Joke();
        context=this;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        //new GetJokeAsyncTask().execute();
        //Toast.makeText(this,joke.getJoke(), Toast.LENGTH_SHORT).show();
      //  Intent intent=new Intent(context,DisplayActivity.class);
        //intent.putExtra("joke",s);
        //startActivity(intent);
        new GetJokeAsyncTask().execute();
    }

    public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context,s, Toast.LENGTH_SHORT).show();

        }

    }
}
