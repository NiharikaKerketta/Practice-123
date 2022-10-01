package practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class GetTheDataFromTheDataBase {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		
		try {
		//register the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//establish the connection with database
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
		
		//create SQL statement
		Statement statement = connection.createStatement();
		String query = "select * from studentinfo";
		
		//execute the query
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t" + result.getString(4));
		}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		//close the database connection
		connection.close();
		}
	}
}
	


