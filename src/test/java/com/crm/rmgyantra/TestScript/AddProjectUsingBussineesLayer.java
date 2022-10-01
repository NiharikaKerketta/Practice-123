package com.crm.rmgyantra.TestScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class AddProjectUsingBussineesLayer {

	public static void main(String[] args) throws SQLException {
		
		Connection connection =null;
		try {
			//register the database
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			//establish the connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			
			//issue the statement
			Statement statement = connection.createStatement();
			String query = "insert into project(project_id, created_by, created_on, project_name, status, team_size) value('TY_PROJ_012', 'Riya', '16/12/2021', 'ONGC', 'Completed', '50')";
			
			//execute the queries
			int result = statement.executeUpdate(query);
			
			//verification
			if(result == 1) {
				System.out.println("One data got added into the database");
			}
			else {
				System.out.println("No data got added into the database");
			}
		}
		
		catch  (Exception e) {
			e.getStackTrace();
		}
	
		
		finally {
			//close the connection
			connection.close();
		}
	}
}
