package edu.csumb.moli9479.applicationpocketcs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static edu.csumb.moli9479.applicationpocketcs.R.attr.icon;
import static edu.csumb.moli9479.applicationpocketcs.R.attr.preserveIconSpacing;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ALGORITHMS_INFO = 0;
    private static final int DATASTRUCTURES_INFO = 1;
    private static final int SOFTWAREDESIGN_INFO = 2;

    private ActionBarDrawerToggle toggle;
    public static User currentUser;
    private String TAG;

    @BindView(R.id.AlgorithmsButton) Button algorithms;
    @BindView(R.id.DataStructuresButton) Button dataStructures;
    @BindView(R.id.SoftwareDesignPatternButton) Button softwareDesign;
    @BindView(R.id.image) ProfilePictureView profilePicture;
    @BindView(R.id.nameTextView) TextView nameView;
    @BindView(R.id.idTextView) TextView idView;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
    @BindView(R.id.navigationView) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setNavigationDrawer();
        setUserProfilePhoto_Name_ID();

    }

    public void setNavigationDrawer(){
        toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setUserProfilePhoto_Name_ID(){
        Profile profile = Profile.getCurrentProfile();

        profilePicture.setProfileId(profile.getId());
        nameView.setText(profile.getName());
        idView.setText(profile.getId());
    }

    @OnClick(R.id.AlgorithmsButton)
    public void onAlgorithmsButtonClick(){
        initializeCategoryScreen(ALGORITHMS_INFO);
        //Toast.makeText(this, "Clicked on algo button", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.DataStructuresButton)
    public void onDataStructuresButtononClick(){
        initializeCategoryScreen(DATASTRUCTURES_INFO);
    }

    @OnClick(R.id.SoftwareDesignPatternButton)
    public void onSoftwareDesignonClick(){
        initializeCategoryScreen(SOFTWAREDESIGN_INFO);
    }

    public void initializeCategoryScreen(int info) {
        Bundle extraInfo = new Bundle();
        Intent i = new Intent(this, CategoryScreen.class);
        extraInfo.putInt("categoryScreen", info);
        i.putExtras(extraInfo);
        startActivity(i);
    }

   
    private void goToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void logoutUser(DialogInterface.OnClickListener view){

        GraphRequest delPermRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "/{user-id}/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                if(graphResponse!=null){
                    FacebookRequestError error =graphResponse.getError();
                    if(error!=null){
                        Log.e(TAG, error.toString());
                    }else {
                        finish();
                    }
                }
            }
        });
        Log.d(TAG,"Executing revoke permissions with graph path" + delPermRequest.getGraphPath());
        delPermRequest.executeAsync();
        LoginManager.getInstance().logOut();
        goToLoginScreen();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.Home_menu_item:
                Toast.makeText(MainActivity.this,"Clicked on home",Toast.LENGTH_LONG).show();
                break;

            case R.id.Profile_menu_item:
                Toast.makeText(MainActivity.this, "Clicked on Profile", Toast.LENGTH_LONG).show();
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;

            case R.id.Quizzes_menu_item:
                Toast.makeText(MainActivity.this, "Clicked on Quizzes", Toast.LENGTH_LONG).show();
                break;

            case R.id.Community_menu_item:
                Toast.makeText(MainActivity.this, "Clicked on Community", Toast.LENGTH_LONG).show();
                break;

            case R.id.About_menu_item:
                Toast.makeText(MainActivity.this, "Clicked on About", Toast.LENGTH_LONG).show();
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;

            case R.id.Logout_menu_item:
                Toast.makeText(MainActivity.this, "Clicked on Logout", Toast.LENGTH_LONG).show();
                //Alert Dialog to ask user if they are sure they want to logout.
                new AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logoutUser(this);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(R.drawable.ic_priority_high_black_24dp)
                        .show();
                break;
        }
        //Code below checks if the drawer is open,
            //if it is open and something is clicked, it will close.
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawerLayout);
        if(dl.isDrawerOpen(GravityCompat.START)){
            dl.closeDrawer(GravityCompat.START);
        }

        return false;
    }

    @Override
    public void onResume(){
        super.onResume();

        Profile profile = Profile.getCurrentProfile();
        if(profile == null){
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

}
