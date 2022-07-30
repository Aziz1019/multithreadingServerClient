import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    public static Connection connection = null;

    public static Connection getInstance() throws SQLException {
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/user_book", "postgres", "root123");
        }
        return connection;
    }
}
