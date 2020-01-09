package testClass;


import constans.Constants;
import constans.Navegador;
import driver.DriverContext;
import page.DocumentoFirmado;
import page.FirmaClave;
import page.tapTipoFirma;
import page.wsMedsing;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.IOException;


public class CPA001CreacionFirmaClave {

    public void firmaConClave() throws ParserConfigurationException, TransformerException, IOException, AWTException, InterruptedException {

        wsMedsing wsMedsign = new wsMedsing();
        wsMedsign.WsCreacionClave ();

        DriverContext.setUp (Navegador.Chrome, Constants.URL_MEDSIGN);

        tapTipoFirma tipoFirma = new tapTipoFirma ();
        tipoFirma.tabFirma ("FirmaClave");


        FirmaClave firmaClave = new FirmaClave();

        firmaClave.ingresarPassClave ();


        DocumentoFirmado docFirmado = new DocumentoFirmado();
        docFirmado.esperarProceso ();
        docFirmado.procesarFirma ();

        docFirmado.estadoFirmaHsm();

    }


}
