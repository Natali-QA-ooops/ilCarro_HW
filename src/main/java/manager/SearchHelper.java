package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchHelper extends HelperBase {
    public SearchHelper(WebDriver wd) {
        super(wd);
    }

    public void fillSearchFormCurrentMonth(String city, String dataFrom, String dataTo) {
        fillInputCity(city);
        selectPeriodCurrentMonth(dataFrom, dataTo);
    }

    private void fillInputCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector(".pac-item"));
        pause(500);
    }

    public void selectPeriodCurrentMonth(String dataFrom, String dataTo) {
        //2/7/2022 - 2/11/2022----> 7 - 11
        click(By.id("dates"));

        //div[text()=' 7 ']
        //div[text()=' 11 ']

        String[] dataF = dataFrom.split("/"); //--->>>> [2],[7],[2022] ----> get[1] = 7
        String[] dataT = dataTo.split("/"); // ---->>>> [2], [11], [2022]  ---->get[1] = 11

        String locator = "//div[text()=' " + dataF[1] + " ']"; //     //div[text()=' 7 ']
        String loc = String.format("//div[text()=' %s ']", dataF[1]);  //div[text()=' 7 ']

        click(By.xpath(locator)); // 28

        String locator2 = "//div[text()=' " + dataT[1] + " ']";////div[text()=' 11 ']
        click(By.xpath(locator2));
        pause(5000);

    }

    public void returnToHome() {
        click(By.cssSelector(".header a[href='/']"));
    }

    public void fillSearchFormInFuture(String city, String from, String to) {
        fillInputCity(city);
        selectAnyData(from, to);
    }

    private void selectAnyData(String dataFrom, String dataTo) {
        //"3/30/2022","6/25/2022"
        //"3/30/2022","1/31/2023"
        //"1/10/2023" "1/31/2023"

        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate now = LocalDate.now();

        click(By.id("dates"));

        int mounthDif = from.getYear() - now.getYear()
                == 0 ? from.getMonthValue() - now.getMonthValue() : 12 - now.getMonthValue() + from.getMonthValue();

        clickNextMonth(mounthDif);
        String dataLocator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(dataLocator));

        mounthDif = to.getYear() - from.getYear()
                == 0 ? to.getMonthValue() - from.getMonthValue() : 12 - from.getMonthValue() + to.getMonthValue();
        clickNextMonth(mounthDif);

        dataLocator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(dataLocator));

    }

    private void clickNextMonth(int count) {
        for (int i = 0; i < count; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector(".search-results"));
    }

    public void fillSearchFormInPast(String city, String dF, String dT) {
        fillInputCity(city);
        //wd.findElement(By.id("dates")).sendKeys("02/04/2022 - 02/06/2022");
        typePeriodInPast(dF, dT);
    }

    private void typePeriodInPast(String dF, String dT) {
        type(By.id("dates"), dF + " - " + dT);
        click(By.cssSelector(".cdk-overlay-container"));
    }

    public boolean isDataInPast() {
        WebElement el = wd.findElement(By.cssSelector(".error .ng-star-inserted"));
        String text = el.getText();
        new WebDriverWait(wd, 10)
                .until(ExpectedConditions.textToBePresentInElement(el, text));
        System.out.println(text);
        //return text.equals("You can't pick date before today");
        return text.contains("pick date before today");
    }

    public boolean isYallaButtonInactive() {
        return !wd.findElement(By.cssSelector("[type='submit']")).isEnabled();
    }

}
