import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class Test_cases {
    static WebDriver driver;
    static JavascriptExecutor js;
    @BeforeAll
    public static void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Documents\\LAB_Sem_3.2\\Testing\\Lab07\\chromedriver-win32\\chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void signup(){
        driver.get("https://practicesoftwaretesting.com/#/auth/login");
        driver.findElement(By.id("email")).sendKeys("customer@practicesoftwaretesting.com");
        driver.findElement(By.id("password")).sendKeys("welcome01");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void sortOptions() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1054, 654));
        driver.findElement(By.cssSelector("*[data-test=\"nav-home\"]")).click();
        driver.findElement(By.cssSelector(".grid-title:nth-child(1)")).click();
        driver.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Name (A - Z)']")).click();
        }
        driver.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Name (Z - A)']")).click();
        }
        driver.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Price (High - Low)']")).click();
        }
        driver.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Price (Low - High)']")).click();
        }
    }

    @Test
    public void filtercheckbox() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1054, 654));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"filters\"]/div[2]/ul/div[1]/label/input")));
        js.executeScript("window.scrollTo(0,547.2000122070312)");
        driver.findElement(By.cssSelector("*[data-test=\"category-01HVVVNW24FJ869AXQH1SCN9XK\"]")).click();
        js.executeScript("window.scrollTo(0,147.1999969482422)");
        driver.findElement(By.cssSelector("*[data-test=\"category-01HVVVNW24FJ869AXQH1SCN9XK\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"category-01HVVVNW24FJ869AXQH1SCN9XM\"]")).click();
        js.executeScript("window.scrollTo(0,847.2000122070312)");
        driver.findElement(By.cssSelector("*[data-test=\"category-01HVVVNW24FJ869AXQH1SCN9XM\"]")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(18) > label")).click();
        driver.findElement(By.cssSelector(".checkbox:nth-child(18) > label")).click();
        driver.findElement(By.cssSelector("*[data-test=\"brand-01HVVVNW1T7ASAAENDW3TN8Y7C\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"brand-01HVVVNW1T7ASAAENDW3TN8Y7C\"]")).click();
        assertTrue(driver.findElement(By.cssSelector("*[data-test=\"brand-01HVVVNW1T7ASAAENDW3TN8Y7C\"]")).isEnabled());
    }

    @Test
    public void searchbox() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1054, 654));
        js.executeScript("window.scrollTo(0,454.3999938964844)");
        driver.findElement(By.cssSelector("*[data-test=\"search-query\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"search-query\"]")).sendKeys("Bolt cutters");
        driver.findElement(By.cssSelector("*[data-test=\"search-submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        String expected1=driver.findElement(By.cssSelector("h3")).getText();
        String expected2=driver.findElement(By.cssSelector("*[data-test=\"search_completed\"]")).getText();
        assertEquals("Searched for: Bolt cutters", expected1);
        assertEquals("Bolt Cutters"+"\n"+"$48.41", expected2);
    }

    @Test
    public void searchboxwithNoResult() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1054, 654));
        js.executeScript("window.scrollTo(0,454.3999938964844)");
        driver.findElement(By.cssSelector("*[data-test=\"search-query\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"search-query\"]")).sendKeys("Anything");
        driver.findElement(By.cssSelector("*[data-test=\"search-submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        String expected1=driver.findElement(By.cssSelector("h3")).getText();
        String expected2=driver.findElement(By.cssSelector("*[data-test=\"search_completed\"]")).getText();
        assertEquals("Searched for: Anything", expected1);
        assertEquals("No results found.", expected2);
    }

    @Test
    public void priceRange() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1552, 832));
        {
            WebElement element = driver.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector(".ngx-slider-pointer-max")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".grid-title:nth-child(7)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".grid-title:nth-child(7)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".grid-title:nth-child(7)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.id("filters")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector(".ngx-slider-pointer-max")).click();
    }

    @Test
    public void contractpage() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1552, 832));
        driver.findElement(By.cssSelector("*[data-test=\"nav-contact\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"first-name\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"first-name\"]")).sendKeys("John");
        driver.findElement(By.cssSelector("*[data-test=\"last-name\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"last-name\"]")).sendKeys("Doe");
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).sendKeys("John@gmail.com");
        driver.findElement(By.cssSelector("*[data-test=\"subject\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"subject\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Customer service']")).click();
        }
        driver.findElement(By.cssSelector("*[data-test=\"message\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"message\"]")).sendKeys("I am here to say, customer service is satisfactory.");
        driver.findElement(By.cssSelector("*[data-test=\"contact-submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        String result1= driver.findElement(By.cssSelector("h3")).getText();
        String result2= driver.findElement(By.cssSelector(".alert")).getText();

        assertEquals("Contact", result1);
        assertEquals("Thanks for your message! We will contact you shortly.", result2);
    }

    @Test
    public void productamount() {
        driver.get("https://practicesoftwaretesting.com/#/");
        driver.manage().window().setSize(new Dimension(1552, 832));
        js.executeScript("window.scrollTo(0,108)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"product-name\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"product-name\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-plus")));
        driver.findElement(By.cssSelector(".fa-plus")).click();
        driver.findElement(By.cssSelector(".fa-plus")).click();
        driver.findElement(By.cssSelector(".fa-minus")).click();
        driver.findElement(By.cssSelector("*[data-test=\"quantity\"]")).click();

        driver.findElement(By.cssSelector("*[data-test=\"product-description\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"quantity\"]")).click();
    }



    @Test
    public void addtocart() {
        driver.get("https://practicesoftwaretesting.com/#/");
        driver.manage().window().setSize(new Dimension(1552, 832));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"product-name\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"product-name\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"add-to-cart\"]")));


        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(.,'Product added to shopping cart.')]")));
        String result=driver.findElement(By.xpath("//div[contains(.,'Product added to shopping cart.')]")).getText();
        assertEquals("Product added to shopping cart.", result);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
