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

import java.util.ArrayList;

public class CategoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);

        //Toast.makeText(CategoryScreen.this, "Screen accessed", Toast.LENGTH_LONG).show();
        System.out.println("Screen accessed");

        LinearLayout spinnerLayout = (LinearLayout)findViewById(R.id.spinnerLayout);

        boolean dropBoxIsCreated;

        switch (getIntent().getExtras().getInt("categoryScreen")) {
            case 0:
                //Toast.makeText(CategoryScreen.this, "Algorithms Screen should show", Toast.LENGTH_LONG).show();
                System.out.println("Algorithms Screen should show");
                String [] algorithmCategories = {"Sorting", "Searching", "String Matching", "Graph Problems", "Optimization"};
                dropBoxIsCreated = createDefaultDropBox(algorithmCategories, 0, spinnerLayout);
                System.out.println(dropBoxIsCreated);
                break;
            case 1:
                //Toast.makeText(CategoryScreen.this, "Data Structures Screen should show", Toast.LENGTH_LONG).show();
                System.out.println("Data Structures Screen should show");
                String [] dataStructuresCategories = {"LinkedLists", "Trees", "Sets", "Hashing", "Arrays"};
                dropBoxIsCreated = createDefaultDropBox(dataStructuresCategories, 1, spinnerLayout);
                System.out.println(dropBoxIsCreated);
                break;
            case 2:
                //Toast.makeText(CategoryScreen.this, "Software Design Patterns Screen should show", Toast.LENGTH_LONG).show();
                System.out.println("Software Design Patterns Screen should show");
                String [] softwareDesignPatternsCategories = {"Algorithm Strategy Patterns", "Computational Design Patterns", "Execution Patterns", "Implementation Strategy Patterns", "Structural Design Patterns"};
                dropBoxIsCreated = createDefaultDropBox(softwareDesignPatternsCategories, 2, spinnerLayout);
                break;
            default:
                //Toast.makeText(CategoryScreen.this, "Error Populating Screen", Toast.LENGTH_LONG).show();
                dropBoxIsCreated = false;
                System.out.println("Error Populating screen");
                break;
        }
        System.out.println(dropBoxIsCreated);
    }

    public boolean createDefaultDropBox(String []categories, int caseNumber, LinearLayout spinnerLayout) {
        ArrayList<String> categoryDefaultSpinnerValues = new ArrayList<String>();
        switch (caseNumber) {
            case 0:
                categoryDefaultSpinnerValues.add("Quick Sort");
                categoryDefaultSpinnerValues.add("Binary Search");
                categoryDefaultSpinnerValues.add("Boyer-Moore String Search");
                categoryDefaultSpinnerValues.add("Traveling-Salesman Problem");
                categoryDefaultSpinnerValues.add("First fit");
                break;
            case 1:
                categoryDefaultSpinnerValues.add("Doubly Linked List");
                categoryDefaultSpinnerValues.add("Binary Tree");
                categoryDefaultSpinnerValues.add("MultiSet");
                categoryDefaultSpinnerValues.add("HashMap");
                categoryDefaultSpinnerValues.add("Dynamic Array");
                break;
            case 2:
                categoryDefaultSpinnerValues.add("???");
                categoryDefaultSpinnerValues.add("???");
                categoryDefaultSpinnerValues.add("???");
                categoryDefaultSpinnerValues.add("???");
                categoryDefaultSpinnerValues.add("???");
                break;
            default:
                System.out.println("Case number is out of bounds");
                return false;
        }
        for (int i = 0; i < categories.length; i++) {
            String[] defaultSpinnerValue = new String[2];
            defaultSpinnerValue[0] = categories[i];
            switch (i) {
                case 0:
                    defaultSpinnerValue[1] = categoryDefaultSpinnerValues.get(i);
                    break;
                case 1:
                    defaultSpinnerValue[1] = categoryDefaultSpinnerValues.get(i);
                    break;
                case 2:
                    defaultSpinnerValue[1] = categoryDefaultSpinnerValues.get(i);
                    break;
                case 3:
                    defaultSpinnerValue[1] = categoryDefaultSpinnerValues.get(i);
                    break;
                default:
                    System.out.println("Unable to add to drop box");
                    return false;
            }
            Spinner newSpinner = new Spinner(this);
            newSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpinnerValue));
            newSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    final Intent intent;
                    switch (position) {
                        case 2:
                            Toast.makeText(CategoryScreen.this, "Add a new category here", Toast.LENGTH_LONG).show();
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
            spinnerLayout.addView(newSpinner);
        }
        return true;
    }
}
