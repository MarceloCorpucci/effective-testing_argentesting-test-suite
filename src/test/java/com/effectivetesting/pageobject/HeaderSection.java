package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderSection {

	private WebDriver driver;
	
	public HeaderSection(WebDriver driver) {
		this.driver = driver;
	}
	
	public EntryListPageObject goToBlog() {
		driver.findElement(By.cssSelector("#blog")).click();
		return new EntryListPageObject(driver);
	}
	
	public TagListPageObject goToTags() {
		driver.findElement(By.cssSelector("#tag")).click();
		return new TagListPageObject(driver);
	}
	
	public HomePageObject logOut() {
		driver.findElement(By.cssSelector("#logout")).click();
		return new HomePageObject(driver);		
	}
}