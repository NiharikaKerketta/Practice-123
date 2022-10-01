package com.crm.rmgyantra.TestScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class AddProjectUsingGUI {

	public static void main(String[] args) throws SQLException {
		
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://localhost:8084/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
			driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			driver.findElement(By.xpath("(//i[@class='material-icons'])[1]")).click();
			
			driver.findElement(By.name("projectName")).sendKeys("CISCO");
			driver.findElement(By.name("createdBy")).sendKeys("Raju");
			
			WebElement ele = driver.findElement(By.name("status"));
			Select s = new Select(ele);
			s.selectByVisibleText("Created");
			
			driver.findElement(By.xpath("//input[@value='Add Project']")).click();
			
			driver.quit();
			
			Connection connection = null;
			try {
			//register the database
				String expectedproject_name ="CISCO"; 
				
				Driver driver1 = new Driver();
				DriverManager.registerDriver(driver1);
				
			//establish the connection
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
				
			//issue the statement
				Statement statement = connection.createStatement();
				String query = "select * from project";
				
			//execute the queries
				ResultSet result = statement.executeQuery(query);
				
				while(result.next()) {
					if(result.getString(4).equals(expectedproject_name)) {
						System.out.println("Passed");
					}
				}			
	}
		catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			//close the database connection
			connection.close();
		}
	}
}
