package com.contentside.tech.test

import com.contentside.tech.AcceptanceMockApplication
import io.cucumber.java8.En
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

/**
 * Abstraction of an acceptance test.
 */
@ActiveProfiles("test", "test-at")
@RunWith(Cucumber::class)
@CucumberOptions(
        plugin = ["pretty", "html:target/cucumber/report.html", "json:target/cucumber/acceptance-report.json"],
        strict = true
)
@SpringBootTest(classes = [AcceptanceMockApplication::class])
abstract class AcceptanceTest

/**
 * Abstraction of a set of BDD steps.
 */
interface StepDefinitions : En
