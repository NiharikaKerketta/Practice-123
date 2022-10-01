package practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertTheDataFromTheDataBase {

	public static void main(String[] args) throws SQLException {
		
		//register the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//establish the connection with database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
		
		//issue the statement
		Statement statement = connection.createStatement();
		String query = "insert into studentinfo(id, fname, lname, address) values ('6', 'jhon', 'louis', 'china')";
		
		//execute the queries
		int result = statement.executeUpdate(query);
		
		//verification
		if(result == 2) {
			System.out.println("==>two database added<==");
		}
		else {
			System.out.println("==>Data is not added to the database<== ");
		}
		
		//close the database connection
		connection.close();
		
	}

}
