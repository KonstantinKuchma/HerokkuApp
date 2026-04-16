import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputTest {
    /*
    4. Inputs - Проверить на возможность ввести различные цифровые и
нецифровые значения, используя Keys.ARROW_UP И
Keys.ARROW_DOWN
Локатор: By.tagName(“input”)
     */
    @Test
    public void checkInput() {
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
        driver.get("https://the-internet.herokuapp.com/inputs");

        driver.findElement(By.tagName("input")).sendKeys("Tst");//пытаемся ввести Tst
        //проверяем что поле осталось пустым
        String text = driver.findElement(By.tagName("input")).getAttribute("value");// вычитываем значение из поля
        softAsser.assertEquals(text, "");// сравниваем то что вычитали
        System.out.println(text);

        driver.findElement(By.tagName("input")).sendKeys("10");//пытаемся ввести 10
        //проверяем что введено 10
        String text1 = driver.findElement(By.tagName("input")).getAttribute("value");
        softAsser.assertEquals(text1, "10");
        System.out.println(text1);

        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);//имитирует нажатие кнопки
        //проверяем что введено 11
        String text2 = driver.findElement(By.tagName("input")).getAttribute("value");
        softAsser.assertEquals(text2, "11");
        System.out.println(text2);

        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);//имитирует нажатие кнопки
        //проверяем что введено 10
        String text3 = driver.findElement(By.tagName("input")).getAttribute("value");
        softAsser.assertEquals(text3, "10");
        System.out.println(text3);

        //закрывает браузер
        driver.quit();
        softAsser.assertAll();
    }
}
