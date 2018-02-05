package br.pb.achilespcl.core;

import static br.pb.achilespcl.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {
	
		
		
		
	@After
	public void finish(){
		if(!Propriedades.FECHAR_BROWSER){
			killDriver();
		}
		
	}
	
}
