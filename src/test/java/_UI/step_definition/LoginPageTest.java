package _UI.step_definition;

import _UI.ui_utils.Selenium_utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

/**
 * In this class all step definitions for those steps in feature file are stored. Good practice to keep your steps
 * as short as possible. If you think your Test method will look bigger, try to create a method in implementation class
 * and call it in your step definition method.
 */
public class LoginPageTest {
    ScenarioContext context;

    public LoginPageTest(ScenarioContext scenarioContext){
        this.context = scenarioContext;
    }

    @Given("This is sample scenario step")
    public void this_is_sample_scenario_step() {
        // Write code here that turns the phrase above into concrete actions
        /** NOTE
         *  This shows how to get your element from Login page
         *  It's just example. Please follow rules of example
         *
         *  WebElement element = context.loginPage.sampleElement;
         */
    }




}
