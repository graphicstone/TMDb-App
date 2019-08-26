package com.example.tmdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ImageButton mBtnSearch;
    Button mBtnLatest, mBtnNowPlaying, mBtnUpcoming;
    EditText mEditTextSearch;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        initViews();
        String apiUrl ="https://api.themoviedb.org/3/movie/popular?api_key=e1b80108eed81c20bcd6332222b8e0aa&language=en-US&page=1";
        new CheckConnectionStatus().execute(apiUrl);
    }

    class CheckConnectionStatus extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... param)
        {
            URL url = null;
            try
            {
                url = new URL(param[0]);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            try
            {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));
                String s = bufferedReader.readLine();
                bufferedReader.close();
                return s;
            }
            catch (IOException e)
            {
                Log.e("Error", e.getMessage(),e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            JSONObject jsonObject;
            try
            {
                jsonObject = new JSONObject(s);

                ArrayList<MovieDetails> movieList = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                if(jsonArray.length()==0){
                    Toast.makeText(MainActivity.this, "No matches found", Toast.LENGTH_SHORT).show();
                }
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    MovieDetails movieDetails = new MovieDetails(obj.getString("original_title"),obj.getString("overview"),
                            obj.getString("release_date"),obj.getString("vote_average"),obj.getString("poster_path"));

                    movieList.add(movieDetails);

                    RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(movieList,MainActivity.this);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }
            catch(JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }

    private void initViews()
    {
        mBtnLatest = findViewById(R.id.btn_latest);
        mBtnSearch = findViewById(R.id.btn_search);
        mBtnNowPlaying = findViewById(R.id.btn_now_playing);
        mBtnUpcoming = findViewById(R.id.btn_upcoming);
        mEditTextSearch = findViewById(R.id.edit_search);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        View current = getCurrentFocus();
        if(current != null)
            current.clearFocus();

        mBtnSearch.setOnClickListener(this);
        mBtnLatest.setOnClickListener(this);
        mBtnNowPlaying.setOnClickListener(this);
        mBtnUpcoming.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_latest:
                String apiUrl ="https://api.themoviedb.org/3/movie/popular?api_key=e1b80108eed81c20bcd6332222b8e0aa&language=en-US&page=1";
                new CheckConnectionStatus().execute(apiUrl);
                break;
            case R.id.btn_now_playing:
                apiUrl="https://api.themoviedb.org/3/movie/now_playing?api_key=e1b80108eed81c20bcd6332222b8e0aa&language=en-US&page=1";
                new CheckConnectionStatus().execute(apiUrl);
                break;
            case R.id.btn_upcoming:
                apiUrl="https://api.themoviedb.org/3/movie/upcoming?api_key=e1b80108eed81c20bcd6332222b8e0aa&language=en-US&page=1";
                new CheckConnectionStatus().execute(apiUrl);
                break;
            case R.id.btn_search:
                String  x;
                x = mEditTextSearch.getText().toString();
                if(x.isEmpty())
                {
                    Toast.makeText(this,"Empty field",Toast.LENGTH_SHORT).show();
                    break;
                }
                apiUrl="https://api.themoviedb.org/3/search/movie?language=en-US&query=" + x + "&page=1&include_adult=false" ;
                new CheckConnectionStatus().execute(apiUrl);
                mEditTextSearch.setText("");
                break;
        }
    }
}