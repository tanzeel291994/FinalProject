package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Joke;
import com.example.androidfetchlibrary.DisplayActivity;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity {

    Joke joke;
    static Context context;
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
        Intent intent=new Intent(context,DisplayActivity.class);
        //intent.putExtra("joke",s);
        startActivity(intent);
        // new GetJokeAsyncTask().execute();
    }
}
