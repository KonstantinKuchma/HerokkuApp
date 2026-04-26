import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class FileUploadedTest {

    @Test
    public void checkFileUploaded() {
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
        driver.get("https://the-internet.herokuapp.com/upload");

        File file = new File("src/test/resources/1.txt");//указываем относительный путь и используем его далее
        driver.findElement(By.cssSelector("[type=file")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.xpath("//*[@id=\"file-submit\"]")).click();// нажимаем кнопку Upload
        //дожидаемся появления надписи
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/h3")));
        //проверяем соответствие названия файла
        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), "1.txt");
        //закрывает браузер
        driver.quit();
    }
}
