package edu.csumb.moli9479.applicationpocketcs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.koushikdutta.ion.Ion;
import java.util.HashMap;

public class CategoryDataDisplay extends AppCompatActivity implements OnClickListener{

    private int categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_data_display);

        Button previous = (Button)findViewById(R.id.previousActivity);
        Button mainMenu = (Button)findViewById(R.id.mainMenu);

        previous.setOnClickListener(this);
        mainMenu.setOnClickListener(this);

        OurSQLiteDatabase database = OurSQLiteDatabase.getDatabase(this);

        try {
            if(getIntent().getExtras().getBoolean("isAdding")) {
                System.out.println(getIntent().getExtras().getInt("categoryToAdd"));
                System.out.println(getIntent().getExtras().getString("categoryName"));
                System.out.println(getIntent().getExtras().getString("categoryDescription"));
                System.out.println(getIntent().getExtras().getString("categoryImage"));
                System.out.println(getIntent().getExtras().getString("categoryLink"));

                categoryID = getIntent().getExtras().getInt("categoryToAdd");

                switch (getIntent().getExtras().getInt("categoryToAdd")) {
                    case 0:
                        Algorithms algorithm = new Algorithms();
                        algorithm.setName(getIntent().getExtras().getString("categoryName"));
                        algorithm.setDescription(getIntent().getExtras().getString("categoryDescription"));
                        algorithm.setRuntime(getIntent().getExtras().getString("categoryRuntime"));
                        algorithm.setImage(getIntent().getExtras().getString("categoryImage"));
                        algorithm.setHelpfulLink(getIntent().getExtras().getString("categoryLink"));
                        algorithm.setCategoryID(getIntent().getExtras().getInt("categoryInputNumber"));
                        algorithm.setUserID(database.getAllAlgorithms().size());

                        database.addAlgorithm(algorithm);

                        displayAlgorithmData(algorithm);
                        break;
                    case 1:
                        DataStructures dataStructures = new DataStructures();
                        dataStructures.setName(getIntent().getExtras().getString("categoryName"));
                        dataStructures.setDescription(getIntent().getExtras().getString("categoryDescription"));
                        dataStructures.setRuntime(getIntent().getExtras().getString("categoryRuntime"));
                        dataStructures.setImage(getIntent().getExtras().getString("categoryImage"));
                        dataStructures.setHelpfulLink(getIntent().getExtras().getString("categoryLink"));
                        dataStructures.setCategoryID(getIntent().getExtras().getInt("categoryInputNumber"));

                        database.addDataStructure(dataStructures);

                        displayDataStructureData(dataStructures);
                        break;
                    case 2:
                        SoftwareDesign softwareDesign = new SoftwareDesign();
                        softwareDesign.setName(getIntent().getExtras().getString("categoryName"));
                        softwareDesign.setDescription(getIntent().getExtras().getString("categoryDescription"));
                        softwareDesign.setBenefits(getIntent().getExtras().getString("categoryBenefits"));
                        softwareDesign.setCosts(getIntent().getExtras().getString("categoryCosts"));
                        softwareDesign.setImage(getIntent().getExtras().getString("categoryImage"));
                        softwareDesign.setHelpfulLink(getIntent().getExtras().getString("categoryLink"));
                        softwareDesign.setCategoryID(getIntent().getExtras().getInt("categoryInputNumber"));
                        softwareDesign.setUserID(database.getAllSoftwareDesigns().size());

                        database.addSoftwareDesign(softwareDesign);

                        displaySoftwareDesignPatternData(softwareDesign);
                        break;
                    default:
                        System.out.println("Error adding to database");
                        break;
                }
            } else {
                categoryID = getIntent().getExtras().getInt("categoryID");
                switch (getIntent().getExtras().getInt("categoryID")) {
                    case 0:
                        HashMap<Integer, Algorithms> algorithms = database.getAllAlgorithms();
                        for (HashMap.Entry<Integer, Algorithms> algorithm : algorithms.entrySet()) {
                            if(algorithm.getValue().getName().equals(getIntent().getExtras().getString("algorithmName"))) {
                                displayAlgorithmData(algorithm.getValue());
                                break;
                            }
                        }
                        break;
                    case 1:
                        HashMap<Integer, DataStructures> dataStructures = database.getAllDataStructures();
                        for (HashMap.Entry<Integer, DataStructures> dataStructure : dataStructures.entrySet()) {
                            if(dataStructure.getValue().getName().equals(getIntent().getExtras().getString("dataStructureName"))) {
                                displayDataStructureData(dataStructure.getValue());
                                break;
                            }
                        }
                        break;
                    case 2:
                        HashMap<Integer, SoftwareDesign> softwareDesignPatterns = database.getAllSoftwareDesigns();
                        for (HashMap.Entry<Integer, SoftwareDesign> softwareDesign : softwareDesignPatterns.entrySet()) {
                            if(softwareDesign.getValue().getName().equals(getIntent().getExtras().getString("softwareDesignName"))) {
                                displaySoftwareDesignPatternData(softwareDesign.getValue());
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e) {
            System.out.println("Addition cancelled");
            categoryID = getIntent().getExtras().getInt("categoryID");
        }
        //http://bit.ly/2fXkXLa
        //algorithms1.setHelpfulLink("http://www.cc.gatech.edu/classes/cs3158_98_fall/quicksort.html");
        //http://b.gatech.edu/2gLvfPF
        //http://bit.ly/2gNmW3x
    }

    public void displayAlgorithmData(Algorithms algorithm) {

        System.out.println(algorithm);

        TextView name = (TextView)findViewById((R.id.name));
        name.setText(algorithm.getName());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(algorithm.getDescription());

        TextView runtime = (TextView)findViewById(R.id.runtime);
        runtime.setText(algorithm.getRuntime());

        ImageView pseudocode = (ImageView)findViewById(R.id.pseudocode);
        try {
            Ion.with(pseudocode).load(algorithm.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView links = (TextView)findViewById(R.id.links);
        links.setText(algorithm.getHelpfulLink());
        Linkify.addLinks(links, Linkify.ALL);
        links.setLinksClickable(true);
        links.setMovementMethod(LinkMovementMethod.getInstance());
        links.setHighlightColor(Color.BLUE);
    }

    public void displayDataStructureData(DataStructures dataStructure) {

        System.out.println(dataStructure);

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(dataStructure.getName());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(dataStructure.getDescription());

        TextView runtime = (TextView)findViewById(R.id.runtime);
        runtime.setText(dataStructure.getRuntime());

        ImageView pseudocode = (ImageView)findViewById(R.id.pseudocode);
        try {
            Ion.with(pseudocode).load(dataStructure.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView links = (TextView)findViewById(R.id.links);
        links.setText(dataStructure.getHelpfulLink());
        Linkify.addLinks(links, Linkify.ALL);
        links.setLinksClickable(true);
        links.setMovementMethod(LinkMovementMethod.getInstance());
        links.setHighlightColor(Color.BLUE);
    }

    public void displaySoftwareDesignPatternData(SoftwareDesign softwareDesign) {

        System.out.println(softwareDesign);

        LinearLayout costLayout = (LinearLayout)findViewById(R.id.costs);

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(softwareDesign.getName());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(softwareDesign.getDescription());

        TextView benefits = (TextView)findViewById(R.id.runtime);
        benefits.setText(softwareDesign.getBenefits());

        TextView costs = new TextView(this);
        costs.setText(softwareDesign.getCosts());
        costLayout.addView(costs);

        ImageView pseudocode = (ImageView)findViewById(R.id.pseudocode);
        try {
            Ion.with(pseudocode).load(softwareDesign.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView links = (TextView)findViewById(R.id.links);
        links.setText(softwareDesign.getHelpfulLink());
        Linkify.addLinks(links, Linkify.ALL);
        links.setLinksClickable(true);
        links.setMovementMethod(LinkMovementMethod.getInstance());
        links.setHighlightColor(Color.BLUE);
    }

    public void onClick(View v) {
        Intent intent;
        Bundle extraInfo = new Bundle();
        switch (v.getId()) {
            case R.id.previousActivity:
                intent = new Intent(this, CategoryScreen.class);
                extraInfo.putInt("categoryScreen", categoryID);
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            case R.id.mainMenu:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
