package ca.zl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UiTestIT {
	private static final String SHUTDOWN_URL = "http://localhost:9024/shutdown";
	private static final String START_URL = "http://localhost:9024/start";
	private WebDriver browser;

	@Before
	public void setup() {
		browser = new FirefoxDriver();
	}

	@Test
	public void basicTest() throws InterruptedException {
		// Wait for Tomcat starts up
		Thread.sleep(10000);
		browser.get(START_URL);
		WebElement numbersInput = browser.findElement(By.id("numbers"));
		WebElement indexInput = browser.findElement(By.id("order"));
		Select select = new Select(browser.findElement(By.id("complexity")));
		WebElement submitButton = browser.findElement(By.name("submit"));

		// test linear algorithm
		numbersInput.sendKeys("1;2;3;2");
		indexInput.sendKeys("2");
		select.selectByValue("n");
		submitButton.submit();
		Thread.sleep(2000);
		assertEquals(
				"The result list is 1;2;3, and the 2th largest number is 2",
				browser.findElement(By.id("puzzleResult")).getText());

		// test O(nlogn) algorithm
		numbersInput.clear();
		numbersInput.sendKeys("1;8;3;8;6;9;3");
		indexInput.clear();
		indexInput.sendKeys("2");
		select.selectByValue("nlogn");
		submitButton.submit();
		Thread.sleep(2000);
		assertEquals(
				"The result list is 1;3;6;8;9, and the 2th largest number is 8",
				browser.findElement(By.id("puzzleResult")).getText());

		// test least memory algorithm
		select.selectByValue("leastmemory");
		submitButton.submit();
		Thread.sleep(2000);
		assertEquals(
				"The result list is 1;3;6;8;9, and the 2th largest number is 8",
				browser.findElement(By.id("puzzleResult")).getText());

		// test invalid input
		numbersInput.clear();
		numbersInput.sendKeys("1;2;a");
		indexInput.clear();
		indexInput.sendKeys("a");
		Actions builder = new Actions(browser);
		builder.moveToElement(submitButton).perform();
		submitButton.click();
		Thread.sleep(2000);
		assertEquals("rgba(255, 0, 0, 1)",
				numbersInput.getCssValue("border-top-color"));
		assertEquals("rgba(255, 0, 0, 1)",
				indexInput.getCssValue("border-top-color"));

	}

	@After
	public void tearDown() {
		browser.get(SHUTDOWN_URL);
		browser.close();
	}

}
