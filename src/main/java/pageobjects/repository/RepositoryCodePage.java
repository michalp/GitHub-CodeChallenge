package pageobjects.repository;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/10/2017.
 */
public class RepositoryCodePage extends RepositoryMainPage {

    private SelenideElement createNewFileButton = $(byXpath("//button[contains(text(), 'Create new file')]"));
    private SelenideElement createNewPullRequestButton = $(byXpath("//a[contains(text(), 'New pull request')]"));
    private SelenideElement readmePane = $(byId("readme"));

    public FileEditorPage createNewFile() {
        createNewFileButton.click();
        return (FileEditorPage) new FileEditorPage().get();
    }

    @Override
    protected void isLoaded() throws Error {
        createNewFileButton.shouldBe(visible);
        readmePane.shouldBe(visible);
    }

}
