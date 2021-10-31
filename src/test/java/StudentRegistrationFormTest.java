import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Korenina");
        $("#userEmail").setValue("anna@korenina.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("9100101234");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1974");
        $(".react-datepicker__day--009:not(.react-datepicker__day--outside-month)").click();
        // $$(".react-datepicker__day--009").filter(not(cssClass(".react-datepicker__day--outside-month"))).first().click();
        // $("[aria-label=\"Choose Saturday, November 9th, 1974\"]").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img/new.jpg"));
        // $("#uploadPicture").uploadFromClasspath("/img/new.jpg");
        $("#currentAddress").setValue("Nizhnij Novgorod, Russian Federation");
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text("Anna Korenina"), text("anna@korenina.com"), text("Female"), text("9100101234"),
                text("09 September,1974"), text("Maths, English"), text("Sports, Music"), text("new.jpg"), text("Nizhnij Novgorod, Russian Federation"),
                text("Haryana Panipat"));

        $("#closeLargeModal").click();

    }
}
