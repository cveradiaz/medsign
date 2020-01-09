package utils;

import driver.DriverContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reporter.EstadoPrueba;
import reporter.ImedReports;


public class metodosGenericos {



    public metodosGenericos() {
    }
    public static boolean visualizarObjeto(WebElement elementName, int timeout) {
        try {
            System.out.println ("Valida si Es visible el elemnto a validar.");
            WebDriverWait wait = new WebDriverWait (DriverContext.getDriver ( ), timeout);
            wait.until (ExpectedConditions.visibilityOf (elementName));
            System.out.println ("Es visible el elemnto a validar.");
            return true;
        } catch (Exception e) {
            System.out.println ("No es visible el elemento a validar.");
            return false;
        }
    }
    public static boolean validarEnable(WebElement objeto, int segundos) {
        System.out.println ("Se validará que el objeto:" + objeto + " se encuentre enabled en " + segundos + " segundos.");
        int milisegundos = segundos * 1000;
        boolean res = false;
        for (int i = 0; i < 9; ++i) {
            if (Utils.isEnabled (objeto)) {
                System.out.println ("El objeto:" + objeto + " se encuentra enabled.");
                res = true;
                break;
            }
            if (i == 9) {
                System.out.println ("El objeto:" + objeto + " después de " + segundos + " segundos no se encuentra enabled.");
                res = false;
            } else {
                try {
                    Thread.sleep ((long) milisegundos);
                } catch (InterruptedException var6) {
                    Assert.fail ("El Sleep del metodo validarEnable falló, el motivo:" + var6.getMessage ( ));
                }
            }
        }
        return res;
    }
    public static void reporteObjetoDesplegado(boolean estadoObjeto, String objeto, String vista, boolean fatal) {
        if (estadoObjeto) {
            ImedReports.addWebReportImage ("Elemento encontrado: " + objeto, "El objeto:" + objeto + ", se visualiza correctamente en la vista de " + vista + ".", EstadoPrueba.PASSED, false);
            ImedReports.addReport ("El objeto:" + objeto + ", se visualiza correctamente en la vista de " + vista + ".", "", EstadoPrueba.PASSED, false);
        } else {
            ImedReports.addWebReportImage ("Elemento no encontrado: " + objeto, "El objeto:" + objeto + ", no se visualiza  en la vista de " + vista + ".", EstadoPrueba.FAILED, false);
            ImedReports.addReport ("El objeto:" + objeto + ", no se visualiza  en la vista de " + vista + ".", "", EstadoPrueba.FAILED, true);
        }
    }
    public static boolean seleccionCombo(WebElement ComboBox, String descripcion) {
        boolean selec = metodosGenericos.visualizarObjeto (ComboBox, 10);
        if (selec) {
            Select Box = new Select (ComboBox);
            Box.selectByVisibleText (descripcion);
            ImedReports.addReport ("SeleccionCombo", "se realiza la seleccion del comboBOX", EstadoPrueba.PASSED, false);
            return true;
        } else {
            ImedReports.addWebReportImage ("NoSeleccionaComboBOX", "No se puede realizar seleccion ComboBOX", EstadoPrueba.FAILED, true);
            return false;
        }
    }
}