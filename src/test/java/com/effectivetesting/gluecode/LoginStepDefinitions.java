package com.effectivetesting.gluecode;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.effectivetesting.pageobject.LoginPageObject;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class LoginStepDefinitions {
	private LoginPageObject loginPage;
	private String currentMessage;

	
	@Autowired
	BaseStepDefinitions baseStep;
	
	@Dado("^el usuario \"([^\"]*)\" con email \"([^\"]*)\" y password \"([^\"]*)\"$")
	public void el_usuario_con_email_y_password(String userName, String email, String password) throws Throwable {
		baseStep.setEmail(email);
		baseStep.setPassword(password);
        
		GlueCodeHelper.createUser(userName, email, password);
	}

	@Cuando("^se loguea en el blog$")
	public void se_loguea_en_el_blog() throws Throwable {
		loginPage = new LoginPageObject(baseStep.getDriver());
		currentMessage = loginPage
			.login(baseStep.getEmail(), baseStep.getPassword())
			.getStatusMessage();
	}

	@Entonces("^obtiene el mensaje \"([^\"]*)\" en pantalla$")
	public void obtiene_el_mensaje_en_pantalla(String expectedMessage) throws Throwable {
		Assert.assertEquals(expectedMessage, currentMessage);
	}
	
}
