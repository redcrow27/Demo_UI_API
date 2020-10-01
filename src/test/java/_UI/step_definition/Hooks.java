package _UI.step_definition;

import common_util.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

public class Hooks {
    ScenarioContext context;

    public Hooks(ScenarioContext scenarioContext){
        this.context = scenarioContext;
    }

    @Before
    public void setUp(Scenario scenario){
        if(!ConfigReader.readProperty("testType", "src/test/resources/properties/configuration.properties").equalsIgnoreCase("apiOnly"))
            context.initializeDriver();
        context.initializeClasses(context);
        context.scenario = scenario;

        /**
         * for API part
         * comment out while start API testing
         * RestAssured.baseURI = "add here end point URL";
         */

    }

    @After
    public void tearDown(Scenario scenario) {
        /**
         *  This method will take a screen shot
         *  and will add to your report when test fail
         */
        if (context.driver != null) {
            if (scenario.isFailed()) {
                context.seleniumUtils.takeScreenshot();
            }
            context.seleniumUtils.quitDriver();
        }
    }
}
