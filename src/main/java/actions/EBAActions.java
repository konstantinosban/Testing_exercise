package actions;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static locators.DetailsArea.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EBAActions  {
    private ChromeDriver driver;
    private WebDriverWait wait;

    public EBAActions (ChromeDriver driver, WebDriverWait wait){
        this.driver = driver ;
        this.wait = wait;
    }








    @Step
    public void user_is_on_the_search_page(String url) {
        driver.get(url);
        try {
            WebElement acceptCookiesButton =
                    wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES));
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println("No cookies popup shown.");
        }
    }

    @Step
    public void user_see_the_input_field()  {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(QUESTION_ID_FIELD));
        assertTrue(input.isDisplayed());
    }

    @Step
    public void user_enter_into_the_field(String value) {
        WebElement input = driver.findElement(QUESTION_ID_FIELD);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        input.clear();
        input.sendKeys(value);
    }

    @Step
    public void user_click_search_button() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        }

    }

    @Step
    public void user_should_see_exactly_one_result() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(RESULTS, 0));
        List<WebElement> results = driver.findElements(RESULTS);
        assertEquals("Expected exactly 1 result", 1, results.size());
    }

    @Step
    public void result_should_correspond_to_question_id(String questionId) {
        WebElement result = driver.findElement(RESULTS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String resultText = result.getText();
        assertTrue("Expected to find Question ID " + questionId, resultText.contains(questionId));
    }

    @Step
    public void user_apply_search_filters() {
        // Dropdown: Regulation
        WebElement regulationDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(LEGAL_ACT_REGULATION));
        regulationDropdown.click();// CRR

        // Start Date
        WebElement startDate = wait.until(ExpectedConditions.visibilityOfElementLocated(PERIOD_POSTED_START));
        startDate.clear();
        startDate.sendKeys("02-12-2022");

        // End Date
        WebElement endDate = wait.until(ExpectedConditions.visibilityOfElementLocated(PERIOD_POSTED_END));
        endDate.clear();
        endDate.sendKeys("02-12-2024");

        // Keywords
        WebElement keywordsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(KEYWORDS));
        keywordsInput.clear();
        keywordsInput.sendKeys("capital requirements");
    }



    @Step
    public void results_are_displayed() {
        List<WebElement> results = driver.findElements(RESULTS);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", results.get(0));
        assertTrue("Expected at least one result, but found none", results.size() > 0);

    }

    @Step
    public void user_clicks_reset_button() {
        WebElement resetButton = wait.until(ExpectedConditions.presenceOfElementLocated(RESET_BUTTON));


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", resetButton);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-spinner")));
        } catch (TimeoutException e) {
            System.out.println("NO SPINNER");
        }

        wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetButton);


    }

    @Step
    public void all_filters_and_results_are_cleared() {
        WebElement legalActDropdown = driver.findElement(FILTER_LEGAL_ACT);
        wait.until(ExpectedConditions.elementToBeClickable(legalActDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", legalActDropdown);
        Select select = new Select(legalActDropdown);
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        assertTrue("Expected Legal Act dropdown to be cleared or default", selectedOptions.isEmpty());

        WebElement startDate = driver.findElement(PERIOD_POSTED_START);
        assertEquals("Expected start date to be cleared", "", startDate.getAttribute("value"));

        WebElement endDate = driver.findElement(PERIOD_POSTED_END);
        assertEquals("Expected end date to be cleared", "", endDate.getAttribute("value"));

        WebElement keywords = driver.findElement(KEYWORDS);
        assertEquals("Expected keywords input to be cleared", "", keywords.getAttribute("value"));

    }

    @Step
    public void user_filters_by_date_range(String start, String end) {
        WebElement startDate = wait.until(ExpectedConditions.visibilityOfElementLocated(PUBLICATION_START));
        WebElement endDate = wait.until(ExpectedConditions.visibilityOfElementLocated(PUBLICATION_END));


        startDate.clear();
        startDate.sendKeys(start);

        endDate.clear();
        endDate.sendKeys(end);
    }

    @Step
    public void all_results_are_within_range(String startDateStr, String endDateStr) {
        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(RESULTS));
        assertTrue("Expected at least one result", results.size() > 0);


        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter resultFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate startDate = LocalDate.parse(startDateStr, inputFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, inputFormatter);

        for (WebElement result : results) {

            String publishedDateText = result.findElement(By.xpath(".//li[contains(.,'Published as final Q')]")
            ).getText();

            String[] parts = publishedDateText.split(":");
            if (parts.length < 2) continue;

            String datePart = parts[1].trim();
            LocalDate publishedDate = LocalDate.parse(datePart, resultFormatter);

            assertTrue(
                    "Found a result outside the expected date range: " + publishedDate,
                    (publishedDate.isEqual(startDate) || publishedDate.isAfter(startDate)) &&
                            (publishedDate.isEqual(endDate) || publishedDate.isBefore(endDate))
            );
        }
    }


}







