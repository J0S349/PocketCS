package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    //Creating variable references to the XML button and CallbackManager.
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TAG = "Login.Activity";

        if(AccessToken.getCurrentAccessToken() != null){
            goMainScreen(Profile.getCurrentProfile());
        }

        //Callback manager manages callbacks into the FB SDK from an Activity's onActivityResult() Method.
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //If login in successful,
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                goMainScreen(profile);
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
    private void goMainScreen(Profile profile) {
        if(profile != null){
            //Passing in the name,id and photo from the profile.
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id",profile.getId());
            intent.putExtra("photo",profile.getProfilePictureUri(200,200));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    //All Request Code, Result Code, and data are recieved by the activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}