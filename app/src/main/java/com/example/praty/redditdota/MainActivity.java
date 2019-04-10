package com.example.praty.redditdota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public final String baseURL="https://www.reddit.com/";

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedApi feedApi= retrofit.create(FeedApi.class);

        Call<Feed> call=feedApi.getFeed();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
              //  Log.d(TAG, "onResponse: feed:"+ response.body().toString());
                Log.d(TAG, "onResponse: Server response:"+response.toString());

                List<Entry> entries=response.body().getEntries();
//                Log.d(TAG, "onResponse: author:"+ entries.get(0).getAuthor());
//                Log.d(TAG, "onResponse: updated:"+entries.get(0).getUpdated());

               // Log.d(TAG, "onResponse: 1st post:"+response.body().getEntries());


                ArrayList<Post> posts=new ArrayList<Post>();
                for(int i=0;i<entries.size();i++){
                    ExtractXML extractXML1=new ExtractXML(entries.get(i).getContent(), "<a href=");
                    extractXML1.startExtract();
                    List<String> postContent=extractXML1.getHrefs();
                   // Log.d(TAG, "onResponse: Post link:"+ postContent.get(postContent.size()-1));
                    int size1=postContent.size();
                    for(int j=0;j<size1;j++){

                        //Log.d(TAG, "onResponse: href: "+ postContent.get(j));
                        if(postContent.get(j).endsWith(".jpg") || postContent.get(j).endsWith(".jpeg")|| postContent.get(j).endsWith(".png")){
                            postContent.add(postContent.get(j));
                        }
                    }

                    if(postContent.size()==size1)
                    posts.add(new Post(
                            entries.get(i).getAuthor().getName(),
                            entries.get(i).getUpdated(),
                            entries.get(i).getTitle(),
                            postContent.get(postContent.size()-1),
                            postContent.get(postContent.size()-1)

                    ));
                    else{
                        posts.add(new Post(
                                entries.get(i).getAuthor().getName(),
                                entries.get(i).getUpdated(),
                                entries.get(i).getTitle(),
                                postContent.get(postContent.size()-2),
                                postContent.get(postContent.size()-1)
                        ));


                    }
                }

//                for(int j=0;j<posts.size();j++){
//                    Log.d(TAG, "onResponse: PostURL:"+ posts.get(j).getPostURL());
//                    Log.d(TAG, "onResponse: ThumbnailURL:"+posts.get(j).getThumbnailURL());
//                }

                Log.d(TAG, "onResponse: posts size:"+posts.size());
                setupRecyclerView(posts);





            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS:" + t.getMessage() );
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setupRecyclerView(final ArrayList<Post> posts) {
        mRecyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager=new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new MyAdapter(this, posts, new ItemClickListener(){

            @Override
            public void onItemClick(View v, int position) {
                Intent mIntent=new Intent(MainActivity.this, CommentsActivity.class);
                mIntent.putExtra("Title", posts.get(position).getTitle());
                mIntent.putExtra("Author",posts.get(position).getAuthor());
                mIntent.putExtra("Updated",posts.get(position).getDate_updated());
                mIntent.putExtra("PostURL", posts.get(position).getPostURL());
                mIntent.putExtra("ImageURL",posts.get(position).getThumbnailURL());
                startActivity(mIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
