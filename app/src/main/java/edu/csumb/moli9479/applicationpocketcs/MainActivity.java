package edu.csumb.moli9479.applicationpocketcs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ALGORITHMS_INFO = 0;
    private static final int DATASTRUCTURES_INFO = 1;
    private static final int SOFTWAREDESIGN_INFO = 2;
    private static final int ALGORITHMS_QUIZ = 1;
    private static final int DATASTRUCTURE_QUIZ = 2;
    private static final int SOFTWAREDESIGN_QUIZ = 3;
    private ActionBarDrawerToggle toggle;
    private fbUser user;

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
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setNavigationDrawer();
    }

    public void setNavigationDrawer(){
        toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick(R.id.AlgorithmsButton)
    public void onAlgorithmsButtonClick(){
        initializeCategoryScreen(ALGORITHMS_INFO);
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
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createQuiz(int info) {
        Bundle extraInfo = new Bundle();
        Intent i = new Intent(this, quizTemplate.class);
        extraInfo.putInt("quizType", info);
        i.putExtras(extraInfo);
        startActivity(i);
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

            case R.id.algorithms_quiz_menu_item:
                createQuiz(ALGORITHMS_QUIZ);
                break;

            case R.id.datastructures_quiz_menu_item:
                createQuiz(DATASTRUCTURE_QUIZ);
                break;

            case R.id.softwareDesign_quiz_menu_item:
                createQuiz(SOFTWAREDESIGN_QUIZ);

            case R.id.Community_menu_item:
                break;

            case R.id.About_menu_item:
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
                                goToLoginScreen();
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
