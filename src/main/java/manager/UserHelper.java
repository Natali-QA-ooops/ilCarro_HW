package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {

        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void submitForm() {

        click(By.cssSelector("[type='submit']"));
    }

    public void openRegForm() {
        wd.findElement(By.xpath("//a[text()=' Sign up ']")).click();
        //click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(String name, String lastname, String email, String password) {
        if (wd.findElement(By.tagName("form")).isDisplayed()) {

            type(By.id("name"), name);
            type(By.cssSelector("#lastName"), lastname);
            type(By.id("email"), email);
            type(By.cssSelector("#password"), password);
        }
    }
    public void fillRegistrationForm(User user) {
        if (wd.findElement(By.tagName("form")).isDisplayed()) {

            type(By.id("name"), user.getName());
            type(By.cssSelector("#lastName"), user.getLastName());
            type(By.id("email"), user.getEmail());
            type(By.cssSelector("#password"), user.getPassword());
        }
    }

    public void checkPolicy() {
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            click(By.xpath("//label[@for='terms-of-use']"));
        }
    }

    public void checkPolicyByXY() {
        WebElement label = wd.findElement(By.xpath("//label[@for='terms-of-use']"));
        Rectangle rect = label.getRect();

        int offSetX = rect.getWidth() / 3;
        int offSetY = rect.getHeight() / 3;

        Actions actions = new Actions(wd);
        actions.moveToElement(label).release().build().perform();

        System.out.println(offSetX + " " + offSetY);
        actions.moveByOffset(-offSetX, -offSetY).click().release().build().perform();
    }


    public boolean isRegistrationSuccess() {

        WebElement until = new WebDriverWait(wd, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='dialog-container']//h2")));
        String text = until.getText();
        System.out.println(text);
        return text.equals("You are logged in success");

    }


    public boolean isLogOutPresent() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));

    }

    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
    }

    public void clickOkButton() {
        new WebDriverWait(wd, 15)
               .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='dialog-container']")));

        click(By.xpath("//button[text()='Ok']"));
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submitForm();
        clickOkButton();
        pause(3000);
    }

    public void registration(User user) {
        openRegForm();
        fillRegistrationForm(user);
        checkPolicyByXY();
        submitForm();


    }


}
