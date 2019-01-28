package com.alosboiya.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    ImageView poster;
    ImageView imageView;
    TextView title;
    TextView rate;
    TextView desc;
    ImageView fav;
    helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        poster=(ImageView)findViewById(R.id.image_poster);
        imageView=(ImageView)findViewById(R.id.movie_image);
        title=(TextView)findViewById(R.id.movie_title);
        rate=(TextView)findViewById(R.id.movie_rate);
        desc=(TextView)findViewById(R.id.movie_des);
        fav=(ImageView)findViewById(R.id.imagebutton);
        Bundle bundle = getIntent().getExtras();
        final Movie movie = (Movie) bundle.getParcelable("intent");
        title.setText(movie.getTitle());
        desc.setText(movie.getOver_view());
        rate.setText(movie.getrate());
        Picasso.with(getApplicationContext()).load(movie.getPass()).into(poster);
        Picasso.with(getApplicationContext()).load(movie.getPoster_pass()).into(imageView);
        helper = new helper(this);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.insert(movie.getTitle(),movie.getPoster_pass());
                Toast.makeText(Main2Activity.this,"done",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
