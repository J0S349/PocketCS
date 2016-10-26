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

/**
 * Created by Peeps on 10/25/16.
 */
public class AlgorithmsTableIT {
    private static final String TABLE_NAME = "movies_dev";

    private DBConnector connector;
    private AlgorithmsTableIT table;

    @Before
    public void connect()
    {
        connector = new DBConnector();
        table = new AlgorithmsTableIT();
    }

}
