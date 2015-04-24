package br.com.navegadores;

import org.openqa.selenium.WebDriver;

public class Drivers {

	public WebDriver getDriver(InterNavegadores navegador, String proxy) {
		WebDriver driver = navegador.getDriver(proxy);
		return driver;
	}

}
