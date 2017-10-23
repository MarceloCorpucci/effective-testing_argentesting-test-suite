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
		driver.findElement(By.cssSelector("#title")).sendKeys(title);
		driver.findElement(By.cssSelector("#body")).sendKeys(text);
		return this;
	}
	
	public EntryPageObject addTag(String tag) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.cssSelector("#tags")));
		actions.click();
		actions.sendKeys(tag);
		actions.build().perform();
		
		return this;
	}
	
	public EntryPageObject setStatus(String status) {
		Select statusDropDown = new Select(driver.findElement(By.cssSelector("#status")));
		statusDropDown.selectByVisibleText(status);
		return this;
	}
	
	public EntryPageObject saveEntry() {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebDriverWait wait = (new WebDriverWait(driver, 15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#notification")));
		
		return this;
	}
	
	public EntryPageObject editEntry() {
		driver.findElement(By.cssSelector("body > div.container > div:nth-child(1) > div.col-md-3 > li:nth-child(4) > a")).click();
		return this;
	}
	
	public String getResultMessage() {
		return driver.findElement(By.cssSelector("#notification > span")).getText();
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
