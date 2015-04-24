package br.com.partes;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.configuracoes.EnumElemento;

/**
 * Contem os metodos basicos de interacao
 */
public class Nucleo {

	private WebDriver driver = null;

	public Nucleo(WebDriver driver) {

		this.driver = driver;
	}

	/**
	 * Retorna o driver para ser usado nas outras classes.
	 * 
	 * @return Retorna o driver.
	 * @author Rog�rio Figueiredo
	 */
	protected WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Acessa uma p�gina atrav�s do endere�o.
	 * 
	 * @param url
	 *            Passe o endere�o completo, inclusive o protocolo(http,https,
	 *            etc).
	 * @author Rogerio Figueiredo.
	 */
	public void navegar(String url) {

		driver.get(url);

		if (driver.getPageSource().contains("P�gina n�o encontrada...")) {
			System.err
					.println("Navegou para: "
							+ url
							+ ": Foi direcionada para p�gina n�o encontrada, confira a url.");
		}

		if (driver.getPageSource().contains("ERROR")) {
			System.err.println("Navegou para: " + url
					+ ": Foi direcionada para p�gina de erro, confira a url.");
		}

	}

	/**
	 * Pega o texto do elemento
	 * 
	 * @param EnumElemento
	 *            Passe o tipo do elemento que deseja pegar o texto, o elemento
	 *            � um enumerado.
	 * @param nomeElemento
	 *            Passe o nome do elemento que deseja pegar o texto.
	 * @return Ir� retornar o texto do elemento.
	 * @author Rog�rio Figueiredo.
	 */
	public String pegarTexto(EnumElemento EnumElemento, String nomeElemento) {
		WebElement elemento = null;
		elemento = pegarElemento(EnumElemento, nomeElemento);
		if (elemento == null) {
			return "";
		} else {
			String texto = elemento.getText();
			return texto;
		}
	}

	/**
	 * Pega a url corrente
	 * 
	 * @return Retorna a url
	 */

	public String pegarUrlCorrente() {

		return driver.getCurrentUrl();

	}

	/**
	 * Pega a o c�digo html da p�gina
	 * 
	 * @return Retorna o html da p�gina
	 */

	public String pegarCodigoPagina() {

		return driver.getPageSource();

	}

