package _UI.step_definition;

import _UI.pages.CommonPage;
import _UI.pages.LoginPage;
import _UI.ui_utils.Selenium_utils;
import common_util.ConfigReader;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ScenarioContext {
     public Scenario scenario;
     public WebDriver driver;


     /** Classes
      *  When you create new class add here
      */
     public LoginPage loginPage;
     public Selenium_utils seleniumUtils;
     public CommonPage commonPage;


     /**
      *  API
      */
     public RequestSpecification request;
     public Response response;
     public Headers headers;
     public JSONObject jsonObject;


     //NOTE: Selenium methods
     public void initializeClasses(ScenarioContext scenarioContext){
          /**
           *  After declare class name on class level create object from class
           */
          loginPage = new LoginPage(driver);
          seleniumUtils = new Selenium_utils(scenarioContext);
          commonPage = new CommonPage(driver);
     }

     public void initializeDriver() {
          if (driver == null) {
               switch (ConfigReader.readProperty("browser", "src/test/resources/properties/configuration.properties")) {
                    case "chrome":
                         WebDriverManager.chromedriver().setup();
                         driver = new ChromeDriver();
                         break;
                    case "firefox":
                         WebDriverManager.firefoxdriver().setup();
                         driver = new FirefoxDriver();
                         break;
                    default:
                         System.out.println("Invalid browser type");
               }
          }
          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
          driver.manage().window().maximize();
     }

     //NOTE: API methods
     public String getResponseBody(){
          return response.getBody().asString();
     }

     public int getStatusCode(){
          return response
                  .getStatusCode();
     }

     public String getResponseID(){
          return response.body().jsonPath().getString("_id");
     }


}
