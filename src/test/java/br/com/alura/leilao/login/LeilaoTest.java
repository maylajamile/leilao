package br.com.alura.leilao.login;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LeilaoTest {
	
	private LeilaoPage paginaDeLeiloes;
	private CadastroLeilaoPage paginaDeCadastro;

	@AfterEach
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
	}
	
	@BeforeEach
	public void beforeEach() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioLogin("fulano", "pass");
		this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
		this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do dia "+hoje;
		String valor = "500.00";
		
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		
		Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");
		
		Assert.assertFalse(this.paginaDeCadastro.isPaginaAtual());
		Assert.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());
	}
}
