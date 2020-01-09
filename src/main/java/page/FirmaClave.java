package page;

import driver.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reporter.EstadoPrueba;
import reporter.ImedReports;
import utils.metodosGenericos;

import static utils.metodosGenericos.visualizarObjeto;


public class FirmaClave {

    private WebDriver driver;

    public FirmaClave() {
        this.driver = DriverContext.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath ="//a[contains(text(),'Firma Clave')]")

    private WebElement btn_clave;



   /*
    //a[(@data-type ="clave")]
    //div//a[(@data-type ="clave")]
      //div[contains(@class,'col-sm-4 col-md-6')]//a[contains(text(),'Firma Clave')]
    //*[@data-type="clave"]
    //*[@id="signing"]
    */


    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement txt_Pass_clave;

    @FindBy(xpath = "//*[@id=\"sign\"]")
    private WebElement btn_firmar_clave;


    public void prueba(){

        boolean hola;
        hola = btn_clave.isEnabled ();
        System.out.println ("dgsds"+hola );
    }

    public void FirmaClave() throws InterruptedException {



        WebElement svgObject = driver.findElement(By.xpath("//*[@id=\"signing\"]"));
        Actions builder = new Actions (driver);
        builder.click(svgObject).build().perform();




        Thread.sleep (4000);

        boolean hsm = visualizarObjeto(btn_clave, 60);
        System.out.println(hsm);
        if (hsm) {
            btn_clave.click();
           ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);
        } else {
            btn_clave.click();
            btn_clave.click ();
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }
    }

    public void ingresarPassClave() {
        boolean clave = visualizarObjeto(txt_Pass_clave, 60);
        if (clave) {
            txt_Pass_clave.sendKeys("Shinaide4");
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);
        } else {
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }

        boolean firmar = visualizarObjeto(btn_firmar_clave, 60);
        if (firmar) {
            System.out.println("boton firmasa " + firmar);
            btn_firmar_clave.click();
            ImedReports.addReport("esperaElemento", "Se valida el despliegue el ingreso de usuario", EstadoPrueba.PASSED, false);
            metodosGenericos.reporteObjetoDesplegado(true, "Se valida el despliegue el ingreso de usuario", "ingreso de usuario", false);
        } else {
            ImedReports.addReport("esperaElemento", "Despliegue incorrecto el ingreso de usuaruio", EstadoPrueba.FAILED, true);
            metodosGenericos.reporteObjetoDesplegado(false, "Despliegue incorrecto el ingreso de usuario", "ingreso de usuario", true);
        }
    }




    }



