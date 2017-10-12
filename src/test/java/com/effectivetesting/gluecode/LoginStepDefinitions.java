package com.effectivetesting.gluecode;

import static com.github.restdriver.serverdriver.RestServerDriver.body;
import static com.github.restdriver.serverdriver.RestServerDriver.delete;
import static com.github.restdriver.serverdriver.RestServerDriver.post;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.effectivetesting.entities.User;
import com.effectivetesting.pageobject.LoginPageObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class LoginStepDefinitions {
	private static final String ID = "23";
	private static final String DEFAULT_BASE_URL = "http://localhost:8000";
	private WebDriver driver;
	private LoginPageObject loginPage;
	private String currentMessage;
	private String email;
	private String password;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(DEFAULT_BASE_URL);
	}
	
	@Dado("^el usuario \"([^\"]*)\" con email \"([^\"]*)\" y password \"([^\"]*)\"$")
	public void el_usuario_con_email_y_password(String userName, String email, String password) throws Throwable {
		this.email = email;	
        this.password = password;
        
		User user = createTestObject(userName, email, password);
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = mapper.writeValueAsString(user);
		
		post(DEFAULT_BASE_URL + "/api/user", body(jsonInString, "application/json"));
	}

	@Cuando("^se loguea en el blog$")
	public void se_loguea_en_el_blog() throws Throwable {
		loginPage = new LoginPageObject(driver);
		currentMessage = loginPage
			.login(email, password)
			.getStatusMessage();
	}

	@Entonces("^obtiene el mensaje \"([^\"]*)\" en pantalla$")
	public void obtiene_el_mensaje_en_pantalla(String expectedMessage) throws Throwable {
		Assert.assertEquals(expectedMessage, currentMessage);
	}
	

	@After
	public void tearDown() {
		driver.quit();
		delete(DEFAULT_BASE_URL + "/api/user/" + ID);
	}
	
	private User createTestObject(String userName, String email, String password) {
		User user = new User();

		user.setId(ID);
		user.setEmail(email);
		user.setpassword_hash(password);
		user.setName(userName);
		
		return user;
	}
}
