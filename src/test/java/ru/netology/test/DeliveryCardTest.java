package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationByCustomerInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.generateByInfo;

public class DeliveryCardTest {

    RegistrationByCustomerInfo registrationInfo = generateByInfo("ru");

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldEnterValidData() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue(registrationInfo.getFullName());
        $("[data-test-id='phone'] input").setValue(registrationInfo.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text((DataGenerator.forwardDate(3))));
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(5));
        $(byText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Перепланировать"));
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text((DataGenerator.forwardDate(5))));
    }

    @Test
    void shouldEnterEmptyCity() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue(registrationInfo.getFullName());
        $("[data-test-id='phone'] input").setValue(registrationInfo.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".input_invalid[data-test-id='city']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEnterNotValidCity() {
        $("[data-test-id='city'] input").setValue("Wendell");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue(registrationInfo.getFullName());
        $("[data-test-id='phone'] input").setValue(registrationInfo.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".input_invalid[data-test-id='city']").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldEnterEmptyName() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue(registrationInfo.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEnterNotValidName() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue("Aleksandr Osipov");
        $("[data-test-id='phone'] input").setValue(registrationInfo.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldEnterEmptyPhone() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue(registrationInfo.getFullName());
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEnterNotValidPhone() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue(registrationInfo.getFullName());
        $("[data-test-id='phone'] input").setValue("+798012345");
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldEmptyCheckBox() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.forwardDate(3));
        $("[data-test-id='name'] input").setValue(registrationInfo.getFullName());
        $("[data-test-id='phone'] input").setValue(registrationInfo.getPhoneNumber());
        $(byText("Запланировать")).click();
        $(".input_invalid[data-test-id='agreement']").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
