package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.LogInPage;
import utilities.Config;
import utilities.Driver;

public class LogInSteps {
    LogInPage logInPage = new LogInPage();



    @Given("user is on log in page")
    public void user_is_on_log_in_page() {
        Driver.getDriver().get(Config.getProperty("sausedemo"));

    }
    @Given("the user writes username")
    public void the_user_writes_username() {
      logInPage.userName.sendKeys(Config.getProperty("userName"));

    }
    @Then("the use provides the password")
    public void the_use_provides_the_password() {
       logInPage.password.sendKeys(Config.getProperty("password"));

    }
    @Then("the user clicks the sign in button")
    public void the_user_clicks_the_sign_in_button() {
        logInPage.clickButton.click();

    }
    @Then("verify if the user in this page")
    public void verify_if_the_user_in_this_page() {
        String expectedUrl = " https://www.saucedemo.com/inventory.html";


        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), expectedUrl);

    }



}