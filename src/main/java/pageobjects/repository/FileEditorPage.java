package pageobjects.repository;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

/**
 * Created by mpaszek on 10/11/2017.
 */
public class FileEditorPage extends RepositoryCodePage {

    private static final String PAGE_TITLE = "New File";

    private SelenideElement fileBox = $(byClassName("file-box"));
    private SelenideElement newFileNameTextbox = fileBox.$(byName("filename"));
    private SelenideElement codeEditorPane = fileBox.$(byClassName("CodeMirror-code")).$("pre").$("span");

    private SelenideElement commitSummaryTextfield = $(byId("commit-summary-input"));
    private SelenideElement commitDescriptionTextarea = $(byId("commit-description-textarea"));
    private SelenideElement commitToMasterRadioButton = $(byXpath("//input[@name='commit-choice' and @value='direct']"));
    private SelenideElement createNewBranchRadioButton = $(byXpath("//input[@name='commit-choice' and @value='quick-pull']"));
    private SelenideElement branchNameTextbox = $(byXpath("//input[contains(@class,'js-quick-pull-new-branch-name') and @placeholder='New branch name…']"));
    private SelenideElement commitNewFileButton = $(byId("submit-file"));

    public FileEditorPage withName(String fileName) {
        newFileNameTextbox.val(fileName);
        return this;
    }

    public FileEditorPage withContent(String fileContent) {
        actions()
                .click(codeEditorPane)
                .moveToElement(codeEditorPane)
                .sendKeys(fileContent)
                .build()
                .perform();
        return this;
    }

    public FileEditorPage withCommitSummary(String commitSummary) {
        commitSummaryTextfield.val(commitSummary);
        return this;
    }

    public FileEditorPage withCommitDescription(String commitDescription) {
        commitDescriptionTextarea.val(commitDescription);
        return this;
    }

    public FileEditorPage intoNewBranch(String branchName) {
        createNewBranchRadioButton.click();
        branchNameTextbox.click();
        branchNameTextbox.val(branchName);
        return this;
    }

    public OpenPullRequestPage openPullRequest() {
        commitNewFileButton.click();
        return (OpenPullRequestPage) new OpenPullRequestPage().get();
    }

    public RepositoryCodePage createAndCommit() {
        commitNewFileButton.click();
        return (RepositoryCodePage) new RepositoryCodePage().get();
    }

    @Override
    protected void isLoaded() throws Error {
        fileBox.shouldBe(visible);
        checkCurrentPageTitle(PAGE_TITLE);
    }
}
