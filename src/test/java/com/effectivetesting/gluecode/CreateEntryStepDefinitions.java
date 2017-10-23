package com.effectivetesting.gluecode;

import static org.junit.Assert.assertFalse;
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
import cucumber.api.java.es.Entonces;

public class CreateEntryStepDefinitions {
	private LoginPageObject loginPage;
	private EntryPageObject entryPage;
	private EntryListPageObject entryListPage;
	private String title;
	
	@Autowired
	BaseStepDefinitions baseStep;
	
	@Cuando("^Crea la entrada \"([^\"]*)\" con el texto \"([^\"]*)\"$")
	public void crea_la_entrada_con_el_texto(String title, String body) throws Throwable {
		this.title = title;
		
		loginPage = new LoginPageObject(baseStep.getDriver());
		entryPage = loginPage
			.login(baseStep.getEmail(), baseStep.getPassword())
			.goToCreateEntry()
			.createEntry(title, body);
	}

	@Entonces("^la entrada queda publicada en la lista de entradas del blog\\.$")
	public void la_entrada_queda_publicada_en_la_lista_de_entradas_del_blog() throws Throwable {
		entryListPage = entryPage
							.saveEntry()
							.openEntryList();
		
		assertTrue(entryListPage.getEntries().contains(this.title));
	}

	@Cuando("^le agrega el tag \"([^\"]*)\"$")
	public void le_agrega_el_tag(String tag) throws Throwable {
		entryPage.addTag(tag);
	}

	@Cuando("^la asigna la entrada como Draft$")
	public void la_asigna_la_entrada_como_Draft() throws Throwable {
		entryPage
			.setStatus("Draft")
			.saveEntry();
	}

	@Entonces("^la entrada no es visible por otros usuarios$")
	public void la_entrada_no_es_visible_por_otros_usuarios() throws Throwable {
	    String currentEntries = entryPage
	    	.logOut()
	    	.openEntryList()
	    	.getEntries();
	    
		assertFalse(currentEntries.contains(this.title));
	}
	
	@After("@crearEntrada")
	public void tearDown() throws JsonParseException, JsonMappingException, IOException {
		baseStep.getDriver().quit();
		GlueCodeHelper.deleteUser();
		GlueCodeHelper.deleteEntry(this.title);
	}
}
