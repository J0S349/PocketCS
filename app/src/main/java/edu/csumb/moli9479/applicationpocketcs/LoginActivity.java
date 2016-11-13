package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //Creating variable references to the XML button and CallbackManager.
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        TAG = "Login.Activity";
        checkIfLoggedin(accessToken.getCurrentAccessToken() != null);
        /*if(AccessToken.getCurrentAccessToken() != null){
            goMainScreen(Profile.getCurrentProfile());
        }
        */
        //Callback manager manages callbacks into the FB SDK from an Activity's onActivityResult() Method.
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        //Access token track
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {


            }
        };
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //If login in successful,
            @Override
            public void onSuccess(LoginResult loginResult) {
                //accessToken.getCurrentAccessToken();

                accessToken = loginResult.getAccessToken();

                Profile profile = Profile.getCurrentProfile();

                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                Log.d("response", String.valueOf(object));

                                try {

                                   MainActivity.currentUser = new User(object.getString("id"), object.getString("name"));

                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "Error with graph request", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name");
                request.setParameters(parameters);
                request.executeAsync();

                goMainScreen(Profile.getCurrentProfile());


            }
            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.cancel_login, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkIfLoggedin(boolean isLoggedIn){
        if(isLoggedIn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
   private void goMainScreen(Profile profile) {
       if(profile != null){
            //Passing in the name,id and photo from the profile.
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id",profile.getId());
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onResume(){
        super.onResume();

        if(accessToken != null){
            checkIfLoggedin(accessToken.getCurrentAccessToken() != null);
        }
    }

    @Override
    //All Request Code, Result Code, and data are recieved by the activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


}