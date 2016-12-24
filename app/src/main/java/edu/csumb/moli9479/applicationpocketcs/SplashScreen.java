package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Bundle;
import java.net.URLDecoder;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
    OkHttpClient client = new OkHttpClient();
    String imageID,algorithmName,DSName,softwareName,runtime,description,helpfulLink,benefits,costs;
    int categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //removed title from Splash Screen
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences settings = getSharedPreferences("SetData",0);
        boolean firstStart = settings.getBoolean("firstStart",true);



        if(firstStart){
            setData();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setDataStructures();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           setSoftwareDesign();
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
       final  OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);

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
                String responseData = response.body().string();
                responseData = URLDecoder.decode(responseData, "UTF-8");

                try {
                    StringTokenizer tokenizer = new StringTokenizer(responseData,"∑");
                    while (tokenizer.hasMoreTokens()){

                       JSONObject jsonObject = new JSONObject(tokenizer.nextToken());
                        imageID = (String) jsonObject.get("imageID");
                        algorithmName = (String) jsonObject.get("algoName");
                        runtime = (String) jsonObject.get("runtime");
                        description = (String) jsonObject.get("description");
                        helpfulLink = (String) jsonObject.get("helpfulLink");
                        categoryID = (int)jsonObject.get("categoryID");

                        Algorithms algorithm = new Algorithms(algorithmName, description,runtime, imageID, helpfulLink,categoryID);
                        db.addAlgorithm(algorithm);
                        //db.close();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        });
    }

    public void setDataStructures(){
        final OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);

        Request request = new Request.Builder()
                .url("http://sample-env.yf5ym3ie28.us-east-1.elasticbeanstalk.com/rest/getTable?tableName=DataStructures")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "Unable to reach database", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                responseData = URLDecoder.decode(responseData, "UTF-8");

                try{
                    StringTokenizer tokenizer = new StringTokenizer(responseData,"∑");
                    //OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);

                    while (tokenizer.hasMoreTokens()){
                        JSONObject jsonObject = new JSONObject(tokenizer.nextToken());
                        imageID = (String) jsonObject.get("imageID");
                        DSName = (String) jsonObject.get("DSName");
                        runtime = (String) jsonObject.get("runtime");
                        description = (String) jsonObject.get("description");
                        helpfulLink = (String) jsonObject.get("helpfulLink");
                        categoryID = (int)jsonObject.get("categoryID");
                        Log.d("datastructures", imageID + " " + DSName + " " + runtime + " " + description + " " + helpfulLink + " " + categoryID);

                        DataStructures dataStructure = new DataStructures(DSName, description,runtime, imageID, helpfulLink, categoryID);
                        db.addDataStructure(dataStructure);
                       // db.close();
                    }

                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        });

    }


    public void setSoftwareDesign(){
        final OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);

        Request request = new Request.Builder()
                .url("http://sample-env.yf5ym3ie28.us-east-1.elasticbeanstalk.com/rest/getTable?tableName=SoftwareDesigns")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "Unable to reach database", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                responseData = URLDecoder.decode(responseData, "UTF-8");

                try{
                    StringTokenizer tokenizer = new StringTokenizer(responseData,"∑");

                    while (tokenizer.hasMoreTokens()){
                        JSONObject jsonObject = new JSONObject(tokenizer.nextToken());
                        costs = (String) jsonObject.get("downside/analogy");
                        softwareName = (String) jsonObject.get("SDName");
                        imageID = (String) jsonObject.get("imageID");
                        benefits = (String)jsonObject.get("benefit/analogy");
                        description = (String) jsonObject.get("description");
                        helpfulLink = (String) jsonObject.get("helpfulLink");
                        categoryID = (int)jsonObject.get("categoryID");

                        SoftwareDesign softwareDesign = new SoftwareDesign(softwareName,description,imageID,benefits,costs,helpfulLink,categoryID);
                        db.addSoftwareDesign(softwareDesign);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

