package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void submitLoginForm() {
        click(By.cssSelector("[type='submit']"));
    }

//==================================================================

    public void openRegForm() {

        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(String name, String lastname, String email, String password) {
        if (wd.findElement(By.tagName("form")).isDisplayed()) {

            type(By.id("name"),name);
            type(By.cssSelector("#lastName"),lastname);
            type(By.id("email"),email);
            type(By.cssSelector("#password"),password);
        }
    }

    public void checkPolicy() {
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            click(By.xpath("//label[@for='terms-of-use']"));
        }
    }

    public boolean isRegistrationSuccess() {
        WebElement massage = wd.findElement(By.cssSelector("h2.message"));
        String text = massage.getText();
        return text.equals("You are logged in success");
    }


}
