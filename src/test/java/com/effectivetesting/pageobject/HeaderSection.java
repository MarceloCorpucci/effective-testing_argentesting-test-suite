package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderSection {

	private WebDriver driver;
	
	public HeaderSection(WebDriver driver) {
		this.driver = driver;
	}
	
	public EntryListPageObject goToBlog() {
		driver.findElement(By.id("blog")).click();
		return new EntryListPageObject(driver);
	}
	
	public HomePageObject logOut() {
		driver.findElement(By.id("logout")).click();
		return new HomePageObject(driver);		
	}
}