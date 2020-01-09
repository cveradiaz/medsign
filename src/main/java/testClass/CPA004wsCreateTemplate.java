package testClass;


import constans.Constants;
import constans.Navegador;
import driver.DriverContext;
import page.wsMedsing;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class CPA004wsCreateTemplate {

    public void wsCreateTemplate() throws ParserConfigurationException, TransformerException, IOException {

        wsMedsing wsMedsign = new wsMedsing();
        wsMedsign.WsCreateTemplate ();

        DriverContext.setUp (Navegador.Chrome, Constants.URL_MEDSIGN);


    }


}
