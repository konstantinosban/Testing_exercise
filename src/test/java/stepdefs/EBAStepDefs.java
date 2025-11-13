package stepdefs;
import actions.EBAActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EBAStepDefs {
    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/features",
            glue = {"stepdefs"},
            plugin = {"pretty", "html:target/cucumber-report.html"}
    )
    public class RunCucumberTest {
    }

    private ChromeDriver driver;
    private WebDriverWait wait;
    private EBAActions actions;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new EBAActions(driver, wait);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("user is on the search page {string}")
    public void userAmOnSearchPage(String url) {
        actions.user_is_on_the_search_page(url);
    }

    @And("user see the {string} input field")
    public void userSeesQuestionIdInputField(String fieldName) {
        actions.user_see_the_input_field();
    }

    @When("user enter {string} into the {string} field")
    public void userEntersQuestionId (String value, String fieldName) {
        actions.user_enter_into_the_field (value);
    }

    @And("user click the Search button")
    public void userClicksSearchButton() {
        actions.user_click_search_button();
    }

    @Then("user should see exactly one result in the results list")
    public void userSeesOneResult() {
        actions.user_should_see_exactly_one_result();
    }

    @And("result should correspond to Question ID {string}")
    public void resultShouldMatchQuestionId(String questionId) {
        actions.result_should_correspond_to_question_id(questionId);
    }

    @When("user apply search filters")
    public void userApplySearchFilters() {
        actions.user_apply_search_filters();

    }

    @And("user clicks the 'Search' button")
    public void userClickSearchButton() {
        actions.user_click_search_button();
    }

    @And("results are displayed")
    public void resultsAreDisplayed() {
        actions.results_are_displayed();
    }

    @And("user clicks the 'Reset' button")
    public void userClicksResetButton() {
        actions.user_clicks_reset_button();
    }

    @Then("all filters and results are cleared")
    public void allFiltersAndResultsAreCleared() {
        actions.all_filters_and_results_are_cleared();
    }

    @Then("all results are within the date range {string} to {string}")
    public void verifyResultsWithinDateRange(String start, String end) {
        actions.all_results_are_within_range(start, end);
    }

    @When("user enters {string} as start date and {string} as end date")
    public void userEntersStartAndEndDates(String start, String end) {
        actions.user_filters_by_date_range(start, end);
    }



}

