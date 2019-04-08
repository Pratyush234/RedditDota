package com.example.praty.redditdota;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";
    public final String baseURL="https://www.reddit.com/";

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    TextView mTitle, mUpdated, mAuthor;
    ImageView mImage;

    String currentFeed;

    List<Comment> mComments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        mTitle=(TextView) findViewById(R.id.postTitle);
        mAuthor=(TextView) findViewById(R.id.postAuthor);
        mUpdated=(TextView) findViewById(R.id.postUpdated);
        mImage=(ImageView) findViewById(R.id.postThumbnail);


        Intent mIntent=getIntent();
        mTitle.setText(mIntent.getStringExtra("Title"));
        mAuthor.setText(mIntent.getStringExtra("Author"));
        mUpdated.setText(mIntent.getStringExtra("Updated"));
        Glide.with(getApplicationContext()).load(mIntent.getStringExtra("ImageURL"))
                .into(mImage);




        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedApi feedApi= retrofit.create(FeedApi.class);

        String PostUrl=mIntent.getStringExtra("PostURL");
        Log.d(TAG, "onCreate: PostUrl:"+PostUrl);
        try{
            String[] splitURL=PostUrl.split(baseURL);
            currentFeed=splitURL[1];
            Log.d(TAG, "onCreate: currentFeed:"+currentFeed);

        }catch (ArrayIndexOutOfBoundsException e){
            Log.e(TAG, "onCreate: ArrayIndexOutOfBoundsException"+ e.getMessage() );
        }

        Call<Feed> call=feedApi.getComments(currentFeed);

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: Server response:"+response.toString());
                assert response.body() != null;
                List<Entry> entries=response.body().getEntries();
                for(int i=1;i<entries.size();i++){
                    //Log.d(TAG, "onResponse: entry:"+entries.get(i).toString()+"\n-------");
                    ExtractXML extractXML=new ExtractXML(entries.get(i).getContent(),"<div class=\"md\"><p>","</p>");
                    extractXML.startExtract();
                    List<String> commentDetails=extractXML.getHrefs();

                    for(int j=0;j<commentDetails.size();j++){
                        Log.d(TAG, "onResponse: comments: "+commentDetails.get(j));
                    }

                   try {
                       mComments.add(new Comment(
                               commentDetails.get(0),
                               entries.get(i).getAuthor().getName(),
                               entries.get(i).getUpdated()

                       ));
                   }
                   catch (IndexOutOfBoundsException e){
                       mComments.add(new Comment(
                               "Error reading comment",
                               "None",
                               "None"
                               ));
                       Log.e(TAG, "onResponse: IndexOutOfBoundsException"+ e.getMessage() );
                   }catch (NullPointerException e){
                       mComments.add(new Comment(
                               commentDetails.get(0),
                               "None",
                               entries.get(i).getUpdated()

                       ));
                   }


                }

                setupRecyclerView();

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS:" + t.getMessage() );
                Toast.makeText(CommentsActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupRecyclerView() {

        mRecyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter= new CommentsAdapter(mComments);
        mRecyclerView.setAdapter(mAdapter);
    }
}
