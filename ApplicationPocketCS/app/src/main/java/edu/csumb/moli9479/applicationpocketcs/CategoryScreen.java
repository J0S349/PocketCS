package edu.csumb.moli9479.applicationpocketcs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class CategoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);

        //Toast.makeText(CategoryScreen.this, "Screen accessed", Toast.LENGTH_LONG).show();
        System.out.println("Screen accessed");

        LinearLayout rootLayout = (LinearLayout)findViewById(R.id.activity_category_screen);
        //ScrollView scrollView = (ScrollView)findViewById(R.id.scrollView);

        switch (getIntent().getExtras().getInt("categoryScreen")) {
            case 0:
                //Toast.makeText(CategoryScreen.this, "Algorithms Screen should show", Toast.LENGTH_LONG).show();
                System.out.println("Algorithms Screen should show");
                String [] algorithmCategories = {"Sorting", "Searching", "String Matching", "Graph Problems"};
                //ImageView pocketCSView = (ImageView)findViewById(R.id.pocketCSView);
                for(int i = 0; i < algorithmCategories.length; i++) {
                    System.out.println("Loop: " + i);
                    //String [] defaultSpinnerValue = {algorithmCategories[i]};
                    String [] defaultSpinnerValue = new String[2];
                    defaultSpinnerValue[0] = algorithmCategories[i];
                    switch(i) {
                        case 0:
                            defaultSpinnerValue[1] = "Quick Sort";
                            break;
                        case 1:
                            defaultSpinnerValue[1] = "Binary Search";
                            break;
                        case 2:
                            defaultSpinnerValue[1] = "Boyer-Moore String Search";
                            break;
                        case 3:
                            defaultSpinnerValue[1] = "Traveling-Salesman Problem";
                            break;
                        default:
                            System.out.println("Unable to add to drop box");
                            break;
                    }
                    System.out.println("Fus: " + i);
                    Spinner newSpinner = new Spinner(this);
                    System.out.println("Roh: " + i);
                    //newSpinner.setId(i);
                    newSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpinnerValue));
                    System.out.println("Dah: " + i);
                    newSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            final Intent intent;
                            switch(position) {
                                case 2:
                                    Toast.makeText(CategoryScreen.this, "Add a new algorithm here", Toast.LENGTH_LONG).show();
                                    break;
                                case 1:
                                    intent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                    startActivity(intent);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    rootLayout.addView(newSpinner);
                    System.out.println("Working: " + i);
                }
                setContentView(rootLayout);
                System.out.println("Done");
                break;
            case 1:
                //Toast.makeText(CategoryScreen.this, "Data Structures Screen should show", Toast.LENGTH_LONG).show();
                System.out.println("Data Structures Screen should show");
                String [] dataStructuresCategories = {"LinkedLists", "Trees", "Sets", "Hashing", "Arrays"};
                //ImageView pocketCSView = (ImageView)findViewById(R.id.pocketCSView);
                for(int i = 0; i < dataStructuresCategories.length; i++) {
                    System.out.println("Loop: " + i);
                    //String [] defaultSpinnerValue = {dataStructuresCategories[i]};
                    String [] defaultSpinnerValue = new String[2];
                    defaultSpinnerValue[0] = dataStructuresCategories[i];
                    switch(i) {
                        case 0:
                            defaultSpinnerValue[1] = "Doubly Linked List";
                            break;
                        case 1:
                            defaultSpinnerValue[1] = "Binary Tree";
                            break;
                        case 2:
                            defaultSpinnerValue[1] = "MultiSet";
                            break;
                        case 3:
                            defaultSpinnerValue[1] = "HashMap";
                            break;
                        case 4:
                            defaultSpinnerValue[1] = "Dynamic Array";
                            break;
                        default:
                            System.out.println("Unable to add to drop box");
                            break;
                    }
                    Spinner newSpinner = new Spinner(this);
                    //newSpinner.setId(i);
                    newSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpinnerValue));
                    newSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            final Intent intent;
                            switch(position) {
                                case 2:
                                    Toast.makeText(CategoryScreen.this, "Add a new data structure here", Toast.LENGTH_LONG).show();
                                    break;
                                case 1:
                                    intent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                    startActivity(intent);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    //newSpinner.setId(i);
                    newSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpinnerValue));
                    rootLayout.addView(newSpinner);
                }
                setContentView(rootLayout);
                break;
            case 2:
                //Toast.makeText(CategoryScreen.this, "Software Design Patterns Screen should show", Toast.LENGTH_LONG).show();
                System.out.println("Software Design Patterns Screen should show");
                String [] softwareDesignPatternsCategories = {"Algorithm Strategy Patterns", "Computational Design Patterns", "Execution Patterns", "Implementation Strategy Patterns", "Structural Design Patterns"};
                //ImageView pocketCSView = (ImageView)findViewById(R.id.pocketCSView);
                for(int i = 0; i < softwareDesignPatternsCategories.length; i++) {
                    System.out.println("Loop: " + i);
                    //String [] defaultSpinnerValue = {softwareDesignPatternsCategories[i]};
                    String [] defaultSpinnerValue = new String[2];
                    defaultSpinnerValue[0] = softwareDesignPatternsCategories[i];
                    switch(i) {
                        case 0:
                            defaultSpinnerValue[1] = "???";
                            break;
                        case 1:
                            defaultSpinnerValue[1] = "???";
                            break;
                        case 2:
                            defaultSpinnerValue[1] = "???";
                            break;
                        case 3:
                            defaultSpinnerValue[1] = "???";
                            break;
                        case 4:
                            defaultSpinnerValue[1] = "???";
                            break;
                        default:
                            System.out.println("Unable to add to drop box");
                            break;
                    }
                    Spinner newSpinner = new Spinner(this);
                    //newSpinner.setId(i);
                    newSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpinnerValue));
                    newSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            final Intent intent;
                            switch(position) {
                                case 2:
                                    Toast.makeText(CategoryScreen.this, "Add a new software design here", Toast.LENGTH_LONG).show();
                                    break;
                                case 1:
                                    intent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                    startActivity(intent);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    //newSpinner.setId(i);
                    newSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpinnerValue));
                    rootLayout.addView(newSpinner);
                }
                setContentView(rootLayout);
                break;
            default:
                //Toast.makeText(CategoryScreen.this, "Error Populating Screen", Toast.LENGTH_LONG).show();
                System.out.println("Error Populating screen");
                break;
        }
        System.out.println("Hello!");
    }
}
