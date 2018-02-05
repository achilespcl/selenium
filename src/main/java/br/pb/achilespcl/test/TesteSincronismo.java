package br.pb.achilespcl.test;
import static br.pb.achilespcl.core.DriverFactory.getDriver;
import static br.pb.achilespcl.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.pb.achilespcl.core.DSL;


public class TesteSincronismo {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void init(){
		dsl = new DSL();
	}
	
	@After
	public void finish(){
		killDriver();
	}

	@Test
	public void deveInteragirComEsperaFixa() throws InterruptedException{
		dsl.click("buttonDelay");
		Thread.sleep(5000);
		dsl.write("novoCampo", "DeuCerto");
	}
	
	@Test
	public void deveInteragirComEsperaImplicita() throws InterruptedException{
		getDriver().manage().timeouts().implicitlyWait(5,  TimeUnit.SECONDS);
		dsl.click("buttonDelay");
		dsl.write("novoCampo", "DeuCerto");
		driver.manage().timeouts().implicitlyWait(0,  TimeUnit.SECONDS);
	}
	
	@Test
	public void deveInteragirComEsperaExplicita() throws InterruptedException{
		dsl.click("buttonDelay");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.write("novoCampo", "DeuCerto");
	}
	
	@Test
	public void deveInteragirComAjax(){
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl.write("j_idt116:name", "Teste");
		dsl.click("j_idt116:j_idt119");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("j_idt116:display"), "Teste"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt129")));
		Assert.assertEquals("Teste", dsl.getText("j_idt116:display"));
		

	}
}
