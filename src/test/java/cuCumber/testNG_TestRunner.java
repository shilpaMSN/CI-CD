package cuCumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cuCumber",glue="Framework.stepDefination",
monochrome=true,tags="@ErrorValidation",plugin={"html:target/cucumber.html"})
public class testNG_TestRunner extends AbstractTestNGCucumberTests{
	

}
