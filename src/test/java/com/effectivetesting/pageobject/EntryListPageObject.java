package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntryListPageObject {

	private WebDriver driver;
	
	public EntryListPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getEntries() {
		return driver.findElement(By.xpath("//*[@id=\"content_title\"]")).getText();
	}

	public EntryListPageObject searchEntry(String entry) {
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/form/div/input")).sendKeys(entry);
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/form/div/span/button")).click();
		return this;
	}
	
	public EntryPageObject selectEntry(String entry) throws InterruptedException { 
		driver.findElement(By.xpath("//*[@id=\"content_title\"]/div/p/a")).click();
		return new EntryPageObject(driver);
	}
}
