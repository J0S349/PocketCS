import com.amazonaws.services.dynamodbv2.document.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by GabrielZapata on 10/29/16.
 */
public class DataStructuresTableIT {

    private static final String TABLE_NAME = "DataStructuresTableTest";
    private static final String NAME_COLUMN = "DSName";


    private DBConnector connector;
    private DataStructuresTable table;

    @Before
    public void connect()
    {
        connector = new DBConnector();
        DataStructuresTable.openTable(TABLE_NAME, connector).deleteTable();
        table = DataStructuresTable.createTable(TABLE_NAME, connector);

        // Adding the same values every time
        table.put(2,0,"hash map",2, "description","O(n)","image.png","01/01/16", "00/00/00","linktohelp.com");
    }

    @After
    public void disconnect() {
        table.deleteTable(); //error on line: 33
        connector.close();
    }

    @Test
    public void createAndVerifyTable(){
        Item result = table.get(2);
        String DSName = (String) result.get(NAME_COLUMN);

        assertThat(DSName, equalTo("hash map"));
    }
    @Test
    public void updateItemOnTable(){
        table.update(2,0,"hash table",2, "description","O(n)","image.png","01/01/16", "00/00/00","linktohelp.com");
        Item result = table.get(2);
        String DSName = (String) result.get(NAME_COLUMN);

        assertThat(DSName, equalTo("hash table"));
    }

    @Test
    public void deleteItem(){
        table.delete(2);

        assertNull(table.get(2));
    }
}
