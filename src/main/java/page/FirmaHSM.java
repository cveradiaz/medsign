package page;

import driver.DriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reporter.EstadoPrueba;
import reporter.ImedReports;
import utils.metodosGenericos;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FirmaHSM {
    private WebDriver driver;

    public FirmaHSM() {
        this.driver = DriverContext.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@data-type=\"hsm\"]")
    private WebElement btn_hsm;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement txt_Pass_hsm;

    @FindBy(xpath = "//*[@id=\"sign\"]")
    private WebElement btn_firmar_hsm;


    public void FirmaHSM() throws InterruptedException {

        boolean hsm = metodosGenericos.visualizarObjeto(btn_hsm, 60);
        System.out.println(hsm);
        if (hsm) {
            btn_hsm.click();
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);
        } else {
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }
    }

    public void ingresarPassHsm() {
        boolean clave = metodosGenericos.visualizarObjeto(txt_Pass_hsm, 60);
        if (clave) {
            txt_Pass_hsm.sendKeys("123456");
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);

        } else {
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }

        boolean firmar = metodosGenericos.visualizarObjeto(btn_firmar_hsm, 60);
        if (firmar) {
            btn_firmar_hsm.click();
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);
        } else {
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }
    }


    public  void tabFirmaHsm() throws AWTException {
        Robot robot = new Robot ();


        //Funcion para Medsign
        robot.keyPress (KeyEvent.VK_TAB);
        robot.keyRelease (KeyEvent.VK_TAB);
        robot.keyPress (KeyEvent.VK_TAB);
        robot.keyRelease (KeyEvent.VK_TAB);
        robot.keyRelease (KeyEvent.VK_TAB);
        robot.keyPress (KeyEvent.VK_TAB);
        robot.keyRelease (KeyEvent.VK_TAB);
        robot.keyPress (KeyEvent.VK_TAB);
        robot.keyRelease (KeyEvent.VK_TAB);
        robot.keyRelease (KeyEvent.VK_TAB);
        robot.keyPress (KeyEvent.VK_TAB);
        robot.keyRelease (KeyEvent.VK_TAB);
        robot.keyPress (KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


}
