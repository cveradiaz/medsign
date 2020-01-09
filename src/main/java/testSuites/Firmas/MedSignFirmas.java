package testSuites.Firmas;

import driver.DriverContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporter.ImedReports;
import testClass.CPA001CreacionFirmaClave;
import testClass.CPA002CreacionFirmaHsm;
import testClass.CPA003wsShow;
import testClass.CPA004wsCreateTemplate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.IOException;

public class MedSignFirmas {
    @BeforeMethod
    public void setUp(){
        ImedReports.createPDF ();
    }

    @AfterMethod
    public void tearDown() {

        DriverContext.quitDriver();
    }

    @Test
    public void CPA001CreacionFirmaClave() throws ParserConfigurationException, IOException, TransformerException, AWTException, InterruptedException {
        CPA001CreacionFirmaClave firmaClave = new CPA001CreacionFirmaClave();
        firmaClave.firmaConClave();
        ImedReports.closePDF();

    }

    @Test
    public void CPA002CreacionFirmaHsm() throws AWTException, ParserConfigurationException, IOException, InterruptedException, TransformerException {
        CPA002CreacionFirmaHsm firmaHsm = new CPA002CreacionFirmaHsm ();
        firmaHsm.firmaConHsm ();
        ImedReports.closePDF ();
    }

    @Test
    public void CPA003wsShow() throws IOException, TransformerException, ParserConfigurationException {
        CPA003wsShow wsShow = new CPA003wsShow ();
        wsShow.wsShow ();
        ImedReports.closePDF ();
    }

    @Test
    public void CPA004wsCreateTemplate() throws IOException, TransformerException, ParserConfigurationException {
        CPA004wsCreateTemplate wsCreateTemplate = new CPA004wsCreateTemplate ();
        wsCreateTemplate.wsCreateTemplate ();
        ImedReports.closePDF ();
    }
}
