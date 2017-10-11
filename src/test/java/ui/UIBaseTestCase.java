package ui;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;

/**
 * Created by mpaszek on 10/8/2017.
 */
public class UIBaseTestCase {

    private static final Integer TIMEOUT = 10000;
    private static final String BROWSER = "chrome";
    private static final String URL = "https://github.com/login";
    static final String USERNAME = "mptestuser";
    static final String PASSWORD = "Asdf1234!";

    @BeforeClass
    public static void initialize() {
        Configuration.timeout = Long.valueOf(TIMEOUT);
        Configuration.browser = BROWSER;
        Configuration.baseUrl = URL;
        Configuration.fastSetValue = true;
    }

}
