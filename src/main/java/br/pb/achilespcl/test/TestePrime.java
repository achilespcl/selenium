package br.pb.achilespcl.test;
import static br.pb.achilespcl.core.DriverFactory.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pb.achilespcl.core.DSL;

public class TestePrime {
	
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
	public void deveInteragirComRadioPrime(){
		dsl.findByXpath(".//table[@id='j_idt117:console']/tbody/tr/td[1]/div//span").click();
		
		Assert.assertTrue(dsl.isChecked("j_idt117:console:0"));
	}
	
	@Test
	public void deveInteragirComRadioPrimePS4(){
		dsl.findByXpath("//label[.='PS4']/..//span").click();
		
		Assert.assertTrue(dsl.isChecked("j_idt117:console:1"));
	}
	
	@Test
	public void deveInteragirComCombo(){
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.findByXpath("//*[@id='j_idt117:console_label']/..").click();
		dsl.click("j_idt117:console_3");
		Assert.assertEquals(dsl.getSelected("j_idt117:console_input"), "Wii U");
	}
}