//        System.out.println(driver.switchTo().alert().getText());
    }

    @Test
    public void addtofavourite() {
        driver.get("https://practicesoftwaretesting.com/#/auth/login");
        driver.findElement(By.id("email")).sendKeys("customer@practicesoftwaretesting.com");
        driver.findElement(By.id("password")).sendKeys("welcome01");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"nav-favorites\"]")));

//        driver.get("https://practicesoftwaretesting.com/");
        driver.findElement(By.cssSelector("*[data-test=\"nav-home\"]")).click();
        driver.manage().window().setSize(new Dimension(1552, 832));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card:nth-child(1) > .card-body")));
        driver.findElement(By.cssSelector(".card:nth-child(1) > .card-body")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"add-to-favorites\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"add-to-favorites\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".toast-body")));
        driver.findElement(By.cssSelector(".toast-body")).click();
    }

    @Test
    public void favouritepage() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1552, 832));
        driver.findElement(By.cssSelector("*[data-test=\"nav-sign-in\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"email\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).sendKeys("customer@practicesoftwaretesting.com");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("welcome01");
        driver.findElement(By.cssSelector("*[data-test=\"login-submit\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"nav-favorites\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"nav-favorites\"]")).click();


    }

    @Test
    public void profilepage() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1054, 654));
        {
            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"nav-sign-in\"]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("*[data-test=\"nav-sign-in\"]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"password\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("welcome01");
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).sendKeys("customer@practicesoftwaretesting.com");
        driver.findElement(By.cssSelector("*[data-test=\"login-submit\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"nav-profile\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"nav-profile\"]")).click();
    }

    @Test
    public void addtocartandcheckout() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1552, 832));
//        {
//            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"nav-sign-in\"]"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).clickAndHold().perform();
//        }
//        {
//            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"nav-sign-in\"]"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).perform();
//        }
//        {
//            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"nav-sign-in\"]"));
//            Actions builder = new Actions(driver);
//            builder.moveToElement(element).release().perform();
//        }
        driver.findElement(By.cssSelector("*[data-test=\"nav-sign-in\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"email\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"email\"]")).sendKeys("customer@practicesoftwaretesting.com");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("welcome01");
        driver.findElement(By.cssSelector("*[data-test=\"login-submit\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"nav-profile\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"nav-home\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"product-name\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"product-name\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"add-to-cart\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart\"]")).click();
//        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nav-link > .fa")));

        driver.findElement(By.cssSelector(".nav-link > .fa")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".fa"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"proceed-1\"]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"proceed-1\"]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"proceed-1\"]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector("*[data-test=\"proceed-1\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"proceed-2\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"state\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"state\"]")).sendKeys("Any state");
        driver.findElement(By.cssSelector("*[data-test=\"postcode\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"postcode\"]")).sendKeys("8598");
        driver.findElement(By.cssSelector("*[data-test=\"proceed-3\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"payment-method\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"payment-method\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Buy Now Pay Later']")).click();
        }
        driver.findElement(By.cssSelector("*[data-test=\"monthly_installments\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"monthly_installments\"]"));
            dropdown.findElement(By.xpath("//option[. = '3 monthly installments']")).click();
        }
        driver.findElement(By.cssSelector("*[data-test=\"finish\"]")).click();
//        driver.findElement(By.cssSelector(".help-block")).click();

        driver.findElement(By.cssSelector("*[data-test=\"finish\"]")).click();
//
    }

    @Test
    public void paymentwithcreditcard() {
        driver.get("https://practicesoftwaretesting.com/");
        driver.manage().window().setSize(new Dimension(1552, 832));
        {
            WebElement element = driver.findElement(By.cssSelector("*[data-test=\"nav-menu\"]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card:nth-child(2) .card-title")));
        driver.findElement(By.cssSelector(".card:nth-child(2) .card-title")).click();
        driver.findElement(By.cssSelector(".card:nth-child(2) .card-title")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"add-to-cart\"]")));
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart\"]")).click();
        driver.findElement(By.cssSelector(".nav-link > .fa")).click();
        driver.findElement(By.cssSelector("*[data-test=\"proceed-1\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"proceed-2\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"state\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"state\"]")).sendKeys("New state");
        driver.findElement(By.cssSelector("*[data-test=\"postcode\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"postcode\"]")).sendKeys("9875");
        driver.findElement(By.cssSelector("*[data-test=\"proceed-3\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"payment-method\"]")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"payment-method\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Credit Card']")).click();
        }
        driver.findElement(By.cssSelector("*[data-test=\"credit_card_number\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"credit_card_number\"]")).sendKeys("1234-5896-8758-8965");
        driver.findElement(By.cssSelector("*[data-test=\"expiration_date\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"expiration_date\"]")).sendKeys("07/2027");
        driver.findElement(By.cssSelector("*[data-test=\"card_holder_name\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"card_holder_name\"]")).sendKeys("John Dow");
        driver.findElement(By.cssSelector("*[data-test=\"finish\"]")).click();

    }

    @AfterAll
    public static void afterall(){
        driver.close();
    }
}
