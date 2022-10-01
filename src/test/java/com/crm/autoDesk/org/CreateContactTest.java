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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactTest {

	public static void main(String[] args) throws IOException {
		
		FileInputStream file = new FileInputStream("./data/commonData.properties");
		Properties p = new Properties();
		p.load(file);
		
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		String BROWSER = p.getProperty("browser");
		
		//get RanDom number
		Random ran = new Random();
		int ranDomNo = ran.nextInt(10000);
		
		
		//read Test data from excel sheet
		FileInputStream file_ex = new FileInputStream("./data/Book02.xlsx");
		 Workbook wb = WorkbookFactory.create(file_ex);
		 Sheet sh = wb.getSheet("Sheet2");
		 Row row = sh.getRow(3);
		String contact = row.getCell(3).getStringCellValue() + ranDomNo;

		
		WebDriver driver;
		if(BROWSER.contains("firefox")) {
			driver=new FirefoxDriver();
		}
		else if (BROWSER.contains("chrome")) {
			driver=new ChromeDriver();
		}
		else if (BROWSER.contains("ie")) {
			driver =new InternetExplorerDriver();
		}
		else {
			driver =new ChromeDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//enter all the details and create a new contact
		driver.findElement(By.name("lastname")).sendKeys(contact);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//verify the contact name in the header of the message
		String status = driver.findElement(By.className("dvHeaderText")).getText();
		if(status.contains(contact)) {
			System.out.println("contact is successfuly created..PASS");
		} else {
			System.out.println("contact is not created..FAIL");
		}
		
		//logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
		
		
		
		
	}

}
