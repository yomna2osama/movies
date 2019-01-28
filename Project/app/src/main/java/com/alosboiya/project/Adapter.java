package com.alosboiya.project;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HP on 7/19/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {

    public Context cont ;
    public List<Movie> list;
    public RecyclerViewClickList recyclerViewClickList;
    public Adapter(List<Movie> list ,Context cont , RecyclerViewClickList recyclerViewClickList){
        this.list=list;
        this.cont=cont;
        this.recyclerViewClickList=recyclerViewClickList;
    }
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.design,parent,false);
        holder myholder=new holder(row);
        return myholder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
       /* String imageurl=list.get(position).getPoster_pass();
        Picasso.with(cont).load(imageurl).into(holder.image);*/
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        public holder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickList.recyclerViewListClicked(this.getLayoutPosition());
        }
        public void bind(Movie movie)
        {
            Picasso.with(cont).load(Uri.parse(movie.getPoster_pass())).into(image);
        }
    }
}

