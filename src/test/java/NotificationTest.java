import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class NotificationTest {
    /*
   8. * Notification Messages - кликнуть на кнопку, дождаться появления
нотификации, проверить соответствие текста ожиданиям
    */
    @Test
    public void notificationMessage() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        // определяем браузер с которым хоти работать
        WebDriver driver = new ChromeDriver(options);
        //объявляем софт ассерт, чтоб ы моно было делать несколько проверок
        SoftAssert softAsser = new SoftAssert();
        //задаем ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //открывает страницу по указанному урлу
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        //находим и нажимаем на кнопку
        driver.findElement(By.xpath("/html/body/div[2]/div/div/p/a")).click();
        //задаем ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //проверяем соответствия текста
        String text = driver.findElement(By.xpath("/html/body/div[1]/div/div")).getText().replace("×", "").trim();// вычитываем значение из поля
        softAsser.assertEquals(text, "Action unsuccesful, please try again");// сравниваем то что вычитали
        System.out.println(text);

        //закрывает браузер
        driver.quit();
        softAsser.assertAll();
    }
}
