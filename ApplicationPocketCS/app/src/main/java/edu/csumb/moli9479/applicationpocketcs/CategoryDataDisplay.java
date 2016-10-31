package edu.csumb.moli9479.applicationpocketcs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class CategoryDataDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_data_display);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.activity_category_data_display);
        HashMap<Integer, Algorithms> algorithms;
        Algorithms algorithms1 = new Algorithms();
        algorithms1.setRuntime("O(nlog(n))");
        algorithms1.setDescription("Quickly sorts an array");
        algorithms1.setHelpfulLink("http://www.cc.gatech.edu/classes/cs3158_98_fall/quicksort.html");
        algorithms1.setCategoryID(1);
        algorithms1.setImage((R.drawable.quicksort_pseudocode + ""));
        algorithms1.setName("Quick Sort");
        algorithms1.setUserID(1);
        OurSQLiteDatabase database = OurSQLiteDatabase.getDatabase(this);

        System.out.println(algorithms1);

        database.addAlgorithm(algorithms1);
        algorithms = database.getAllAlgorithms();

        System.out.println(algorithms);

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(algorithms.get(1).getDescription());

        //linearLayout.addView(description);

        TextView runtime = (TextView)findViewById(R.id.runtime);
        runtime.setText(algorithms.get(1).getRuntime());

        //linearLayout.addView(runtime);

        ImageView pseudocode = (ImageView)findViewById(R.id.pseudocode);
        if(algorithms.get(1).getImage().equals((R.drawable.quicksort_pseudocode))) {
            System.out.println("Pseudocode matches!");
        }
        Drawable image = getResources().getDrawable((R.drawable.quicksort_pseudocode));
        pseudocode.setImageDrawable(image);

        //linearLayout.addView(pseudocode);

        TextView links = (TextView)findViewById(R.id.links);
        links.setText(algorithms.get(1).getHelpfulLink());

        //linearLayout.addView(links);

        setContentView(linearLayout);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /*public static Context getContext() {
        return this;
    }*/

}
