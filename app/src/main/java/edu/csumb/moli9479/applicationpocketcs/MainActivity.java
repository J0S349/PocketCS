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
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import static edu.csumb.moli9479.applicationpocketcs.R.attr.icon;
import static edu.csumb.moli9479.applicationpocketcs.R.attr.preserveIconSpacing;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {


    private ProfilePictureView profilepic;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the Navigation Drawer.
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        Button algorithms = (Button)findViewById(R.id.AlgorithmsButton);
        algorithms.setOnClickListener(this);
        Button dataStructures = (Button)findViewById(R.id.DataStructuresButton);
        dataStructures.setOnClickListener(this);
        Button softwareDesign = (Button)findViewById(R.id.SoftwareDesignPatternButton);
        softwareDesign.setOnClickListener(this);


        //Setting the fields with their respect values.
       //firstnameView = (TextView) findViewById(R.id.firstname);
        //userId = (TextView) findViewById(R.id.userId);

        profilepic = (ProfilePictureView) findViewById(R.id.image);

        //If user is not logged in, go to the login screen.
        if(AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }

        //Extracting the information provided by the bundle.
       // Bundle inBundle = getIntent().getExtras();
        /*if(inBundle != null){
            String id = inBundle.get("id").toString();
           //userId.setText(id);
           profilepic.setProfileId(id);
        }*/

        Profile profile = Profile.getCurrentProfile();
        profilepic.setProfileId(profile.getId());
    }

    public void onClick(View v) {
        Bundle extraInfo = new Bundle();
        if(v.getId() == R.id.AlgorithmsButton)
        {
            Intent i = new Intent(this, CategoryScreen.class);
            extraInfo.putInt("categoryScreen", 0);
            i.putExtras(extraInfo);
            startActivity(i);
        }
        else if(v.getId() == R.id.DataStructuresButton)
        {
            Intent i = new Intent(this, CategoryScreen.class);
            extraInfo.putInt("categoryScreen", 1);
            i.putExtras(extraInfo);
            startActivity(i); // Or startActivity(new Intent(this, About.class));
        }
        else if(v.getId() == R.id.SoftwareDesignPatternButton)
        {
            Intent i = new Intent(this, CategoryScreen.class);
            extraInfo.putInt("categoryScreen", 2);
            i.putExtras(extraInfo);
            startActivity(i); // Or startActivity(new Intent(this, About.class));
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
     *************************************************************************************************************
     * @param view*/
    public void logout(DialogInterface.OnClickListener view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    /*******************************************************************************
     Method for the hamburger icon, if the item clicked is the hamburger icon
     the navigation will appear, else it will be hidden.
     *******************************************************************************/
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
                                logout(this);
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
}
