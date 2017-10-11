package ui;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.repository.RepositoryMainPage;
import pageobjects.repository.RepositoryPullRequestsPage;

/**
 * Created by mpaszek on 10/10/2017.
 */
public class PullRequestTest extends UIBaseTestCase {

    private static final String REPOSITORY_NAME = "MySuperRepo";

    @BeforeClass
    public static void setup() {
        new LoginPage()
                .login(USERNAME, PASSWORD);

        HomePage homePage = new HomePage();

        if (!homePage.isRepositoryPresent(REPOSITORY_NAME)) {
            homePage
                    .addNewRepository()
                    .withName(REPOSITORY_NAME)
                    .withDescription(REPOSITORY_NAME)
                    .publicRepository()
                    .initializeRepository()
                    .create();
        }
    }

    @Before
    public void beforeTest() {
        new HomePage()
                .navigateToHomepage()
                .openRepository(REPOSITORY_NAME);
    }

    @Test
    public void pushChanges() {
        String fileNameAndContent = "testFile_" + System.currentTimeMillis();

        new RepositoryMainPage()
                .openRepositoryCode()
                .createNewFile()
                .withName(fileNameAndContent)
                .withContent(fileNameAndContent)
                .withCommitSummary("Adding " + fileNameAndContent)
                .withCommitDescription(fileNameAndContent + " file was added for testing purposes")
                .createAndCommit();
    }

    @Test
    public void createAndAcceptPullRequest() {
        long currentTime = System.currentTimeMillis();
        String fileNameAndContent = "testFileMerge_" + currentTime;
        String branchName = "testBranch" + currentTime;

        new RepositoryMainPage()
                .openRepositoryCode()
                .createNewFile()
                .withName(fileNameAndContent)
                .withContent(fileNameAndContent)
                .withCommitSummary("Merging " + fileNameAndContent)
                .intoNewBranch(branchName)
                .openPullRequest()
                .withComment("New pull request for " + fileNameAndContent)
                .create();

        new RepositoryPullRequestsPage()
                .mergePullRequest()
                .verifyMergeWasSuccessful();
    }

    @AfterClass
    public static void cleanup() {
        new HomePage()
                .logout();
    }

}
