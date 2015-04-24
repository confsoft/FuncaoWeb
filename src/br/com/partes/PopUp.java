package br.com.partes;

import java.util.Iterator;

import org.openqa.selenium.WebDriver;


public class PopUp {
	
	private String janelaAtual;
	private String janelaInicial;
	private WebDriver driver;
	
	private void setJanelaAtual(){
		janelaAtual = driver.getWindowHandle();
	}

	public String getJanelaAtual(){
		return janelaAtual;
	}

	public void setJanelaInicial(){
		janelaInicial = driver.getWindowHandle();
	}

	public String getJanelaInicial(){
		return janelaInicial;
	}	
	
	
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}

	public WebDriver getDriver(){
		return driver;
	}
	
	/**
	 * Método Construtor.
	 * @param drive
	 * Passe o driver.
	 */
	public PopUp(WebDriver drive){		
		setDriver(drive);
		setJanelaAtual();
		setJanelaInicial();
	}
	
	/**
	 * Seleciona uma janela com base no título da janela.
	 * @param titulo
	 * Passe o título da janela que deseja selecionar.
	 */
	public void selecionaPopUpByTitulo(String titulo){
		WebDriver popup = null;
		Iterator<String> windowIterator = driver.getWindowHandles().iterator();
	    while(windowIterator.hasNext()) { 
	    	String windowHandle = windowIterator.next(); 
	        popup = driver.switchTo().window(windowHandle);
	        if (popup.getTitle().equals(titulo)){
	        	setJanelaAtual();
	        	break;
	        }
	    }	    
	}
	
	
	/**
	 * Seleciona uma janela usando como base um texto que exista na mesma.
	 * @param texto
	 * Passe um texto que exista na janela que deseja selecionar.
	 */
	public void selecionaPopUpByConteudo(String texto){
		WebDriver popup = null;
		Iterator<String> windowIterator = driver.getWindowHandles().iterator();
	    while(windowIterator.hasNext()) { 
	    	String windowHandle = windowIterator.next(); 
	        popup = driver.switchTo().window(windowHandle);
	        if (popup.getPageSource().contains(texto)){
	        	setJanelaAtual();
	        	break;
	        }
	    }	    
	}
    
	/**
	 * Seleciona a janela inicial.
	 */
	public void selecionaJanaleInicial(){
		
		driver.switchTo().window(this.janelaInicial);
    }
	
	/**
	 * Fecha a janela atual e volta para janela inicial.
	 * @author Rogério Figueiredo.
	 */
	public void fechaPopUp(){
		driver.close();
		selecionaJanaleInicial();
	}
}
