package com.effectivetesting.gluecode;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

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

	@Before("@regresion")
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
		driver = new ChromeDriver();
		
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setJavascriptEnabled(true);                
//		caps.setCapability("takesScreenshot", true);  
//		caps.setCapability(
//		                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//		                        "/usr/local/share/phantomjs-1.9.8-linux-x86_64/bin/phantomjs"
//		                    );
//		driver = new PhantomJSDriver(caps);
		
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(GlueCodeHelper.DEFAULT_BASE_URL);
	}

}
