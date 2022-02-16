package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        if (text != null && !text.isEmpty()) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }


    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(By locator){

        return wd.findElements(locator).size()>0;
    }
    public void typeLocation(String address) {
        type(By.id("pickUpPlace"),address);
        click(By.cssSelector(".pac-item"));
        pause(500);
    }


    public void takeScreenShot(String pathToFile){
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screen = new File(pathToFile);

        try{
            Files.copy(tmp,screen);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
