import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DynamicControlsTest {


    @Test
    public void checkDynamicControls() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        // определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        //задаем ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// объявляем явное ожидание
        //открывает страницу по указанному урлу
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        //проверки чекбокса
        driver.findElement(By.xpath("//*[text() = 'Remove']")).click();// нажимаем кнопку Remove
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));//дожидаемся появления надписи
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));//проверяем, что чек-бокса нет
        //проверки инпут поля
        WebElement inputField = driver.findElement(By.cssSelector("#input-example input"));//находим инпут поле
        assertFalse(inputField.isEnabled());
        driver.findElement(By.xpath("//*[text() = 'Enable']")).click();// нажимаем кнопку Enable
        //дожидаемся появления надписи (после клика на Enable предыдущий элемент с id "message" пропадает)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertTrue(inputField.isEnabled());
        //закрывает браузер
        driver.quit();
    }
}
