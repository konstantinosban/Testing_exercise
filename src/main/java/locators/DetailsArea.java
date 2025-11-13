package locators;

import org.openqa.selenium.By;



public class DetailsArea  {

    public static final By QUESTION_ID_FIELD = By.xpath("//input[@id='edit-qa-question-id']");
    public static final By RESULTS = By.xpath("//article[contains(@class, 'teaser-qa')]");
    public static final By LEGAL_ACT_REGULATION = By.xpath("//option[@value='33']");
    public static final By FILTER_LEGAL_ACT = By.id("edit-qa-legal-act");
    public static final By PERIOD_POSTED_START = By.xpath("//input[@id='edit-qa-submission-date-start']");
    public static final By PERIOD_POSTED_END = By.xpath("//input[@id='edit-qa-submission-date-end']");
    public static final By PUBLICATION_START = By.xpath("//input[@id='edit-qa-final-publishing-date-start']");
    public static final By PUBLICATION_END = By.xpath("//input[@id='edit-qa-final-publishing-date-end']");
    public static final By KEYWORDS = By.xpath("//input[@id='edit-keywords']");


    //BUTTON
    public static final By SEARCH_BUTTON = By.xpath("//button[@id='edit-submit-question-answers']");
    public static final By ACCEPT_COOKIES = By.xpath("//a[contains(@class, 'wt-ecl-button') and contains(text(), 'Accept')]");
    public static final By RESET_BUTTON = By.xpath("//button[@id='edit-reset']");

}
