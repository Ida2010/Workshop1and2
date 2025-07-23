package PurchaseProduct;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/Cucumber/Features/PurchaseProduct.feature",
        glue = {"PurchaseProduct"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class PurchaseProductTest {
}



