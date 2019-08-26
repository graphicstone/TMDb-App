package com.example.tmdbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    private ArrayList<MovieDetails> mMovieList;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<MovieDetails> mMovieList, Context mContext) {
        this.mMovieList = mMovieList;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position)
    {
        holder.movieTitle.setText(mMovieList.get(position).getOrginal_title());
        holder.movieTitle.setTag(position);
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500/"+ mMovieList.get(position).getPoster_path()).into(holder.movImg);


    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView movImg;
        TextView movieTitle;

        public MyViewHolder(@NonNull final View itemView)
        {
            super(itemView);
            movieTitle =itemView.findViewById(R.id.movie_name);
            movImg = itemView.findViewById(R.id.img_mov);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int pos = (int) movieTitle.getTag();
                    StringBuilder buffer = new StringBuilder();
                    buffer.append("Overview : ").append(mMovieList.get(pos).getOverview()).append("\n\n");
                    buffer.append("Release Date : ").append(mMovieList.get(pos).getRelease_date()).append("\n\n");
                    buffer.append("Rating : ").append( mMovieList.get(pos).getVote_average()).append("\n");

                    new AlertDialog.Builder(mContext)
                            .setTitle(mMovieList.get(pos).getOrginal_title())
                            .setMessage(buffer.toString())
                            .setCancelable(true)
                            .show();
                }
            });
        }
    }
}

