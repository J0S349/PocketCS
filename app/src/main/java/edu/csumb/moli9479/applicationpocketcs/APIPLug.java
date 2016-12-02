package edu.csumb.moli9479.applicationpocketcs;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by josephmolina on 12/1/16.
 */

public interface APIPlug {
    @GET("Algorithms")
    Call<CSTopicResults> getAlgorithms();
}
