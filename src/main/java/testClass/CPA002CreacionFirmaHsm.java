package testClass;


import constans.Constants;
import constans.Navegador;
import driver.DriverContext;
import page.DocumentoFirmado;
import page.FirmaHSM;
import page.tapTipoFirma;
import page.wsMedsing;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.IOException;


public class CPA002CreacionFirmaHsm {

    public void firmaConHsm() throws ParserConfigurationException, TransformerException, IOException, AWTException, InterruptedException {

        wsMedsing wsMedsign = new wsMedsing();
        wsMedsign.WsCreacionHsm ();

        DriverContext.setUp (Navegador.Chrome, Constants.URL_MEDSIGN);

        tapTipoFirma tipoFirma = new tapTipoFirma ();
        tipoFirma.tabFirma ("FirmaHsm");


        FirmaHSM firmaHSM = new FirmaHSM ();
        firmaHSM.ingresarPassHsm ();


        DocumentoFirmado docFirmado = new DocumentoFirmado();
        docFirmado.esperarProceso ();
        docFirmado.procesarFirma ();
        docFirmado.estadoFirmaHsm();

    }


}
