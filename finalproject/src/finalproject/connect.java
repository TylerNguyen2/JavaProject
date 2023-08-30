package finalproject;
import java.sql.*;


public class connect {
	public static Connection ConnectionDb() {
		
		String url = "jdbc:mysql://localhost:3306/new_schema";
		String username = "root";
        String password = "mar092002";

        System.out.println("Connecting database...");

        try(Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }		
	}

}
