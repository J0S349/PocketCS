package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences settings = getSharedPreferences("SetData",0);
        boolean firstStart = settings.getBoolean("firstStart",true);

        if(firstStart){
            setData();
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstStart",false);
            editor.commit();
        }


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    public void setData() {
        Request request = new Request.Builder()
                .url("http://sample-env.yf5ym3ie28.us-east-1.elasticbeanstalk.com/rest/getTable?tableName=Algorithms")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("Splash screen", response.toString());
                String reponseData = response.body().string();
                reponseData = URLDecoder.decode(reponseData, "UTF-8");
                StringTokenizer tokenizer = new StringTokenizer(reponseData, "∑");
                //StringTokenizer tokenizer2 = new StringTokenizer(tokenizer.toString(),",");
                while (tokenizer.hasMoreTokens()) {
                    System.out.println(tokenizer.nextToken());
                }
                Log.d("Splashscren", reponseData);
                /*while (tokenizer2.hasMoreTokens()) {
                    System.out.println(tokenizer.nextToken());
                }*/

            }
        });
        //How do I divide the string to the parameters I need for the database
    }
}

