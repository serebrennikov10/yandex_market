package ru.yandex;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = "src/test/java/ru.yandex",
        tags = "@all",
        snippets = SnippetType.CAMELCASE
)
public class RunnerTest {
}