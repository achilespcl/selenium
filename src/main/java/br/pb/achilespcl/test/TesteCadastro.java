package br.pb.achilespcl.test;
import static br.pb.achilespcl.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import br.pb.achilespcl.core.BaseTest;
import br.pb.achilespcl.core.DSL;
import br.pb.achilespcl.exception.SexInvalidException;
import br.pb.achilespcl.page.CampoTreinamentoPage;


public class TesteCadastro extends BaseTest {
	
	private DSL dsl;
	private CampoTreinamentoPage ctp;
	
	@Before
	public void init(){
		getDriver().get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		ctp = new CampoTreinamentoPage();
	}
	
	
	private String getXpath(String id){
		return "//*[@id='"+id+"']/span";
	}
	
	
	@Test
	public void deveCadastrar() throws SexInvalidException{
		
		ctp.setName("William");
		ctp.setSurname("Bonner");
		ctp.setSex('m');
		ctp.setFavoriteFood("2");
		ctp.setSchooling("Mestrado");
		ctp.setSports("Corrida");
		ctp.submit();
		
		
		
		Assert.assertEquals(dsl.getTextByXpath(this.getXpath("resultado")), "Cadastrado!");
		Assert.assertEquals(dsl.getTextByXpath(this.getXpath("descNome")), "William");
		Assert.assertEquals(dsl.getTextByXpath(this.getXpath("descSobrenome")), "Bonner");
		Assert.assertEquals(dsl.getTextByXpath(this.getXpath("descSexo")), "Masculino");
		Assert.assertEquals(dsl.getTextByXpath(this.getXpath("descComida")), "Pizza");
		Assert.assertEquals(dsl.getTextByXpath(this.getXpath("descEscolaridade")), "mestrado");
		Assert.assertEquals(dsl.getTextByXpath(this.getXpath("descEsportes")), "Corrida");
	}
	
	
	@Test
	public void deveValidarNomeObrigatorio(){
		dsl.click("elementosForm:cadastrar");
		
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		
		alert.accept();
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio(){
		dsl.write("elementosForm:nome", "William");
		dsl.click("elementosForm:cadastrar");
		
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarSexoObrigatorio(){
		dsl.write("elementosForm:nome", "William");
		dsl.write("elementosForm:sobrenome", "Bonner");
		dsl.click("elementosForm:cadastrar");
		
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		dsl.write("elementosForm:nome", "William");
		dsl.write("elementosForm:sobrenome", "Bonner");
		dsl.click("elementosForm:sexo:0");
		dsl.click("elementosForm:cadastrar");
		dsl.comboSelect("elementosForm:esportes", "Karate");
		dsl.comboSelect("elementosForm:esportes", "O que eh esporte?");
		dsl.click("elementosForm:cadastrar");
		
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
		
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys("William");
		getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("Bonner");
		getDriver().findElement(By.id("elementosForm:sexo:1")).click();
		getDriver().findElement(By.id("elementosForm:comidaFavorita:0")).click();
		getDriver().findElement(By.id("elementosForm:comidaFavorita:3")).click();
		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();
	}
	
}
