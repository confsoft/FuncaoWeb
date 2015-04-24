package br.com.navegadores;

import java.util.ArrayList;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class DriverSafari implements InterNavegadores {

	private WebDriver getSafariConfigurado(String proxy) {

		DesiredCapabilities capabilities = DesiredCapabilities.safari();

		ArrayList<String> switches = new ArrayList<String>();

		if (!(proxy.equals(""))) {
			switches.add("--proxy-server=" + "http://" + proxy);
		} else {
			System.out.println("Você não está usando proxy!");
		}

		switches.add("--start-maximized");

		System.out.println(isSupportedPlatform());
		capabilities.setPlatform(isSupportedPlatform());
		capabilities.setBrowserName("safari");
		capabilities.setJavascriptEnabled(true);

		return new SafariDriver(capabilities);

	}
	
	
	private Platform isSupportedPlatform() {
		return Platform.getCurrent();

	}
	@Override
	public WebDriver getDriver(String proxy) {
		return getSafariConfigurado(proxy);
	}

	

}
