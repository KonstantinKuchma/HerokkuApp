import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DropdownTest {
    /*
   3. Dropdown - Взять все элементы дроп-дауна и проверить их наличие.
Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
он выбран
Локатор: By.id(“dropdown”)
    */
    @Test
    public void dropDown() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        // определяем браузер с которым хоти работать
        WebDriver driver = new ChromeDriver(options);
        //объявляем софт ассерт, чтоб ы моно было делать несколько проверок
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //открывает страницу по указанному урлу
        driver.get("https://the-internet.herokuapp.com/dropdown");
        //создаем объект селект
        Select select = new Select(driver.findElement(By.id("dropdown")));

        // проверяем наличие и текст каждой опции напрямую по индексу
        softAssert.assertEquals(
                select.getOptions().get(0).getText(),
                "Please select an option"
        );
        softAssert.assertEquals(
                select.getOptions().get(1).getText(),
                "Option 1"
        );
        softAssert.assertEquals(
                select.getOptions().get(2).getText(),
                "Option 2"
        );

        // проверяем возможность выбора опций
        select.selectByVisibleText("Option 1");
        softAssert.assertEquals(
                select.getFirstSelectedOption().getText(),
                "Option 1"
        );

        select.selectByVisibleText("Option 2");
        softAssert.assertEquals(
                select.getFirstSelectedOption().getText(),
                "Option 2"
        );
        //закрывает браузер
        driver.quit();
        softAssert.assertAll();
    }
}
