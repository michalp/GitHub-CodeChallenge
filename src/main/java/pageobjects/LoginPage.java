package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by mpaszek on 10/8/2017.
 *
 * This is the page for users to log in to the application.
 */
public class LoginPage {

    private SelenideElement usernameTextfield = $(byId("login_field"));
    private SelenideElement passwordTextfield = $(byId("password"));
    private SelenideElement signInButton = $(byName("commit"));

    public HomePage login(String username, String password) {
        return login("/", username, password);
    }

    public HomePage login(String url, String username, String password) {
        openLoginPage(url);
        performLogin(username, password);
        return (HomePage) new HomePage().get();
    }

    private LoginPage openLoginPage(String url) {
        open(url);
        waitForLoginPageToLoad();
        return this;
    }

    private LoginPage performLogin(String username, String password) {
        usernameTextfield.val(username);
        passwordTextfield.val(password);
        signInButton.click();
        return this;
    }

    private LoginPage waitForLoginPageToLoad() {
        usernameTextfield.waitUntil(visible, 30000);
        passwordTextfield.should(appear);
        signInButton.should(appear);
        return this;
    }

}
