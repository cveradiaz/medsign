package page;

import driver.DriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class tapTipoFirma {

    private WebDriver driver;

    public tapTipoFirma() {
        this.driver = DriverContext.getDriver();
        PageFactory.initElements(this.driver, this);
    }


    public  void tabFirma( String tipoFirma) throws AWTException {
        Robot robot = new Robot ( );

        switch (tipoFirma) {

            case "FirmaClave":
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
                robot.keyRelease (KeyEvent.VK_TAB);

                robot.keyPress (KeyEvent.VK_TAB);
                robot.keyPress (KeyEvent.VK_ENTER);

                robot.keyRelease (KeyEvent.VK_ENTER);

                break;
            case "FirmaHsm":

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
                robot.keyPress (KeyEvent.VK_ENTER);
                robot.keyRelease (KeyEvent.VK_ENTER);

                break;

        }
    }
}
