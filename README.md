FuncaoWeb
=========

Framework para facilitar testes com selenium WebDriver.

Para quem usa Selenium com Java 2.35 e gostaria de facilitar as coisas ao desenvolver testes.

Projeto escrito no eclipse com JUnit 4.

Recursos
--------

* Centralizar o uso do Selenium em um componente
* Deixar o código mais legivél escrevendo comandos em português
* Poupar preocupações técnicas do Selenium
* Usar cookies, javascript, frames, listas, etc. facilmente

Introdução
----------

Baixe o projeto para o seu workspace.

Crie um projeto Java, faça referência ao FuncaoWeb.

Escreva um teste usando o FuncaoWeb, por exemplo uma pesquisa do google sobre Selenium.

Criar uma classe JUnit 4 como abaixo:

	import static org.junit.Assert.*;

	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;

	import br.com.configuracoes.EnumElemento;
	import br.com.funcao.Funcao;
	import br.com.navegadores.DriverFireFox;

	public class TestePesquisaSelenium {

		// instanciado um objeto Funcao para trabalhar com FireFox
		br.com.funcao.Funcao funcao = new Funcao(new DriverFireFox());
	
		// navegar para a pagina de pesquisa antes de iniciar o teste
		@Before
		public void setUp() throws Exception {
			funcao.navegar("http://www.google.com.br/");
		}

		// exibe um "alerta" após o teste para ver o resultado
		@After
		public void tearDown() throws Exception {
			funcao.usarUtilidade().msg("Busca", "Fez busca");
			funcao.fechar();
		}

		/**
		 * teste de pesquisa,
		 * note que preciso apenas escrever texto 
		 * e para isso usamos apenas um método
		 */
		@Test
		public void test() {
			funcao.escreverTexto(EnumElemento.NAME, "q", "selenium hq", true);
			assertEquals("Existe pagina seleniumhq", 
					"Selenium - Web Browser Automation", 
					funcao.pegarTexto(EnumElemento.CLASSNAME, "r"));
		}

	}

Explore! Existe mais recursos e incentivamos sugestões para o projeto.

Veja recursos na wiki: [Listas](https://github.com/confsoft/FuncaoWeb/wiki/Usando-Lista)
