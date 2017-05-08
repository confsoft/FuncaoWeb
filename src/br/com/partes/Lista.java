package br.com.partes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import br.com.configuracoes.EnumElemento;

import java.util.List;

public class Lista {

    private WebElement elemento = null;

    /**
     * Construtor.
     *
     * @param elemento Passe o elemento que deseja usar.
     * @author Rogério Figueiredo.
     */
    public Lista(WebElement elemento) {
        this.elemento = elemento;
    }

    /**
     * Construtor sem dependencia de WebElement
     *
     * @param nucleo       a classe funcao que esta sendo usada
     * @param EnumElemento tipo de elemento
     * @param nomeElemento nome do elemento a ser usado
     */
    public Lista(Nucleo nucleo, EnumElemento EnumElemento, String nomeElemento) {
        this.elemento = nucleo.pegarElemento(EnumElemento, nomeElemento);
    }

    /**
     * Seleciona uma opção da lista com base no seu indice.
     *
     * @param indice Passe o indice do item da lista que deseja selecionar.
     * @author Rogério Figueiredo.
     */
    public void selecionarOpcaoPeloIndice(int indice) {
        new Select(this.elemento).selectByIndex(indice);
    }

    /**
     * Seleciona uma opção da lista com base no seu valor.
     *
     * @param valor Passe o valor do item da lista que deseja selecionar.
     * @author Rogério Figueiredo.
     */
    public void selecionarOpcaoPeloValor(String valor) {
        new Select(this.elemento).selectByValue(valor);
    }

    /**
     * Seleciona uma opção da lista com base no seu nome.
     *
     * @param texto Passe o nome do item da lista que deseja selecionar.
     * @author Rogério Figueiredo.
     */
    public void selecionarOpcaoPeloTexto(String texto) {
        new Select(this.elemento).selectByVisibleText(texto);
    }

    /**
     * Pega todas as opções da lista e retorna eles.
     *
     * @return Retorna todas as opções da lista.
     * @author Rogério Figueiredo.
     */

    public List<WebElement> getItens() {
        return new Select(this.elemento).getOptions();
    }

    /**
     * Pega o item selecionado e retorna o mesmo.
     *
     * @return Retorna o item selecionado.
     * @author Rogério Figueiredo.
     */
    public String getItemSelecionado() {
        return new Select(this.elemento).getFirstSelectedOption().getText();
    }

    /**
     * Remove a seleção de todos os itens
     *
     * @author Rogério Figueiredo.
     */
    public void removerSelecao() {
        Select e = new Select(this.elemento);

        if (e.isMultiple()) {
            e.deselectAll();
        } else {
            selecionarOpcaoPeloIndice(0);
        }
    }
}
