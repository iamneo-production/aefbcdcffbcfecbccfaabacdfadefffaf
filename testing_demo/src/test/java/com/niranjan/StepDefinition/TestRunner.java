package com.one.StepDefinitions;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(features="/home/coder/project/workspace/testing_demo/src/test/resources/Features",glue={"StepDefinitions"})

public class TestRunner {
    
}