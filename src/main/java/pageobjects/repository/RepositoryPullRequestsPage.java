package pageobjects.repository;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/10/2017.
 */
public class RepositoryPullRequestsPage extends RepositoryMainPage {

    private SelenideElement pullRequestHeaderPane = $(byId("partial-discussion-header"));
    private SelenideElement pullRequestConversationPane = $(byId("discussion_bucket"));
    private SelenideElement mergePullRequestButton = $(byXpath("//button[contains(text(),'Merge pull request')]"));
    private SelenideElement confirmMergeButton = $(byXpath("//button[contains(text(),'Confirm  merge')]"));
    private SelenideElement mergeResultMessagePane = $(byClassName("post-merge-message"));

    public RepositoryPullRequestsPage mergePullRequest() {
        mergePullRequestButton.click();
        confirmMergeButton.click();
        return this;
    }

    public RepositoryPullRequestsPage verifyMergeWasSuccessful() {
        mergeResultMessagePane.shouldHave(text("Pull request successfully merged and closed"));
        return this;
    }

    @Override
    protected void isLoaded() throws Error {
        pullRequestHeaderPane.shouldBe(visible);
        pullRequestConversationPane.shouldBe(visible);
    }
}
