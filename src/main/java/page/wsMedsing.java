package page;

import constans.Archivo;
import constans.Constants;
import driver.DriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.MetodosMedsign;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class wsMedsing {

    private WebDriver driver;

    public wsMedsing() {
        this.driver = DriverContext.getDriver();
        PageFactory.initElements(this.driver, this);
    }



    public void WsCreacionClave() throws IOException, TransformerException, ParserConfigurationException {

        MetodosMedsign.leerExcel(Archivo.createClave);
        String Response = MetodosMedsign.obtenerResponse(Constants.requestQA, Archivo.createClave);
        System.out.println("probando" + Response);
        Constants.URL_MEDSIGN = MetodosMedsign.buscarTag(Response, "Url");
    }

    public void WsCreacionHsm() throws IOException, TransformerException, ParserConfigurationException {

        MetodosMedsign.leerExcel(Archivo.createHsm);
        String Response = MetodosMedsign.obtenerResponse(Constants.requestQA, Archivo.createHsm);
        System.out.println("probando" + Response);
        Constants.URL_MEDSIGN = MetodosMedsign.buscarTag(Response, "Url");
    }

    public void WsCreacionShow() throws IOException, TransformerException, ParserConfigurationException {

        MetodosMedsign.leerExcel(Archivo.show);
        String Response = MetodosMedsign.obtenerResponse(Constants.requestQA, Archivo.show);
        System.out.println("probando" + Response);
        Constants.URL_MEDSIGN = MetodosMedsign.buscarTag(Response, "Url");
    }

    public void WsCreateTemplate() throws IOException, TransformerException, ParserConfigurationException {

        MetodosMedsign.leerExcel(Archivo.createTemplate);
        String Response = MetodosMedsign.obtenerResponse(Constants.requestQA, Archivo.createTemplate);
        System.out.println("probando" + Response);
        Constants.URL_MEDSIGN = MetodosMedsign.buscarTag(Response, "Url");
    }

}
