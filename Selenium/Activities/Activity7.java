import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/*Using Selenium:

Open a new browser to https://v1.training-support.net/selenium/drag-drop
Get the title of the page and print it to the console.
On the page, perform:
Find the ball and simulate a click and drag to move it into "Dropzone 1".
Verify that the ball has entered Dropzone 1.
Once verified, move the ball into "Dropzone 2".
Verify that the ball has entered Dropzone 2.
Close the browser.*/
public class Activity7 {
    public static void main(String[] args) {
        // Set up Firefox driver
        WebDriverManager.firefoxdriver().setup();
        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();
        // Create the Actions object
        Actions builder = new Actions(driver);

        // Open the page
        driver.get("https://v1.training-support.net/selenium/drag-drop");
        // Print the title of the page
        System.out.println("Home page title: " + driver.getTitle());

        // Find the football
        WebElement football = driver.findElement(By.id("draggable"));
        // Find the dropzone1
        WebElement dropzone1 = driver.findElement(By.id("droppable"));
        // Find the dropzone2
        WebElement dropzone2 = driver.findElement(By.id("dropzone2"));

        // Perform drag and drop to dropzone 1
        builder.clickAndHold(football).moveToElement(dropzone1).pause(2000).release().build().perform();
        // Verify that the ball was dropped in dropzone 1
        String dropzone1Verify = dropzone1.findElement(By.tagName("p")).getText();
        if(dropzone1Verify.equals("Dropped!")) {
            System.out.println("Ball was dropped in dropzone 1");
        }

        // Perform drag and drop to dropzone 2
        builder.dragAndDrop(football, dropzone2).build().perform();
        // Verify that the ball was dropped in dropzone 2
        String dropzone2Verify = dropzone2.findElement(By.tagName("p")).getText();
        if(dropzone2Verify.equals("Dropped!")) {
            System.out.println("Ball was dropped in dropzone 2");
        }

        // Close the browser
        driver.close();
    }
}