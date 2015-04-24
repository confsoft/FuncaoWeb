package br.com.partes;

import br.com.configuracoes.EnumElemento;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

public class Espera {

	private Nucleo nucleo = null;

	public Espera(Nucleo nucleo) {
		this.nucleo = nucleo;
	}

	public void wait(int n) {
		long t0, t1;
		t0 = System.currentTimeMillis();
		do {
			t1 = System.currentTimeMillis();
		} while ((t1 - t0) < (n * 1000));
	}

	// Elementos Presentes

	/**
	 * Espera um texto por um tempo infinito.
	 * 
	 * @param texto
	 *            Passe o texto que deseja esperar aparecer.
	 * @author Rog�rio Figueiredo.
	 */
	public void textoPresente(String texto) {
		while (true) {
			if (nucleo.getDriver().getPageSource().contains(texto)) {
				break;
			}
		}
	}

	/**
	 * Espera um texto por um tempo estabelecido.
	 * 
	 * @param texto
	 *            Passe o texto que deseja esperar aparecer.
	 * @param tempo
	 *            Passe o tempo que deseja aguardar o texto aparecer, tempo em
	 *            segundos.
	 * @return Retorna um booleano.
	 * @author Rog�rio Figueiredo.
	 */
	public boolean textoPresente(String texto, int tempo) {
		int count = 0;
		while (count <= tempo) {
			wait(1);
			if (nucleo.getDriver().getPageSource().contains(texto)) {
				return true;
			}
			count++;
		}
		return false;
	}

	/**
	 * Espera o atributo ter um valor definido por um tempo infinito.
	 * 
	 * @param elemento
	 *            Passe o elemento.
	 * @param atributo
	 *            Passe o atributo do elemento.
	 * @param valor
	 *            Passe o valor que espera que exista no atributo.<br>
	 * <br>
	 *            <b>Veja um exemplo:</b><br>
	 *            funcao.usarEspera().atributoPresente(funcao.usarBasico().
	 *            pegarElemento(EnumElemento.ID, "login"), "maxlength","255");<br>
	 * <br>
	 * @author Rog�rio Figueiredo.
	 */
	public void atributoPresente(WebElement elemento, String atributo,
			String valor) {
		if (elemento == null) {
			String erro = ("N�o foi encontrado o elemento");

			System.err.println(erro);
			nucleo.getDriver().close();
			fail(erro);
		}
		while (true) {
			if (elemento.getAttribute(atributo).equals(valor)) {
				break;
			}
		}
	}

	/**
	 * Espera o atributo ter um valor definido por um tempo determinado, tempo
	 * em segundos.
	 * 
	 * @param elemento
	 *            Passe o elemento.
	 * @param atributo
	 *            Passe o atributo do elemento.
	 * @param valor
	 *            Passe o valor que espera que exista no atributo.
	 * @param tempo
	 *            Passe o tempo que deseja esperar o atributo ter o valor
	 *            definido.<br>
	 * <br>
	 *            <b>Veja um exemplo:</b><br>
	 *            funcao.usarEspera().atributoPresente(funcao.usarBasico().
	 *            pegarElemento(EnumElemento.ID, "login"), "maxlength","255",5);<br>
	 * <br>
	 * @author Rog�rio Figueiredo.
	 */
	public boolean atributoPresente(WebElement elemento, String atributo,
			String valor, int tempo) {

		int count = 0;
		while (count <= tempo) {

			wait(1);
			if (elemento.getAttribute(atributo).equals(valor)) {
				return true;
			}
			count++;
		}
		return false;

	}

