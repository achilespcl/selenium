package br.pb.achilespcl.test;
import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrames {
	
	private WebDriver driver;
	
	@Before
	public void init(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(640, 480));
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finish(){
		driver.quit();
	}
	
	@Test
	public void deveInteragirComFrame(){
		driver.switchTo().frame("frame1");
		
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		
		Assert.assertEquals("Frame OK!", msg);
		
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
		
	}	
	
	@Test
	public void deveInteragirComJanela(){
		driver.findElement(By.id("buttonPopUpEasy")).click();
		
		driver.switchTo().window("Popup");
		
		driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");
		driver.close();
		
		driver.switchTo().window("");
		
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("e agora?");

	}	
	
	@Test
	public void deveInteragirComJanelaSemTitulo(){
		driver.findElement(By.id("buttonPopUpEasy")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textArea")).sendKeys("Deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textArea")).sendKeys("E agora?");
	}
	
}
