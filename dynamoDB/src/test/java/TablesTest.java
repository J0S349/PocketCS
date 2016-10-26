import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.amazonaws.SystemDefaultDnsResolver;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by Peeps on 10/15/16.
 */
public class TablesTest {

    private Tables db;
    @Before
    public void clearDB(){
       //# DBConnector
                //db = new Tables();
        //db.emptyDB(); // Empty the DB before doing something with it
    }


    @Test
    public void TestSuccessfulTableCreated(){
        /*
        db.createAlgorithmsOrDataStructuresTable("Algorithms");
        db.createAlgorithmsOrDataStructuresTable("Data Structures");
        db.createCategoryTable("AlgorithmsCategory");
        db.createCategoryTable("Data Structures");
        db.createUserTable();

        // Get the number of tables within the DynamoDB instance
        TableCollection<ListTablesResult> dbTables = DBConnector.getDynamoDB().listTables();
        Iterator<Table> iterator = dbTables.iterator();

        while(iterator.hasNext()){

            System.out.println(iterator.next().getTableName());
        }
        */
    }
}
