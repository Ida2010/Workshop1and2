package PurchaseProduct;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PurchaseProductSteps {

    WebDriver driver;
    String orderAmount;

    @Given("User is on the login page")
    public void user_is_on_login_page() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/");
        driver.findElement(By.className("user-info")).click();
        Thread.sleep(2000);
    }

    @When("User logs in with email {string} and password {string}")
    public void user_logs_in(String email, String password) throws InterruptedException {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();
        Thread.sleep(2000);
    }

    @And("User selects {string}")
    public void user_selects_product(String productName) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='wrapper']/div/nav/ol/li[1]/a/span")).click();


        driver.findElement(By.xpath("//*[@id='category-3']/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Hummingbird printed sweater')]")).click();
        Thread.sleep(2000);
    }

    @And("User verifies the discount is {string}")
    public void user_verifies_discount(String discount) {
        WebElement discountElement = driver.findElement(By.className("discount-percentage"));
        Assert.assertEquals(discount, discountElement.getText());
    }

    @And("User selects size {string}")
    public void user_selects_size(String size) {
        WebElement sizeDropdown = driver.findElement(By.xpath("//*[@id='group_1']"));
        Select select = new Select(sizeDropdown);
        select.selectByVisibleText(size);
    }

    @And("User sets quantity to {string}")
    public void user_sets_quantity(String quantity) {
        // WebElement qty = driver.findElement(By.className("<i class='material-icons touch spin-up'></i>"));
        //for (int i=1;i<=4;i++) { driver.findElement(By.className ("touchspin-up")).click();}
        driver.findElement(By.className ("touchspin-up")).click();
        driver.findElement(By.className ("touchspin-up")).click();
        driver.findElement(By.className ("touchspin-up")).click();
        driver.findElement(By.className ("touchspin-up")).click();
        try {Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }


    }

    @And("User adds the product to the cart")
    public void user_adds_product_to_cart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/button")).click();
        Thread.sleep(2000);
    }

    @And("User proceeds to checkout")
    public void user_proceeds_to_checkout() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Proceed to checkout')]")).click();
        driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div[1]/div[2]/div/a")).click();
        Thread.sleep(2000);

    }
    @And("User confirms the address")
    public void user_confirms_the_address() throws InterruptedException {
        driver.findElement(By.id("id-address-delivery-address-20800")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='checkout-addresses-step']/div/div/form/div[2]/button")).click();
    }


    @And("User selects delivery method {string}")
    public void user_selects_delivery(String method) throws InterruptedException {
        if (method.equalsIgnoreCase("Self pick up")) {
            driver.findElement(By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a")).click();
        }
        Thread.sleep(2000);
        driver.findElement(By.name("confirmDeliveryOption")).click();
        Thread.sleep(2000);
    }

    @And("User selects payment method {string}")
    public void user_selects_payment(String paymentMethod) throws InterruptedException {
        if (paymentMethod.equalsIgnoreCase("Pay by Check")) {
            driver.findElement(By.id("payment-option-1")).click();
        }

        //driver.findElement(By.xpath("//*[@id='conditions-to-approve']/ul/li/div[2]/label"));
        WebElement agreeTnC = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        if (!agreeTnC.isSelected())
            agreeTnC.click();

        Thread.sleep(2000);
    }

    @And("User confirms the order")
    public void user_confirms_order() throws InterruptedException {
        WebElement orderAmountElement = driver.findElement(By.xpath(".//*[@id='conditions_to_approve[terms-and-conditions]']"));
        orderAmount = orderAmountElement.getText();
        driver.findElement(By.xpath("//*[@id='payment-confirmation']/div[1]/button")).click();
        Thread.sleep(2000);
    }

    @Then("Order confirmation is displayed")
    public void order_confirmation_displayed() {
        WebElement confirmation = driver.findElement(By.xpath("//*[@id='content-hook_order_confirmation']/div/div/div/h3"));
        String confirmationdisplayed= confirmation.getText();
        Assert.assertTrue(confirmation.getText().contains("YOUR ORDER IS CONFIRMED"));
    }

    @And("Order appears in history with status {string}")
    public void order_appears_in_history(String expectedStatus) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='order-items']/div[1]/h3[1]")).click();

        Thread.sleep(2000);


        driver.quit();
    }
}





























































