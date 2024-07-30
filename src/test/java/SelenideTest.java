import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com/";
    }

    @Test
    void shouldJUnit5Test() {

        var jUnit5TestClass = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {\s
                @Test\s
                void test() {\s
                Configuration.assertionMode = SOFT;\s
                open("page.html");\s
                $("#first").should(visible).click();\s
                $("#second").should(visible).click();\s
                }\s
                }\s
                """;

        open("/selenide/selenide");

        $("#wiki-tab").click();

        $(".filter-bar #wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box ul[data-filterable-for='wiki-pages-filter']")
                .$(byText("SoftAssertions"))
                .closest("li")
                .shouldBe(enabled)
                .$("span.Truncate")
                .click();

        $("#user-content-3-using-junit5-extend-test-class").scrollIntoView(true);
        $("#wiki-body").shouldHave(text(jUnit5TestClass));
    }
}
