package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/9/2017.
 *
 * This is the main page (https://github.com) for unauthorized users.
 */
public class MainPage {

    private SelenideElement usernameTextfield = $(byId("user[login]"));
    private SelenideElement emailTextfield = $(byId("user[email]"));
    private SelenideElement passwordTextfield = $(byId("user[password]"));


    public MainPage checkUserSignupFieldsAreDisplayed() {
        usernameTextfield.shouldBe(visible);
        emailTextfield.shouldBe(visible);
        passwordTextfield.shouldBe(visible);
        return this;
    }

}
