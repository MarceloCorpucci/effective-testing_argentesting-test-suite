package com.effectivetesting.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TagListPageObject {
	
	private WebDriver driver;
	
	public TagListPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public TagListPageObject searchTag(String entry) throws IOException {
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
