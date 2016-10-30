import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.dynamodbv2.datamodeling.unmarshallers.IntegerSetUnmarshaller;
import com.amazonaws.services.dynamodbv2.datamodeling.unmarshallers.StringSetUnmarshaller;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.s3.model.Region;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Peeps on 10/25/16.
 */
public class AlgorithmsTable {
    private static final String KEY_COLUMN = "algoID";
    private static final String USER_ID_COLUMN = "userID";
    private static final String NAME_COLUMN = "algoName";
    private static final String CATEGORY_ID_COLUMN = "categoryID";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String RUNTIME_COLUMN = "runtime";
    private static final String IMAGE_ID_COLUMN = "imageID";
    private static final String DATE_CREATED_COLUMN = "dateCreated";
    private static final String DATE_UPDATED_COLUMN = "dateUpdated";
    private static final String HELPFUL_LINK_COLUMN = "helpfulLink";

    private Table table;

    public static AlgorithmsTable createTable(String tableName, DBConnector connector){

        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName(KEY_COLUMN)
                .withAttributeType(ScalarAttributeType.N));

        // Create the KeySchema for knowing what is primary key(s) of the table
         KeySchemaElement keySchema = new KeySchemaElement()
                 .withAttributeName(KEY_COLUMN)
                .withKeyType(KeyType.HASH); //Partition key

        KeySchemaElement sortSchema = new KeySchemaElement().clone()
                .withAttributeName(NAME_COLUMN)
                .withKeyType(KeyType.RANGE);

        // Now we create a table request so that DynamoDB know that we want to create a table
        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(keySchema)
                //.withKeySchema(sortSchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));

        // Now we create the request to create the table.
        Table table;
        try {
            table = connector.getDynamoDB().createTable(request);
        } catch (Exception e){

            e.printStackTrace();
            return null;
        }

        try {
            table.waitForActive();

        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return new AlgorithmsTable(table);

    }

    private AlgorithmsTable(Table table){
        this.table = table;
    }

    public static AlgorithmsTable openTable(String tableName, DBConnector connector){
        Table table = connector.getDynamoDB().getTable(tableName);
        return new AlgorithmsTable(table);
    }

    public void put(long id, long userID, String algoName, int categoryID, String description, String runtime,
                    String imageID, String dateCreated, String dateUpdated, String helpfulLink)
    {

        Item sessionRow = new Item()
                .withPrimaryKey(KEY_COLUMN, id)
                .withLong(USER_ID_COLUMN, userID)
                .withString(NAME_COLUMN, algoName)
                .withInt(CATEGORY_ID_COLUMN, categoryID)
                .withString(DESCRIPTION_COLUMN, description)
                .withString(RUNTIME_COLUMN, runtime)
                .withString(IMAGE_ID_COLUMN, imageID)
                .withString(DATE_CREATED_COLUMN, dateCreated)
                .withString(DATE_UPDATED_COLUMN, dateUpdated)
                .withString(HELPFUL_LINK_COLUMN, helpfulLink);
        try{
            table.putItem(sessionRow);

        } catch (Exception e){
            System.out.println("Error adding row to table");
            e.printStackTrace();
        }
    }

    public void update(
            long id, long userID, String algoName, int categoryID, String description, String runtime,
            String imageID, String dateCreated, String dateUpdated, String helpfulLink
    )
    {
        // Taking advantage of tables ability to add / update an item that is entered into the table
       put(id, userID, algoName, categoryID, description, runtime, imageID, dateCreated, dateUpdated, helpfulLink);

    }

    public Item get(long id){

        Item item = table.getItem(KEY_COLUMN, id);
        return item;
    }
    public void delete(long id) {
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey(KEY_COLUMN, id);
        table.deleteItem(deleteItemSpec);
    }

    public boolean deleteTable() {
        try {
            table.delete();
        } catch (ResourceNotFoundException ex) {
            return false; // didn't exist
        }
        try {
            table.waitForDelete();
        } catch (InterruptedException ex) {
            // See http://www.yegor256.com/2015/10/20/interrupted-exception.html
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
        return true; // Success table deletion
    }
}

