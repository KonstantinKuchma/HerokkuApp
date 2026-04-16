import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AddRemoveElementTest {
    /*
    1. Add/Remove Elements - добавить 2 элемента, удалить элемент,
    проверить количество элементов DELETE
    Локаторы xpath:
    a. By.xpath("//button[text()='Add Element']")
    b. By.xpath("//button[text()='Delete']")
     */
    @Test
    public void checkAddRemoveElement() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        // определяем браузер с которым хоти работать
        WebDriver driver = new ChromeDriver(options);
        //объявляем софт ассерт, чтоб ы моно было делать несколько проверок
        SoftAssert softAsser = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //открывает страницу по указанному урлу
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        //находим и нажимаем на кнопку Add Element 2 раза
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        //проверяем что кнопки 2
        int size = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAsser.assertEquals(size, 2);
        //находим и нажимаем на кнопку Delete
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        //проверяем что кнопка 1
        int size1 = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAsser.assertEquals(size1, 1);
        //закрывает браузер
        driver.quit();
        softAsser.assertAll();
    }
}
