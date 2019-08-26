//package com.example.tmdbapp;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class CheckConnectionStatus extends AsyncTask<String, Void, String> {
//
//    Context mContext;
//
//    public CheckConnectionStatus(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    @Override
//    protected String doInBackground(String... param)
//    {
//        URL url = null;
//        try
//        {
//            url = new URL(param[0]);
//        } catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
//        try
//        {
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = urlConnection.getInputStream();
//
//            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));
//            String s = bufferedReader.readLine();
//            bufferedReader.close();
//            return s;
//        }
//        catch (IOException e)
//        {
//            Log.e("Error", e.getMessage(),e);
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String s)
//    {
//        JSONObject jsonObject;
//        try
//        {
//            jsonObject = new JSONObject(s);
//
//            ArrayList<MovieDetails> movieList = new ArrayList<>();
//            JSONArray jsonArray = jsonObject.getJSONArray("results");
//            for(int i=0;i<jsonArray.length();i++) {
//                JSONObject obj = jsonArray.getJSONObject(i);
//                MovieDetails movieDetails = new MovieDetails(obj.getString("original_title"),obj.getString("overview"),
//                        obj.getString("release_date"),obj.getString("vote_average"),obj.getString("poster_path"));
//
//                movieList.add(movieDetails);
//
//
//            }
//        }
//        catch(JSONException e) {
//            e.printStackTrace();
//        }
//        super.onPostExecute(s);
//    }
//}
