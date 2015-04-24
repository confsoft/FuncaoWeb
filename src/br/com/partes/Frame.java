package br.com.partes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Frame {

	private WebDriver driver = null;

	public Frame(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Acessa um frame usando como referência o nome do frame.
	 * @param param_nome
	 * Passe o nome do frame.
	 * @author Rogério Figueiredo.
	 */
	public void frame(String param_nome) {
		driver.switchTo().frame(param_nome);
		
	}

	/**
	 * Acessa um frame usando como referência o indice do frame.
	 * @param param_index
	 * Passe o indice do frame.
	 * @author Rogério Figueiredo.
	 */
	public void frame(int param_index) {
		driver.switchTo().frame(param_index);
		
	}

	/**
	 * Retorna ao frame original da página.
	 * @author Rogério Figueiredo.
	 */
	public void framePadrao() {
		driver.switchTo().defaultContent();
	}

	public void framePorID(String param_id)
	{
		driver.switchTo().frame(driver.findElement(By.id(param_id)));
		
	}
	
	public void framePorXpath(String param_xpath)
	{
		driver.switchTo().frame(driver.findElement(By.xpath(param_xpath)));
		
	}

}
