package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(features = "src/test/java/featureFiles",
                                 glue = {"stepDefinitions"},
                                 plugin = "json:target/jsonReports/cucumber-report.json",
                                 tags = "@regression")
public class TestRunner extends AbstractTestNGCucumberTests {
}
