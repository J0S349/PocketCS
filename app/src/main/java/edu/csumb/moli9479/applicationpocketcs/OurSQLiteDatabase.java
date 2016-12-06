package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/6/16.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OurSQLiteDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PocketDB";

    // Table Name - users
    private static final String TABLE_USERS = "users";

    // Columns Names of users Table
    private static final String KEY_ID = "ID";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FACEBOOK_ID = "facebookID";

    private static final String TABLE_SOFTWARE_DESIGN = "softwareDesign";
    // Columns Names of softwareDesign Table
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_USER_ID = "userID";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_BENEFITS = "benefits";
    private static final String KEY_COSTS = "costs";
    private static final String KEY_CATEGORY_ID = "categoryID";
    private static final String KEY_HELPFUL_LINK = "helpfulLink";
    private static final String KEY_DATE_CREATED = "dateCreated";
    private static final String KEY_DATE_UPDATED = "dateUpdated";

    private static final String TABLE_SOFTWARE_DESIGN_CATEGORY = "softwareDesignCategory";
    private static final String TABLE_DATA_STRUCTURES = "dataStructures";
    private static final String KEY_RUNTIME = "runtime";
    private static final String TABLE_DATA_STRUCTURES_CATEGORY = "dataStructuresCategory";
    private static final String TABLE_ALGORITHMS = "algorithms";
    private static final String TABLE_ALGORITHMS_CATEGORY = "algorithmsCategory";

    private static final String TABLE_QUESTIONS = "questions";
    private static final String KEY_QID = "questionid";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_FIRSTOPTION = "firstOption"; // option a
    private static final String KEY_SECONDOPTION= "secondOption"; // option b
    private static final String KEY_THIRDOPTION = "thirdOption"; // option c
    private static final String KEY_QCATEGORY = "qCategory";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Log TAG for debugging purpose
    private static final String TAG = "PocketCSAppLog";

    private static OurSQLiteDatabase database;

    // Constructor
    private OurSQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OurSQLiteDatabase getDatabase(Context context) {
        if(database == null)
            database = new OurSQLiteDatabase(context);
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create a table called "users"
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FIRST_NAME + " TEXT, " +
                KEY_LAST_NAME + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_FACEBOOK_ID + " TEXT )";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_SOFTWARE_DESIGN_TABLE = "CREATE TABLE " + TABLE_SOFTWARE_DESIGN + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_DESCRIPTION + " TEXT, " +
                KEY_USER_ID + " INTEGER, " +
                KEY_IMAGE + " TEXT, " +
                KEY_BENEFITS + " TEXT, " +
                KEY_COSTS + " TEXT, " +
                KEY_CATEGORY_ID + " INTEGER, " +
                KEY_HELPFUL_LINK + " TEXT, " +
                KEY_DATE_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                KEY_DATE_UPDATED + " DATETIME DEFAULT CURRENT_TIMESTAMP )";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_SOFTWARE_DESIGN_TABLE);

        String CREATE_SOFTWARE_DESIGN_CATEGORY_TABLE = "CREATE TABLE " + TABLE_SOFTWARE_DESIGN_CATEGORY + "( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_DESCRIPTION + " TEXT )";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_SOFTWARE_DESIGN_CATEGORY_TABLE);

        String CREATE_DATA_STRUCTURES_TABLE = "CREATE TABLE " + TABLE_DATA_STRUCTURES + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_DESCRIPTION + " TEXT, " +
                KEY_USER_ID + " INTEGER, " +
                KEY_RUNTIME + " TEXT, " +
                KEY_IMAGE + " TEXT, " +
                KEY_CATEGORY_ID + " INTEGER, " +
                KEY_HELPFUL_LINK + " TEXT, " +
                KEY_DATE_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                KEY_DATE_UPDATED + " DATETIME DEFAULT CURRENT_TIMESTAMP )";
        Log.d("SQLite db", CREATE_DATA_STRUCTURES_TABLE);

        // execute an SQL statement to create the table
        db.execSQL(CREATE_DATA_STRUCTURES_TABLE);

        String CREATE_DATA_STRUCTURES_CATEGORY_TABLE = "CREATE TABLE " + TABLE_DATA_STRUCTURES_CATEGORY + "( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_DESCRIPTION + " TEXT )";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_DATA_STRUCTURES_CATEGORY_TABLE);

        String CREATE_ALGORITHMS_TABLE = "CREATE TABLE " + TABLE_ALGORITHMS + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_DESCRIPTION + " TEXT, " +
                KEY_USER_ID + " INTEGER, " +
                KEY_RUNTIME + " TEXT, " +
                KEY_IMAGE + " TEXT," +
                KEY_CATEGORY_ID + " INTEGER, " +
                KEY_HELPFUL_LINK + " TEXT, " +
                KEY_DATE_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                KEY_DATE_UPDATED + " DATETIME DEFAULT CURRENT_TIMESTAMP )";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_ALGORITHMS_TABLE);

        String CREATE_ALGORITHMS_CATEGORY_TABLE = "CREATE TABLE " + TABLE_ALGORITHMS_CATEGORY + "( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_DESCRIPTION + " TEXT )";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_ALGORITHMS_CATEGORY_TABLE);

        Log.d("OurSQLiteDatabase","created db");
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + " ( "
                + KEY_QID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_QUESTION + " TEXT, " +
                KEY_ANSWER + " TEXT, " +
                KEY_FIRSTOPTION + " TEXT, " +
                KEY_SECONDOPTION + " TEXT, " +
                KEY_THIRDOPTION + " TEXT, " +
                KEY_QCATEGORY + " INTEGER)";

        db.execSQL(CREATE_QUESTIONS_TABLE);
        Log.d("OurSQLiteDatabase","created db");
    }

    public void addPredeterminedQuestions(){
        Question q1 = new Question("What is the big-O notation of: " +
                "4n^2 + 3?", "O(n^2)", "O(n)", "O(logn)", "O(n^2)", 1);
        addQuestion(q1);
        Question q2 = new Question("What is the big-O notation of: n^3 + log(^2) + 100nlog(n)",
                "O(n^3)","O(n^2)", "O(n^6)", "O(n^3)", 1);
        addQuestion(q2);
        Question q3 =  new Question("What is the big-O notation of: 4n^10 + 3n^3 + 8n?",
                "O(n^10)","O(n^14)","O(n^10)","O(n^13)",1);
        addQuestion(q3);
        Question q4 = new Question("What is the big-O notation of: log(n) + 23n?",
                "O(n)","O(n^2)", "O(1)","O(n)",1);
        addQuestion(q4);
        Question q5 = new Question("What type of dynamic programming approach does merge sort take?"
                , "Divide and Conquer", "Binary Search", "Divide and Conquer", "Greedy Algorithm",1);
        addQuestion(q5);


        //Data Structure questions.
        Question q6 = new Question("In memory, what data structure is used when using a recursive function"
                , "Stack", "Queue", "Linked-List", "Stack",2);
        addQuestion(q6);
        Question q7 = new Question("What is the minimum # of queues need when implementing a priority queue"
                , "2", "1", "2", "3",2);
        addQuestion(q7);
        Question q8 = new Question("Which data structure is non-linear?"
                , "Trees", "Linked-List", "Trees", "Arrays",2);
        addQuestion(q8);
        Question q9 = new Question("All global variables are stored in _____ memory"
                , "Heap", "Heap", "Static", "Dynamic",2);
        addQuestion(q9);
        Question q10 = new Question("What is the minimum # of nodes that a binary tree can have?"
                , "Zero", "One","Zero", "Two",2);
        addQuestion(q10);


        //Software Design patterns
        Question q11 =  new Question("Which of the following is the correct list of entities of MVC?"
                , "Model,View,Controller", "Model,Viewer,Control", "Model,View,Control","Model,View,Controller",3);
        addQuestion(q11);
        Question q12 = new Question("In which of the following pattern, a class behavior changes based on its state?"
                , "State","State","Null object", "Strategy",3);
        addQuestion(q12);
        Question q13 = new Question("Why are Patterns important?",
                "They make captured design accessible to both novice and other experts",
                "Capture expert design knowledge",
                "None of the above",
                "They make captured design accessible to both novice and other experts",3);
        addQuestion(q13);
        Question q14 = new Question("What is a pattern?"
                , "All of the above", "Solves a software design problem",
                "A model proposed for imitation", "All of the above",3);
        addQuestion(q14);
        Question q15 = new Question("What benefits does patterns provide?"
                , "All of the above",
                "Increase development efficiency", "Promoting communication","All of the above",3);
        addQuestion(q15);

    }


    // onUpdate() is invoked when you upgrade the database scheme.
    // Don’t consider it seriously for the sample app.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_DESIGN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE_DESIGN_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA_STRUCTURES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA_STRUCTURES_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALGORITHMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALGORITHMS_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

        // create fresh database
        this.onCreate(db);
    }

    public void addQuestion(Question question){

        Log.d("addQuestion", "inside addQuestion function");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("addQuestion", "finished getting writable db");

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQUESTION());
        values.put(KEY_ANSWER,question.getANSWER());
        values.put(KEY_FIRSTOPTION,question.getFIRSTOPTION());
        values.put(KEY_SECONDOPTION, question.getSECONDOPTION());
        values.put(KEY_THIRDOPTION,question.getTHIRDOPTION());
        values.put(KEY_QCATEGORY,question.getQCATEGORY());


        //db.insert(TABLE_QUESTIONS,null,values);
        db.insertOrThrow(TABLE_QUESTIONS,null,values);
        db.close();
        Log.d("addQuestion", "after addQuestion function");
    }

    public List<Question> getAlgorithmQuestions(){
        List<Question> questionList = new ArrayList<Question>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_QCATEGORY + " = 1 LIMIT 5";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setQID(cursor.getInt(0));
                question.setQUESTION(cursor.getString(1));
                question.setANSWER(cursor.getString(2));
                question.setFIRSTOPTION(cursor.getString(3));
                question.setSECONDOPTION(cursor.getString(4));
                question.setTHIRDOPTION(cursor.getString(5));
                question.setCATEGORY(cursor.getInt(6));

                questionList.add(question);
            }while (cursor.moveToNext());
        }

        return questionList;
    }

    public List <Question> getDatabaseQuestions(){
        List<Question> databaseList = new ArrayList<Question>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_QCATEGORY + " = 2 LIMIT 5";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setQID(cursor.getInt(0));
                question.setQUESTION(cursor.getString(1));
                question.setANSWER(cursor.getString(2));
                question.setFIRSTOPTION(cursor.getString(3));
                question.setSECONDOPTION(cursor.getString(4));
                question.setTHIRDOPTION(cursor.getString(5));
                question.setCATEGORY(cursor.getInt(6));

                databaseList.add(question);
            }while (cursor.moveToNext());
        }
        return databaseList;

    }

    public List<Question> getSoftwareDesignQuestions(){
        List<Question> softwareList = new ArrayList<Question>();

        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_QCATEGORY + " = 3 LIMIT 5";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setQID(cursor.getInt(0));
                question.setQUESTION(cursor.getString(1));
                question.setANSWER(cursor.getString(2));
                question.setFIRSTOPTION(cursor.getString(3));
                question.setSECONDOPTION(cursor.getString(4));
                question.setTHIRDOPTION(cursor.getString(5));
                question.setCATEGORY(cursor.getInt(6));

                softwareList.add(question);
            }while (cursor.moveToNext());
        }
        return softwareList;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    //---------------------------------------------------------------------

    public void addUser(User user){
        Log.d(TAG, "addUser() - " + user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, user.getFirstName());
        values.put(KEY_LAST_NAME, user.getLastName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_FACEBOOK_ID, user.getFacebookID());

        // 3. insert
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all users from the database
    public HashMap<Integer, User> getAllUsers() {
        HashMap<Integer, User> users = new HashMap<Integer, User>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_USERS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build user and add it to list
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setID(cursor.getInt(0));
                user.setFirstName(cursor.getString(1));
                user.setLastName(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setFacebookID(cursor.getString(4));

                // Add user to users
                users.put(user.getID(), user);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllUsers() - " + users.toString());

        // return users
        return users;
    }

    // Updating single user
    public int updateUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, user.getFirstName());
        values.put(KEY_LAST_NAME, user.getLastName());
        values.put(KEY_EMAIL, user.getEmail());

        // 3. updating row
        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(user.getID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single user
    public void deleteUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_USERS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(user.getID()) });

        // 3. close
        db.close();

        Log.d(TAG, "deleteUser() - " + user.toString());
    }

    //---------------------------------------------------------------------

    public void addSoftwareDesign(SoftwareDesign softwareDesign){
        Log.d(TAG, "addSoftwareDesign() - " + softwareDesign.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, softwareDesign.getName());
        values.put(KEY_DESCRIPTION, softwareDesign.getDescription());
        values.put(KEY_USER_ID, softwareDesign.getUserID());
        values.put(KEY_IMAGE, softwareDesign.getImage());
        values.put(KEY_BENEFITS, softwareDesign.getBenefits());
        values.put(KEY_COSTS, softwareDesign.getCosts());
        values.put(KEY_CATEGORY_ID, softwareDesign.getCategoryID());
        values.put(KEY_HELPFUL_LINK, softwareDesign.getHelpfulLink());
        values.put(KEY_DATE_CREATED, getDateTime());
        values.put(KEY_DATE_UPDATED, getDateTime());

        // 3. insert
        db.insert(TABLE_SOFTWARE_DESIGN, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all software designs from the database
    public HashMap<Integer, SoftwareDesign> getAllSoftwareDesigns() {
        HashMap<Integer, SoftwareDesign> softwareDesigns = new HashMap<Integer, SoftwareDesign>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_SOFTWARE_DESIGN;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build softwareDesign and add it to list
        SoftwareDesign softwareDesign = null;
        if (cursor.moveToFirst()) {
            do {
                softwareDesign = new SoftwareDesign();
                softwareDesign.setID(cursor.getInt(0));
                softwareDesign.setName(cursor.getString(1));
                softwareDesign.setDescription(cursor.getString(2));
                softwareDesign.setUserID(cursor.getInt(3));
                softwareDesign.setImage(cursor.getString(4));
                softwareDesign.setBenefits(cursor.getString(5));
                softwareDesign.setCosts(cursor.getString(6));
                softwareDesign.setCategoryID(cursor.getInt(7));
                softwareDesign.setHelpfulLink(cursor.getString(8));
                softwareDesign.setDateCreated(cursor.getString(9));
                softwareDesign.setDateUpdated(cursor.getString(10));

                // Add softwareDesign to softwareDesigns
                softwareDesigns.put(softwareDesign.getID(), softwareDesign);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllSoftwareDesigns() - " + softwareDesigns.toString());

        // return softwareDesigns
        return softwareDesigns;
    }

    // Updating single softwareDesign
    public int updateSoftwareDesign(SoftwareDesign softwareDesign) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, softwareDesign.getName());
        values.put(KEY_DESCRIPTION, softwareDesign.getDescription());
        values.put(KEY_USER_ID, softwareDesign.getUserID());
        values.put(KEY_IMAGE, softwareDesign.getImage());
        values.put(KEY_BENEFITS, softwareDesign.getBenefits());
        values.put(KEY_COSTS, softwareDesign.getCosts());
        values.put(KEY_CATEGORY_ID, softwareDesign.getCategoryID());
        values.put(KEY_HELPFUL_LINK, softwareDesign.getHelpfulLink());
        values.put(KEY_DATE_CREATED, softwareDesign.getDateCreated());
        values.put(KEY_DATE_UPDATED, getDateTime());


        // 3. updating row
        int i = db.update(TABLE_SOFTWARE_DESIGN, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(softwareDesign.getID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single softwareDesign
    public void deleteSoftwareDesign(SoftwareDesign softwareDesign) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SOFTWARE_DESIGN,
                KEY_ID+" = ?",
                new String[] { String.valueOf(softwareDesign.getID()) });

        // 3. close
        db.close();

        Log.d(TAG, "deleteSoftwareDesign() - " + softwareDesign.toString());
    }

    //---------------------------------------------------------------------

    public void addSoftwareDesignCategory(SoftwareDesignCategory softwareDesignCategory){
        Log.d(TAG, "addSoftwareDesignCategory() - " + softwareDesignCategory.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, softwareDesignCategory.getName());
        values.put(KEY_DESCRIPTION, softwareDesignCategory.getDescription());

        // 3. insert
        db.insert(TABLE_SOFTWARE_DESIGN_CATEGORY, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all software design categories from the database
    public HashMap<Integer, SoftwareDesignCategory> getAllSoftwareDesignCategories() {
        HashMap<Integer, SoftwareDesignCategory> softwareDesignCategories = new HashMap<Integer, SoftwareDesignCategory>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_SOFTWARE_DESIGN_CATEGORY;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build softwareDesign and add it to list
        SoftwareDesignCategory softwareDesignCategory = null;
        if (cursor.moveToFirst()) {
            do {
                softwareDesignCategory = new SoftwareDesignCategory();
                softwareDesignCategory.setID(cursor.getInt(0));
                softwareDesignCategory.setName(cursor.getString(1));
                softwareDesignCategory.setDescription(cursor.getString(2));

                // Add softwareDesignCategory to softwareDesignCategories
                softwareDesignCategories.put(softwareDesignCategory.getID(), softwareDesignCategory);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllSoftwareDesignCategories() - " + softwareDesignCategories.toString());

        // return softwareDesignCategories
        return softwareDesignCategories;
    }

    // Updating single softwareDesignCategory
    public int updateSoftwareDesignCategory(SoftwareDesignCategory softwareDesignCategory) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, softwareDesignCategory.getName());
        values.put(KEY_DESCRIPTION, softwareDesignCategory.getDescription());


        // 3. updating row
        int i = db.update(TABLE_SOFTWARE_DESIGN_CATEGORY, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(softwareDesignCategory.getID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single softwareDesignCategory
    public void deleteSoftwareDesignCategory(SoftwareDesignCategory softwareDesignCategory) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SOFTWARE_DESIGN_CATEGORY,
                KEY_ID+" = ?",
                new String[] { String.valueOf(softwareDesignCategory.getID()) });

        // 3. close
        db.close();

        Log.d(TAG, "deleteSoftwareDesignCategory() - " + softwareDesignCategory.toString());
    }

    //---------------------------------------------------------------------

    public void addDataStructure(DataStructures dataStructure){
        Log.d(TAG, "addDataStructure() - " + dataStructure.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, dataStructure.getName());
        values.put(KEY_DESCRIPTION, dataStructure.getDescription());
        values.put(KEY_USER_ID, dataStructure.getUserID());
        values.put(KEY_RUNTIME, dataStructure.getRuntime());
        values.put(KEY_IMAGE, dataStructure.getImage());
        values.put(KEY_CATEGORY_ID, dataStructure.getCategoryID());
        values.put(KEY_HELPFUL_LINK, dataStructure.getHelpfulLink());
        values.put(KEY_DATE_CREATED, getDateTime());
        values.put(KEY_DATE_UPDATED, getDateTime());

        // 3. insert
        db.insert(TABLE_DATA_STRUCTURES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all data structures from the database
    public HashMap<Integer, DataStructures> getAllDataStructures() {
        HashMap<Integer, DataStructures> dataStructures = new HashMap<Integer, DataStructures>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_DATA_STRUCTURES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build dataStructure and add it to list
        DataStructures dataStructure = null;
        if (cursor.moveToFirst()) {
            do {
                dataStructure = new DataStructures();
                dataStructure.setID(cursor.getInt(0));
                dataStructure.setName(cursor.getString(1));
                dataStructure.setDescription(cursor.getString(2));
                dataStructure.setUserID(cursor.getInt(3));
                dataStructure.setRuntime(cursor.getString(4));
                dataStructure.setImage(cursor.getString(5));
                dataStructure.setCategoryID(cursor.getInt(6));
                dataStructure.setHelpfulLink(cursor.getString(7));
                dataStructure.setDateCreated(cursor.getString(8));
                dataStructure.setDateUpdated(cursor.getString(9));

                // Add dataStructure to dataStructures
                dataStructures.put(dataStructure.getID(), dataStructure);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllDataStructures() - " + dataStructures.toString());

        // return dataStructures
        return dataStructures;
    }

    // Updating single dataStructure
    public int updateDataStructure(DataStructures dataStructure) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, dataStructure.getName());
        values.put(KEY_DESCRIPTION, dataStructure.getDescription());
        values.put(KEY_USER_ID, dataStructure.getUserID());
        values.put(KEY_IMAGE, dataStructure.getImage());
        values.put(KEY_RUNTIME, dataStructure.getRuntime());
        values.put(KEY_CATEGORY_ID, dataStructure.getCategoryID());
        values.put(KEY_HELPFUL_LINK, dataStructure.getHelpfulLink());
        values.put(KEY_DATE_CREATED, dataStructure.getDateCreated());
        values.put(KEY_DATE_UPDATED, getDateTime());


        // 3. updating row
        int i = db.update(TABLE_DATA_STRUCTURES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(dataStructure.getID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single dataStructure
    public void deleteDataStructure(DataStructures dataStructure) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_DATA_STRUCTURES,
                KEY_ID+" = ?",
                new String[] { String.valueOf(dataStructure.getID()) });

        // 3. close
        db.close();

        Log.d(TAG, "deleteDataStructure() - " + dataStructure.toString());
    }

    //---------------------------------------------------------------------

    public void addDataStructuresCategory(DataStructuresCategory dataStructuresCategory){
        Log.d(TAG, "addDataStructuresCategory() - " + dataStructuresCategory.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, dataStructuresCategory.getName());
        values.put(KEY_DESCRIPTION, dataStructuresCategory.getDescription());

        // 3. insert
        db.insert(TABLE_DATA_STRUCTURES_CATEGORY, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all data structures categories from the database
    public HashMap<Integer, DataStructuresCategory> getAllDataStructuresCategories() {
        HashMap<Integer, DataStructuresCategory> dataStructuresCategories = new HashMap<Integer, DataStructuresCategory>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_DATA_STRUCTURES_CATEGORY;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build dataStructuresCategory and add it to list
        DataStructuresCategory dataStructuresCategory = null;
        if (cursor.moveToFirst()) {
            do {
                dataStructuresCategory = new DataStructuresCategory();
                dataStructuresCategory.setID(cursor.getInt(0));
                dataStructuresCategory.setName(cursor.getString(1));
                dataStructuresCategory.setDescription(cursor.getString(2));

                // Add dataStructuresCategory to dataStructuresCategories
                dataStructuresCategories.put(dataStructuresCategory.getID(), dataStructuresCategory);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllDataStructuresCategories() - " + dataStructuresCategories.toString());

        // return dataStructuresCategories
        return dataStructuresCategories;
    }

    // Updating single dataStructuresCategory
    public int updateDataStructuresCategory(DataStructuresCategory dataStructuresCategory) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, dataStructuresCategory.getName());
        values.put(KEY_DESCRIPTION, dataStructuresCategory.getDescription());


        // 3. updating row
        int i = db.update(TABLE_DATA_STRUCTURES_CATEGORY, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(dataStructuresCategory.getID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single dataStructuresCategory
    public void deleteDataStructuresCategory(DataStructuresCategory dataStructuresCategory) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_DATA_STRUCTURES_CATEGORY,
                KEY_ID+" = ?",
                new String[] { String.valueOf(dataStructuresCategory.getID()) });

        // 3. close
        db.close();

        Log.d(TAG, "deleteDataStructuresCategory() - " + dataStructuresCategory.toString());
    }

    //---------------------------------------------------------------------

    public void addAlgorithm(Algorithms algorithm){
        Log.d(TAG, "addAlgorithm() - " + algorithm.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, algorithm.getName());
        values.put(KEY_DESCRIPTION, algorithm.getDescription());
        values.put(KEY_USER_ID, algorithm.getUserID());
        values.put(KEY_IMAGE, algorithm.getImage());
        values.put(KEY_RUNTIME, algorithm.getRuntime());
        values.put(KEY_CATEGORY_ID, algorithm.getCategoryID());
        values.put(KEY_HELPFUL_LINK, algorithm.getHelpfulLink());

        values.put(KEY_DATE_CREATED, getDateTime());
        values.put(KEY_DATE_UPDATED, getDateTime());

        // 3. insert
        db.insert(TABLE_ALGORITHMS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all algorithms from the database
    public HashMap<Integer, Algorithms> getAllAlgorithms() {
        HashMap<Integer, Algorithms> algorithms = new HashMap<Integer, Algorithms>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ALGORITHMS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build dataStructure and add it to list
        Algorithms algorithm = null;
        if (cursor.moveToFirst()) {
            do {
                algorithm = new Algorithms();
                algorithm.setID(cursor.getInt(0));
                algorithm.setName(cursor.getString(1));
                algorithm.setDescription(cursor.getString(2));
                algorithm.setUserID(cursor.getInt(3));
                algorithm.setRuntime(cursor.getString(4));
                algorithm.setImage(cursor.getString(5));
                algorithm.setCategoryID(cursor.getInt(6));
                algorithm.setHelpfulLink(cursor.getString(7));
                algorithm.setDateCreated(cursor.getString(8));
                algorithm.setDateUpdated(cursor.getString(9));

                // Add algorithm to algorithms
                algorithms.put(algorithm.getID(), algorithm);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllAlgorithms() - " + algorithms.toString());

        // return algorithms
        return algorithms;
    }

    // Updating single algorithm
    public int updateAlgorithms(Algorithms algorithm) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, algorithm.getName());
        values.put(KEY_DESCRIPTION, algorithm.getDescription());
        values.put(KEY_USER_ID, algorithm.getUserID());
        values.put(KEY_IMAGE, algorithm.getImage());
        values.put(KEY_RUNTIME, algorithm.getRuntime());
        values.put(KEY_CATEGORY_ID, algorithm.getCategoryID());
        values.put(KEY_HELPFUL_LINK, algorithm.getHelpfulLink());
        values.put(KEY_DATE_CREATED, algorithm.getDateCreated());
        values.put(KEY_DATE_UPDATED, getDateTime());


        // 3. updating row
        int i = db.update(TABLE_ALGORITHMS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(algorithm.getID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single algorithm
    public void deleteAlgorithm(Algorithms algorithm) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ALGORITHMS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(algorithm.getID()) });

        // 3. close
        db.close();

        Log.d(TAG, "deleteAlgorithm() - " + algorithm.toString());
    }

    //---------------------------------------------------------------------

    public void addAlgorithmsCategory(AlgorithmsCategory algorithmsCategory){
        Log.d(TAG, "addAlgorithmsCategory() - " + algorithmsCategory.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, algorithmsCategory.getName());
        values.put(KEY_DESCRIPTION, algorithmsCategory.getDescription());

        // 3. insert
        db.insert(TABLE_ALGORITHMS_CATEGORY, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    // Get all algorithm categories from the database
    public HashMap<Integer, AlgorithmsCategory> getAllAlgorithmCategories() {
        HashMap<Integer, AlgorithmsCategory> algorithmsCategories = new HashMap<Integer, AlgorithmsCategory>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ALGORITHMS_CATEGORY;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build algorithmsCategory and add it to list
        AlgorithmsCategory algorithmsCategory = null;
        if (cursor.moveToFirst()) {
            do {
                algorithmsCategory = new AlgorithmsCategory();
                algorithmsCategory.setID(cursor.getInt(0));
                algorithmsCategory.setName(cursor.getString(1));
                algorithmsCategory.setDescription(cursor.getString(2));

                // Add algorithmsCategory to algorithmsCategories
                algorithmsCategories.put(algorithmsCategory.getID(), algorithmsCategory);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllAlgorithmsCategories() - " + algorithmsCategories.toString());

        // return algorithmsCategories
        return algorithmsCategories;
    }

    // Updating single algorithmCategory
    public int updateAlgorithmsCategory(AlgorithmsCategory algorithmsCategory) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, algorithmsCategory.getName());
        values.put(KEY_DESCRIPTION, algorithmsCategory.getDescription());


        // 3. updating row
        int i = db.update(TABLE_ALGORITHMS_CATEGORY, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(algorithmsCategory.getID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single algorithmsCategory
    public void deleteAlgorithmsCategory(AlgorithmsCategory algorithmsCategory) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ALGORITHMS_CATEGORY,
                KEY_ID+" = ?",
                new String[] { String.valueOf(algorithmsCategory.getID()) });

        // 3. close
        db.close();

        Log.d(TAG, "deleteAlgorithmsCategory() - " + algorithmsCategory.toString());
    }
}
