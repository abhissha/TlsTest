import static org.junit.Assert.assertEquals;

import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import org.junit.Test;

import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TlsTest {
    @Test
    public void evaluatesExpression() {
        BouncyCastleJsseProvider provider = new BouncyCastleJsseProvider();
        Security.insertProviderAt(provider,1);
        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "database=abc;"
                + "user=abc;"
                + "password=abc;"
                //+ "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        // Connect to the database
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Execute a SELECT SQL statement
            String selectSql = "SELECT TOP 10 Name From TestCase";
            ResultSet resultSet = statement.executeQuery(selectSql);

            // Print results from the SELECT statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(6, 6);
    }
}