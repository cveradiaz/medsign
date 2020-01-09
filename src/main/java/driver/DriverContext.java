package driver;


import constans.Navegador;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverContext {
    private static DriverManager driverManager = new DriverManager();
    private static Navegador tipoNavegador;
    private static String ambienteURL = "";


    public DriverContext() {
    }

    public static String getAmbienteURL() {
        return ambienteURL;
    }

    public static void setAmbienteURL(String ambienteURL) {
        ambienteURL = ambienteURL;
    }


    public static String getTipoNavegador() {
        return tipoNavegador.toString();
    }

    public static void setTipoNavegador(Navegador tipoNavegador) {
        tipoNavegador = tipoNavegador;
    }

    public static void setUp(Navegador nav, String ambURL) {
        setTipoNavegador(nav);
        System.out.println(nav);
        setAmbienteURL(ambURL);
        System.out.println(ambURL);
        System.out.println("driver context");

        driverManager.resolveDriver(nav, ambURL);
    }

    public static WebDriver getDriver() {
        return driverManager.getDriver();
    }

    public static void setDriverTimeout(Integer tiempo) {
        driverManager.getDriver().manage().timeouts().implicitlyWait((long)tiempo, TimeUnit.SECONDS);
    }

    public static void quitDriver() {
        driverManager.getDriver().quit();
    }

    public static Dimension getSreenSize() {
        return driverManager.getScreenSize();
    }
}

