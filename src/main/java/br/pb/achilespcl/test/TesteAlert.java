package br.pb.achilespcl.test;
import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
	
	@Test
	public void deveInteragirComAlertSimples(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(640, 480));
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement btn_alert = driver.findElement(By.id("alert"));
		btn_alert.click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertConfirm(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(640, 480));
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement btn_confirm = driver.findElement(By.id("confirm"));
		btn_confirm.click();
		
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		
		Assert.assertEquals("Confirm Simples", texto);
		alert.accept();
		texto = alert.getText();
		Assert.assertEquals("Confirmado", texto);
		alert.accept();
		
		btn_confirm.click();
		alert = driver.switchTo().alert();
		texto = alert.getText();
		Assert.assertEquals("Confirm Simples", texto);
		alert.dismiss();
		texto = alert.getText();
		Assert.assertEquals("Negado", texto);
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertPrompt(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(640, 480));
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement btn_prompt = driver.findElement(By.id("prompt"));
		btn_prompt.click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("12");
		alert.accept();
		Assert.assertEquals("Era 12?", alert.getText());
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		
		driver.quit();
	}
}
