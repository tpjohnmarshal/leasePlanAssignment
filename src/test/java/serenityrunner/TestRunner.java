package serenityrunner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = {"dsl"},
        tags = "@automated"
)
public class TestRunner {
}