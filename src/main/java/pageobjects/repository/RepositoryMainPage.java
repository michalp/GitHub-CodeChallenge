package pageobjects.repository;

import com.codeborne.selenide.SelenideElement;
import pageobjects.UIBasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/9/2017.
 */
public class RepositoryMainPage extends UIBasePage {

    private String navigationTabLocator = "//nav[@role='navigation']";
    private SelenideElement repositoryCodeLink = $(byXpath(navigationTabLocator + "//*[contains(text(), 'Code')]"));
    private SelenideElement repositoryPullRequestsLink = $(byXpath(navigationTabLocator + "//a[contains(@href,'/pulls')]"));
    private SelenideElement repositorySettingsLink = $(byXpath(navigationTabLocator + "/a[contains(@href,'/settings')]"));
    private SelenideElement repositoryContentPane = $(byClassName("repository-content"));

    public RepositoryCodePage openRepositoryCode() {
        repositoryCodeLink.click();
        return (RepositoryCodePage) new RepositoryCodePage().get();
    }

    public RepositoryPullRequestsPage openRepositoryPullRequests() {
        repositoryPullRequestsLink.click();
        return (RepositoryPullRequestsPage) new RepositoryPullRequestsPage().get();
    }

    public RepositorySettingsPage openRepositorySettings() {
        repositorySettingsLink.click();
        return (RepositorySettingsPage) new RepositorySettingsPage().get();
    }

    @Override
    protected void isLoaded() throws Error {
        repositoryContentPane.shouldBe(visible);
    }
}
