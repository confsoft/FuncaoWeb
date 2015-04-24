package br.com.partes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Javascript {

	private WebDriver driver;

	public Javascript(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Metodo para executar comando javascript
	 * @param comando o comando que ser� executado
	 * @return retorna um objeto gen�rico, se objeto for nulo retorna uma string de aviso
	 * <br />
	 * Mais informa��es em: <a href="http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/JavascriptExecutor.html#executeScript%28java.lang.String,%20java.lang.Object...%29">
	 * referencia JavascriptExecutor</a>
	 */
	public Object executaComandoJavascript(String comando) {
		Object objeto = ((JavascriptExecutor) driver)
				.executeScript(comando, "");
		return objeto == null ? new String(
				"Comando Javascript retornou Objeto Nulo") : objeto;
	}

}
