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
		funcao.clicar(EnumElemento.ID, "selectMarcacarro_chosen");
		funcao.usarEspera().elementoEstarClicavel(EnumElemento.XPATH, "//*[@id='selectMarcacarro_chosen']//input", 10);
		funcao.escreverTexto(EnumElemento.XPATH, "//*[@id='selectMarcacarro_chosen']//input", marca);
		funcao.clicar(EnumElemento.XPATH, "//*[@id='selectMarcacarro_chosen']//ul/li");
	}
	/**
	 * Selecionar o modelo a partir do texto
	 * @param modelo Passar texto que e exibido do modelo
	 */
	public void selecionarModelo(String modelo){
        funcao.clicar(EnumElemento.ID, "selectAnoModelocarro_chosen");
        funcao.usarEspera().elementoEstarClicavel(EnumElemento.XPATH, "//*[@id='selectAnoModelocarro_chosen']//input", 10);
        funcao.escreverTexto(EnumElemento.XPATH, "//*[@id='selectAnoModelocarro_chosen']//input", modelo);
        funcao.clicar(EnumElemento.XPATH, "//*[@id='selectAnoModelocarro_chosen']//ul/li");
	}
	/**
	 * Selecionar o ano
	 * @param ano passar texto que e exibido na lista
	 */
	public void selecionarAno(String ano){
        funcao.clicar(EnumElemento.ID, "selectAnocarro_chosen");
        funcao.usarEspera().elementoEstarClicavel(EnumElemento.XPATH, "//*[@id='selectAnocarro_chosen']//input", 10);
        funcao.escreverTexto(EnumElemento.XPATH, "//*[@id='selectAnocarro_chosen']//input", ano);
        funcao.clicar(EnumElemento.XPATH, "//*[@id='selectAnocarro_chosen']//ul/li");
	}
	/**
	 * Retorna o valor exibido da consulta de precos medios de veiculos<br />
	 * Utilize sempre apos selecionar o ANO para evitar erros :x
	 * @return retorna valor exibido na pesquisa
	 */
	public float buscaValorResultado(){
	    String elemento = "#resultadoConsultacarroFiltros > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(2) > p:nth-child(1)";
	    funcao.usarEspera().elementoEstarClicavel(EnumElemento.CSS, elemento, 10);
		return Float.parseFloat(
		        funcao.pegarTexto(EnumElemento.CSS, elemento)
                        .replace("R$", "")
                        .replace(".", "")
                        .replace(",", "")
                ) / 100;
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
