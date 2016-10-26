
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.dynamodbv2.datamodeling.unmarshallers.IntegerSetUnmarshaller;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.s3.model.Region;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Peeps on 10/9/16.
 */

public class Tables {

    private int dataStructuresCounter = 1;
    private int algorithmsCounter = 1;

    // This function will create all the tables
    public static void initialize(){
        createAlgorithmsOrDataStructuresTable("Algorithms");
        createAlgorithmsOrDataStructuresTable("Data Structures");
    }

    public static void createAlgorithmsOrDataStructuresTable(String tableName){
        //checks to see whether the tables already exist or not
        if(tableAlreadyExist(tableName))
        {
            return;
        }

        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

        attributeDefinitions.add(new AttributeDefinition()
                                    .withAttributeName("ID")
                                    .withAttributeType(ScalarAttributeType.N)); // makes a ID column of type Number (int)

        // makes a name column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("name")
                .withAttributeType(ScalarAttributeType.S));
        // Makes a description column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("description")
                .withAttributeType(ScalarAttributeType.S));
        // Makes a UserID column of type int
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("userID")
                .withAttributeType(ScalarAttributeType.N));
        // Makes a categoryID column of type int
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("categoryID")
                .withAttributeType(ScalarAttributeType.N));
        // Makes a runtime column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("runtime")
                .withAttributeType(ScalarAttributeType.S));
        // Makes a image column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("image")
                .withAttributeType(ScalarAttributeType.S));
        // Makes a helpfulLink column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("helpfulLink")
                .withAttributeType(ScalarAttributeType.S));
        // Makes dateCreated column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("dateCreated")
                .withAttributeType(ScalarAttributeType.S));
        // Makes dateUpdated column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("dateUpdated")
                .withAttributeType(ScalarAttributeType.S));

        // Create the KeySchema for knowing what is primary key(s) of the table
        ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement()
                        .withAttributeName("ID")
                        .withKeyType(KeyType.HASH));    // Partition Key

        // Now we create a table request so that DynamoDB know that we want to create a table
        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                                            .withReadCapacityUnits(5L)
                                            .withWriteCapacityUnits(6L));

        // Now we create the reques to create the table.
        try {
            DBConnector.getDynamoDB().createTable(request);
            System.out.println();
            } catch (Exception e){
            System.out.println("There was an issue creating the table " + tableName);
            e.printStackTrace();
        }
    }

    // This is used to create category tables
    public static void createCategoryTable(String tableName){
        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("ID")
                .withAttributeType(ScalarAttributeType.N)); // makes a ID column of type Number (int)

        // makes a name column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("name")
                .withAttributeType(ScalarAttributeType.S));
        // Makes a description column of type String
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("description")
                .withAttributeType(ScalarAttributeType.S));

        // Create the KeySchema for knowing what is primary key(s) of the table
        ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement()
                .withAttributeName("ID")
                .withKeyType(KeyType.HASH));    // Partition Key

        // Now we create a table request so that DynamoDB know that we want to create a table
        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));
        // Now we create the request to create the table.
        try {
            DBConnector.getDynamoDB().createTable(request);
            System.out.println();
        } catch (Exception e){
            System.out.println("There was an issue creating the table " + tableName);
            e.printStackTrace();
        }
    }

    // Create the user table
    public static void createUserTable(){
        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("ID")
                .withAttributeType(ScalarAttributeType.N)); // makes a ID column of type Number (int)
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("firstName")
                .withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("lastName")
                .withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("email")
                .withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("facebookID")
                .withAttributeType(ScalarAttributeType.S));

        // Create the KeySchema for knowing what is primary key(s) of the table
        ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement()
                .withAttributeName("ID")
                .withKeyType(KeyType.HASH));    // Partition Key

        // Now we create a table request so that DynamoDB know that we want to create a table
        CreateTableRequest request = new CreateTableRequest()
                .withTableName("user")
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));

        // Now we create the request to create the table.
        try {
            DBConnector.getDynamoDB().createTable(request);
            System.out.println();
        } catch (Exception e){
            System.out.println("There was an issue creating the table user");
            e.printStackTrace();
        }
    }

    // This function will check whether the database contains the table already or not
    public static boolean tableAlreadyExist(String tableName){
        TableCollection<ListTablesResult> tables = DBConnector.getDynamoDB().listTables();
        Iterator<Table> iterator = tables.iterator();

        while(iterator.hasNext()) {
            Table table = iterator.next();
            if (table.getTableName().equals(tableName)) //checks whether the table being created is inside the database
            {
                return true;
            }
        }
        return false; // table doesn't exist
    }

    // Adding information to either a DataStructures Table or Algorithms Table
    public static boolean addDataStructureOrCategory(ArrayList<Object> params){
        // make sure that it contains
        return false;
    }

    // Adding a row to any type of category table (dataStructuresCategory, algorithmsCategory, softwareDesignPatternsCategory)
    public static boolean addCategory(String tableName, int primaryKey, String name, String description)
    {
        Table table = DBConnector.getDynamoDB().getTable(tableName);
        // Try to add into the table
        try{
            Item sessionRow = new Item()
                    .withPrimaryKey("ID", primaryKey)
                    .withString("name", name)
                    .withString("description", description); //creating a session row to add to table
            table.putItem(sessionRow);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // Adding a user to the table
    public static boolean addUser(int ID, String firstName, String lastName, String email, String facebookID)
    {
        Table table = DBConnector.getDynamoDB().getTable("user");
        try{
            Item newUser = new Item()
                    .withPrimaryKey("ID", ID)
                    .withString("firstName", firstName)
                    .withString("lastName", lastName)
                    .withString("email", email)
                    .withString("facebookID", facebookID);
            table.putItem(newUser);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Empty the database
    public static void emptyDB() {
        TableCollection<ListTablesResult> tables = DBConnector.getDynamoDB().listTables();

        // Database is already empty
        if(tables == null)
            return;
        Iterator<Table> iterator = tables.iterator();

        while (iterator.hasNext()) {
            Table table = iterator.next();

            table.delete();
            try {
                // Try deleting the table
                table.waitForDelete();

            } catch(Exception e){
                System.out.println("Error deleting table" + table.getTableName());
            }
        }
    }
}
