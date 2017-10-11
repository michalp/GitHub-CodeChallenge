package pageobjects;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Created by mpaszek on 10/8/2017.
 */
public abstract class UIBasePage extends LoadableComponent<UIBasePage>{

    protected void checkCurrentPageTitle(String expectedPageTitle) {
        String currentPageTitle = WebDriverRunner.getWebDriver().getTitle();
        if (!currentPageTitle.contains(expectedPageTitle)) {
            throw new Error("Incorrect page title! \nEXPECTED: " + expectedPageTitle + "\nACTUAL: " + currentPageTitle);
        }
    }

    @Override
    protected void load() {
    }

    @Override
    protected abstract void isLoaded() throws Error;
}
