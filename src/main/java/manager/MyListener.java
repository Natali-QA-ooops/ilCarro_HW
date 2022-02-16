package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class MyListener extends AbstractWebDriverEventListener {

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    public MyListener(){
        super();
    }


    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        logger.info("Start find element --->" +by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
        logger.info("The element with locator --->" +by+ "---> was found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logger.info("We have a throwable --->"+throwable.getMessage());
        logger.info(throwable.fillInStackTrace().toString());

        int index = (int)(System.currentTimeMillis()/1000)%3600;
        String link = "src/test/screenshorts/screen"+index+".png";

        logger.info("This is your screen with error -->"+link);
//        HelperBase base = new HelperBase(driver);
//        base.takeScreenShot(link);
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screen = new File(link);

        try{
            Files.copy(tmp,screen);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
