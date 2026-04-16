import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {
    /*
    5. Typos - Проверить соответствие параграфа орфографии
Локатор: By.tagName(“p”)
     */
    @Test
    public void checkTypos() {
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
        driver.get("https://the-internet.herokuapp.com/typos");

        for (int i = 0; i < 10; i++) { //повторяем проверку 10 раз
            //вычитываем строку со страницы
            String text = driver.findElement(By.xpath("(//p)[2]")).getText();
            softAsser.assertEquals(text, "Sometimes you'll see a typo, other times you won't.");//проверяем соответствие
            System.out.println(text);
            driver.navigate().refresh();//обновляем страницу
        }
        //закрывает браузер
        driver.quit();
        softAsser.assertAll();
    }
}
