package _UI.ui_utils;

import _UI.step_definition.ScenarioContext;
import common_util.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * All Selenium related re-usable methods are stored in this Class
 */
public class Selenium_utils {
    ScenarioContext context;

    public Selenium_utils(ScenarioContext scenarioContext){
        context = scenarioContext;
    }

    public void quitDriver(){
        context.driver.quit();
        context.driver = null;
    }

    /**
     * This method will make driver to sleep with given time
     * @param milliseconds it will take int "1sec = 1000"
     */
    public static void sleep(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (InterruptedException e){
            e.printStackTrace();;
        }
    }

    /**
     * This method will wait till element is clickable
     * @param element it will take an element
     */
    public void waitForClickability(WebElement element){
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method will wait till element is visible
     * @param element it will take an element
     */
    public void waitForVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method will wait till all elements is visible
     * @param elementList it will take list of elements
     */
    public void waitForVisibilityOfAll(List<WebElement> elementList){
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
    }

    /**
     * This method will wait till page to load
     */
    public void waitForPageToLoad(){
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(pageLoadCondition);
    }

    /**
     * This method will wait till alert
     */
    public void waitForAlert(){
        WebDriverWait wait = new WebDriverWait(context.driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * This method will click to button and will highlights
     * @param element it will take an element
     */
    public void click(WebElement element){
        waitForClickability(element);
        highlightElement(element);
        element.click();
    }

    /**
     * This method will send keys
     * @param element it will take an element
     * @param input enter string value
     */
    public void sendKeys(WebElement element, String input){
        waitForVisibility(element);
        highlightElement(element);
        element.sendKeys(input);
    }

    /**
     * This method will get text of element
     * @param element it will take an element
     * @return it will return element's text value as a String
     */
    public String getText(WebElement element){
        waitForVisibility(element);
        highlightElement(element);
        return element.getText();
    }

    /**
     * This method will move into view
     * @param element it will take an element
     */
    public void moveIntoView(WebElement element){
        try{
            ((JavascriptExecutor)context.driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }catch (Exception e){
            e.printStackTrace();
        }
        highlightElement(element);
    }

    /**
     * This method will highlight element
     * @param element it will take an element
     */
    public void highlightElement(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)context.driver;

        for (int i = 0; i < 2; i++){
            try{
                if(i == 0){
                    executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border:3px solid red; background: yellow");
                }else {
                    sleep(300);
                    executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * This method will switch to window
     * @param currentPageID it will take String page ID
     */
    public void switchToWindow(String currentPageID){
        Set<String> set = context.driver.getWindowHandles();
        for (String window: set){
            if (!window.equalsIgnoreCase(currentPageID)) context.driver.switchTo().window(window);
        }
    }

    /**
     *  This method will take screenshot of page
     */
    public void takeScreenshot(){
        waitForPageToLoad();
        try{
            byte[] screenshot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
            context.scenario.attach(screenshot, "image/png", "Screenshot | " + DateTime.CURRENT_DATETIME);
        }catch (WebDriverException e){
            e.printStackTrace();
        }
    }

    /**
     * This method will move to element and then take a screenshot
     * @param element it will take an element
     */
    public void takeScreenshot(WebElement element){
        waitForPageToLoad();
        moveIntoView(element);
        JavascriptExecutor executor = (JavascriptExecutor)context.driver;

        try{
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border:3px solid red; background: yellow");
            byte[] screenshot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
            context.scenario.attach(screenshot, "image/png", "Screenshot | " + DateTime.CURRENT_DATETIME);
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }catch (WebDriverException e){
            e.printStackTrace();
        }
    }

    /**
     * This method will add info to report
     * @param msg will take a message as a String
     * @param takeScreenshot if make true it will take screen shot as well
     *                       but while it false is not going to take screen shot
     */
    public void logInfo(String msg, boolean takeScreenshot){
        context.scenario.log(DateTime.CURRENT_DATETIME + " INFO: " + msg);
        if (takeScreenshot)
            takeScreenshot();
    }

    /**
     * This method will switch to window
     * without taking any paramaters
     */
    public void windowHandle() {
        String mainWindowHandle = context.driver.getWindowHandle();
        Set<String> windowHandles = context.driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()) {
            String child_window = iterator.next();
            if (!mainWindowHandle.equals(child_window)) {
                context.driver.switchTo().window(child_window);
            }
        }
    }


}
