package br.com.partes;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;


public class Cook {
	WebDriver drive = null;
	public Cook(WebDriver driver)
	{
		drive = driver;
	}
	
	/**
	 * Retorna o valor do cookie usando como referência o nome.
	 * @param nome
	 * Passe o nome do Cookie.
	 * @return
	 * Retorna o valor do Cookie.
	 * @author Rogério Figueiredo.
	 */
	public String getCookie(String nome){		
		return drive.manage().getCookieNamed(nome).toString();		
	}
	
	/**
	 * Adiciona um Cookie.
	 * @param nome
	 * Passe o nome do Cookie.
	 * @param valor
	 * Passe o valor do Cookie.
	 * @author Rogério Figueiredo.
	 */
	public void setCookie(String nome, String valor){		
		Cookie cookie = new Cookie(nome,valor);		
		drive.manage().addCookie(cookie);		
	}

	/**
	 * Remove um Cookie.
	 * @param nome
	 * Passe o nome do Cookie que deseja remover.
	 * @author Rogério Figueiredo
	 */
	public void removeCookie(String nome){				
		drive.manage().deleteCookie(drive.manage().getCookieNamed(nome));		
	}
	
	/**
	 * Remove todos os Cookies.
	 * @author Rogério Figueiredo
	 */
	public void limpaCookies(){				
		drive.manage().deleteAllCookies();		
	}
	
	/**
	 * Exibe todos os Cookies no console do Java.
	 * @author Rogério Figueiredo
	 */
	public void exibeTodosCookies()
	{
		Set<Cookie> allCookies = drive.manage().getCookies();
		for (Cookie loadedCookie : allCookies) {
		    System.out.println(String.format("%s -> %s", loadedCookie.getName(), loadedCookie.getValue()));
		}
	}
}
