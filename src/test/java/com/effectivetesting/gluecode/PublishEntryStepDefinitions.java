package com.effectivetesting.gluecode;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.effectivetesting.pageobject.EntryListPageObject;
import com.effectivetesting.pageobject.EntryPageObject;
import com.effectivetesting.pageobject.LoginPageObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cucumber.api.java.After;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class PublishEntryStepDefinitions {
	private LoginPageObject loginPage;
	private EntryPageObject entryPage;
	private EntryListPageObject entryListPage;
	private String title;
	private String email;
	private String pass;
	
	@Autowired
	BaseStepDefinitions baseStep;
	
	@Dado("^que el usuario \"([^\"]*)\" creo la entrada \"([^\"]*)\" en Draft$")
	public void que_el_usuario_creo_la_entrada_en_Draft(String email, String title) throws Throwable {
		this.title = title;
		this.email = email;
		this.pass = "userx";
		
		GlueCodeHelper.createUser("Juan", email, pass);
		GlueCodeHelper.createEntry(title, "this is another test.", "Draft", "Test");
	}

	@Cuando("^hace una publicacion$")
	public void hace_una_publicacion() throws Throwable {
		loginPage = new LoginPageObject(baseStep.getDriver());
		entryPage = loginPage
			.login(this.email, this.pass)
			.openEntryList()
			.searchEntry(this.title)
			.selectEntry(this.title)
			.editEntry()
			.setStatus("Public")
			.saveEntry();
	}
	
	@Entonces("^se muestra el mensaje \"([^\"]*)\"$")
	public void se_muestra_el_mensaje(String expectedMessage) throws Throwable {
		assertTrue(entryPage.getResultMessage().equals(expectedMessage));
	}

	@Entonces("^la entrada se suma a la lista de entradas del blog\\.$")
	public void la_entrada_se_suma_a_la_lista_de_entradas_del_blog() throws Throwable {
		entryListPage = entryPage
				.saveEntry()
				.openEntryList();

		assertTrue(entryListPage.getEntries().contains(this.title));
	}

	@After("@publicarEntrada")
	public void tearDown() throws JsonParseException, JsonMappingException, IOException {
		baseStep.getDriver().quit();
		GlueCodeHelper.deleteUser();
		GlueCodeHelper.deleteEntry(this.title);
	}

}
