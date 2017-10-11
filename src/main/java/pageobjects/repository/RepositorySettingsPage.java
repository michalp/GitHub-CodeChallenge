package pageobjects.repository;

import com.codeborne.selenide.SelenideElement;
import pageobjects.HomePage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/10/2017.
 */
public class RepositorySettingsPage extends RepositoryMainPage {

    private static final String PAGE_TITLE = "Options";

    private SelenideElement repositoryNameTextfield = $(byId("rename_field"));
    private SelenideElement deleteRepositoryButton = $(withText("Delete this repository"));
    private SelenideElement removalConfirmationPopup = $(byId("facebox"));
    private SelenideElement verificationTextfield = removalConfirmationPopup.$(byName("verify"));
    private SelenideElement confirmRemovalButton = removalConfirmationPopup.$(byXpath(".//button[text()='I understand the consequences, delete this repository']"));

    public HomePage deleteRepository(String repositoryName) {
        deleteRepositoryButton
                .scrollTo()
                .click();

        removalConfirmationPopup.should(appear);
        verificationTextfield.val(repositoryName);

        confirmRemovalButton.click();
        return (HomePage) new HomePage().get();
    }

    @Override
    protected void isLoaded() throws Error {
        repositoryNameTextfield.shouldBe(visible);
        checkCurrentPageTitle(PAGE_TITLE);
    }
}
