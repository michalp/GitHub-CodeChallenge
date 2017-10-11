package ui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mpaszek on 10/9/2017.
 */
public class RepositoryTest extends UIBaseTestCase {

    private final String repositoryName = "MPtest" + ThreadLocalRandom.current().nextInt(100, 1000);

    @BeforeClass
    public static void setup() {
        new LoginPage()
                .login(USERNAME, PASSWORD);
    }

    @Test
    public void createAndRemoveRepository() {
        new HomePage()
                .addNewRepository()
                .withName(repositoryName)
                .withDescription(repositoryName)
                .publicRepository()
                .create()
                .openRepositorySettings()
                .deleteRepository(repositoryName)
                .checkRepositorySuccessfullyDeleted(repositoryName);
    }

    @AfterClass
    public static void cleanup() {
        new HomePage()
                .logout();
    }

}
