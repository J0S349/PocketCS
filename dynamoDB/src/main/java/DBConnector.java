
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.s3.model.Region;

/**
 * Created by Peeps on 10/8/16.
 *  This is the class that will allow us to comunicate between the database and our application
 *
 */

public class DBConnector {

    private static DynamoDB dynamoDB;
    private static AmazonDynamoDB client;

    public static void initialize(){
        String access = System.getProperty("access");
        String secret = System.getProperty("secret");
        

        // This will create a new instance of the object in case that it is not created yet
        if(access == null || secret == null || access.equals("") || secret.equals("")){
            client = new AmazonDynamoDBClient(new BasicAWSCredentials("access", "secret"));
            // connect to local dynamoDB server
            client.setEndpoint("http://localhost:8080");
        }
        else {  // This will just set the client object to connect to the dynamo database
            client = new AmazonDynamoDBClient(new BasicAWSCredentials(access, secret));
            client.setRegion(Region.US_West.toAWSRegion());
        }
        dynamoDB = new DynamoDB(client);
    }

    public static void close(){
        dynamoDB.shutdown();
        client.shutdown();
    }

    // returns the dynamo DB instance. Singletons software design pattern
    public static DynamoDB getDynamoDB(){return dynamoDB; } //get dynamoDB instance

}
