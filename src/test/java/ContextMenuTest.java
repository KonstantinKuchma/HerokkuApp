import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContextMenuTest {
    @Test
    public void checkContextMenu() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        // определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        //задаем ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //открывает страницу по указанному урлу
        driver.get("https://the-internet.herokuapp.com/context_menu");

        Actions actions = new Actions(driver);//создаем экземпляр класса actions и передаем наш драйвер
        //создание цепочки действий - клик правой кнопкой
        actions.contextClick(driver.findElement(By.xpath("//*[@id=\"hot-spot\"]")))
                .build().perform();
        Alert alert = driver.switchTo().alert();//переключение на аллерт
        //проверка текста в аллерте
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();//закрываем аллерт
        //закрывает браузер
        driver.quit();
    }
}
