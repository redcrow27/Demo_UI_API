package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"_UI/step_definition", "_API/step_definition"},
        plugin = {
                "json:target/cucumber.json",
                "rerun:target/re-run.txt"
        },
        tags = "@loginPage"
        ,dryRun = false
)
public class RunnerTest extends AbstractTestNGCucumberTests {

        /**
         * This method will run tests on parallel
         * when parallel = true
         */
        @DataProvider(parallel = false)
        public Object[][] scenarios(){
                return super.scenarios();
        }
}
