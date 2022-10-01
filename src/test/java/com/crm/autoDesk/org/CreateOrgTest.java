package com.crm.autoDesk.org;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException {
		
		//get the java representation object of the physical file
		FileInputStream file = new FileInputStream("./data/commonData.properties");
		
		//create an object of property class to load all the keys
		Properties p = new Properties();
		p.load(file);
		
		//read the values using getProperty ("key")
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		String BROWSER = p.getProperty("browser");
		
		Random ran = new Random();
		int ranDom = ran.nextInt(10000);
		
		FileInputStream file_ex = new FileInputStream("./data/Book01.xlsx");
		Workbook wb = WorkbookFactory.create(file_ex);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(3);
		String orgName = row.getCell(2).getStringCellValue() + ranDom ;
		
		WebDriver driver;
		if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("ie")) {
			driver = new InternetExplorerDriver();
		}
		else {
			driver = new ChromeDriver();
		}
				
		//login
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String status = driver.findElement(By.className("dvHeaderText")).getText();
		if(status.contains(orgName)) {
			System.out.println("Organisation is created...PASS");
		}
		else {
			System.out.println("Organisation is not created... FAIL");
		}
		
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
		
	}

}
