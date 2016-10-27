import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.amazonaws.SystemDefaultDnsResolver;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Peeps on 10/25/16.
 */
public class AlgorithmsTableIT {

    private static final String TABLE_NAME = "Algorithms";

    private DBConnector connector;
    private AlgorithmsTable table;

    @Before
    public void connect()
    {
        connector = new DBConnector();
        AlgorithmsTable.openTable(TABLE_NAME, connector).deleteTable();
        table = AlgorithmsTable.openTable(TABLE_NAME, connector);
        //table = AlgorithmsTable.createTable(TABLE_NAME, connector);
        assert(table != null);
    }

    @After
    public void disconnect() {
        table.deleteTable(); //error on line: 33
        connector.close();
    }

    @Test
    public void createAndVerifyTable(){
        table.put(1,1,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");
        //error line: 41
        //String data = table.get(1);

    }

}
