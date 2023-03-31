package br.com.alura.leilao.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;

public class PageObject {
	
	protected FirefoxDriver browser;
	
	public PageObject(FirefoxDriver browser) {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
		
		if (browser == null) {
			this.browser = new FirefoxDriver();
		} else {
			this.browser = browser;
		}
		
		//Esperar para que então lance um erro.
//		this.browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
//		.pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void fechar() {
		this.browser.quit();
	}
}
