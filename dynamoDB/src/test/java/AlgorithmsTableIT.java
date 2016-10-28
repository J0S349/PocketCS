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
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Peeps on 10/25/16.
 */
public class AlgorithmsTableIT {

    private static final String TABLE_NAME = "Algorithms";
    private static final String NAME_COLUMN = "algoName";


    private DBConnector connector;
    private AlgorithmsTable table;

    @Before
    public void connect()
    {
        connector = new DBConnector();
        AlgorithmsTable.openTable(TABLE_NAME, connector).deleteTable();
        table = AlgorithmsTable.createTable(TABLE_NAME, connector);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        // 1: Searching 2: Sorting 3:
        // Adding the same values everytime
        table.put(1,0,"Binary Search", 1 ,"It is used for searching for a value in a sorted list.", "Best Case: log(n) Worst Case: O(n)", "NULL", timeStamp, timeStamp,"NULL");
        table.put(2,0,"Merge Sort", 2 ,"Is able to sort a list of number in increasing order", "Best Case: O( nlog(n)) Worst Case: O(nlog(n))", "NULL", timeStamp, timeStamp,"NULL");
        table.put(3,0,"Quick Sort", 1 ,"Can sort a list of number in increaseing order by using a pivot", "Best Case: O (nlog(n)) Worst Case: O(n*n)", "NULL", timeStamp, timeStamp,"null");
        table.put(4,0,"", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");
        table.put(5,0,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");
        table.put(6,0,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");
        table.put(7,0,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");
        table.put(8,0,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");
        table.put(9,0,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");
        table.put(10,0,"Binary Search", 1 ,"It is a searching algorithm", "Best Case: log(n) Worst Case: O(n)", "blah", "today", "never","blah");

    }

    @After
    public void disconnect() {
        //table.deleteTable(); //error on line: 33
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
