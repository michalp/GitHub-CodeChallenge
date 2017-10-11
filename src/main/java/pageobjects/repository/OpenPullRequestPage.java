package pageobjects.repository;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by mpaszek on 10/11/2017.
 */
public class OpenPullRequestPage extends RepositoryCodePage {

    private SelenideElement pullRequestTitleTextbox = $(byId("pull_request_title"));
    private SelenideElement pullRequestCommentTextarea = $(byId("pull_request_body"));
    private SelenideElement createPullRequestButton = $(byXpath("//button[text()='Create pull request']"));

    public OpenPullRequestPage withTitle(String pullRequestTitle) {
        pullRequestTitleTextbox.val(pullRequestTitle);
        return this;
    }

    public OpenPullRequestPage withComment(String comment) {
        pullRequestCommentTextarea.val(comment);
        return this;
    }

    public RepositoryPullRequestsPage create() {
        createPullRequestButton.click();
        return (RepositoryPullRequestsPage) new RepositoryPullRequestsPage().get();
    }

    @Override
    protected void isLoaded() throws Error {
        pullRequestTitleTextbox.shouldBe(visible);
    }

}
