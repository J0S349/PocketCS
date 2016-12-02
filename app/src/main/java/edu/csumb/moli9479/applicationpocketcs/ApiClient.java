package edu.csumb.moli9479.applicationpocketcs;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by josephmolina on 12/1/16.
 */

public class ApiClient {
    private static APIPlug REST_CLIENT;
    private static final String API_URL = "http://sample-env.yf5ym3ie28.us-east-1.elasticbeanstalk.com/";

    static {
        setupRestClient();
    }

    private ApiClient(){}

    public static APIPlug get(){
        return REST_CLIENT;
    }

    private static void setupRestClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        REST_CLIENT = retrofit.create(APIPlug.class);
    }
}
