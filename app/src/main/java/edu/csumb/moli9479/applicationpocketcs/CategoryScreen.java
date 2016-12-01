package edu.csumb.moli9479.applicationpocketcs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class CategoryScreen extends AppCompatActivity implements OnClickListener{

    private String [] categories;
    private boolean [] isClickedTwice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);

        LinearLayout spinnerLayout = (LinearLayout)findViewById(R.id.spinnerLayout);
        Button addButton = (Button)findViewById(R.id.addCategory);
        Button editButton = (Button)findViewById(R.id.editCategory);
        boolean dropBoxIsCreated;

        addButton.setOnClickListener(this);
        editButton.setOnClickListener(this);

        isClickedTwice = new boolean[5];
        isClickedTwice[0] = false;
        isClickedTwice[1] = false;
        isClickedTwice[2] = false;
        isClickedTwice[3] = false;
        isClickedTwice[4] = false;

        switch (getIntent().getExtras().getInt("categoryScreen")) {
            case 0:
                String [] algorithmCategories = {"Sorting", "Searching", "String Matching", "Graph Problems", "Optimization"};
                categories = algorithmCategories;
                dropBoxIsCreated = createDefaultAlgorithmButton(algorithmCategories, spinnerLayout);
                break;
            case 1:
                String [] dataStructuresCategories = {"LinkedLists", "Trees", "Sets", "Hashing", "Arrays"};
                categories = dataStructuresCategories;
                dropBoxIsCreated = createDefaultDataStructureButton(dataStructuresCategories, spinnerLayout);
                break;
            case 2:
                String [] softwareDesignPatternsCategories = {"Algorithm Strategy Patterns", "Computational Design Patterns", "Execution Patterns", "Implementation Strategy Patterns", "Structural Design Patterns"};
                categories = softwareDesignPatternsCategories;
                dropBoxIsCreated = createDefaultSoftwareDesignPatternButton(softwareDesignPatternsCategories, spinnerLayout);
                break;
            default:
                dropBoxIsCreated = false;
                System.out.println("Error Populating screen");
                break;
        }


    }

    public boolean createDefaultAlgorithmButton(String []categories, LinearLayout spinnerLayout) {
        for (int i = 0; i < categories.length; i++) {
            if(!displayButton(spinnerLayout, categories[i], i)) {
                return false;
            }
        }
        return true;
    }

    public boolean createDefaultDataStructureButton(String []categories, LinearLayout spinnerLayout) {
        for (int i = 0; i < categories.length; i++) {
            if(!displayButton(spinnerLayout, categories[i], i)) {
                return false;
            }
        }
        return true;
    }

    public boolean createDefaultSoftwareDesignPatternButton(String []categories, LinearLayout spinnerLayout) {
        for (int i = 0; i < categories.length; i++) {
            if(!displayButton(spinnerLayout, categories[i], i)) {
                return false;
            }
        }
        return true;
    }

    public boolean displayButton(LinearLayout spinnerLayout, String defaultButtonValue, int id) {
        Button newButton = new Button(this);
        newButton.setText(defaultButtonValue);
        newButton.setId(categories.length + id);
        newButton.setOnClickListener(this);

        LinearLayout fragmentLayout = new LinearLayout(this);
        fragmentLayout.setOrientation(LinearLayout.VERTICAL);
        fragmentLayout.setId((2 * categories.length) + id);

        spinnerLayout.addView(newButton);
        spinnerLayout.addView(fragmentLayout);
        return true;
    }

    public void onClick(View v) {
        final Bundle extraInfo = new Bundle();
        final Intent intent = new Intent(this, CategoryInputScreen.class);
        LinearLayout linearLayout;
        switch (v.getId()) {
            case R.id.addCategory:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                LinearLayout diagLayout = new LinearLayout(this);
                diagLayout.setOrientation(LinearLayout.VERTICAL);
                TextView textView = new TextView(this);

                switch (getIntent().getExtras().getInt("categoryScreen")) {
                    case 0:
                        textView.setText("Choose an algorithm category to add to:");
                        break;
                    case 1:
                        textView.setText("Choose a data structure category to add to:");
                        break;
                    case 2:
                        textView.setText("Choose a software design category to add to:");
                        break;
                    default:
                        break;
                }

                // set prompts.xml to alertdialog builder
                diagLayout.addView(textView);
                if(categories != null) {
                    for(int i = 0; i < categories.length; i++) {
                        Button button = new Button(this);
                        button.setText(categories[i]);
                        button.setId(i);
                        button.setOnClickListener(this);
                        diagLayout.addView(button);
                    }
                }

                alertDialogBuilder.setView(diagLayout);

                // set dialog message
                alertDialogBuilder.setCancelable(true).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                // create and show alert dialog
                alertDialogBuilder.create().show();
                break;
            case R.id.editCategory:
                Intent defaultDataIntent = new Intent(this, CategoryDataDisplay.class);
                startActivity(defaultDataIntent);
                break;
            case 0:
                extraInfo.putInt("categoryNumber", getIntent().getExtras().getInt("categoryScreen"));
                extraInfo.putInt("categoryInputNumber", v.getId());
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            case 1:
                extraInfo.putInt("categoryNumber", getIntent().getExtras().getInt("categoryScreen"));
                extraInfo.putInt("categoryInputNumber", v.getId());
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            case 2:
                extraInfo.putInt("categoryNumber", getIntent().getExtras().getInt("categoryScreen"));
                extraInfo.putInt("categoryInputNumber", v.getId());
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            case 3:
                extraInfo.putInt("categoryNumber", getIntent().getExtras().getInt("categoryScreen"));
                extraInfo.putInt("categoryInputNumber", v.getId());
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            case 4:
                extraInfo.putInt("categoryNumber", getIntent().getExtras().getInt("categoryScreen"));
                extraInfo.putInt("categoryInputNumber", v.getId());
                intent.putExtras(extraInfo);
                startActivity(intent);
                break;
            case 5:
                linearLayout = (LinearLayout)findViewById(v.getId() + 5);
                if(isClickedTwice[v.getId() - 5]) {
                    linearLayout.removeAllViews();
                    isClickedTwice[v.getId() - 5] = false;
                } else {
                    OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);
                    if (getIntent().getExtras().getInt("categoryScreen") == 0) {
                        final HashMap<Integer, Algorithms> algorithms = db.getAllAlgorithms();
                        for (HashMap.Entry<Integer, Algorithms> algorithm : algorithms.entrySet()) {
                            if(algorithm.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = algorithm.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(algorithms.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("algorithmName", algorithms.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 1) {
                        final HashMap<Integer, DataStructures> dataStructures = db.getAllDataStructures();
                        for (HashMap.Entry<Integer, DataStructures> dataStructure : dataStructures.entrySet()) {
                            if(dataStructure.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = dataStructure.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(dataStructures.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("dataStructureName", dataStructures.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 2) {
                        final HashMap<Integer, SoftwareDesign> softwareDesigns = db.getAllSoftwareDesigns();
                        for (HashMap.Entry<Integer, SoftwareDesign> softwareDesign : softwareDesigns.entrySet()) {
                            if(softwareDesign.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = softwareDesign.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(softwareDesigns.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("softwareDesignName", softwareDesigns.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    }
                    isClickedTwice[v.getId() - 5] = true;
                }
                break;
            case 6:
                linearLayout = (LinearLayout)findViewById(v.getId() + 5);
                if(isClickedTwice[v.getId() - 5]) {
                    linearLayout.removeAllViews();
                    isClickedTwice[v.getId() - 5] = false;
                } else {
                    OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);
                    if (getIntent().getExtras().getInt("categoryScreen") == 0) {
                        final HashMap<Integer, Algorithms> algorithms = db.getAllAlgorithms();
                        for (HashMap.Entry<Integer, Algorithms> algorithm : algorithms.entrySet()) {
                            if(algorithm.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = algorithm.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(algorithms.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("algorithmName", algorithms.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 1) {
                        final HashMap<Integer, DataStructures> dataStructures = db.getAllDataStructures();
                        for (HashMap.Entry<Integer, DataStructures> dataStructure : dataStructures.entrySet()) {
                            if(dataStructure.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = dataStructure.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(dataStructures.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("dataStructureName", dataStructures.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 2) {
                        final HashMap<Integer, SoftwareDesign> softwareDesigns = db.getAllSoftwareDesigns();
                        for (HashMap.Entry<Integer, SoftwareDesign> softwareDesign : softwareDesigns.entrySet()) {
                            if(softwareDesign.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = softwareDesign.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(softwareDesigns.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("softwareDesignName", softwareDesigns.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    }
                    isClickedTwice[v.getId() - 5] = true;
                }
                break;
            case 7:
                linearLayout = (LinearLayout)findViewById(v.getId() + 5);
                if(isClickedTwice[v.getId() - 5]) {
                    linearLayout.removeAllViews();
                    isClickedTwice[v.getId() - 5] = false;
                } else {
                    OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);
                    if (getIntent().getExtras().getInt("categoryScreen") == 0) {
                        final HashMap<Integer, Algorithms> algorithms = db.getAllAlgorithms();
                        for (HashMap.Entry<Integer, Algorithms> algorithm : algorithms.entrySet()) {
                            if(algorithm.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = algorithm.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(algorithms.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("algorithmName", algorithms.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 1) {
                        final HashMap<Integer, DataStructures> dataStructures = db.getAllDataStructures();
                        for (HashMap.Entry<Integer, DataStructures> dataStructure : dataStructures.entrySet()) {
                            if(dataStructure.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = dataStructure.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(dataStructures.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("dataStructureName", dataStructures.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 2) {
                        final HashMap<Integer, SoftwareDesign> softwareDesigns = db.getAllSoftwareDesigns();
                        for (HashMap.Entry<Integer, SoftwareDesign> softwareDesign : softwareDesigns.entrySet()) {
                            if(softwareDesign.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = softwareDesign.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(softwareDesigns.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("softwareDesignName", softwareDesigns.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    }
                    isClickedTwice[v.getId() - 5] = true;
                }
                break;
            case 8:
                linearLayout = (LinearLayout)findViewById(v.getId() + 5);
                if(isClickedTwice[v.getId() - 5]) {
                    linearLayout.removeAllViews();
                    isClickedTwice[v.getId() - 5] = false;
                } else {
                    OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);
                    if (getIntent().getExtras().getInt("categoryScreen") == 0) {
                        final HashMap<Integer, Algorithms> algorithms = db.getAllAlgorithms();
                        for (HashMap.Entry<Integer, Algorithms> algorithm : algorithms.entrySet()) {
                            if(algorithm.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = algorithm.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(algorithms.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("algorithmName", algorithms.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 1) {
                        final HashMap<Integer, DataStructures> dataStructures = db.getAllDataStructures();
                        for (HashMap.Entry<Integer, DataStructures> dataStructure : dataStructures.entrySet()) {
                            if(dataStructure.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = dataStructure.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(dataStructures.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("dataStructureName", dataStructures.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 2) {
                        final HashMap<Integer, SoftwareDesign> softwareDesigns = db.getAllSoftwareDesigns();
                        for (HashMap.Entry<Integer, SoftwareDesign> softwareDesign : softwareDesigns.entrySet()) {
                            if(softwareDesign.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = softwareDesign.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(softwareDesigns.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("softwareDesignName", softwareDesigns.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    }
                    isClickedTwice[v.getId() - 5] = true;
                }
                break;
            case 9:
                linearLayout = (LinearLayout)findViewById(v.getId() + 5);
                if(isClickedTwice[v.getId() - 5]) {
                    linearLayout.removeAllViews();
                    isClickedTwice[v.getId() - 5] = false;
                } else {
                    OurSQLiteDatabase db = OurSQLiteDatabase.getDatabase(this);
                    if (getIntent().getExtras().getInt("categoryScreen") == 0) {
                        final HashMap<Integer, Algorithms> algorithms = db.getAllAlgorithms();
                        for (HashMap.Entry<Integer, Algorithms> algorithm : algorithms.entrySet()) {
                            if(algorithm.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = algorithm.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(algorithms.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("algorithmName", algorithms.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 1) {
                        final HashMap<Integer, DataStructures> dataStructures = db.getAllDataStructures();
                        for (HashMap.Entry<Integer, DataStructures> dataStructure : dataStructures.entrySet()) {
                            if(dataStructure.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = dataStructure.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(dataStructures.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("dataStructureName", dataStructures.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    } else if (getIntent().getExtras().getInt("categoryScreen") == 2) {
                        final HashMap<Integer, SoftwareDesign> softwareDesigns = db.getAllSoftwareDesigns();
                        for (HashMap.Entry<Integer, SoftwareDesign> softwareDesign : softwareDesigns.entrySet()) {
                            if(softwareDesign.getValue().getCategoryID() == v.getId() - 5) {
                                final int key = softwareDesign.getKey();
                                CardView cardView = new CardView(this);
                                cardView.setContentPadding(10, 10, 10, 10);
                                TextView test = new TextView(this);
                                test.setText(softwareDesigns.get(key).getName());
                                test.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                cardView.addView(test);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        extraInfo.putInt("categoryID", getIntent().getExtras().getInt("categoryScreen"));
                                        extraInfo.putString("softwareDesignName", softwareDesigns.get(key).getName());
                                        Intent newIntent = new Intent(CategoryScreen.this, CategoryDataDisplay.class);
                                        newIntent.putExtras(extraInfo);
                                        startActivity(newIntent);
                                    }
                                });
                                linearLayout.addView(cardView);
                            }
                        }
                    }
                    isClickedTwice[v.getId() - 5] = true;
                }
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

}
