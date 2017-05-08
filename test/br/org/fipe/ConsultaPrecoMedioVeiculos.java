package br.org.fipe;

import br.com.configuracoes.EnumElemento;

public class ConsultaPrecoMedioVeiculos extends ObjetoPaginaBase {
	
	/**
	 * Navegar para pagina princiapl de consulta de veiculos
	 */
	public void navegar(){
		funcao.navegar("http://www.fipe.org.br/pt-br/indices/veiculos/#carro");
	}
	/**
	 * Navegar para a pagina de consulta de carros e utilitarios
	 */
	public void consultaCarrosClickLink(){
		funcao.clicar(EnumElemento.XPATH, "//a[@data-action=\"veiculos\"]");
	}
	/**
	 * Selecionar uma marca de carro a partir do texto da marca 
	 * @param marca valor Passar texto que e exibido da marca
	 */
	public void selecionaMarca(String marca){
		funcao.usarLista(EnumElemento.ID, "ddlMarca").selecionarOpcaoPeloTexto(marca);
	}
	/**
	 * Selecionar o modelo a partir do texto
	 * @param modelo Passar texto que e exibido do modelo
	 */
	public void selecionarModelo(String modelo){
		funcao.usarEspera().elementoEstarClicavel(EnumElemento.ID, "ddlModelo", 10);
		funcao.usarLista(EnumElemento.ID, "ddlModelo").selecionarOpcaoPeloTexto(modelo);
	}
	/**
	 * Selecionar o ano
	 * @param ano passar texto que e exibido na lista
	 */
	public void selecionarAno(String ano){
		//
		funcao.usarEspera().elementoEstarClicavel(EnumElemento.ID, "ddlAnoValor", 10);
		funcao.usarLista(EnumElemento.ID, "ddlAnoValor").selecionarOpcaoPeloTexto(ano);
	}
	/**
	 * Retorna o valor exibido da consulta de precos medios de veiculos<br />
	 * Utilize sempre apos selecionar o ANO para evitar erros :x
	 * @return retorna valor exibido na pesquisa
	 */
	public float buscaValorResultado(){
		return Float.parseFloat(funcao.pegarTexto(EnumElemento.ID, "lblValor").replace("R$", "").replace(".", "").replace(",", "")) / 100;
	}
	
	/**
	 * Exibe caixa de texto com uma mensagem
	 * @param mensagem mensagem a ser exibida
	 */
	public void mensagem(String mensagem){
		funcao.usarUtilidade().msg("FIPE", mensagem);
	}
	/**
	 * Fecha navegador da pagina ConsultaPrecoMedioVeiculos
	 */
	public void fechar() {
		funcao.fechar();
	}
}
