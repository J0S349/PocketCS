package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

public class MainActivity extends AppCompatActivity {

    private TextView firstnameView,userId;
    private ProfilePictureView profilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstnameView = (TextView) findViewById(R.id.firstname);
        userId = (TextView) findViewById(R.id.userId);
        profilepic = (ProfilePictureView) findViewById(R.id.image);

        //If user is not logged in, go to the login screen.
        if(AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }

        //Extracting the information provided by the bundle.
        Bundle inBundle = getIntent().getExtras();
        if(inBundle != null){
            String name = inBundle.get("name").toString();
            firstnameView.setText(name);
            String id = inBundle.get("id").toString();
            userId.setText(id);
            profilepic.setProfileId(id);
        }
    }

    /******************************************************************************************************************
     * Method that is used to send the user back to the loginScreen if they are not logged in.
     ******************************************************************************************************************/
    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /*************************************************************************************************************
    Method that will log the user out of the application.
     **************************************************************************************************************/
    public void logout(View view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
