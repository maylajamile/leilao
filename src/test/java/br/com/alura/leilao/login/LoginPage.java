package br.com.alura.leilao.login;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPage extends PageObject {
	
	private static final String URL_LOGIN =  "http://localhost:8080/login";
	private FirefoxDriver browser;
	
	public LoginPage() {
		super(null);
		browser.navigate().to(URL_LOGIN);
	}

	public void fechar() {
		this.browser.quit();
	}

	public void preencheFormularioLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);		
	}

	public LeilaoPage efetuaLogin() {
		browser.findElement(By.id("login-form")).submit();
		return new LeilaoPage(browser);
	}

	public boolean isPaginaDeLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}

	public String getNomeUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaParaPaginaDeLances() {
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}

	public boolean isPaginaDeLoginComDadosInvalidos() {
		return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
	}
}
