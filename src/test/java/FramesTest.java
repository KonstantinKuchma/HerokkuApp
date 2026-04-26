import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTest {
    @Test
    public void checkFrames() {
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
        driver.get("https://the-internet.herokuapp.com/frames");

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/a")).click();// нажимаем кнопку iFrame
        driver.switchTo().frame(0);
        //дожидаемся появления надписи
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tinymce\"]/p")));
        //проверяем соответствие текста во фреймме
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p")).getText(), "Your content goes here.");
        driver.switchTo().defaultContent();
        //закрывает браузер
        //driver.quit();
    }
}
