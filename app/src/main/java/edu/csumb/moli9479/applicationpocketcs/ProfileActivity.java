package edu.csumb.moli9479.applicationpocketcs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

public class ProfileActivity extends AppCompatActivity {
    private ProfilePictureView profilepic;
    private TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Profile profile = Profile.getCurrentProfile();
        profilepic = (ProfilePictureView) findViewById(R.id.userProfilePhoto);
        profilepic.setProfileId(profile.getId());

        username = (TextView) findViewById(R.id.userName);
        username.setText(profile.getName());

    }
}
