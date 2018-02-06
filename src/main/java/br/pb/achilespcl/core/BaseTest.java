package br.pb.achilespcl.core;

import static br.pb.achilespcl.core.DriverFactory.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	
	@Rule
	public TestName testName = new TestName();
		
	@After
	public void finish() throws IOException{
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File file = ss.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(file, new File(System.getProperty("user.dir") +
				File.separator + "screenshots" + File.separator +
				testName.getMethodName()+".jpg"));
		
		if(Propriedades.FECHAR_BROWSER){
			killDriver();
		}
	}
	
}
