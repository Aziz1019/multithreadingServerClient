import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static Connection con = null;

    public static void main(String[] args) throws SQLException {
        con = PostgresConnection.getInstance();
        viewCat();
    }

    public static void viewCat(){
        String sql = "select * from users";
        try {
            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.println(id + " " + first_name + " ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
