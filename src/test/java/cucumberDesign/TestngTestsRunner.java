package cucumberDesign;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumberDesign",glue="danish.StepDefinition",
monochrome=true,plugin= {"html:target/cucumber.html"})
public class TestngTestsRunner extends AbstractTestNGCucumberTests{

}
