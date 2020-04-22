package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SysTest {
    private final String fireFoxDriver = "/Users/Anselm/cmc-web-2020/geckodriver";//Path to driver
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.firefox.driver", fireFoxDriver);
        driver = new FirefoxDriver();
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void departmentEditTest() {
        driver.get("http://localhost:8080/personnel/");
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.cssSelector("div:nth-child(4) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Домашнаяя страница")).click();
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.id("department_name")).click();
        driver.findElement(By.id("department_name")).sendKeys("Финансовый отдел");
        driver.findElement(By.cssSelector("div:nth-child(4) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Домашнаяя страница")).click();
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.id("department_name")).click();
        driver.findElement(By.id("department_name")).sendKeys("test");
        driver.findElement(By.cssSelector("div:nth-child(4) > input")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test");
        driver.findElement(By.linkText("Домашнаяя страница")).click();
        Assert.assertEquals(driver.findElement(By.linkText("test")).getText(), "test");
        driver.findElement(By.linkText("test")).click();
        driver.findElement(By.linkText("Изменить подразделение")).click();
        driver.findElement(By.id("department_name")).click();
        driver.findElement(By.id("department_name")).clear();
        driver.findElement(By.id("department_name")).sendKeys("Финансовый отдел");
        driver.findElement(By.cssSelector("div:nth-child(4) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Домашнаяя страница")).click();
        Assert.assertEquals(driver.findElement(By.linkText("test")).getText(), "test");
        driver.findElement(By.linkText("test")).click();
        driver.findElement(By.linkText("Изменить подразделение")).click();
        driver.findElement(By.id("department_name")).click();
        driver.findElement(By.id("department_name")).sendKeys(" test");
        {
            WebElement dropdown = driver.findElement(By.id("head_department"));
            dropdown.findElement(By.xpath("//option[. = 'Финансовый отдел']")).click();
        }
        driver.findElement(By.cssSelector("option:nth-child(6)")).click();
        driver.findElement(By.cssSelector("div:nth-child(4) > input")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test test");
        Assert.assertEquals(driver.findElement(By.linkText("Финансовый отдел")).getText(), "Финансовый отдел");
        driver.findElement(By.linkText("Домашнаяя страница")).click();
        Assert.assertEquals(driver.findElement(By.linkText("test test")).getText(), "test test");
        driver.findElement(By.linkText("test test")).click();
        driver.findElement(By.linkText("Удалить подразделение")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("test test"));
            assert(elements.size() == 0);
        }
        driver.findElement(By.linkText("Домашнаяя страница")).click();
    }
    @Test
    public void departmentPositionEditTest() {
        driver.get("http://localhost:8080/personnel/");
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.findElement(By.linkText("Andelme Headquarters")).click();
        Assert.assertEquals(driver.findElement(By.linkText("Афанасьева Екатерина Сергеевна")).getText(), "Афанасьева Екатерина Сергеевна");
        Assert.assertEquals(driver.findElement(By.linkText("Начальник отдела кадров")).getText(), "Начальник отдела кадров");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), "1.0");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).getText(), "160000.0 руб");
        {
            WebElement dropdown = driver.findElement(By.id("postype_id"));
            dropdown.findElement(By.xpath("//option[. = 'Бухгалтер']")).click();
        }
        driver.findElement(By.cssSelector("#postype_id > option:nth-child(4)")).click();
        driver.findElement(By.id("work_rate")).clear();
        driver.findElement(By.id("work_rate")).sendKeys("0.75");
        driver.findElement(By.id("work_rate")).click();
        driver.findElement(By.cssSelector("td:nth-child(7) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.id("worker_id"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.linkText("Бухгалтер")).getText(), "Бухгалтер");
        Assert.assertEquals(driver.findElement(By.cssSelector("td:nth-child(8)")).getText(), "0.75");
        Assert.assertEquals(driver.findElement(By.cssSelector("td:nth-child(9)")).getText(), "45000.0 руб");
        {
            WebElement dropdown = driver.findElement(By.id("worker_id"));
            dropdown.findElement(By.xpath("//option[. = 'Афанасьева Екатерина Сергеевна']")).click();
        }
        driver.findElement(By.cssSelector("td:nth-child(6) option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("td:nth-child(10) > input")).click();
        Assert.assertEquals(driver.findElement(By.linkText("Афанасьева Екатерина Сергеевна")).getText(), "Афанасьева Екатерина Сергеевна");
        Assert.assertEquals(driver.findElement(By.linkText("Бухгалтер")).getText(), "Бухгалтер");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), "0.75");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).getText(), "45000.0 руб");
        {
            List<WebElement> elements = driver.findElements(By.id("worker_id"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.linkText("Начальник отдела кадров")).getText(), "Начальник отдела кадров");
        Assert.assertEquals(driver.findElement(By.cssSelector("td:nth-child(8)")).getText(), "1.0");
        Assert.assertEquals(driver.findElement(By.cssSelector("td:nth-child(9)")).getText(), "160000.0 руб");
        driver.findElement(By.linkText("удалить")).click();
        {
            List<WebElement> elements = driver.findElements(By.id("postype_id"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.id("work_rate"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(7) > input"));
            assert(elements.size() > 0);
        }
        {
            WebElement dropdown = driver.findElement(By.id("worker_id"));
            dropdown.findElement(By.xpath("//option[. = 'Афанасьева Екатерина Сергеевна']")).click();
        }
        driver.findElement(By.cssSelector("#worker_id > option:nth-child(2)")).click();
        {
            WebElement dropdown = driver.findElement(By.id("postype_id"));
            dropdown.findElement(By.xpath("//option[. = 'Лаборант']")).click();
        }
        driver.findElement(By.cssSelector("#postype_id > option:nth-child(7)")).click();
        driver.findElement(By.id("work_rate")).clear();
        driver.findElement(By.id("work_rate")).sendKeys("0.5");
        driver.findElement(By.id("work_rate")).click();
        {
            WebElement element = driver.findElement(By.id("work_rate"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.cssSelector("td:nth-child(7) > input")).click();
        Assert.assertEquals(driver.findElement(By.linkText("Афанасьева Екатерина Сергеевна")).getText(), "Афанасьева Екатерина Сергеевна");
        Assert.assertEquals(driver.findElement(By.linkText("Лаборант")).getText(), "Лаборант");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), "0.5");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).getText(), "5000.0 руб");
        {
            List<WebElement> elements = driver.findElements(By.id("worker_id"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.linkText("Бухгалтер")).getText(), "Бухгалтер");
        Assert.assertEquals(driver.findElement(By.cssSelector("td:nth-child(8)")).getText(), "0.75");
        Assert.assertEquals(driver.findElement(By.cssSelector("td:nth-child(9)")).getText(), "45000.0 руб");
        driver.findElement(By.linkText("Афанасьева Екатерина Сергеевна")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Афанасьева Екатерина Сергеевна");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).getText(), "Начальник отдела кадров");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText(), "Andelme Headquarters");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), "1.0");
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(2) > td:nth-child(4)"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(2) > td:nth-child(5)"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(1)")).getText(), "Бухгалтер");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(1)")).getText(), "Лаборант");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(5)")).getText(), "н.в.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(7) > p")).getText(), "Лаборант Andelme Headquarters");
        driver.findElement(By.linkText("Домашнаяя страница")).click();
    }
    @Test
    public void linkNavigationTest() {
        driver.get("http://localhost:8080/personnel/");
        driver.manage().window().setSize(new Dimension(1440, 900));
        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Andelme Corporations");
        driver.findElement(By.linkText("Andelme Headquarters")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Andelme Headquarters");
        driver.findElement(By.linkText("Финансовый отдел")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Финансовый отдел");
        driver.findElement(By.linkText("Гончаров Сергей Рудольфович")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Гончаров Сергей Рудольфович");
        driver.findElement(By.linkText("Вакансии")).click();
        driver.findElement(By.linkText("Работники")).click();
        driver.findElement(By.linkText("Должности")).click();
        driver.findElement(By.linkText("Начальник отдела кадров")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "Начальник отдела кадров");
        driver.findElement(By.linkText("Домашнаяя страница")).click();
    }
    @Test
    public void postypeEditTest() {
        driver.get("http://localhost:8080/personnel/");
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.findElement(By.linkText("Должности")).click();
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.cssSelector("div:nth-child(5) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Должности")).click();
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.id("postype_name")).click();
        driver.findElement(By.id("postype_name")).sendKeys("test");
        driver.findElement(By.id("salary")).click();
        driver.findElement(By.id("salary")).sendKeys("1000");
        driver.findElement(By.id("responsibilities")).click();
        driver.findElement(By.id("responsibilities")).sendKeys("test");
        driver.findElement(By.cssSelector("div:nth-child(5) > input")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(2) > p")).getText(), "1000 руб");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(3) > p")).getText(), "test");
        driver.findElement(By.linkText("Изменить должность")).click();
        driver.findElement(By.id("postype_name")).click();
        driver.findElement(By.id("postype_name")).sendKeys(" test");
        driver.findElement(By.id("responsibilities")).click();
        driver.findElement(By.id("responsibilities")).sendKeys(" test");
        driver.findElement(By.id("salary")).click();
        driver.findElement(By.id("salary")).sendKeys("0");
        driver.findElement(By.cssSelector("div:nth-child(5) > input")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test test");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(2) > p")).getText(), "10000 руб");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(3) > p")).getText(), "test test");
        driver.findElement(By.linkText("Вакансии")).click();
        {
            WebElement dropdown = driver.findElement(By.id("postype_id"));
            dropdown.findElement(By.xpath("//option[. = 'test test']")).click();
        }
        driver.findElement(By.cssSelector("option:nth-child(12)")).click();
        driver.findElement(By.linkText("Должности")).click();
        Assert.assertEquals(driver.findElement(By.linkText("test test")).getText(), "test test");
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.linkText("отмена")).click();
        driver.findElement(By.linkText("test test")).click();
        driver.findElement(By.linkText("Удалить должность")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("test test"));
            assert(elements.size() == 0);
        }
        driver.findElement(By.linkText("Домашнаяя страница")).click();
    }
    @Test
    public void vacancyPositionEditTest() {
        driver.get("http://localhost:8080/personnel/");
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.findElement(By.linkText("Вакансии")).click();
        {
            List<WebElement> elements = driver.findElements(By.id("postype_id"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.id("department_id"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.id("work_rate"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(6) > input"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.cssSelector("td:nth-child(6) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Вакансии")).click();
        {
            WebElement dropdown = driver.findElement(By.id("postype_id"));
            dropdown.findElement(By.xpath("//option[. = 'Начальник отдела кадров']")).click();
        }
        driver.findElement(By.cssSelector("#postype_id > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("td:nth-child(6) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Вакансии")).click();
        {
            WebElement dropdown = driver.findElement(By.id("department_id"));
            dropdown.findElement(By.xpath("//option[. = 'Andelme Headquarters']")).click();
        }
        driver.findElement(By.cssSelector("#department_id > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("td:nth-child(6) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Вакансии")).click();
        {
            WebElement dropdown = driver.findElement(By.id("postype_id"));
            dropdown.findElement(By.xpath("//option[. = 'Начальник отдела кадров']")).click();
        }
        driver.findElement(By.cssSelector("#postype_id > option:nth-child(2)")).click();
        {
            WebElement dropdown = driver.findElement(By.id("department_id"));
            dropdown.findElement(By.xpath("//option[. = 'Отдел кадров']")).click();
        }
        driver.findElement(By.cssSelector("#department_id > option:nth-child(5)")).click();
        driver.findElement(By.id("work_rate")).clear();
        driver.findElement(By.id("work_rate")).sendKeys("0.5");
        driver.findElement(By.id("work_rate")).click();
        driver.findElement(By.cssSelector("td:nth-child(6) > input")).click();
        Assert.assertEquals(driver.findElement(By.linkText("Начальник отдела кадров")).getText(), "Начальник отдела кадров");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(2) > a")).getText(), "Отдел кадров");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(3)")).getText(), "0.5");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(4)")).getText(), "80000.0 руб");
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(8) > td:nth-child(5) > a"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(2) > a")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(8) #worker_id"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.linkText("Начальник отдела кадров")).getText(), "Начальник отдела кадров");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(8)")).getText(), "0.5");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(9)")).getText(), "80000.0 руб");
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(8) > td > input"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(8) > td:nth-child(10) > a"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Вакансии")).click();
        driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(5) > a")).click();
        {
            List<WebElement> elements = driver.findElements(By.id("postype_id"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.id("department_id"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.id("work_rate"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(6) > input"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.cssSelector("tr:nth-child(5) > td:nth-child(2) > a")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(3) > #worker_id"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td > #postype_id"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td > #work_rate"));
            assert(elements.size() > 0);
        }
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(7) > input"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Домашнаяя страница")).click();
    }
    @Test
    public void workerEditTest() {
        driver.get("http://localhost:8080/personnel/");
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.findElement(By.linkText("Работники")).click();
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.cssSelector("div:nth-child(8) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Работники")).click();
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("test");
        driver.findElement(By.id("birth_date")).click();
        driver.findElement(By.id("birth_date")).sendKeys("2020-04-01");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("test");
        driver.findElement(By.id("phone_number")).click();
        driver.findElement(By.id("phone_number")).sendKeys("test");
        driver.findElement(By.cssSelector("div:nth-child(8) > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("div > h1"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("Работники")).click();
        driver.findElement(By.cssSelector("div")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("test");
        driver.findElement(By.id("birth_date")).click();
        driver.findElement(By.id("birth_date")).sendKeys("2020-04-01");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("test");
        driver.findElement(By.id("phone_number")).click();
        driver.findElement(By.id("phone_number")).sendKeys("+7(000)000-00-00");
        {
            WebElement dropdown = driver.findElement(By.id("education_degree"));
            dropdown.findElement(By.xpath("//option[. = 'bachelor']")).click();
        }
        driver.findElement(By.cssSelector("option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("div:nth-child(8) > input")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(2) > p")).getText(), "01.04.2020 г");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(4) > p")).getText(), "bachelor");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(6) > p")).getText(), "test");
        driver.findElement(By.linkText("Изменить данные работника")).click();
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys(" test");
        driver.findElement(By.cssSelector("div:nth-child(5)")).click();
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys(" test");
        driver.findElement(By.id("phone_number")).click();
        driver.findElement(By.id("phone_number")).clear();
        driver.findElement(By.id("phone_number")).sendKeys("+7(111)111-11-11");
        {
            WebElement dropdown = driver.findElement(By.id("education_degree"));
            dropdown.findElement(By.xpath("//option[. = 'doctor']")).click();
        }
        driver.findElement(By.cssSelector("option:nth-child(4)")).click();
        driver.findElement(By.cssSelector("div:nth-child(8) > input")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test test");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(2) > p")).getText(), "01.04.2020 г");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(4) > p")).getText(), "doctor");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(5) > p")).getText(), "+7(111)111-11-11");
        Assert.assertEquals(driver.findElement(By.cssSelector("div:nth-child(6) > p")).getText(), "test test");
        driver.findElement(By.linkText("Работники")).click();
        Assert.assertEquals(driver.findElement(By.linkText("test test")).getText(), "test test");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(13) > td:nth-child(3)")).getText(), "doctor");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr:nth-child(13) > td:nth-child(4)")).getText(), "Не работает");
        driver.findElement(By.linkText("test test")).click();
        driver.findElement(By.linkText("Удалить данные работника")).click();
        {
            List<WebElement> elements = driver.findElements(By.linkText("test test"));
            assert(elements.size() == 0);
        }
        driver.findElement(By.linkText("Домашнаяя страница")).click();
    }
}
