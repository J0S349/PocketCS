package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
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
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity{

    //Creating variable references to the XML button and CallbackManager.
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private String userId;
    fbUser user;

    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    private String TAG;

    private static ProfilePictureView navProfileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        TAG = "Login.Activity";

        //Callback manager manages callbacks into the FB SDK from an Activity's onActivityResult() Method.
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //If login in successful,
            @Override
            public void onSuccess(LoginResult loginResult) {
               if(Profile.getCurrentProfile() == null){
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                            mProfileTracker.stopTracking();
                        }
                    };
                }
                else{
                    Profile profile = Profile.getCurrentProfile();
                    //navProfileImage = (ProfilePictureView) findViewById(R.id.nav_profilePhoto);
                    if(profile != null){
                        goMainScreen(profile);
                    }
                }
                Profile profile = Profile.getCurrentProfile();
                Log.d("Fb Login Success ", "In OnSuccess");
                if(profile != null){
                    Log.d("Message: ", "Welcome "+profile.getName());
                    goMainScreen(profile);
                }
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

    private boolean checkFbLogin(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        // if it is not equal to null it will return true;
        return accessToken != null;
    }

   private void goMainScreen(Profile profile) {
            //Passing in the name,id and photo from the profile.
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id",profile.getId());
            intent.putExtra("userid",userId);
            startActivity(intent);
            finish();
    }
    @Override
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        if(profile != null){
            Log.d("Fb login success", "in onResume");
            //Toast.makeText(getApplicationContext(),"Setting nav image",Toast.LENGTH_LONG).show();
            //navProfileImage.setProfileId(profile.getId());
            goMainScreen(profile);
        }
    }

    private void setupTokenTracker() {
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.d("APP currentAccessToken", "" + currentAccessToken);
            }
        };
    }

    @Override
    //All Request Code, Result Code, and data are recieved by the activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(callbackManager.onActivityResult(requestCode,resultCode,data)){
            return;
        }
        //callbackManager.onActivityResult(requestCode,resultCode,data);
    }

}