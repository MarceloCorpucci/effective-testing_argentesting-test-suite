package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePageObject login(String email, String pass) {
		driver.findElement(By.cssSelector("#login > a")).click();
		driver.findElement(By.cssSelector("#email")).sendKeys(email);
		driver.findElement(By.cssSelector("#password")).sendKeys(pass);
		driver.findElement(By.cssSelector("#btn-submit")).click();
		
		return new HomePageObject(driver);
	}
}
