package br.com.navegadores;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.configuracoes.InterNavegador;

public class DriverInternetExplorer implements InterNavegadores {
	private InternetExplorerDriverService ieService;

	private WebDriver getIeConfigurado(String proxy) {

		org.openqa.selenium.Proxy proxyy = new org.openqa.selenium.Proxy();
		proxyy.setHttpProxy(proxy);
		proxyy.setFtpProxy(proxy);
		proxyy.setSslProxy(proxy);
		proxyy.setAutodetect(false);

		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						false);
		capabilities.setCapability(CapabilityType.PROXY, proxyy);
		capabilities.setBrowserName("ie");

		ieService = new InternetExplorerDriverService.Builder()
				.usingDriverExecutable(new File(InterNavegador.PATH_IEX))
				.usingAnyFreePort().build();
		try {
			ieService.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		return new RemoteWebDriver(ieService.getUrl(), capabilities);

		// return new InternetExplorerDriver(capabilities);

	}

	@Override
	public WebDriver getDriver(String proxy) {
		return getIeConfigurado(proxy);
	}

}
