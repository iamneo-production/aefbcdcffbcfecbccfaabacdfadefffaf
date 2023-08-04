package com.one.StepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Login {
    private WebDriver driver;

    @Given("I am on the Google search page")
    public void navigateToGoogleSearchPage() {
        initializeDriver();
        driver.get("https://www.google.com");
    }

    @When("I enter {string} in the search box")
    public void enterSearchTerm(String searchTerm) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
    }

    @When("I click the \"Search\" button")
    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.click();
    }

    @Then("I should see search results for {string}")
    public void verifySearchResults(String searchTerm) {
        WebElement searchResultsContainer = driver.findElement(By.id("search"));
        Assert.assertTrue("Search results container is not displayed",
                searchResultsContainer.isDisplayed());

        List<WebElement> searchResultItems = driver.findElements(By.cssSelector(".tF2Cxc"));
        for (WebElement resultItem : searchResultItems) {
            String resultText = resultItem.getText().toLowerCase();
            Assert.assertTrue("Search term not found in search result",
                    resultText.contains(searchTerm.toLowerCase()));
        }

        Assert.assertTrue("Number of search results is not greater than 0",
                searchResultItems.size() > 0);
    }

    @When("I enter {string} in the image search box")
    public void enterImageSearchTerm(String searchTerm) {
        WebElement imageSearchBox = driver.findElement(By.name("q"));
        imageSearchBox.sendKeys(searchTerm);
    }

    @When("I click the \"Search Images\" button")
    public void clickSearchImagesButton() {
        WebElement searchImagesButton = driver.findElement(By.cssSelector(".Tg7LZd"));
        searchImagesButton.click();
    }

    @Given("I construct the Google search URL with string as parameter")
    public void constructGoogleSearchURL(String searchTerm) {
        initializeDriver();
        String url = "https://www.google.com/search?q=" + searchTerm;
        driver.get(url);
    }

    @Given("I am on the Wikipedia home page")
    public void navigateToWikipediaHomePage() {
        initializeDriver();
        driver.get("https://www.wikipedia.org");
    }

    @When("I pick a language string")
    public void pickLanguage(String language) {
        WebElement languageLink = driver.findElement(By.linkText(language));
        languageLink.click();
    }

    @When("I search for articles with the term string")
    public void searchWikipediaArticles(String searchTerm) {
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @Then("I should see search results related to string")
    public void verifySearchResultsRelatedTo(String searchTerm) {
        List<WebElement> searchResults = driver.findElements(By.cssSelector(".mw-search-result-heading a"));
        for (WebElement result : searchResults) {
            String resultText = result.getText().toLowerCase();
            Assert.assertTrue("Search term not found in search result",
                    resultText.contains(searchTerm.toLowerCase()));
        }
    }

    @Then("each search result links to the correct articles")
    public void verifySearchResultLinks() {
        List<WebElement> searchResults = driver.findElements(By.cssSelector(".mw-search-result-heading a"));
        for (WebElement result : searchResults) {
            String url = result.getAttribute("href");
            Assert.assertTrue("Search result link is invalid", url.contains("wikipedia.org"));
        }
    }

    @Then("I can view the page history")
    public void viewPageHistory() {
        WebElement historyLink = driver.findElement(By.linkText("View history"));
        historyLink.click();
    }

    @Given("I have the base URI for JSON Placeholder API")
    public void setBaseURI() {
        
    }

    @When("I hit the \"posts\" endpoint")
    public void hitPostsEndpoint() {
        
    }

    @Then("I should get a response with status code 200")
    public void verifyStatusCode() {
        
    }

    @Then("the response contains valid JSON data")
    public void verifyValidJSONData() {
        
    }

    @Then("the response contains specific data fields")
    public void verifySpecificDataFields() {
        
    }

    private void initializeDriver() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = prop.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "path_to_geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }
}