package AddAddress;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/Cucumber/Features/AddAddress.feature",
        glue = "AddAddress",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class AddAddressTest {
}
