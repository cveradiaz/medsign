package driver;

import constans.Navegador;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

import static constans.Constants.browser;

public class DriverManager {

    private DesiredCapabilities capabilities = new DesiredCapabilities ();
    private WebDriver webDriver;
    private File root = new File("driverNavegador");
    private String extensionDriver = "";


    public DriverManager() {
    }

    protected void resolveDriver(Navegador nav, String ambURL) {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("\nSistema operativo ->" + System.getProperty("os.name").toLowerCase() + "\n");
        if (!os.contains("mac")) {
            this.extensionDriver = ".exe";
        }

        File driverPath;
        System.out.println(nav);


        switch(nav) {
            case Chrome:
                System.out.println("Se selecciona Chrome");
                browser = "Chrome";
                driverPath = new File(this.root, "chromedriver" + this.extensionDriver);
                System.setProperty("webdriver.chrome.driver", driverPath.getAbsolutePath());
                this.webDriver = new ChromeDriver ();
                /*
                ChromeOptions chromeOptions= new ChromeOptions();
                chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
                this.webDriver = new ChromeDriver(chromeOptions);
                
                 */
                this.capabilities.setBrowserName("Chrome");
                this.webDriver.manage().window().maximize();
                break;
            case Explorer:
                browser = "Explorer";
                System.out.println("Se selecciona Explorer");


                this.capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
                this.capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
                this.capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
                this.capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                this.capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                this.capabilities.setJavascriptEnabled(true);



                driverPath = new File(this.root, "IEDriverServer" + this.extensionDriver);

               System.setProperty("webdriver.ie.driver", driverPath.getAbsolutePath());


                this.webDriver =  new InternetExplorerDriver ();


                this.capabilities.setBrowserName("Explorer");

                this.webDriver.manage().window().maximize();

                break;
            case Firefox:
                browser = "Firefox";
                System.out.println("Se selecciona Firefox");
                driverPath = new File(this.root, "geckodriver" + this.extensionDriver);
                System.setProperty("webdriver.gecko.driver", driverPath.getAbsolutePath());
                this.webDriver = new FirefoxDriver();
                this.capabilities.setBrowserName("Firefox");
                this.webDriver.manage().window().maximize();
                break;
            case Edge:
                browser = "Edge";
                System.out.println("Se selecciona Edge");
                driverPath = new File(this.root, "MicrosoftWebDriver" + this.extensionDriver);
                System.setProperty("webdriver.edge.driver", driverPath.getAbsolutePath());
                this.webDriver = new EdgeDriver ();
                this.capabilities.setBrowserName("Microsoft Edge");
                this.webDriver.manage().window().maximize();
                break;
            case Safari:
                browser = "Safari";
                System.out.println("Se selecciona Safari");
                driverPath = new File(this.root, "safaridriver" + this.extensionDriver);
                System.setProperty("webdriver.safari.driver", driverPath.getAbsolutePath());
                this.webDriver = new SafariDriver ();
                this.capabilities.setBrowserName("Safari");
                this.webDriver.manage().window().maximize();
                break;
            default:
                System.out.println("No es posible lanzar el navegador, no se reconoce el navegador: " + nav);
        }

        this.webDriver.get(ambURL);
    }

    protected WebDriver getDriver() {
        return this.webDriver;
    }

    protected void setDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected Dimension getScreenSize() {
        return this.webDriver.manage().window().getSize();
    }
}
