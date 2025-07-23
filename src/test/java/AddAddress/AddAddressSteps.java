package AddAddress;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddAddressSteps {

    private static WebDriver driver;

    @Given("I am logged in as a registered user with email {string} and password {string}")
    public void i_am_logged_in_as_a_registered_user_with_email_and_password(String email, String password) {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-password"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-login"))).click();
    }

    @When("I click on Address")
    public void i_click_on_address() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addressLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Addresses']")));
        addressLink.click();
    }

    @And("I click on \"+ Create new address\"")
    public void i_click_on_create_new_address() {
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-link-action='add-address']")));
        createButton.click();
    }

    @And("I fill in the new assress form with alias {string}, address {string}, city {string}, zip {string}, country {string}, phone {string}")
    public void i_fill_in_the_new_assress_form(String alias, String address, String city, String zip, String country, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("alias"))).clear();
        driver.findElement(By.name("alias")).sendKeys(alias);
        driver.findElement(By.name("address1")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("postcode")).sendKeys(zip);

        WebElement countryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("id_country")));
        Select countrySelect = new Select(countryDropdown);

        try {
            countrySelect.selectByVisibleText(country);
        } catch (NoSuchElementException e) {
            for (WebElement option : countrySelect.getOptions()) {
                System.out.println("Option: " + option.getText());
            }
            throw e;
        }

        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.xpath("//button[contains(@class,'form-control-submit')]")).click();
    }

    @Then("I verify the new address is added correctly with alias {string}")
    public void i_verify_the_new_address_is_added_correctly_with_alias(String alias) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isPresent = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h4[contains(text(),'" + alias + "')]"))).size() > 0;

        Assert.assertTrue("Address with alias " + alias + " was not found!", isPresent);
        driver.quit();
        driver = null;
    }
}
