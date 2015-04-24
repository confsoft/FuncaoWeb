package br.org.fipe;

import br.com.configuracoes.InterProxy;
import br.com.funcao.Funcao;
import br.com.navegadores.DriverFireFox;

public class ObjetoPaginaBase {
	// campo funcao para utilizar fw FuncaoWeb
	protected Funcao funcao;
	/**
	 * Construtor Base para usar fw FuncaoWeb
	 */
	public ObjetoPaginaBase(){
		funcao = new Funcao(new DriverFireFox(), InterProxy.Homolog);
	}
	
}
