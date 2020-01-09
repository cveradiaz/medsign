package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static reporter.EstadoPrueba.PASSED;

public class MetodosMedsign {

    public static  void leerExcel(String nombreArchivo) throws TransformerException, ParserConfigurationException, IOException {


        String so = System.getProperty( "os.name" );
        String pathArchivos =""; String rutaArchivos="";
        int RowCount;
        int cantidadFilas=0;
        int [] contador = {};

        if(so.equals( "Windows 10" )){
            pathArchivos = new File( "" ).getAbsolutePath();
            rutaArchivos = pathArchivos.concat( "\\dataFile\\" );
        }else {
            pathArchivos = new File( "." ).getAbsolutePath();
            rutaArchivos = pathArchivos.concat( "//dataFile//" );
        }



        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        ImedReports.addReport("", "", EstadoPrueba.WARNING, false);

        switch (nombreArchivo) {

            case "CreateClave":
                Element rootElement = ((org.w3c.dom.Document) doc).createElement("x:Envelope");
                doc.appendChild(rootElement);

                Element Header = ((org.w3c.dom.Document) doc).createElement("x:Header");
                rootElement.appendChild(Header);

                Element Body = ((org.w3c.dom.Document) doc).createElement("x:Body");
                rootElement.appendChild(Body);
                Element Create = ((org.w3c.dom.Document) doc).createElement("x:Create");
                Body.appendChild(Create);

                Element Auth = ((org.w3c.dom.Document) doc).createElement("x:Auth");
                Create.appendChild(Auth);


                FileInputStream file = new FileInputStream(new File(rutaArchivos + nombreArchivo + ".xlsx"));
                XSSFWorkbook workbook1 = new XSSFWorkbook(file);
                XSSFSheet sheet1 = workbook1.getSheetAt(0);

                String SecretToken = String.valueOf(sheet1.getRow(0).getCell(0));
                String value_SecretToken = String.valueOf(sheet1.getRow(1).getCell(0));
                System.out.println(">>> row " + SecretToken + "-" + value_SecretToken);
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
                ImedReports.addReport ("leerExecelvalue_value_Md5",value_Md5,EstadoPrueba.PASSED,false);
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

                Element elemSecretToken = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretToken);
                elemSecretToken.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretToken));
                Auth.appendChild( elemSecretToken );

                Element elemSecretKey = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretKey);
                elemSecretKey.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretKey));
                Auth.appendChild( elemSecretKey );

                Element elemName = ((org.w3c.dom.Document) doc).createElement("urn:"+Name);
                elemName.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Name));
                Create.appendChild( elemName );


                Element elemInstitution = ((org.w3c.dom.Document) doc).createElement("urn:"+Institution);
                elemInstitution.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Institution));
                Create.appendChild( elemInstitution );

                Element elemDocType = ((org.w3c.dom.Document) doc).createElement("urn:"+DocType);
                elemDocType.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_DocType));
                Create.appendChild( elemDocType );

                Element elemMd5 = ((org.w3c.dom.Document) doc).createElement("urn:"+Md5);
                elemMd5.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Md5));
                Create.appendChild( elemMd5 );

                Element elemMimeType = ((org.w3c.dom.Document) doc).createElement("urn:"+MimeType);
                elemMimeType.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_MimeType));
                Create.appendChild( elemMimeType );

                Element elemSize = ((org.w3c.dom.Document) doc).createElement("urn:"+Size);
                elemSize.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Size));
                Create.appendChild( elemSize );

                Element elempdf = ((org.w3c.dom.Document) doc).createElement("urn:"+pdf);
                elempdf.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_pdf));
                Create.appendChild( elempdf );

                Element elemsigners = ((org.w3c.dom.Document) doc).createElement("urn:"+signers);
                elemsigners.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_signers));
                Create.appendChild( elemsigners );

                break;

            case "CreateHsm":
                 rootElement = ((org.w3c.dom.Document) doc).createElement("x:Envelope");
                doc.appendChild(rootElement);

                 Header = ((org.w3c.dom.Document) doc).createElement("x:Header");
                rootElement.appendChild(Header);

                 Body = ((org.w3c.dom.Document) doc).createElement("x:Body");
                rootElement.appendChild(Body);
                 Create = ((org.w3c.dom.Document) doc).createElement("x:Create");
                Body.appendChild(Create);

                 Auth = ((org.w3c.dom.Document) doc).createElement("x:Auth");
                Create.appendChild(Auth);


                 file = new FileInputStream(new File(rutaArchivos + nombreArchivo + ".xlsx"));
                 workbook1 = new XSSFWorkbook(file);
                 sheet1 = workbook1.getSheetAt(0);

                 SecretToken = String.valueOf(sheet1.getRow(0).getCell(0));
                 value_SecretToken = String.valueOf(sheet1.getRow(1).getCell(0));
                System.out.println(">>> row " + SecretToken + "-" + value_SecretToken);
                ImedReports.addReport ("leerExecelvalue_SecretToken",value_SecretToken, PASSED,false);


                 SecretKey = String.valueOf(sheet1.getRow(0).getCell(1));
                 value_SecretKey = String.valueOf(sheet1.getRow(1).getCell(1));
                ImedReports.addReport ("leerExecelvalue_value_SecretKey",value_SecretKey, PASSED,false);
                System.out.println (">>> row "+SecretKey  +"-"+value_SecretKey);

                 Name = String.valueOf(sheet1.getRow(0).getCell(2));

                 value_Name = String.valueOf(sheet1.getRow(1).getCell(2));
                ImedReports.addReport ("leerExecelvalue_value_Name",value_Name, PASSED,false);
                System.out.println ( ">>> row "+Name  +"-"+value_Name);

                 Institution = String.valueOf(sheet1.getRow(0).getCell(3));

                 value_Institution = String.valueOf(sheet1.getRow(1).getCell(3));
                ImedReports.addReport ("leerExecelvalue_value_Institution",value_Institution, PASSED,false);
                System.out.println (">>> row "+Institution  +"-"+value_Institution);

                 DocType = String.valueOf(sheet1.getRow(0).getCell(4));

                 value_DocType = String.valueOf(sheet1.getRow(1).getCell(4));
                ImedReports.addReport ("leerExecelvalue_value_DocType",value_DocType, PASSED,false);
                System.out.println (">>> row "+DocType  +"-"+value_DocType);

                 Md5 = String.valueOf(sheet1.getRow(0).getCell(5));

                 value_Md5 = String.valueOf(sheet1.getRow(1).getCell(5));
                ImedReports.addReport ("leerExecelvalue_value_Md5",value_Md5,EstadoPrueba.PASSED,false);
                System.out.println (">>> row "+Md5  +"-"+value_Md5);

                 MimeType = String.valueOf(sheet1.getRow(0).getCell(6));

                 value_MimeType = String.valueOf(sheet1.getRow(1).getCell(6));
                ImedReports.addReport ("leerExecelvalue_value_MimeType",value_MimeType, PASSED,false);
                System.out.println (">>> row "+MimeType  +"-"+value_MimeType);

                 Size = String.valueOf(sheet1.getRow(0).getCell(7));

                 value_Size = String.valueOf(sheet1.getRow(1).getCell(7));
                ImedReports.addReport ("leerExecelvalue_value_Size",value_Size, PASSED,false);
                System.out.println (">>> row "+Size  +"-"+value_Size);

                 pdf = String.valueOf(sheet1.getRow(0).getCell(8));

                 value_pdf = String.valueOf(sheet1.getRow(1).getCell(8));
                ImedReports.addReport ("leerExecelvalue_value_pdf",value_pdf, PASSED,false);
                System.out.println (">>> row "+pdf  +"-"+value_pdf);

                 signers = String.valueOf(sheet1.getRow(0).getCell(9));

                 value_signers = String.valueOf(sheet1.getRow(1).getCell(9));
                ImedReports.addReport ("leerExecelvalue_signers",value_signers, PASSED,false);
                System.out.println (">>> row "+signers  +"-"+value_signers);

                 elemSecretToken = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretToken);
                elemSecretToken.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretToken));
                Auth.appendChild( elemSecretToken );

                 elemSecretKey = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretKey);
                elemSecretKey.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretKey));
                Auth.appendChild( elemSecretKey );

                 elemName = ((org.w3c.dom.Document) doc).createElement("urn:"+Name);
                elemName.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Name));
                Create.appendChild( elemName );


                 elemInstitution = ((org.w3c.dom.Document) doc).createElement("urn:"+Institution);
                elemInstitution.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Institution));
                Create.appendChild( elemInstitution );

                 elemDocType = ((org.w3c.dom.Document) doc).createElement("urn:"+DocType);
                elemDocType.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_DocType));
                Create.appendChild( elemDocType );

                 elemMd5 = ((org.w3c.dom.Document) doc).createElement("urn:"+Md5);
                elemMd5.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Md5));
                Create.appendChild( elemMd5 );

                 elemMimeType = ((org.w3c.dom.Document) doc).createElement("urn:"+MimeType);
                elemMimeType.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_MimeType));
                Create.appendChild( elemMimeType );

                 elemSize = ((org.w3c.dom.Document) doc).createElement("urn:"+Size);
                elemSize.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Size));
                Create.appendChild( elemSize );

                 elempdf = ((org.w3c.dom.Document) doc).createElement("urn:"+pdf);
                elempdf.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_pdf));
                Create.appendChild( elempdf );

                 elemsigners = ((org.w3c.dom.Document) doc).createElement("urn:"+signers);
                elemsigners.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_signers));
                Create.appendChild( elemsigners );

                break;
            case "Show":
                rootElement = ((org.w3c.dom.Document) doc).createElement("soapenv:Envelope");
                doc.appendChild(rootElement);

                Header = ((org.w3c.dom.Document) doc).createElement("soapenv:Header");
                rootElement.appendChild(Header);

                Body = ((org.w3c.dom.Document) doc).createElement("soapenv:Body");
                rootElement.appendChild(Body);
                Element Show = ((org.w3c.dom.Document) doc).createElement("urn:Show");
                Body.appendChild(Show);

                Auth = ((org.w3c.dom.Document) doc).createElement("Auth");
                Show.appendChild(Auth);


                FileInputStream file1 = new FileInputStream(new File(rutaArchivos + nombreArchivo + ".xlsx"));
                workbook1 = new XSSFWorkbook(file1);
                sheet1 = workbook1.getSheetAt(0);

                SecretToken = String.valueOf(sheet1.getRow(0).getCell(0));
                value_SecretToken = String.valueOf(sheet1.getRow(1).getCell(0));
                System.out.println(">>> row " + SecretToken + "-" + value_SecretToken);
                ImedReports.addReport ("leerExecelvalue_SecretToken",value_SecretToken, PASSED,false);


                SecretKey = String.valueOf(sheet1.getRow(0).getCell(1));
                value_SecretKey = String.valueOf(sheet1.getRow(1).getCell(1));
                ImedReports.addReport ("leerExecelvalue_value_SecretKey",value_SecretKey, PASSED,false);
                System.out.println (">>> row "+SecretKey  +"-"+value_SecretKey);

                String Id = String.valueOf(sheet1.getRow(0).getCell(2));

                String value_Id = String.valueOf(sheet1.getRow(1).getCell(2));
                ImedReports.addReport ("leerExecelvalue_value_Name",value_Id, PASSED,false);
                System.out.println ( ">>> row "+Id  +"-"+value_Id);

                Institution = String.valueOf(sheet1.getRow(0).getCell(3));

                value_Institution = String.valueOf(sheet1.getRow(1).getCell(3));
                ImedReports.addReport ("leerExecelvalue_value_Institution",value_Institution, PASSED,false);
                System.out.println (">>> row "+Institution  +"-"+value_Institution);

                elemSecretToken = ((org.w3c.dom.Document) doc).createElement(SecretToken);
                elemSecretToken.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretToken));
                Auth.appendChild( elemSecretToken );

                elemSecretKey = ((org.w3c.dom.Document) doc).createElement(SecretKey);
                elemSecretKey.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretKey));
                Auth.appendChild( elemSecretKey );

                Element elemId = ((org.w3c.dom.Document) doc).createElement(Id);
                elemId.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Id));
                Show.appendChild( elemId );


                elemInstitution = ((org.w3c.dom.Document) doc).createElement(Institution);
                elemInstitution.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Institution));
                Show.appendChild( elemInstitution );
                break;

            case "CreateTemplate":

                rootElement = ((org.w3c.dom.Document) doc).createElement("x:Envelope");
                doc.appendChild(rootElement);

                Header = ((org.w3c.dom.Document) doc).createElement("x:Header");
                rootElement.appendChild(Header);

                Body = ((org.w3c.dom.Document) doc).createElement("x:Body");
                rootElement.appendChild(Body);
                Element CreateTemplate = ((org.w3c.dom.Document) doc).createElement("x:CreateTemplate");
                Body.appendChild(CreateTemplate);

                Auth = ((org.w3c.dom.Document) doc).createElement("x:Auth");
                CreateTemplate.appendChild(Auth);


                file = new FileInputStream(new File(rutaArchivos + nombreArchivo + ".xlsx"));
                workbook1 = new XSSFWorkbook(file);
                sheet1 = workbook1.getSheetAt(0);

                SecretToken = String.valueOf(sheet1.getRow(0).getCell(0));
                value_SecretToken = String.valueOf(sheet1.getRow(1).getCell(0));
                System.out.println(">>> row " + SecretToken + "-" + value_SecretToken);
                ImedReports.addReport ("leerExecelvalue_SecretToken",value_SecretToken, PASSED,false);


                SecretKey = String.valueOf(sheet1.getRow(0).getCell(1));
                value_SecretKey = String.valueOf(sheet1.getRow(1).getCell(1));
                ImedReports.addReport ("leerExecelvalue_value_SecretKey",value_SecretKey, PASSED,false);
                System.out.println (">>> row "+SecretKey  +"-"+value_SecretKey);

                Name = String.valueOf(sheet1.getRow(0).getCell(2));

                value_Name = String.valueOf(sheet1.getRow(1).getCell(2));
                ImedReports.addReport ("leerExecelvalue_value_Name",value_Name, PASSED,false);
                System.out.println ( ">>> row "+Name  +"-"+value_Name);

                Institution = String.valueOf(sheet1.getRow(0).getCell(3));

                value_Institution = String.valueOf(sheet1.getRow(1).getCell(3));
                ImedReports.addReport ("leerExecelvalue_value_Institution",value_Institution, PASSED,false);
                System.out.println (">>> row "+Institution  +"-"+value_Institution);

                DocType = String.valueOf(sheet1.getRow(0).getCell(4));

                value_DocType = String.valueOf(sheet1.getRow(1).getCell(4));
                ImedReports.addReport ("leerExecelvalue_value_DocType",value_DocType, PASSED,false);
                System.out.println (">>> row "+DocType  +"-"+value_DocType);

                String Fields = String.valueOf(sheet1.getRow(0).getCell(5));

                String value_fields = String.valueOf(sheet1.getRow(1).getCell(5));
                ImedReports.addReport ("leerExecelvalue_value_fields",value_fields, PASSED,false);
                System.out.println (">>> row "+Fields  +"-"+value_fields);

                String Signers = String.valueOf(sheet1.getRow(0).getCell(6));

                value_signers = String.valueOf(sheet1.getRow(1).getCell(6));
                ImedReports.addReport ("leerExecelvalue_signers",value_signers, PASSED,false);
                System.out.println (">>> row "+Signers  +"-"+value_signers);

                elemSecretToken = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretToken);
                elemSecretToken.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretToken));
                Auth.appendChild( elemSecretToken );

                elemSecretKey = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretKey);
                elemSecretKey.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretKey));
                Auth.appendChild( elemSecretKey );

                elemName = ((org.w3c.dom.Document) doc).createElement("urn:"+Name);
                elemName.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Name));
                CreateTemplate.appendChild( elemName );


                elemInstitution = ((org.w3c.dom.Document) doc).createElement("urn:"+Institution);
                elemInstitution.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Institution));
                CreateTemplate.appendChild( elemInstitution );

                elemDocType = ((org.w3c.dom.Document) doc).createElement("urn:"+DocType);
                elemDocType.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_DocType));
                CreateTemplate.appendChild( elemDocType );

                Element elemFields = ((org.w3c.dom.Document) doc).createElement("urn:"+Fields);
                elemFields.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_fields));
                CreateTemplate.appendChild( elemFields );

                elemsigners = ((org.w3c.dom.Document) doc).createElement("urn:"+Signers);
                elemsigners.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_signers));
                CreateTemplate.appendChild( elemsigners );

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + nombreArchivo);
        }

        // escribimos el contenido en un archivo .xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File(rutaArchivos + nombreArchivo + ".xml"));
        transformer.transform(source, result);
        System.out.println("archivo generado!");


    }



    public static String obtenerResponse(String urlRequest, String archivoXml) throws IOException {

        String so = System.getProperty( "os.name" );
        String pathArchivos =""; String rutaArchivos="";
        int RowCount;
        int cantidadFilas=0;
        int [] contador = {};

        if(so.equals( "Windows 10" )){
            pathArchivos = new File( "" ).getAbsolutePath();
            rutaArchivos = pathArchivos.concat( "\\dataFile\\" );
        }else {
            pathArchivos = new File( "." ).getAbsolutePath();
            rutaArchivos = pathArchivos.concat( "//dataFile//" );
        }

        File xmlFile = new File(rutaArchivos + archivoXml + ".xml");
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();

        for(String line = bufReader.readLine(); line != null; line = bufReader.readLine()) {
            sb.append(line).append("\n");
        }

        String xml2String = sb.toString();
        bufReader.close();
        xml2String = xml2String.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();

        try {
            URL obj = new URL(urlRequest);
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml2String);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            System.out.println("archivo !"+responseStatus);
            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();

            String inputLine;
            while((inputLine = res.readLine()) != null) {
                response.append(inputLine);
            }

            res.close();
            return response.toString();
        } catch (Exception var17) {
            System.out.println(var17);
            return "";
        }
    }



    public static String buscarTag(String respuesta, String tagRaiz, String tagNameBuscar) {
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException var12) {
            var12.printStackTrace();
        }

        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(respuesta));
        Document doc = null;

        try {
            doc = documentBuilder.parse(inputSource);
        } catch (SAXException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        String tagResponse = "";
        NodeList tagName = doc.getElementsByTagName(tagRaiz);

        for(int i = 0; i < tagName.getLength(); ++i) {
            NodeList Code = ((Element)tagName.item(i)).getElementsByTagName(tagNameBuscar);
            tagResponse = Code.item(i).getTextContent();
        }

        return tagResponse;
    }

    public static String buscarTag(String respuesta, String tagNameBuscar) {
        DocumentBuilder documentBuilder = null;
        String tagResponse = "";

        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException var9) {
            var9.printStackTrace();
        }

        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(respuesta));
        Document doc = null;

        try {
            doc = documentBuilder.parse(inputSource);
        } catch (SAXException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        NodeList tagName = doc.getElementsByTagName(tagNameBuscar);
        tagResponse = tagName.item(0).getTextContent();
        return tagResponse;
    }

}
