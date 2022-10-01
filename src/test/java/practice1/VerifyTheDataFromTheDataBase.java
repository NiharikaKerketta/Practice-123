package practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class VerifyTheDataFromTheDataBase {

	public static void main(String[] args) throws SQLException {
		String expectedId="2";
		//register the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//establish the connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
		
		//issue the statement
		Statement statement = connection.createStatement();
		String query = "select * from studentinfo";
		
		//execute queries
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			if(result.getString(2).equals(expectedId));
			{
				System.out.println("passed");
			}
		}
		
		//close the database connection
		connection.close();
	}

}
