package page;

import constans.Constants;
import driver.DriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reporter.EstadoPrueba;
import reporter.ImedReports;
import utils.metodosGenericos;

public class DocumentoFirmado {
    private WebDriver driver;

    public DocumentoFirmado() {
        this.driver = DriverContext.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,\"card shadow-sm sidebar-column\")]//h5[1]")
    private WebElement lbl_hsm_ic;

    @FindBy(xpath = "//span[contains(@class,\"text-primary\")]")
    private WebElement lbl_hsm_status;

    @FindBy(xpath = "//*[@id=\"signed\"]")
    private WebElement lbl_hsm_Procesar;

    @FindBy(xpath = "//button[contains(@id,\"signed\")]")
    private WebElement btnPrFirma;

    public void esperarProceso() {
        boolean existe = false;
        int intentos = 0;
        while ((!existe) & intentos < 10) {
            System.out.println(intentos);
            existe = metodosGenericos.visualizarObjeto (btnPrFirma, 10);
            System.out.println("esperamos que apareca el proceso de firma " + existe);
            if (!existe) {
                intentos++;
            } else {
                System.out.println("Se encuentra Elemento :" + btnPrFirma.getText());
            }
        }
    }


    public void procesarFirma() throws InterruptedException {
        boolean existe = false;
        int intentos = 0;
        while ((!existe) && intentos <= 5) {
            System.out.println(intentos);
            existe = metodosGenericos.visualizarObjeto(lbl_hsm_ic, 10);
            System.out.println("buscamos el procesando firma " + existe);
            if (!existe) {
                DriverContext.getDriver().navigate().refresh();
                intentos++;
            } else {
                System.out.println("Se encuentra Elemento :" + lbl_hsm_ic.getText());
            }
        }
        if (!existe) {
            ImedReports.addWebReportImage("esafsfaf", "holan pruebas", EstadoPrueba.FAILED, false);
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.FAILED, true);
        }

    }


    public void estadoFirmaHsm() {
        DriverContext.setAmbienteURL(Constants.URL_MEDSIGN);
        boolean estado = metodosGenericos.visualizarObjeto(lbl_hsm_ic, 60);
        if (estado) {
            String IC = "";
            IC = lbl_hsm_ic.getText();
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);
        } else {
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }
        boolean status = metodosGenericos.visualizarObjeto(lbl_hsm_status, 60);
        if (status) {
            String Status = "";
            Status = lbl_hsm_status.getText();
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);

        } else {
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }
    }




}
