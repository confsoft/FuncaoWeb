package br.com.navegadores;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.opera.core.systems.OperaDriver;

public class DriverOpera implements InterNavegadores {

	private WebDriver getOperaConfigurado(String proxy) {
		DesiredCapabilities capabilities = DesiredCapabilities.opera();

		ArrayList<String> switches = new ArrayList<String>();

		if (!(proxy.equals(""))) {
			switches.add("--proxy-server=" + "http://" + proxy);
		} else {
			System.out.println("Você não está usando proxy!");
		}

		switches.add("--start-maximized");
		capabilities.setCapability("opera.switches", switches);
		capabilities.setBrowserName("opera");
		capabilities.setJavascriptEnabled(true);

		return new OperaDriver(capabilities);
	}

	@Override
	public WebDriver getDriver(String proxy) {
		return getOperaConfigurado(proxy);
	}

}
