package br.pb.achilespcl.suite;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.pb.achilespcl.core.DriverFactory;
import br.pb.achilespcl.test.TesteCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class
})

public class SuiteTeste {
	
	
	@AfterClass
	public static void finishAll(){
		DriverFactory.killDriver();
	}
}
