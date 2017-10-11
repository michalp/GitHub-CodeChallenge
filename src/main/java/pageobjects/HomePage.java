package pageobjects;

import com.codeborne.selenide.SelenideElement;
import pageobjects.repository.CreateRepositoryPage;
import pageobjects.repository.RepositoryCodePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/9/2017.
 *
 * This is the main page (https://github.com) for authorized/logged in users.
 */
public class HomePage extends UIBasePage {

    private static final String PAGE_TITLE = "GitHub";

    private SelenideElement homePageButton = $(byAttribute("aria-label", "Homepage"));
    private SelenideElement userLinksDropdowns = $(byId("user-links"));
    private SelenideElement createNewDropdown = userLinksDropdowns.$(byAttribute("aria-label", "Create new…"));
    private SelenideElement userSettingsDropdown = userLinksDropdowns.$(byAttribute("aria-label", "View profile and more"));
    private SelenideElement notificationPane = $(byId("js-flash-container"));
    private SelenideElement yourRepositoriesPane = $(byId("your_repos"));

    public HomePage navigateToHomepage() {
        homePageButton.click();
        return (HomePage) get();
    }

    public CreateRepositoryPage addNewRepository() {
        createNewDropdown.click();
        createNewDropdown
                .parent()
                .$(withText("New repository"))
                .click();
        return (CreateRepositoryPage) new CreateRepositoryPage().get();
    }

    public UserProfilePage openUserProfile() {
        userSettingsDropdown
                .parent()
                .$(withText("Your profile"))
                .click();
        return (UserProfilePage) new UserProfilePage().get();
    }

    public MainPage logout() {
        userSettingsDropdown.click();
        userSettingsDropdown
                .parent()
                .$("form[class='logout-form']")
                .click();
        return new MainPage().checkUserSignupFieldsAreDisplayed();
    }

    public HomePage checkRepositorySuccessfullyDeleted(String repository) {
        notificationPane.shouldHave(and("Repository removed",
                text("Your repository"),
                text(repository),
                text("was successfully deleted")));
        return this;
    }

    public boolean isRepositoryPresent(String repositoryName) {
        return yourRepositoriesPane
                .shouldBe(visible)
                .$$(byXpath(".//span[@class='repo']"))
                .findBy(exactText(repositoryName))
                .isDisplayed();
    }

    public RepositoryCodePage openRepository(String repositoryName) {
        yourRepositoriesPane
                .$$("a")
                .findBy(text(repositoryName))
                .should(exist)
                .click();
        return (RepositoryCodePage) new RepositoryCodePage().get();
    }

    @Override
    protected void isLoaded() throws Error {
        userSettingsDropdown.shouldBe(visible);
        yourRepositoriesPane.shouldBe(visible);
        checkCurrentPageTitle(PAGE_TITLE);
    }
}
