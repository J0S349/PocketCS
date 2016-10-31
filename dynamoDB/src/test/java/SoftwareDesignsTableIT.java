import com.amazonaws.services.dynamodbv2.document.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by GabrielZapata on 10/30/16.
 */
public class SoftwareDesignsTableIT {
    private static final String TABLE_NAME = "SoftwareDesignTableTest";
    private static final String NAME_COLUMN = "SDName";


    private DBConnector connector;
    private SoftwareDesignsTable table;

    @Before
    public void connect()
    {
        connector = new DBConnector();
        SoftwareDesignsTable.openTable(TABLE_NAME, connector).deleteTable();
        table = SoftwareDesignsTable.createTable(TABLE_NAME, connector);

        // Adding the same values every time
        table.put(2,0, "Singleton", 1, "fill later", "its good", "its bad", "image.png", "01/01/16", "00/00/16", "linktohelp.com");
    }

    @After
    public void disconnect() {
        table.deleteTable();
        connector.close();
    }

    @Test
    public void createAndVerifyTable(){
        Item result = table.get(2);
        String DSName = (String) result.get(NAME_COLUMN);

        assertThat(DSName, equalTo("Singleton"));//<---
    }
    @Test
    public void updateItemOnTable(){
        table.update(2,0, "Singleton Design", 1, "fill later", "its good", "its bad", "image.png", "01/01/16", "00/00/16", "linktohelp.com");
        Item result = table.get(2);
        String SDName = (String) result.get(NAME_COLUMN);

        assertThat(SDName, equalTo("Singleton Design"));//<---
    }

    @Test
    public void deleteItem(){
        table.delete(2);

        assertNull(table.get(2));
    }
}
