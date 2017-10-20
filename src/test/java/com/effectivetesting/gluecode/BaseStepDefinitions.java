package com.effectivetesting.gluecode;

import static com.github.restdriver.serverdriver.RestServerDriver.delete;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import cucumber.api.java.After;
import cucumber.api.java.Before;

@Component
public class BaseStepDefinitions {
	private String email;
	private String password;
	private WebDriver driver;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(GlueCodeHelper.DEFAULT_BASE_URL);
	}
	
	@After
	public void tearDown() {
		driver.quit();
		delete(GlueCodeHelper.DEFAULT_BASE_URL + "/api/user/" + GlueCodeHelper.USER_ID);
	}	

}
