package com.effectivetesting.gluecode;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cucumber.api.java.After;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class PublishEntryStepDefinitions {
	private String title;
	
	@Autowired
	BaseStepDefinitions baseStep;
	
	@Dado("^que el usuario \"([^\"]*)\" creo la entrada \"([^\"]*)\" en Draft$")
	public void que_el_usuario_creo_la_entrada_en_Draft(String email, String title) throws Throwable {
	}

	@Cuando("^hace una publicacion$")
	public void hace_una_publicacion() throws Throwable {
	}
	
	@Entonces("^se muestra el mensaje \"([^\"]*)\"$")
	public void se_muestra_el_mensaje(String expectedMessage) throws Throwable {
	}

	@Entonces("^la entrada se suma a la lista de entradas del blog\\.$")
	public void la_entrada_se_suma_a_la_lista_de_entradas_del_blog() throws Throwable {
	}

	@After("@publicarEntrada")
	public void tearDown() throws JsonParseException, JsonMappingException, IOException {
		baseStep.getDriver().quit();
		GlueCodeHelper.deleteUser();
		GlueCodeHelper.deleteEntry(this.title);
	}

}
