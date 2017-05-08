package br.com.navegadores;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class DriverFireFox implements InterNavegadores {

    private WebDriver getFireFoxConfigurado(String proxy) {
        int porta = 0;
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("javascript.enabled", true);

        //if (habilitarFireBug) { //Criar uma flag para usar essa parte.
        //	setFireBug(profile);
        //	System.out.println("true");
        //}

        if (!(proxy.equals(""))) {

            String[] caminhoCompleto = proxy.split(", |:| ");
            proxy = caminhoCompleto[0];
            porta = Integer.parseInt(caminhoCompleto[1]);

            profile.setPreference("network.proxy.type", 1);
            setProxy(profile, proxy, porta);

            System.out.println(proxy + ":" + porta);
        } else {
            profile.setPreference("network.proxy.type", 0);
            System.out.println("proxy:''");
        }

        System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
        return new FirefoxDriver(profile);
    }

    private void setProxy(FirefoxProfile profile, String proxy, int porta) {

        profile.setPreference("network.proxy.http", proxy);
        profile.setPreference("network.proxy.http_port", porta);

        profile.setPreference("network.proxy.ssl", proxy);
        profile.setPreference("network.proxy.ssl_port", porta);

        profile.setPreference("network.proxy.ftp", proxy);
        profile.setPreference("network.proxy.ftp_port", porta);

        profile.setPreference("network.proxy.gopher", proxy);
        profile.setPreference("network.proxy.gopher_port", porta);

        profile.setPreference("network.proxy.socks", proxy);
        profile.setPreference("network.proxy.socks_port", porta);
        profile.setPreference("network.http.defensive-auth-prompting", false);
        profile.setPreference("security.ask_for_password", 0);

    }

//	private void setFireBug(FirefoxProfile profile) {
//
//		try {
//			profile.addExtension(new File(InterNavegador.path_firebug));
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		profile.setPreference("extensions.firebug.currentVersion", "16.0.2");
//		profile.setPreference("extensions.firebug.allPagesActivation", "on");
//
//		profile.setPreference("extensions.firebug.defaultPanelName", "net");
//		profile.setPreference("extensions.firebug.net.enableSites", true);
//		profile.setPreference("extensions.firebug.onByDefault", true);
//
//		profile.setPreference("extensions.firebug.DBG_NETEXPORT", false);
//		profile.setPreference("extensions.firebug.defaultPanelName", "net");
//		profile.setPreference("extensions.firebug.net.enableSites", true);
//		profile.setPreference("extensions.firebug.net.persistent", true);
//		profile.setPreference(
//				"extensions.firebug.netexport.alwaysEnableAutoExport", true);
//		profile.setPreference("extensions.firebug.netexport.autoExportToFile",
//				true);
//		profile.setPreference(
//				"extensions.firebug.netexport.autoExportToServer", false);
//		profile.setPreference("extensions.firebug.netexport.defaultLogDir",
//				"C:\\temp");
//		profile.setPreference("extensions.firebug.netexport.showPreview", true);
//		profile.setPreference(
//				"extensions.firebug.netexport.sendToConfirmation", false);
//		profile.setPreference("extensions.firebug.netexport.pageLoadedTimeout",
//				1500);
//		profile.setPreference("extensions.firebug.netexport.Automation", true);
//		profile.setPreference("extensions.firebug.consoleexport.active", true);
//	}

    public WebDriver getDriver(String proxy) {
        return getFireFoxConfigurado(proxy);
    }
}
