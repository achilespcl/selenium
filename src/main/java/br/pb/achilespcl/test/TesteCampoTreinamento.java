package br.pb.achilespcl.test;
import static br.pb.achilespcl.core.DriverFactory.getDriver;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.pb.achilespcl.core.BaseTest;
import br.pb.achilespcl.core.DSL;
import br.pb.achilespcl.page.CampoTreinamentoPage;

public class TesteCampoTreinamento extends BaseTest{
	
	private DSL dsl;
	private CampoTreinamentoPage ctp;
	
	@Before
	public void init(){
		dsl = new DSL();
		ctp = new CampoTreinamentoPage();
	}

	@Test
	public void testeTextField(){
		ctp.setName("Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", dsl.getValue("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea(){
		dsl.write("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", getDriver().findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}
	
	@Test
	public void deveInteragirComRadioButton(){
		dsl.click("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isChecked("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComCheckBox(){
		dsl.click("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isChecked("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveInteragirComComboBox(){
		dsl.comboSelect("elementosForm:escolaridade", "2o grau completo");
//		combo.selectByIndex(2);
//		combo.selectByValue("Superior");
		Assert.assertEquals("2o grau completo", dsl.getSelected("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		Select combo = new Select(dsl.findById("elementosForm:escolaridade"));
//		combo.selectByIndex(2);
//		combo.selectByValue("Superior");
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean find = false;
		for (WebElement option : options) {
			if(option.getText().equals("Mestrado")){
				find = true;
				break;
			}
		}
		
		Assert.assertTrue(find);
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.comboSelect("elementosForm:esportes", "Natacao");
		dsl.comboSelect("elementosForm:esportes", "Corrida");
		dsl.comboSelect("elementosForm:esportes", "O que eh esporte?");
		WebElement element = dsl.findById("elementosForm:esportes");
		Select combo = new Select(element);
		List<WebElement> old = combo.getAllSelectedOptions();
		Assert.assertEquals(3, old.size());
	}
	
	@Test
	public void deveInteragirComBotoes(){
		dsl.click("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.getValue("buttonSimple"));
	}
	
	@Test
	public void deveInteragirComLinks(){
		dsl.goToLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.getText("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
		WebElement titleText = dsl.findByTag("h3");
		WebElement pageText = dsl.findByClass("facilAchar");
//		Assert.assertTrue(pageText.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", titleText.getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", pageText.getText());
	}

	@Test 
	public void deveClicarBotaoTabela(){
		dsl.clickBtnTable("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
}
