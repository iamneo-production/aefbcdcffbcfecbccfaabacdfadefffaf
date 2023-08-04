Feature: Google Search and Wikipedia Verification

Scenario: Perform a Google search and verify the results
  Given I am on the Google search page
  When I enter "search term" in the search box
  And I click the "Search" button
  Then I should see search results for "search term"
    And the search results container is displayed
    And each search result contains the word "search term"
    And the search results show relevant information
    And the number of search results is greater than 0

Scenario: Search for images on Google
  Given I am on the Google search page
  When I enter "search term" in the image search box
  And I click the "Search Images" button
  Then I should see image search results for "search term"
    And the image search results container is displayed
    And each image search result contains the word "search term"
    And the number of image search results is greater than 0

Scenario: Perform a Google search using URL query parameters
  Given I construct the Google search URL with "search term" as parameter
  When I navigate to the URL
  Then I should see search results for "search term"
    And the search results container is displayed
    And each search result contains the word "search term"
    And the search results show relevant information
    And the number of search results is greater than 0

Scenario: Verify Wikipedia functionality
  Given I am on the Wikipedia home page
  When I pick a language "English"
  And I search for articles with the term "Wikipedia"
  Then I should see search results related to "Wikipedia"
    And each search result links to the correct articles
    And I can view the page history

Scenario: Basic service-level testing using REST Assured
  Given I have the base URI for JSON Placeholder API
  When I hit the "posts" endpoint
  Then I should get a response with status code 200
    And the response contains valid JSON data
    And the response contains specific data fields
