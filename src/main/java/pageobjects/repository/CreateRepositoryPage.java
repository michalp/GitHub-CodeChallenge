package pageobjects.repository;

import com.codeborne.selenide.SelenideElement;
import pageobjects.UIBasePage;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/9/2017.
 */
public class CreateRepositoryPage extends UIBasePage {

    private static final String PAGE_TITLE = "Create a New Repository";

    private SelenideElement repositoryNameTextfield = $(byId("repository_name"));
    private SelenideElement repositoryDescriptionTextfield = $(byId("repository_description"));
    private SelenideElement repositoryPublicRadioButton = $(byId("repository_public_true"));
    private SelenideElement repositoryPrivateRadioButton = $(byId("repository_public_false"));
    private SelenideElement repositoryInitializeWithReadmeCheckbox = $(byId("repository_auto_init"));
    private SelenideElement repositoryCreateButton = $(withText("Create repository"));

    public CreateRepositoryPage withName(String name) {
        repositoryNameTextfield
                .val(name)
                .shouldHave(cssClass("is-autocheck-successful"));
        return this;
    }

    public CreateRepositoryPage withDescription(String description) {
        repositoryDescriptionTextfield.val(description);
        return this;
    }

    public CreateRepositoryPage publicRepository() {
        repositoryPublicRadioButton.click();
        return this;
    }

    public CreateRepositoryPage privateRepository() {
        repositoryPrivateRadioButton.click();
        return this;
    }

    public CreateRepositoryPage initializeRepository() {
        repositoryInitializeWithReadmeCheckbox.click();
        return this;
    }

    public RepositoryMainPage create() {
        repositoryCreateButton.click();
        return (RepositoryMainPage) new RepositoryMainPage().get();
    }

    @Override
    protected void isLoaded() throws Error {
        repositoryNameTextfield.shouldBe(visible);
        repositoryCreateButton.shouldBe(visible);
        checkCurrentPageTitle(PAGE_TITLE);
    }
}
