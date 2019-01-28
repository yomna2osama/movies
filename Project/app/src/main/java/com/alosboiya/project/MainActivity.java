package com.alosboiya.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickList {
    RequestQueue requestQueue;
    String imagesectorurl = "http://image.tmdb.org/t/p/w342/";
    List<Movie> mylist,list;
    RecyclerView recycle;
    Adapter myadapter;
    String myurl="https://api.themoviedb.org/3/discover/movie?api_key=69f8d44407d7b73a4103add4c76fccb6";
    helper help;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        mylist=new ArrayList<Movie>();
        list=new ArrayList<Movie>();
        recycle=(RecyclerView)findViewById(R.id.recycle);
        recycle.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        get_data(myurl);
        myadapter=new Adapter(mylist,getApplicationContext(),this);
        recycle.setAdapter(myadapter);
        help=new helper(this);
    }

    public void get_data(String url) {

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray myarray = response.getJSONArray("results");
                    for (int i=0; i < myarray.length();i++)
                    {
                        JSONObject myjsonobj = myarray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(myjsonobj.getString("title"));
                        String imageurl=imagesectorurl+myjsonobj.getString("poster_path");
                        movie.setPoster_pass(imageurl);
                        String posturl = imagesectorurl+myjsonobj.getString("backdrop_path");
                        movie.setpass(posturl);
                        movie.setOver_view(myjsonobj.getString("overview"));
                        movie.setrate(myjsonobj.getString("vote_average"));
                        mylist.add(movie);
                    }
                    myadapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void recyclerViewListClicked(int position) {
        Intent myintent=new Intent(this,Main2Activity.class);
        myintent.putExtra("intent", (Parcelable) mylist.get(position));
        startActivity(myintent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shape,menu);
        return true;
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        if(id==R.id.favourite)
        {
           list=help.select();

            myadapter=new Adapter(mylist,getApplicationContext(),this);
            recycle.setAdapter(myadapter);
            myadapter.notifyDataSetChanged();


            return true;
        }

        return super.onOptionsItemSelected(menuItem);

    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.favourite)
        {
            cursor = help.selectCursor();
            if(cursor != null) {
                Toast.makeText(MainActivity.this,cursor.getCount() +"Cursor not Null",Toast.LENGTH_SHORT).show();
                cursor.moveToFirst();
            }

         //   cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++)
            {
                Movie mymovie=new Movie();
                mymovie.setPoster_pass(cursor.getString(1));
                mymovie.setTitle(cursor.getString(0));
                list.add(mymovie);
                cursor.moveToNext();
            }
            Toast.makeText(MainActivity.this,list.size()+"",Toast.LENGTH_SHORT).show();
            Adapter M=new Adapter(list,getApplicationContext(),this);
            M.notifyDataSetChanged();
            recycle.setAdapter(M);
            setTitle("favourite");
            return true;
        }
        if(id==R.id.movies)
        {
            myadapter=new Adapter(mylist,getApplicationContext(),this);
            recycle.setAdapter(myadapter);
            myadapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}