package edu.csumb.moli9479.applicationpocketcs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.userProfilePhoto) ProfilePictureView profilepic;
    @BindView(R.id.userProfileName) TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        Profile profile = Profile.getCurrentProfile();
        profilepic.setProfileId(profile.getId());
        username.setText(profile.getName());

    }
}