	/**
	 * Pega o elemento e retorna o mesmo.
	 * 
	 * @param EnumElemento
	 *            Passe o tipo do elemento que deseja pegar, � um enumerado.
	 * @param nomeElemento
	 *            Passe o nome do elemento que deseja pegar.
	 * @return Retorna o elemento.
	 * @author Rog�rio Figueiredo.
	 */
	public WebElement pegarElemento(EnumElemento EnumElemento,
			String nomeElemento) {
		WebElement elemento = null;
		int timeout = 10;
		try {
			switch (EnumElemento) {
			case ID:
				elemento = (new WebDriverWait(driver, timeout))
						.until(ExpectedConditions.presenceOfElementLocated(By.id(nomeElemento)));
				break;
			case NAME:
				elemento = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.name(nomeElemento)));
				break;
			case XPATH:
				elemento = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nomeElemento)));
				break;
			case CLASSNAME:
				elemento = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.className(nomeElemento)));
				break;
			case LINK:
				elemento = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText(nomeElemento)));
				break;
			case PARTIALINK:
				elemento = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(nomeElemento)));
				break;
			case CSS:
				elemento = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(nomeElemento)));
				break;
			default:
				elemento = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.id(nomeElemento)));
				break;
			}
			return elemento;
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	/**
	 * Clica em um elemento.
	 * 
	 * @param EnumElemento
	 *            Passe o tipo do elemento, � um enumerado.
	 * @param nomeElemento
	 *            Passe o nome do elemento.
	 * @author Rog�rio Figueiredo.
	 */
	public void clicar(EnumElemento EnumElemento, String nomeElemento) {
		WebElement elemento;
		elemento = pegarElemento(EnumElemento, nomeElemento);

		if (!(elemento == null)) {
			elemento.click();
		} else {
			String erro = ("Voc� usou a fun��o clicar, mas n�o foi\n"
					+ "poss�vel clicar no elemento usando as informa��es que voc� passou:\n"
					+ "Nome do elemento: " + "<" + nomeElemento + ">" + "\n"
					+ "Tipo do Elemento: " + "<" + EnumElemento + ">" + "\n" + "A��o: Clicar\n");

			System.err.println(erro);

			fail(erro);
		}

	}

	

	/**
	 * Pega o t�tulo da p�gina.
	 * 
	 * @return Retorna o t�tulo da p�gina.
	 * @author Rog�rio Figueiredo.
	 */
	public String pegarTitulo() {
		return driver.getTitle();

	}

	/**
	 * Fecha a p�gina
	 * 
	 * @author Rog�rio Figueiredo.
	 */
	public void fechar() {
        driver.close();
	}

	/**
	 * Maximiza o navegador.
	 * 
	 * @author Rog�rio Figueiredo.
	 */
	public void maximizar() {
		driver.manage().window().maximize();

	}

	/**
	 * Faz upload de arquivo.
	 * 
	 * @param EnumElemento
	 *            Passe o tipo do elemento, � um enumerado.
	 * @param nomeElemento
	 *            Passe o nome do elemento.
	 * @param caminhoArquivo
	 *            Passe o caminho do arquivo.
	 * @author Rog�rio Figueiredo.
	 */
	public void upload(EnumElemento EnumElemento, String nomeElemento,
			String caminhoArquivo) {
		WebElement elemento = null;
		elemento = pegarElemento(EnumElemento, nomeElemento);
		elemento.sendKeys(caminhoArquivo);

	}

	/**
	 * Pega o valor do atributo do elemento.
	 * 
	 * @param EnumElemento
	 *            Passe o tipo do elemento, � um enumerado.
	 * @param nomeElemento
	 *            Passe o nome do elemento.
	 * @return Retorna o atributo do elemento.
	 * @author Rog�rio Figueiredo.
	 */

	public String pegarValorAtributo(EnumElemento EnumElemento,
			String nomeElemento, String atributo) {
		WebElement elemento = null;
		elemento = pegarElemento(EnumElemento, nomeElemento);

		if (elemento == null) {
			String erro = ("Voc� usou a fun��o pegarValorAtributo, mas n�o foi\n"
					+ "poss�vel pegar o valor do atributo no elemento usando as informa��es que voc� passou:\n"
					+ "Nome do elemento: "
					+ "<"
					+ nomeElemento
					+ ">"
					+ "\n"
					+ "Tipo do Elemento: " + "<" + EnumElemento + ">" + "\n" + "A��o: Pegar atributo\n");

			System.err.println(erro);
			fechar();
			fail(erro);
		}

		return elemento.getAttribute(atributo);

	}

	/**
	 * Escreve um texto no elemento.
	 * 
	 * @param EnumElemento
	 *            Passe o tipo do elemento, � um enumerado.
	 * @param nomeElemento
	 *            Passe o nome do elemento.
	 * @param escreverTexto
	 *            Passe o texto que deseja escrever no elemento.
	 * @author Rog�rio Figueiredo.
	 */
	public void escreverTexto(EnumElemento EnumElemento, String nomeElemento,
			String escreverTexto) {

		escreverTexto(EnumElemento, nomeElemento, escreverTexto, false);

	}

	public void escreverTexto(EnumElemento EnumElemento, String nomeElemento,
			String escreverTexto, Boolean submit) {
		WebElement elemento = null;
		elemento = pegarElemento(EnumElemento, nomeElemento);

		if (!(elemento == null)) {
			elemento.click();
		} else {
			String erro = ("Voc� usou a fun��o escreverTexto, mas n�o foi\n"
					+ "poss�vel escrever no elemento usando as informa��es que voc� passou:\n"
					+ "Nome do elemento: " + "<" + nomeElemento + ">" + "\n"
					+ "Tipo do Elemento: " + "<" + EnumElemento + ">" + "\n" + "A��o: Escrever\n");

			System.err.println(erro);
			fechar();
			fail(erro);
		}

		elemento.clear();
		elemento.sendKeys(escreverTexto);
		if (submit) {
			elemento.submit();
		}

	}

	/**
	 * Ajustar o scroll vertical.
	 * 
	 * @param altura
	 *            Passe um n�mero inteiro.
	 * @author Rog�rio Figueiredo
	 */
	public void ScroolAjustar(int altura) {
		String teste = "window.scrollBy(0," + altura + ")";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(teste, "");
	}

	/**
	 * M�todo usado para fechar popUp que � aberto ap�s fechar uma p�gina.
	 * 
	 * @author Rog�rio Figueiredo
	 */
	public void fecharTodasJanelas() {
		fecharTodasJanela();
		aguardar(500);
		fecharTodasJanela();
	}

	private void fecharTodasJanela() {
		WebDriver popup = null;
		Iterator<String> windowIterator = driver.getWindowHandles().iterator();
		while (windowIterator.hasNext()) {
			String windowHandle = windowIterator.next();
			popup = driver.switchTo().window(windowHandle);
			popup.close();
		}
	}

	/**
	 * Aguarda um determinado tempo para prosseguir, tempo em milesegundos.
	 * 
	 * @param milesegundos
	 *            Passe o tempo que deseja aguardar.
	 * @author Rog�rio Figueiredo.
	 */
	public void aguardar(int milesegundos) {
		try {
			Thread.sleep(milesegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tira uma foto da p�gina
	 * 
	 * @param path
	 *            - Retorna o local que a imagem foi gravada.
	 * @author Rog�rio Figueiredo.
	 */

	public String captureScreen(String path) {
		path = path + "\\";
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);
			path = path + source.getName();
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}

		System.out.println("Local que foi gravado a imagem: " + path);
		return path;
	}

	public boolean clicarAlerta() {
		try {
			Alert alerta = driver.switchTo().alert();
			System.out.println("Alerta Exibido: "
					+ driver.switchTo().alert().getText());
			alerta.accept();
			System.out.println("Clicou no alerta!");
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}
}
