package com.effectivetesting.gluecode;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.effectivetesting.pageobject.EntryListPageObject;
import com.effectivetesting.pageobject.LoginPageObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cucumber.api.java.After;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class SearchEntryStepDefinitions {
	private LoginPageObject loginPage;
	private EntryListPageObject entryListPage;
	private String email;
	private String pass;
	private String title;
	
	@Autowired
	BaseStepDefinitions baseStep;
	
	@Dado("^que el usuario \"([^\"]*)\" publico la entrada \"([^\"]*)\" con tag \"([^\"]*)\"$")
	public void que_el_usuario_publico_la_entrada_con_tag(String email, String title, String tag) throws Throwable {
		this.email = email;
		this.title = title;
		this.pass = "userx";
		
		GlueCodeHelper.createUser("Juan", email, pass);
		GlueCodeHelper.createEntry(title, "this is a tutorial.", "Public", tag);
	}

	@Cuando("^un usuario selecciona el tag \"([^\"]*)\" de la lista de tags del blog$")
	public void un_usuario_selecciona_el_tag_de_la_lista_de_tags_del_blog(String tag) throws Throwable {
		loginPage = new LoginPageObject(baseStep.getDriver());
		entryListPage = loginPage
			.login(this.email, this.pass)
			.openTagList()
			.searchTag(tag)
			.selectTag();
	}

	@Entonces("^se muestra la entrada \"([^\"]*)\" en la lista de entradas publicadas\\.$")
	public void se_muestra_la_entrada_en_la_lista_de_entradas_publicadas(String title) throws Throwable {
		assertTrue(entryListPage.getEntries().contains(title));
	}
	
	@After("@buscarEntrada")
	public void tearDown() throws JsonParseException, JsonMappingException, IOException {
		baseStep.getDriver().quit();
		GlueCodeHelper.deleteUser();
		GlueCodeHelper.deleteEntry(this.title);
	}

}
