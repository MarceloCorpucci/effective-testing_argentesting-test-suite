package com.effectivetesting.pageobject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntryListPageObject {

	private WebDriver driver;
	
	public EntryListPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getEntries() {
		return driver.findElement(By.cssSelector("#content_title")).getText();
	}

	public EntryListPageObject searchEntry(String entry) throws IOException {
//		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(file, new File("/tmp/screenshots/testEntry.png"));
		
		WebDriverWait wait = (new WebDriverWait(driver, 15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search_input")));
		
		driver.findElement(By.id("search_input")).sendKeys(entry);
		driver.findElement(By.id("search_button")).click();
		return this;
	}
	
	public EntryPageObject selectEntry(String entry) throws InterruptedException { 
		WebDriverWait wait = (new WebDriverWait(driver, 15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content_title > div > p > a")));
		
		driver.findElement(By.cssSelector("#content_title > div > p > a")).click();
		return new EntryPageObject(driver);
	}
}
