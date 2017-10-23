package com.effectivetesting.gluecode;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cucumber.api.java.After;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class SearchEntryStepDefinitions {
	private String title;
	
	@Autowired
	BaseStepDefinitions baseStep;
	
	@Dado("^que el usuario \"([^\"]*)\" publico la entrada \"([^\"]*)\" con tag \"([^\"]*)\"$")
	public void que_el_usuario_publico_la_entrada_con_tag(String email, String title, String tag) throws Throwable {
	}

	@Cuando("^un usuario selecciona el tag \"([^\"]*)\" de la lista de tags del blog$")
	public void un_usuario_selecciona_el_tag_de_la_lista_de_tags_del_blog(String tag) throws Throwable {
	}

	@Entonces("^se muestra la entrada \"([^\"]*)\" en la lista de entradas publicadas\\.$")
	public void se_muestra_la_entrada_en_la_lista_de_entradas_publicadas(String title) throws Throwable {
	}
	
	@After("@buscarEntrada")
	public void tearDown() throws JsonParseException, JsonMappingException, IOException {
		baseStep.getDriver().quit();
		GlueCodeHelper.deleteUser();
		GlueCodeHelper.deleteEntry(this.title);
	}

}
