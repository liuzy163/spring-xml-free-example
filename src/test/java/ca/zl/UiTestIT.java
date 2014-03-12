package ca.zl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UiTestIT {
	private WebDriver browser;

	@Before
	public void setup() {
		browser = new FirefoxDriver();
	}

	@Test
	public void testNumbers() {
		browser.get("http://localhost:8080/start");
		WebElement numbersInput = browser.findElement(By.id("numbers"));
		numbersInput.sendKeys("1;2;3");
		assertEquals("1;2;3", browser.findElement(By.id("numbers"))
				.getAttribute("value"));

		// numbersInput.sendKeys(",");
		// assertEquals("error", numbersInput.getAttribute("class"));
	}

	@After
	public void tearDown() {
		browser.close();
	}
}
