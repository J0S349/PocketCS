package edu.csumb.moli9479.applicationpocketcs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class CategoryInputScreen extends AppCompatActivity implements OnClickListener{

    private EditText categoryName;
    private EditText categoryDescription;
    private EditText categoryRuntime;
    private EditText categoryBenefits;
    private EditText categoryCosts;
    private EditText categoryImage;
    private EditText categoryLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_input_screen);

        LinearLayout editLayout = (LinearLayout)findViewById(R.id.editLayout);
        categoryName = new EditText(this);
        categoryDescription = new EditText(this);
        categoryRuntime = new EditText(this);
        categoryBenefits = new EditText(this);
        categoryCosts = new EditText(this);
        categoryImage = new EditText(this);
        categoryLink = new EditText(this);

        switch (getIntent().getExtras().getInt("categoryNumber")) {
            case 0:
                categoryName.setHint("Enter the name of your new algorithm");

                categoryDescription.setHint("Enter the algorithm's description");

                categoryRuntime.setHint("Enter the algorithm's runtime");

                categoryImage.setHint("Enter the algorithm's pseudocode image");

                categoryLink.setHint("Enter the algorithm's helpful link");

                editLayout.addView(categoryName);
                editLayout.addView(categoryDescription);
                editLayout.addView(categoryRuntime);
                editLayout.addView(categoryImage);
                editLayout.addView(categoryLink);


                break;
            case 1:
                categoryName.setHint("Enter the name of your new data structure");

                categoryDescription.setHint("Enter the data structure's description");

                categoryRuntime.setHint("Enter the data structure's runtime");

                categoryImage.setHint("Enter the data structure's pseudocode image");

                categoryLink.setHint("Enter the data structure's helpful link");

                editLayout.addView(categoryName);
                editLayout.addView(categoryDescription);
                editLayout.addView(categoryRuntime);
                editLayout.addView(categoryImage);
                editLayout.addView(categoryLink);
                break;
            case 2:
                categoryName.setHint("Enter the name of your new software design pattern");

                categoryDescription.setHint("Enter the software design pattern's description");

                categoryBenefits.setHint("Enter the software design pattern's benefits");

                categoryCosts.setHint("Enter the sofware design pattern's costs");

                categoryImage.setHint("Enter the software design pattern's pseudocode image");

                categoryLink.setHint("Enter the software design pattern's helpful link");

                editLayout.addView(categoryName);
                editLayout.addView(categoryDescription);
                editLayout.addView(categoryBenefits);
                editLayout.addView(categoryCosts);
                editLayout.addView(categoryImage);
                editLayout.addView(categoryLink);
                break;
            default:
                System.out.println("Error, layout cannot be displayed.");
                break;
        }
        Button addButton = (Button)findViewById(R.id.addButton);
        Button cancelButton = (Button)findViewById(R.id.cancelButton);

        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }


    public void onClick(View v) {
        Bundle extraInfo = new Bundle();
        Intent intent = new Intent(this, CategoryDataDisplay.class);
        switch (v.getId()) {
            case R.id.addButton:
                extraInfo.putInt("categoryToAdd", getIntent().getExtras().getInt("categoryNumber"));
                extraInfo.putInt("categoryInputNumber", getIntent().getExtras().getInt("categoryInputNumber"));
                extraInfo.putBoolean("isAdding", true);
                extraInfo.putString("categoryName", categoryName.getText().toString());
                extraInfo.putString("categoryDescription", categoryDescription.getText().toString());
                extraInfo.putString("categoryImage", categoryImage.getText().toString());
                extraInfo.putString("categoryLink", categoryLink.getText().toString());
                if(getIntent().getExtras().getInt("categoryNumber") == 2) {
                    extraInfo.putString("categoryBenefits", categoryBenefits.getText().toString());
                    extraInfo.putString("categoryCosts", categoryCosts.getText().toString());
                } else {
                    extraInfo.putString("categoryRuntime", categoryRuntime.getText().toString());
                }
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            case R.id.cancelButton:
                extraInfo.putBoolean("isAdding", false);
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
