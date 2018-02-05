package br.pb.achilespcl.test;

import org.junit.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteGoogle {
	
	private WebDriver driver;
	
	@Before
	public void init(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(640, 480));
	}
	
	@After
	public void finish(){
		driver.quit();
	}
	
	@Test
	public void teste() {
		//System.setProperty("webdriver.gecko.driver", "C:\\webdrivers\\geckodriver\\geckodriver.exe");
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
	}
}
