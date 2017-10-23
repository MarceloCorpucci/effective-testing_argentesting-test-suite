package com.effectivetesting.gluecode;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.After;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class LoginStepDefinitions {
	
	@Autowired
	BaseStepDefinitions baseStep;
	
	@Dado("^el usuario \"([^\"]*)\" con email \"([^\"]*)\" y password \"([^\"]*)\"$")
	public void el_usuario_con_email_y_password(String userName, String email, String password) throws Throwable {
	}

	@Cuando("^se loguea en el blog$")
	public void se_loguea_en_el_blog() throws Throwable {
	}

	@Entonces("^obtiene el mensaje \"([^\"]*)\" en pantalla$")
	public void obtiene_el_mensaje_en_pantalla(String expectedMessage) throws Throwable {
	}
	
	@After("@login")
	public void tearDown() {
		baseStep.getDriver().quit();
		GlueCodeHelper.deleteUser();
	}	
}
