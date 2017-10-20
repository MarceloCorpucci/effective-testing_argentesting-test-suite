package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntryPageObject {
	private WebDriver driver;
	private HeaderSection header;
	
	public EntryPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public EntryPageObject createEntry(String title, String text) {
		driver.findElement(By.id("title")).sendKeys(title);
		driver.findElement(By.id("body")).sendKeys(text);
		return this;
	}
	
	public EntryPageObject addTag(String tag) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("tags")));
		actions.click();
		actions.sendKeys(tag);
		actions.build().perform();
		
		return this;
	}
	
	public EntryPageObject setStatus(String status) {
		Select statusDropDown = new Select(driver.findElement(By.id("status")));
		statusDropDown.selectByVisibleText(status);
		return this;
	}
	
	public EntryPageObject saveEntry() {
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/form/div[5]/div/button")).click();
		
		WebDriverWait wait = (new WebDriverWait(driver, 15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("notification")));
		
		return this;
	}
	
	public EntryListPageObject openEntryList() {
		header = new HeaderSection(driver);
		return header.goToBlog();
	}
	
	public HomePageObject logOut() {
		header = new HeaderSection(driver);
		return header.logOut();
	}
}
