package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TagListPageObject {
	
	private WebDriver driver;
	
	public TagListPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public TagListPageObject searchTag(String entry) {
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/form/div/input")).sendKeys(entry);
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/form/div/span/button")).click();
		return this;
	}
	
	public EntryListPageObject selectTag() {
		driver.findElement(By.xpath("//*[@id=\"content_title\"]/ul/li/a")).click();
		return new EntryListPageObject(driver);
	}
}
