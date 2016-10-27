import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.amazonaws.SystemDefaultDnsResolver;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.scene.control.Tab;
import jdk.nashorn.internal.parser.JSONParser;
import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.util.ajax.JSONObjectConvertor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Peeps on 10/25/16.
 */
public class AlgorithmsTableIT {

    private static final String TABLE_NAME = "AlgorithmTableTest";
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

    private DBConnector connector;
    private AlgorithmsTable table;

    @Before
    public void connect()
    {
        connector = new DBConnector();
        AlgorithmsTable.openTable(TABLE_NAME, connector).deleteTable();
        table = AlgorithmsTable.createTable(TABLE_NAME, connector);

        // Adding in a value every time
        table.put(2,1,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");

    }

    @After
    public void disconnect() {
        table.deleteTable(); //error on line: 33
        connector.close();
    }

    @Test
    public void createAndVerifyTable(){
        Item result = table.get(2);
        String algoName = (String) result.get(NAME_COLUMN);

        assertThat(algoName, equalTo("Binary Search"));

    }

    @Test
    public void updateItemOnTable(){
        table.update(2,1,"Binary Search Algorithm", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");

        Item result = table.get(2);
        String algoName = (String) result.get(NAME_COLUMN);

        assertThat(algoName, equalTo("Binary Search Algorithm"));
    }

    @Test
    public void deleteItem(){

        table.delete(2);

        assertNull(table.get(2));
    }

}
