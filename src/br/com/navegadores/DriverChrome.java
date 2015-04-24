package br.com.navegadores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import br.com.configuracoes.InterNavegador;

public class DriverChrome implements InterNavegadores {

	private ChromeDriverService chromeService;
	
	private WebDriver getChromeConfigurado(String proxy) {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		ArrayList<String> switches = new ArrayList<String>();
		
		//Habilitar todas as extensões, mas o problema que não carrega o proxy correto
		//switches.add("--user-data-dir="+System.getProperty("user.home")+"\\AppData\\Local\\Google\\Chrome\\User Data");
		//switches.add("--load-extension="+System.getProperty("user.home")+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\chklaanhfefbnpoihckbnefhakgolnmc\\0.0.32_0");
		
		if (!(proxy.equals(""))) {
			System.out.println("Passou aqui");
			switches.add("--proxy-server=" + "http://" + proxy);
						
		} else {
			System.out.println("Você não está usando proxy!");
		}
		switches.add("--ignore-certificate-errors");
		switches.add("--start-maximized");
		
		//switches.add("--disable-extensions"); Desabilitar todas as extensões.
		
		capabilities.setBrowserName("chrome");
		
		capabilities.setJavascriptEnabled(true);

		
		capabilities.setCapability("chrome.switches", switches);
		chromeService = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(InterNavegador.PATH_CHROME))
				.usingAnyFreePort().build();
				
		try {
			chromeService.start();

		} catch (IOException e) {
			System.out.println(e);
		}

		return new RemoteWebDriver(chromeService.getUrl(), capabilities);
	}

	@Override
	public WebDriver getDriver(String proxy) {
		return getChromeConfigurado(proxy);
	}

	

}
