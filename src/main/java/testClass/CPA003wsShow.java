package testClass;


import constans.Constants;
import constans.Navegador;
import driver.DriverContext;
import page.wsMedsing;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class CPA003wsShow {

    public void wsShow() throws ParserConfigurationException, TransformerException, IOException {

        wsMedsing wsMedsign = new wsMedsing();
        wsMedsign.WsCreacionShow ();

        DriverContext.setUp (Navegador.Chrome, Constants.URL_MEDSIGN);


    }


}
