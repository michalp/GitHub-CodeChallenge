package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/10/2017.
 */
public class UserProfilePage extends UIBasePage {

    private String navigationTabLocator = "//nav[@role='navigation']";
    private SelenideElement overviewLink = $(byXpath(navigationTabLocator + "//a[contains(text(), 'Overview')]"));
    private SelenideElement repositoriesLink = $(byXpath(navigationTabLocator + "//a[contains(text(), 'Repositories')]"));
    private SelenideElement changeAvatarButton = $(byAttribute("aria-label", "Change your avatar"));
    private SelenideElement editProfileButton = $(byXpath("//a[text()='Edit profile']"));

    public UserProfilePage openOverview() {
        overviewLink.click();
        return this;
    }

    public UserProfilePage openRepositories() {
        repositoriesLink.click();
        return this;
    }

    @Override
    protected void isLoaded() throws Error {
        changeAvatarButton.shouldBe(visible);
        editProfileButton.shouldBe(visible);
    }
}
