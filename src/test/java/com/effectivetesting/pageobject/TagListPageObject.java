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

public class TagListPageObject {
	
	private WebDriver driver;
	
	public TagListPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public TagListPageObject searchTag(String entry) throws IOException {
//		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(file, new File("/tmp/screenshots/testTag.png"));
		
		WebDriverWait wait = (new WebDriverWait(driver, 15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search_input")));
		
		driver.findElement(By.cssSelector("#search_input")).sendKeys(entry);
		driver.findElement(By.cssSelector("#search_button")).click();
		return this;
	}
	
	public EntryListPageObject selectTag() {
		driver.findElement(By.cssSelector("#content_title > ul > li > a")).click();
		return new EntryListPageObject(driver);
	}
}
