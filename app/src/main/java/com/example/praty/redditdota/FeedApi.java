package com.example.praty.redditdota;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FeedApi {

    @GET("r/DotA2/.rss")
    Call<Feed> getFeed();

    @GET("{feed_name}/.rss")
    Call<Feed> getComments(@Path("feed_name") String feed_name);
}
