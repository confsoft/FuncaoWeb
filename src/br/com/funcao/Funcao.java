package br.com.funcao;

import org.openqa.selenium.WebDriver;

import br.com.conexao.InstrucaoSql;
import br.com.configuracoes.EnumElemento;
import br.com.navegadores.InterNavegadores;
import br.com.navegadores.Drivers;
import br.com.partes.Javascript;
import br.com.partes.Nucleo;
import br.com.partes.Cook;
import br.com.partes.Espera;
import br.com.partes.Frame;
import br.com.partes.Lista;
import br.com.partes.PopUp;
import br.com.ulilidades.GerarCPF;
import br.com.ulilidades.Util;

public class Funcao extends Nucleo {

	private GerarCPF geraCpf = null;
	private Frame frame = null;
	private Cook cook = null;
	private PopUp popUp = null;
	private Espera espera = null;
	private InstrucaoSql instrucaoSql = null;
	private Util util = null;
	private Javascript javascript = null;

	/**
	 * Construtor.
	 * 
	 * @param EnumNavegador
	 *            Passe o navegador que deseja usar na automa��o.
	 * @param EnumEnderecoProxy
	 *            Passe o endere�o do proxy a ser usado, se n�o tiver proxy
	 *            deixe em branco.
	 * @param habilitarFireBug
	 *            Passe true ou false para habilitar ou desabilitar o fireBug.
	 */
	public Funcao(InterNavegadores nav, String InterProxy) {
		super(new Drivers().getDriver(nav, InterProxy));
	}
	
	public Funcao(InterNavegadores nav) {
		super(new Drivers().getDriver(nav, ""));
	}

	/**
	 * Permite o acesso aos m�todos nativos do WebDriver.
	 * 
	 * @return Retorna o driver.
	 * @author Rog�rio Figueiredo
	 */
	public WebDriver usarDriver() {
		return getDriver();
	}

	/**
	 * Cont�m m�todos da classe busca da empresa.
	 * 
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 * @author Rog�rio Figueiredo.
	 */
	public InstrucaoSql usarInstrucaoSql() {

		if (instrucaoSql == null) {
			instrucaoSql = new InstrucaoSql();
		}

		return instrucaoSql;
	}

	/**
	 * Cont�m m�todos que geram CPF v�lidos.
	 * 
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 * @author Rog�rio Figueiredo.
	 */
	public GerarCPF usarGeraCPF() {

		if (geraCpf == null) {
			geraCpf = new GerarCPF();
		}

		return geraCpf;
	}

	/**
	 * Cont�m m�todos que acessa os frames.
	 * 
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 * @author Rog�rio Figueiredo.
	 */
	public Frame usarFrame() {
		if (frame == null) {
			frame = new Frame(getDriver());
		}

		return frame;
	}

	/**
	 * Cont�m m�todos para trabalhar com Cookie.
	 * 
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 * @author Rog�rio Figueiredo.
	 */
	public Cook usarCook() {
		if (cook == null) {
			cook = new Cook(getDriver());
		}

		return cook;
	}

	/**
	 * Cont�m m�todos para trabalhar com PopUp.
	 * 
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 * @author Rog�rio Figueiredo.
	 */
	public PopUp usarPopUp() {
		if (popUp == null) {
			popUp = new PopUp(getDriver());
		}

		return popUp;
	}

	/**
	 * Cont�m m�todos para trabalhar com a lista.
	 * 
	 * @param elemento
	 *            Passe o elemento, use a fun��o usarWeb.pegarElemento.
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 
	public Lista usarLista(WebElement elemento) {

		lista = new Lista(elemento);

		return lista;
	} */
	/**
	 * Trabalhar com lista sem dependencia de WebElement
	 * @param EnumElemento como acessar o elemento
	 * @param nomeElemento nome do elemento a ser acessado
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 */
	public Lista usarLista(EnumElemento EnumElemento, String nomeElemento) {
		return new Lista(this, EnumElemento, nomeElemento);
	}

	/**
	 * Cont�m m�todos para trabalhar com a espera.
	 * 
	 * @return Retorna a inst�ncia da classe para voc� ter acesso aos m�todos.
	 * @author Rog�rio Figueiredo.
	 */
	public Espera usarEspera() {
		if (espera == null) {
			espera = new Espera(this);
		}

		return espera;
	}

	public Util usarUtilidade() {

		if (util == null) {
			util = new Util();
		}

		return util;
	}
	
	public Javascript usarJavascript(){
		if(javascript == null){
			javascript = new Javascript(getDriver());
		}
		return javascript;
	}

}