	/**
	 * Espera por um tempo infinito pela presen�a de um elemento.
	 * 
	 * @param elemento
	 *            Passe o elemento que deseja esperar eternamente pela sua
	 *            presen�a.
	 * @author Rog�rio Figueiredo.
	 */
	public void elementoPresente(WebElement elemento) {
		try {

			while (true) {
				if (!(elemento == null)) {
					if (elemento.isDisplayed()) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Espera por um tempo determinado pela presen�a de um elemento, tempo em
	 * segundos.
	 * 
	 * @param EnumElemento
	 *            Passe o elemento.
	 * @param tempo
	 *            Passe o tempo que deseja ficar esperando o elemento
	 *            aparecer.(segundos)
	 * @return Retorna um booleano.
	 */
	public boolean elementoEstarClicavel(final EnumElemento EnumElemento, final String nomeElemento, int tempo) {
		// cria um novo objeto de espera do WebDriver
		new WebDriverWait(this.nucleo.getDriver(), tempo)
			// uma nova condi��o foi criada, verificando se o elemento esta habilitado
			.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver driver){
					WebElement elemento = nucleo.pegarElemento(EnumElemento, nomeElemento);
					if (elemento.isEnabled()){
						return elemento;
					}
					return null;
				}
			});
		return true;
	}

	/**
	 * Espera por um tempo determinado pela presen�a de um elemento, tempo em
	 * segundos.
	 * 
	 * @param elemento
	 *            Passe o elemento.
	 * @param tempo
	 *            Passe o tempo que deseja ficar esperando o elemento aparecer.
	 * @return Retorna um booleano.
	 */
	public boolean elementoPresente(WebElement elemento, int tempo) {
		try {
			int count = 0;

			while (count <= tempo) {
				wait(1);

				if (!(elemento == null)) {
					if (elemento.isDisplayed()) {
						return true;
					}
					count++;
				} else {
					return false;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * Verifica se existe o texto na p�gina e retorna um booleano.
	 * 
	 * @param texto
	 *            Passe o texto que deseja verificar se existe na p�gina ou no
	 *            c�digo fonte.
	 * @return Retorna true ou false.
	 */
	public Boolean existeTextoNaPagina(String texto) {
		boolean retorno = nucleo.getDriver().getPageSource().contains(texto);
		return retorno;
	}

	public Boolean existeTextoNaPaginaArray(String[] lista) {

		boolean retorno = false;

		for (int i = 0; i < lista.length; i++) {
			retorno = nucleo.getDriver().getPageSource().contains(lista[i]);
			if (retorno == true) {
				return true;
			}

		}

		return retorno;
	}

	/**
	 * Verifica se existe o textos na p�gina, se n�o existir ele falha e fecha.
	 * 
	 * @param procureEsseTexto
	 *            Passe o primeiro texto que deseja verificar se existe na
	 *            p�gina.
	 * @param ouProcureEsseTexto
	 *            Passe o segundo texto que deseja verificar se existe na
	 *            p�gina, caso n�o encontre o primeiro.
	 * @param encerrar
	 *            Passe true caso deseje encerrar o teste ap�s a falha.
	 */
	public void textoPresenteNaPaginaOu(String procureEsseTexto,
			String ouProcureEsseTexto, Boolean encerrar) {
		if (textoPresente(procureEsseTexto, 1)) {
			System.out.println("Texto:" + "< " + procureEsseTexto + " >"
					+ " Encontrado com Sucesso");
		} else {
			if (textoPresente(ouProcureEsseTexto, 1)) {
				System.out.println("Texto:" + "< " + ouProcureEsseTexto + " >"
						+ " Encontrado com Sucesso");
				return;
			}

			String erro = "N�o foram encontrados os textos:\n" + "< "
					+ procureEsseTexto + " >" + "\n" + "< "
					+ ouProcureEsseTexto + " >\n";
			System.err.println(erro);
			if (encerrar) {
				nucleo.getDriver().quit();
				fail(erro);
			}

		}
	}

	public void textoPresenteNaPaginaE(String procureEsseTexto,
			String eProcureEsseTexto, Boolean encerrar) {
		if (textoPresente(procureEsseTexto, 1)
				&& textoPresente(eProcureEsseTexto, 1)) {
			System.out.println("Texto:" + "< " + procureEsseTexto + " - "
					+ eProcureEsseTexto + " >" + " Encontrado com Sucesso");
		} else {
			String erro = "N�o foram encontrados os dois textos:\n" + "< "
					+ procureEsseTexto + " >" + "\n" + "< " + eProcureEsseTexto
					+ " >\n";
			System.err.println(erro);
			if (encerrar) {
				nucleo.getDriver().quit();
				fail(erro);
			}
		}
	}

}
