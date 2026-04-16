import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxTest {
    /*
    2. Checkboxes - проверить, что первый чекбокс unchecked, отметить
первый чекбокс, проверить что он checked. Проверить, что второй чекбокс
checked, сделать unheck, проверить, что он unchecked
Локатор: By.cssSelector("[type=checkbox]”)
     */
    @Test
    public void checkCheckbox() {
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
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        boolean isCheck = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAsser.assertFalse(isCheck);//проверяем, что первый чекбокс не выбран
        System.out.println(isCheck);

        WebElement checkbox = driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(1)"));//нажимаем на первый чекбокс
        checkbox.click();
        boolean isCheck1 = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAsser.assertTrue(isCheck1);//проверяем, что первый чекбокс выбран
        System.out.println(isCheck1);

        boolean isCheck2 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAsser.assertTrue(isCheck2);//проверяем, что второй чекбокс выбран
        System.out.println(isCheck2);

        WebElement checkbox1 = driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(2)"));//нажимаем на второй чекбокс
        checkbox1.click();
        boolean isCheck3 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAsser.assertFalse(isCheck3);//проверяем, что второй чекбокс не выбран
        System.out.println(isCheck3);

        //закрывает браузер
        driver.quit();
        softAsser.assertAll();

    }
}
