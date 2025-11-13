Feature: Keyword Search Functionality

  # Test Case 1
  Scenario: Search by specific Question ID returns correct result
    Given user is on the search page "https://www.eba.europa.eu/single-rule-book-qa/search"
    And user see the "Question ID" input field
    When user enter "4680" into the "Question ID" field
    And user click the Search button
    Then user should see exactly one result in the results list
    And result should correspond to Question ID "4680"

  # Test case 2
  Scenario: Reset Search Filters
    Given user is on the search page "https://www.eba.europa.eu/single-rule-book-qa/search"
    When user apply search filters
    And user clicks the 'Search' button
    And results are displayed
    And user clicks the 'Reset' button
    Then all filters and results are cleared

    # Τest case 3
  Scenario: Filter results by date range
    Given user is on the search page "https://www.eba.europa.eu/single-rule-book-qa/search"
    When user enters '01-01-2010' as start date and '12-31-2025' as end date
    And user clicks the 'Search' button
    Then results are displayed
    And all results are within the date range '01-01-2010' to '12-12-2025'





