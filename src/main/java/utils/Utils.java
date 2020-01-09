package utils;

import constans.Constants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import reporter.EstadoPrueba;
import reporter.ImedReports;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;

import static constans.Constants.AMBIENTE;
import static reporter.EstadoPrueba.PASSED;

public class Utils {

    public static String tipoAmbiente(){
        if(AMBIENTE.equals("QA")){
            return "Certificación";
        }else if(AMBIENTE.equals("INT")){
            return "Integración";
        }else{
            return "Desarrollo";
        }

    }

    public static boolean isEnabled(WebElement element) throws NoSuchElementException {
        System.out.println("Esta el elemento habilitado?:" + element.getAttribute("enabled"));
        return element.getAttribute("enabled").trim().equals("true");
    }

    //Forzamos un enter para eliminar mensaje de Windows (15-10-2019)
    public static void KeyPressMsgWind() throws InterruptedException, AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }


    public static  void leerExcel(String nombreArchivo) throws TransformerException, ParserConfigurationException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        ImedReports.addReport("","", EstadoPrueba.WARNING,false);

        Element rootElement = ((Document) doc).createElement("x:Envelope");
        doc.appendChild(rootElement);

        Element Header = ((Document) doc).createElement("x:Header");
        rootElement.appendChild(Header);

        Element Body = ((Document) doc).createElement("x:Body");
        rootElement.appendChild(Body);

        Element Create = ((Document) doc).createElement("urn:Create");
        Body.appendChild(Create);

        Element Auth = ((Document) doc).createElement("urn:Auth");
        Create.appendChild(Auth);


        FileInputStream file = new FileInputStream(new File(Constants.pathArchivos + nombreArchivo+ ".xlsx"));
        XSSFWorkbook workbook1 = new XSSFWorkbook(file);
        XSSFSheet sheet1 = workbook1.getSheetAt(0);


        String SecretToken = String.valueOf (sheet1.getRow(0).getCell(0));
        String value_SecretToken = String.valueOf (sheet1.getRow(1).getCell(0));
        System.out.println ( ">>> row "+SecretToken  +"-"+value_SecretToken);

        ImedReports.addReport ("leerExecelvalue_SecretToken",value_SecretToken, PASSED,false);


        String SecretKey = String.valueOf(sheet1.getRow(0).getCell(1));
        String value_SecretKey = String.valueOf(sheet1.getRow(1).getCell(1));
        ImedReports.addReport ("leerExecelvalue_value_SecretKey",value_SecretKey, PASSED,false);
        System.out.println (">>> row "+SecretKey  +"-"+value_SecretKey);

        String Name = String.valueOf(sheet1.getRow(0).getCell(2));

        String value_Name = String.valueOf(sheet1.getRow(1).getCell(2));
        ImedReports.addReport ("leerExecelvalue_value_Name",value_Name, PASSED,false);
        System.out.println ( ">>> row "+Name  +"-"+value_Name);

        String Institution = String.valueOf(sheet1.getRow(0).getCell(3));

        String value_Institution = String.valueOf(sheet1.getRow(1).getCell(3));
        ImedReports.addReport ("leerExecelvalue_value_Institution",value_Institution, PASSED,false);
        System.out.println (">>> row "+Institution  +"-"+value_Institution);

        String DocType = String.valueOf(sheet1.getRow(0).getCell(4));

        String value_DocType = String.valueOf(sheet1.getRow(1).getCell(4));
        ImedReports.addReport ("leerExecelvalue_value_DocType",value_DocType, PASSED,false);
        System.out.println (">>> row "+DocType  +"-"+value_DocType);

        String Md5 = String.valueOf(sheet1.getRow(0).getCell(5));

        String value_Md5 = String.valueOf(sheet1.getRow(1).getCell(5));
        ImedReports.addReport ("leerExecelvalue_value_Md5",value_Md5, EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+Md5  +"-"+value_Md5);

        String MimeType = String.valueOf(sheet1.getRow(0).getCell(6));

        String value_MimeType = String.valueOf(sheet1.getRow(1).getCell(6));
        ImedReports.addReport ("leerExecelvalue_value_MimeType",value_MimeType, PASSED,false);
        System.out.println (">>> row "+MimeType  +"-"+value_MimeType);

        String Size = String.valueOf(sheet1.getRow(0).getCell(7));

        String value_Size = String.valueOf(sheet1.getRow(1).getCell(7));
        ImedReports.addReport ("leerExecelvalue_value_Size",value_Size, PASSED,false);
        System.out.println (">>> row "+Size  +"-"+value_Size);

        String pdf = String.valueOf(sheet1.getRow(0).getCell(8));

        String value_pdf = String.valueOf(sheet1.getRow(1).getCell(8));
        ImedReports.addReport ("leerExecelvalue_value_pdf",value_pdf, PASSED,false);
        System.out.println (">>> row "+pdf  +"-"+value_pdf);

        String signers = String.valueOf(sheet1.getRow(0).getCell(9));

        String value_signers = String.valueOf(sheet1.getRow(1).getCell(9));
        ImedReports.addReport ("leerExecelvalue_signers",value_signers, PASSED,false);
        System.out.println (">>> row "+signers  +"-"+value_signers);

        Element elemSecretToken = ((Document) doc).createElement("urn:"+SecretToken);
        elemSecretToken.appendChild( ((Document) doc).createTextNode(value_SecretToken));
        Auth.appendChild( elemSecretToken );

        Element elemSecretKey = ((Document) doc).createElement("urn:"+SecretKey);
        elemSecretKey.appendChild( ((Document) doc).createTextNode(value_SecretKey));
        Auth.appendChild( elemSecretKey );

        Element elemName = ((Document) doc).createElement("urn:"+Name);
        elemName.appendChild( ((Document) doc).createTextNode(value_Name));
        Create.appendChild( elemName );


        Element elemInstitution = ((Document) doc).createElement("urn:"+Institution);
        elemInstitution.appendChild( ((Document) doc).createTextNode(value_Institution));
        Create.appendChild( elemInstitution );

        Element elemDocType = ((Document) doc).createElement("urn:"+DocType);
        elemDocType.appendChild( ((Document) doc).createTextNode(value_DocType));
        Create.appendChild( elemDocType );

        Element elemMd5 = ((Document) doc).createElement("urn:"+Md5);
        elemMd5.appendChild( ((Document) doc).createTextNode(value_Md5));
        Create.appendChild( elemMd5 );

        Element elemMimeType = ((Document) doc).createElement("urn:"+MimeType);
        elemMimeType.appendChild( ((Document) doc).createTextNode(value_MimeType));
        Create.appendChild( elemMimeType );

        Element elemSize = ((Document) doc).createElement("urn:"+Size);
        elemSize.appendChild( ((Document) doc).createTextNode(value_Size));
        Create.appendChild( elemSize );

        Element elempdf = ((Document) doc).createElement("urn:"+pdf);
        elempdf.appendChild( ((Document) doc).createTextNode(value_pdf));
        Create.appendChild( elempdf );

        Element elemsigners = ((Document) doc).createElement("urn:"+signers);
        elemsigners.appendChild( ((Document) doc).createTextNode(value_signers));
        Create.appendChild( elemsigners );

        // escribimos el contenido en un archivo .xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer ();
        DOMSource source = new DOMSource( doc );

        StreamResult result = new StreamResult(new File(Constants.pathArchivos + nombreArchivo + ".xml"));
        transformer.transform(source, result);
        System.out.println("archivo generado!");

    }


    public static String obtenerResponse(String urlRequest, String archivoXml) throws IOException {

        File xmlFile = new File( Constants.pathArchivos + archivoXml +".xml");
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder(); String line = bufReader.readLine();
        while( line != null){
            sb.append(line).append("\n"); line = bufReader.readLine();
        }
        String xml2String = sb.toString();
        bufReader.close();
        xml2String = xml2String.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();

        try {
            //String url = "http://liquidador3qa.i-med.cl/wsImed/wsConfirmacion.asmx";
            String url = urlRequest;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");

            String xml;


            xml = xml2String;


            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            //System.out.println(responseStatus);
            BufferedReader res = new BufferedReader(new InputStreamReader( con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = res.readLine()) != null) {
                response.append(inputLine);
            }

            //System.out.println("response:" + response.toString());
            res.close();
            //System.out.println("response:" + response.toString());
            return response.toString();

        } catch (Exception e) {
            System.out.println(e);
        }

        return "";

    }


        public static String buscarTag(String respuesta, String tagNameBuscar){

        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(respuesta));
        Document dostor = null;
        try {
            dostor = documentBuilder.parse(inputSource);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(">>> metodo "+dostor.getElementsByTagName(tagNameBuscar).getLength());
        NodeList tagName = dostor.getElementsByTagName(tagNameBuscar);




        System.out.println(tagNameBuscar +" : "+tagName.item(0).getTextContent());
        String url = tagName.item(0).getTextContent();

        return url;

    }





}
